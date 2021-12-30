export default class Querystring {

    static encode(params) {
        if (!params) {
            return '';
        }
        params = Object.entries(params)
            .reduce((pre, [key, val]) => {
                if (val != null) {
                    pre[key] = val;
                }
                return pre;
            }, {});
        return new URLSearchParams(params).toString();
    }
    
}