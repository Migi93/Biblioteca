package com.api.biblioteca.exceptions;


import org.springframework.http.HttpStatus;


public class RequestApiValidationException extends ApplicationException {

    private final String externalMessage;
    private final HttpStatus statusCode;

    public RequestApiValidationException(String externalMessage, HttpStatus statusCode) {
        super(externalMessage, statusCode);
        this.externalMessage = externalMessage;
        this.statusCode = statusCode;
    }

    public RequestApiValidationException(String externalMessage, HttpStatus statusCode, String[] args) {
        super(externalMessage, statusCode, args);
        this.externalMessage = String.format(externalMessage, args);
        this.statusCode = statusCode;
    }

    @Override
    public String getExternalMessage() {
        return externalMessage;
    }
    
    @Override
    public HttpStatus getStatusCode() {
        return statusCode;
    }
}
