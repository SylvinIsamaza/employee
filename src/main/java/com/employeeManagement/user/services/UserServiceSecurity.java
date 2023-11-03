package com.employeeManagement.user.services;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserServiceSecurity {
    UserDetailsService userDetailsService();

}
