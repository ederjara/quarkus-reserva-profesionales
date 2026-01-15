package com.ejara.platform.infraestructure.exceptions.handlers;

public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}
