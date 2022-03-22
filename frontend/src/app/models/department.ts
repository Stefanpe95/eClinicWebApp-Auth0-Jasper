export class Department {

    departmentID?: number;
    name?: string;

    constructor(init?: Partial<Department>) {
        Object.assign(this, init);
    }
}
