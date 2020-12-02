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
import TurmsBusinessError from "../../model/turms-business-error";
import TurmsStatusCode from "../../model/turms-status-code";
import StateStore from "../state-store";
import PromiseSeal from "../../model/promise-seal";

export default class HeartbeatService {

    private static readonly DEFAULT_HEARTBEAT_INTERVAL = 120 * 1000;
    private static readonly HEARTBEAT_REQUEST = new Uint8Array(0);

    private _stateStore: StateStore;

    private _heartbeatInterval: number;
    private _minRequestInterval: number;
    private _heartbeatTimer?: Timer;
    private _heartbeatPromises: PromiseSeal[] = [];

    constructor(stateStore: StateStore, minRequestInterval: number, heartbeatInterval?: number) {
        this._stateStore = stateStore;
        this._minRequestInterval = minRequestInterval;
        this._heartbeatInterval = heartbeatInterval || HeartbeatService.DEFAULT_HEARTBEAT_INTERVAL;
    }

    start(): void {
        if (this._heartbeatTimer && this._heartbeatTimer.isRunning) {
            this._heartbeatTimer.reset(this._heartbeatInterval);
        } else {
            this._heartbeatTimer = new Timer((): void => {
                const difference = new Date().getTime() - this._stateStore.lastRequestDate.getTime();
                if (difference > this._minRequestInterval) {
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
                this._heartbeatPromises.push({
                    resolve,
                    reject
                });
            } else {
                reject(TurmsBusinessError.fromCode(TurmsStatusCode.CLIENT_SESSION_HAS_BEEN_CLOSED));
            }
        });
    }

    resolveHeartbeatPromises(): void {
        for (const cb of this._heartbeatPromises) {
            cb.resolve();
        }
        this._heartbeatPromises = [];
    }

    rejectHeartbeatPromises(error: any): void {
        for (const cb of this._heartbeatPromises) {
            cb.reject(error);
        }
        this._heartbeatPromises = [];
    }

}