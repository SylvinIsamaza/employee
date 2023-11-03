package com.employeeManagement.user.services;


import com.employeeManagement.user.model.JWtAuthenticationResponse;
import com.employeeManagement.user.model.RefreshTokenRequest;
import com.employeeManagement.user.model.SignInRequest;
import com.employeeManagement.user.model.SignUpRequest;
import com.employeeManagement.user.entities.User;

public interface AuthenticationService {
    User signUp(SignUpRequest signUpRequest);
    JWtAuthenticationResponse signin(SignInRequest signInRequest);
    public  JWtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
