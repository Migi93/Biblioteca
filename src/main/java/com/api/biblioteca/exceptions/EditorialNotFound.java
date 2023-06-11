package com.api.biblioteca.exceptions;

import org.springframework.http.HttpStatus;

public class EditorialNotFound extends ApplicationException {
    private static final String EXTERNAL_MESSAGE = "No se encuentra la %s";

    public EditorialNotFound() {
        super(EXTERNAL_MESSAGE, HttpStatus.BAD_REQUEST);
    }

    public EditorialNotFound(String variable, HttpStatus statusCode) {
        super(EXTERNAL_MESSAGE, statusCode, new String[]{variable});
    }
}
