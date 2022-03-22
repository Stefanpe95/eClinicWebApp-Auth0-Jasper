import { Doctor } from "./doctor";
import { Patient } from "./patient";
import { Role } from "./role";

export class ClinicUser {
    userid?:string;
    email?:string ;
    name?: string;

    //POTREBNO DA SE IZLISTAJU(PREGLED)
    role?:Role;
    doctor?:Doctor;
    patient?:Patient;

    //POTREBNO ZA DODAVANJE(SAMO ID!)->POTREBNO DA SE PRAVI PADAJUCI MENI!!!***
   // roleid?:number;
	//doctorid?:number;
	//patientid?:number;


}
