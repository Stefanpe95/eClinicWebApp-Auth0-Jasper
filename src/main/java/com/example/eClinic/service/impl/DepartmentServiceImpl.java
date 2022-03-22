package com.example.eClinic.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.eClinic.dto.DepartmentDto;
import com.example.eClinic.model.Department;
import com.example.eClinic.repository.DepartmentRepository;
import com.example.eClinic.service.IDepartmentService;

@Service
public class DepartmentServiceImpl implements IDepartmentService {

	@Autowired
	private DepartmentRepository departmentRepository;

	@Override
	@Transactional
	public synchronized boolean addDepartment(DepartmentDto department) {
		Optional<Department> departmentObj = departmentRepository.findById(department.getDepartmentID());
		Department d1 = new Department(department.getName());

		if (departmentObj.isEmpty()) {
			departmentRepository.save(d1);
			return true;
		} else
			return false;

	}

	@Override
	public List<Department> getAllDepartments() {
		List<Department> list = departmentRepository.findAll();
		return list;
	}

	@Override
	public Optional<Department> getDepartmentbyId(long departmentId) {
		Optional<Department> obj = departmentRepository.findById(departmentId);
		return obj;
	}

	@Override
	@Transactional
	public boolean deleteDepartment(long departmentId) {
		Optional<Department> obj = departmentRepository.findById(departmentId);
		if (obj.isEmpty())
			return false;
		else {
			departmentRepository.delete(obj.get());
			return true;
		}

	}

}
