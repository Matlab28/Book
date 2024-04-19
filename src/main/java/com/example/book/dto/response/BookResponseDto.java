package com.example.book.dto.response;

import com.example.book.entity.CategoryEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@RequiredArgsConstructor
public class BookResponseDto {
    private Long id;

    private String title, author, publicationDate;
    private Double price;
    private CategoryEntity categoryEntities;
}
