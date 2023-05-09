import JSONBig from 'json-bigint';
import * as _ from 'lodash-es';

class CommonUtil {

    static copy(val: any): any {
        return JSONBig.parse(JSONBig.stringify(val));
    }

    static deepSearch(object: any, key: string, predicate: Function): any {
        if (Object.hasOwnProperty.call(object, key) && predicate(key, object[key])) {
            return object;
        }
        for (const val of Object.values(object)) {
            if (typeof val === 'object') {
                const o = CommonUtil.deepSearch(val, key, predicate);
                if (o != null) {
                    return o;
                }
            }
        }
    }

    static isBigNumber(val: any): boolean {
        return val?._isBigNumber;
    }

    static sort(a: string | number, b: string | number, isAscend = true): number {
        let result;
        if (typeof a === 'string' && typeof b === 'string') {
            const t1 = new Date(parseInt(a));
            const t2 = new Date(parseInt(b));
            result = isNaN(t1.getTime()) || isNaN(t2.getTime())
                ? a.localeCompare(b)
                : t1.getTime() - t2.getTime();
        } else if (typeof a === 'number' && typeof b === 'number') {
            result = a - b;
        } else {
            throw new Error(`Unknown value type: ${typeof a}`);
        }
        return isAscend ? result : -result;
    }

    static range(start: number, end: number): Array<number> {
        const result = [];
        for (let i = start; i <= end; i++) {
            result.push(i);
        }
        return result;
    }

    static splitByCapitals(text: string): string {
        return text.split(/(?=[A-Z])/).join(' ');
    }

    static upperFirst(text: string): string {
        return text.charAt(0).toUpperCase() + text.slice(1);
    }

}

declare namespace CommonUtil {
    const get: typeof _.get;
    const set: typeof _.set;
    const isEqual: typeof _.isEqual;
    const unique: typeof _.uniq;
}

Object.assign(CommonUtil, {
    get: _.get,
    set: _.set,
    isEqual: _.isEqual,
    unique: _.uniq
});

export default CommonUtil;