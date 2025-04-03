package com.demo.employee.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.demo.employee.dateutils.DateUtils;
import com.demo.employee.entity.EmployeeEntity;
import com.demo.employee.repository.EmployeeRepository;
import com.demo.example.controller.dto.Employee;

import io.micrometer.common.util.StringUtils;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	public Employee createEmployee(Employee employee) {

		EmployeeEntity ee = new EmployeeEntity();
		ee.setFirstname(employee.getFirstName());
		ee.setLastname(employee.getLastName());
		ee.setSalary(BigDecimal.valueOf(employee.getSalary()));
		ee.setStartdate(DateUtils.format("yyyy-MM-dd", employee.getStartDate()));
		EmployeeEntity a = employeeRepository.save(ee);

		employee.setId(a.getId());
		return employee;

	}

	public Employee patchEmployee(Long id, Employee employee) {

		Optional<EmployeeEntity> employeeEntityOptional = employeeRepository.findById(id);

		EmployeeEntity employeeEntity = employeeEntityOptional.get();

		if (!StringUtils.isBlank(employee.getFirstName())) {
			employeeEntity.setFirstname(employee.getFirstName());
		}
		EmployeeEntity updatedEntity = employeeRepository.save(employeeEntity);

		Employee e = transformEmployeeEntityToEmployeeDTO(updatedEntity);

		return e;
	}

	private static Employee transformEmployeeEntityToEmployeeDTO(EmployeeEntity updatedEntity) {
		Employee e = new Employee();
		e.setFirstName(updatedEntity.getFirstname());
		e.setLastName(updatedEntity.getLastname());
		e.setSalary(updatedEntity.getSalary().doubleValue());
		return e;
	}

	public Employee putEmployee(Long id, Employee employee) {

		Optional<EmployeeEntity> employeeEntityOptional = employeeRepository.findById(id);

		EmployeeEntity employeeEntity = employeeEntityOptional.get();
		employeeEntity.setFirstname(employee.getFirstName());
		employeeEntity.setLastname(employee.getLastName());
		employeeEntity.setSalary(BigDecimal.valueOf(employee.getSalary()));

		EmployeeEntity updatedEntity = employeeRepository.save(employeeEntity);

		Employee e = transformEmployeeEntityToEmployeeDTO(updatedEntity);

		return e;

	}

	public Employee getEmployee(Long id) {
		Optional<EmployeeEntity> employeeEntityOptional = employeeRepository.findById(id);

		return transformEmployeeEntityToEmployeeDTO(employeeEntityOptional.get());
	}

	public Employee deleteEmployee(Long id) {
		Optional<EmployeeEntity> employeeEntityOptional = employeeRepository.findById(id);
		EmployeeEntity employeeEntity = employeeEntityOptional.get();
		Employee deletedEmployee = transformEmployeeEntityToEmployeeDTO(employeeEntity);

		employeeRepository.deleteById(id);
		return deletedEmployee;
	}

}
