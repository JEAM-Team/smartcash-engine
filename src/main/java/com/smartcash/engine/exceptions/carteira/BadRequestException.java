package com.smartcash.engine.exceptions.carteira;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
