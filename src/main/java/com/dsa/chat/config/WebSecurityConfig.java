
package com.dsa.chat.config;

import com.dsa.chat.domain.UriConstants;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private final JwtTokenFilter jwtTokenFilter;

    public WebSecurityConfig(JwtTokenFilter jwtTokenFilter) {
        this.jwtTokenFilter = jwtTokenFilter;
    }

    @Bean
    public FilterRegistrationBean<JwtTokenFilter> jwtTokenFilterRegistration(JwtTokenFilter jwtTokenFilter) {
        FilterRegistrationBean<JwtTokenFilter> registration = new FilterRegistrationBean<>(jwtTokenFilter);
        registration.setEnabled(false);

        return registration;
    }

    @Bean
    @Order(1)
    public SecurityFilterChain webSocketSecurityFilterChain(
            HttpSecurity http) throws Exception {

        http
                .securityMatcher("/chat")
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()
                );

        return http.build();
    }

    @Bean
    @Order(2)
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(
                        corsConfigurationSource()
                ))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                UriConstants.AUTHORIZED_REQUESTS_PATH
                        ).permitAll()
                        .requestMatchers(HttpMethod.GET,UriConstants.GET_ALL_USERS).hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET,UriConstants.GET_USER_BY_ID).hasAnyRole("ADMIN","MODERATOR","USER")
                        .requestMatchers(HttpMethod.GET,UriConstants.GET_USER_BY_USERNAME).hasAnyRole("ADMIN","MODERATOR","USER")
                        .requestMatchers(HttpMethod.PUT,UriConstants.UPDATE_DSA_USER_INFO).hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE,UriConstants.DELETE_USER_BY_ID).hasRole("ADMIN")

                        .requestMatchers(HttpMethod.GET,UriConstants.GET_ALL_MESSAGES).hasAnyRole("ADMIN","MODERATOR","USER")
                        .requestMatchers(HttpMethod.GET,UriConstants.GET_MESSAGES_BY_USER).hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET,UriConstants.GET_MESSAGE_BY_ID).hasAnyRole("ADMIN","MODERATOR","USER")
                        .requestMatchers(HttpMethod.POST,UriConstants.SAVE_MESSAGE_INFO).hasAnyRole("ADMIN","MODERATOR","USER")
                        .requestMatchers(HttpMethod.PUT,UriConstants.UPDATE_MESSAGE_INFO).hasAnyRole("ADMIN","MODERATOR","USER")
                        .requestMatchers(HttpMethod.DELETE,UriConstants.DELETE_MESSAGE).hasRole("ADMIN")

                        .requestMatchers(HttpMethod.GET,UriConstants.GET_ALL_EVENTS).hasAnyRole("ADMIN","MODERATOR","USER")
                        .requestMatchers(HttpMethod.GET,UriConstants.GET_PAST_EVENTS).hasAnyRole("ADMIN","MODERATOR","USER")
                        .requestMatchers(HttpMethod.GET,UriConstants.GET_UPCOMING_EVENTS).hasAnyRole("ADMIN","MODERATOR","USER")
                        .requestMatchers(HttpMethod.GET,UriConstants.GET_EVENT_BY_ID).hasAnyRole("ADMIN","MODERATOR","USER")
                        .requestMatchers(HttpMethod.GET,UriConstants.GET_EVENT_BY_NAME).hasAnyRole("ADMIN","MODERATOR","USER")
                        .requestMatchers(HttpMethod.POST,UriConstants.ADD_EVENT_INFO).hasAnyRole("ADMIN","MODERATOR")
                        .requestMatchers(HttpMethod.PUT,UriConstants.UPDATE_EVENT_INFO).hasAnyRole("ADMIN","MODERATOR")
                        .requestMatchers(HttpMethod.DELETE,UriConstants.DELETE_EVENT_INFO).hasAnyRole("ADMIN","MODERATOR")

                        .requestMatchers(HttpMethod.GET,UriConstants.GET_ALL_ANNOUNCEMENTS).hasAnyRole("ADMIN","MODERATOR","USER")
                        .requestMatchers(HttpMethod.POST,UriConstants.CREATE_ANNOUNCEMENT).hasAnyRole("ADMIN","MODERATOR")
                        .requestMatchers(HttpMethod.PUT,UriConstants.UPDATE_ANNOUNCEMENT).hasAnyRole("ADMIN","MODERATOR")
                        .requestMatchers(HttpMethod.DELETE,UriConstants.DELETE_ANNOUNCEMENT).hasAnyRole("ADMIN","MODERATOR")

                        .requestMatchers(HttpMethod.GET,UriConstants.RETRIEVE_ATTENDEES_BY_EVENT).hasAnyRole("ADMIN","MODERATOR","USER")
                        .requestMatchers(HttpMethod.POST,UriConstants.SAVE_ATTENDANCE).hasAnyRole("ADMIN","MODERATOR","USER")
                        .requestMatchers(HttpMethod.PUT,UriConstants.UPDATE_ATTENDANCE).hasAnyRole("ADMIN","MODERATOR","USER")
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )
                )
                .addFilterBefore(
                        jwtTokenFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOriginPattern("*");

        configuration.setAllowedMethods(
                Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS")
        );

        configuration.setAllowedHeaders(
                Arrays.asList("Authorization", "Content-Type")
        );

        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
