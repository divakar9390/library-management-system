package com.example.dto.response;

public class UserResponseDto {

    private String userId;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private String status;

    public UserResponseDto(
            String userId,
            String name,
            String email,
            String phoneNumber,
            String address,
            String status) {

        this.userId = userId;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getStatus() {
        return status;
    }
}