package com.api.biblioteca.exceptions;

import org.springframework.http.HttpStatus;

public class ObjectFoundException extends ApplicationException {

    private static final String EXTERNAL_MESSAGE = "ERROR. El %s ya existe.";

    public ObjectFoundException() {
        super(EXTERNAL_MESSAGE, HttpStatus.BAD_REQUEST);
    }

    public ObjectFoundException(String variable, HttpStatus statusCode) {
        super(EXTERNAL_MESSAGE, statusCode, new String[]{variable});
    }
}