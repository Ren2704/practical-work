package com.example.demo.exceptions;

import com.example.demo.exceptions.dto.response.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ErrorResponse handleNotFoundException(NotFoundException ex, HttpServletRequest request) {
        return ErrorResponse.buildErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage(), request);
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(AlreadyExistsException.class)
    public ErrorResponse handleBadRequestException(AlreadyExistsException ex, HttpServletRequest request) {
        return ErrorResponse.buildErrorResponse(HttpStatus.CONFLICT, ex.getMessage(), request);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({PhotoProcessingException.class, RuntimeException.class})
    public ErrorResponse handleException(RuntimeException ex, HttpServletRequest request) {
        return ErrorResponse.buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), request);
    }

    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    @ExceptionHandler(CompreFaceException.class)
    public ErrorResponse handleCompreFace(CompreFaceException ex, HttpServletRequest request) {
        return ErrorResponse.buildErrorResponse(HttpStatus.BAD_GATEWAY, ex.getMessage(), request);
    }
}
