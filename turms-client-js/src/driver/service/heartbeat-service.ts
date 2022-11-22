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

import PromiseSeal from '../../model/promise-seal';
import ResponseError from '../../error/response-error';
import ResponseStatusCode from '../../model/response-status-code';
import StateStore from '../state-store';
import Timer from '../../util/timer';
import { TurmsNotification } from '../../model/proto/notification/turms_notification';
import BaseService from './base-service';

export default class HeartbeatService extends BaseService {

    private static readonly DEFAULT_HEARTBEAT_INTERVAL = 120 * 1000;
    private static readonly HEARTBEAT_FAILURE_REQUEST_ID = -100;
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
        if (this.isRunning) {
            return;
        }
        this._heartbeatTimer = new Timer((): void => {
            const now = Date.now();
            const difference = Math.min(
                now - this._stateStore.lastRequestDate,
                now - this._lastHeartbeatRequestDate);
            if (difference > this._heartbeatInterval) {
                this.send().then(() => null);
                this._lastHeartbeatRequestDate = now;
            }
        }, this._heartbeatTimerInterval);
        this._heartbeatTimer.start();
    }

    stop(): void {
        this._heartbeatTimer?.stop();
    }

    send(): Promise<void> {
        return new Promise((resolve, reject): void => {
            if (!this._stateStore.isConnected || !this._stateStore.isSessionOpen) {
                return reject(ResponseError.from({
                    code: ResponseStatusCode.CLIENT_SESSION_HAS_BEEN_CLOSED
                }));
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

    rejectHeartbeatPromisesIfFail(notification: TurmsNotification): boolean {
        if (HeartbeatService.HEARTBEAT_FAILURE_REQUEST_ID === parseInt(notification.requestId)) {
            this._rejectHeartbeatPromises(ResponseError.fromNotification(notification));
            return true;
        }
    }

    private _rejectHeartbeatPromises(error: ResponseError): void {
        for (const cb of this._heartbeatPromises) {
            cb.reject(error);
        }
        this._heartbeatPromises = [];
    }

    // Base methods

    override close(): Promise<void> {
        this.onDisconnected();
        return Promise.resolve();
    }

    override onDisconnected(error?: Error): void {
        this.stop();
        const e = ResponseError.from({
            code: ResponseStatusCode.CLIENT_SESSION_HAS_BEEN_CLOSED,
            cause: error
        });
        this._rejectHeartbeatPromises(e);
    }
}