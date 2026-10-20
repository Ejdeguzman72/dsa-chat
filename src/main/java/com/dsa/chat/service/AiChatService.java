package com.dsa.chat.service;

import tools.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Map;

@Service
public class AiChatService {

    private final RestClient restClient;

    public AiChatService(
            @Value("${openai.api.key}") String apiKey) {

        this.restClient = RestClient.builder()
                .baseUrl("https://api.openai.com/v1")
                .defaultHeader(
                        "Authorization",
                        "Bearer " + apiKey)
                .build();
    }

    public String askAI(String message) {

        Map<String, Object> request = Map.of(
                "model", "gpt-4.1-mini",
                "instructions",
                "You are a helpful AI assistant in a chatroom.",
                "input", message
        );

        JsonNode response = restClient.post()
                .uri("/responses")
                .body(request)
                .retrieve()
                .body(JsonNode.class);

        if (response == null) {
            throw new IllegalStateException(
                    "AI returned an empty response");
        }

        for (JsonNode output : response.path("output")) {
            for (JsonNode content : output.path("content")) {
                if ("output_text".equals(
                        content.path("type").asText())) {
                    return content.path("text").asText();
                }
            }
        }

        throw new IllegalStateException(
                "AI returned no text");
    }
}