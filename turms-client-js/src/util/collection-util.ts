export default class CollectionUtil {

    static uniqueArray(array: any[]): any[] {
        return Array.from(new Set(array));
    }

}