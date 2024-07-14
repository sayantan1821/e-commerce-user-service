package com.sayantan.userservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponseDTO {
    private String message;
    private String stackTrace;

    public ErrorResponseDTO(String message, String stackTrace) {
        this.message = message;
        this.stackTrace = stackTrace;
    }
}
