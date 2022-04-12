export default class DataParser {

    static getDateTimeStr(value: Date): string | undefined {
        return value ? '' + value.getTime() : undefined;
    }

}