package com.employeeManagement.employee.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponse {
private  String email;
private  String name;
private  String message;
}
