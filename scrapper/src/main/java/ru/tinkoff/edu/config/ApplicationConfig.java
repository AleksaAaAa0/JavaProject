package ru.tinkoff.edu.config;

import org.jetbrains.annotations.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;
import ru.tinkoff.edu.Scheduler;
import ru.tinkoff.edu.controller.LinkUpdaterScheduler;

@Validated
@ConfigurationProperties(prefix = "app", ignoreUnknownFields = true)
public record ApplicationConfig(@NotNull String test) {

}