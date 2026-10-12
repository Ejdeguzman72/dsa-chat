
package com.dsa.chat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final ChatWebSocketHandler chatHandler;
    private final JwtWebSocketInterceptor jwtInterceptor;

    public WebSocketConfig(
            ChatWebSocketHandler chatHandler,
            JwtWebSocketInterceptor jwtInterceptor) {

        this.chatHandler = chatHandler;
        this.jwtInterceptor = jwtInterceptor;
    }

    @Override
    public void registerWebSocketHandlers(
            WebSocketHandlerRegistry registry) {

        registry.addHandler(chatHandler, "/chat")
                .addInterceptors(jwtInterceptor)
                .setAllowedOrigins("*");
    }
}
