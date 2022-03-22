import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddAppointmentComponent } from './shared/add-appointment/add-appointment.component';
import { HomeComponent } from './home/home.component';
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
import { AuthGuard } from './auth.guard';
import { UpdateUserListComponent } from './shared/update-user-list/update-user-list.component';


const routes: Routes = [
  { path: '', component: HomeComponent, canActivate: [AuthGuard], pathMatch: 'full' },
  { path: 'callback', component: CallbackComponent },
  { path: "users-list", component: UserListComponent, pathMatch: 'full' },
  { path: "add-user", component: AddUserComponent, canActivate: [AuthGuard], pathMatch: 'full' },
  { path: "add-appointment", component: AddAppointmentComponent, canActivate: [AuthGuard], pathMatch: 'full' },
  { path: "doctors-list", component: DoctorListComponent, canActivate: [AuthGuard], pathMatch: 'full' },
  { path: "appointments-list", component: AppointmentListComponent, canActivate: [AuthGuard], pathMatch: 'full' },
  { path: "patients-list", component: PatientListComponent, canActivate: [AuthGuard], pathMatch: 'full' },
  { path: "roles-list", component: RoleListComponent, canActivate: [AuthGuard], pathMatch: 'full' },
  { path: "update-user/:userid", component: UpdateUserComponent, canActivate: [AuthGuard], pathMatch: 'full' },
  { path: "update-user-list/:userid", component: UpdateUserListComponent, canActivate: [AuthGuard], pathMatch: 'full' },
  { path: "update-appointment/:appointmentID", component: UpdateAppointmentComponent, canActivate: [AuthGuard], pathMatch: 'full' },
  { path: "appointment-details/:appointmentID", component: AppointmentDetailsComponent, canActivate: [AuthGuard], pathMatch: 'full' },
  { path: "user-details/:userid", component: UserDetailsComponent, canActivate: [AuthGuard], pathMatch: 'full' },
  { path: '**', redirectTo: '' }

];

@NgModule({
  imports: [RouterModule.forRoot(routes, { onSameUrlNavigation: 'reload' })],
  exports: [RouterModule],
})
export class AppRoutingModule { }
