import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Doctor } from '../models/doctor';
import { DoctorAdd } from '../models/doctor-add';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class DoctorService {
  httpOptions: object;

  private baseURL = "http://localhost:8080/api/v1/"

  constructor(private httpClient: HttpClient, private auth: AuthService) {
    this.httpOptions = { headers: new HttpHeaders({ "Authorization": "Bearer " + auth.token! }) }
  }

  getDoctorsList(): Observable<Doctor[]> {
    return this.httpClient.get<Doctor[]>(this.baseURL + "getAllDoctors", this.httpOptions);
  }

  updateDoctor(id: number, user: DoctorAdd): Observable<DoctorAdd> {
    return this.httpClient.put(`${this.baseURL + "updateDoctor"}/${id}`, user, this.httpOptions);
  }

  AddDoctor(doctor: Doctor): Observable<Doctor> {
    return this.httpClient.post(this.baseURL + 'addDoctor', doctor, this.httpOptions);
  }

}
