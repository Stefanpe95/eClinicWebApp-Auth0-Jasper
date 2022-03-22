package com.example.eClinic.dto;

import java.sql.Timestamp;

import com.example.eClinic.model.Doctor;
import com.example.eClinic.model.Patient;

public class AppointmentGetDto {

	private long appointmentID;
	private Timestamp date;
	private String appointmentNote;
	private Patient patient;
	private Doctor doctor;

	public long getAppointmentid() {
		return appointmentID;
	}

	public void setAppointmentid(long appointmentid) {
		this.appointmentID = appointmentid;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public String getAppointment_note() {
		return appointmentNote;
	}

	public void setAppointment_note(String appointment_note) {
		this.appointmentNote = appointment_note;
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

	public AppointmentGetDto(long appointmentID, Timestamp date, String appointmentNote, Patient patient,
			Doctor doctor) {
		super();
		this.appointmentID = appointmentID;
		this.date = date;
		this.appointmentNote = appointmentNote;
		this.patient = patient;
		this.doctor = doctor;
	}

	@Override
	public String toString() {
		return "AppointmentDto [appointmentid=" + appointmentID + ", date=" + date + ", appointment_note="
				+ appointmentNote + ", patient=" + patient + ", doctor=" + doctor + "]";
	}

}
