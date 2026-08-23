package com.example.dto;

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

    private String getBookId() {
        return bookId;
    }
    private String getTitle() {
        return title;
    }  
    private Double getPrice() {
        return price;
    }
    private Boolean getAvailability() {
        return availability;
    }
    private String getLanguage() {
        return language;
    }

    public BookResponseDto () {
    }
    
}
