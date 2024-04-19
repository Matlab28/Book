package com.example.book.repository;

import com.example.book.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
    Optional<List<CustomerEntity>> findByCardNumbOrEmail(String cardNum, String email);


    @Query(value = "SELECT * FROM customer WHERE expDate >= :minExpDate", nativeQuery = true)
    Optional<List<CustomerEntity>> findByExpDate(@Param("minExpDate") String minExpDate);
}
