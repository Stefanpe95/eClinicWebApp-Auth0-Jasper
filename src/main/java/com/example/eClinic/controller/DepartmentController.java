package com.example.eClinic.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.eClinic.dto.DepartmentDto;
import com.example.eClinic.model.Department;
import com.example.eClinic.service.IDepartmentService;

@RestController
@RequestMapping("/api/v1")
public class DepartmentController {

	@Autowired
	private IDepartmentService departmentService;
	

	  @PostMapping("/addDepartment") 
	  private @ResponseBody ResponseEntity<Boolean> addDepartment(@RequestBody DepartmentDto department) 
	  	  { 
		  try {
			  System.out.println(department.toString());
			  return ResponseEntity.status(HttpStatus.CREATED).body(departmentService.addDepartment(department)); 
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		  }
	  

		@GetMapping("/getAllDepartments")
		public ResponseEntity<List<Department>> getAllDepartments() {
			List<Department> list = departmentService.getAllDepartments();
			if(list.isEmpty()) return new ResponseEntity<List<Department>>(list, HttpStatus.NOT_FOUND);
			return new ResponseEntity<List<Department>>(list, HttpStatus.OK);
		}

		@GetMapping("/getDepartmentbyId/{id}")
		public ResponseEntity<Department> getDepartmentbyId(@PathVariable("id") Integer id) {
			Optional<Department> department = departmentService.getDepartmentbyId(id);
			if(department.isEmpty()) return new ResponseEntity<Department>(department.get(), HttpStatus.NOT_FOUND);
			else return new ResponseEntity<Department>(department.get(), HttpStatus.OK);
		}
	  	

		@DeleteMapping("/deleteDepartment/{id}")
		public ResponseEntity<Department> deleteDepartment(@PathVariable("id") Integer id) {
	  		Optional<Department> obj=departmentService.getDepartmentbyId(id);
	  		if(obj.isEmpty()) return new ResponseEntity<Department>(obj.get(),HttpStatus.NOT_FOUND);
	  		else {departmentService.deleteDepartment(id);
			return new ResponseEntity<Department>(obj.get(),HttpStatus.OK);}
		}
}
