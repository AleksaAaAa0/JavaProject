package ru.tinkoff.edu.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.net.URI;
import java.sql.Timestamp;

@Getter
@Setter
@Accessors(chain = true)
@Entity
public class Link {
    @Id
    @Column(name = "link_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "link_url")
    private URI url;

    @Column(name = "updated_at")
    private Timestamp updatedAt;
}
