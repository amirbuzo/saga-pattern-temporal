package com.saga.common.error.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.saga.common.error.ErrorResponse;
import com.saga.common.error.InvalidOperationException;
import com.saga.common.error.ResourceNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(ResourceNotFoundException cause) {
        return new ResponseEntity<>(new ErrorResponse(cause.getMessage(), "ResourceNotFound"), HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(InvalidOperationException.class)
    public ResponseEntity<ErrorResponse> handleException(InvalidOperationException cause) {
        return new ResponseEntity<>(new ErrorResponse(cause.getMessage(), "InvalidOperation"), HttpStatus.PRECONDITION_FAILED);
    }
    
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleException(RuntimeException cause) {
        return new ResponseEntity<>(new ErrorResponse(cause.getMessage(), "RuntimeException"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
