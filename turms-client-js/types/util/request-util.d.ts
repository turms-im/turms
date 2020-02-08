export default class RequestUtil {
    static ERROR: Error;
    static wrapValueIfNotNull(value: any): any | undefined;
    static wrapTimeIfNotNull(value: Date): any | undefined;
    private static isFalsy;
    private static isTruthy;
    static throwIfEmpty(value: any): void;
    static throwIfAnyFalsy(...values: any[]): void;
    static throwIfAllFalsy(...values: any[]): void;
    static areAllFalsy(...values: any[]): boolean;
}
