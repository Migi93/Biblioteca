package com.api.biblioteca.exceptions;

import org.springframework.http.HttpStatus;

public class BookNotFoundException extends ApplicationException {

    private static final String EXTERNAL_MESSAGE = "ERROR. El %s no existe.";

    public BookNotFoundException() {
        super(EXTERNAL_MESSAGE, HttpStatus.BAD_REQUEST);
    }

    public BookNotFoundException(String variable, HttpStatus statusCode) {
        super(EXTERNAL_MESSAGE, statusCode, new String[]{variable});
    }
}
