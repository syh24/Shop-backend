package com.shop.mew.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class NotExistOrderException extends RuntimeException {
    public NotExistOrderException(String message) {
        super(message);
    }
}
