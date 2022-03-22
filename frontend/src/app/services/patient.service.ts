import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Patient } from '../models/patient';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})


export class PatientService {


  httpOptions: object;
  private baseURL = 'http://localhost:8080/api/v1/';
  private getPatientPIDURL = this.baseURL + "getPatientbyPID";

  constructor(private httpClient: HttpClient, private auth: AuthService) {
    this.httpOptions = { headers: new HttpHeaders({ "Authorization": "Bearer " + auth.token! }) }
  }

  getPatientsList(): Observable<Patient[]> {
    return this.httpClient.get<Patient[]>(this.baseURL + 'getAllPatients', this.httpOptions);
  }

  getPatientByPID(patientPID: number): Observable<Patient> {
    return this.httpClient.get<Patient>(`${this.getPatientPIDURL}/${patientPID}`, this.httpOptions);
  };

  AddPatient(patient: Patient): Observable<Patient> {
    return this.httpClient.post(this.baseURL + 'addPatient', patient, this.httpOptions);
  }

  getBloodTypeList(): Observable<string[]> {
    return this.httpClient.get<string[]>(this.baseURL + 'getAllBloodTypes', this.httpOptions);
  }

  getGenderList(): Observable<string[]> {
    return this.httpClient.get<string[]>(this.baseURL + 'getAllGender', this.httpOptions);
  }

  updatePatient(id: number, user: Patient): Observable<Patient> {
    return this.httpClient.put(`${this.baseURL + "updatePatient"}/${id}`, user, this.httpOptions)
  }

}
