package ru.tinkoff.edu.dao;

import java.math.BigInteger;
import java.net.URI;
import java.sql.SQLException;

public interface ChatDAO {
    void addChat(int chatId) throws SQLException;
    void deleteChat(int chatId) throws SQLException;
}
