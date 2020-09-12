export default class Timer {
    private _timerId?: number;
    private _interval?: number;
    private _callback: (...args: any[]) => void;
    private _lightMode: boolean;
    private _ignoreNextCall: boolean;
    private _isRunning: boolean;

    constructor(callback: (...args: any[]) => void, interval: number, lightMode = true) {
        this._callback = (): void => {
            if (!this._ignoreNextCall) {
                callback();
            } else {
                this._ignoreNextCall = false;
            }
        };
        this._interval = interval;
        this._lightMode = lightMode;
        this._ignoreNextCall = false;
        this._isRunning = false;
    }

    get isRunning(): boolean {
        return this._isRunning;
    }

    stop(): Timer {
        if (this._timerId) {
            clearInterval(this._timerId);
        }
        this._isRunning = false;
        this._timerId = undefined;
        return this;
    }

    start(): Timer {
        if (!this._timerId) {
            this._timerId = window.setInterval(this._callback, this._interval);
        }
        this._isRunning = true;
        return this;
    }

    reset(interval: number): Timer {
        if (this._lightMode && this._interval === interval) {
            this._ignoreNextCall = true;
            return this;
        } else {
            this._interval = interval;
            return this.stop().start();
        }
    }
}
