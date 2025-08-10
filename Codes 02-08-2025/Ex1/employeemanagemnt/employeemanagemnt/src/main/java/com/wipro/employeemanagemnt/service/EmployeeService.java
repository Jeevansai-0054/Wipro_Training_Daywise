package com.wipro.employeemanagemnt.service;

import com.wipro.employeemanagemnt.model.Employee;
import com.wipro.employeemanagemnt.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repo;

    public Employee saveEmployee(Employee employee) {
        return repo.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return repo.findAll();
    }

    public Employee getEmployeeById(int id) {
        return repo.findById(id).orElse(null);
    }

    public void deleteEmployee(int id) {
        repo.deleteById(id);
    }
}