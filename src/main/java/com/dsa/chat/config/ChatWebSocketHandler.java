package com.dsa.chat.config;

import com.dsa.chat.domain.ChatMessageAddUpdateRequest;
import com.dsa.chat.domain.UserSearchResponse;
import com.dsa.chat.service.AiChatService;
import com.dsa.chat.service.ChatMessageService;
import com.dsa.chat.service.DsaUserService;
import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

@Component
public class ChatWebSocketHandler extends TextWebSocketHandler {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(ChatWebSocketHandler.class);

    private final ChatMessageService chatMessageService;
    private final DsaUserService userService;
    private final AiChatService aiChatService;

    private final Set<WebSocketSession> sessions =
            ConcurrentHashMap.newKeySet();

    private final ExecutorService executorService =
            Executors.newFixedThreadPool(4);

    public ChatWebSocketHandler(
            ChatMessageService chatMessageService,
            DsaUserService userService,
            AiChatService aiChatService) {

        this.chatMessageService = chatMessageService;
        this.userService = userService;
        this.aiChatService = aiChatService;
    }

    private String getUsername(WebSocketSession session) {
        return (String) session.getAttributes().get("username");
    }

    @Override
    public void afterConnectionEstablished(
            WebSocketSession session) throws Exception {

        String username = getUsername(session);

        if (username == null || username.isBlank()) {
            session.close(CloseStatus.POLICY_VIOLATION);
            return;
        }

        sessions.add(session);

        LOGGER.info("User connected: {}", username);

        broadcastMessage(username + " joined the chat");
    }

    @Override
    protected void handleTextMessage(
            WebSocketSession session,
            TextMessage message) throws Exception {

        String username = getUsername(session);

        if (username == null || username.isBlank()) {
            session.close(CloseStatus.POLICY_VIOLATION);
            return;
        }

        String payload = message.getPayload().trim();

        if (payload.isEmpty()) {
            return;
        }

        // Retrieve user
        UserSearchResponse user =
                userService.retrieveUserByUsername(username);

        if (user == null || user.getAuthInfo() == null) {
            LOGGER.warn("User not found: {}", username);
            session.close(CloseStatus.POLICY_VIOLATION);
            return;
        }

        // Save user message to database
        ChatMessageAddUpdateRequest request = new ChatMessageAddUpdateRequest();

        request.setDsaUser(user.getAuthInfo());
        request.setContent(payload);
        request.setSentDatetime(LocalDateTime.now());

        chatMessageService.sendMessage(request);

        // Broadcast user message
        String chatMessage = "[" + username + "]: " + payload;

        LOGGER.info("Message received: {}", chatMessage);

        broadcastMessage(chatMessage);

        // Check if message is directed to AI
        if (isAiCommand(payload)) {

            String question = payload.substring(3).trim();

            if (question.isEmpty()) {
                sendToSession(session, "[DSA AI]: Please enter a question.");
                return;
            }

            processAiMessage(session, username, question);
        }
    }

    private boolean isAiCommand(String payload) {

        return payload.regionMatches(
                true, 0, "@AI", 0, 3)
                && (payload.length() == 3
                || Character.isWhitespace(payload.charAt(3)));
    }

    private void processAiMessage(WebSocketSession session, String username, String question) {

        try {
            executorService.submit(() -> {

                try {
                    LOGGER.info("Processing AI request from user: {}", username);

                    String aiResponse = aiChatService.askAI(question);

                    if (aiResponse == null ||
                            aiResponse.isBlank()) {

                        sendToSession(session, "[DSA AI]: No response was generated.");
                        return;
                    }

                    String aiMessage = "[DSA AI]: " + aiResponse;

                    LOGGER.info("AI response generated for user: {}", username);

                    broadcastMessage(aiMessage);

                } catch (Exception e) {

                    LOGGER.error("AI request failed for user: {}", username, e);

                    sendToSession(session, "[DSA AI]: Sorry, I couldn't process your request.");
                }
            });

        } catch (RejectedExecutionException e) {

            LOGGER.error("AI executor rejected request", e);

            sendToSession(session, "[DSA AI]: AI is currently unavailable.");
        }
    }

    private void sendToSession(WebSocketSession session, String message) {

        if (!session.isOpen()) {
            return;
        }

        try {
            synchronized (session) {
                if (session.isOpen()) {
                    session.sendMessage(new TextMessage(message));
                }
            }

        } catch (IOException e) {
            LOGGER.warn("Failed to send message to session {}", session.getId(), e);
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {

        boolean removed = sessions.remove(session);

        String username = getUsername(session);

        LOGGER.info("User disconnected: {}", username);

        if (removed && username != null) {
            broadcastMessage(username + " left the chat");
        }
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {

        LOGGER.warn("WebSocket error for session {}", session.getId(), exception);

        sessions.remove(session);

        if (session.isOpen()) {
            session.close(CloseStatus.SERVER_ERROR);
        }
    }

    private void broadcastMessage(String message) {

        for (WebSocketSession client : sessions) {

            if (!client.isOpen()) {
                sessions.remove(client);
                continue;
            }

            try {
                synchronized (client) {

                    if (client.isOpen()) {
                        client.sendMessage(
                                new TextMessage(message)
                        );
                    }
                }

            } catch (IOException exception) {

                LOGGER.warn(
                        "Failed to send message to {}",
                        client.getId(),
                        exception
                );

                sessions.remove(client);

                try {
                    client.close(CloseStatus.SERVER_ERROR);
                } catch (IOException ignored) {
                    // Connection already closed
                }
            }
        }
    }

    @PreDestroy
    public void shutdown() {

        LOGGER.info("Shutting down AI executor");

        executorService.shutdown();
    }
}