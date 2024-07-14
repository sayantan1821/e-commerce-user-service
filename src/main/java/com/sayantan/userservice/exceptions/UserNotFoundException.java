package com.sayantan.userservice.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String mssg) {
        super(mssg);
    }
}
