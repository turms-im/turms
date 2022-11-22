import SharedContextService, { NotificationType, RequestType } from '../driver/service/shared-context-service';
import WebSocketClient, { EventListener } from './websocket-client';
import WebSocketMetrics from './websocket-metrics';

export default class WorkerWebSocketClient extends WebSocketClient {

    private readonly _sharedContextService: SharedContextService;

    private _isConnecting: boolean;
    private _isConnected: boolean;
    private _metrics = new WebSocketMetrics();

    constructor(url: string, listener: EventListener, sharedContextService: SharedContextService) {
        super(url, listener);
        let connectStart;
        sharedContextService.addNotificationListener(NotificationType.WEBSOCKET_CONNECTING, () => {
            connectStart = Date.now();
            this._isConnecting = true;
            this._isConnected = false;
        });
        sharedContextService.addNotificationListener(NotificationType.WEBSOCKET_CONNECTED, () => {
            this._isConnecting = false;
            this._isConnected = true;
            this._metrics.connectTime = Date.now() - connectStart;
            this.notifyOnOpened();
        });
        sharedContextService.addNotificationListener(NotificationType.WEBSOCKET_CLOSED, notification => {
            this._isConnecting = false;
            this._isConnected = false;
            this._metrics = new WebSocketMetrics();
            this.notifyOnClosed({
                code: notification.data.code,
                reason: notification.data.reason
            });
        });
        sharedContextService.addNotificationListener(NotificationType.WEBSOCKET_MESSAGE_RECEIVED, notification => {
            const data = notification.data as ArrayBufferLike;
            this._metrics.dataReceived += data.byteLength;
            this.notifyOnMessage(data);
        });
        this._sharedContextService = sharedContextService;
        sharedContextService.request({
            type: RequestType.CONNECT,
            data: url
        }).then(alreadyCreated => {
            if (alreadyCreated) {
                this.notifyOnOpened();
            }
        }).catch((error: Error) => {
            this.notifyOnClosed({
                code: 1006,
                reason: error.message
            });
        });
    }

    override get isConnecting(): boolean {
        return this._isConnecting;
    }

    override get isConnected(): boolean {
        return this._isConnected;
    }

    override get metrics(): WebSocketMetrics {
        return this._metrics;
    }

    override close(): void {
        // do nothing
    }

    override send(data: ArrayBufferLike | Blob | ArrayBufferView): Promise<void> {
        return this._sharedContextService.request({
            type: RequestType.SEND_DATA,
            data
        }).then(() => {
            this._metrics.dataSent += data instanceof Blob ? data.size : data.byteLength;
        });
    }

}