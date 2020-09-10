import StateStore from "../state-store";
export default class HeartbeatService {
    private static HEARTBEAT_REQUEST;
    private _stateStore;
    private _heartbeatInterval;
    private _minRequestsInterval;
    private _heartbeatTimer?;
    private _heartbeatCallbacks;
    constructor(stateStore: StateStore, heartbeatInterval: number, minRequestsInterval: number);
    start(): void;
    stop(): void;
    reset(): void;
    send(): Promise<void>;
    notifyHeartbeatCallbacks(): void;
}
