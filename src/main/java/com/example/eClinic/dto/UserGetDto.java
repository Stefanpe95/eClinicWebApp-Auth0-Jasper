package com.example.eClinic.dto;

import com.example.eClinic.model.Doctor;
import com.example.eClinic.model.Patient;
import com.example.eClinic.model.Role;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class UserGetDto {
	private long userid;
	private String username;
	private String password;
	private String name;
	private String surname;
	private String pid;

	private Doctor doctor;
	private Patient patient;

	@JsonInclude(Include.NON_NULL)
	private Role role;

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	@Override
	public String toString() {
		return "UserGetDto [userid=" + userid + ", username=" + username + ", password=" + password + ", name=" + name
				+ ", surname=" + surname + ", pid=" + pid + ", doctor=" + doctor + ", patient=" + patient + ", role="
				+ role + "]";
	}

}
