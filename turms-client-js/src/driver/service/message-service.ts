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

import RequestUtil from '../../util/request-util';
import TurmsStatusCode from '../../model/turms-status-code';
import TurmsBusinessError from '../../model/turms-business-error';
import StateStore from '../state-store';
import NotificationUtil from '../../util/notification-util';
import {ParsedNotification} from '../../model/parsed-notification';
import BaseService from './base-service';
import {TurmsNotification} from '../../model/proto/notification/turms_notification';
import {TurmsRequest} from '../../model/proto/request/turms_request';

interface RequestPromiseSeal {
    timeoutId?: number,
    resolve: (value?: unknown) => void;
    reject: (reason?: TurmsBusinessError) => void;
}

/**
 * Handle TurmsRequest and TurnsNotification
 */
export default class MessageService extends BaseService {
    private static readonly DEFAULT_REQUEST_TIMEOUT = 60 * 1000;

    private readonly _requestTimeout: number;
    private readonly _minRequestInterval?: number;
    private _notificationListeners: ((notification: ParsedNotification) => void)[] = [];
    private _requestMap: Record<number, RequestPromiseSeal> = {};

    constructor(stateStore: StateStore, requestTimeout?: number, minRequestInterval?: number) {
        super(stateStore);
        this._requestTimeout = isNaN(requestTimeout) || requestTimeout <= 0
            ? MessageService.DEFAULT_REQUEST_TIMEOUT
            : requestTimeout;
        this._minRequestInterval = minRequestInterval;
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
                    return Promise.reject(TurmsBusinessError.fromCode(TurmsStatusCode.CLIENT_SESSION_ALREADY_ESTABLISHED));
                }
            } else if (!this._stateStore.isConnected || !this._stateStore.isSessionOpen) {
                return reject(TurmsBusinessError.from(TurmsStatusCode.CLIENT_SESSION_HAS_BEEN_CLOSED));
            }
            const now = new Date();
            const difference = now.getTime() - this._stateStore.lastRequestDate.getTime();
            const isFrequent = this._minRequestInterval > 0 && difference <= this._minRequestInterval;
            if (isFrequent) {
                return reject(TurmsBusinessError.fromCode(TurmsStatusCode.CLIENT_REQUESTS_TOO_FREQUENT));
            }
            const requestId = RequestUtil.generateRandomId(this._requestMap);
            message.requestId = '' + requestId;

            try {
                const data = TurmsRequest.encode(message).finish();
                this._stateStore.websocket.send(data);
            } catch (e) {
                reject(e);
            }
            this._stateStore.lastRequestDate = now;

            let timeoutId;
            if (this._requestTimeout > 0) {
                timeoutId = setTimeout(() => {
                    delete this._requestMap[requestId];
                    reject(TurmsBusinessError.fromCode(TurmsStatusCode.REQUEST_TIMEOUT));
                }, this._requestTimeout);
            }
            this._requestMap[requestId] = {
                timeoutId,
                resolve,
                reject
            };
        });
    }

    didReceiveNotification(notification: TurmsNotification): void {
        const isResponse = !notification.relayedRequest && notification.requestId;
        if (isResponse) {
            const requestId = parseInt(notification.requestId);
            if (requestId) {
                const cb = this._requestMap[requestId];
                if (cb) {
                    if (cb.timeoutId) {
                        clearTimeout(cb.timeoutId);
                    }
                    delete this._requestMap[requestId];
                    if (notification.code) {
                        if (TurmsStatusCode.isSuccessCode(notification.code)) {
                            cb.resolve(notification);
                        } else {
                            cb.reject(TurmsBusinessError.fromNotification(notification));
                        }
                    } else {
                        cb.reject(TurmsBusinessError.from(TurmsStatusCode.INVALID_NOTIFICATION, 'The code is missing'))
                    }
                }
            }
        }
        const clonedNotification = JSON.parse(JSON.stringify(notification));
        const parsedNotification = NotificationUtil.transform(clonedNotification);
        this._notifyNotificationListeners(parsedNotification as ParsedNotification);
    }

    private _rejectRequestPromises(error: TurmsBusinessError): void {
        Object.values(this._requestMap).forEach(request => request.reject(error));
        this._requestMap = {};
    }

    // Base methods

    close(): Promise<void> {
        this.onDisconnected();
        return Promise.resolve();
    }

    onDisconnected(): void {
        const error = TurmsBusinessError.fromCode(TurmsStatusCode.CLIENT_SESSION_HAS_BEEN_CLOSED);
        this._rejectRequestPromises(error);
    }
}