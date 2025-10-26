package com.multitrans.wasalliya.exceptions;

import java.util.NoSuchElementException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleBodyError(HttpMessageNotReadableException ex){
        return ResponseEntity.badRequest().body("Please make sure you're json file are set as well");
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNotFount(NoSuchElementException ex){
        return ResponseEntity.badRequest().body("The record you are trying to reach is not found");
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrity(DataIntegrityViolationException ex){
        return ResponseEntity.badRequest().body("All the fields are required brother.");
    }

}
