package ru.tinkoff.edu;

import com.pengrad.telegrambot.Callback;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.GetUpdatesResponse;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import ru.tinkoff.edu.client.ScrapperClient;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class BotUpdater implements UpdatesListener {
    String comand;
    TelegramBot bot;
    int updateid_fromComand = 0;
    ScrapperClient client = new ScrapperClient();
    public BotUpdater (TelegramBot bot){
        this.bot = bot;
    }

    @Override
    public int process(List<Update> updates) {
            updates.forEach(update ->{

                String msg = update.message().text();
                System.out.println(update.message().chat().username() + " " + msg + update.message().chat().id());


                if (msg == null) msg = "/help";

                switch (msg){
                    case "/start" -> {
                        start(update);
                    }
                    case "/help" -> {
                        bot.execute(new SendMessage(update.message().chat().id(), "никто не поможет"));
                    }
                    case "/track" -> {
                        bot.execute(new SendMessage(update.message().chat().id(), "type link"));
                        update.message().messageId();
                        updateid_fromComand = update.message().messageId();
                        comand = msg;
                    }
                    case "/untrack" -> {
                        bot.execute(new SendMessage(update.message().chat().id(), "type link"));
                        updateid_fromComand = update.message().messageId();
                        comand = msg;
                    }
                    case "/list" -> {
                        bot.execute(new SendMessage(update.message().chat().id(), "list"));
                    }
                    default ->{
                        if (update.message().messageId() == updateid_fromComand + 2){
                            switch (comand){
                                case "/track" -> {
                                    try {
                                        client.addLink(update.message().chat().id(), update.message().text());
                                    } catch (URISyntaxException e) {
                                        throw new RuntimeException(e);
                                    }
                                    bot.execute(new SendMessage(update.message().chat().id(), "track"));

                                }
                                case "/untrack" -> bot.execute(new SendMessage(update.message().chat().id(), "untrack"));

                            }

                        }
                        else bot.execute(new SendMessage(update.message().chat().id(), "введена хрень"));
                    }

                }
            });
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        }

        void start(Update update){

            new ScrapperClient().addChat(update.message().chat().id());
            bot.execute(new SendMessage(update.message().chat().id(), "зарегали команду старт"));
        }

    }



