package com.example.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Author;

public interface AuthorRepository extends JpaRepository<Author,String>{
    Author findByName(String name);

    boolean existsByPhoneNumber(String phoneNumber);
    boolean existsByEmail(String email);

}
