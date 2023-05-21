package ru.tinkoff.edu.controller;


import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import ru.tinkoff.edu.TgBot;
import ru.tinkoff.edu.dto.ApiErrorResponse;
import ru.tinkoff.edu.dto.LinkUpdaterRequest;

import java.util.Arrays;

@RequestMapping("/updates")
@RestController
public class LinksController {

    @ApiResponse(responseCode = "200", description = "Обновление подготовлено :)")
    @ApiResponse(responseCode = "400", description = "Неправильные параметры запроса")

    @Operation(summary = "Отправить обновление")
    @PostMapping(consumes = "application/json", produces = "application/json")
    String updateLink(@RequestBody @Valid LinkUpdaterRequest request){
        TelegramBot bot = TgBot.getBot();
        System.err.println(Arrays.toString(request.tgChatIds()));
        for (Integer chatid : request.tgChatIds()) {
            bot.execute(new SendMessage(chatid, "По ссылке  " + request.url() + " произошло обновление "
                    + request.description()));
        }
        return  "some body ones told me..";
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleException(MethodArgumentNotValidException e){
        return ResponseEntity.badRequest().body(new ApiErrorResponse("Неправильные параметры запроса",
                e.getStatusCode().toString(),
                e.getObjectName(),
                e.getLocalizedMessage(),
                new String[]{String.valueOf(e.getStackTrace())}));
    }



}

