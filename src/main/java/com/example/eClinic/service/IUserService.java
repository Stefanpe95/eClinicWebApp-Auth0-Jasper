package com.example.eClinic.service;

import java.util.List;
import java.util.Optional;

import com.example.eClinic.dto.UserAddDto;
import com.example.eClinic.dto.UserDto;
import com.example.eClinic.dto.UserLoginDto;
import com.example.eClinic.model.User;

public interface IUserService {

	User addUser(UserDto user);

	User updateUser(String UserID, UserAddDto user);

	List<User> getAllUsers();

	Optional<User> getUserById(String userID);

	User deleteUser(String userID);

	String login(UserLoginDto user);

	List<User> getUsersByRoleName(String roleName);

}
