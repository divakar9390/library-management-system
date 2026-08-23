package com.example.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

@Entity
public class Book {
    @Id
    @NotBlank(message = "Book ID cannot be blank")
    @Column(unique = true,nullable = false)
    private String bookId;

    @NotBlank(message = "Title cannot be blank")
    private String title;

    @NotBlank(message = "ISBN cannot be blank")
    @Column(unique = true,nullable = false)
    private String isbn;
    
    @NotNull(message = "Price cannot be null")
    @DecimalMin(value = "100.0", message = "Price must be at least 100")
    @DecimalMax(value = "10000.0", message = "Price must be at most 10000")
    private Double price;
    
    @NotNull(message = "Availability cannot be null")
    private Boolean availability;

    @NotNull(message = "Publication date cannot be null")
    @PastOrPresent(message = "Publication date must be in the past or present")
    private LocalDate publicationDate;

    @NotBlank(message = "Publisher cannot be blank")
    private String publisher;

    @NotBlank
    private String language;

    @ManyToOne
    @JoinColumn(name = "author_id")
    @JsonBackReference
    private Author author;



    Book(String bookId,String title,String isbn,Double price,Boolean availability,LocalDate publicationDate,String publisher,String language,Author author){
        this.bookId = bookId;
        this.title = title;
        this.isbn = isbn;
        this.price = price;
        this.availability = availability;
        this.publicationDate = publicationDate;
        this.publisher = publisher;
        this.language = language;
        this.author = author;
    }



    public String getBookId(){
        return bookId;
    }  
    public void setBookId(String bookId){
        this.bookId = bookId;
    }
    
    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title = title;
    }

    public String getIsbn(){
        return isbn;
    }
    public void setIsbn(String isbn){
        this.isbn = isbn;
    }

    public Double getPrice(){
        return price;
    }
    public void setPrice(Double price){
        this.price = price;
    }

    public Boolean getAvailability(){
        return availability;
    }
    public void setAvailability(Boolean availability){
        this.availability = availability;
    }

    public LocalDate getPublicationDate(){
        return publicationDate;
    }
    public void setPublicationDate(LocalDate publicationDate){
        this.publicationDate = publicationDate;
    }

    public String getPublisher(){
        return publisher;
    }
    public void setPublisher(String publisher){
        this.publisher = publisher;
    }

    public String getLanguage(){
        return language;
    }
    public void setLanguage(String language){
        this.language = language;
    }
  
    public Author getAuthor() {
        return author;
    }
    public void setAuthor(Author author) {
        this.author = author;
    }

    public Book() {
    }

    
}
