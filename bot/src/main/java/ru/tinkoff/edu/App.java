package ru.tinkoff.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.tinkoff.edu.config.AppConfig;

@SpringBootApplication
@EnableConfigurationProperties(AppConfig.class)
public class App {
public static void main(String[] args) {
        var ctx = SpringApplication.run(App.class, args);
        new TgBot(ctx.getBean("BOT", Bot.class).getToken());
        }
}
