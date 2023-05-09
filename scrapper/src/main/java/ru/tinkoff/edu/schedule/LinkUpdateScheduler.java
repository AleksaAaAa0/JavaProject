package ru.tinkoff.edu.schedule;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.client.BotClient;
import ru.tinkoff.edu.client.GitHubClient;
import ru.tinkoff.edu.client.StackOverflowClient;
import ru.tinkoff.edu.dto.LinkUpdaterResponse;
import ru.tinkoff.edu.jdbc.JdbcLinkService;
import ru.tinkoff.edu.jdbc.Link;
import ru.tinkoff.edu.rabbitmq.Producer;

import java.net.URISyntaxException;
import java.sql.*;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class LinkUpdateScheduler {
    private final RabbitTemplate rabbitTemplate;
    JdbcLinkService service;

    {
        try {
            service = new JdbcLinkService();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Scheduled(fixedDelayString = "#{@schedulerIntervalMs}")
    public void update() throws SQLException, URISyntaxException {
        //Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/scrapper", "postgres","postgres");

        service.update();

    }
}
