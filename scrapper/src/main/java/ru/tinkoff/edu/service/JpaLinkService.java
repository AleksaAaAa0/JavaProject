package ru.tinkoff.edu.service;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.entity.Link;
import ru.tinkoff.edu.repository.LinkRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JpaLinkService {

    @Autowired
    LinkRepository repository;

    @Transactional
    public Link save(Link link) {
        return repository.save(link);
    }

    @Transactional
    public void delete(Link link) {
        repository.delete(link);
    }

    @Transactional(readOnly = true)
    public List<Link> getAll() {
        return repository.findAll();
    }



}
