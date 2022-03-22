package com.example.eClinic.dto;

import com.example.eClinic.model.BloodType;
import com.example.eClinic.model.Gender;

public class PatientAddDto {
	
	private long patientid;
	private int age;
	private Gender gender;
	private BloodType bloodtype;
	private long patientPID;
	public long getPatientid() {
		return patientid;
	}
	public void setPatientid(long patientid) {
		this.patientid = patientid;
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
	public PatientAddDto(long patientid, int age, Gender gender, BloodType bloodtype, long patientPID) {
		super();
		this.patientid = patientid;
		this.age = age;
		this.gender = gender;
		this.bloodtype = bloodtype;
		this.patientPID = patientPID;
	}
	@Override
	public String toString() {
		return "PatientAddDto [patientid=" + patientid + ", age=" + age + ", gender=" + gender + ", bloodtype="
				+ bloodtype + ", patientPID=" + patientPID + "]";
	}
	
	

}
