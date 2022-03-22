import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ClinicUser } from '../models/clinic-user';
import { UserAdd } from '../models/user-add';
import { UserGet } from '../models/user-get';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  httpOptions: object;
  userAuth: ClinicUser = new ClinicUser();
  private baseURL = "http://localhost:8080/api/v1/"
  private getAllUsersURL = this.baseURL + "getAllUsers";
  private addUserURL = this.baseURL + "addUser";
  private getUserURL = this.baseURL + "getUserById";
  private updateUserURL = this.baseURL + "updateUser";
  private deleteUserURL = this.baseURL + "deleteUser";
  private loginUserURL = this.baseURL + "login";
  private getUserByRoleNameURL = this.baseURL + "getUsersByRoleName";
  private checkUserURL = this.baseURL + "checkUser";

  constructor(private httpClient: HttpClient, private auth: AuthService) {
    this.httpOptions = { headers: new HttpHeaders({ "Authorization": "Bearer " + auth.token! }) }
    this.userAuth = auth.clinicUser!;
  }


  getUsersList(): Observable<UserGet[]> {
    return this.httpClient.get<UserGet[]>(this.getAllUsersURL, this.httpOptions);
  }

  addUser(user: UserAdd): Observable<UserAdd> {
    return this.httpClient.post(this.addUserURL, user, this.httpOptions);
  }

  getUserById(id: string): Observable<UserGet> {
    return this.httpClient.get<UserGet>(`${this.getUserURL}/${id}`, this.httpOptions);
  };

  updateUser(id: string, user: UserAdd): Observable<UserAdd> {
    return this.httpClient.put(`${this.updateUserURL}/${id}`, user, this.httpOptions);
  }

  deleteUser(id: string): Observable<any> {
    return this.httpClient.delete(`${this.deleteUserURL}/${id}`, this.httpOptions);
  }

  getUsersByRoleName(rolename: any): Observable<UserGet[]> {
    return this.httpClient.get<UserGet[]>(`${this.getUserByRoleNameURL}/${rolename}`, this.httpOptions);
  }

  login(user: ClinicUser):Observable<any> {
    return this.httpClient.post(this.baseURL + "login", user, {
      responseType:'text',
      ...this.httpOptions
    }  );
  }

  checkUser(id: string): Observable<ClinicUser> {
    return this.httpClient.post(this.baseURL + "checkUser", id, this.httpOptions);
  }

}
