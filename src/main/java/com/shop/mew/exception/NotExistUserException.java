package com.shop.mew.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class NotExistUserException extends RuntimeException{
    public NotExistUserException(String message) {
        super(message);
    }
}
