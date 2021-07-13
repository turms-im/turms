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

import StateStore from '../state-store';
import SystemUtil from '../../util/system-util';
import TurmsBusinessError from '../../model/turms-business-error';
import TurmsStatusCode from '../../model/turms-status-code';
import BaseService from './base-service';

export interface ConnectOptions {
    wsUrl?: string,
    connectTimeout?: number
}

export interface ConnectionDisconnectInfo {
    wasConnected: boolean,
    wsUrl: string,
    code: number,
    reason: string
}

export default class ConnectionService extends BaseService {

    private readonly _initialWsUrl: string;
    private readonly _initialConnectTimeout: number;

    private _disconnectPromises = [];

    private _onConnectedListeners: (() => void)[] = [];
    private _onDisconnectedListeners: ((info: ConnectionDisconnectInfo) => void)[] = [];
    private _messageListeners: ((message: ArrayBuffer) => void)[] = [];

    constructor(stateStore: StateStore, wsUrl?: string, connectTimeout?: number) {
        super(stateStore);
        this._initialWsUrl = wsUrl || 'ws://localhost:10510';
        this._initialConnectTimeout = isNaN(connectTimeout) || connectTimeout <= 0
            ? 30 * 1000
            : connectTimeout;
        this._closeConnectionBeforeUnload();
    }

    /**
     * Try to close the connection gracefully manually
     * because there is no guarantee that the browser will do this for us
     */
    private _closeConnectionBeforeUnload(): void {
        if (SystemUtil.isBrowser()) {
            window.addEventListener('beforeunload', () => {
                const ws = this._stateStore.websocket;
                const status = ws?.readyState;
                if (status === WebSocket.OPEN || status === WebSocket.CONNECTING) {
                    // Don't use 1001 because the code must be either 1000,
                    // or between 3000 and 4999 according to Chrome
                    ws.close(1000);
                }
            });
        }
    }

    private _resetStates(): void {
        this._resolveDisconnectPromises();
    }

    // Listeners

    addOnConnectedListener(listener: () => void): void {
        this._onConnectedListeners.push(listener);
    }

    addOnDisconnectedListener(listener: (info: ConnectionDisconnectInfo) => void): void {
        this._onDisconnectedListeners.push(listener);
    }

    addMessageListener(listener: (message: ArrayBuffer) => void): void {
        this._messageListeners.push(listener);
    }

    removeOnConnectedListener(listener: () => void): void {
        this._onConnectedListeners = this._onConnectedListeners
            .filter(cur => cur !== listener);
    }

    removeOnDisconnectedListener(listener: (info: ConnectionDisconnectInfo) => void): void {
        this._onDisconnectedListeners = this._onDisconnectedListeners
            .filter(cur => cur !== listener);
    }

    removeMessageListener(listener: (message: ArrayBuffer) => void): void {
        this._messageListeners = this._messageListeners
            .filter(cur => cur !== listener);
    }

    private _notifyOnConnectedListener(): void {
        this._onConnectedListeners.forEach(listener => listener.call(this));
    }

    private _notifyOnDisconnectedListeners(info: ConnectionDisconnectInfo): void {
        this._onDisconnectedListeners.forEach(listener => listener.call(this, info));
    }

    private _notifyMessageListeners(message: ArrayBuffer): void {
        this._messageListeners.forEach(listener => listener.call(this, message));
    }

    private _resolveDisconnectPromises(): void {
        this._disconnectPromises.forEach(cb => cb());
        this._disconnectPromises = [];
    }

    // Connection

    connect({
                wsUrl = this._initialWsUrl,
                connectTimeout = this._initialConnectTimeout
            }: ConnectOptions = {}): Promise<void> {
        return new Promise((resolve, reject) => {
            if (this._stateStore.isConnected) {
                return wsUrl === this._stateStore.websocket.url
                    ? resolve()
                    : Promise.reject(TurmsBusinessError.fromCode(TurmsStatusCode.CLIENT_SESSION_ALREADY_ESTABLISHED));
            }
            this._resetStates();
            const ws = new WebSocket(wsUrl);
            ws.binaryType = 'arraybuffer';
            this._stateStore.websocket = ws;

            let connectTimeoutId;
            if (connectTimeout > 0) {
                connectTimeoutId = setTimeout(() => {
                    reject(TurmsBusinessError.fromCode(TurmsStatusCode.CONNECT_TIMEOUT));
                }, connectTimeout);
            }

            // onClose will always be triggered with a CloseEvent instance when
            // 1. rejected by the HTTP upgrade error response
            // 2. disconnected no matter by error (after onerror) or else
            // so that we don't need to add a listener on onerror
            ws.onclose = (event): void => {
                if (connectTimeoutId) {
                    clearTimeout(connectTimeoutId);
                }
                const info = this._onWebSocketClose(event);
                reject(info);
            };
            ws.onopen = ((): void => {
                if (connectTimeoutId) {
                    clearTimeout(connectTimeoutId);
                }
                this._onWebSocketOpen();
                resolve();
            });
            ws.onmessage = (event): void => this._notifyMessageListeners(event.data);
        });
    }

    disconnect(): Promise<void> {
        const ws = this._stateStore.websocket;
        if (this._stateStore.isConnected || ws?.readyState === WebSocket.CONNECTING) {
            return new Promise(resolve => {
                this._disconnectPromises.push(resolve);
                ws.close(1000);
            });
        }
        return Promise.resolve();
    }

    // Lifecycle hooks

    private _onWebSocketOpen(): void {
        this._notifyOnConnectedListener();
    }

    private _onWebSocketClose(event: CloseEvent): ConnectionDisconnectInfo {
        const wasConnected = this._stateStore.isConnected;
        this._resolveDisconnectPromises();
        const info = {
            wasConnected,
            wsUrl: (event.target as WebSocket).url,
            code: event.code,
            reason: event.reason
        };
        this._notifyOnDisconnectedListeners(info);
        return info;
    }

    // Base methods

    close(): Promise<void> {
        return this.disconnect();
    }

    // eslint-disable-next-line @typescript-eslint/no-empty-function
    onDisconnected(): void {
    }

}