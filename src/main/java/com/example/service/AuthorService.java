package com.example.service;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.dto.request.AuthorRequestDto;
import com.example.dto.response.AuthorResponseDto;
import com.example.exception.DuplicateResourcesException;
import com.example.exception.ResourcesNotFoundException;
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

    public AuthorResponseDto save(AuthorRequestDto request){

        if(authorRepository.existsByPhoneNumber(request.getPhoneNumber())){
               throw new DuplicateResourcesException("Phone Number Already Exists " +request.getPhoneNumber());
        }
        if(authorRepository.existsByEmail(request.getEmail())){
            throw new DuplicateResourcesException("Author  Email Already Exists " +request.getEmail());
        }

        Author author = new Author();
        {
        author.setName(request.getName());
        author.setEmail(request.getEmail());
        author.setNationality(request.getNationality());
        author.setPhoneNumber(request.getPhoneNumber());
        author.setDateOfBirth(request.getDateOfBirth());}

        String authorId;
        do{
            authorId = idGenerator.generatorAuthorId(author.getName());
        } while (authorRepository.existsById(authorId));
        author.setAuthorId(authorId);
        Author savedAuthor = authorRepository.save(author);
        return new AuthorResponseDto(
            savedAuthor.getAuthorId(),
            savedAuthor.getName(),
            savedAuthor.getEmail(),
            savedAuthor.getNationality()
        );
    } 
    public List<AuthorResponseDto> saveAll(List<AuthorRequestDto> requests){
        List<Author> authors = requests.stream()
            .map(request -> {
                 if(authorRepository.existsByPhoneNumber(request.getPhoneNumber())){
                        throw new DuplicateResourcesException("Phone Number Already Exists " +request.getPhoneNumber());
                 }
                 if(authorRepository.existsByEmail(request.getEmail())){
                        throw new DuplicateResourcesException("Author  Email Already Exists " +request.getEmail());
                 }
                Author author = new Author();

                author.setName(request.getName());
                author.setEmail(request.getEmail());
                author.setNationality(request.getNationality());
                author.setPhoneNumber(request.getPhoneNumber());
                author.setDateOfBirth(request.getDateOfBirth());

                String authorId;

                do {
                    authorId = idGenerator.generatorAuthorId(author.getName());
                } while (authorRepository.existsById(authorId));

                author.setAuthorId(authorId);

                return author;
            })
            .toList();

        return authorRepository.saveAll(authors).stream().map(author -> new AuthorResponseDto(
            author.getAuthorId(),
            author.getName(),
            author.getEmail(),
            author.getNationality()
        )).collect(Collectors.toList());
    }
    
    public AuthorResponseDto findById(String id) {

        Author author = authorRepository.findById(id)
                .orElseThrow(() -> 
                    new ResourcesNotFoundException(
                        "Author with Id not found: " + id
                    )
                );

        return new AuthorResponseDto(
                author.getAuthorId(),
                author.getName(),
                author.getEmail(),
                author.getNationality()
        );
    }
    public AuthorResponseDto findByName(String name){
        Author author = authorRepository.findByName(name); 
        if(author == null){
            throw new ResourcesNotFoundException("author with name not found!"+name);
        }
        return new AuthorResponseDto(
            author.getAuthorId(),
            author.getName(),
            author.getEmail(),
            author.getNationality()
        );
    }
    public Page<AuthorResponseDto> findall(Pageable pageable){
        Page<Author> authors = authorRepository.findAll(pageable);
        if(authors.isEmpty()){
            throw new ResourcesNotFoundException("Authors Not found");
        }
        return authors.map(author -> new AuthorResponseDto(
            author.getAuthorId(),
            author.getName(),
            author.getEmail(),
            author.getNationality()
        ));
    }

    public void deletdeById(String id){
        if(!authorRepository.existsById(id)){
            throw new ResourcesNotFoundException("Author with Id Not Found" +id); 
        }
        authorRepository.deleteById(id);
    }
}
