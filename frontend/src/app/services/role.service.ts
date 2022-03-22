import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Role } from '../models/role';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class RoleService {
  httpOptions: object;
  private getAllRolesURL = "http://localhost:8080/api/v1/getAllRoles";

  constructor(private httpClient: HttpClient, private auth: AuthService) {
    this.httpOptions = { headers: new HttpHeaders({ "Authorization": "Bearer " + auth.token! }) }
  }

  getRolesList(): Observable<Role[]> {
    return this.httpClient.get<Role[]>(this.getAllRolesURL, this.httpOptions);
  }
}
