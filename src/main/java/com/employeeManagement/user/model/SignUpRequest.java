package com.employeeManagement.user.model;

import lombok.Data;

@Data
public class SignUpRequest {

    private  String fullName;
    private  String email;
    private  String phoneNumber;
    private  String password;
}
