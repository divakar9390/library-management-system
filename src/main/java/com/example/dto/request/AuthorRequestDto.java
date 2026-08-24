package com.example.dto.request;

import java.time.LocalDate;

public class AuthorRequestDto {
    private String name;
    private String email;
    private String nationality;
    private String phoneNumber;
    private LocalDate dateOfBirth;

    public AuthorRequestDto(String name, String email, String nationality,String phoneNumber,LocalDate dateOfBirth) {
        this.name = name;
        this.email = email;
        this.nationality = nationality; 
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
    }
    
    public String getName() {
        return name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getNationality(){
        return nationality;
    }
    public String getPhoneNumber(){
        return phoneNumber;
    }

    public LocalDate getDateOfBirth(){
        return dateOfBirth;
    }
     
    public AuthorRequestDto(){
        
    }

    
}
