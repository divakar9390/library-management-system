package com.example.dto.request;

public class LoanRequestDto {

    private String userId;
    private String bookId;

    public LoanRequestDto() {
    }

    public LoanRequestDto(String userId, String bookId) {
        this.userId = userId;
        this.bookId = bookId;
        
    }

    public String getUserId() {
        return userId;
    }

    public String getBookId() {
        return bookId;
    }
}