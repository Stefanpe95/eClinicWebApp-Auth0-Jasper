package com.example.eClinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.eClinic.model.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

}
