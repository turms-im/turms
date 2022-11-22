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

import NotificationUtil from '../../util/notification-util';
import { ParsedNotification } from '../../model/parsed-notification';
import ResponseError from '../../error/response-error';
import ResponseStatusCode from '../../model/response-status-code';
import StateStore from '../state-store';
import { TurmsNotification } from '../../model/proto/notification/turms_notification';
import { TurmsRequest } from '../../model/proto/request/turms_request';
import BaseService from './base-service';

interface RequestPromiseSeal {
    timeoutId?: number,
    resolve: (value?: TurmsNotification) => void;
    reject: (reason?: ResponseError) => void;
}

/**
 * Handle TurmsRequest and TurnsNotification
 */
export default class MessageService extends BaseService {

    private readonly _requestTimeout: number;
    private readonly _minRequestInterval: number;
    private _notificationListeners: ((notification: ParsedNotification) => void)[] = [];
    private _idToRequest: Record<number, RequestPromiseSeal> = {};

    constructor(stateStore: StateStore, requestTimeout?: number, minRequestInterval?: number) {
        super(stateStore);
        this._requestTimeout = isNaN(requestTimeout) || requestTimeout <= 0
            ? 60 * 1000
            : requestTimeout;
        this._minRequestInterval = minRequestInterval || 0;
    }

    // Listeners

    addNotificationListener(listener: (notification: ParsedNotification) => void): void {
        this._notificationListeners.push(listener);
    }

    removeNotificationListener(listener: (notification: ParsedNotification) => void): void {
        this._notificationListeners = this._notificationListeners
            .filter(cur => cur !== listener);
    }

    private _notifyNotificationListeners(parsedNotification: ParsedNotification): void {
        for (const listener of this._notificationListeners) {
            try {
                listener.call(this, parsedNotification);
            } catch (e) {
                console.error(e);
            }
        }
    }

    // Request and notification

    sendRequest(
        message: TurmsRequest): Promise<TurmsNotification> {
        return new Promise((resolve, reject) => {
            if (message.createSessionRequest) {
                if (this._stateStore.isSessionOpen) {
                    return reject(ResponseError.from({
                        code: ResponseStatusCode.CLIENT_SESSION_ALREADY_ESTABLISHED
                    }));
                }
            } else if (!this._stateStore.isConnected || !this._stateStore.isSessionOpen) {
                return reject(ResponseError.from({
                    code: ResponseStatusCode.CLIENT_SESSION_HAS_BEEN_CLOSED
                }));
            }
            const now = Date.now();
            const difference = now - this._stateStore.lastRequestDate;
            const isFrequent = this._minRequestInterval > 0 && difference <= this._minRequestInterval;
            if (isFrequent) {
                return reject(ResponseError.from({
                    code: ResponseStatusCode.CLIENT_REQUESTS_TOO_FREQUENT
                }));
            }
            const requestId = this._generateRandomId();
            message.requestId = '' + requestId;
            let data;
            try {
                data = TurmsRequest.encode(message).finish();
            } catch (e) {
                return reject(e);
            }
            this._stateStore.websocket.send(data)
                .then(() => {
                    this._stateStore.lastRequestDate = now;
                    let timeoutId;
                    if (this._requestTimeout > 0) {
                        timeoutId = setTimeout(() => {
                            delete this._idToRequest[requestId];
                            reject(ResponseError.from({
                                code: ResponseStatusCode.REQUEST_TIMEOUT
                            }));
                        }, this._requestTimeout);
                    }
                    this._idToRequest[requestId] = {
                        timeoutId,
                        resolve,
                        reject
                    };
                })
                .catch(e => reject(e));
        });
    }

    didReceiveNotification(notification: TurmsNotification): void {
        const isResponse = !notification.relayedRequest && notification.requestId;
        if (isResponse) {
            const requestId = parseInt(notification.requestId);
            if (requestId) {
                const cb = this._idToRequest[requestId];
                if (cb) {
                    if (cb.timeoutId) {
                        clearTimeout(cb.timeoutId);
                    }
                    delete this._idToRequest[requestId];
                    if (notification.code) {
                        if (ResponseStatusCode.isSuccessCode(notification.code)) {
                            cb.resolve(notification);
                        } else {
                            cb.reject(ResponseError.fromNotification(notification));
                        }
                    } else {
                        cb.reject(ResponseError.from({
                            code: ResponseStatusCode.INVALID_NOTIFICATION,
                            reason: 'The code is missing'
                        }));
                    }
                }
            }
        }
        const clonedNotification = JSON.parse(JSON.stringify(notification));
        const parsedNotification = NotificationUtil.transform(clonedNotification);
        this._notifyNotificationListeners(parsedNotification as ParsedNotification);
    }

    private _generateRandomId(): number {
        let id;
        do {
            id = 1 + Math.floor(Math.random() * 9007199254740991);
        } while (this._idToRequest[id]);
        return id;
    }

    private _rejectRequestPromises(error: ResponseError): void {
        Object.values(this._idToRequest).forEach(request => request.reject(error));
        this._idToRequest = {};
    }

    // Base methods

    override close(): Promise<void> {
        this.onDisconnected();
        return Promise.resolve();
    }

    override onDisconnected(error?: Error): void {
        const e = ResponseError.from({
            code: ResponseStatusCode.CLIENT_SESSION_HAS_BEEN_CLOSED,
            cause: error
        });
        this._rejectRequestPromises(e);
    }
}