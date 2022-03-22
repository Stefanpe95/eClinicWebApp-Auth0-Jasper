package com.example.eClinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.eClinic.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
