package com.example.eClinic.service;

import java.util.List;
import java.util.Optional;

import com.example.eClinic.dto.RoleDto;
import com.example.eClinic.model.Role;

public interface IRoleService {

	boolean addRole(RoleDto role);

	boolean deleteRole(long roleId);

	List<Role> getAllRoles();

	Optional<Role> getRolebyId(long roleId);
}
