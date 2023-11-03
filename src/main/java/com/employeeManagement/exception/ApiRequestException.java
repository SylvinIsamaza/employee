package com.employeeManagement.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiRequestException extends  RuntimeException{

    private    final HttpStatus status;
    public ApiRequestException(String message, HttpStatus status) {
        super(message);
        this.status= status;

    }

    public ApiRequestException(String message, Throwable cause, HttpStatus status) {
        super(message, cause);
        this.status=status;
    }
}
