export default class Timer {
    private _timerId?;
    private _interval?;
    private _callback;
    private _lightMode;
    private _ignoreNextCall;
    private _isRunning;
    constructor(callback: (...args: any[]) => void, interval: number, lightMode?: boolean);
    get isRunning(): boolean;
    stop(): Timer;
    start(): Timer;
    reset(interval: number): Timer;
}
