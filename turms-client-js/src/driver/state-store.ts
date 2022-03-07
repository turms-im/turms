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

import WebSocketClient from '../transport/websocket-client';
import SharedContextService, { NotificationType, RequestType } from './service/shared-context-service';

export default class StateStore {

    // Service
    private readonly _sharedContextService?: SharedContextService;

    // Connection
    private _websocket?: WebSocketClient;

    // Session
    private _isSessionOpen: boolean;
    private _sessionId?: string;
    private _serverId?: string;

    // Request
    private _lastRequestDate = 0;

    constructor(sharedContextService?: SharedContextService) {
        this._sharedContextService = sharedContextService;
        sharedContextService?.addNotificationListener(NotificationType.UPDATE_IS_SESSION_OPEN, notification => {
            this._isSessionOpen = notification.data;
        });
        sharedContextService?.addNotificationListener(NotificationType.UPDATE_SESSION_ID, notification => {
            this._sessionId = notification.data;
        });
        sharedContextService?.addNotificationListener(NotificationType.UPDATE_SERVER_ID, notification => {
            this._serverId = notification.data;
        });
        sharedContextService?.addNotificationListener(NotificationType.UPDATE_LAST_REQUEST_DATE, notification => {
            this._lastRequestDate = notification.data;
        });
    }

    get sharedContextService(): SharedContextService | undefined {
        return this._sharedContextService;
    }

    get useSharedContext(): boolean {
        return !!this._sharedContextService;
    }

    get websocket(): WebSocketClient | undefined {
        return this._websocket;
    }

    set websocket(value: WebSocketClient) {
        this._websocket = value;
    }

    get isConnected(): boolean {
        return !!this._websocket?.isConnected;
    }

    get isSessionOpen(): boolean {
        return this._isSessionOpen;
    }

    set isSessionOpen(value: boolean) {
        this._isSessionOpen = value;
        this._sharedContextService?.request({
            type: RequestType.UPDATE_IS_SESSION_OPEN,
            data: value
        });
    }

    get sessionId(): string {
        return this._sessionId;
    }

    set sessionId(value: string) {
        this._sessionId = value;
        this._sharedContextService?.request({
            type: RequestType.UPDATE_SESSION_ID,
            data: value
        });
    }

    get serverId(): string {
        return this._serverId;
    }

    set serverId(value: string) {
        this._serverId = value;
        this._sharedContextService?.request({
            type: RequestType.UPDATE_SERVER_ID,
            data: value
        });
    }

    get lastRequestDate(): number {
        return this._lastRequestDate;
    }

    set lastRequestDate(value: number) {
        this._lastRequestDate = value;
        this._sharedContextService?.request({
            type: RequestType.UPDATE_LAST_REQUEST_DATE,
            data: value
        });
    }

    reset(): void {
        this._websocket = null;
        this._isSessionOpen = false;
        this._sessionId = null;
        this._serverId = null;
        this._lastRequestDate = 0;
    }

}