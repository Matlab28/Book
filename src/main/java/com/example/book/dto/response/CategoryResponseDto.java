package com.example.book.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
public class CategoryResponseDto {
    private Long id;

    private String category;
}
