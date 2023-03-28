package ru.tinkoff.edu;

import java.time.Duration;

public record Scheduler(
        Duration interval
) {
}
