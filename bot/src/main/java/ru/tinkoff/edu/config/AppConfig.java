package ru.tinkoff.edu.config;

import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix = "app", ignoreUnknownFields = true)
public record AppConfig(@NotNull String test) {
    @Bean
    public String test(AppConfig config) {
        return config.test;
    }



}
