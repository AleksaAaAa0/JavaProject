package ru.tinkoff.edu.dto;

import java.util.List;

public record ListLinksResponse(
        Integer size,
        List<LinkResponse> links
) {
}
