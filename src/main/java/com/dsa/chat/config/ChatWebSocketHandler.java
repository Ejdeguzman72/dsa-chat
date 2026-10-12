
package com.dsa.chat.config;

import com.dsa.chat.domain.ChatMessageAddUpdateRequest;
import com.dsa.chat.domain.DsaUser;
import com.dsa.chat.domain.UserSearchResponse;
import com.dsa.chat.service.ChatMessageService;
import com.dsa.chat.service.DsaUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ChatWebSocketHandler extends TextWebSocketHandler {
    private final ChatMessageService chatMessageService;
    private final DsaUserService userService;
    private static final Logger LOGGER =
            LoggerFactory.getLogger(ChatWebSocketHandler.class);

    private final Set<WebSocketSession> sessions =
            ConcurrentHashMap.newKeySet();

    public ChatWebSocketHandler(ChatMessageService chatMessageService, DsaUserService userService) {
        this.chatMessageService = chatMessageService;
        this.userService = userService;
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

        UserSearchResponse user = userService.retrieveUserByUsername(username);
        ChatMessageAddUpdateRequest request = new ChatMessageAddUpdateRequest();
        request.setDsaUser(user.getAuthInfo());
        request.setContent(payload);
        request.setSentDatetime(LocalDateTime.now());
        chatMessageService.sendMessage(request);

        String chatMessage =
                "[" + username + "]: " + payload;

        LOGGER.info("Message received: {}", chatMessage);

        broadcastMessage(chatMessage);
    }

    @Override
    public void afterConnectionClosed(
            WebSocketSession session,
            CloseStatus status) throws Exception {

        boolean removed = sessions.remove(session);

        String username = getUsername(session);

        LOGGER.info("User disconnected: {}", username);

        if (removed && username != null) {
            broadcastMessage(username + " left the chat");
        }
    }

    @Override
    public void handleTransportError(
            WebSocketSession session,
            Throwable exception) throws Exception {

        LOGGER.warn(
                "WebSocket error for session {}",
                session.getId(),
                exception
        );

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
                }
            }
        }
    }
}
