package com.example.presentation.exceptions;

public class PresentationException extends RuntimeException {

    public PresentationException(String message) {
        super(message);

    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
