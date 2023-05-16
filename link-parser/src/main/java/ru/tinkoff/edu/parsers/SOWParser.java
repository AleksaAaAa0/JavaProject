package ru.tinkoff.edu.parsers;

public final class SOWParser implements Parse{
    @Override
    public String parse(String URL) {
        return URL.split("/")[4];
    }
}
