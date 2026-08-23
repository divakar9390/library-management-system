package com.example.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;


@Entity
public class Author {
    @Id
    private String authorId;

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @Email (message = "Invalid email format")
    private String email;

    @NotBlank(message = "Nationality cannot be blank")
    private String nationality;

    @NotBlank(message = "Phone number cannot be blank")
    @Pattern(regexp = "^[6-9]\\d{9}$", message = "Invalid phone number")
    private String phoneNumber;

    @NotNull(message = "Date of birth  cannot be null")
    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;


    @OneToMany(mappedBy = "author")
    @JsonBackReference
    @JsonManagedReference
    
    private List<Book> books = new ArrayList<>();

    Author(String authorId,String name,String email,String nationality,String phoneNumber,LocalDate dateOfBirth,List<Book> books){
        this.authorId = authorId;
        this.name = name;
        this.email = email;
        this.nationality = nationality;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.books = books;
    }

    public String getAuthorId(){
        return authorId;
    }
    public void setAuthorId(String authorId){
        this.authorId = authorId;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }

    public String getNationality(){
        return nationality;
    }
    public void setNationality(String nationality){
        this.nationality = nationality;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getDateOfBirth(){
        return dateOfBirth;
    }
    public void setDateOfBirth(LocalDate dateOfBirth){
        this.dateOfBirth = dateOfBirth;
    }

    public List<Book> getBooks(){
        return books;
    }
    public void setBooks(List<Book> books){
        this.books = books;
    }
    
    public Author() {
    }
    
}
