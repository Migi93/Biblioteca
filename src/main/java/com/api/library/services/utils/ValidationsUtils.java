package com.api.library.services.utils;

import com.api.library.exceptions.RequiredMissingFieldException;
import com.api.library.exceptions.WorngLengthFieldException;
import org.springframework.http.HttpStatus;

public class ValidationsUtils {
    public void validateLengthName(int amount, int length, String name) {
        if (length > amount) {
            throw new WorngLengthFieldException(name, HttpStatus.PAYLOAD_TOO_LARGE);
        }
    }

    public void validateIsEmpty(String field, String name) {
        if (field.isEmpty()) {
            throw new RequiredMissingFieldException(name, HttpStatus.BAD_REQUEST);
        }
    }


}
