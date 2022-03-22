package com.example.eClinic.model;

import java.sql.Timestamp;

public class Report {

	private Timestamp date;
	private String appointment_note;
	private String doctor_name;
	private String doctor_email;
	private String patient_name;
	private String patient_email;
	private String speciality;
	private long doctorPID;
	private String department_name;
	private int age;
	private Gender gender;
	private BloodType bloodtype;
	private long patientPID;

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public String getDoctor_name() {
		return doctor_name;
	}

	public void setDoctor_name(String doctor_name) {
		this.doctor_name = doctor_name;
	}

	public String getDoctor_email() {
		return doctor_email;
	}

	public void setDoctor_email(String doctor_email) {
		this.doctor_email = doctor_email;
	}

	public String getPatient_name() {
		return patient_name;
	}

	public void setPatient_name(String patient_name) {
		this.patient_name = patient_name;
	}

	public String getPatient_email() {
		return patient_email;
	}

	public void setPatient_email(String patient_email) {
		this.patient_email = patient_email;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public long getDoctorPID() {
		return doctorPID;
	}

	public void setDoctorPID(long doctorPID) {
		this.doctorPID = doctorPID;
	}

	public String getDepartment_name() {
		return department_name;
	}

	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public BloodType getBloodtype() {
		return bloodtype;
	}

	public void setBloodtype(BloodType bloodtype) {
		this.bloodtype = bloodtype;
	}

	public long getPatientPID() {
		return patientPID;
	}

	public void setPatientPID(long patientPID) {
		this.patientPID = patientPID;
	}

	public String getAppointment_note() {
		return appointment_note;
	}

	public void setAppointment_note(String appointment_note) {
		this.appointment_note = appointment_note;
	}

	public Report(Timestamp date, String appointment_note, String doctor_name, String doctor_email, String patient_name,
			String patient_email, String speciality, long doctorPID, String department_name, int age, Gender gender,
			BloodType bloodtype, long patientPID) {
		super();
		this.date = date;
		this.appointment_note = appointment_note;
		this.doctor_name = doctor_name;
		this.doctor_email = doctor_email;
		this.patient_name = patient_name;
		this.patient_email = patient_email;
		this.speciality = speciality;
		this.doctorPID = doctorPID;
		this.department_name = department_name;
		this.age = age;
		this.gender = gender;
		this.bloodtype = bloodtype;
		this.patientPID = patientPID;
	}

	@Override
	public String toString() {
		return "Report [date=" + date + ", appointment_note=" + appointment_note + ", doctor_name=" + doctor_name
				+ ", doctor_email=" + doctor_email + ", patient_name=" + patient_name + ", patient_email="
				+ patient_email + ", speciality=" + speciality + ", doctorPID=" + doctorPID + ", department_name="
				+ department_name + ", age=" + age + ", gender=" + gender + ", bloodtype=" + bloodtype + ", patientPID="
				+ patientPID + "]";
	}

}
