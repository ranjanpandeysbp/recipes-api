package com.mycompany.recipeapi.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

    private static final Logger LOGGER = LoggerFactory.getLogger(ResourceNotFoundException.class);

    public ResourceNotFoundException() {
        super();
    }

    public ResourceNotFoundException(String message) {
        super(message);
        LOGGER.error(message);
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
        LOGGER.error(message, cause);
    }
}
