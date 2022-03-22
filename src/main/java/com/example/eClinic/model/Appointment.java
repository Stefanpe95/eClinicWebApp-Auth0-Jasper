package com.example.eClinic.model;

import java.sql.Timestamp;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Appointment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long appointmentID;

	@Column(unique = true, nullable = false)
	private Timestamp date;

	@Basic
	@Column(length = 5000)
	private String appointmentNote;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "doctorid", referencedColumnName = "doctorid", nullable = false)
	private Doctor doctor;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "patientid", referencedColumnName = "patientid", nullable = false)
	private Patient patient;

	public Appointment(Timestamp date, String appointmentNote) {
		this.date = date;
		this.appointmentNote = appointmentNote;
	}

	public long getAppointmentID() {
		return appointmentID;
	}

	public void setAppointmentID(long appointmentID) {
		this.appointmentID = appointmentID;
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

	public Appointment() {
	}

	@Override
	public String toString() {
		return "Appointment [appointmentNote=" + appointmentNote + "]";
	}

}
