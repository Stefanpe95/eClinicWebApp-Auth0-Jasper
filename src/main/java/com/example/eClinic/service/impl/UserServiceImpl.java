package com.example.eClinic.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.eClinic.dto.UserAddDto;
import com.example.eClinic.dto.UserDto;
import com.example.eClinic.dto.UserLoginDto;
import com.example.eClinic.model.Doctor;
import com.example.eClinic.model.Patient;
import com.example.eClinic.model.Role;
import com.example.eClinic.model.User;
import com.example.eClinic.repository.PatientRepository;
import com.example.eClinic.repository.RoleRepository;
import com.example.eClinic.repository.UserRepository;
import com.example.eClinic.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private PatientRepository patientRepository;

	@Override
	@Transactional
	public User addUser(UserDto user) {

		Role usersRole = roleRepository.findById(2L).get();
		Doctor doctor = user.getDoctor();
		Patient patient = user.getPatient();
		User u1 = new User(user.getUserid(), user.getEmail(), user.getName(), patient, doctor, usersRole);

		userRepository.saveAndFlush(u1);

		return u1;
	}

	@Override
	public List<User> getAllUsers() {
		List<User> list = userRepository.findAll();
		return list;
	}

	@Override
	public Optional<User> getUserById(String userID) {
		Optional<User> obj = userRepository.findById(userID);
		return obj;
	}

	@Override
	@Transactional
	public User updateUser(String UserID, UserAddDto user) {
		Optional<User> u1 = userRepository.findById(UserID);
		if (u1.isEmpty())
			return null;
		Optional<Role> r1 = roleRepository.findById(user.getRoleid());
		Optional<Patient> patient = patientRepository.findById(user.getPatientid());

		User user1 = u1.get();
		user1.setEmail(user.getEmail());
		user1.setName(user.getName());
		user1.setRole(r1.get());
		user1.setPatient(patient.get());

		userRepository.saveAndFlush(user1);
		return user1;

	}

	@Override
	@Transactional
	public User deleteUser(String userID) {
		Optional<User> obj = userRepository.findById(userID);
		obj.get().setDoctor(null);
		obj.get().setPatient(null);
		userRepository.delete(obj.get());
		return obj.get();

	}

	@Override
	public String login(UserLoginDto user) {
		Optional<User> u = userRepository.findUserByemailEquals(user.getEmail());
		System.out.println(u.get().toString());
		if (u.get() != null)
			return u.get().getUserid();
		else
			return null;
	}

	@Override
	public List<User> getUsersByRoleName(String roleName) {
		List<User> users = userRepository.findUsersByRole(roleName);
		return users;
	}

}
