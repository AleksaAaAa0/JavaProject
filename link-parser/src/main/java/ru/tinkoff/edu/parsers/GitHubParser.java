package ru.tinkoff.edu.parsers;

public final class GitHubParser implements Parse {

    @Override
    public String parse(String URL) {
        return URL.split("/")[3] + ":" +  URL.split("/")[4];
    }
}
