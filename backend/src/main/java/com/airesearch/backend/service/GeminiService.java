package com.airesearch.backend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class GeminiService {

    @Value("${gemini.api.key}")
    private String apiKey;

    private final WebClient webClient;

    public GeminiService(WebClient.Builder builder) {
        this.webClient = builder
                .baseUrl("https://generativelanguage.googleapis.com")
                .build();
    }

    public String summarize(String text) {

        // Limit text length (Gemini Free Tier)
        String limitedText = text.length() > 12000
                ? text.substring(0, 12000)
                : text;

        String requestBody = """
        {
          "contents": [
            {
              "parts": [
                {
                  "text": "Summarize the following research paper in simple language:\\n%s"
                }
              ]
            }
          ]
        }
        """.formatted(
                limitedText
                        .replace("\\", "\\\\")
                        .replace("\"", "\\\"")
                        .replace("\n", "\\n")
        );

        return webClient.post()
                .uri("/v1beta/models/gemini-2.0-flash:generateContent?key=" + apiKey)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestBody)
                .retrieve()
                .onStatus(
                        status -> status.isError(),
                        response -> response.bodyToMono(String.class)
                                .map(body -> new RuntimeException("Gemini Error:\n" + body))
                )
                .bodyToMono(String.class)
                .block();
    }
}