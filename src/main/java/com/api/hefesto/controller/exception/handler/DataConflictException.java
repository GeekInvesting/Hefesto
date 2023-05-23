package com.api.hefesto.controller.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DataConflictException extends RuntimeException {

    public DataConflictException(String message) {
        super(message);
    }
}
