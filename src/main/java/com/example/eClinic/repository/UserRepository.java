package com.example.eClinic.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.eClinic.model.Doctor;
import com.example.eClinic.model.Patient;
import com.example.eClinic.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

	Optional<User> findUserByemailEquals(String email);

	Optional<User> findUserBypatientEquals(Patient patient);

	Optional<User> findUserBydoctorEquals(Doctor doctor);

	@Query(value = "SELECT * FROM user INNER JOIN role ON user.roleid=role.roleid WHERE role_name LIKE %:rolename%", nativeQuery = true)
	List<User> findUsersByRole(@Param("rolename") String roleName);

}
