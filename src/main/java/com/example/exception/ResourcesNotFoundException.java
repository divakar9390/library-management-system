package com.example.exception;

public class ResourcesNotFoundException extends RuntimeException {

    public ResourcesNotFoundException(String message){
        super(message);
    }
    
}
