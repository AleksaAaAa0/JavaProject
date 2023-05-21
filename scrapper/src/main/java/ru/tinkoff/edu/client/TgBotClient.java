package ru.tinkoff.edu.client;

import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import ru.tinkoff.edu.dto.LinkUpdaterResponse;

import java.net.URI;
import java.net.URISyntaxException;

public class TgBotClient {
    private final WebClient webClient;

    public TgBotClient() {
        String gitHubBaseUrl = "http://localhost:8080";
        this.webClient = WebClient.create(gitHubBaseUrl);
    }

    //можно указать базовый URL
    public TgBotClient(String baseUrl) {
        this.webClient = WebClient.create(baseUrl);
    }

    public String updateLink(Integer id, URI url,String description, Integer[] ids) throws URISyntaxException {

        return webClient.post().uri("/updates")
                .header("Content-Type", "application/json")
                .body(BodyInserters.fromValue(new LinkUpdaterResponse(id, url, description, ids)))
                .retrieve()
                .bodyToMono(String.class).block();

    }
}
