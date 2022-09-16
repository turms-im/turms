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

import { ParsedNotification } from '../model/parsed-notification';
import SystemUtil from '../util/system-util';
import { TurmsNotification } from '../model/proto/notification/turms_notification';
import { TurmsRequest } from '../model/proto/request/turms_request';
import WebSocketMetrics from '../transport/websocket-metrics';
import StateStore from './state-store';
import MessageService from './service/message-service';
import HeartbeatService from './service/heartbeat-service';
import SharedContextService, { Notification, NotificationType, Request } from './service/shared-context-service';
import ConnectionService, { ConnectOptions, ConnectionDisconnectInfo } from './service/connection-service';

export default class TurmsDriver {

    private readonly _stateStore: StateStore;

    private readonly _connectionService: ConnectionService;
    private readonly _heartbeatService: HeartbeatService;
    private readonly _messageService: MessageService;
    private readonly _sharedContextService?: SharedContextService;

    constructor(wsUrl?: string,
                connectTimeout?: number,
                requestTimeout?: number,
                minRequestInterval?: number,
                heartbeatInterval?: number,
                useSharedContext?: boolean) {
        if (useSharedContext) {
            this._sharedContextService = new SharedContextService(this._stateStore);
        }
        this._stateStore = new StateStore(this._sharedContextService);
        this._connectionService = this.initConnectionService(wsUrl, connectTimeout);
        this._heartbeatService = new HeartbeatService(this._stateStore, heartbeatInterval);
        this._messageService = new MessageService(this._stateStore, requestTimeout, minRequestInterval);
        if (SystemUtil.isBrowser()) {
            window.addEventListener('beforeunload', () => this.close());
        }
    }

    // Getters

    stateStore(): StateStore {
        return this._stateStore;
    }

    // Initializers and close

    initConnectionService(wsUrl?: string, connectTimeout?: number): ConnectionService {
        const connectionService = new ConnectionService(this._stateStore, wsUrl, connectTimeout);
        connectionService.addOnDisconnectedListener(() => this._onConnectionDisconnected());
        connectionService.addMessageListener(message => this._onMessage(message));
        return connectionService;
    }

    close(): Promise<void> {
        return Promise.allSettled([
            this._connectionService.close(),
            this._heartbeatService.close(),
            this._messageService.close()]
        ).then(() => null);
    }

    // Shared Context

    addSharedContextNotificationListener(type: NotificationType, listener: ((notification: Notification) => void)): void {
        this._sharedContextService?.addNotificationListener(type, listener);
    }

    requestSharedContext(request: Request): Promise<any> {
        if (!this._sharedContextService) {
            return Promise.reject(new Error('The shared context is disabled'));
        }
        return this._sharedContextService.request(request);
    }

    // Heartbeat Service

    startHeartbeat(): void {
        this._heartbeatService.start();
    }

    stopHeartbeat(): void {
        this._heartbeatService.stop();
    }

    sendHeartbeat(): Promise<void> {
        return this._heartbeatService.send();
    }

    get isHeartbeatRunning(): boolean {
        return this._heartbeatService.isRunning;
    }

    // Connection Service

    connect(options: ConnectOptions = {}): Promise<void> {
        return this._connectionService.connect(options);
    }

    disconnect(): Promise<void> {
        return this._connectionService.disconnect();
    }

    get isConnected(): boolean {
        return this._stateStore.isConnected;
    }

    get connectionMetrics(): WebSocketMetrics | undefined {
        return this._stateStore.websocket?.metrics;
    }

    // Connection Listeners

    addOnConnectedListener(listener: () => void): void {
        this._connectionService.addOnConnectedListener(listener);
    }

    addOnDisconnectedListener(listener: (disconnectInfo: ConnectionDisconnectInfo) => void): void {
        this._connectionService.addOnDisconnectedListener(listener);
    }

    removeOnConnectedListener(listener: () => void): void {
        this._connectionService.removeOnConnectedListener(listener);
    }

    removeOnDisconnectedListener(listener: (disconnectInfo: ConnectionDisconnectInfo) => void): void {
        this._connectionService.removeOnDisconnectedListener(listener);
    }

    // Message Service

    send(message: TurmsRequest): Promise<TurmsNotification> {
        const notification = this._messageService.sendRequest(message);
        if (message.createSessionRequest) {
            notification.then(() => {
                this._heartbeatService.start();
            });
        }
        return notification;
    }

    addNotificationListener(listener: ((notification: ParsedNotification) => void)): void {
        this._messageService.addNotificationListener(listener);
    }

    removeNotificationListener(listener: ((notification: ParsedNotification) => void)): void {
        this._messageService.removeNotificationListener(listener);
    }

    // Intermediary functions as a mediator between services

    private _onConnectionDisconnected(): void {
        this._stateStore.reset();
        this._heartbeatService.onDisconnected();
        this._messageService.onDisconnected();
    }

    private _onMessage(message: ArrayBuffer): void {
        if (message.byteLength) {
            let notification: TurmsNotification;
            try {
                notification = TurmsNotification.decode(new Uint8Array(message));
            } catch (e) {
                console.error('Failed to parse TurmsNotification', e);
                return;
            }
            if (this._heartbeatService.rejectHeartbeatPromisesIfFail(notification)) {
                return;
            }
            const session = notification.data?.userSession;
            if (session) {
                this._stateStore.sessionId = session.sessionId;
                this._stateStore.serverId = session.serverId;
            } else if (notification.closeStatus) {
                this._stateStore.isSessionOpen = false;
            }
            this._messageService.didReceiveNotification(notification);
        } else {
            this._heartbeatService.resolveHeartbeatPromises();
        }
    }

}
