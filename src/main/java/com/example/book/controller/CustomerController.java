package com.example.book.controller;

import com.example.book.dto.request.CustomerRequestDto;
import com.example.book.dto.response.CustomerResponseDto;
import com.example.book.service.CustomerService;
import com.example.book.service.LibraryException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService service;

    @PostMapping("/create")
    public String create(@RequestBody CustomerRequestDto dto) {
        return service.create(dto);
    }

    @GetMapping("/read")
    public List<CustomerResponseDto> read() {
        return service.read();
    }

    @PutMapping("/update/{id}")
    public String update(@PathVariable Long id, @RequestBody CustomerRequestDto dto) throws LibraryException {
        return service.update(id, dto);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) throws LibraryException {
        return service.delete(id);
    }

    @GetMapping("/num-email")
    public List<CustomerResponseDto> findByCardNumOrEmail(@RequestParam String cardNum,
                                                          @RequestParam String email) throws LibraryException {
        return service.findByCardNumbOrEmail(cardNum, email);
    }

    @GetMapping("exp-date")
    public List<CustomerResponseDto> findByExpDate(@RequestParam String minExpDate) throws LibraryException {
        return service.findByExpDate(minExpDate);
    }
}
