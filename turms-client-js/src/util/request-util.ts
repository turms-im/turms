export default class RequestUtil {
    static ERROR = new Error('Illegal parameters');

    static getDateTimeStr(value: Date): string | undefined {
        return value ? '' + value.getTime() : undefined;
    }

    public static isFalsy(value: any): boolean {
        return value == null || value?.length === 0;
    }

    public static isTruthy(value: any): boolean {
        return !this.isFalsy(value);
    }

    static throwIfEmpty(value): void {
        if (!Object.keys(value).length) {
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

}