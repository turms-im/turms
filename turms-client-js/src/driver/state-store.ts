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

import UserLocation from "../model/user-location";
import {im} from "../model/proto-bundle";
import UserStatus = im.turms.proto.UserStatus;
import DeviceType = im.turms.proto.DeviceType;

export interface UserInfo {
    userId?: string,
    userOnlineStatus?: UserStatus,
    deviceType?: DeviceType,
    location?: UserLocation
}

export default class StateStore {

    private _websocket?: WebSocket;
    private _isConnected: boolean;
    private _connectionRequestId?: number;
    private _sessionId?: string;
    private _lastRequestDate = new Date(0);
    private _userInfo: UserInfo = {};

    get websocket(): WebSocket {
        return this._websocket;
    }

    set websocket(value: WebSocket) {
        this._websocket = value;
    }

    get isConnected(): boolean {
        return this._isConnected;
    }

    set isConnected(value: boolean) {
        this._isConnected = value;
    }

    get connectionRequestId(): number {
        return this._connectionRequestId;
    }

    set connectionRequestId(value: number) {
        this._connectionRequestId = value;
    }

    get sessionId(): string {
        return this._sessionId;
    }

    set sessionId(value: string) {
        this._sessionId = value;
    }

    get lastRequestDate(): Date {
        return this._lastRequestDate;
    }

    set lastRequestDate(value: Date) {
        this._lastRequestDate = value;
    }

    get userInfo(): UserInfo {
        return this._userInfo;
    }

    set userInfo(value: UserInfo) {
        this._userInfo = value;
    }

}