package com.employeeManagement.user.handlers;

import com.employeeManagement.user.errors.ApiException;
import com.employeeManagement.user.exception.ApiRequestException;
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
                e,
                e.getStatus(),
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(exc, e.getStatus());
    }
}
