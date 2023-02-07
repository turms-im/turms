import JSONBig from 'json-bigint';

type CompositeQueryKey = { isCompositeKey: boolean, name: string };
export default class RequestUtil {
    static generateDateRangeParams(dateNames: Array<string>, start: number, end: number): Record<string, number> {
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

    static getQueryParams(queryKey: string | CompositeQueryKey, targetKeys: string[] | Record<string, any>[]): string {
        targetKeys = targetKeys.map(key => {
            key = JSONBig.parse(key);
            return key.key || key;
        });
        let params = '?';
        const isCompositeKey = (queryKey as CompositeQueryKey).isCompositeKey || queryKey === 'keys';
        if (isCompositeKey) {
            const keyName = (queryKey as CompositeQueryKey).name || queryKey;
            targetKeys.forEach((key, index) => {
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