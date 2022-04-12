export default class Timer {
    private _timerId?: number | NodeJS.Timer;
    private readonly _interval?: number;
    private readonly _callback: (...args: any[]) => void;
    private _ignoreNextCall: boolean;
    private _isRunning: boolean;

    constructor(callback: (...args: any[]) => void, interval: number) {
        this._callback = (): void => {
            if (!this._ignoreNextCall) {
                callback();
            } else {
                this._ignoreNextCall = false;
            }
        };
        this._interval = interval;
        this._ignoreNextCall = false;
        this._isRunning = false;
    }

    get isRunning(): boolean {
        return this._isRunning;
    }

    stop(): Timer {
        if (this._timerId) {
            clearInterval(this._timerId as any);
        }
        this._isRunning = false;
        this._timerId = null;
        return this;
    }

    start(): Timer {
        if (!this._timerId) {
            this._timerId = setInterval(this._callback, this._interval);
        }
        this._isRunning = true;
        return this;
    }

}
