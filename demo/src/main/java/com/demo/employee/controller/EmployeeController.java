package com.demo.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.employee.controller.controlleradvice.RequiredFieldExceptionHandler;
import com.demo.employee.dateutils.DateUtils;
import com.demo.employee.service.EmployeeService;
import com.demo.example.controller.dto.Employee;

import io.micrometer.common.util.StringUtils;

@RestController
@RequestMapping("/api")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@PostMapping("/employee")
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
		validation(employee);
		return ResponseEntity.ok(employeeService.createEmployee(employee));
	}

	@PatchMapping("/employee/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
		validation(employee);
		Employee updateEmployee = employeeService.patchEmployee(id, employee);

		return ResponseEntity.ok(updateEmployee);
	}

	@PutMapping("/employee/{id}")
	public ResponseEntity<Employee> updatedEmployee(@PathVariable Long id, @RequestBody Employee employee) {
		validation(employee);
		Employee updatedEmployee = employeeService.putEmployee(id, employee);
		return ResponseEntity.ok(updatedEmployee);
	}

	@GetMapping("/employee/{id}")
	public ResponseEntity<Employee> getEmployee(@PathVariable Long id) {
		Employee employee = employeeService.getEmployee(id);
		return ResponseEntity.ok(employee);
	}

	@DeleteMapping("/employee/{id}")
	public ResponseEntity<Employee> deleteEmployee(@PathVariable Long id) {
		Employee deletedEmployee = employeeService.deleteEmployee(id);
		return ResponseEntity.ok(deletedEmployee);
	}

	private void validation(Employee employee) {
		if (StringUtils.isBlank(employee.getFirstName())) {
			throw new RequiredFieldExceptionHandler("FirstName is Required");
		}
		if (!DateUtils.isValidFormat("yyyy-MM-dd", employee.getStartDate())) {
			throw new RequiredFieldExceptionHandler("Invalid Start Date Format. Accepted format is yyyy-MM-dd");
		}
	}

}
