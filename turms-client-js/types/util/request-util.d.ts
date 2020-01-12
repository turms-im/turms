export default class RequestUtil {
    static ERROR: Error;
    static getIfNotNull(value: any): any | undefined;
    static getTimeIfNotNull(value: Date): any | undefined;
    private static isFalsy;
    private static isTruthy;
    static throwIfEmpty(value: any): void;
    static throwIfAnyFalsy(...values: any[]): void;
    static throwIfAllFalsy(...values: any[]): void;
}
