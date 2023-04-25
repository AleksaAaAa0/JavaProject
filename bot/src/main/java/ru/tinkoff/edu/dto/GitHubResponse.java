package ru.tinkoff.edu.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;

public record GitHubResponse(
        OffsetDateTime pushedAt,

        OffsetDateTime updatedAt,

        String fullName
) {
}