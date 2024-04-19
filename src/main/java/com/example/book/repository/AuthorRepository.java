package com.example.book.repository;

import com.example.book.entity.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.SqlReturnType;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {

    Optional<List<AuthorEntity>> findByYearsTheyLivedBetween(String first, String second);

    @Query("SELECT a FROM AuthorEntity a WHERE a.age >=  :minAge")
    Optional<List<AuthorEntity>> findByMinAge(@Param("minAge") String age);
}
