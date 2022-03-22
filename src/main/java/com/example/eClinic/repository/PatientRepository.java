package com.example.eClinic.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.eClinic.model.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {
	Optional<Patient> findPatientBypatientPIDEquals(long patientPID);
}
