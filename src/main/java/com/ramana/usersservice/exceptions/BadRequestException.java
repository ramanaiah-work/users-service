package com.ramana.usersservice.exceptions;


import java.io.Serial;

public class BadRequestException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 8941382489034144984L;

    public BadRequestException(String message) {
        super(message);
    }
}
