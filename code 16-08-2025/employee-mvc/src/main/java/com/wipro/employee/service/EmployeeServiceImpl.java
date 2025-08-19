package com.wipro.employee.service;

import com.wipro.employee.entity.Employee;
import com.wipro.employee.exception.EmployeeNotFoundException;
import com.wipro.employee.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;
    public EmployeeServiceImpl(EmployeeRepository repository) { this.repository = repository; }

    @Override
    public Employee saveEmployee(Employee employee) { return repository.save(employee); }

    @Override
    public Employee getEmployeeById(Long id) {
        return repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id " + id));
    }

    @Override
    public List<Employee> getAllEmployees() { return repository.findAll(); }

    @Override
    public List<Employee> searchEmployees(String keyword) { return repository.findByNameContainingIgnoreCase(keyword); }

    @Override
    public Employee updateEmployee(Long id, Employee employee) {
        Employee existing = getEmployeeById(id);
        existing.setName(employee.getName());
        existing.setContract(employee.getContract());
        existing.setAddress(employee.getAddress());
        existing.setDepartment(employee.getDepartment());
        return repository.save(existing);
    }

    @Override
    public void deleteEmployee(Long id) {
        Employee existing = getEmployeeById(id);
        repository.delete(existing);
    }
}