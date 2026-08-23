package com.example.controller;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.BookResponseDto;
import com.example.model.Book;
import com.example.service.BookService;


@RestController
public class BookController {
    public final BookService bookService;
    private final BookResponseDto bookResponseDto;

    public BookController(BookService bookService, BookResponseDto bookResponseDto) {
        this.bookService = bookService;
        this.bookResponseDto = bookResponseDto;
    }

    @PostMapping("/books/save")
    public Book saveBook(@RequestBody Book book){
        return bookService.save(book);
    }
    @PostMapping("/books/saveAll")
    public List<Book> saveAllBooks(@RequestBody List<Book> books){
        return bookService.saveAll(books);
    }

    @GetMapping("/books")
    public List<Book> findall(){
        return bookService.findall();
    }
    @GetMapping("/books/{id}")
    public Optional<BookResponseDto> findById(@PathVariable String id){
        return bookService.findById(id);
    }

    @DeleteMapping("/books/{id}")
    public void deleteById(@PathVariable String id){
        bookService.deleteById(id);
    }
}
