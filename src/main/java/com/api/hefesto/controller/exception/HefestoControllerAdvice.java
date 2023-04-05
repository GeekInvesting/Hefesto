package com.api.hefesto.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.api.hefesto.controller.exception.handler.DataConflictException;
import com.api.hefesto.controller.exception.handler.ResourceNotFoundException;

import jakarta.ws.rs.NotAcceptableException;

@ControllerAdvice
public class HefestoControllerAdvice {
    
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException e) {
        String errorMessage = "Illegal error Exception: " + e.getMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException e) {
        String errorMessage = "Not Found Exception: " + e.getMessage();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

    @ExceptionHandler(DataConflictException.class)
    public ResponseEntity<Object> handleDataConflictException(DataConflictException e) {
        String errorMessage = "Data Conflict Exception: " + e.getMessage();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorMessage);
    }

    @ExceptionHandler(NotAcceptableException.class)
    public ResponseEntity<Object> handleNotAcceptableException(NotAcceptableException e) {
        String errorMessage = "Invalid Data Exception: " + e.getMessage();
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(errorMessage);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception e) {
        String errorMessage = "Error Exception: " + e.getMessage();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
    }
}
