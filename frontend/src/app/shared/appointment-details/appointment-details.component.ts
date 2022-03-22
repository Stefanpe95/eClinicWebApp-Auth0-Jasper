import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Appointment } from 'src/app/models/appointment';
import { AppointmentService } from 'src/app/services/appointment.service';

@Component({
  selector: 'app-appointment-details',
  templateUrl: './appointment-details.component.html',
  styleUrls: ['./appointment-details.component.css']
})
export class AppointmentDetailsComponent implements OnInit {

  appointmentid!: number;
  appointment?: Appointment;

  constructor(private route: ActivatedRoute, private appointmentService: AppointmentService) { }

  ngOnInit(): void {

    this.appointmentid = this.route.snapshot.params['appointmentID'];

    this.appointment = new Appointment();
    this.appointmentService.getAppointmentById(this.appointmentid).subscribe(data => {
      this.appointment = data;
    })
  }

}
