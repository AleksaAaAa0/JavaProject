package ru.tinkoff.edu.parsers;

public sealed interface Parse permits GitHubParser, SOWParser {
    public String parse(String URL);
}
