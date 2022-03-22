export class Role {

    roleid?: number;
    roleName?: string;

    constructor(init?: Partial<Role>) {
        Object.assign(this, init);
    }
}
