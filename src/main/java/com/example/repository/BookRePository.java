package com.example.repository;


import org.springframework.data.jpa.repository.JpaRepository;


import com.example.model.Book;

public interface  BookRePository extends JpaRepository<Book, String> {

    Book findByTitle(String title);

    boolean existsByIsbn(String isbn);

    Book findByIsbn(String isbn);
   
}
