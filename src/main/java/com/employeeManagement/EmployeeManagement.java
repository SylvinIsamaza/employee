package com.employeeManagement;

import com.employeeManagement.user.entities.Role;
import com.employeeManagement.user.entities.User;
import com.employeeManagement.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class EmployeeManagement implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(EmployeeManagement.class, args);
    }

    public void run(String... args) {
        User adminAccount = userRepository.findByRole(Role.ADMIN);
        if (null == adminAccount) {
            User user = new User();
            user.setEmail("jodos20061@gmail.com");
            user.setFullName("Jean de Dieu NSHIMYUMUKIZA");
            user.setRole(Role.ADMIN);
            user.setPassword(new BCryptPasswordEncoder().encode("jodos2006"));
            userRepository.save(user);
        }

    }
}
