import StateStore from '../state-store';
export default abstract class BaseService {
    protected readonly _stateStore: StateStore;
    protected constructor(stateStore: StateStore);
    abstract close(): Promise<void>;
    abstract onDisconnected(): void;
}
