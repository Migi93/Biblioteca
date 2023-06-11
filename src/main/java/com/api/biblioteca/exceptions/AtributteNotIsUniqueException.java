package com.api.biblioteca.exceptions;

import org.springframework.http.HttpStatus;

public class AtributteNotIsUniqueException extends ApplicationException {

    private static final String EXTERNAL_MESSAGE = "El campo %s ya existe.";

    public AtributteNotIsUniqueException() {
        super(EXTERNAL_MESSAGE, HttpStatus.BAD_REQUEST);
    }

    public AtributteNotIsUniqueException(String variable, HttpStatus statusCode) {
        super(EXTERNAL_MESSAGE, statusCode, new String[]{variable});
    }
}
