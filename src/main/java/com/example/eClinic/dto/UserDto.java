package com.example.eClinic.dto;

import com.example.eClinic.model.Doctor;
import com.example.eClinic.model.Patient;
import com.example.eClinic.model.Role;

public class UserDto {
	private String userid;
	private String email;
	private String name;

	private Patient patient;
	private Doctor doctor;
	private Role role;

	public Role getRole() {
		return role;
	}

	public UserDto(String userid, String email, String name, Patient patient, Doctor doctor, Role role) {
		this.userid = userid;
		this.email = email;
		this.name = name;
		this.patient = patient;
		this.doctor = doctor;
		this.role = role;
	}

	public UserDto() {

	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UserDto(String userid, String email, String name) {
		super();
		this.userid = userid;
		this.email = email;
		this.name = name;
	}

	public UserDto(String userid, String email, String name, Patient patient, Doctor doctor) {
		super();
		this.userid = userid;
		this.email = email;
		this.name = name;
		this.patient = patient;
		this.doctor = doctor;

	}

	@Override
	public String toString() {
		return "UserDto [userid=" + userid + ", email=" + email + ", name=" + name + ", patient=" + patient
				+ ", doctor=" + doctor + ", role=" + role + "]";
	}

}
