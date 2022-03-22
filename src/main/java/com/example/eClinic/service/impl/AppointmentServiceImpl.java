package com.example.eClinic.service.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.mail.internet.InternetAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.eClinic.dto.AppointmentAddDto;
import com.example.eClinic.model.Appointment;
import com.example.eClinic.model.Doctor;
import com.example.eClinic.model.Patient;
import com.example.eClinic.model.Report;
import com.example.eClinic.model.User;
import com.example.eClinic.repository.AppointmentRepository;
import com.example.eClinic.repository.DoctorRepository;
import com.example.eClinic.repository.PatientRepository;
import com.example.eClinic.repository.UserRepository;
import com.example.eClinic.service.IAppointmentService;

import it.ozimov.springboot.mail.model.Email;
import it.ozimov.springboot.mail.model.EmailAttachment;
import it.ozimov.springboot.mail.model.defaultimpl.DefaultEmail;
import it.ozimov.springboot.mail.model.defaultimpl.DefaultEmailAttachment;
import it.ozimov.springboot.mail.service.EmailService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class AppointmentServiceImpl implements IAppointmentService {

	@Autowired
	private AppointmentRepository appointmentRepository;
	@Autowired
	private DoctorRepository doctorRepository;
	@Autowired
	private PatientRepository patientRepository;
	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional
	public Appointment addAppointment(AppointmentAddDto appointment) {
		System.out.println(appointment.toString());
		Optional<Appointment> obj = appointmentRepository.findById(appointment.getAppointmentID());
		Optional<Doctor> usersDoctor = doctorRepository.findById(appointment.getDoctorid());
		Optional<Patient> usersPatient = patientRepository.findById(appointment.getPatientid());

		Appointment a1 = new Appointment(appointment.getDate(), appointment.getAppointment_note());

		if (usersDoctor.isPresent()) {
			a1.setDoctor(usersDoctor.get());
		}
		if (usersPatient.isPresent()) {
			a1.setPatient(usersPatient.get());
		}

		if (obj.isEmpty()) {
			usersPatient.get().getAppointments().add(a1);
			patientRepository.save(usersPatient.get());
			appointmentRepository.save(a1);
			return a1;
		}

		return a1;

	}

	@Override
	@Transactional
	public Appointment deleteAppointment(long appointmentId) {
		Optional<Appointment> obj = appointmentRepository.findById(appointmentId);
		obj.get().setDoctor(null);
		obj.get().setPatient(null);
		appointmentRepository.delete(obj.get());
		return obj.get();
	}

	@Override
	public List<Appointment> getAllAppointments() {
		List<Appointment> list = appointmentRepository.findAll();
		return list;
	}

	@Override
	public Optional<Appointment> getAppointmentbyId(long appointmentId) {
		Optional<Appointment> obj = appointmentRepository.findById(appointmentId);
		return obj;
	}

	@Override
	@Transactional
	public Appointment updateAppointment(long appointmentID, AppointmentAddDto appointment) {
		Optional<Appointment> a1 = appointmentRepository.findById(appointmentID);
		if (a1.isEmpty())
			return null;

		Appointment appointment1 = a1.get();

		appointment1.setAppointment_note(appointment.getAppointment_note());
		appointment1.setDate(appointment.getDate());

		if (appointment.getDoctorid() != null) {

			Optional<Doctor> d1 = doctorRepository.findById(appointment.getDoctorid());
			if (d1.isPresent()) {
				appointment1.setDoctor(d1.get());
			}

		}

		if (appointment.getPatientid() != null) {

			Optional<Patient> p1 = patientRepository.findById(appointment.getPatientid());
			if (p1.isPresent()) {
				appointment1.setPatient(p1.get());
			}
		}
		appointmentRepository.saveAndFlush(appointment1);
		return appointment1;
	}

	@Override
	public List<Appointment> getAppointmentsbyDoctorId(Long doctorid) {
		List<Appointment> appointments = appointmentRepository.findAppointmentsbyDoctorId(doctorid);
		return appointments;
	}

	@Override
	public List<Appointment> getAppointmentsbyPatientId(Long patientid) {
		List<Appointment> appointments = appointmentRepository.findAppointmentsbyPatientId(patientid);
		return appointments;
	}

	@Override
	public Report reportAppointment(long appointmentID) {

		Map<String, Object> map = new HashMap<String, Object>();
		List<Report> reportDataList = new ArrayList<Report>();
		map.put("appointmentid", appointmentID);
		Optional<Appointment> a = appointmentRepository.findById(appointmentID);
		Optional<User> user_doctor = userRepository.findUserBydoctorEquals(a.get().getDoctor());
		Optional<User> user_patient = userRepository.findUserBypatientEquals(a.get().getPatient());

		Report r = new Report(a.get().getDate(), a.get().getAppointment_note(), user_doctor.get().getName(),
				user_doctor.get().getEmail(), user_patient.get().getName(), user_patient.get().getEmail(),
				a.get().getDoctor().getSpeciality(), a.get().getDoctor().getDoctorPID(),
				a.get().getDoctor().getDepartment().getName(), user_patient.get().getPatient().getAge(),
				user_patient.get().getPatient().getGender(), user_patient.get().getPatient().getBloodtype(),
				user_patient.get().getPatient().getPatientPID());
		reportDataList.add(r);

		try {
			JasperPrint jasperPrint = JasperFillManager.fillReport(
					"C:\\Users\\stefa\\OneDrive\\Desktop\\eClinic\\reports\\eClinic_JavaBean_Report.jasper", null,
					new JRBeanCollectionDataSource(reportDataList));
			JasperExportManager.exportReportToPdfFile(jasperPrint, "reports\\appointment.pdf");
			JasperExportManager.exportReportToPdfFile(jasperPrint,
					"C:\\Users\\stefa\\OneDrive\\Desktop\\eClinic\\frontend\\src\\assets\\report\\appointment.pdf");
			new Thread(() -> sendMailAttachmentPDF(user_patient.get().getEmail(), user_patient.get().getName()))
					.start();

		} catch (JRException e) {

			e.printStackTrace();
		}

		if (a.isPresent()) {
			return r;
		} else
			return null;

	}

	@Autowired
	public EmailService emailService;

	public void sendMailAttachmentPDF(String receiverMail, String receiverName) {
		Email email;
		try {
			email = DefaultEmail.builder().from(new InternetAddress("applicationeclinic@gmail.com", "eClinicAdmin"))
					.to(Collections.singletonList(new InternetAddress(receiverMail, receiverMail))).subject("eClinic")
					.body("Thank you for using our medical service!\nAppointment schedule is attached.\n")
					.attachment(getPDFAttachment("Patient_report_" + receiverMail)).encoding("UTF-8").build();
			emailService.send(email);

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

	}

	private EmailAttachment getPDFAttachment(String filename) {
		Path path = Paths.get("reports\\appointment.pdf");
		DefaultEmailAttachment attachment;
		try {
			attachment = DefaultEmailAttachment.builder().attachmentName(filename + ".pdf")
					.attachmentData(Files.readAllBytes(path)).mediaType(MediaType.APPLICATION_PDF).build();
			return attachment;
		} catch (IOException e) {

			e.printStackTrace();
			return null;
		}

	}

}
