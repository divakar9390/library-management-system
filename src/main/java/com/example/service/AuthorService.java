package com.example.service;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.model.Author;
import com.example.repository.AuthorRepository;
import com.example.util.IDGenerator;
@Service
public class AuthorService {
    
    private final AuthorRepository authorRepository;
    private final IDGenerator idGenerator;

    public AuthorService(AuthorRepository authorRepository, IDGenerator idGenerator) {
        this.authorRepository = authorRepository;
        this.idGenerator = idGenerator;
    }

    public Author save(Author author){
        String authorId;
        do{
            authorId = idGenerator.generatorAuthorId(author.getName());
        } while (authorRepository.existsById(authorId));
        author.setAuthorId(authorId);
        return authorRepository.save(author);
    } 
    public List<Author> saveAll(List<Author> authors){
        authors.forEach(author->{
            String authorId;
            do{
                authorId = idGenerator.generatorAuthorId(author.getName());
            }while(authorRepository.existsById(authorId));
            author.setAuthorId(authorId);
        });
        return authorRepository.saveAll(authors);
    }
    
    public Optional<Author> findById(String id){
        return authorRepository.findById(id);
    }
    public List<Author> findall(){
        return authorRepository.findAll();
    }

    public void deletdeById(String id){
        authorRepository.deleteById(id);
    }
}
