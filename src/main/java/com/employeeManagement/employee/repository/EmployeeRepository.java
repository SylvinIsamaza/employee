package com.employeeManagement.employee.repository;


import com.employeeManagement.employee.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {


    ArrayList<Employee> findByCreatedByEmail(String email);
    Employee findByCreatedByEmailAndId(String email,Long id);

}
