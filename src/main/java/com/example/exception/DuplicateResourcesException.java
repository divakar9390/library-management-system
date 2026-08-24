package com.example.exception;

public class DuplicateResourcesException extends RuntimeException {

    public DuplicateResourcesException(String message){
        super(message);
    }
    
}
