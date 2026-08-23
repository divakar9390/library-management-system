package com.example.util;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Component;

@Component
public class IDGenerator {

    public static String generatorBookId(){
        int n = ThreadLocalRandom.current().nextInt(100000, 999999);
        String bookId = "BK" + n;
        return bookId;
    }

    public static String generatorAuthorId(String name){
        int n = ThreadLocalRandom.current().nextInt(1000, 9999);
        String authorId = name.substring(0,3).toUpperCase() + n;
        return authorId;
    }
    
}
