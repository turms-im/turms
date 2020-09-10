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
export interface ConnectionDisconnectInfo {
    wasConnected: boolean;
    isClosedByClient: boolean;
    event: CloseEvent;
}
export default class ConnectionService {
    private _stateStore;
    private _isClosedByClient;
    private _disconnectionCallbacks;
    private _connectOptions;
    private _onConnectedListeners;
    private _onDisconnectedListeners;
    private _onMessageListeners;
    constructor(stateStore: StateStore);
    private _resetStates;
    addOnConnectedListener(listener: () => void): void;
    addOnDisconnectedListener(listener: (info: ConnectionDisconnectInfo) => Promise<void>): void;
    addOnMessageListener(listener: (message: any) => void): void;
    private _notifyOnConnectedListener;
    private _notifyOnDisconnectedListeners;
    private _notifyOnMessageListener;
    private _triggerDisconnectCallbacks;
    connect(options: ConnectOptions): Promise<void>;
    disconnect(): Promise<void>;
    reconnect(host?: string): Promise<void>;
    private static _fillLoginInfo;
    private static _clearLoginInfo;
    private _onWebSocketOpen;
    private _onWebSocketClose;
}
