package ru.tinkoff.edu.dao;

import java.net.URI;

public record Link(
        Integer id,
        URI url
) {
}
