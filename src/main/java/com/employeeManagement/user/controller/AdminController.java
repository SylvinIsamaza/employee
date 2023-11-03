package com.employeeManagement.user.controller;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
@Hidden
public class AdminController {
    @GetMapping
    public ResponseEntity<String> getAdmin(String heder){


        return  ResponseEntity.ok().body("hai Admin");

    }
}
