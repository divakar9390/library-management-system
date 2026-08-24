package com.example.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Author;

public interface AuthorRepository extends JpaRepository<Author,String>{
    Optional<Author> findByName(String name);

}
