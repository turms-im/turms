import WebSocketMetrics from './websocket-metrics';

type OnCloseParams = {
    code: number,
    reason: string
};

export interface EventListener {
    onOpen: (() => any);
    onClose: ((event: OnCloseParams) => any);
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

    protected notifyOnOpen(): void {
        this._listener.onOpen();
    }

    protected notifyOnClose(event: OnCloseParams): void {
        this._listener.onClose(event);
    }

    protected notifyOnMessage(data: ArrayBuffer): void {
        this._listener.onMessage(data);
    }

    abstract close(): void;

    abstract send(data: ArrayBufferLike | Blob | ArrayBufferView): Promise<void>;
}
