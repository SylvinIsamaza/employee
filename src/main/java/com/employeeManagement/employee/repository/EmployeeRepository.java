package com.employeeManagement.employee.repository;


import com.employeeManagement.employee.Entity.Employee;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {


    ArrayList<Employee> findByCreatedByEmail(String email, Pageable pageable);

    Employee findByCreatedByEmailAndId(String email, UUID id);


}
