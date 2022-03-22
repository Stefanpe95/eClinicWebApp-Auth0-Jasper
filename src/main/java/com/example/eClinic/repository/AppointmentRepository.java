package com.example.eClinic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.eClinic.model.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

	@Query(value = "SELECT * FROM appointment INNER JOIN user ON user.doctorid=appointment.doctorid WHERE appointment.doctorid = :doctorid", nativeQuery = true)
	List<Appointment> findAppointmentsbyDoctorId(@Param("doctorid") Long doctorid);

	@Query(value = "SELECT * FROM appointment INNER JOIN user ON user.patientid=appointment.patientid WHERE appointment.patientid = :patientid", nativeQuery = true)
	List<Appointment> findAppointmentsbyPatientId(@Param("patientid") Long patientid);

}
