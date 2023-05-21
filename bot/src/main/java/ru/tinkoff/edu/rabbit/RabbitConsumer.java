package ru.tinkoff.edu.rabbit;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.TgBot;
import ru.tinkoff.edu.dto.LinkUpdaterRequest;
import ru.tinkoff.edu.dto.ListLinksResponse;


@Component
public class RabbitConsumer {

    @RabbitListener(queues = "update")
    public void listen(LinkUpdaterRequest request) {
        TelegramBot bot = TgBot.getBot();
        System.err.println("from rabbit " + request);
        for (Integer chatid : request.tgChatIds()) {
            bot.execute(new SendMessage(chatid, "По данной ссылке  " + request.url() + " произошло обновление "
                    + request.description()));
        }
    }
    @RabbitListener(queues = "listResponse")
    public void list(@Payload ListLinksResponse in, @Header("chatId") Long id) {
        System.err.println(in);
        StringBuilder response = new StringBuilder("Отслеживаемые ссылки: \n");
        in.links().forEach(link-> response.append(link.url()).append("\n"));
        TelegramBot bot = TgBot.getBot();
        bot.execute(new SendMessage(id, response.toString()));
    }


}
