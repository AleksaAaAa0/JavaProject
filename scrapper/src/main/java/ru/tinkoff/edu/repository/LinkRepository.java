package ru.tinkoff.edu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tinkoff.edu.entity.Link;

public interface LinkRepository extends JpaRepository<Link, Long> {
}
