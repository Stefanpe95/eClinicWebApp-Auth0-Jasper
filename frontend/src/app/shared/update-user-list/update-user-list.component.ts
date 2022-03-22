import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';
import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ClinicUser } from 'src/app/models/clinic-user';
import { Department } from 'src/app/models/department';
import { Doctor } from 'src/app/models/doctor';
import { DoctorAdd } from 'src/app/models/doctor-add';
import { Patient } from 'src/app/models/patient';
import { Role } from 'src/app/models/role';
import { UserAdd } from 'src/app/models/user-add';
import { DepartmentService } from 'src/app/services/department.service';
import { DoctorService } from 'src/app/services/doctor.service';
import { PatientService } from 'src/app/services/patient.service';
import { RoleService } from 'src/app/services/role.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-update-user-list',
  templateUrl: './update-user-list.component.html',
  styleUrls: ['./update-user-list.component.css']
})
export class UpdateUserListComponent implements OnInit {

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
  patientadd: Patient = new Patient();

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
    this.userService.getUserById(this.id).subscribe(data => {

      this.role = data.role?.roleid;

      if (data.doctor != null) {
        this.department = data.doctor.department?.departmentID;
        this.doctor = data.doctor?.doctorid;
        this.doctorObj = data.doctor!;
      }

      if (data.patient != null) {
        this.patient = data.patient?.patientid;
        this.age != data.patient?.age;
        this.bloodtype = data.patient?.bloodtype?.toString();
        this.gender = data.patient?.gender?.toString();
        this.patientObj = data.patient!;
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

    //ako upisem samo pacijentov PID (ako se izgube ostali podaci sa stranice a upisani su u bazu)
    if (patient.patientid == undefined && this.patientObj.patientPID != undefined) {

      this.patientService.getPatientByPID(this.patientObj.patientPID!).subscribe(x => {
        this.addedPatient = x;
        console.log(this.addedPatient)
        this.patientObj = new Patient();

        //dodela patient id za update user-a
        this.useradd.patientid = this.addedPatient.patientid
        this.userService.updateUser(this.id, this.useradd).subscribe(data => {

        });

      });


    }
    else {

      this.patientService.AddPatient(patient).subscribe(x => {
        if (x == true) {
          this.patientService.getPatientByPID(this.patientObj.patientPID!).subscribe(x => {
            this.addedPatient = x;
            console.log(this.addedPatient)
            this.patientObj = new Patient();

            //dodela patient id za update user-a
            this.useradd.patientid = this.addedPatient.patientid
            this.userService.updateUser(this.id, this.useradd).subscribe(data => {
            });

          });
        }
        else {

          //samo update postojeceg pacijenta
          this.patientadd.patientid = this.patient;
          this.patientadd.age = this.patientObj.age;
          let index = this.bloodtypes.findIndex(x => x === this.bloodtype);
          this.patientadd.bloodtype = index;
          let index2 = this.genders.findIndex(x => x === this.gender);
          this.patientadd.gender = index2;
          this.patientadd.patientPID = this.patientObj.patientPID;

          this.patientService.updatePatient(this.patientObj.patientid!, this.patientadd).subscribe(x => {
          });
        }


      });
    }

  }



  onSubmit() {

    if (this.role == 2) {
      this.AddPatient(this.patientObj);
    }
    if (this.role == 1) {



      console.log(this.doctoradd)

      if(this.doctor == undefined){
        this.doctoradd.doctorPID = this.doctorObj.doctorPID;
        this.doctoradd.speciality = this.doctorObj.speciality;
        this.doctoradd.departmentid = this.department
        this.doctorService.AddDoctor(this.doctorObj).subscribe(x=>{
          console.log(x)
        })
      }else{
        this.doctoradd.doctorid = this.doctor;
        this.doctoradd.doctorPID = this.doctorObj.doctorPID;
        this.doctoradd.speciality = this.doctorObj.speciality;
        this.doctoradd.departmentid = this.department
      }
      this.doctorService.updateDoctor(this.doctor!, this.doctoradd).subscribe(x => {
      })
    }

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
