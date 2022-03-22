package com.example.eClinic.service;

import java.util.List;
import java.util.Optional;

import com.example.eClinic.dto.PatientAddDto;
import com.example.eClinic.dto.PatientDto;
import com.example.eClinic.model.Patient;

public interface IPatientService {

	boolean addPatient(PatientDto patient);

	boolean deletePatient(long patientId);

	List<Patient> getAllPatients();

	Optional<Patient> getPatientbyId(long patientId);

	Optional<Patient> findPatientBypatientPIDEquals(long PID);

	Patient updatePatient(long patientid, PatientAddDto patient);
}
