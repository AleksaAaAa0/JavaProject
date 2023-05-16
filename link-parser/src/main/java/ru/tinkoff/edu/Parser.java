package ru.tinkoff.edu;

import ru.tinkoff.edu.data.GitHub;
import ru.tinkoff.edu.data.SOW;
import ru.tinkoff.edu.parsers.GitHubParser;
import ru.tinkoff.edu.parsers.SOWParser;

public class Parser {
    public void parseURL(String url) {
        try {
            url = url.strip();
            if (url.contains("github.com")) {
                String parse = new GitHubParser().parse(url);
                GitHub response = new GitHub(parse.split(":")[0], parse.split(":")[1]);
                System.out.println(response);
            } else if (url.contains("stackoverflow.com")) {
                String parse = new SOWParser().parse(url);
                SOW response = new SOW(parse);
                System.out.println(response);
            } else {
                throw new RuntimeException("something wrong");
            }

        } catch (Exception e) {
            throw new RuntimeException("something wrong");
        }

    }
}
