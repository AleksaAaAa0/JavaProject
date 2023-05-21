package ru.tinkoff.edu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tinkoff.edu.entity.Chat;

public interface ChatRepository extends JpaRepository<Chat, Long> {

}
