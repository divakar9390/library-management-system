package com.example.controller;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.dto.request.AuthorRequestDto;
import com.example.dto.response.AuthorResponseDto;
import com.example.service.AuthorService;

import jakarta.validation.Valid;

@RestController
public class AuthorController {
    private final AuthorService authorService;
   

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
     
    }
    
    @PostMapping("/authors/save")
    public AuthorResponseDto createAuthor(@Valid @RequestBody AuthorRequestDto author){
        return authorService.save(author);
    }
    @PostMapping("/authors/saveAll")
    public List<AuthorResponseDto> createAuthors(@Valid @RequestBody List<AuthorRequestDto> authors){
        return authorService.saveAll(authors);
    }

    @GetMapping("/authors")
    public Page<AuthorResponseDto> findallAuthors(Pageable pageable){
        return authorService.findall(pageable);
    }

    @GetMapping("/authors/{id}")
    public AuthorResponseDto findAuthorById(@PathVariable String id){
        return authorService.findById(id);
    }
    @GetMapping("/authors/name/{name}")
    public AuthorResponseDto findAuthorByName(@PathVariable String name){
        return authorService.findByName(name);
    }

    @DeleteMapping("/authors/{id}")
    public void deleteAuthorById(@PathVariable String id){
        authorService.deletdeById(id);
    }

}
