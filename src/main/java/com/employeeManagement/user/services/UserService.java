package com.employeeManagement.user.services;


import com.employeeManagement.user.model.ResetPasswordRequest;
import com.employeeManagement.user.model.UpdatePasswordResponse;
import com.employeeManagement.user.model.UpdateUserRequest;
import com.employeeManagement.user.entities.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

public interface UserService {
 UserDetails getUser( );
    User updateUser(UpdateUserRequest updateUserRequest);

    Optional<User> deleteUSer() ;
    List<User> getAllUsers();
    UpdatePasswordResponse updatePassword (ResetPasswordRequest resetPasswordRequest);
}
