package com.api.biblioteca.exceptions;

import org.springframework.http.HttpStatus;

public class RequiredMissingFieldException extends ApplicationException {
    private static final String EXTERNAL_MESSAGE = "El campo %s no puede estar vacio";

    public RequiredMissingFieldException(HttpStatus statusCode) {
        super(EXTERNAL_MESSAGE, statusCode);
    }

    public RequiredMissingFieldException(String mensaje, HttpStatus statusCode) {

        super(EXTERNAL_MESSAGE, statusCode, new String[]{mensaje});
    }
}
