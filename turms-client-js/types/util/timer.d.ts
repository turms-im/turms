export default class Timer {
    private _timerId?;
    private _interval?;
    private readonly _callback;
    private _ignoreNextCall;
    private _isRunning;
    constructor(callback: (...args: any[]) => void, interval: number);
    get isRunning(): boolean;
    stop(): Timer;
    start(): Timer;
}
