package com.weather.api.exception.handler;

public class WindSurferLocationNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public WindSurferLocationNotFoundException(String errorMessage) {
        super(errorMessage);
    }

}