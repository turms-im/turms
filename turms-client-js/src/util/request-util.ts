export default class RequestUtil {
    static ERROR = new Error('Illegal parameters');

    static getDateTimeStr(value: Date): string | undefined {
        return value ? '' + value.getTime() : undefined;
    }

    public static isFalsy(value: any): boolean {
        return typeof value == null || value === [];
    }

    public static isTruthy(value: any): boolean {
        return !this.isFalsy(value);
    }

    static throwIfEmpty(value): void {
        if (Object.keys(value).length === 0) {
            throw RequestUtil.ERROR;
        }
    }

    static throwIfAnyFalsy(...values: any[]): void {
        if (this.isFalsy(values)) {
            throw RequestUtil.ERROR;
        } else {
            for (const value of values) {
                if (this.isFalsy(value)) {
                    throw RequestUtil.ERROR;
                }
            }
        }
    }

    static throwIfAllFalsy(...values: any[]): void {
        if (RequestUtil.areAllFalsy(values)) {
            throw RequestUtil.ERROR;
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
            id = 1 + Math.floor(Math.random() * 9007199254740991);
        } while (Object.prototype.hasOwnProperty.call(requestMap, id));
        return id;
    }

}