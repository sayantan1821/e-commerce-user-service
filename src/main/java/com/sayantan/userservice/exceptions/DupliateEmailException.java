package com.sayantan.userservice.exceptions;

public class DupliateEmailException extends RuntimeException{
    public DupliateEmailException(String mssg) {
        super(mssg);
    }
}
