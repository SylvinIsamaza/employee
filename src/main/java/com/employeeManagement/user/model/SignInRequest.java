package com.employeeManagement.user.model;

import lombok.Data;

@Data
public class SignInRequest {
    private  String email;
    private  String password;
}
