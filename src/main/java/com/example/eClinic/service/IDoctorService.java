package com.example.eClinic.service;

import java.util.List;
import java.util.Optional;

import com.example.eClinic.dto.DoctorAddDto;
import com.example.eClinic.dto.DoctorDto;
import com.example.eClinic.model.Doctor;

public interface IDoctorService {

	boolean addDoctor(DoctorDto doctor);

	boolean deleteDoctor(long doctorId);

	List<Doctor> getAllDoctors();

	Optional<Doctor> getDoctorbyId(long doctorId);

	Doctor updateDoctor(long doctorid, DoctorAddDto doctor);

}
