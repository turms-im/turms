export default class Validator {

    public static isNullOrEmpty(value: any): boolean {
        return value == null || (typeof value !== 'string' && value?.length === 0);
    }

    public static isNotNullOrEmpty(value: any): boolean {
        return !this.isNullOrEmpty(value);
    }

    static throwIfAnyNull(...values: any[]): void {
        if (null == values) {
            throw new Error('Illegal parameters');
        }
        for (const value of values) {
            if (null == value) {
                throw new Error('Illegal parameters');
            }
        }
    }

    static throwIfAllNullOrEmpty(...values: any[]): void {
        if (Validator.areAllNullOrEmpty(values)) {
            throw new Error('Illegal parameters');
        }
    }

    static areAllNull(...values: any[]): boolean {
        if (null == values) {
            return true;
        }
        for (const value of values) {
            if (null != value) {
                return false;
            }
        }
        return true;
    }

    static areAllNullOrEmpty(...values: any[]): boolean {
        if (this.isNullOrEmpty(values)) {
            return true;
        }
        for (const value of values) {
            if (this.isNotNullOrEmpty(value)) {
                return false;
            }
        }
        return true;
    }

    static areAllNullOrNotNull(...values: any[]): boolean {
        if (this.isNullOrEmpty(values)) {
            return true;
        }
        const isFirstValueNull = values[0] == null;
        for (const value of values) {
            if ((value == null) != isFirstValueNull) {
                return false;
            }
        }
        return true;
    }

}