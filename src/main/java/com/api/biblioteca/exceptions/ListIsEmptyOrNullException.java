package com.api.biblioteca.exceptions;

import org.springframework.http.HttpStatus;

public class ListIsEmptyOrNullException extends ApplicationException {

    private static final String EXTERNAL_MESSAGE = "La lista de %s esta vacia";

    public ListIsEmptyOrNullException() {
        super(EXTERNAL_MESSAGE, HttpStatus.BAD_REQUEST);
    }

    public ListIsEmptyOrNullException(String variable, HttpStatus statusCode) {
        super(EXTERNAL_MESSAGE, statusCode, new String[]{variable});
    }
}
