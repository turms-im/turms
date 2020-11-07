import UserLocation from "../../model/user-location";
import { im } from "../../model/proto-bundle";
import StateStore from "../state-store";
import DeviceType = im.turms.proto.DeviceType;
import UserStatus = im.turms.proto.UserStatus;
export interface ConnectOptions {
    wsUrl?: string;
    connectTimeout?: number;
    userId: string;
    password: string;
    deviceType?: DeviceType;
    userOnlineStatus?: UserStatus;
    location?: UserLocation;
}
export interface ConnectionDisconnectInfo {
    wasConnected: boolean;
    isClosedByClient: boolean;
    event: CloseEvent;
}
export default class ConnectionService {
    private static readonly DEFAULT_WEBSOCKET_URL;
    private static readonly DEFAULT_CONNECT_TIMEOUT;
    private _stateStore;
    private readonly _initialWsUrl;
    private readonly _initialConnectTimeout;
    private readonly _storePassword;
    private _isClosedByClient;
    private _disconnectPromises;
    private _connectOptions;
    private _onConnectedListeners;
    private _onDisconnectedListeners;
    private _onMessageListeners;
    constructor(stateStore: StateStore, wsUrl?: string, connectTimeout?: number, storePassword?: boolean);
    private _resetStates;
    addOnConnectedListener(listener: () => void): void;
    addOnDisconnectedListener(listener: (info: ConnectionDisconnectInfo) => Promise<void>): void;
    addOnMessageListener(listener: (message: any) => void): void;
    private _notifyOnConnectedListener;
    private _notifyOnDisconnectedListeners;
    private _notifyOnMessageListeners;
    private _resolveDisconnectPromises;
    connect(options: ConnectOptions): Promise<void>;
    disconnect(): Promise<void>;
    reconnect(host?: string): Promise<void>;
    private static _fillLoginInfo;
    private static _clearLoginInfo;
    private _onWebSocketOpen;
    private _onWebSocketClose;
}
