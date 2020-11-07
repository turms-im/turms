/*
 * Copyright (C) 2019 The Turms Project
 * https://github.com/turms-im/turms
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import UserLocation from "../../model/user-location";
import TurmsBusinessError from "../../model/turms-business-error";
import TurmsStatusCode from "../../model/turms-status-code";
import {im} from "../../model/proto-bundle";
import StateStore from "../state-store";
import DeviceType = im.turms.proto.DeviceType;
import UserStatus = im.turms.proto.UserStatus;

const COOKIE_REQUEST_ID = 'rid';
const COOKIE_USER_ID = 'uid';
const COOKIE_PASSWORD = 'pwd';
const COOKIE_USER_ONLINE_STATUS = 'us';
const COOKIE_DEVICE_TYPE = 'dt';
const COOKIE_LOCATION = 'loc';

export interface ConnectOptions {
    wsUrl?: string,
    connectTimeout?: number,

    userId: string,
    password: string,
    deviceType?: DeviceType,
    userOnlineStatus?: UserStatus,
    location?: UserLocation
}

export interface ConnectionDisconnectInfo {
    wasConnected: boolean,
    isClosedByClient: boolean,
    event: CloseEvent
}

export default class ConnectionService {

    private static readonly DEFAULT_WEBSOCKET_URL = 'ws://localhost:9510';
    private static readonly DEFAULT_CONNECT_TIMEOUT = 30 * 1000;

    private _stateStore: StateStore;
    private readonly _initialWsUrl: string;
    private readonly _initialConnectTimeout: number;
    private readonly _storePassword: boolean;

    private _isClosedByClient = false;
    private _disconnectPromises = [];
    private _connectOptions = {} as ConnectOptions;

    private _onConnectedListeners: (() => void)[] = [];
    private _onDisconnectedListeners: ((info: ConnectionDisconnectInfo) => Promise<void>)[] = [];
    private _onMessageListeners: ((message: any) => void)[] = [];

    constructor(stateStore: StateStore, wsUrl?: string, connectTimeout?: number, storePassword = true) {
        this._stateStore = stateStore;
        this._initialWsUrl = wsUrl || ConnectionService.DEFAULT_WEBSOCKET_URL;
        if (!connectTimeout && connectTimeout !== 0) {
            this._initialConnectTimeout = ConnectionService.DEFAULT_CONNECT_TIMEOUT;
        } else {
            this._initialConnectTimeout = connectTimeout;
        }
        this._storePassword = storePassword;
        this._resetStates();
    }

    private _resetStates(): void {
        this._stateStore.connectionRequestId = null;
        this._isClosedByClient = false;
        this._resolveDisconnectPromises();
    }

    // Listeners

    addOnConnectedListener(listener: () => void): void {
        this._onConnectedListeners.push(listener);
    }

    addOnDisconnectedListener(listener: (info: ConnectionDisconnectInfo) => Promise<void>): void {
        this._onDisconnectedListeners.push(listener);
    }

    addOnMessageListener(listener: (message: any) => void): void {
        this._onMessageListeners.push(listener);
    }

    private _notifyOnConnectedListener(): void {
        for (const listener of this._onConnectedListeners) {
            listener.call(this);
        }
    }

    private _notifyOnDisconnectedListeners(info: ConnectionDisconnectInfo): Promise<void> {
        let promise;
        for (const listener of this._onDisconnectedListeners) {
            const result = listener.call(this, info);
            if (!promise) {
                promise = result;
            }
        }
        if (promise) {
            return promise;
        } else {
            return Promise.reject();
        }
    }

    private _notifyOnMessageListeners(message: any): void {
        for (const listener of this._onMessageListeners) {
            listener.call(this, message);
        }
    }

    private _resolveDisconnectPromises(): void {
        for (const cb of this._disconnectPromises) {
            cb();
        }
        this._disconnectPromises = [];
    }

    // Connection

    connect(options: ConnectOptions): Promise<void> {
        return new Promise((resolve, reject) => {
            if (this._stateStore.isConnected) {
                return TurmsBusinessError.fromCode(TurmsStatusCode.CLIENT_SESSION_ALREADY_ESTABLISHED);
            } else {
                this._resetStates();
                this._stateStore.connectionRequestId = Math.floor(Math.random() * 16383) + 1;
                ConnectionService._fillLoginInfo(this._stateStore.connectionRequestId, options.userId, options.password, options.userOnlineStatus, options.deviceType, options.location);
                this._stateStore.userInfo = {
                    userId: options.userId,
                    deviceType: options.deviceType,
                    userOnlineStatus: options.userOnlineStatus,
                    location: options.location
                }
                this._connectOptions = JSON.parse(JSON.stringify(options));
                if (!this._storePassword) {
                    delete this._connectOptions["password"];
                }

                const ws = new WebSocket(options.wsUrl || this._initialWsUrl);
                ws.binaryType = "arraybuffer";
                this._stateStore.websocket = ws;

                const connectTimeout = options.connectTimeout || this._initialConnectTimeout;
                let connectTimeoutId;
                if (connectTimeout && connectTimeout > 0) {
                    connectTimeoutId = setTimeout(() => {
                        reject(new Error('Connection Timeout'));
                    }, connectTimeout);
                }

                // onClose will always be triggered with a CloseEvent instance when
                // 1. rejected by the HTTP upgrade error response
                // 2. disconnected no matter by error (after onError) or else
                this._stateStore.websocket.onclose = (event): void => {
                    if (connectTimeoutId) {
                        clearTimeout(connectTimeoutId);
                    }
                    this._onWebSocketClose(event)
                        // for the case when redirecting successfully
                        .then(() => resolve())
                        .catch(e => reject(e));
                };
                this._stateStore.websocket.onopen = ((): void => {
                    if (connectTimeoutId) {
                        clearTimeout(connectTimeoutId);
                    }
                    this._onWebSocketOpen();
                    resolve();
                });
                this._stateStore.websocket.onmessage = (event): void => this._notifyOnMessageListeners(event.data);
            }
        });
    }

    disconnect(): Promise<void> {
        if (this._stateStore.isConnected || this._stateStore.websocket.readyState === WebSocket.CONNECTING) {
            this._isClosedByClient = true;
            return new Promise(resolve => {
                this._disconnectPromises.push(resolve);
                this._stateStore.websocket.close();
            });
        } else {
            return Promise.reject();
        }
    }

    reconnect(host?: string): Promise<void> {
        if (host) {
            const isSecure = this._connectOptions.wsUrl && this._connectOptions.wsUrl.startsWith("wss://");
            this._connectOptions.wsUrl = `${isSecure ? "wss://" : "ws://"}${host}`;
        }
        return this.connect(this._connectOptions);
    }

    private static _fillLoginInfo(
        requestId: number,
        userId: string,
        password: string,
        userOnlineStatus?: UserStatus,
        deviceType?: DeviceType,
        location?: UserLocation): void {
        document.cookie = `${COOKIE_REQUEST_ID}=${requestId}; path=/`;
        document.cookie = `${COOKIE_USER_ID}=${userId}; path=/`;
        document.cookie = `${COOKIE_PASSWORD}=${escape(password)}; path=/`;
        if (userOnlineStatus) {
            document.cookie = `${COOKIE_USER_ONLINE_STATUS}=${UserStatus[userOnlineStatus]}; path=/`;
        }
        if (deviceType) {
            document.cookie = `${COOKIE_DEVICE_TYPE}=${DeviceType[deviceType]}; path=/`;
        }
        if (location) {
            document.cookie = `${COOKIE_LOCATION}=${location.toString()}; path=/`;
        }
    }

    private static _clearLoginInfo(): void {
        const now = new Date().toUTCString();
        document.cookie = `${COOKIE_USER_ID}=;expires=${now}`;
        document.cookie = `${COOKIE_PASSWORD}=;expires=${now}`;
        document.cookie = `${COOKIE_USER_ONLINE_STATUS}=;expires=${now}`;
        document.cookie = `${COOKIE_DEVICE_TYPE}=;expires=${now}`;
        document.cookie = `${COOKIE_REQUEST_ID}=;expires=${now}`;
        document.cookie = `${COOKIE_LOCATION}=;expires=${now}`;
    }

    // Lifecycle hooks

    private _onWebSocketOpen(): void {
        ConnectionService._clearLoginInfo();
        this._stateStore.isConnected = true;
        this._notifyOnConnectedListener();
    }

    private _onWebSocketClose(event: CloseEvent): Promise<void> {
        ConnectionService._clearLoginInfo();
        const wasConnected = this._stateStore.isConnected;
        this._stateStore.isConnected = false;
        this._resolveDisconnectPromises();
        return this._notifyOnDisconnectedListeners({
            wasConnected,
            isClosedByClient: this._isClosedByClient,
            event
        });
    }

}