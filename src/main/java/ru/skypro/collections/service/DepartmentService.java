package ru.skypro.collections.service;

import ru.skypro.collections.model.Employee;

import java.util.List;
import java.util.Map;

public interface DepartmentService {

    Employee getEmployeeWithMaxSalary(Integer departmentID);
    Employee getEmployeeWithMinSalary(Integer departmentID);
    List<Employee> getAllEmployeesByDepartment(Integer departmentID);
    Map<Integer, List<Employee>> getAllEmployees();
}
