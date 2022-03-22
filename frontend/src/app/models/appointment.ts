import { Doctor } from "./doctor";
import { Patient } from "./patient";

export class Appointment {

    appointmentID?: number;
    date?: Date;
    appointment_note?: string;
    patient?: Patient;
    doctor?: Doctor;

    constructor(init?: Partial<Appointment>) {
        Object.assign(this, init);
    }
}
