import JSONBig from 'json-bigint';

export default class RequestUtil {
    static generateDateRangeParams(dateNames, start, end) {
        const params = {};
        dateNames.forEach(name => {
            if (name.endsWith('Date')) {
                if (start) {
                    params[`${name}Start`] = start;
                }
                params[`${name}End`] = end;
            } else {
                if (start) {
                    params[`${name}StartDate`] = start;
                }
                params[`${name}EndDate`] = end;
            }
        });
        return params;
    }
    static getQueryParams(queryKey, targetKeys) {
        let params = '?';
        if (queryKey === 'keys') {
            targetKeys.forEach((key, index) => {
                key = JSONBig.parse(key);
                Object.entries(key).forEach(entry => {
                    if (params !== '?') {
                        params += '&';
                    }
                    params += `keys[${index}].${entry[0]}=${entry[1]}`;
                });
            });
        } else {
            params += `${queryKey}=${targetKeys.join(',')}`;
        }
        params = encodeURI(params);
        return params;
    }
}