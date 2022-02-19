import WebSocketClient, { EventListener } from './websocket-client';

export default class PlainWebSocketClient extends WebSocketClient {

    private readonly _ws: WebSocket;

    constructor(url: string, listener: EventListener) {
        super(url, listener);
        const ws = new WebSocket(url);
        ws.binaryType = 'arraybuffer';
        ws.onopen = (): void => this.notifyOnOpen();
        // onClose will always be triggered with a CloseEvent instance when
        // 1. rejected by the HTTP upgrade error response
        // 2. disconnected no matter by error (after onerror) or else,
        // so we don't need to add a listener on onerror
        ws.onclose = (e): void => this.notifyOnClose(e);
        ws.onmessage = (e): void => this.notifyOnMessage(e.data);
        this._ws = ws;
    }

    override get isConnecting(): boolean {
        return this._ws.readyState === WebSocket.CONNECTING;
    }

    override get isConnected(): boolean {
        return this._ws.readyState === WebSocket.OPEN;
    }

    override close(): void {
        this._ws.close();
    }

    override send(data: ArrayBufferLike | Blob | ArrayBufferView): Promise<void> {
        try {
            this._ws.send(data);
            return Promise.resolve();
        } catch (e) {
            return Promise.reject(e);
        }
    }

}