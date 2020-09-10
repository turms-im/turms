export default class RequestUtil {
    static ERROR: Error;
    static wrapValueIfNotNull(value: any): any | undefined;
    static wrapTimeIfNotNull(value: Date): any | undefined;
    static isFalsy(value: any): boolean;
    static isTruthy(value: any): boolean;
    static throwIfEmpty(value: any): void;
    static throwIfAnyFalsy(...values: any[]): void;
    static throwIfAllFalsy(...values: any[]): void;
    static areAllFalsy(...values: any[]): boolean;
    static generateRandomId(requestMap: Record<number, any>): number;
}
