package com.airlines.services;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ErrorService extends RuntimeException {

    public ErrorService(String iata) {
        super("Could not find airline: " + iata);
    }
}
