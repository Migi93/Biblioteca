package com.api.library.exceptions;

import org.springframework.http.HttpStatus;

public class BookNotFoundException extends ApplicationException {

    private static final String EXTERNAL_MESSAGE = "Error. The %s does not exist.";

    public BookNotFoundException() {
        super(EXTERNAL_MESSAGE, HttpStatus.BAD_REQUEST);
    }

    public BookNotFoundException(String variable, HttpStatus statusCode) {
        super(EXTERNAL_MESSAGE, statusCode, new String[]{variable});
    }
}
