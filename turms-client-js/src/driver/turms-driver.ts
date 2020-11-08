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

import TurmsStatusCode from "../model/turms-status-code";
import TurmsBusinessError from "../model/turms-business-error";
import {im} from "../model/proto-bundle";
import {ParsedNotification} from "../model/parsed-notification";
import UserLocation from "../model/user-location";
import {SessionDisconnectInfo} from "../model/session-disconnect-info";
import ReasonService from "./service/reason-service";
import MessageService from "./service/message-service";
import HeartbeatService from "./service/heartbeat-service";
import ConnectionService, {ConnectionDisconnectInfo} from "./service/connection-service";
import SessionService, {SessionStatus} from "./service/session-service";
import StateStore from "./state-store";
import TurmsCloseStatus from "../model/turms-close-status";
import TurmsNotification = im.turms.proto.TurmsNotification;
import UserStatus = im.turms.proto.UserStatus;
import DeviceType = im.turms.proto.DeviceType;

export default class TurmsDriver {

    private _queryReasonWhenLoginFailed = true;
    private _queryReasonWhenDisconnected = true;

    private _onSessionConnected: () => void;
    private _onSessionDisconnected: (disconnectInfo: SessionDisconnectInfo) => void;
    private _onSessionClosed: (disconnectInfo: SessionDisconnectInfo) => void;

    private _stateStore: StateStore;

    private _connectionService: ConnectionService;
    private _heartbeatService: HeartbeatService;
    private _messageService: MessageService;
    private _reasonService: ReasonService;
    private _sessionService: SessionService;

    constructor(wsUrl?: string,
                connectTimeout?: number,
                requestTimeout?: number,
                minRequestInterval?: number,
                heartbeatInterval?: number,
                httpUrl?: string,
                queryReasonWhenLoginFailed = true,
                queryReasonWhenDisconnected = true,
                storePassword?: boolean) {
        this._queryReasonWhenLoginFailed = queryReasonWhenLoginFailed;
        this._queryReasonWhenDisconnected = queryReasonWhenDisconnected;

        this._stateStore = new StateStore();

        this._connectionService = this.initConnectionService(wsUrl, connectTimeout, storePassword);
        this._heartbeatService = new HeartbeatService(this._stateStore, minRequestInterval, heartbeatInterval);
        this._messageService = new MessageService(this._stateStore, requestTimeout, minRequestInterval);
        this._reasonService = new ReasonService(this._stateStore, httpUrl);
        this._sessionService = this.initSessionService();
    }

    // Initializers
    initConnectionService(wsUrl?: string, connectTimeout?: number, storePassword?: boolean): ConnectionService {
        const connectionService = new ConnectionService(this._stateStore, wsUrl, connectTimeout, storePassword);
        connectionService.addOnConnectedListener(() => this._onConnectionConnected());
        connectionService.addOnDisconnectedListener(info => this._onConnectionDisconnected(info));
        connectionService.addOnMessageListener(message => this._onMessage(message))
        return connectionService;
    }

    initSessionService(): SessionService {
        const sessionService = new SessionService(this._stateStore);
        sessionService.addOnSessionConnectedListener(() => {
            if (this._onSessionConnected) {
                this._onSessionConnected();
            }
        });
        sessionService.addOnSessionDisconnectedListener(disconnectInfo => {
            if (this._onSessionDisconnected) {
                this._onSessionDisconnected(disconnectInfo);
            }
        });
        sessionService.addOnSessionClosedListener(disconnectInfo => {
            if (this._onSessionClosed) {
                this._onSessionClosed(disconnectInfo);
            }
        })
        return sessionService;
    }

    // Session Service

    getStatus(): SessionStatus {
        return this._sessionService.getStatus();
    }

    isConnected(): boolean {
        return this._sessionService.isConnected();
    }

    isClosed(): boolean {
        return this._sessionService.isClosed();
    }

    set onSessionConnected(listener: () => void) {
        this._onSessionConnected = listener;
    }

    set onSessionDisconnected(listener: (disconnectInfo: SessionDisconnectInfo) => void) {
        this._onSessionDisconnected = listener;
    }

    set onSessionClosed(listener: (disconnectInfo: SessionDisconnectInfo) => void) {
        this._onSessionClosed = listener;
    }

    // Heartbeat Service

    startHeartbeat(): void {
        this._heartbeatService.start();
    }

    stopHeartbeat(): void {
        this._heartbeatService.stop();
    }

    resetHeartbeat(): void {
        this._heartbeatService.reset();
    }

    sendHeartbeat(): Promise<void> {
        return this._heartbeatService.send();
    }

    // Connection Service

    connect(
        userId: string,
        password: string,
        deviceType?: DeviceType,
        userOnlineStatus?: UserStatus,
        location?: UserLocation): Promise<void> {
        return this._connectionService.connect({
            userId,
            password,
            deviceType,
            userOnlineStatus,
            location
        });
    }

    reconnect(host?: string): Promise<void> {
        return this._connectionService.reconnect(host);
    }

    disconnect(): Promise<void> {
        return this._connectionService.disconnect();
    }

    // Message Service

    send(message: im.turms.proto.ITurmsRequest): Promise<TurmsNotification> {
        if (this._stateStore.isConnected) {
            try {
                const notification = this._messageService.sendRequest(message);
                this.resetHeartbeat();
                return notification;
            } catch (e) {
                return Promise.reject(e);
            }
        } else {
            return Promise.reject(TurmsBusinessError.fromCode(TurmsStatusCode.CLIENT_SESSION_HAS_BEEN_CLOSED));
        }
    }

    addOnNotificationListener(listener: ((notification: ParsedNotification) => void)): void {
        this._messageService.addOnNotificationListener(listener);
    }

    // Intermediary functions as a mediator between services

    private _onConnectionConnected(): void {
        this._heartbeatService.start();
        this._sessionService.notifyOnSessionConnectedListeners();
    }

    private _onConnectionDisconnected(info): Promise<void> {
        this._heartbeatService.stop();
        const error = TurmsBusinessError.fromCode(TurmsStatusCode.CLIENT_SESSION_HAS_BEEN_CLOSED);
        this._heartbeatService.rejectHeartbeatPromises(error);
        const isDisconnectOnLogin = !info.wasConnected;
        if (isDisconnectOnLogin) {
            return this._triggerOnLoginFailure();
        } else {
            this._triggerOnSessionDisconnected(info);
            return Promise.reject();
        }
    }

    private _triggerOnLoginFailure(): Promise<void> {
        if (this._queryReasonWhenLoginFailed) {
            return this._reasonService.queryLoginFailureReason()
                .then(reason => {
                    if (reason.statusCode == 307) {
                        return this.reconnect(reason.reason);
                    } else {
                        const desc = reason.reason
                            ? `${reason.statusCode} - ${reason.reason}`
                            : reason.statusCode;
                        throw new Error(`Failed to login: ${desc}`);
                    }
                });
        } else {
            return Promise.reject(new Error(`Failed to login due to 1. user doesn't exist or is inactive; 2. password mismatch; 3. the server doesn't exist or is unavailable`));
        }
    }

    private _triggerOnSessionDisconnected(info: ConnectionDisconnectInfo): void {
        const event = info.event;
        const requiredInfo: SessionDisconnectInfo = {
            wasConnected: info.wasConnected,
            isClosedByClient: info.isClosedByClient,
            isReconnecting: false
        };
        if (info.isClosedByClient) {
            this._sessionService.notifyOnSessionClosedListeners(event, {
                ...requiredInfo,
                closeStatus: TurmsCloseStatus.DISCONNECTED_BY_CLIENT
            });
        } else {
            if (this._queryReasonWhenDisconnected) {
                this._reasonService.queryDisconnectionReason()
                    .then(reason => {
                        if (reason.closeCode == TurmsCloseStatus.REDIRECT) {
                            this._sessionService.notifyOnSessionDisconnectedListeners(event, {
                                ...requiredInfo,
                                isReconnecting: true,
                                closeStatus: reason.closeCode
                            })
                            this.reconnect(reason.reason).then(() => null);
                        } else {
                            this._sessionService.notifyOnSessionClosedListeners(event, {
                                ...requiredInfo,
                                closeStatus: reason.closeCode
                            });
                        }
                    })
                    .catch(error => {
                        this._sessionService.notifyOnSessionClosedListeners(event, {
                            ...requiredInfo,
                            error
                        });
                    });
            } else {
                this._sessionService.notifyOnSessionClosedListeners(event, requiredInfo);
            }
        }
    }

    private _onMessage(message): void {
        if (message && message.byteLength) {
            let notification;
            try {
                notification = TurmsNotification.decode(new Uint8Array(message));
            } catch (e) {
                console.error('Failed to parse TurmsNotification', e);
                return;
            }
            const isSessionInfo = notification.data && notification.data.session;
            if (isSessionInfo) {
                this._sessionService.sessionId = notification.data.session.sessionId;
            }
            this._messageService.didReceiveNotification(notification);
        } else {
            this._heartbeatService.resolveHeartbeatPromises();
        }
    }

}
