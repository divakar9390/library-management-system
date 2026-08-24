package com.example.controller;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.request.BookRequestDto;
import com.example.dto.response.BookResponseDto;
import com.example.service.BookService;

import jakarta.validation.Valid;


@RestController
public class BookController {
    public final BookService bookService;
   
    public BookController(BookService bookService) {
        this.bookService = bookService;
       
    }

    @PostMapping("/books/save")
    public BookResponseDto saveBook(@Valid @RequestBody BookRequestDto book){
        return bookService.save(book);
    }
    @PostMapping("/books/saveAll")
    public List<BookResponseDto> saveAllBooks(@Valid @RequestBody List<BookRequestDto> books){
        return bookService.saveAll(books);
    }

    @GetMapping("/books")
    public List<BookResponseDto> findall(){
        return bookService.findall();
    }
    @GetMapping("/books/{id}")
    public BookResponseDto findById(@PathVariable String id){
        return bookService.findById(id);
    }
    @GetMapping("/books/title/{title}")
    public BookResponseDto findByTitle(@PathVariable String title){
        return bookService.findByTitle(title);
    }

    @DeleteMapping("/books/{id}")
    public void deleteById(@PathVariable String id){
        bookService.deleteById(id);
    }
    
}
