import { Component, OnInit } from '@angular/core';
import { Doctor } from 'src/app/models/doctor';
import { DoctorService } from 'src/app/services/doctor.service';

@Component({
  selector: 'app-doctor-list',
  templateUrl: './doctor-list.component.html',
  styleUrls: ['./doctor-list.component.css']
})
export class DoctorListComponent implements OnInit {

  doctors: Doctor[] = [];

  constructor(private doctorSevice: DoctorService) { }

  ngOnInit(): void {
    this.getDoctors();
  }

  getDoctors() {
    this.doctorSevice.getDoctorsList().subscribe(data => {
      this.doctors = data;
    })
  }

}
