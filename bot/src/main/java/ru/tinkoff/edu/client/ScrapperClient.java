package ru.tinkoff.edu.client;

import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import ru.tinkoff.edu.dto.AddLinkRequest;
import ru.tinkoff.edu.dto.ScrapperResponse;

import java.net.URI;
import java.net.URISyntaxException;
@RequiredArgsConstructor
public class ScrapperClient {
    
    private final WebClient webClient;

    public ScrapperClient() {
        String gitHubBaseUrl = "http://localhost:8081";
        this.webClient = WebClient.create(gitHubBaseUrl);
    }
    
    public String addChat(Long id) {
        return webClient.post().uri("/tg-chat/{id}", id)
                .header("tg_chat_id", String.valueOf(id))
                .header("Content-Type", "application/json").retrieve()
                .bodyToMono(String.class).block();

    }

    public String deleteChat(Long chatId) {

        return webClient.post().uri("/tg-chat/{id}", chatId)
                .header("tg_chat_id", String.valueOf(chatId))
                .header("Content-Type", "application/json").retrieve()
                .bodyToMono(String.class).block();

    }

    public String addLink(Long chatId, String url) throws URISyntaxException {

        return webClient.post().uri("/links")
                .header("tg_chat_id", String.valueOf(chatId))
                .body(BodyInserters.fromValue(new AddLinkRequest(new URI(url))))
                .retrieve()
                .bodyToMono(String.class).block();

    }
    public ScrapperResponse deleteLink(Long chatId) {

        return webClient.get().uri("/links")
                .header("tg_chat_id", String.valueOf(chatId))
                .header("Content-Type", "application/json").retrieve()
                .bodyToMono(ScrapperResponse.class).block();

    }
    public ScrapperResponse getAllLinks(Long chatId) {

        return webClient.get().uri("/links")
                .header("tg_chat_id", String.valueOf(chatId))
                .header("Content-Type", "application/json").retrieve()
                .bodyToMono(ScrapperResponse.class).block();

    }
}
