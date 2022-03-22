package com.example.eClinic.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.eClinic.dto.DoctorAddDto;
import com.example.eClinic.dto.DoctorDto;
import com.example.eClinic.model.Doctor;
import com.example.eClinic.service.IDoctorService;

@RestController
@RequestMapping("/api/v1")
public class DoctorController {

	@Autowired
	private IDoctorService doctorService;

	@PostMapping("/addDoctor")
	private @ResponseBody ResponseEntity<Boolean> addDoctor(@RequestBody DoctorDto doctor) {
		try {
			System.out.println(doctor.toString());
			return ResponseEntity.status(HttpStatus.CREATED).body(doctorService.addDoctor(doctor));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

	}

	@GetMapping("/getAllDoctors")
	public ResponseEntity<List<Doctor>> getAllDoctors() {
		List<Doctor> list = doctorService.getAllDoctors();
		if (list.isEmpty())
			return new ResponseEntity<List<Doctor>>(list, HttpStatus.NOT_FOUND);
		return new ResponseEntity<List<Doctor>>(list, HttpStatus.OK);
	}

	@GetMapping("/getDoctorbyId/{id}")
	public ResponseEntity<Doctor> getDoctorbyId(@PathVariable("id") Integer id) {
		Optional<Doctor> doctor = doctorService.getDoctorbyId(id);
		if (doctor.isEmpty())
			return new ResponseEntity<Doctor>(doctor.get(), HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<Doctor>(doctor.get(), HttpStatus.OK);
	}

	@DeleteMapping("/deleteDoctor/{id}")
	public ResponseEntity<Doctor> deleteDoctor(@PathVariable("id") Integer id) {
		Optional<Doctor> obj = doctorService.getDoctorbyId(id);
		if (obj.isEmpty())
			return new ResponseEntity<Doctor>(obj.get(), HttpStatus.NOT_FOUND);
		else {
			doctorService.deleteDoctor(id);
			return new ResponseEntity<Doctor>(obj.get(), HttpStatus.OK);
		}
	}

	@PutMapping("/updateDoctor/{id}")
	public ResponseEntity<Doctor> updateDoctor(@PathVariable Long id, @RequestBody DoctorAddDto doctorDetails) {

		try {
			return ResponseEntity.status(HttpStatus.OK).body(doctorService.updateDoctor(id, doctorDetails));
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

	}
}
