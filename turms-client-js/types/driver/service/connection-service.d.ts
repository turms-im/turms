import StateStore from '../state-store';
import BaseService from './base-service';
export interface ConnectOptions {
    wsUrl?: string;
    connectTimeout?: number;
}
export interface ConnectionDisconnectInfo {
    wasConnected: boolean;
    wsUrl: string;
    code: number;
    reason: string;
}
export default class ConnectionService extends BaseService {
    private static readonly WS_STATUS_CODE_GOING_AWAY;
    private readonly _initialWsUrl;
    private readonly _initialConnectTimeout;
    private _disconnectPromises;
    private _onConnectedListeners;
    private _onDisconnectedListeners;
    private _messageListeners;
    constructor(stateStore: StateStore, wsUrl?: string, connectTimeout?: number);
    private _closeConnectionBeforeUnload;
    private _resetStates;
    addOnConnectedListener(listener: () => void): void;
    addOnDisconnectedListener(listener: (info: ConnectionDisconnectInfo) => void): void;
    addMessageListener(listener: (message: ArrayBuffer) => void): void;
    removeOnConnectedListener(listener: () => void): void;
    removeOnDisconnectedListener(listener: (info: ConnectionDisconnectInfo) => void): void;
    removeMessageListener(listener: (message: ArrayBuffer) => void): void;
    private _notifyOnConnectedListener;
    private _notifyOnDisconnectedListeners;
    private _notifyOnMessageListeners;
    private _resolveDisconnectPromises;
    connect({ wsUrl, connectTimeout }?: ConnectOptions): Promise<void>;
    disconnect(): Promise<void>;
    private _onWebSocketOpen;
    private _onWebSocketClose;
    close(): Promise<void>;
    onDisconnected(): void;
}
