import { SessionDisconnectInfo } from "../../model/session-disconnect-info";
import StateStore from "../state-store";
export declare enum SessionStatus {
    CONNECTED = 0,
    DISCONNECTED = 1,
    CLOSED = 2
}
export default class SessionService {
    private _stateStore;
    private _currentStatus;
    private _onSessionConnectedListeners;
    private _onSessionDisconnectedListeners;
    private _onSessionClosedListeners;
    constructor(stateStore: StateStore);
    set sessionId(sessionId: string);
    getStatus(): SessionStatus;
    isConnected(): boolean;
    isClosed(): boolean;
    addOnSessionConnectedListener(listener: () => void): void;
    addOnSessionDisconnectedListener(listener: (disconnectInfo: SessionDisconnectInfo) => void): void;
    addOnSessionClosedListener(listener: (disconnectInfo: SessionDisconnectInfo) => void): void;
    notifyOnSessionConnectedListeners(): void;
    notifyOnSessionDisconnectedListeners(event: CloseEvent, info: SessionDisconnectInfo): void;
    notifyOnSessionClosedListeners(event: CloseEvent, info: SessionDisconnectInfo): void;
    private static _parseDisconnectInfo;
}
