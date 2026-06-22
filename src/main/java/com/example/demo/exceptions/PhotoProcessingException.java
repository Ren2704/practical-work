package com.example.demo.exceptions;

public class PhotoProcessingException extends RuntimeException {
    public PhotoProcessingException(String message) {
        super(message);
    }
}