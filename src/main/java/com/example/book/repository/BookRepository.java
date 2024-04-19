package com.example.book.repository;

import com.example.book.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {
//    BookEntity findByPriceOrPublicationDate(Double price, Integer publicationDate);

    //JPQL
//    @Query("SELECT p FROM BookEntity p WHERE p.price > :publishedYear")
//    List<BookEntity> givenPubYear(@Param("publishedYear") Integer publishedYear);

    //Native
//    @Query(value = "SELECT p FROM BookEntity p  WHERE price > :minPrice", nativeQuery = true)
//    List<BookEntity> lowerThanPrice(@Param("minPrice") Double minPrice);


    @Query(value = "SELECT * FROM books WHERE price >= :minPrice", nativeQuery = true)
    Optional<List<BookEntity>> findByMinPrice(@Param("minPrice") Double minPrice);

    @Query(value = "SELECT * FROM books WHERE publicationDate >= :minPubDate", nativeQuery = true)
    Optional<List<BookEntity>> findByMinPubDate(@Param("minPubDate") String minPubDate);
}
