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

import com.example.eClinic.dto.RoleDto;
import com.example.eClinic.model.Role;
import com.example.eClinic.service.IRoleService;

@RestController
@RequestMapping("/api/v1")
public class RoleController {

	@Autowired
	private IRoleService roleService;

	@PostMapping("/addRole")
	private @ResponseBody ResponseEntity<Boolean> addRole(@RequestBody RoleDto role) {
		try {
			System.out.println(role.toString());
			return ResponseEntity.status(HttpStatus.OK).body(roleService.addRole(role));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@GetMapping("/getAllRoles")
	public ResponseEntity<List<Role>> getAllRoles() {
		List<Role> list = roleService.getAllRoles();
		if (list.isEmpty())
			return new ResponseEntity<List<Role>>(list, HttpStatus.NOT_FOUND);
		return new ResponseEntity<List<Role>>(list, HttpStatus.OK);
	}

	@GetMapping("/getRolebyId/{id}")
	public ResponseEntity<Role> getRolebyId(@PathVariable("id") Integer id) {
		Optional<Role> role = roleService.getRolebyId(id);
		if (role.isEmpty())
			return new ResponseEntity<Role>(role.get(), HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<Role>(role.get(), HttpStatus.OK);
	}

	@DeleteMapping("/deleteRole/{id}")
	public ResponseEntity<Role> deleteDepartment(@PathVariable("id") Integer id) {
		Optional<Role> obj = roleService.getRolebyId(id);
		if (obj.isEmpty())
			return new ResponseEntity<Role>(obj.get(), HttpStatus.NOT_FOUND);
		else {
			roleService.deleteRole(id);
			return new ResponseEntity<Role>(obj.get(), HttpStatus.OK);
		}
	}
}
