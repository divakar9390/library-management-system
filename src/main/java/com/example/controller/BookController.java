package com.example.controller;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.request.BookRequestDto;
import com.example.dto.response.BookResponseDto;
import com.example.service.BookService;


@RestController
public class BookController {
    public final BookService bookService;
   
    public BookController(BookService bookService) {
        this.bookService = bookService;
       
    }

    @PostMapping("/books/save")
    public BookResponseDto saveBook(@RequestBody BookRequestDto book){
        return bookService.save(book);
    }
    @PostMapping("/books/saveAll")
    public List<BookResponseDto> saveAllBooks(@RequestBody List<BookRequestDto> books){
        return bookService.saveAll(books);
    }

    @GetMapping("/books")
    public List<BookResponseDto> findall(){
        return bookService.findall();
    }
    @GetMapping("/books/{id}")
    public Optional<BookResponseDto> findById(@PathVariable String id){
        return bookService.findById(id);
    }
    @GetMapping("/books/title/{title}")
    public Optional<BookResponseDto> findByTitle(@PathVariable String title){
        return bookService.findByTitle(title);
    }

    @DeleteMapping("/books/{id}")
    public void deleteById(@PathVariable String id){
        bookService.deleteById(id);
    }
    
}
