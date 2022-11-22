import WebSocketClient, { EventListener } from './websocket-client';
import WebSocketMetrics from './websocket-metrics';

export default class PlainWebSocketClient extends WebSocketClient {

    private readonly _ws: WebSocket;
    private _metrics = new WebSocketMetrics();

    constructor(url: string, listener: EventListener) {
        super(url, listener);
        const connectStart = Date.now();
        const ws = new WebSocket(url);
        ws.binaryType = 'arraybuffer';
        ws.onopen = (): void => {
            this._metrics.connectTime = Date.now() - connectStart;
            this.notifyOnOpened();
        };
        // onClose will always be triggered with a CloseEvent instance when
        // 1. rejected by the HTTP upgrade error response
        // 2. disconnected no matter by error (after onerror) or else,
        // so we don't need to add a listener on onerror
        ws.onclose = (e): void => {
            this._metrics = new WebSocketMetrics();
            this.notifyOnClosed(e);
        };
        ws.onmessage = (e): void => {
            const data = e.data as ArrayBufferLike;
            this.notifyOnMessage(data);
            this._metrics.dataReceived += data.byteLength;
        };
        this._ws = ws;
    }

    override get isConnecting(): boolean {
        return this._ws.readyState === WebSocket.CONNECTING;
    }

    override get isConnected(): boolean {
        return this._ws.readyState === WebSocket.OPEN;
    }

    override get metrics(): WebSocketMetrics {
        return this._metrics;
    }

    override close(): void {
        this._ws.close();
    }

    override send(data: ArrayBufferLike | Blob | ArrayBufferView): Promise<void> {
        try {
            this._ws.send(data);
            this._metrics.dataSent += data instanceof Blob ? data.size : data.byteLength;
            return Promise.resolve();
        } catch (e) {
            return Promise.reject(e);
        }
    }

}