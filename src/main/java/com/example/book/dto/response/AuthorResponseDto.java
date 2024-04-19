package com.example.book.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
public class AuthorResponseDto {
    private Long id;

    private String name, surname, nationality, age;
    private String yearsTheyLived;
    private Boolean stillAlive;

}
