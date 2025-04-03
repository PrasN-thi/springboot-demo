package com.demo.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.employee.entity.EmployeeEntity;
import com.demo.example.controller.dto.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {



}
