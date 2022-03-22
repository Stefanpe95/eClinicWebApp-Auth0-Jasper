package com.example.eClinic.service;

import java.util.List;
import java.util.Optional;

import com.example.eClinic.dto.DepartmentDto;
import com.example.eClinic.model.Department;

public interface IDepartmentService {

	boolean addDepartment(DepartmentDto department);

	boolean deleteDepartment(long departmentId);

	List<Department> getAllDepartments();

	Optional<Department> getDepartmentbyId(long departmentId);

}
