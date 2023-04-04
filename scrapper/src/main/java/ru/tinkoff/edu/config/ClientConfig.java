package ru.tinkoff.edu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.edu.client.GitHubClient;
import ru.tinkoff.edu.client.StackOverflowClient;

@Configuration
public class ClientConfig {

    @Bean
    public GitHubClient gitHubClientService() {
        return new GitHubClient();
    }

    @Bean
    public StackOverflowClient stackOverflowClientService() {
        return new StackOverflowClient();
    }

}
