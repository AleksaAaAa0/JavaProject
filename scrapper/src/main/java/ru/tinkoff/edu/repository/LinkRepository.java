package ru.tinkoff.edu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tinkoff.edu.entity.Links;

public interface LinkRepository extends JpaRepository<Links, Long> {
}
