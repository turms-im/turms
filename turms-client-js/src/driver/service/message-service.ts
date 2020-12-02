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

import {im} from "../../model/proto-bundle";
import RequestUtil from "../../util/request-util";
import TurmsStatusCode from "../../model/turms-status-code";
import TurmsBusinessError from "../../model/turms-business-error";
import StateStore from "../state-store";
import NotificationUtil from "../../util/notification-util";
import {ParsedNotification} from "../../model/parsed-notification";
import TurmsNotification = im.turms.proto.TurmsNotification;
import TurmsRequest = im.turms.proto.TurmsRequest;

interface RequestPromiseSeal {
    timeoutId?: number,
    resolve: (value?: unknown) => void;
    reject: (reason?: any) => void;
}

/**
 * Handle TurmsRequest and TurnsNotification
 */
export default class MessageService {
    private static readonly DEFAULT_REQUEST_TIMEOUT = 60 * 1000;

    private _stateStore: StateStore;

    private _requestTimeout: number;
    private _minRequestInterval?: number;
    private _onNotificationListeners: ((notification: ParsedNotification) => void)[] = [];
    private _requestMap: Record<number, RequestPromiseSeal> = {};

    constructor(stateStore: StateStore, requestTimeout?: number, minRequestInterval?: number) {
        this._stateStore = stateStore;
        if (!requestTimeout && requestTimeout !== 0) {
            this._requestTimeout = MessageService.DEFAULT_REQUEST_TIMEOUT;
        } else {
            this._requestTimeout = requestTimeout;
        }
        this._minRequestInterval = minRequestInterval;
    }

    // Listeners

    addOnNotificationListener(listener: (notification: ParsedNotification) => void): void {
        this._onNotificationListeners.push(listener);
    }

    private _notifyOnNotificationListeners(parsedNotification: ParsedNotification): void {
        for (const listener of this._onNotificationListeners) {
            try {
                listener.call(this, parsedNotification);
            } catch (e) {
                console.error(e);
            }
        }
    }

    // Request and notification

    sendRequest(
        message: im.turms.proto.ITurmsRequest): Promise<TurmsNotification> {
        return new Promise((resolve, reject) => {
            const now = new Date();
            const isFrequent = this._minRequestInterval > 0 && now.getTime() - this._stateStore.lastRequestDate.getTime() <= this._minRequestInterval;
            if (isFrequent) {
                reject(TurmsBusinessError.fromCode(TurmsStatusCode.CLIENT_REQUESTS_TOO_FREQUENT));
            } else {
                const requestId = RequestUtil.generateRandomId(this._requestMap);
                message.requestId = {
                    value: '' + requestId
                };

                const data = TurmsRequest.encode(message).finish();
                this._stateStore.websocket.send(data);
                this._stateStore.lastRequestDate = now;

                let timeoutId;
                if (this._requestTimeout > 0) {
                    timeoutId = setTimeout(() => {
                        delete this._requestMap[requestId];
                        reject(TurmsBusinessError.fromCode(TurmsStatusCode.TIMEOUT));
                    }, this._requestTimeout);
                }
                this._requestMap[requestId] = {
                    timeoutId,
                    resolve,
                    reject
                };
            }
        });
    }

    didReceiveNotification(notification: TurmsNotification): void {
        const isResponse = !notification.relayedRequest && notification.requestId;
        if (isResponse) {
            const requestId = parseInt(notification.requestId.value);
            if (requestId) {
                const cb = this._requestMap[requestId];
                if (cb) {
                    if (cb.timeoutId) {
                        clearTimeout(cb.timeoutId);
                    }
                    delete this._requestMap[requestId];
                    if (notification.code) {
                        if (TurmsStatusCode.isSuccessCode(notification.code.value)) {
                            cb.resolve(notification);
                        } else {
                            cb.reject(TurmsBusinessError.fromNotification(notification));
                        }
                    } else {
                        cb.reject(TurmsBusinessError.fromMessage('Invalid notification: the code is missing'))
                    }
                }
            }
        }
        const notificationCopy = JSON.parse(JSON.stringify(notification));
        const parsedNotification = NotificationUtil.transform(notificationCopy);
        this._notifyOnNotificationListeners(parsedNotification as ParsedNotification);
    }

}