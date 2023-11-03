package com.employeeManagement.handlers;

import com.employeeManagement.errors.ApiException;
import com.employeeManagement.exception.ApiRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZoneId;
import java.time.ZonedDateTime;
@ControllerAdvice
@RestController

public class ApiExceptionHandler  {
    @ExceptionHandler(value = {ApiRequestException.class, UsernameNotFoundException.class})

    public ResponseEntity<Object> handleApiRequestExceptionAlready(ApiRequestException e) {
        ApiException exc = new ApiException(
                e.getMessage(),
                e.getCause(),
                e.getStatus(),
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(exc, e.getStatus());
    }
}
