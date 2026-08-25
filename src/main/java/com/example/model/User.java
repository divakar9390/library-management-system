package com.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
@Entity
public class User {

    @Id
    private String userId;

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email cannot be blank")
    private String email;

    @NotBlank(message = "Phone number cannot be blank")
    @Pattern(
        regexp = "^[6-9]\\d{9}$",
        message = "Invalid phone number"
    )
    private String phoneNumber;

    @NotBlank(message = "Address cannot be blank")
    private String address;

    @NotBlank(message = "Status cannot be blank")
    private String status;

    public User(String userId,String name,String email,String phoneNumber,String address,String status){
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.status = status;

    }
    public User(){

    }

    public String getUserId(){
        return userId;
    }
    public String getName(){
        return name;
    }
    public String  getEmail(){
        return email;
    }
    public String getPhoneNumber(){
        return phoneNumber;
    }
    public String getAddress(){
        return address;
    }
    public String getStatus(){
        return status;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }
     public void setName(String name){
        this.name = name;
    }
     public void setEmail(String email){
        this.email = email;
    }
     public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }
     public void setAddress(String address){
        this.address = address ;
    }
     public void setStatus(String status){
        this.status = status;
    }
    
}
    

