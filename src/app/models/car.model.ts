export class Car {
    public get contactNumber(): string {
        return this._contactNumber;
    }
    public set contactNumber(value: string) {
        this._contactNumber = value;
    }
    constructor(
        private _vin: string,
        private _maker: string,
        private _model: string,
        private _modelYear: number,
        private _price: number,
        private _showroom: string,
        private _contactNumber: string
    ) {}

    // Getters


    get vin(): string {
        return this._vin;
    }

    get maker(): string {
        return this._maker;
    }

    get model(): string {
        return this._model;
    }

    get modelYear(): number {
        return this._modelYear;
    }

    get price(): number {
        return this._price;
    }

    get showroom(): string {
        return this._showroom;
    }

    // Setters


    set vin(value: string) {
        this._vin = value;
    }

    set maker(value: string) {
        this._maker = value;
    }

    set model(value: string) {
        this._model = value;
    }

    set modelYear(value: number) {
        this._modelYear = value;
    }

    set price(value: number) {
        this._price = value;
    }

    set showroom(value: string) {
        this._showroom = value;
    }
}
