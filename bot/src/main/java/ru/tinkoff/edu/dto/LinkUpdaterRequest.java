package ru.tinkoff.edu.dto;

import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

import java.net.URI;

@Validated
public record LinkUpdaterRequest(
        @NotNull
        Integer id,

        @NotNull
        URI url,

        @NotNull
        String description,

        @NotNull
        Integer[] tgChatIds
        ) {
}
