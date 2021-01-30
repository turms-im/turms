export default class StateStore {
    private _websocket?;
    private _isSessionOpen;
    private _sessionId?;
    private _serverId?;
    private _lastRequestDate;
    get websocket(): WebSocket;
    set websocket(value: WebSocket);
    get isConnected(): boolean;
    get isSessionOpen(): boolean;
    set isSessionOpen(value: boolean);
    get sessionId(): string;
    set sessionId(value: string);
    get serverId(): string;
    set serverId(value: string);
    get lastRequestDate(): Date;
    set lastRequestDate(value: Date);
    reset(): void;
}
