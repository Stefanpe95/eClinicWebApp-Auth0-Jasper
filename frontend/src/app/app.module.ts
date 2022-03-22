import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatFormFieldControl, MatFormFieldModule } from '@angular/material/form-field';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { CommonModule } from '@angular/common';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatNativeDateModule } from '@angular/material/core';
import { NgxMatDatetimePickerModule, NgxMatNativeDateModule, NgxMatTimepickerModule } from '@angular-material-components/datetime-picker';
import { AddAppointmentComponent } from './shared/add-appointment/add-appointment.component';
import { MatInputModule } from '@angular/material/input';
import { HomeComponent } from './home/home.component';
import { NgxPaginationModule } from 'ngx-pagination';
import { AddUserComponent } from './shared/add-user/add-user.component';
import { AppointmentDetailsComponent } from './shared/appointment-details/appointment-details.component';
import { AppointmentListComponent } from './shared/appointment-list/appointment-list.component';
import { DoctorListComponent } from './shared/doctor-list/doctor-list.component';
import { PatientListComponent } from './shared/patient-list/patient-list.component';
import { RoleListComponent } from './shared/role-list/role-list.component';
import { UpdateAppointmentComponent } from './shared/update-appointment/update-appointment.component';
import { UpdateUserComponent } from './shared/update-user/update-user.component';
import { UserDetailsComponent } from './shared/user-details/user-details.component';
import { UserListComponent } from './shared/user-list/user-list.component';
import { CallbackComponent } from './callback/callback.component';
import { UpdateUserListComponent } from './shared/update-user-list/update-user-list.component';
import { UserService } from './services/user.service';



@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    UserListComponent,
    DoctorListComponent,
    PatientListComponent,
    RoleListComponent,
    AddUserComponent,
    UpdateUserComponent,
    UserDetailsComponent,
    DoctorListComponent,
    AppointmentListComponent,
    AddAppointmentComponent,
    UpdateAppointmentComponent,
    AppointmentDetailsComponent,
    CallbackComponent,
    UpdateUserListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    CommonModule,
    NgxPaginationModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    MatFormFieldModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatFormFieldModule,
    MatInputModule,
    NgxMatDatetimePickerModule,
    NgxMatTimepickerModule,
    NgxMatNativeDateModule
  ],
  providers: [UserService],
  exports: [NgxPaginationModule],
  bootstrap: [AppComponent]
})
export class AppModule { }
