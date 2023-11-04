package com.employeeManagement.user.controller;


import com.employeeManagement.user.model.ResetPasswordRequest;
import com.employeeManagement.user.model.UpdatePasswordResponse;
import com.employeeManagement.user.model.UpdateUserRequest;
import com.employeeManagement.user.entities.User;
import com.employeeManagement.user.services.UserService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "User")
public class UserController {
    private final UserService userService;

    @Hidden
    @GetMapping
    public ResponseEntity<String> getUser() {
        return ResponseEntity.ok().body("hai User");

    }


//     get single user by providing token

    @Operation(
            summary = "Unlock the Power of JWT: Retrieve User Details",
            description = "Discover the secured gateway to retrieve user information by including your token in the request header.",
            responses = {
                    @ApiResponse(
                            description = "Success! User details are at your fingertips.",
                            responseCode = "200",
                            useReturnTypeSchema = true
                    ),
                    @ApiResponse(
                            description = "Uh-oh! Your token appears to be invalid or unauthorized.",
                            responseCode = "403 or 401"
                    )
            }, method = "get userDetails"


    )
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/getUser")
    public ResponseEntity<UserDetails> getSingleUser() {
        return ResponseEntity.ok().body(userService.getUser());
    }


    //     update user by passing token in header and body
    @Operation(
            summary = "Update User Profile",
            description = "This protected operation allows users to update their profile details by providing a valid access token in the request header and new credentials. Please note that the email cannot be updated.",
            responses = {
                    @ApiResponse(
                            description = "Profile updated successfully.",
                            responseCode = "200",
                            useReturnTypeSchema = true
                    ),
                    @ApiResponse(
                            description = "Invalid or unauthorized access. Please check your token.",
                            responseCode = "403 or 401"
                    ),
                    @ApiResponse(
                            description = "User not found. The specified user does not exist.",
                            responseCode = "404"
                    )
            },
            method = "updateUser"

    )
    @SecurityRequirement(name = "bearerAuth")

    @PutMapping("/updateUser")
    public ResponseEntity<UserDetails> updateUser(@RequestBody UpdateUserRequest updateUserRequest) {
        return ResponseEntity.ok().body(userService.updateUser(updateUserRequest));
    }


    //     delete user by passing token then send email tell you that we have deleted your account
    @Operation(
            summary = "Terminate Account Gracefully",
            description = "Safeguarded operation for deleting user details via a token in the header and send email notifying you. ",
            responses = {
                    @ApiResponse(
                            description = "Your account has been gracefully terminated. A confirmation email has been sent.",
                            responseCode = "200",
                            useReturnTypeSchema = true
                    ),
                    @ApiResponse(
                            description = "Oops! Your token is invalid or unauthorized. Account deletion failed.",
                            responseCode = "403 or 401"
                    ),
                    @ApiResponse(
                            description = "User not found. Deletion request aborted.",
                            responseCode = "404"
                    )
            },
            method = "deleteUser"

    )

    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping("/deleteUser")
    public ResponseEntity<?> deleteUser() {
        try {
            return ResponseEntity.ok().body(userService.deleteUSer());

        } catch (UsernameNotFoundException e) {
            log.error(" reaching out");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(" user not foundd");
        }
    }

    //     get all user
    @Operation(
            summary = "get all users by admin token",
            description = "protected operation for  get all users by providing    admin token , if it isn't admin token this can success",
            responses = {
                    @ApiResponse(

                            description = " success",
                            responseCode = "200",
                            useReturnTypeSchema = true
                    ),
                    @ApiResponse(
                            description = " invalid token or Unauthorized",
                            responseCode = "403 or 401"


                    )
                    ,
                    @ApiResponse(
                            description = "user not found",
                            responseCode = "404"


                    )

            }, method = "get all users"


    )
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        log.info(" call get all  users");
        return ResponseEntity.ok().body(userService.getAllUsers());
    }


//     update user password

    @Operation(
            summary = "update user password by using his token",
            description = "protected operation for  updating specific pass by providing oldPassword  and  user token , if token or oldPassword is invalid then this will provide  bad request status code",
            responses = {
                    @ApiResponse(

                            description = " success",
                            responseCode = "200",
                            useReturnTypeSchema = true
                    ),
                    @ApiResponse(
                            description = " invalid token or Unauthorized or invalid old password",
                            responseCode = "400 or 401  "


                    )
                    ,
                    @ApiResponse(
                            description = "user not found",
                            responseCode = "404"


                    )

            }, method = "update user password api"

//make  changes
    )
    @SecurityRequirement(name = "bearerAuth")

    @PutMapping("/updatePassword")
    public  ResponseEntity<UpdatePasswordResponse> sendResetUrl(@RequestBody ResetPasswordRequest resetPasswordRequest){
        log.info("call  send reset link");
        return  ResponseEntity.ok().body(userService.updatePassword(resetPasswordRequest));
    }
}
