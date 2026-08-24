package com.example.dto.response;

public class AuthorResponseDto {
    private String authorId;
    private String name;
    private String email;
    private String nationality;

    public AuthorResponseDto(String authorId, String name, String email, String nationality) {
        this.authorId = authorId;
        this.name = name;
        this.email = email;
        this.nationality = nationality;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public AuthorResponseDto() {
    }
}