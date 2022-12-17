export default class Validator {

    public static isFalsy(value: any): boolean {
        return value == null || value?.length === 0;
    }

    public static isTruthy(value: any): boolean {
        return !this.isFalsy(value);
    }

    static throwIfEmpty(value): void {
        if (!Object.keys(value).length) {
            throw new Error('Illegal parameters');
        }
    }

    static throwIfAnyFalsy(...values: any[]): void {
        if (this.isFalsy(values)) {
            throw new Error('Illegal parameters');
        } else {
            for (const value of values) {
                if (this.isFalsy(value)) {
                    throw new Error('Illegal parameters');
                }
            }
        }
    }

    static throwIfAllFalsy(...values: any[]): void {
        if (Validator.areAllFalsy(values)) {
            throw new Error('Illegal parameters');
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

    static areAllNullOrNonNull(...values: any[]): boolean {
        const isFirstValueNull = values[0] == null;
        for (const value of values) {
            if ((value == null) != isFirstValueNull) {
                return false;
            }
        }
        return true;
    }

}