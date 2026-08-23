package com.example.dto;

import org.springframework.stereotype.Component;

@Component
public class BookResponseDto {

    private String bookId;
    private String title;
    private Double  price;
    private Boolean availability;
    private String language;

    public BookResponseDto(String bookId, String title, Double price, Boolean availability, String language) {
        this.bookId = bookId;
        this.title = title;
        this.price = price;
        this.availability = availability;
        this.language = language;
    }

    public String getBookId() {
        return bookId;
    }
    public String getTitle() {
        return title;
    }  
    public Double getPrice() {
        return price;
    }
    public Boolean getAvailability() {
        return availability;
    }
    public String getLanguage() {
        return language;
    }

    public BookResponseDto () {
    }
    
}
