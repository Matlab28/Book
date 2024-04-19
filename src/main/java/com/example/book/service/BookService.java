package com.example.book.service;

import com.example.book.dto.request.BookRequestDto;
import com.example.book.dto.response.BookResponseDto;
import com.example.book.entity.BookEntity;
import com.example.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {
    private final ModelMapper modelMapper;
    private final BookRepository repository;

    public String create(BookRequestDto dto) {
        BookEntity bookEntity = mapDtoToEntity(dto);

        repository.save(bookEntity);
        return "Thank you for adding values!";
    }

    public List<BookResponseDto> readAll() {
        List<BookEntity> all = repository.findAll();

        return all
                .stream()
                .map(m -> modelMapper.map(m, BookResponseDto.class))
                .collect(Collectors.toList());
    }

    public String update(Long id, BookRequestDto dto) throws LibraryException {
        BookEntity entity = repository.findById(id)
                .orElseThrow(() -> new LibraryException("Book not found by - " + id));

        modelMapper.map(dto, entity);
        repository.save(entity);
        return id + " updated successfully!";
    }

    public String delete(Long id) throws LibraryException {
        BookEntity bookEntity = repository.findById(id)
                .orElseThrow(() -> new LibraryException("Book not found by - " + id));

        repository.delete(bookEntity);
        return id + " deleted successfully!";
    }

    public List<BookResponseDto> findByMinPrice(Double minPrice) throws LibraryException {
        List<BookEntity> entities = repository.findByMinPrice(minPrice)
                .orElseThrow(() -> new LibraryException("Book not found by - " + minPrice));

        return entities
                .stream()
                .map(m -> modelMapper.map(m, BookResponseDto.class))
                .collect(Collectors.toList());
    }

    public List<BookResponseDto> findByLetter(String letter) {
        List<BookEntity> all = repository.findAll();

        List<BookEntity> collect = all
                .stream()
                .filter(l -> l.getTitle().contains(letter))
                .filter(k -> k.getTitle().contains(letter))
                .toList();

        return collect
                .stream()
                .map(m -> modelMapper.map(m, BookResponseDto.class))
                .collect(Collectors.toList());
    }

    public List<BookResponseDto> findBYMInPubDate(String minPubDate) throws LibraryException {
        List<BookEntity> list = repository.findByMinPubDate(minPubDate)
                .orElseThrow(() -> new LibraryException("Book not found by - " + minPubDate));

        return list
                .stream()
                .map(m -> modelMapper.map(m, BookResponseDto.class))
                .collect(Collectors.toList());
    }


//    public BookResponseDto priceOrYear(Double price, Integer publicationDate) {
//        BookEntity bookEntity = repository.findByPriceOrPublicationDate(price, publicationDate);
//        return resMapEntityToDto(bookEntity);
//    }

    public BookEntity mapDtoToEntity(BookRequestDto dto) {
        return modelMapper.map(dto, BookEntity.class);
    }

    public BookResponseDto resMapEntityToDto(BookEntity entity) {
        return modelMapper.map(entity, BookResponseDto.class);
    }

//    public BookRequestDto mapEntityToDto(BookEntity entity) {
//        return modelMapper.map(entity, BookRequestDto.class);
//    }
}
