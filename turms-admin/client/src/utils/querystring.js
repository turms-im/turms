export default class Querystring {

    static encode(params) {
        if (!params) {
            return '';
        }
        const searchParams = new URLSearchParams();
        Object.entries(params)
            .forEach(([key, val]) => {
                if (val == null || val.length === 0) {
                    return;
                }
                searchParams.append(key, val);
            });
        return searchParams.toString();
    }
    
}