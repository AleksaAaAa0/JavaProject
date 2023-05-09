package ru.tinkoff.edu.config;

import jakarta.validation.constraints.NotNull;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.annotation.Validated;
import ru.tinkoff.edu.schedule.Scheduler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Validated
@ConfigurationProperties(prefix = "app", ignoreUnknownFields = false)
public record ApplicationConfig(@NotNull String test, Scheduler scheduler) {

    @Bean
    public long schedulerIntervalMs(ApplicationConfig config) {
        return config.scheduler().interval().toMillis();
    }
    @Bean
    public Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/scrapper", "postgres","postgres");
    }
}
