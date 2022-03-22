import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Department } from '../models/department';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class DepartmentService {
  httpOptions: object;
  private getAllDepartmentsURL = "http://localhost:8080/api/v1/getAllDepartments";

  constructor(private httpClient: HttpClient, private auth: AuthService) {
    this.httpOptions = { headers: new HttpHeaders({ "Authorization": "Bearer " + auth.token! }) }
  }

  getDepartmentsList(): Observable<Department[]> {
    return this.httpClient.get<Department[]>(this.getAllDepartmentsURL, this.httpOptions);
  }
}
