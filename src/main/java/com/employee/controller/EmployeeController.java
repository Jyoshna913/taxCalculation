package com.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.employee.entity.EmployeeEntity;
import com.employee.service.EmpService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
	@Autowired
	EmpService employeeService;

	@PostMapping("/createEmp")
	private ResponseEntity createEmployee(@RequestBody EmployeeEntity employee) {
		try {
			if (employee.getEmployeeId() <= 0) {
				return ResponseEntity.ok("Ivalid Data,Employee code should be give");
			} else if (employee.getEmail() == "" && employee.getEmail().equalsIgnoreCase("")) {
				return ResponseEntity.ok("Ivalid Data,Email should not be null");
			} else if (employee.getFirstName() == "" && employee.getFirstName().equalsIgnoreCase("")) {
				return ResponseEntity.ok("Ivalid Data,FirstName should not be null");
			} else if (employee.getLastName() == "" && employee.getLastName().equalsIgnoreCase("")) {
				return ResponseEntity.ok("Ivalid Data,LastName should not be null");
			} else if (employee.getPhoneNumbers().size() < 1) {
				return ResponseEntity.ok("Ivalid Data,atleast one mobile number should give");
			} else if (employee.getDoj() == null) {
				return ResponseEntity.ok("Ivalid Data,DOJ should not be empty");
			} else if (employee.getSalary() <= 0) {
				return ResponseEntity.ok("Ivalid Data,Please provide monthly Salary");
			} else {
				employeeService.createEmp(employee);
				return new ResponseEntity("New employee created with id: " + employee.getEmployeeId(),
						HttpStatus.CREATED);
			}
		} catch (Exception exception) {
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("{employeeId}/tax-deductions")
	private List taxForEmps(@PathVariable("employeeId") String employeeId) {
		return employeeService.calEmpTax(employeeId);
	}
}
