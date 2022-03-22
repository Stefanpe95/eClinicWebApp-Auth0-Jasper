
export class Patient {
    patientid?: number;
    age?: number;
    gender?: number;
    patientPID?: number;
    bloodtype?: number;

    constructor(init?: Partial<Patient>) {
        Object.assign(this, init);
    }
}
