import Timer from "../util/timer";
// eslint-disable-next-line @typescript-eslint/ban-ts-ignore
// @ts-ignore
import * as WebSocketAsPromised from "websocket-as-promised";
import TurmsStatusCode from "../model/turms-status-code";
import TurmsError from "../model/turms-error";
import {im} from "../model/proto-bundle";
// @ts-ignore
import fetch from "unfetch";
import querystring from "querystring";
import TurmsClient from "../turms-client";
import NotificationUtil from "../util/notification-util";
import {ParsedNotification} from "../model/parsed-notification";
import TurmsNotification = im.turms.proto.TurmsNotification;
import TurmsRequest = im.turms.proto.TurmsRequest;

const HEARTBEAT_INTERVAL = 20 * 1000;
export default class TurmsDriver {
    private _heartbeatInterval: number;

    // eslint-disable-next-line @typescript-eslint/no-explicit-any
    private _websocket: any; //WebSocketAsPromised
    private _heartbeatTimer?: Timer;
    private _onMessage?: (notification: ParsedNotification) => void;
    private _onClose?: (wasConnected: boolean, error?: any, status?: TurmsError) => void;

    private _turmsClient: TurmsClient;
    private _url = 'ws://localhost:9510';
    private _httpUrl = 'http://localhost:9510';
    private _connectionTimeout = 10 * 1000;
    private _requestTimeout = 60 * 1000;
    private _minRequestsInterval = 0;
    private _requestsMap = {};
    private _lastRequestDate = new Date(0);
    private _isLastRequestHeartbeat = false;
    private _queryReasonWhenLoginFailed = true;
    private _queryReasonWhenDisconnected = true;
    private _userId: number;
    private _password: string;
    private _requestId: number;
    private _sessionId?: string;
    private _address?: string;

    constructor(client: TurmsClient,
                url?: string,
                connectionTimeout?: number,
                requestTimeout?: number,
                minRequestsInterval?: number,
                httpUrl?: string,
                queryReasonWhenLoginFailed = true,
                queryReasonWhenDisconnected = true) {
        this._turmsClient = client;
        if (url) this._url = url;
        if (connectionTimeout) this._connectionTimeout = connectionTimeout;
        if (requestTimeout) this._requestTimeout = requestTimeout;
        if (minRequestsInterval) this._minRequestsInterval = minRequestsInterval;
        this._heartbeatInterval = HEARTBEAT_INTERVAL;
        if (httpUrl) this._httpUrl = httpUrl;
        this._queryReasonWhenLoginFailed = queryReasonWhenLoginFailed;
        this._queryReasonWhenDisconnected = queryReasonWhenDisconnected;
    }

    set onClose(value: any) {
        this._onClose = value;
    }

    set onMessage(value: any) {
        this._onMessage = value;
    }

    sendHeartbeat(): Promise<void> {
        return new Promise((resolve, reject): void => {
            if (this.connected()) {
                this._setLastRequestRecord(true, new Date());
                this._websocket.send(new Uint8Array(0));
                resolve();
            } else {
                reject();
            }
        });
    }

    connected(): boolean {
        return this._websocket && this._websocket.isOpened;
    }

    disconnect(): Promise<CloseEvent> {
        if (this._websocket.isOpened || this._websocket.isOpening) {
            return this._websocket.close();
        } else {
            return Promise.reject();
        }
    }

    connect(userId: number, password: string, requestId: number, url?: string, connectionTimeout?: number, requestTimeout?: number): Promise<void> {
        return new Promise((resolve, reject) => {
            if (this.connected()) {
                reject(TurmsError.CLIENT_ALREADY_CONNECTED);
            } else {
                this._userId = userId;
                this._password = password;
                this._requestId = requestId;
                this._websocket = new WebSocketAsPromised(url || this._url, {
                    createWebSocket: (serverUrl): WebSocket => {
                        const ws = new WebSocket(serverUrl);
                        ws.binaryType = "arraybuffer";
                        return ws;
                    },
                    attachRequestId: (data: Uint8Array): Uint8Array => data,
                    packMessage: (data: Uint8Array): Uint8Array => data,
                    unpackMessage: (data: ArrayBuffer): TurmsNotification => TurmsNotification.decode(new Uint8Array(data)),
                    connectionTimeout: connectionTimeout || this._connectionTimeout,
                    timeout: requestTimeout || this._requestTimeout,
                    extractRequestId: (notification: TurmsNotification): number | undefined => {
                        if (!notification.relayedRequest && notification.requestId) {
                            return notification.requestId.value;
                        }
                    }
                });
                this._websocket.onUnpackedMessage.addListener((notification: TurmsNotification) => {
                    if (notification && notification.data && notification.data.session) {
                        this._sessionId = notification.data.session.sessionId;
                        this._address = notification.data.session.address;
                    }
                    if (this._onMessage) {
                        this._onMessage(NotificationUtil.transform(notification) as ParsedNotification);
                    }
                });
                let causedByError = false;
                this._websocket.onClose.addListener(() => {
                    if (!causedByError) {
                        this._onWebsocketClose()
                            .then(() => resolve())
                            .catch((error) => reject(error));
                    }
                });
                this._websocket.onError.addListener((error) => {
                    causedByError = true;
                    this._onWebsocketError(error)
                        .then(() => resolve())
                        .catch((error) => reject(error));
                });
                this._websocket.open()
                    .then(() => {
                        this._onWebsocketOpen();
                        resolve();
                    })
                    .catch((error) => reject(error));
            }
        });
    }

    resetHeartBeatTimer(): void {
        if (this._heartbeatTimer) {
            this._heartbeatTimer.reset(this._heartbeatInterval);
        }
    }

    send(message: im.turms.proto.ITurmsRequest): Promise<TurmsNotification> {
        if (this.connected()) {
            const now = new Date();
            if (!this._minRequestsInterval || now.getTime() - this._lastRequestDate.getTime() > this._minRequestsInterval) {
                this._setLastRequestRecord(false, now);
                const requestId = this._generateRandomId();
                message.requestId = {value: requestId};
                const data = TurmsRequest.encode(message).finish();
                this.resetHeartBeatTimer();
                return this._websocket.sendRequest(data, {
                    requestId: requestId
                }).then((notification: TurmsNotification) => {
                    if (notification.code && TurmsStatusCode.isErrorCode(notification.code.value)) {
                        throw TurmsError.fromNotification(notification);
                    } else {
                        return notification;
                    }
                });
            } else if (this._isLastRequestHeartbeat) {
                return new Promise((resolve, reject) => {
                    setTimeout(() => {
                        this.send(message)
                            .then(response => resolve(response))
                            .catch(error => reject(error));
                    }, this._minRequestsInterval);
                })
            } else {
                return Promise.reject("Request is too frequent");
            }
        } else {
            return Promise.reject("The WebSocket is closed");
        }
    }

    private _setLastRequestRecord(isLastRequestHeartbeat: boolean, lastRequestDate: Date) {
        this._isLastRequestHeartbeat = isLastRequestHeartbeat;
        this._lastRequestDate = lastRequestDate;
    }

    private _generateRandomId(): number {
        let id;
        do {
            id = Math.floor(Math.random() * 9007199254740991);
        } while (Object.prototype.hasOwnProperty.call(this._requestsMap, id));
        this._requestsMap[id] = true;
        return id;
    }

    private _onWebsocketOpen(): void {
        if (this._heartbeatTimer && this._heartbeatTimer.isRunning) {
            this._heartbeatTimer.reset(this._heartbeatInterval);
        } else {
            this._heartbeatTimer = new Timer((): Promise<void> => {
                const difference = new Date().getTime() - this._lastRequestDate.getTime();
                if (difference > this._minRequestsInterval) {
                    return this.sendHeartbeat();
                } else {
                    return Promise.reject();
                }
            }, this._heartbeatInterval);
            this._heartbeatTimer.start();
        }
    }

    private _onWebsocketClose(): Promise<void> {
        const wasLogged = !!(this._heartbeatTimer && this._heartbeatTimer.isRunning);
        if (this._heartbeatTimer && this._heartbeatTimer.isRunning) {
            this._heartbeatTimer.stop();
        }
        if (this._onClose) {
            if (this._queryReasonWhenDisconnected && this._userId && this._sessionId) {
                const params = querystring.stringify({
                    userId: this._userId,
                    sessionId: this._sessionId
                });
                return fetch(`${this._httpUrl}/reasons/disconnection?${params}`)
                    .then(response => {
                        return response.text()
                            .then(text => {
                                this._onClose(wasLogged, null,
                                    text ? TurmsError.fromCode(parseInt(text)) : null);
                            });
                    });
            } else {
                this._onClose(wasLogged);
            }
        }
        return Promise.resolve();
    }

    private _onWebsocketError(error): Promise<void> {
        const wasLogged = !!(this._heartbeatTimer && this._heartbeatTimer.isRunning);
        if (this._heartbeatTimer && this._heartbeatTimer.isRunning) {
            this._heartbeatTimer.stop();
        }
        if (!wasLogged && this._queryReasonWhenLoginFailed && this._userId && this._requestId) {
            const params = querystring.stringify({
                userId: this._userId,
                requestId: this._requestId
            });
            return fetch(`${this._httpUrl}/reasons/login-failed?${params}`)
                .then(response => {
                    if (response.status === 307) {
                        return this._turmsClient.userService.relogin();
                    } else {
                        return response.text()
                            .then(text => {
                                this._onClose(wasLogged, error, new TurmsError(3000, text))
                            });
                    }
                });
        } else {
            this._onClose(wasLogged, error);
        }
        return Promise.resolve();
    }
}
