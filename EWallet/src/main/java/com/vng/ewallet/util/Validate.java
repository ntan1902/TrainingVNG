package com.vng.ewallet.util;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@Log4j2
public class Validate {
    public static Map<String, String> checkValidate(BindingResult result) {
        log.info("Inside checkValidate of Validate");
        if(result.hasErrors()) {
            Map<String, String> err = new HashMap<>();

            for(FieldError fieldError : result.getFieldErrors()) {
                log.error("Inside checkValidate of Validate: {}", fieldError.getDefaultMessage());
                err.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return err;
        }
        return null;
    }
}
