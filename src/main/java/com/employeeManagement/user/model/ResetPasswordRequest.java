package com.employeeManagement.user.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResetPasswordRequest {
private  String  oldPassword;
private    String newPassword;
}
