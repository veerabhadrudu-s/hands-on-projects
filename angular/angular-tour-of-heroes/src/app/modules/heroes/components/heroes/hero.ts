
/*
 * Author: sveera
 */
export class Hero {
    private _id: number;
    private _name: String;

    constructor(id: number, name: String) {
        this._id = id;
        this._name = name;
    }

    public get id(): number {
        return this._id;
    }

    public set id(value: number) {
        this._id = value;
    }

    public get name(): String {
        return this._name;
    }

    public set name(value: String) {
        this._name = value;
    }

}
