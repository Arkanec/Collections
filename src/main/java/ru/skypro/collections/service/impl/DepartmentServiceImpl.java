package ru.skypro.collections.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.collections.model.Employee;
import ru.skypro.collections.service.DepartmentService;
import ru.skypro.collections.service.EmployeeService;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final EmployeeService employeeService;

    public DepartmentServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public Employee getEmployeeWithMaxSalary(Integer departmentID) {
        return employeeService.getAll().values()
                .stream()
                .filter(employee -> departmentID.equals(employee.getDepartment()))
                .max(Comparator.comparingInt(Employee::getSalary))
                .orElse(null);
    }

    @Override
    public Employee getEmployeeWithMinSalary(Integer departmentID) {
        return employeeService.getAll().values()
                .stream()
                .filter(employee -> departmentID.equals(employee.getDepartment()))
                .min(Comparator.comparingInt(Employee::getSalary))
                .orElse(null);
    }

    @Override
    public List<Employee> getAllEmployeesByDepartment(Integer departmentID) {
        return employeeService.getAll().values()
                .stream()
                .filter(employee -> departmentID.equals(employee.getDepartment()))
                .collect(Collectors.toList());
    }

    @Override
    public Map<Integer, List<Employee>> getAllEmployees() {
        return employeeService.getAll().values()
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }
}
