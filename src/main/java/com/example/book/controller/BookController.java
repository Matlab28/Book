package com.example.book.controller;

import com.example.book.dto.request.BookRequestDto;
import com.example.book.dto.response.BookResponseDto;
import com.example.book.service.BookService;
import com.example.book.service.LibraryException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book")
public class BookController {
    private final BookService service;

    @PostMapping("/create")
    public String create(@RequestBody BookRequestDto dto) {
        return service.create(dto);
    }

    @GetMapping("/read")
    public List<BookResponseDto> read() {
        return service.readAll();
    }

    @PutMapping("/update/{id}")
    public String update(@PathVariable Long id, @RequestBody BookRequestDto dto) throws LibraryException {
        return service.update(id, dto);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) throws LibraryException {
        return service.delete(id);
    }

    @GetMapping("/min-price")
    public List<BookResponseDto> findByMinPrice(@RequestParam Double minPrice) throws LibraryException {
        return service.findByMinPrice(minPrice);
    }

    @GetMapping("/find-letter")
    public List<BookResponseDto> findByLetter(@RequestParam String letter) {
        return service.findByLetter(letter);
    }

    @GetMapping("/min-pub-date")
    public List<BookResponseDto> findByMinPubDate(@RequestParam String minExpDate) throws LibraryException {
        return service.findBYMInPubDate(minExpDate);
    }
}
