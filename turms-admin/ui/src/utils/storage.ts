export default class Storage {

    static get(key: string): any {
        return JSON.parse(localStorage.getItem(key));
    }

    static set(key: string, val: any): void {
        val = JSON.stringify(val);
        localStorage.setItem(key, val);
    }

    static getArray(arrayKey: string): Array<any> {
        return Storage.get(arrayKey) ?? [];
    }

    static push(arrayKey: string, val: any, maxSize: number): void {
        const array = Storage.getArray(arrayKey);
        if (array.length > maxSize) {
            return;
        }
        array.push(val);
        localStorage.setItem(arrayKey, JSON.stringify(array));
    }

}