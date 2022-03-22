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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.eClinic.dto.UserAddDto;
import com.example.eClinic.dto.UserDto;
import com.example.eClinic.dto.UserLoginDto;
import com.example.eClinic.model.Doctor;
import com.example.eClinic.model.Patient;
import com.example.eClinic.model.Role;
import com.example.eClinic.model.User;
import com.example.eClinic.repository.DoctorRepository;
import com.example.eClinic.repository.PatientRepository;
import com.example.eClinic.repository.RoleRepository;
import com.example.eClinic.service.IUserService;

@RestController
@RequestMapping("/api/v1")
public class UserController {

	@Autowired
	PatientRepository patientRepository;
	@Autowired
	DoctorRepository doctorRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	private IUserService userService;

	@PostMapping("/addUser")
	public @ResponseBody ResponseEntity<UserDto> addUser(@RequestBody UserDto user) {
		try {
			userService.addUser(user);
			return new ResponseEntity<UserDto>(user, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

	}

	@GetMapping("/getAllUsers")
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> list = userService.getAllUsers();
		return new ResponseEntity<List<User>>(list, HttpStatus.OK);
	}

	@GetMapping("/getUserById/{id}")
	public ResponseEntity<User> getUserbyId(@PathVariable("id") String id) {
		Optional<User> user = userService.getUserById(id);
		User u=user.get();
		System.out.println(u);
		if (user.isEmpty()) {
			u = null;
			return new ResponseEntity<User>(u, HttpStatus.NOT_FOUND);
		} else
			return new ResponseEntity<User>(user.get(), HttpStatus.OK);
	}

	@DeleteMapping("/deleteUser/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable("id") String id) {
		Optional<User> obj = userService.getUserById(id);
		if (obj.isEmpty())
			return new ResponseEntity<User>(obj.get(), HttpStatus.NOT_FOUND);
		else {
			userService.deleteUser(id);
			return new ResponseEntity<User>(obj.get(), HttpStatus.OK);
		}
	}

	@PutMapping("/updateUser/{id}")
	public ResponseEntity<UserDto> updateUser(@PathVariable String id, @RequestBody UserAddDto userDetails) {

		try {
			userService.updateUser(id, userDetails);
			Patient patient = null;
			Doctor doctor = null;
			if (userDetails.getPatientid() != null)
				patient = patientRepository.getById(userDetails.getPatientid());
			if (userDetails.getDoctorid() != null)
				doctor = doctorRepository.getById(userDetails.getDoctorid());
			Role role = roleRepository.getById(userDetails.getRoleid());
			UserDto user = new UserDto(userDetails.getUserid(), userDetails.getEmail(), userDetails.getName(), patient,
					doctor, role);
			return new ResponseEntity<UserDto>(user, HttpStatus.OK);
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

	}

	@GetMapping("/getUsersByRoleName/{roleName}")
	public ResponseEntity<List<User>> getUsersbyRoleName(@PathVariable("roleName") String roleName) {
		List<User> users = userService.getUsersByRoleName(roleName);
		if (users.isEmpty())
			return new ResponseEntity<List<User>>(users, HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	@PostMapping("/checkUser")
	public @ResponseBody ResponseEntity<UserLoginDto> checkUser(@RequestBody String userID) {

		Optional<User> AuthUser = userService.getUserById(userID);
		if (AuthUser.isEmpty())
			return null;
		else {
			UserLoginDto user = new UserLoginDto(AuthUser.get().getEmail(), AuthUser.get().getName());
			return ResponseEntity.status(HttpStatus.OK).body(user);
		}


	}

	@PostMapping("/login")
	private @ResponseBody ResponseEntity<String> loginUser(@RequestBody UserLoginDto user) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(userService.login(user));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

	}

}
