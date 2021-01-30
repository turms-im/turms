import StateStore from '../state-store';
import BaseService from './base-service';
export default class HeartbeatService extends BaseService {
    private static readonly DEFAULT_HEARTBEAT_INTERVAL;
    private static readonly HEARTBEAT_REQUEST;
    private readonly _heartbeatInterval;
    private readonly _heartbeatTimerInterval;
    private _lastHeartbeatRequestDate;
    private _heartbeatTimer?;
    private _heartbeatPromises;
    constructor(stateStore: StateStore, heartbeatInterval?: number);
    get isRunning(): boolean;
    start(): void;
    stop(): void;
    send(): Promise<void>;
    resolveHeartbeatPromises(): void;
    private _rejectHeartbeatPromises;
    close(): Promise<void>;
    onDisconnected(): void;
}
