package com.example.eClinic.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.eClinic.dto.RoleDto;
import com.example.eClinic.model.Role;
import com.example.eClinic.repository.RoleRepository;
import com.example.eClinic.service.IRoleService;

@Service
public class RoleServiceImpl implements IRoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public boolean addRole(RoleDto role) {
		Optional<Role> roleObj = roleRepository.findById(role.getRoleID());
		Role r1 = new Role(role.getRoleName());

		if (roleObj.isEmpty()) {
			roleRepository.save(r1);
			return true;
		} else
			return false;
	}

	@Override
	public boolean deleteRole(long roleId) {
		Optional<Role> obj = roleRepository.findById(roleId);
		if (obj.isEmpty())
			return false;
		else {
			roleRepository.delete(obj.get());
			return true;
		}
	}

	@Override
	public List<Role> getAllRoles() {
		List<Role> list = roleRepository.findAll();
		return list;
	}

	@Override
	public Optional<Role> getRolebyId(long roleId) {
		Optional<Role> obj = roleRepository.findById(roleId);
		return obj;
	}
}
