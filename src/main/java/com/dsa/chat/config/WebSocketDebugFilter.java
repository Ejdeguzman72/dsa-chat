
package com.dsa.chat.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class WebSocketDebugFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        if (request.getRequestURI().equals("/chat")) {

            System.out.println("===== WEBSOCKET DEBUG =====");

            System.out.println(
                    "Connection: " + request.getHeader("Connection")
            );

            System.out.println(
                    "Upgrade: " + request.getHeader("Upgrade")
            );

            System.out.println(
                    "All Connection headers: " +
                    Collections.list(
                            request.getHeaders("Connection")
                    )
            );

            System.out.println("===========================");
        }

        filterChain.doFilter(request, response);
    }
}
