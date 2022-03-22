import { Component, OnInit } from '@angular/core';
import { Patient } from 'src/app/models/patient';
import { PatientService } from 'src/app/services/patient.service';

@Component({
  selector: 'app-patient-list',
  templateUrl: './patient-list.component.html',
  styleUrls: ['./patient-list.component.css']
})
export class PatientListComponent implements OnInit {

  patients: Patient[] = [];

  constructor(private patientSevice: PatientService) { }

  ngOnInit(): void {
    this.getPatients();
  }

  getPatients() {
    this.patientSevice.getPatientsList().subscribe(data => {
      this.patients = data;
    })
  }

}
