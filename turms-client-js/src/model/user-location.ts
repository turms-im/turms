export default class UserLocation {
    public longitude: number;
    public latitude: number;

    constructor(longitude: number, latitude: number) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    toString(): string {
        return `${(this.longitude)}:${(this.latitude)}`;
    }
}