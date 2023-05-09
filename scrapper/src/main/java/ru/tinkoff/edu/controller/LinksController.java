package ru.tinkoff.edu.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import ru.tinkoff.edu.jdbc.Link;
import ru.tinkoff.edu.jdbc.JdbcLinkService;
import ru.tinkoff.edu.dto.AddLinkRequest;
import ru.tinkoff.edu.dto.ApiErrorResponse;
import ru.tinkoff.edu.dto.LinkResponse;
import ru.tinkoff.edu.dto.ListLinksResponse;
import ru.tinkoff.edu.entity.Links;
import ru.tinkoff.edu.service.JpaLinkService;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequestMapping(value = "/links", consumes = "application/json", produces = "application/json")
@RequiredArgsConstructor
@RestController
public class LinksController {

    private final JpaLinkService linkServicee;


    @ApiResponse(responseCode = "200", description = "Обновление обработано")
    @ApiResponse(responseCode = "400", description = "Некорректные параметры запроса")
    @ApiResponse(responseCode = "404", description = "Ресурс не найден")

    @Operation(summary = "Получить все отслеживаемые ссылки")
    @GetMapping
    ListLinksResponse getAllLinks(@RequestHeader Long tg_chat_id){
        List<LinkResponse> links = new ArrayList<>();
        try {
            List<Link> list = new JdbcLinkService().findAllLinksById(tg_chat_id);
            list.forEach(link -> links.add(new LinkResponse(link.id(), link.url())));
        } catch (SQLException | URISyntaxException e) {
            throw new RuntimeException(e);
        }


        return  new ListLinksResponse(links.size(), links);
    }

    @Operation(summary = "Добавить отслеживание ссылки")
    @PostMapping
    LinkResponse addLink(@RequestHeader Long tg_chat_id, @RequestBody @Valid AddLinkRequest request) throws URISyntaxException {
        try {
            new JdbcLinkService().addLink(tg_chat_id, request.link());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

//        new JpaLinkService().save(new Links().setId(123L).setUrl(new URI("123321.com")));

        return  new LinkResponse(1,new URI("qe"));
    }

    @Operation(summary = "Убрать отслеживание ссылки")
    @DeleteMapping
    String deleteLink(@RequestHeader int tg_chat_id, @RequestBody @Valid AddLinkRequest request){
        try {
            new JdbcLinkService().deleteLink((long) tg_chat_id,request.link().toString());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  "delete" + tg_chat_id;
    }


    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleException400(MethodArgumentNotValidException e){
        return ResponseEntity.badRequest().body(new ApiErrorResponse("Некорректные параметры запроса",
                e.getStatusCode().toString(),
                e.getObjectName(),
                e.getLocalizedMessage(),
                new String[]{Arrays.toString(e.getStackTrace())}));
    }



    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(HttpClientErrorException.NotFound.class)
    public ResponseEntity<ApiErrorResponse> handleException404(HttpClientErrorException.NotFound e){
        return ResponseEntity.badRequest().body(new ApiErrorResponse("Ресурс не найден",
                e.getStatusCode().toString(),
                e.getStatusText(),
                e.getLocalizedMessage(),
                new String[]{String.valueOf(e.getStackTrace())}));
    }
}

