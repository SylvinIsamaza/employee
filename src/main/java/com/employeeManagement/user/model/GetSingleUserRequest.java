package com.employeeManagement.user.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@Builder
public class GetSingleUserRequest {
    UserDetails userDetails;
}
