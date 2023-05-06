export interface HttpRequestOptions {
    method?: string;
    headers?: Record<string, string>;
    body?: Parameters<XMLHttpRequest['send']>[0];
}

interface HttpResponse {
    ok: boolean;
    statusText: string;
    status: number;
    url: string;
    text: () => Promise<string>;
    json: () => Promise<any>;
    blob: () => Promise<Blob>;
}

export default function (url: string, options: HttpRequestOptions = {}): Promise<HttpResponse> {
    return new Promise((resolve, reject) => {
        const request = new XMLHttpRequest();
        request.open(options.method || 'get', url, true);
        request.onload = (): void => {
            resolve({
                // 200-299
                ok: ((request.status / 100) | 0) == 2,
                statusText: request.statusText,
                status: request.status,
                url: request.responseURL,
                text: () => Promise.resolve(request.responseText),
                json: () => Promise.resolve(request.responseText).then(JSON.parse),
                blob: () => Promise.resolve(new Blob([request.response]))
            });
        };
        request.onerror = reject;
        for (const header in options.headers) {
            request.setRequestHeader(header, options.headers[header]);
        }
        request.send(options.body || null);
    });
}