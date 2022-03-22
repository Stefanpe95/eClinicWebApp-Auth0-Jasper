import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AddAppointment } from '../models/add-appointment';
import { Appointment } from '../models/appointment';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class AppointmentService {
  httpOptions: object;

  private baseURL = "http://localhost:8080/api/v1/"
  private getAllAppointmentsURL = this.baseURL + "getAllAppointments";
  private addAppointmentURL = this.baseURL + "addAppointment";
  private getAppointmentURL = this.baseURL + "getAppointmentbyId";
  private getAppointmentbyDoctorIdURL = this.baseURL + "getAppointmentsbyDoctorId";
  private getAppointmentbyPatientIdURL = this.baseURL + "getAppointmentsbyPatientId";
  private updateAppointmentURL = this.baseURL + "updateAppointment";
  private deleteAppointmentURL = this.baseURL + "deleteAppointment";
  private reportAppointmentURL = this.baseURL + "reportAppointment";

  constructor(private httpClient: HttpClient, private auth: AuthService) {
    this.httpOptions = { headers: new HttpHeaders({ "Authorization": "Bearer " + auth.token! }) }
  }

  getAppointmentsList(): Observable<Appointment[]> {
    return this.httpClient.get<Appointment[]>(this.getAllAppointmentsURL, this.httpOptions);
  }

  addAppointment(appointment: AddAppointment): Observable<AddAppointment> {
    return this.httpClient.post(this.addAppointmentURL, appointment, this.httpOptions);
  }

  getAppointmentById(id: number): Observable<Appointment> {
    return this.httpClient.get<Appointment>(`${this.getAppointmentURL}/${id}`, this.httpOptions);
  };

  getAppointmentsbyDoctorId(doctorid: number): Observable<Appointment[]> {
    return this.httpClient.get<Appointment[]>(`${this.getAppointmentbyDoctorIdURL}/${doctorid}`, this.httpOptions);
  }

  getAppointmentsbyPatientId(patientid: number): Observable<Appointment[]> {
    return this.httpClient.get<Appointment[]>(`${this.getAppointmentbyPatientIdURL}/${patientid}`, this.httpOptions);
  }

  updateAppointment(id: number, user: AddAppointment): Observable<AddAppointment> {
    return this.httpClient.put(`${this.updateAppointmentURL}/${id}`, user, this.httpOptions);
  }

  deleteAppointment(id: number): Observable<any> {
    return this.httpClient.delete(`${this.deleteAppointmentURL}/${id}`, this.httpOptions);
  }

  reportAppointment(id: number): Observable<any> {
    return this.httpClient.get<any>(`${this.reportAppointmentURL}/${id}`, this.httpOptions);
  };


}
