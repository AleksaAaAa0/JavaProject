package ru.tinkoff.edu;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.BotCommand;
import com.pengrad.telegrambot.request.SetMyCommands;


public class TgBot {
    static TelegramBot bot;
    BotCommand[] commands = {
            new BotCommand("start", "регестрация пользователя"),
            new BotCommand("help", "вывод окна с командами"),
            new BotCommand("track", "начать отслеживание ссылки"),
            new BotCommand("untrack", "отмена отслеживание ссылки"),
            new BotCommand("list", "список ссылок, которые отслеживаются")
    };
    TgBot(String token){
        bot = new TelegramBot(token);
        bot.execute(new SetMyCommands(commands));
        bot.setUpdatesListener(new MessageProcessor(bot));
    }

    public static TelegramBot getBot(){
        return bot;
    }

}
