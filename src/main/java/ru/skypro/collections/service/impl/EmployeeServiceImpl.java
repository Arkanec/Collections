package ru.skypro.collections.service.impl;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import ru.skypro.collections.exception.EmployeeAlreadyAddedException;
import ru.skypro.collections.exception.EmployeeNotFoundException;
import ru.skypro.collections.exception.EmployeeStorageIsFullException;
import ru.skypro.collections.model.Employee;
import ru.skypro.collections.service.EmployeeService;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final int STORAGE_SIZE = 50;

    @PostConstruct
    public void initEmployees() {
        add("Ivan", "Petrov", 100_000, 1);
        add("Ivan1", "Petrov", 200_000, 1);
        add("Ivan2", "Petrov", 300_000, 1);

        add("Ivan3", "Petrov", 300_000, 2);
        add("Ivan4", "Petrov", 500_000, 2);

        add("Ivan5", "Petrov", 1_000_000, 3);
    }

    private final Map<String, Employee> employees = new HashMap<>();
    private int salary;
    private int department;


    @Override
    public Employee add(String firstName, String lastName, Integer salary, Integer department) {
        if (employees.size() >= STORAGE_SIZE) {
            throw new EmployeeStorageIsFullException("Невозможно добавить сотрудника. Хранилище полное.");
            }
        Employee employee = new Employee(firstName, lastName, salary, department);
        if (employees.containsKey(employee.getFullName())) {
            throw new EmployeeAlreadyAddedException("Такой сотрудник уже есть");
        }


        employees.put(employee.getFullName(), employee);
        return employee;
    }

    @Override
    public Employee remove(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName, salary, department);
        if (!employees.containsKey(employee.getFullName())) {
            throw new EmployeeNotFoundException("Сотрудник с таким именем и фамилией не найден");
        }

        employees.remove(employee.getFullName());
        return employee;
    }

    @Override
    public Employee find(String firstName, String lastName) {
        Employee requetedEmployee = new Employee(firstName, lastName, salary, department);

        Employee employeeFromMap = employees.get(requetedEmployee.getFullName());
         if (employeeFromMap == null) {
             throw new EmployeeNotFoundException("Сотрудник с таким именем и фамилией не найден");
        }
        return employeeFromMap;
    }

    @Override
    public Map<String, Employee>  getAll() {
        return new HashMap<>(employees);
    }
}
