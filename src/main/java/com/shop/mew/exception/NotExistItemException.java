package com.shop.mew.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class NotExistItemException extends RuntimeException{
    public NotExistItemException(String message) {
        super(message);
    }
}
