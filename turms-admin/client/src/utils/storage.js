export default class Storage {

    static get(key) {
        return localStorage.getItem(key);
    }

    static set(key, val) {
        if (typeof val !== 'string') {
            val = JSON.stringify(val);
        }
        localStorage.setItem(key, val);
    }

    static getArray(arrayKey) {
        const array = localStorage.getItem(arrayKey);
        return array ? JSON.parse(array) : [];
    }

    static push(arrayKey, val, maxSize) {
        const array = Storage.getArray(arrayKey);
        if (array.length > maxSize) {
            return;
        }
        array.push(val);
        localStorage.setItem(arrayKey, JSON.stringify(array));
    }

}