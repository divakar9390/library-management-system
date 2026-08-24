package com.example.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.dto.response.BookResponseDto;
import com.example.model.Book;

public interface  BookRePository extends JpaRepository<Book, String> {

    Optional<BookResponseDto> findByTitle(String title);
   
}
