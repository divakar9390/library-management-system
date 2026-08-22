package com.example.service;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.model.Author;
import com.example.repository.AuthorRepository;
@Service
public class AuthorService {
    
    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author save(Author author){
        return authorRepository.save(author);
    }  
    
    public  Author findById(Long id){
        return authorRepository.findById(id).orElse(null);
    }
    public List<Author> findall(){
        return authorRepository.findAll();
    }

    public void deletdeById(Long id){
        authorRepository.deleteById(id);
    }
}
