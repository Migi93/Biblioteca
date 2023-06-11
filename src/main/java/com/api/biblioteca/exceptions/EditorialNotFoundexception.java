package com.api.biblioteca.exceptions;

import org.springframework.http.HttpStatus;

public class EditorialNotFoundexception extends ApplicationException {
    private static final String EXTERNAL_MESSAGE = "No se encuentra la %s";

    public EditorialNotFoundexception() {
        super(EXTERNAL_MESSAGE, HttpStatus.BAD_REQUEST);
    }

    public EditorialNotFoundexception(String variable, HttpStatus statusCode) {
        super(EXTERNAL_MESSAGE, statusCode, new String[]{variable});
    }
}
