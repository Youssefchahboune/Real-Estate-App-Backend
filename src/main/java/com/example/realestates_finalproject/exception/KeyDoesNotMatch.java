package com.example.realestates_finalproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.management.RuntimeErrorException;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class KeyDoesNotMatch extends RuntimeException {
    public KeyDoesNotMatch(String message){super(message);}
}
