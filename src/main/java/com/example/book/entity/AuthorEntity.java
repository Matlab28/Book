package com.example.book.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
@RequiredArgsConstructor
@Table(name = "author")
public class AuthorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name, surname, nationality, age;

    @Column(name = "centuries")
    private String yearsTheyLived;
    private Boolean stillAlive;


    @OneToMany(mappedBy = "authorEntity", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<BookEntity> bookEntities;
}
