import { HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Appointment } from 'src/app/models/appointment';
import { AppointmentService } from 'src/app/services/appointment.service';
import { UserService } from 'src/app/services/user.service';


@Component({
  selector: 'app-appointment-list',
  templateUrl: './appointment-list.component.html',
  styleUrls: ['./appointment-list.component.css']
})
export class AppointmentListComponent implements OnInit {

  appointments: Appointment[] = [];
  keyRole = localStorage['currentRole'];

  page = 1;
  count = 0;
  pageSize = 5;
  pageSizes = [5, 10, 15];

  constructor(private appointmentService: AppointmentService, userService: UserService, private router: Router) { }

  ngOnInit(): void {
    if (localStorage['currentRole'] == 'admin') { this.getAppointmentsByDoctorId(); }
    else if (localStorage['currentRole'] == 'user') { this.getAppointmentsByPatientId(); }
  }



  getAppointments() {
    this.appointmentService.getAppointmentsList().subscribe(data => {
      this.appointments = data;
      data.forEach(element => {
        element.doctor?.doctorid
      });
    });
  }


  getAppointmentsByDoctorId() {
    this.appointmentService.getAppointmentsbyDoctorId(localStorage['currentDoctorPatient']).subscribe(data => {
      this.appointments = data;
    });
  }

  getAppointmentsByPatientId() {
    this.appointmentService.getAppointmentsbyPatientId(localStorage['currentDoctorPatient']).subscribe(data => {
      this.appointments = data;
    });
  }

  appointmentDetails(appointmentID?: number) {
    this.router.navigate(['appointment-details', appointmentID]);
  }


  updateAppointment(appointmentID?: number) {
    this.router.navigate(['update-appointment', appointmentID]);
  }


  //ZA IZVESTAJ
  openPdf() {
    const pdfUrl = 'assets/report/appointment.pdf';
    window.open(pdfUrl, '_blank');
  }

  reportAppointment(appointmentID?: number) {

    this.appointmentService.reportAppointment(appointmentID!).subscribe(x => {
      this.openPdf();
    })

  }

  deleteAppointment(appointmentID?: any) {
    if (window.confirm(this.keyRole + ",are you sure you want DELETE?")) {
      this.appointmentService.deleteAppointment(appointmentID).subscribe(data => {
        if (this.appointments == null || this.appointments == []) {
          this.router.navigate(['appointments-list']);
        }
        if (localStorage['currentRole'] == 'admin') { this.getAppointmentsByDoctorId(); }
        else if (localStorage['currentRole'] == 'user') { this.getAppointmentsByPatientId(); }
      })
    }

  }

  handlePageChange(event: number): void {
    this.page = event;
    if (localStorage['currentRole'] == 'admin') { this.getAppointmentsByDoctorId(); }
    else if (localStorage['currentRole'] == 'user') { this.getAppointmentsByPatientId(); }
  }

  handlePageSizeChange(event: any): void {
    this.pageSize = event.target.value;
    this.page = 1;
    if (localStorage['currentRole'] == 'admin') { this.getAppointmentsByDoctorId(); }
    else if (localStorage['currentRole'] == 'user') { this.getAppointmentsByPatientId(); }
  }

}
