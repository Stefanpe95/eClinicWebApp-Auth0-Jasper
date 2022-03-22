package com.example.eClinic.model;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class User {

	@Id
	private String userid;
	@Basic
	private String email;
	@Basic
	private String name;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "patientid", referencedColumnName = "patientid")
	private Patient patient;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "doctorid", referencedColumnName = "doctorid")
	private Doctor doctor;

	@ManyToOne
	@JoinColumn(name = "roleid", nullable = false)
	private Role role;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public User() {
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

	public User(String userid, String email, String name, Patient patient, Doctor doctor, Role role) {
		super();
		this.userid = userid;
		this.email = email;
		this.name = name;

		this.patient = patient;
		this.doctor = doctor;
		this.role = role;
	}


}
