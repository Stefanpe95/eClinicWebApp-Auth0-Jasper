import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Doctor } from 'src/app/models/doctor';
import { Patient } from 'src/app/models/patient';
import { Role } from 'src/app/models/role';
import { ClinicUser } from 'src/app/models/clinic-user';
import { UserAdd } from 'src/app/models/user-add';
import { DoctorService } from 'src/app/services/doctor.service';
import { PatientService } from 'src/app/services/patient.service';
import { RoleService } from 'src/app/services/role.service';
import { UserService } from 'src/app/services/user.service';
import { DepartmentService } from 'src/app/services/department.service';
import { Department } from 'src/app/models/department';
import { DoctorAdd } from 'src/app/models/doctor-add';

@Component({
  selector: 'app-update-user',
  templateUrl: './update-user.component.html',
  styleUrls: ['./update-user.component.css']
})
export class UpdateUserComponent implements OnInit {

  id?: any;
  useradd: UserAdd = new UserAdd();
  clinicUser: ClinicUser = new ClinicUser();

  role?: number;
  doctor?: number;
  doctorObj: Doctor = new Doctor();
  patient?: number;
  patientObj: Patient = new Patient();
  addedPatient: Patient = new Patient();
  doctors: Doctor[] = [];
  patients: Patient[] = [];
  roles: Role[] = [];
  age: number | undefined;
  bloodtypes: string[] = [];
  bloodtype: string | undefined;
  gender: string | undefined;
  genders: string[] = [];
  departments: Department[] = [];
  department?: number;
  doctoradd: DoctorAdd = new DoctorAdd();

  //patient details

  keyRole = localStorage['currentRole'];

  RoleValidation: FormControl = new FormControl();
  DoctorValidation: FormControl = new FormControl();
  PatientValidation: FormControl = new FormControl();
  BloodTypeValidation: FormControl = new FormControl();
  GenderValidation: FormControl = new FormControl();
  DepartmentValidation: FormControl = new FormControl();

  constructor(private userService: UserService,
    private doctorService: DoctorService,
    private patientService: PatientService,
    private rolesService: RoleService,
    private departmentService: DepartmentService,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit(): void {

    this.clinicUser = JSON.parse(localStorage.getItem('clinicUser') || "")!;

    this.id = this.route.snapshot.params['userid'];

    this.getRolesList();
    this.getDoctorList();
    this.getPatientsList();
    this.getDepartmentList();
    this.getBloodTypeList();
    this.getGenderList();

    this.RoleValidation.disable();
    this.DoctorValidation.disable();
    this.PatientValidation.disable();

    this.getUserbyIdFunction();

    if (localStorage['currentRole'] == 'admin') {
      this.RoleValidation.disable();
    }

    if (localStorage['currentRole'] == 'user') {
      this.RoleValidation.disable();
      this.PatientValidation.disable();
    }


  }

  getUserbyIdFunction() {
    this.userService.getUserById(this.clinicUser.userid!).subscribe(data => {

      this.role = data.role?.roleid;

      if (data.doctor != null) {
        this.department = data.doctor.department?.departmentID;
        this.doctor = data.doctor?.doctorid;
        this.doctorObj = data.doctor!;
      }

      if (data.patient != null) {
        this.patient = data.patient?.patientid;
        this.patientObj = data.patient!;
        this.age != data.patient?.age;
        this.bloodtype = data.patient?.bloodtype?.toString();
        this.gender = data.patient?.gender?.toString();
      }

      //adding data
      this.useradd.email = data.email
      this.useradd.name = data.name
      this.useradd.roleid = data.role?.roleid

    });

  }

  selectOptionRole(event: any) {
    //getted from event
    console.log(event.target.value);
    this.useradd.roleid = event.target.value;
    //getted from binding
  }

  selectOptionDoctor(event: any) {
    //getted from event
    console.log(event.target.value);
    this.useradd.doctorid = event.target.value;
  }

  selectOptionPatient(event: any) {
    //getted from event
    console.log(event.target.value);
    this.useradd.patientid = event.target.value;
  }

  selectOptionBloodType(event: any) {
    //getted from event
    console.log(event.target.value);
    this.patientObj.bloodtype = event.target.value;
  }

  selectOptionGender(event: any) {
    //getted from event
    console.log(event.target.value);
    this.patientObj.gender = event.target.value;
  }

  selectOptionDepartment(event: any) {
    console.log(event.target.value);
    this.doctoradd.departmentid = event.target.value;
  }

  getRolesList() {
    this.rolesService.getRolesList().subscribe(data => {
      this.roles = data;

    })
  }

  getDoctorList() {
    this.doctorService.getDoctorsList().subscribe(data => {
      this.doctors = data;
    })
  }

  getPatientsList() {
    this.patientService.getPatientsList().subscribe(data => {
      this.patients = data;
    })
  }

  getBloodTypeList() {
    this.patientService.getBloodTypeList().subscribe(data => {
      this.bloodtypes = data;

    })
  }

  getGenderList() {
    this.patientService.getGenderList().subscribe(data => {
      this.genders = data;
    })
  }

  getDepartmentList() {
    this.departmentService.getDepartmentsList().subscribe(data => {
      this.departments = data;
    })
  }

  AddPatient(patient: Patient) {
    this.patientService.AddPatient(patient).subscribe(x => {

      this.patientService.getPatientByPID(this.patientObj.patientPID!).subscribe(x => {
        this.addedPatient = x;
        this.patientObj = new Patient();

        this.useradd.patientid = this.addedPatient.patientid
        this.userService.updateUser(this.clinicUser.userid!, this.useradd).subscribe(data => {
          this.reloadHomePage();

        });


      });

    });
  }



  onSubmit() {

    if (this.role == 2) {
      this.AddPatient(this.patientObj);
    }
    if (this.role == 1) {

      this.doctoradd.doctorid = this.doctor;
      this.doctoradd.doctorPID = this.doctorObj.doctorPID;
      this.doctoradd.speciality = this.doctorObj.speciality;
      this.doctoradd.departmentid = this.department;
      this.doctorService.updateDoctor(this.doctor!, this.doctoradd).subscribe(x => {
      })

    }
    this.goToUserList();
  }

  updateUser() {
    this.userService.updateUser(this.clinicUser.userid!, this.useradd).subscribe(data => {
      this.getUserbyIdFunction();
    });
  }

  reloadHomePage() {
    window.location.reload();
  }

  goToUserList() {
    this.router.navigate(['users-list']);
  }

}
