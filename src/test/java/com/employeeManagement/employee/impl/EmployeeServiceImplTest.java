package com.employeeManagement.employee.impl;

import com.employeeManagement.employee.Entity.Employee;
import com.employeeManagement.employee.repository.EmployeeRepository;
import com.employeeManagement.employee.services.EmployeeService;
import com.employeeManagement.exception.ApiRequestException;
import com.employeeManagement.user.entities.Role;
import com.employeeManagement.user.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EmployeeServiceImplTest {

    private EmployeeService employeeService;
    private EmployeeRepository employeeRepository;

    @BeforeEach
    void setup() {
        employeeRepository = mock(EmployeeRepository.class);
        employeeService = new EmployeeServiceImpl(employeeRepository);
User user = new User();
user.setEmail("jodos2006@gmail.com");
user.setFullName("jodos mushi");
user.setPhoneNumber("235");
user.setPassword("umugabo15");
user.setRole(Role.USER);
user.setId(1);

        // Mock the SecurityContext and Authentication
        SecurityContext securityContext = mock(SecurityContext.class);
        Authentication authentication = mock(Authentication.class);
        when(authentication.getPrincipal()).thenReturn(user);
        when(securityContext.getAuthentication()).thenReturn(authentication);

        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    void testGetExistingEmployee() {
        // Arrange
        Long id = 1L;
        Employee expectedEmployee = new Employee(); // Create your expected Employee object

        // Mock the repository's behavior for findByCreatedByEmailAndId
        when(employeeRepository.findByCreatedByEmailAndId("user@example.com", id))
                .thenReturn(expectedEmployee);
        // Mock the repository's behavior for findById
        when(employeeRepository.findById(id))
                .thenReturn(Optional.of(expectedEmployee));

        // Act
        Employee result = employeeService.getEmployee(id);

        // Assert
        assertNotNull(result);
        assertEquals(expectedEmployee, result);
    }

    @Test
    void testGetNonExistingEmployee() {
        // Arrange
        Long id = 2L;

        // Mock the repository's behavior for findByCreatedByEmailAndId
        when(employeeRepository.findByCreatedByEmailAndId("user@example.com", id))
                .thenReturn(null);
        // Mock the repository's behavior for findById
        when(employeeRepository.findById(id))
                .thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(ApiRequestException.class, () -> employeeService.getEmployee(id));
    }
}
