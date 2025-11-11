package com.bajaj.webhook_solution.service;

import com.bajaj.webhook_solution.model.WebhookResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.Map;

@Service
public class WebhookService {

    private final WebClient webClient;

    public WebhookService(WebClient.Builder builder) {
        this.webClient = builder.baseUrl("https://bfhldevapigw.healthrx.co.in").build();
    }

    public WebhookResponse generateWebhook() {
        Map<String, String> requestBody = Map.of(
                "name", "Rutuja Bhagat",
                "regNo", "PES1UG23CS808",
                "email", "pes1ug23cs808@pesu.pes.edu"
        );

        return webClient.post()
                .uri("/hiring/generateWebhook/JAVA")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(WebhookResponse.class)
                .block();
    }

    public void sendSqlSolution(String webhookUrl, String accessToken, String finalQuery) {
        Map<String, String> body = Map.of("finalQuery", finalQuery);

        webClient.post()
                .uri(webhookUrl)
                .header("Authorization", accessToken)
                .bodyValue(body)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        System.out.println("Final SQL query submitted successfully!");
    }
}
