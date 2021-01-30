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

import Timer from '../../util/timer';
import TurmsBusinessError from '../../model/turms-business-error';
import TurmsStatusCode from '../../model/turms-status-code';
import StateStore from '../state-store';
import PromiseSeal from '../../model/promise-seal';
import BaseService from './base-service';

export default class HeartbeatService extends BaseService {

    private static readonly DEFAULT_HEARTBEAT_INTERVAL = 120 * 1000;
    private static readonly HEARTBEAT_REQUEST = new Uint8Array(0);

    private readonly _heartbeatInterval: number;
    private readonly _heartbeatTimerInterval: number;
    private _lastHeartbeatRequestDate = 0;
    private _heartbeatTimer?: Timer;
    private _heartbeatPromises: PromiseSeal[] = [];

    constructor(stateStore: StateStore, heartbeatInterval?: number) {
        super(stateStore);
        this._heartbeatInterval = heartbeatInterval || HeartbeatService.DEFAULT_HEARTBEAT_INTERVAL;
        this._heartbeatTimerInterval = Math.max(1, this._heartbeatInterval / 10);
    }

    get isRunning(): boolean {
        return !!this._heartbeatTimer?.isRunning;
    }

    start(): void {
        if (!this.isRunning) {
            this._heartbeatTimer = new Timer((): void => {
                const now = new Date().getTime();
                const difference = Math.min(
                    now - this._stateStore.lastRequestDate.getTime(),
                    now - this._lastHeartbeatRequestDate);
                if (difference > this._heartbeatInterval) {
                    this.send().then(() => null);
                    this._lastHeartbeatRequestDate = now;
                }
            }, this._heartbeatTimerInterval);
            this._heartbeatTimer.start();
        }
    }

    stop(): void {
        this._heartbeatTimer?.stop();
    }

    send(): Promise<void> {
        return new Promise((resolve, reject): void => {
            if (!this._stateStore.isConnected || !this._stateStore.isSessionOpen) {
                return reject(TurmsBusinessError.fromCode(TurmsStatusCode.CLIENT_SESSION_HAS_BEEN_CLOSED));
            }
            this._stateStore.websocket.send(HeartbeatService.HEARTBEAT_REQUEST);
            this._heartbeatPromises.push({
                resolve,
                reject
            });
        });
    }

    resolveHeartbeatPromises(): void {
        for (const cb of this._heartbeatPromises) {
            cb.resolve();
        }
        this._heartbeatPromises = [];
    }

    private _rejectHeartbeatPromises(error: TurmsBusinessError): void {
        for (const cb of this._heartbeatPromises) {
            cb.reject(error);
        }
        this._heartbeatPromises = [];
    }

    // Base methods

    close(): Promise<void> {
        this.onDisconnected();
        return Promise.resolve();
    }

    onDisconnected(): void {
        this.stop();
        const error = TurmsBusinessError.from(TurmsStatusCode.CLIENT_SESSION_HAS_BEEN_CLOSED);
        this._rejectHeartbeatPromises(error);
    }

}