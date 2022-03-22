import { Doctor } from "./doctor";
import { Patient } from "./patient";
import { Role } from "./role";

export class UserGet {
    userid?: string;
    email?: string;
    name?: string;

    role?: Role;
    doctor?: Doctor;
    patient?: Patient;
}
