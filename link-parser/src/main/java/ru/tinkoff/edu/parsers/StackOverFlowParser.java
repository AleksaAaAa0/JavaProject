package ru.tinkoff.edu.parsers;

public final class StackOverFlowParser implements Parse{
    @Override
    public String parse(String URL) {
        return URL.split("/")[4];
    }
}
