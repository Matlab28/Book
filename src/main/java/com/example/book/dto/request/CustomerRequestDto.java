package com.example.book.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
public class CustomerRequestDto {
    private String cusName, cardNumb, expDate, cvv ,email, phone;
}
