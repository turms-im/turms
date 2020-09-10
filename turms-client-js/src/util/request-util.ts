export default class RequestUtil {
    static ERROR = new Error("Illegal parameters");

    static wrapValueIfNotNull(value: any): any | undefined {
        return value ? {value} : undefined;
    }

    static wrapTimeIfNotNull(value: Date): any | undefined {
        return value ? {value: '' + value.getTime()} : undefined;
    }

    public static isFalsy(value: any): boolean {
        return typeof value === 'undefined' || value === null || value === [];
    }

    public static isTruthy(value: any): boolean {
        return !this.isFalsy(value);
    }

    static throwIfEmpty(value): void {
        if (Object.keys(value).length === 0) {
            throw this.ERROR;
        }
    }

    static throwIfAnyFalsy(...values: any[]): void {
        if (this.isFalsy(values)) {
            throw this.ERROR;
        } else {
            for (const value of values) {
                if (this.isFalsy(value)) {
                    throw this.ERROR;
                }
            }
        }
    }

    static throwIfAllFalsy(...values: any[]): void {
        if (RequestUtil.areAllFalsy(values)) {
            throw this.ERROR;
        }
    }

    static areAllFalsy(...values: any[]): boolean {
        if (this.isFalsy(values)) {
            return true;
        } else {
            for (const value of values) {
                if (this.isTruthy(value)) {
                    return false;
                }
            }
            return true;
        }
    }

    static generateRandomId(requestMap: Record<number, any>): number {
        let id;
        do {
            id = Math.floor(Math.random() * 16384);
        } while (Object.prototype.hasOwnProperty.call(requestMap, id));
        return id;
    }

}