package com.example.book.controller;

import com.example.book.dto.request.AuthorRequestDto;
import com.example.book.dto.response.AuthorResponseDto;
import com.example.book.service.AuthorService;
import com.example.book.service.LibraryException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/author")
public class AuthorController {
    private final AuthorService service;

    @PostMapping("/create")
    public String create(@RequestBody AuthorRequestDto dto) {
        return service.create(dto);
    }

    @GetMapping("/read")
    public List<AuthorResponseDto> read() {
        return service.read();
    }

    @PutMapping("/update/{id}")
    public String update(@PathVariable Long id, @RequestBody AuthorRequestDto dto) throws LibraryException {
        return service.update(id, dto);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) throws LibraryException {
        return service.delete(id);
    }

    @GetMapping("/min-age")
    public List<AuthorResponseDto> findByMinAge(@RequestParam String minAge) throws LibraryException {
        return service.findByMinAge(minAge);
    }

    @GetMapping("/between")
    public List<AuthorResponseDto> findByBetweenYears(@RequestParam String first,
                                                      @RequestParam String second) throws LibraryException {
        return service.findByBetweenYears(first, second);
    }
}
