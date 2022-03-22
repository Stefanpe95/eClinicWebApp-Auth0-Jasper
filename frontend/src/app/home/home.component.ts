import { HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ClinicUser } from '../models/clinic-user';
import { UserAdd } from '../models/user-add';
import { UserGet } from '../models/user-get';
import { AuthService } from '../services/auth.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  httpOptions: object;

  constructor(private auth: AuthService, private userService: UserService, private router: Router) {
    this.httpOptions = { headers: new HttpHeaders({ "Authorization": "Bearer " + auth.token! }) }
  }

  key = localStorage['clinicUser'];
  user: UserGet = new UserGet();
  email: string = "";
  flagRole!: string | undefined;
  flagDoctor?: number;
  flagPatient?: number;
  clickedDataInfo = false;
  clickedListOfAppointments = false;
  clickedScheduleAppointment = false;
  userExists = false;
  index: number = 1;

  clinicUser: ClinicUser = new ClinicUser();
  addClinicUser: UserAdd = new UserAdd();

  ngOnInit(): void {

    this.userService.login(this.auth.clinicUser!)
      .subscribe(r => {
      });

    this.clinicUser = JSON.parse(localStorage.getItem('clinicUser') || "")!;
    this.checkUserExistence();

  }

  //provera da li korisnik postoji u bazi, ako postoji nece ga upisati ponovo
  checkUserExistence() {
    this.userService.checkUser(this.clinicUser.userid!).subscribe(data => {
      if (data == null) {
        this.userExists = true;
        this.addNoDBUser();
        window.location.reload();
      }
      this.whatIsUser();
    });
  }

  //dodavanje novog korisnika u bazu podataka
  addNoDBUser() {
    this.addClinicUser.userid = this.clinicUser.userid;
    this.addClinicUser.email = this.clinicUser.email;
    this.addClinicUser.name = this.clinicUser.name;
    this.addClinicUser.patientid = this.clinicUser.patient?.patientid;
    this.addClinicUser.roleid = this.clinicUser.role?.roleid;

    this.userService.addUser(this.addClinicUser).subscribe(element => {
    }, error => console.error());
  }

  //na osnovu provere u sustini otkljucati i zakljucati odredjene stranice(administrator ili korisnik)
  whatIsUser() {

    this.userService.getUserById(this.auth?.clinicUser?.userid!).subscribe(data => {
      this.user = data;
      localStorage.setItem('currentRole', data.role?.roleName!);
      if (localStorage['currentRole'] == 'admin') this.flagRole = 'doctor';
      if (localStorage['currentRole'] == 'user') this.flagRole = 'patient';

      if (localStorage['currentRole'] == 'admin') {
        this.flagDoctor = data.doctor?.doctorid;
        localStorage.setItem('currentDoctorPatient', JSON.stringify(this.flagDoctor));
        localStorage['currentDoctorPatient'] = parseInt(localStorage['currentDoctorPatient'])
      }
      else if (localStorage['currentRole'] == 'user') {
        this.flagPatient = data.patient?.patientid;
        localStorage.setItem('currentDoctorPatient', JSON.stringify(this.flagPatient));
        localStorage['currentDoctorPatient'] = parseInt(localStorage['currentDoctorPatient'])
      }

      if (this.userExists == true) {
        window.location.reload();
      }
      else {
      }

    }
    );

  }

  logout() {

    localStorage.removeItem('clinicUser');
    localStorage.removeItem('currentRole');
    localStorage.removeItem('token');
    this.auth.logout();
    this.router.navigate(['/']);
  }

  dataInfo() {
    this.index = 1;
  }

  listofAppointments() {
    this.index = 2;
  }
  scheduleAppointment() {
    this.index = 3;
  }

}
