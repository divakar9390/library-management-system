package com.example.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;


public class AuthorRequestDto {
   
      @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Nationality cannot be blank")
    private String nationality;

    @NotBlank(message = "Phone number cannot be blank")
    @Pattern(
        regexp = "^[6-9]\\d{9}$",
        message = "Invalid phone number"
    )
    private String phoneNumber;

    @NotNull(message = "Date of birth cannot be null")
    @Past(message = "Date of birth must be in the past")
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
