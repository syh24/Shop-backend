package com.shop.mew.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class NotExistCartException extends RuntimeException {
    public NotExistCartException(String message) {
        super(message);
    }
}
