package com.example.demo.service;

import com.example.demo.dto.AiApiResponse;
import com.example.demo.dto.ChatRequest;
import com.example.demo.dto.ChatResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ChatService {

    private final WebClient webClient;

    @Value("${ai.vector-service.url}")
    private String vectorServiceUrl;

    @Value("${ai.gemini-service.url}")
    private String geminiServiceUrl;

    public ChatService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public Mono<ChatResponse> getChatResponse(ChatRequest request) {
        return callApi(vectorServiceUrl, request)
            .flatMap(response -> {
                if (response != null && response.getMessage() != null && !response.getMessage().isEmpty()) {
                    return Mono.just(new ChatResponse(response.getMessage()));
                } else {
                    return callApi(geminiServiceUrl, request)
                        .map(geminiResponse -> new ChatResponse(geminiResponse.getMessage()));
                }
            });
    }

    private Mono<AiApiResponse> callApi(String url, ChatRequest request) {
        return webClient.post()
            .uri(url)
            .bodyValue(request)
            .retrieve()
            .bodyToMono(AiApiResponse.class)
            .onErrorResume(e -> Mono.empty()); // On error, return empty to trigger fallback
    }
}
