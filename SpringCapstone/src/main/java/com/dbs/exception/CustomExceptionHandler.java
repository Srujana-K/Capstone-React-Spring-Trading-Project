package com.dbs.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = InvalidClientException.class)
    public ResponseEntity<ErrorResponse> method1(InvalidClientException invalidClientException) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND, invalidClientException.getMessage());
        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(value = InvalidCustodianException.class)
    public ResponseEntity<ErrorResponse> method2(InvalidCustodianException invalidCustodianException) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND, invalidCustodianException.getMessage());
        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(value = InvalidInstrumentException.class)
    public ResponseEntity<ErrorResponse> method3(InvalidInstrumentException invalidInstrumentException) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND, invalidInstrumentException.getMessage());
        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(value = ValidationException.class)
    public ResponseEntity<ErrorResponse> method4(ValidationException validationException) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND, validationException.getMessage());
        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
