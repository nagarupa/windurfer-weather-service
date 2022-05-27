package com.weather.api.exception.handler;

public class InvalidDateException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InvalidDateException(String errorMessage) {
        super(errorMessage);
    }

}
