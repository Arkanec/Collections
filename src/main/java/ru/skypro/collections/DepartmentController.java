package ru.skypro.collections;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.collections.model.Employee;
import ru.skypro.collections.service.DepartmentService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("departments")

public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("max-salary")
    public Employee getEmployeeWithMaxSalary(@RequestParam("departmentID") Integer departmentID) {
        return departmentService.getEmployeeWithMaxSalary(departmentID);
    }


    @GetMapping("min-salary")
    public Employee getEmployeeWithMinSalary(@RequestParam("departmentID") Integer departmentID) {
        return departmentService.getEmployeeWithMinSalary(departmentID);
    }


    @GetMapping(value = "all", params = "departmentID")
    public List<Employee> getAllEmployeesByDepartment(@RequestParam("departmentID") Integer departmentID) {
        return departmentService.getAllEmployeesByDepartment(departmentID);
    }

    @GetMapping("all")
    public Map<Integer, List<Employee>> getAllEmployees() {
        return departmentService.getAllEmployees();
    }
}
