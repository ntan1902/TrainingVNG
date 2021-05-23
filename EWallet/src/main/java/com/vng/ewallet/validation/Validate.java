package com.vng.ewallet.validation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

public class Validate {
    public static Map<String, String> checkValidate(BindingResult result) {
        if(result.hasErrors()) {
            Map<String, String> err = new HashMap<>();

            for(FieldError fieldError : result.getFieldErrors()) {
                err.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return err;
        }
        return null;
    }
}
