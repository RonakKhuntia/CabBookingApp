package com.clone.backend.uber.exception;

public class RuntimeConflictException extends RuntimeException {
    public RuntimeConflictException() {
        super();
    }

    public RuntimeConflictException(String message) {
        super(message);
    }
}
