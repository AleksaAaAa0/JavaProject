package ru.tinkoff.edu.jdbc;

import java.sql.SQLException;

public interface ChatService {
    void addChat(int chatId) throws SQLException;
    void deleteChat(int chatId) throws SQLException;
}
