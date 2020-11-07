import { LoginFailureReason } from "../../model/login-failure-reason";
import { SessionDisconnectionReason } from "../../model/session-disconnection-reason";
import StateStore from "../state-store";
export default class ReasonService {
    private static readonly DEFAULT_HTTP_URL;
    private _stateStore;
    private _url;
    constructor(stateStore: StateStore, url?: string);
    queryLoginFailureReason(): Promise<LoginFailureReason>;
    queryDisconnectionReason(): Promise<SessionDisconnectionReason>;
}
