package com.example.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

public class BookRequestDto {

    @NotBlank(message = "Title cannot be blank")
    private String title;

    @NotBlank(message = "ISBN cannot be blank")
    private String isbn;

    @NotNull(message = "Price cannot be null")
    @DecimalMin(value = "0.0", message = "Price cannot be negative")
    private Double price;

    @NotNull(message = "Availability cannot be null")
    private Boolean availability;

    @NotNull(message = "Publication date cannot be null")
    @PastOrPresent(message = "Publication date cannot be in the future")
    private LocalDate publicationDate;

    @NotBlank(message = "Publisher cannot be blank")
    private String publisher;

    @NotBlank(message = "Language cannot be blank")
    private String language;

    @NotBlank(message = "Author ID cannot be blank")
    private String authorId;

    public BookRequestDto(String title,String isbn,Double price,Boolean availability,LocalDate publicationDate,String publisher,String language,String authorId){
        this.title = title;
        this.isbn = isbn;
        this.price = price;
        this.availability = availability;
        this.publicationDate = publicationDate;
        this.publisher = publisher;
        this.language = language;
        this.authorId = authorId;

    }

    public String getTitle(){
        return title;
    }
    public String getIsbn(){
        return isbn;
    }
    public Double getPrice(){
        return price;
    }
    public Boolean getAvailability(){
        return availability;
    }
    public LocalDate getDate(){
        return publicationDate;
    }
    public  String getPublisher(){
        return publisher;
    }
    public String getLanguage(){
        return language;
    }
    public String getAuthorId(){
        return authorId;
    }

    public BookRequestDto(){

    }

}
