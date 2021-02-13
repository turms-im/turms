export default class CommonUtil {

    static splitByCapitals(text) {
        return text.split(/(?=[A-Z])/).join(' ');
    }

    static upperFirst(text) {
        return text.charAt(0).toUpperCase() + text.slice(1);
    }

    static deepSearch (object, key, predicate) {
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

    static sort(a, b, isAscend = true) {
        let result;
        if (typeof a ==='string' && typeof b === 'string') {
            const t1 = new Date(a);
            const t2 = new Date(b);
            result = isNaN(t1.getTime()) || isNaN(t2.getTime())
                ? a.localeCompare(b)
                : t1.getTime() - t2.getTime();
        }
        result = result ?? a - b;
        return isAscend ? result : -result;
    }

}