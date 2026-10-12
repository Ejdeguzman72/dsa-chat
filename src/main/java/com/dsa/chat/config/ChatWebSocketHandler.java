
package com.dsa.chat.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ChatWebSocketHandler extends TextWebSocketHandler {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(ChatWebSocketHandler.class);

    private final Set<WebSocketSession> sessions =
            ConcurrentHashMap.newKeySet();

    @Override
    public void afterConnectionEstablished(
            WebSocketSession session) throws Exception {

        sessions.add(session);

        LOGGER.info("User connected: {}", session.getId());

        broadcastMessage(
                "User " + session.getId() + " joined the chat"
        );
    }

    @Override
    protected void handleTextMessage(
            WebSocketSession session,
            TextMessage message) throws Exception {

        String payload = message.getPayload().trim();

        if (payload.isEmpty()) {
            return;
        }

        String chatMessage =
                "[" + session.getId() + "]: " + payload;

        LOGGER.info("Message received: {}", chatMessage);

        broadcastMessage(chatMessage);
    }

    @Override
    public void afterConnectionClosed(
            WebSocketSession session,
            CloseStatus status) throws Exception {

        sessions.remove(session);

        LOGGER.info("User disconnected: {}", session.getId());

        broadcastMessage(
                "User " + session.getId() + " left the chat"
        );
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
                    // Connection already closed.
                }
            }
        }
    }
}
