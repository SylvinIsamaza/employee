package com.employeeManagement.user.impl;


import com.employeeManagement.employee.Entity.Employee;
import com.employeeManagement.employee.repository.EmployeeRepository;
import com.employeeManagement.user.entities.User;
import com.employeeManagement.exception.ApiRequestException;
import com.employeeManagement.user.model.ResetPasswordRequest;
import com.employeeManagement.user.model.UpdatePasswordResponse;
import com.employeeManagement.user.model.UpdateUserRequest;
import com.employeeManagement.user.repository.UserRepository;
import com.employeeManagement.user.services.EmailService;
import com.employeeManagement.user.services.JWTService;
import com.employeeManagement.user.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final EmailService emailService;
    private  final EmployeeRepository employeeRepository;


//     get single user by get token passed in header by Authorization no need to pass it second time that is why this function doesn't take any  argument as in spring config  we require token

    @Override
    public UserDetails getUser() {

        SecurityContext securityContext = SecurityContextHolder.getContext();
        var user = (UserDetails) securityContext.getAuthentication().getPrincipal();

        log.info(" get user have been called: " + user);
        return user;
    }

    //     update user
    @Override
    public User updateUser(UpdateUserRequest updateUserRequest) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        var token = securityContext.getAuthentication().getCredentials().toString();
        var userEmail = jwtService.extractUserName(token);
        User user = userRepository.findByEmail(userEmail).orElseThrow(() -> new ApiRequestException(" user  not found", HttpStatus.NOT_FOUND));
        if (updateUserRequest.getPhoneNumber() != null) {
            user.setPhoneNumber(updateUserRequest.getPhoneNumber());
        }
        if (updateUserRequest.getFullName() != null) {
            user.setFullName(updateUserRequest.getFullName());
        }

        log.info(" update user have been called : " + user);

        userRepository.save(user);
        return user;
    }

    //     delete user
    @Override
    public Optional<User> deleteUSer() throws UsernameNotFoundException {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        var token = securityContext.getAuthentication().getCredentials().toString();
        var userEmail = jwtService.extractUserName(token);
        String subject = "Account Deleted Successfully 🌟";
        List<Employee> employees =  employeeRepository.findByCreatedByEmail(userEmail,null);
        employeeRepository.deleteAll(employees);

        String text = "Hello " + userEmail + ",\n\n" +
                "We want to inform you that your account has been successfully deleted from our API community. We are grateful for the time you spent with us and hope your experience was rewarding.\n\n" +
                "If you ever decide to return, we'll be here to welcome you back. Until then, we wish you the best in your future endeavors.\n\n" +
                "If you have any questions or need assistance, please don't hesitate to reach out. We're here to help.\n\n" +
                "Stay connected with us:\n" +
                "LinkedIn: [Follow us on LinkedIn](https://www.linkedin.com/in/jean-de-dieu-nshimyumukiza-97b315259/)\n" +
                "GitHub: [Check out our repositories on GitHub](https://github.com/jodosjodos)\n" +
                "🌟 Thank you for being part of our community. Let's shine together!\n\n" +
                "Best regards,\n" +
                "Jodos Company Group";

        emailService.sendSimpleEmailMessage(userEmail, subject, text);
        var user = userRepository.findByEmail(userEmail);
        userRepository.deleteByEmail(userEmail);
        log.warn("User account has been successfully deleted.");
        return user;
    }


    //     geta ll users
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override

    public UpdatePasswordResponse updatePassword(ResetPasswordRequest resetPasswordRequest) {

        SecurityContext securityContext = SecurityContextHolder.getContext();
        var token = securityContext.getAuthentication().getCredentials().toString();
        var userEmail = jwtService.extractUserName(token);
        User user = userRepository.findByEmail(userEmail).orElseThrow(() -> new ApiRequestException(" user not found", HttpStatus.NOT_FOUND));
        if (passwordEncoder.matches(resetPasswordRequest.getOldPassword(), user.getPassword())) {
            String encodedPassword = passwordEncoder.encode(resetPasswordRequest.getNewPassword());
            log.info(encodedPassword);
            user.setPassword(encodedPassword);
            userRepository.save(user);
            log.info(" user  password have been updated successfully");
            return
                    UpdatePasswordResponse.builder()
                    .email(userEmail)
                    .msg(" user with this email have updated his password successfully")
                    .build();
        } else {
            throw new ApiRequestException(" please  make sure that old password match with that one  you have saved in db", HttpStatus.BAD_REQUEST);
        }
    }

}
