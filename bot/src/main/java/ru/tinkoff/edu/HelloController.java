package ru.tinkoff.edu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Value("${app.test}")
    String str;

    @RequestMapping("/")
    String hello() {
        return "<html> <h1>Hello " + str + " <br> World!!!</html>";
    }


}
