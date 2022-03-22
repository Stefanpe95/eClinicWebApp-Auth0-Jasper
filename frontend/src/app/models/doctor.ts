import { Department } from "./department";

export class Doctor {

	doctorid?: number;
	speciality?: string;
	doctorPID?: number;

	department?: Department;

	constructor(init?: Partial<Doctor>) {
		Object.assign(this, init);
	}
}
