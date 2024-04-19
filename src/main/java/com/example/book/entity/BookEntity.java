package com.example.book.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
@RequiredArgsConstructor
@Table(name = "books")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private Double price;

    @Column(name = "publication_date")
    private String publicationDate;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private AuthorEntity authorEntity;

    @ManyToMany(mappedBy = "bookEntities")
    private List<CategoryEntity> categoryEntities;

}
