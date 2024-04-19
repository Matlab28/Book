package com.example.book.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
public class AuthorRequestDto {
    private String name, surname, nationality, yearsTheyLived;
    private Integer age;
    private Boolean stillAlive;
}
