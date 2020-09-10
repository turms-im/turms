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

import Timer from "../../util/timer";
import TurmsBusinessException from "../../model/turms-business-exception";
import TurmsStatusCode from "../../model/turms-status-code";
import StateStore from "../state-store";

export default class HeartbeatService {

    private static HEARTBEAT_REQUEST = new Uint8Array(0);

    private _stateStore: StateStore;

    private _heartbeatInterval: number;
    private _minRequestsInterval: number;
    private _heartbeatTimer?: Timer;
    private _heartbeatCallbacks = [];

    constructor(stateStore: StateStore, heartbeatInterval: number, minRequestsInterval: number) {
        this._heartbeatInterval = heartbeatInterval;
        this._minRequestsInterval = minRequestsInterval;
    }

    start(): void {
        if (this._heartbeatTimer && this._heartbeatTimer.isRunning) {
            this._heartbeatTimer.reset(this._heartbeatInterval);
        } else {
            this._heartbeatTimer = new Timer((): void => {
                const difference = new Date().getTime() - this._stateStore.lastRequestDate.getTime();
                if (difference > this._minRequestsInterval) {
                    this.send().then(() => null);
                }
            }, this._heartbeatInterval);
            this._heartbeatTimer.start();
        }
    }

    stop(): void {
        if (this._heartbeatTimer) {
            this._heartbeatTimer.stop();
        }
    }

    reset(): void {
        if (this._heartbeatTimer) {
            this._heartbeatTimer.reset(this._heartbeatInterval);
        }
    }

    send(): Promise<void> {
        return new Promise((resolve, reject): void => {
            if (this._stateStore.isConnected) {
                this._stateStore.websocket.send(HeartbeatService.HEARTBEAT_REQUEST);
                this._heartbeatCallbacks.push(resolve);
            } else {
                reject(TurmsBusinessException.fromCode(TurmsStatusCode.CLIENT_SESSION_HAS_BEEN_CLOSED));
            }
        });
    }

    notifyHeartbeatCallbacks(): void {
        for (const cb of this._heartbeatCallbacks) {
            cb();
        }
        this._heartbeatCallbacks = [];
    }

}