import WebSocketMetrics from './websocket-metrics';

type OnClosedParams = {
    code: number,
    reason: string
};

export interface EventListener {
    onOpened: (() => any);
    onClosed: ((event: OnClosedParams) => any);
    onMessage: ((data: ArrayBuffer) => any);
}

export default abstract class WebSocketClient {

    private readonly _url: string;
    private readonly _listener: EventListener;

    protected constructor(url: string, listener: EventListener) {
        this._url = url;
        this._listener = listener;
    }

    get url(): string {
        return this._url;
    }

    abstract get isConnecting(): boolean;

    abstract get isConnected(): boolean;

    abstract get metrics(): WebSocketMetrics;

    protected notifyOnOpened(): void {
        this._listener.onOpened();
    }

    protected notifyOnClosed(event: OnClosedParams): void {
        this._listener.onClosed(event);
    }

    protected notifyOnMessage(data: ArrayBuffer): void {
        this._listener.onMessage(data);
    }

    abstract close(): void;

    abstract send(data: ArrayBufferLike | Blob | ArrayBufferView): Promise<void>;
}
