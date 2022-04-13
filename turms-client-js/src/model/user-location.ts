export default class UserLocation {
    public longitude: number;
    public latitude: number;
    public details: Record<string, string>;

    constructor(longitude: number, latitude: number, details: Record<string, string>) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.details = details;
    }

    toString(): string {
        return `${(this.longitude)}:${(this.latitude)}:${JSON.stringify(this.details)}`;
    }
}