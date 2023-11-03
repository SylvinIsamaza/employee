package com.employeeManagement.user.controller;

import com.employeeManagement.user.model.JWtAuthenticationResponse;
import com.employeeManagement.user.model.RefreshTokenRequest;
import com.employeeManagement.user.model.SignInRequest;
import com.employeeManagement.user.model.SignUpRequest;
import com.employeeManagement.user.services.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "login register ")
public class AuthenticationController {
    private final AuthenticationService authenticationService;


    @Operation(
            description = " Register user and send email  that notify you that you have created account and you  with credentials you  have created with ",
            summary = "this is summary for  register user with email, password , phone number and full name",
            responses = {
                    @ApiResponse(
                            description = "success  returns new user who have been created",
                            responseCode = "200"

                    ),
                    @ApiResponse(
                            description = "user with that email already exists",
                            responseCode = "409"

                    ),
                    @ApiResponse(
                            description = "provide invalid credentials",
                            responseCode = "401"

                    )
            })
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignUpRequest signUpRequest) {
        return ResponseEntity.ok(authenticationService.signUp(signUpRequest));


    }

//    this is controller that implement login of user by providing password and email
    @Operation(
            description = " Login user ",
            summary = "this is summary for how  login work you must provide password and email",
            responses = {
                    @ApiResponse(
                            description = "success it return  credentials      by adding token and refreshing token",
                            responseCode = "200"

                    ),
                    @ApiResponse(
                            description = "invalid credentials",
                            responseCode = "401"

                    )
            })
    @PostMapping("/signin")
    public ResponseEntity<JWtAuthenticationResponse> signin(@RequestBody SignInRequest signInRequest) {
        return ResponseEntity.ok(authenticationService.signin(signInRequest));
    }

    //     generate new token using refresh token
    @Operation(
            description = "generate new token ",
            summary = "this is summary for generate new token without login , by  using refresh token",
            responses = {
                    @ApiResponse(
                            description = "success it return  new token  and refresh token",
                            responseCode = "200"

                    ),
                    @ApiResponse(
                            description = "invalid token or parsing token have failed ",
                            responseCode = "401"

                    )
            })
    @PostMapping("/refresh")
    public ResponseEntity<JWtAuthenticationResponse> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
    }
}
