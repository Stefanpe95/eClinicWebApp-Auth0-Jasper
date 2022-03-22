import { Component, OnInit, ViewChild } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AddAppointment } from 'src/app/models/add-appointment';
import { Doctor } from 'src/app/models/doctor';
import { Patient } from 'src/app/models/patient';
import { AppointmentService } from 'src/app/services/appointment.service';
import { DoctorService } from 'src/app/services/doctor.service';
import { PatientService } from 'src/app/services/patient.service';

@Component({
  selector: 'app-update-appointment',
  templateUrl: './update-appointment.component.html',
  styleUrls: ['./update-appointment.component.css']
})
export class UpdateAppointmentComponent implements OnInit {


  @ViewChild('picker') picker: any;

  public date: any;
  public disabled = false;
  public showSpinners = true;
  public showSeconds = false;
  public touchUi = true;
  public enableMeridian = false;
  public minDate: any;
  public maxDate: any;
  public stepHour = 1;
  public stepMinute = 15;
  public stepSecond = 1;

  public formGroup = new FormGroup({
    date: new FormControl(null, [Validators.required]),
    date2: new FormControl(null, [Validators.required])
  })
  public dateControl = new FormControl(new Date(2021, 9, 4, 5, 6, 7));
  public dateControlMinMax = new FormControl(new Date());


  id?: any;
  appointmentadd: AddAppointment = new AddAppointment();

  doctor?: number;
  patient?: number;
  doctors: Doctor[] = [];
  patients: Patient[] = [];

  //za odabir datuma
  myFilter = (d: Date | null): boolean => {
    const day = (d || new Date()).getDay();
    // Prevent Saturday and Sunday from being selected.
    return day !== 0 && day !== 6;
  }

  AppointmentForm = new FormGroup({
    Date: new FormControl(''),
    AppointmentNote: new FormControl(''),
    Patient: new FormControl(''),
    Doctor: new FormControl(''),
  });

  PatientValidation: FormControl = new FormControl();
  DoctorValidation: FormControl = new FormControl();

  constructor(private appointmentService: AppointmentService, private router: Router,
    private doctorService: DoctorService,
    private patientService: PatientService,
    private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['appointmentID'];
    this.getDoctorList();
    this.getPatientsList();
    this.appointmentService.getAppointmentById(this.id).subscribe(data => {
      this.appointmentadd = data;

      this.doctor = data.doctor?.doctorid;
      this.patient = data.patient?.patientid;

    });
  }

  getDoctorList() {
    this.doctorService.getDoctorsList().subscribe(data => {
      this.doctors = data;
    })
  }

  getPatientsList() {
    this.patientService.getPatientsList().subscribe(data => {
      this.patients = data;
    })
  }

  selectOptionDate(event: any) {
    this.appointmentadd.date = event.target.value;
  }


  selectOptionDoctor(event: any) {
    //getted from event
    this.appointmentadd.doctorid = event.target.value;
    //getted from binding

  }


  selectOptionPatient(event: any) {
    //getted from event
    this.appointmentadd.patientid = event.target.value;
    //getted from binding
  }

  saveUser() {
    this.appointmentService.updateAppointment(this.id, this.appointmentadd).subscribe(element => {
      this.gotToAppointmentList();
    }, error => console.error());

  }

  redirectToHome() {
    this.router.navigate(['home/:clinicUser']);
  }

  gotToAppointmentList() {
    this.router.navigate(['appointments-list']);
  }

  onSubmit() {

    this.appointmentadd.doctorid = this.doctor;
    this.appointmentadd.patientid = this.patient;
    this.saveUser();

  }

}
