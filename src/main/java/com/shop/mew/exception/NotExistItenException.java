package com.shop.mew.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class NotExistItenException extends RuntimeException{
    public NotExistItenException(String message) {
        super(message);
    }
}
