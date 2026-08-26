package com.example.dto.response;

import java.time.LocalDate;

public class LoanResponseDto {

    private String loanId;
    private String userId;
    private String userName;
    private String bookId;
    private String bookTitle;
    private LocalDate borrowedDate;
    private LocalDate dueDate;
    private LocalDate returnedDate;
    private String status;

    public LoanResponseDto(
            String loanId,
            String userId,
            String userName,
            String bookId,
            String bookTitle,
            LocalDate borrowedDate,
            LocalDate dueDate,
            LocalDate returnedDate,
            String status) {

        this.loanId = loanId;
        this.userId = userId;
        this.userName = userName;
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.borrowedDate = borrowedDate;
        this.dueDate = dueDate;
        this.returnedDate = returnedDate;
        this.status = status;
    }

    public String getLoanId() {
        return loanId;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getBookId() {
        return bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public LocalDate getBorrowedDate() {
        return borrowedDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public LocalDate getReturnedDate() {
        return returnedDate;
    }

    public String getStatus() {
        return status;
    }
}
    

