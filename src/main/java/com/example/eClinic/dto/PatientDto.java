package com.example.eClinic.dto;

import com.example.eClinic.model.BloodType;
import com.example.eClinic.model.Gender;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class PatientDto {

	private long patientid;
	private int age;
	private Gender gender;
	private BloodType bloodtype;
	private long patientPID;

	public BloodType getBloodtype() {
		return bloodtype;
	}

	public void setBloodtype(BloodType bloodtype) {
		this.bloodtype = bloodtype;
	}

	public long getPatientPID() {
		return patientPID;
	}

	public PatientDto(long patientid, int age, Gender gender, BloodType bloodtype, long patientPID) {
		super();
		this.patientid = patientid;
		this.age = age;
		this.gender = gender;
		this.bloodtype = bloodtype;
		this.patientPID = patientPID;
	}

	public void setPatientPID(long patientPID) {
		this.patientPID = patientPID;
	}

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

	@Override
	public String toString() {
		return "PatientDto [patientid=" + patientid + ", age=" + age + ", gender=" + gender + ", bloodtype=" + bloodtype
				+ ", patientPID=" + patientPID + "]";
	}

}
