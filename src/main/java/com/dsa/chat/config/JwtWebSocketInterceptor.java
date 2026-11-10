
package com.dsa.chat.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@Component
public class JwtWebSocketInterceptor implements HandshakeInterceptor {

    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtWebSocketInterceptor.class);

    public JwtWebSocketInterceptor(
            JwtUtil jwtUtil,
            UserDetailsService userDetailsService) {

        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }


    @Override
    public boolean beforeHandshake(
            ServerHttpRequest request,
            ServerHttpResponse response,
            WebSocketHandler wsHandler,
            Map<String, Object> attributes) {

        LOGGER.info("WebSocket handshake requested");

        String token = extractToken(request);

        if (token == null || token.isBlank()) {
            LOGGER.warn("WebSocket rejected: Missing JWT");
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return false;
        }

        try {
            String username = jwtUtil.extractUsername(token);

            LOGGER.debug("WebSocket JWT username extracted");

            if (username == null || username.isBlank()) {
                LOGGER.warn("WebSocket rejected: Empty username");
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                return false;
            }

            UserDetails userDetails =
                    userDetailsService.loadUserByUsername(username);

            boolean valid =
                    jwtUtil.validateToken(token, userDetails);

            if (!valid) {
                LOGGER.warn("WebSocket rejected: Invalid or expired JWT");
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                return false;
            }

            if (!userDetails.isEnabled() ||
                    !userDetails.isAccountNonLocked() ||
                    !userDetails.isAccountNonExpired() ||
                    !userDetails.isCredentialsNonExpired()) {

                LOGGER.warn("WebSocket rejected: User account unavailable");
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                return false;
            }

            attributes.put("username", userDetails.getUsername());

            LOGGER.info("WebSocket authentication successful");

            return true;

        } catch (Exception exception) {

            LOGGER.warn(
                    "WebSocket authentication failed: {}",
                    exception.getClass().getSimpleName()
            );

            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return false;
        }
    }


    private String extractToken(ServerHttpRequest request) {

        String authorization = request.getHeaders()
                .getFirst(HttpHeaders.AUTHORIZATION);

        // Postman
        if (authorization != null &&
                authorization.startsWith("Bearer ")) {

            return authorization.substring(7).trim();
        }

        // Browser
        return UriComponentsBuilder
                .fromUri(request.getURI())
                .build()
                .getQueryParams()
                .getFirst("token");
    }

    @Override
    public void afterHandshake(
            ServerHttpRequest request,
            ServerHttpResponse response,
            WebSocketHandler wsHandler,
            Exception exception) {
        // No additional action required.
    }
}
