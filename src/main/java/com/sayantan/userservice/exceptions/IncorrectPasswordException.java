package com.sayantan.userservice.exceptions;

public class IncorrectPasswordException extends RuntimeException {
    public IncorrectPasswordException(String mssg) {
        super(mssg);
    }
}
