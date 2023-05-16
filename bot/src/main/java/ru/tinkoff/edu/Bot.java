package ru.tinkoff.edu;

import com.pengrad.telegrambot.model.BotCommand;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("BOT")
public class Bot {

    @Value("${app.token}")
    private String test;
    
    public Bot() {
    }

    String getToken() {
        return test;

    }
}
