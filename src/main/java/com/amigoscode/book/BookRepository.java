package com.amigoscode.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT " +
            "new com.amigoscode.book.BookDto(b.id, b.title, b.createdAt) " +
            "FROM Book b")
    List<BookDto> getAllBooksDto();

    @Query("SELECT " +
            "new com.amigoscode.book.BookDto(b.id, b.title, b.createdAt) " +
            "FROM Book b " +
            "WHERE b.id = ?1")
    Optional<BookDto> getBookDtoById(Long id);
}
