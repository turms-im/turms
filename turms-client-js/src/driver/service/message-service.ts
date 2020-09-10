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
import TurmsBusinessException from "../../model/turms-business-exception";
import StateStore from "../state-store";
import TurmsNotification = im.turms.proto.TurmsNotification;
import TurmsRequest = im.turms.proto.TurmsRequest;

/**
 * Handle TurmsRequest and TurnsNotification
 */

interface RequestCallback {
    timeoutId?: number,
    resolve: (value?: unknown) => void;
    reject: (reason?: any) => void;
}

export default class MessageService {

    private _stateStore: StateStore;

    private _requestMap: Record<number, RequestCallback> = {};
    private _requestTimeout?: number;

    constructor(stateStore: StateStore, requestTimeout?: number) {
        this._stateStore = stateStore;
        this._requestTimeout = requestTimeout;
    }

    sendRequest(
        message: im.turms.proto.ITurmsRequest,
        minRequestsInterval: number): Promise<TurmsNotification> {
        const now = new Date();
        const isFrequent = minRequestsInterval > 0 && now.getTime() - this._stateStore.lastRequestDate.getTime() > minRequestsInterval;
        if (isFrequent) {
            throw TurmsBusinessException.fromCode(TurmsStatusCode.CLIENT_REQUESTS_TOO_FREQUENT);
        } else {
            const requestId = RequestUtil.generateRandomId(this._requestMap);
            message.requestId = {
                value: '' + requestId
            };

            const data = TurmsRequest.encode(message).finish();
            this._stateStore.websocket.send(data);
            this._stateStore.lastRequestDate = now;

            return new Promise((resolve, reject) => {
                let timeoutId;
                if (this._requestTimeout && this._requestTimeout > 0) {
                    timeoutId = setTimeout(() => {
                        delete this._requestMap[requestId];
                        reject(new Error('Request timeout'));
                    }, this._requestTimeout);
                }
                this._requestMap[requestId] = {
                    timeoutId,
                    resolve,
                    reject
                };
            });
        }
    }

    triggerOnNotificationReceived(requestId: number, notification: TurmsNotification): void {
        const cb = this._requestMap[requestId];
        if (cb) {
            if (cb.timeoutId) {
                clearTimeout(cb.timeoutId);
            }
            clearTimeout()
            delete this._requestMap[requestId];
            if (notification.code && TurmsStatusCode.isSuccessCode(notification.code.value)) {
                cb.resolve(notification);
            } else {
                cb.reject(TurmsBusinessException.fromNotification(notification));
            }
        }
    }

}