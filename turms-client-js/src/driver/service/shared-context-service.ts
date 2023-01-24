import StateStore from '../state-store';
import BaseService from './base-service';
import SharedContextWorker from './shared-context-worker';

// Use string values because it's clear to distinguish when debugging
export const enum RequestType {
    REGISTER = 'register',
    UNREGISTER = 'unregister',
    REBIND_CONTEXT_ID = 'rebindContextId',

    REQUEST_LOGIN = 'requestLogin',
    FINISH_LOGIN_REQUEST = 'finishLoginRequest',

    UPDATE_LOGGED_IN_USER_INFO = 'updateLoggedInUserInfo',
    UPDATE_IS_SESSION_OPEN = 'updateIsSessionOpen',
    UPDATE_SESSION_ID = 'updateSessionId',
    UPDATE_SERVER_ID = 'updateServerId',
    UPDATE_LAST_REQUEST_DATE = 'updateLastRequestDate',

    CONNECT = 'connect',
    SEND_DATA = 'sendData'
}

export const enum NotificationType {
    UPDATE_LOGGED_IN_USER_INFO = 'updateLoggedInUserInfo',
    UPDATE_IS_SESSION_OPEN = 'updateIsSessionOpen',
    UPDATE_SESSION_ID = 'updateSessionId',
    UPDATE_SERVER_ID = 'updateServerId',
    UPDATE_LAST_REQUEST_DATE = 'updateLastRequestDate',

    WEBSOCKET_CONNECTING = 'websocketConnecting',
    WEBSOCKET_CONNECTED = 'websocketConnected',
    WEBSOCKET_CLOSED = 'websocketClosed',
    WEBSOCKET_MESSAGE_RECEIVED = 'websocketMessageReceived'
}

export type Request = {
    id?: number,
    type: RequestType,
    data?: any
};

export type Response = {
    requestId: number,
    data?: any,
    error?: Error
};

export type Notification = {
    type: NotificationType,
    data?: any
};

const workerSource = `(${SharedContextWorker.toString()})()`;
// Use Base64 instead of Blob for a permanent URL
const workerUrl = `data:application/javascript;base64,${btoa(workerSource)}`;

export default class SharedContextService extends BaseService {

    private readonly _sharedContextWorker: SharedWorker;
    private readonly _port: MessagePort;
    private _globalId: number;

    private readonly _pendingRequests: Record<string, {
        resolve: (response: Response) => void,
        reject: (any) => void
    }> = {};
    private readonly _registerRequest: Promise<void>;
    private requestSeq = 1;

    private readonly _listeners: Record<string, ((notification: Notification) => void)[]> = {};

    constructor(stateStore: StateStore) {
        super(stateStore);
        this._sharedContextWorker = new SharedWorker(workerUrl, 'turms-client:shared-context');
        this._port = this._sharedContextWorker.port;
        this._port.onmessage = (e): void => {
            const data = e.data;
            if (data.requestId) {
                this._resolveRequest(data);
            } else {
                this._handleNotification(data);
            }
        };
        this._registerRequest = this.request({
            type: RequestType.REGISTER
        }).then(response => {
            this._globalId = response.data;
        });
    }

    addNotificationListener(type: NotificationType, listener: (notification: Notification) => void): void {
        let list = this._listeners[type];
        if (!list) {
            list = [];
            this._listeners[type] = list;
        }
        list.push(listener);
    }

    request(request: Request): Promise<any> {
        let register: Promise<void>;
        if (request.type === RequestType.REGISTER) {
            register = Promise.resolve();
        } else {
            if (!this._registerRequest) {
                const error = new Error('Could not send the request to the shared worker because the local service has not registered');
                return Promise.reject(error);
            }
            register = this._registerRequest;
        }
        return register.then(() => {
            return new Promise((resolve, reject) => {
                const requestId = this.requestSeq++;
                request.id = requestId;
                this._pendingRequests[requestId] = {
                    resolve,
                    reject
                };
                this._port.postMessage(request);
            });
        });
    }

    private _resolveRequest(response: Response): void {
        const seal = this._pendingRequests[response.requestId];
        if (!seal) {
            return;
        }
        if (response.error) {
            seal.reject(response.error);
        } else {
            seal.resolve(response.data);
        }
    }

    private _handleNotification(notification: Notification): void {
        (this._listeners[notification.type] || []).forEach(listener => {
            listener(notification);
        });
    }

    override close(): Promise<void> {
        if (this._globalId) {
            return this.request({
                type: RequestType.UNREGISTER
            }).then();
        }
        return Promise.resolve();
    }

    override onDisconnected(error?: Error): void {
        // do nothing
    }

}