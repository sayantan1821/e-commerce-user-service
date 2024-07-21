package com.sayantan.userservice.exceptions;

public class InvalidTokenException extends RuntimeException {
    public InvalidTokenException(String mssg) {
        super(mssg);
    }
}
