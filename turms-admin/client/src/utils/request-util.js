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
        const isCompositeKey = queryKey.isCompositeKey || queryKey === 'keys';
        if (isCompositeKey) {
            const keyName = queryKey.name || queryKey;
            targetKeys.forEach((key, index) => {
                key = JSONBig.parse(key);
                Object.entries(key).forEach(([itemKey, value]) => {
                    if (params !== '?') {
                        params += '&';
                    }
                    params += `${keyName}[${index}].${itemKey}=${value}`;
                });
            });
        } else {
            params += `${queryKey}=${targetKeys.join(',')}`;
        }
        return encodeURI(params);
    }
}