package com.wipro.employee.service;

import com.wipro.employee.entity.Employee;
import java.util.List;

public interface EmployeeService {
    Employee saveEmployee(Employee employee);
    Employee getEmployeeById(Long id);
    List<Employee> getAllEmployees();
    List<Employee> searchEmployees(String keyword);
    Employee updateEmployee(Long id, Employee employee);
    void deleteEmployee(Long id);
}