package ru.tinkoff.edu;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.BotCommand;
import com.pengrad.telegrambot.request.SetMyCommands;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.HashMap;
import java.util.Map;

public final class Bot {

    @Value("{$bot.name}")
    private String BOT_NAME;

    @Value("${app.token}")
    private String BOT_TOKEN;



    public Bot() {

        TelegramBot bot = new TelegramBot("6173129634:AAH5-V1AvHyXpW3GItK8GSRWaWy5AWIfdys");

        BotCommand[] commands = {
                new BotCommand("start", "зарегестрировать пользователя"),
                new BotCommand("help", "вывести окно с командами"),
                new BotCommand("track", "начать отслеживание ссылки"),
                new BotCommand("untrack", "прекратить отслеживание ссылки"),
                new BotCommand("list", "показать список отслеживаемых ссылок")
        };

        bot.execute(new SetMyCommands(commands));
        bot.setUpdatesListener(new BotUpdater(bot));

    }
}
