package com.example.book.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
public class BookRequestDto {
    private String title, author, publicationDate;
    private Double price;
    private Long categoryId;
}
