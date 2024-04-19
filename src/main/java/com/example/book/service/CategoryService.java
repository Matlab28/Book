package com.example.book.service;

import com.example.book.dto.request.CategoryRequestDto;
import com.example.book.dto.response.CategoryResponseDto;
import com.example.book.entity.CategoryEntity;
import com.example.book.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryService {
    private final ModelMapper modelMapper;
    private final CategoryRepository repository;

    public String create(CategoryRequestDto dto) {
        CategoryEntity entity = mapDtoToEntity(dto);
        log.info("Category created successfully!");
        repository.save(entity);
        log.info("Category added database successfully!");
        return "Category created successfully!";
    }

    public List<CategoryResponseDto> read() {
        List<CategoryEntity> all = repository.findAll();

        return all
                .stream()
                .map(m -> modelMapper.map(m, CategoryResponseDto.class))
                .collect(Collectors.toList());
    }

    public String update(Long id, CategoryRequestDto dto) throws LibraryException {
        CategoryEntity entity = repository.findById(id)
                .orElseThrow(() -> new LibraryException("Category not found by - " + id));

        modelMapper.map(dto, entity);
        repository.save(entity);
        return "Category updated successfully!";
    }

    public String delete(Long id) throws LibraryException {
        CategoryEntity entity = repository.findById(id)
                .orElseThrow(() -> new LibraryException("Category not found by - " + id));

        repository.delete(entity);
        return "Category deleted successfully!";
    }

    public CategoryEntity mapDtoToEntity(CategoryRequestDto dto) {
        return modelMapper.map(dto, CategoryEntity.class);
    }

    public CategoryRequestDto mapEntityToDto(CategoryEntity entity) {
        return modelMapper.map(entity, CategoryRequestDto.class);
    }

}
