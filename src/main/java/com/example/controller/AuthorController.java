package com.example.controller;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Author;
import com.example.service.AuthorService;

@RestController
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }
    
    @PostMapping("/authors/save")
    public Author createAuthor(@RequestBody Author author){
        return authorService.save(author);
    }

    @GetMapping("/authors")
    public List<Author> findallAuthors(){
        return authorService.findall();
    }

    @GetMapping("/authors/{id}")
    public Author findAuthorById(@PathVariable Long id){
        return authorService.findById(id);
    }

    @DeleteMapping("/authors/{id}")
    public void deleteAuthorById(@PathVariable Long id){
        authorService.deletdeById(id);
    }

}
