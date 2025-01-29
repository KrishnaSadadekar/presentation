package com.example.presentation.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {

  @ExceptionHandler
    public ResponseEntity<?> userNotFoundException(UserException message)
    {
        return new ResponseEntity<>(message.getMessage(),HttpStatus.NOT_FOUND);
    }
}
