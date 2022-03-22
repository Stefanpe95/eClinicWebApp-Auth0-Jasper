package com.example.eClinic.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class DoctorAddDto {
	private long doctorid;
	private String speciality;
	private long doctorPID;
	private long departmentid;

	public long getDoctorid() {
		return doctorid;
	}

	public void setDoctorid(long doctorid) {
		this.doctorid = doctorid;
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

	public long getDepartmentid() {
		return departmentid;
	}

	public void setDepartmentid(long departmentid) {
		this.departmentid = departmentid;
	}

	public DoctorAddDto(long doctorid, String speciality, long doctorPID, long departmentid) {
		super();
		this.doctorid = doctorid;
		this.speciality = speciality;
		this.doctorPID = doctorPID;
		this.departmentid = departmentid;
	}

	@Override
	public String toString() {
		return "DoctorAddDto [doctorid=" + doctorid + ", speciality=" + speciality + ", doctorPID=" + doctorPID
				+ ", departmentid=" + departmentid + "]";
	}

}
