package com.wipro.employeemanagemnt.repository;

import com.wipro.employeemanagemnt.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
