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

import ResponseError from '../../error/response-error';
import ResponseStatusCode from '../../model/response-status-code';
import StateStore from '../state-store';
import WebSocketFactory from '../../transport/websocket-factory';
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

    private _notifyOnConnectedListeners(): void {
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
            const websocket = this._stateStore.websocket;
            if (websocket?.isConnected) {
                return wsUrl === websocket.url
                    ? resolve()
                    : reject(ResponseError.from({
                        code: ResponseStatusCode.CLIENT_SESSION_ALREADY_ESTABLISHED
                    }));
            }
            this._resetStates();
            this._stateStore.websocket = WebSocketFactory.create(wsUrl, {
                onOpened: (): void => {
                    if (connectTimeoutId) {
                        clearTimeout(connectTimeoutId);
                    }
                    this._onWebSocketOpen();
                    resolve();
                },
                onClosed: (event): void => {
                    if (connectTimeoutId) {
                        clearTimeout(connectTimeoutId);
                    }
                    const info = this._onWebSocketClosed(wsUrl, event);
                    reject(info);
                },
                onMessage: (data): void => this._notifyMessageListeners(data)
            }, this._stateStore.sharedContextService);

            let connectTimeoutId;
            if (connectTimeout > 0) {
                connectTimeoutId = setTimeout(() => {
                    reject(ResponseError.from({
                        code: ResponseStatusCode.CONNECT_TIMEOUT
                    }));
                }, connectTimeout);
            }
        });
    }

    disconnect(): Promise<void> {
        const ws = this._stateStore.websocket;
        if (ws?.isConnected || ws?.isConnecting) {
            return new Promise(resolve => {
                this._disconnectPromises.push(resolve);
                ws.close();
            });
        }
        return Promise.resolve();
    }

    // Lifecycle hooks

    private _onWebSocketOpen(): void {
        this._notifyOnConnectedListeners();
    }

    private _onWebSocketClosed(url: string, event: {
        code: number,
        reason: string
    }): ConnectionDisconnectInfo {
        const wasConnected = this._stateStore.isConnected;
        this._resolveDisconnectPromises();
        const info = {
            wasConnected,
            wsUrl: url,
            code: event.code,
            reason: event.reason
        };
        this._notifyOnDisconnectedListeners(info);
        return info;
    }

    // Base methods

    override close(): Promise<void> {
        return this.disconnect();
    }

    override onDisconnected(error?: Error): void {
        // do nothing
    }

}