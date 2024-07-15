import { Value } from './proto/model/common/value';

export default class UserLocation {
    public longitude: number;
    public latitude: number;
    public details: Record<string, string>;
    public customAttributes: Value[];

    constructor(longitude: number, latitude: number, details: Record<string, string>, customAttributes: Value[]) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.details = details;
        this.customAttributes = customAttributes;
    }

    toString(): string {
        return `${(this.longitude)}:${(this.latitude)}:${JSON.stringify(this.details)}:${JSON.stringify(this.customAttributes)}`;
    }
}