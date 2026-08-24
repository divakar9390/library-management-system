package com.example.dto.request;

import java.time.LocalDate;

public class BookRequestDto {

    private String title;
    private String isbn;
    private Double price;
    private Boolean availability;
    private LocalDate publicationDate;
    private String publisher;
    private String language;
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
