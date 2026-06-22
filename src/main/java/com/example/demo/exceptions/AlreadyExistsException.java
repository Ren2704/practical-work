package com.example.demo.exceptions;

public class AlreadyExistsException extends RuntimeException {
    public AlreadyExistsException(String massage) {
        super(massage);
    }
}