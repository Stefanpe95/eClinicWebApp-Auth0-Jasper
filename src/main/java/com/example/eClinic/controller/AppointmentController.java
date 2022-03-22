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

import com.example.eClinic.dto.AppointmentAddDto;
import com.example.eClinic.model.Appointment;
import com.example.eClinic.model.Report;
import com.example.eClinic.service.IAppointmentService;

@RestController
@RequestMapping("/api/v1")
public class AppointmentController {

	@Autowired
	private IAppointmentService appointmentService;

	@PostMapping("/addAppointment")
	private @ResponseBody ResponseEntity<Appointment> addAppointment(@RequestBody AppointmentAddDto appointment) {
		try {
			System.out.println(appointment.toString());
			return ResponseEntity.status(HttpStatus.CREATED).body(appointmentService.addAppointment(appointment));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

	}

	@GetMapping("/getAllAppointments")
	public ResponseEntity<List<Appointment>> getAllDoctors() {
		List<Appointment> list = appointmentService.getAllAppointments();
		if (list.isEmpty())
			return new ResponseEntity<List<Appointment>>(list, HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<List<Appointment>>(list, HttpStatus.OK);
	}

	@GetMapping("/getAppointmentbyId/{id}")
	public ResponseEntity<Appointment> getAppointmentbyId(@PathVariable("id") Integer id) {
		Optional<Appointment> appointment = appointmentService.getAppointmentbyId(id);
		if (appointment.isEmpty())
			return new ResponseEntity<Appointment>(appointment.get(), HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<Appointment>(appointment.get(), HttpStatus.OK);
	}

	@DeleteMapping("/deleteAppointment/{id}")
	public ResponseEntity<Appointment> deleteDoctor(@PathVariable("id") Integer id) {
		Optional<Appointment> obj = appointmentService.getAppointmentbyId(id);
		if (obj.isEmpty())
			return new ResponseEntity<Appointment>(obj.get(), HttpStatus.NOT_FOUND);
		else {
			appointmentService.deleteAppointment(id);
			return new ResponseEntity<Appointment>(obj.get(), HttpStatus.OK);
		}
	}

	@PutMapping("/updateAppointment/{id}")
	public ResponseEntity<Appointment> updateAppointment(@PathVariable Long id,
			@RequestBody AppointmentAddDto appointmentDetails) {

		try {
			return ResponseEntity.status(HttpStatus.OK)
					.body(appointmentService.updateAppointment(id, appointmentDetails));
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

	}

	@GetMapping("/getAppointmentsbyDoctorId/{id}")
	public ResponseEntity<List<Appointment>> getAppointmentsbyDoctorId(@PathVariable("id") Long id) {
		List<Appointment> appointments = appointmentService.getAppointmentsbyDoctorId(id);

		return new ResponseEntity<List<Appointment>>(appointments, HttpStatus.OK);
	}

	@GetMapping("/getAppointmentsbyPatientId/{id}")
	public ResponseEntity<List<Appointment>> getAppointmentsbyPatientId(@PathVariable("id") Long id) {
		List<Appointment> appointments = appointmentService.getAppointmentsbyPatientId(id);
		return new ResponseEntity<List<Appointment>>(appointments, HttpStatus.OK);
	}

	@GetMapping("/reportAppointment/{appointmentID}")
	private @ResponseBody ResponseEntity<Report> reportAppointment(@PathVariable("appointmentID") Long appointmentID) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(appointmentService.reportAppointment(appointmentID));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

	}

}
