package com.api.hefesto.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.api.hefesto.controller.exception.handler.DataConflictException;
import com.api.hefesto.controller.exception.handler.NotAcceptableException;
import com.api.hefesto.controller.exception.handler.ResourceNotFoundException;
import com.api.hefesto.controller.exception.handler.UnauthorizedException;

@ControllerAdvice
public class HefestoControllerAdvice {
    
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException e) {
        String errorMessage = "{\"message\": \"Illegal error Exception: " + e.getMessage() + "\"}";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException e) {
        String errorMessage = "{\"message\": \"Not Found Exception: " + e.getMessage() + "\"}";
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

    @ExceptionHandler(DataConflictException.class)
    public ResponseEntity<Object> handleDataConflictException(DataConflictException e) {
        String errorMessage = "{\"message\": \"Data Conflict Exception: " + e.getMessage() + "\"}";
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorMessage);
    }

    @ExceptionHandler(NotAcceptableException.class)
    public ResponseEntity<Object> handleNotAcceptableException(NotAcceptableException e) {
        String errorMessage = "{\"message\": \"Invalid Data Exception: " + e.getMessage() + "\"}";
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(errorMessage);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception e) {
        String errorMessage = "{\"message\": \"Error Exception: " + e.getMessage() + "\"}";
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<Object> handleUnauthorizedException(UnauthorizedException e){
        String errorMessage = "{\"message\": \"Token Not is Valid!\"}";
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorMessage);
    }
}
