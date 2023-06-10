package com.api.biblioteca.controllers;

import com.api.biblioteca.exceptions.RequestApiValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final String DESCRIPCION = "Descripcion: ";

    @ExceptionHandler(RequestApiValidationException.class)
    @ResponseBody
    public ResponseEntity<Object> handleRequestApiValidationException(RequestApiValidationException e) {
        return new ResponseEntity<>(Map.of(DESCRIPCION, e.getExternalMessage()), e.getStatusCode());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<Object> handleException(Exception e) {
        return new ResponseEntity<>(Map.of(DESCRIPCION, "Error: Ha ocurrido un error. Contacte con el departamento IT de la Biblioteca."), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
