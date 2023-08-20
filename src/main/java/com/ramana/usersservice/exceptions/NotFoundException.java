package com.ramana.usersservice.exceptions;

import java.io.Serial;

public class NotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -970086936752735207L;

    public NotFoundException(String message) {
        super(message);
    }
}
