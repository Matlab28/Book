package com.example.book.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
public class CustomerResponseDto {
    private Long id;

    private String cusName, cardNumb, expDate, cvv ,email, phone;
}
