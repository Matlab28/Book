package com.example.book.service;

import com.example.book.dto.request.CustomerRequestDto;
import com.example.book.dto.response.CustomerResponseDto;
import com.example.book.entity.CustomerEntity;
import com.example.book.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final ModelMapper modelMapper;
    private final CustomerRepository repository;

    public String create(CustomerRequestDto dto) {
        CustomerEntity entity = mapDtoToEntity(dto);
        String emailRegex = "^[a-zA-Z0-9_]+(\\.[a-zA-Z0-9_]+)*@gmail\\.com$";
        String alphabetRegex = "[a-zA-Z]";
        String numbers = ".*\\d+.*";

        if (!dto.getCardNumb().matches(numbers)) {
            return "Invalid card number input...";
        } else if (dto.getCardNumb().length() != 16) {
            return "Card number's length must be 16 characters";
        }

        if (!dto.getExpDate().matches(numbers)) {
            return "Invalid expire date...";
        } else if (dto.getExpDate().length() != 4) {
            return "Please add correct expire date...";
        }

        if (!dto.getCvv().matches(numbers)) {
            return "Input must be only integers...";
        } else if (dto.getCvv().length() != 3) {
            return "CVV length must be 3 characters...";
        }

        if (!dto.getPhone().matches(numbers) && dto.getPhone().matches(alphabetRegex)) {
            return "Invalid phone number...";
        }

        if (!dto.getEmail().matches(emailRegex)) {
            return "Invalid email address...";
        }

        repository.save(entity);
        return "Customer information added and saved successfully!";
    }

    public List<CustomerResponseDto> read() {
        List<CustomerEntity> all = repository.findAll();

        return all
                .stream()
                .map(m -> modelMapper.map(m, CustomerResponseDto.class))
                .collect(Collectors.toList());
    }

    public String update(Long id, CustomerRequestDto dto) throws LibraryException {
        CustomerEntity customerEntity = repository.findById(id)
                .orElseThrow(() -> new LibraryException(("Customer not found  by - " + id)));

        modelMapper.map(dto, customerEntity);
        repository.save(customerEntity);
        return "Customer updated successfully!";
    }

    public String delete(Long id) throws LibraryException {
        CustomerEntity entity = repository.findById(id)
                .orElseThrow(() -> new LibraryException("Customer not found by - " + id));

        repository.delete(entity);
        return "Customer deleted successfully!";
    }

    public List<CustomerResponseDto> findByCardNumbOrEmail(String cardNum, String email) throws LibraryException {
        List<CustomerEntity> list = repository.findByCardNumbOrEmail(cardNum, email)
                .orElseThrow(() -> new LibraryException("Card number or email not found..."));

        return list
                .stream()
                .map(m -> modelMapper.map(m, CustomerResponseDto.class))
                .collect(Collectors.toList());
    }

    public List<CustomerResponseDto> findByExpDate(String minExpDate) throws LibraryException {
        List<CustomerEntity> list = repository.findByExpDate((minExpDate))
                .orElseThrow(() -> new LibraryException("Customer's card not found by " + minExpDate));

        return list
                .stream()
                .map(m -> modelMapper.map(m, CustomerResponseDto.class))
                .collect(Collectors.toList());
    }

    public CustomerEntity mapDtoToEntity(CustomerRequestDto dto) {
        return modelMapper.map(dto, CustomerEntity.class);
    }

    public CustomerRequestDto mapEntityToDto(CustomerEntity entity) {
        return modelMapper.map(entity, CustomerRequestDto.class);
    }
}
