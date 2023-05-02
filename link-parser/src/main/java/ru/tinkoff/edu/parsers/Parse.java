package ru.tinkoff.edu.parsers;

public sealed interface Parse permits GitHubParser, StackOverFlowParser {
    public String parse(String URL);
}
