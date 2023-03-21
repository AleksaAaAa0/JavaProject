package ru.tinkoff.edu;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/")
    String hello() {
        return "<html> <h1>Hello <br> World!!!</html>";
    }


}
