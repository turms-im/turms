import UserLocation from "../../model/user-location";
import { im } from "../../model/proto-bundle";
import StateStore from "../state-store";
import DeviceType = im.turms.proto.DeviceType;
import UserStatus = im.turms.proto.UserStatus;
export interface ConnectOptions {
    wsUrl: string;
    connectTimeout: number;
    userId: string;
    password: string;
    deviceType?: DeviceType;
    userOnlineStatus?: UserStatus;
    location?: UserLocation;
}
export interface DisconnectionInfo {
    wasConnected: boolean;
    isClosedByClient: boolean;
    event: CloseEvent;
}
export default class ConnectionService {
    private _stateStore;
    private _isClosedByClient;
    private _wasConnected;
    private _disconnectionCallbacks;
    private _connectOptions;
    private _connectionListeners;
    private _disconnectionListeners;
    private _messageListeners;
    constructor(stateStore: StateStore);
    private _resetStates;
    addConnectionListener(cb: () => void): void;
    addDisconnectionListener(cb: (info: DisconnectionInfo) => Promise<void>): void;
    addMessageListener(cb: (message: any) => void): void;
    private _notifyConnectionListener;
    private _notifyDisconnectionListeners;
    private _notifyMessageListener;
    private _triggerDisconnectCallbacks;
    connect(options: ConnectOptions): Promise<void>;
    disconnect(): Promise<void>;
    reconnect(host?: string): Promise<void>;
    private static _fillLoginInfo;
    private static _clearLoginInfo;
    private _onWebsocketOpen;
    private _onWebsocketClose;
}
