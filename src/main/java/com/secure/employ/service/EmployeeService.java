package com.secure.employ.service;

import com.secure.employ.entity.Employee;
import com.secure.employ.exception.EmployeeNotFoundException;
import com.secure.employ.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.getAllEmployees();
    }

    public Employee getEmployeeById(Integer id) {
        Employee employee = employeeRepository.getEmployeeById(id);
        if (employee == null) {
           throw new EmployeeNotFoundException("Employee not found with ID: " + id);
        }
        return employee;
    }

    public void addEmployee(Employee employee) {
        employeeRepository.addEmployee(employee);
    }

    public void updateEmployee(Integer id, Employee employee) {
        Employee existingEmployee = getEmployeeById(id);
        if (existingEmployee != null) {
            employee.setEmployeeId(id);
            employeeRepository.updateEmployee(employee);
        }
    }

    public boolean deleteEmployee (Integer id){
        Employee deleteEmployee = getEmployeeById(id);
        if (deleteEmployee != null) {
            employeeRepository.deleteEmployee(id);
            return true;
        }
        return false;
    }
}

