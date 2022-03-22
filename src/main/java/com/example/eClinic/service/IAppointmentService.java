package com.example.eClinic.service;

import java.util.List;
import java.util.Optional;

import com.example.eClinic.dto.AppointmentAddDto;
import com.example.eClinic.model.Appointment;
import com.example.eClinic.model.Report;

public interface IAppointmentService {

	Appointment addAppointment(AppointmentAddDto appointment);

	Appointment updateAppointment(long appointmentID, AppointmentAddDto user);

	Appointment deleteAppointment(long appointmentId);

	List<Appointment> getAllAppointments();

	Optional<Appointment> getAppointmentbyId(long appointmentId);

	List<Appointment> getAppointmentsbyDoctorId(Long doctorid);

	List<Appointment> getAppointmentsbyPatientId(Long patientid);

	Report reportAppointment(long appointmentID);
}
