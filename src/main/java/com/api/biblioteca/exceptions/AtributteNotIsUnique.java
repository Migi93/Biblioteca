package com.api.biblioteca.exceptions;

import org.springframework.http.HttpStatus;

public class AtributteNotIsUnique extends RequestApiValidationException {

    private static final String EXTERNAL_MESSAGE = "El campo %s ya existe.";

    public AtributteNotIsUnique() {
        super(EXTERNAL_MESSAGE, HttpStatus.BAD_REQUEST);
    }

    public AtributteNotIsUnique(String variable, HttpStatus statusCode) {
        super(EXTERNAL_MESSAGE, statusCode, new String[]{variable});
    }
}
