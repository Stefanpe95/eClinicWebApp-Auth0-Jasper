package com.example.eClinic.dto;

import java.sql.Timestamp;

public class AppointmentAddDto {
	
	private long appointmentID;
	private Timestamp date;
	private String appointment_note;
	private Long patientid;
	private Long doctorid;
	

	public long getAppointmentID() {
		return appointmentID;
	}

	public void setAppointmentID(long appointmentID) {
		this.appointmentID = appointmentID;
	}


	public String getAppointment_note() {
		return appointment_note;
	}

	public void setAppointment_note(String appointment_note) {
		this.appointment_note = appointment_note;
	}

	public Long getPatientid() {
		return patientid;
	}


	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public void setPatientid(Long patientid) {
		this.patientid = patientid;
	}

	public Long getDoctorid() {
		return doctorid;
	}

	public void setDoctorid(Long doctorid) {
		this.doctorid = doctorid;
	}


	@Override
	public String toString() {
		return "AppointmentAddDto [appointmentid=" + appointmentID + ", date=" + date + ", appointment_note="
				+ appointment_note + ", patientid=" + patientid + ", doctorid=" + doctorid + "]";
	}

}
