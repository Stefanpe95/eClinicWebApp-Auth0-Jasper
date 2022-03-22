package com.example.eClinic.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.eClinic.dto.PatientAddDto;
import com.example.eClinic.dto.PatientDto;
import com.example.eClinic.model.BloodType;
import com.example.eClinic.model.Gender;
import com.example.eClinic.model.Patient;
import com.example.eClinic.service.IPatientService;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class PatientController {

	@Autowired
	private IPatientService patientService;

	@PostMapping("/addPatient")
	private @ResponseBody ResponseEntity<Boolean> addPatient(@RequestBody PatientDto patient) {

		try {
			System.out.println(patient.toString());
			return ResponseEntity.status(HttpStatus.CREATED).body(patientService.addPatient(patient));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

	}

	@GetMapping("/getAllPatients")
	public ResponseEntity<List<Patient>> getAllPatients() {
		List<Patient> list = patientService.getAllPatients();
		if (list.isEmpty())
			return new ResponseEntity<List<Patient>>(list, HttpStatus.NOT_FOUND);
		return new ResponseEntity<List<Patient>>(list, HttpStatus.OK);
	}

	@GetMapping("/getPatientbyId/{id}")
	public ResponseEntity<Patient> getDoctorbyId(@PathVariable("id") Integer id) {
		Optional<Patient> patient = patientService.getPatientbyId(id);
		if (patient.isEmpty())
			return new ResponseEntity<Patient>(patient.get(), HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<Patient>(patient.get(), HttpStatus.OK);
	}

	@DeleteMapping("/deletePatient/{id}")
	public ResponseEntity<Patient> deletePatient(@PathVariable("id") Integer id) {
		Optional<Patient> obj = patientService.getPatientbyId(id);
		if (obj.isEmpty())
			return new ResponseEntity<Patient>(obj.get(), HttpStatus.NOT_FOUND);
		else {
			patientService.deletePatient(id);
			return new ResponseEntity<Patient>(obj.get(), HttpStatus.OK);
		}
	}

	@GetMapping("/getAllBloodTypes")
	public ResponseEntity<BloodType[]> getAllBloodTypes() {
		BloodType[] list = BloodType.values();
		if (list.length == 0)
			return new ResponseEntity<BloodType[]>(list, HttpStatus.NOT_FOUND);
		return new ResponseEntity<BloodType[]>(list, HttpStatus.OK);
	}

	@GetMapping("/getAllGender")
	public ResponseEntity<Gender[]> getAllGender() {
		Gender[] list = Gender.values();
		if (list.length == 0)
			return new ResponseEntity<Gender[]>(list, HttpStatus.NOT_FOUND);
		return new ResponseEntity<Gender[]>(list, HttpStatus.OK);
	}

	@GetMapping("/getPatientbyPID/{patientPID}")
	public ResponseEntity<Patient> getPatientbyPID(@PathVariable("patientPID") long patientPID) {
		Optional<Patient> patient = patientService.findPatientBypatientPIDEquals(patientPID);
		System.out.println(patient.toString());
		if (patient.isEmpty())
			return new ResponseEntity<Patient>(patient.get(), HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<Patient>(patient.get(), HttpStatus.OK);
	}

	@PutMapping("/updatePatient/{id}")
	public ResponseEntity<Patient> updatePatient(@PathVariable Long id, @RequestBody PatientAddDto patientDetails) {

		try {
			return ResponseEntity.status(HttpStatus.OK).body(patientService.updatePatient(id, patientDetails));
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

	}

}
