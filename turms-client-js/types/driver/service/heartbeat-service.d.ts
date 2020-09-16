import StateStore from "../state-store";
export default class HeartbeatService {
    private static readonly DEFAULT_HEARTBEAT_INTERVAL;
    private static readonly HEARTBEAT_REQUEST;
    private _stateStore;
    private _heartbeatInterval;
    private _minRequestInterval;
    private _heartbeatTimer?;
    private _heartbeatPromises;
    constructor(stateStore: StateStore, minRequestInterval: number, heartbeatInterval?: number);
    start(): void;
    stop(): void;
    reset(): void;
    send(): Promise<void>;
    resolveHeartbeatPromises(): void;
    rejectHeartbeatPromises(error: any): void;
}
