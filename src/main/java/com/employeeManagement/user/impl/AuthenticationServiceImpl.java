package com.employeeManagement.user.impl;


import com.employeeManagement.user.model.JWtAuthenticationResponse;
import com.employeeManagement.user.model.RefreshTokenRequest;
import com.employeeManagement.user.model.SignInRequest;
import com.employeeManagement.user.model.SignUpRequest;
import com.employeeManagement.user.entities.Role;
import com.employeeManagement.user.entities.User;
import com.employeeManagement.exception.ApiRequestException;
import com.employeeManagement.user.repository.UserRepository;
import com.employeeManagement.user.services.AuthenticationService;
import com.employeeManagement.user.services.EmailService;
import com.employeeManagement.user.services.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final EmailService emailService;

//     delete  user
    @Override
    public User signUp(SignUpRequest signUpRequest) {
        User user = new User();
        user.setFullName(signUpRequest.getFullName());
        user.setEmail(signUpRequest.getEmail());
        user.setPhoneNumber(signUpRequest.getPhoneNumber());
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        boolean userExists = userRepository.findByEmail(signUpRequest.getEmail()).isPresent();
        if (userExists) {
            throw new ApiRequestException(" user already exists", HttpStatus.CONFLICT);
//
        } else {
            String subject = "Welcome to a World of Possibilities! 🚀";
            String text = "Dear " + signUpRequest.getFullName() + "\n\n" +
                    "🌟 Congratulations on joining our vibrant API community! We are delighted to have you on board, and we can't wait to see the incredible projects you'll create with our APIs.\n\n" +
                    "🚀 Your journey starts now. Explore the limitless potential of our APIs by visiting [API Documentation](https://localhost:8080/swagger-ui.html).\n\n" +
                    "🔧 Got questions or need assistance? Our dedicated team is here to help. Don't hesitate to reach out!\n\n" +
                    "🌐 Stay connected with us:\n" +
                    "LinkedIn: [Follow us on LinkedIn](https://www.linkedin.com/in/jean-de-dieu-nshimyumukiza-97b315259/)\n" +
                    "GitHub: [Check out our repositories on GitHub](https://github.com/jodosjodos)\n" +
                    "👉 Let's make your projects shine together!\n\n" +
                    "Best regards,\n" +
                    "Jodos Company Group , here credentials you have created for your account " + signUpRequest;


            emailService.sendSimpleEmailMessage(signUpRequest.getEmail(), subject, text);
            return userRepository.save(user);

        }

    }

//     sign in user
    @Override
    public JWtAuthenticationResponse signin(SignInRequest signinRequest) {

        var user = userRepository.findByEmail(signinRequest.getEmail())
                .orElseThrow(() -> new ApiRequestException(" user not found", HttpStatus.NOT_FOUND));
        if (!passwordEncoder.matches(signinRequest.getPassword(), user.getPassword())) {
            throw new ApiRequestException(" invalid credentials", HttpStatus.UNAUTHORIZED);
        }
        var jwt = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);
        return JWtAuthenticationResponse.builder()
                .refreshToken(refreshToken)
                .token(jwt)
                .user(user)
                .build();
    }

//     get refresh token
    public JWtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        String userEmail = jwtService.extractUserName(refreshTokenRequest.getRefreshToken());
        var user = userRepository.findByEmail(userEmail).orElseThrow(() -> new ApiRequestException(" user not found", HttpStatus.NOT_FOUND));

        if (jwtService.isTokenValid(refreshTokenRequest.getRefreshToken(), user)) {
            var jwt = jwtService.generateToken(user);

            return JWtAuthenticationResponse.builder()
                    .refreshToken(refreshTokenRequest.getRefreshToken())
                    .token(jwt)

                    .build();
        }
        return null;
    }
}
