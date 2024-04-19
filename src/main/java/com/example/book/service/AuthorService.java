package com.example.book.service;

import com.example.book.dto.request.AuthorRequestDto;
import com.example.book.dto.response.AuthorResponseDto;
import com.example.book.entity.AuthorEntity;
import com.example.book.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorService {
    private final ModelMapper modelMapper;
    private final AuthorRepository repository;

    public String create(AuthorRequestDto dto) {
        AuthorEntity authorEntity = mapDtoToEntity(dto);
        String alphabetRegex = "[a-zA-Z]";
        String numbers = ".*\\d+.*";

//        if (!dto.getName().matches(alphabetRegex) && !dto.getSurname().matches(alphabetRegex)
//                && !dto.getNationality().matches(alphabetRegex)) {
//            return "Invalid input...";
//        }

//        if (!dto.getAge().toString().matches(numbers)) {
//            return "Invalid year input...";
//        }

        if (dto.getStillAlive().equals(true)) {
            dto.setYearsTheyLived("-> 2024");
        }

        repository.save(authorEntity);
        return "Author's information added successfully!";
    }

    public List<AuthorResponseDto> read() {
        List<AuthorEntity> all = repository.findAll();

        return all
                .stream()
                .map(r -> modelMapper.map(r, AuthorResponseDto.class))
                .collect(Collectors.toList());
    }

    public String update(Long id, AuthorRequestDto dto) throws LibraryException {
        AuthorEntity entity = repository.findById(id)
                .orElseThrow(() -> new LibraryException("Author not found by - " + id));

        modelMapper.map(dto, entity);
        repository.save(entity);
        return "Updated successfully!";
    }

    public String delete(Long id) throws LibraryException {
        AuthorEntity entity = repository.findById(id)
                .orElseThrow(() -> new LibraryException("Author not found by - " + id));

        repository.delete(entity);
        return "Deleted successfully!";
    }

    public List<AuthorResponseDto> findByMinAge(String minAge) throws LibraryException {
        List<AuthorEntity> list = repository.findByMinAge(minAge)
                .orElseThrow(() -> new LibraryException("Author not found by - " + minAge));

        return list
                .stream()
                .map(m -> modelMapper.map(m, AuthorResponseDto.class))
                .collect(Collectors.toList());
    }

    public List<AuthorResponseDto> findByBetweenYears(String first, String second) throws LibraryException {
        List<AuthorEntity> list = repository.findByYearsTheyLivedBetween(first, second)
                .orElseThrow(() -> new LibraryException("Author not found between '" + first + " - " + second + "' years"));

        return list
                .stream()
                .map(m -> modelMapper.map(m, AuthorResponseDto.class))
                .collect(Collectors.toList());
    }

    public AuthorEntity mapDtoToEntity(AuthorRequestDto dto) {
        return modelMapper.map(dto, AuthorEntity.class);
    }

    public AuthorRequestDto mapEntityToDto(AuthorEntity entity) {
        return modelMapper.map(entity, AuthorRequestDto.class);
    }
}
