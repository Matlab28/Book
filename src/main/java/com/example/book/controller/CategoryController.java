package com.example.book.controller;

import com.example.book.dto.request.CategoryRequestDto;
import com.example.book.dto.response.CategoryResponseDto;
import com.example.book.service.CategoryService;
import com.example.book.service.LibraryException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService service;

    @PostMapping("/create")
    public String create(@RequestBody CategoryRequestDto dto) {
        return service.create(dto);
    }

    @GetMapping("/read")
    public List<CategoryResponseDto> read() {
        return service.read();
    }

    @PutMapping("/update/{id}")
    public String update(@PathVariable Long id, @RequestBody CategoryRequestDto dto) throws LibraryException {
        return service.update(id, dto);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) throws LibraryException {
        return service.delete(id);
    }
}
