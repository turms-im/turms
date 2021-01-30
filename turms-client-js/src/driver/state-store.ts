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

export default class StateStore {

    /** Connection **/
    private _websocket?: WebSocket;

    /** Session **/
    private _isSessionOpen = false;
    private _sessionId?: string;
    private _serverId?: string;

    /** Request **/
    private _lastRequestDate = new Date(0);

    get websocket(): WebSocket {
        return this._websocket;
    }

    set websocket(value: WebSocket) {
        this._websocket = value;
    }

    get isConnected(): boolean {
        const ws = this._websocket;
        return ws && ws.readyState === WebSocket.OPEN;
    }

    get isSessionOpen(): boolean {
        return this._isSessionOpen;
    }

    set isSessionOpen(value: boolean) {
        this._isSessionOpen = value;
    }

    get sessionId(): string {
        return this._sessionId;
    }

    set sessionId(value: string) {
        this._sessionId = value;
    }

    get serverId(): string {
        return this._serverId;
    }

    set serverId(value: string) {
        this._serverId = value;
    }

    get lastRequestDate(): Date {
        return this._lastRequestDate;
    }

    set lastRequestDate(value: Date) {
        this._lastRequestDate = value;
    }

    reset(): void {
        this._websocket = null;
        this._isSessionOpen = false;
        this._sessionId = null;
        this._serverId = null;
        this._lastRequestDate = new Date(0);
    }
}