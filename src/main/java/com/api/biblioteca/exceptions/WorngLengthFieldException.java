package com.api.biblioteca.exceptions;

import org.springframework.http.HttpStatus;

public class WorngLengthFieldException extends RequestApiValidationException {

    private static final String EXTERNAL_MESSAGE = "El campo %s sobrepasa la longitud maxima de entrada.";

    public WorngLengthFieldException() {
        super(EXTERNAL_MESSAGE, HttpStatus.BAD_REQUEST);
    }

    public WorngLengthFieldException(String variable, HttpStatus statusCode) {
        super(EXTERNAL_MESSAGE, statusCode, new String[]{variable});
    }

}
