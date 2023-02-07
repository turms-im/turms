export default class Querystring {

    static encode(params: Record<any, any> | null): string {
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