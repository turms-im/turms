import { im } from "../../model/proto-bundle";
import StateStore from "../state-store";
import { ParsedNotification } from "../../model/parsed-notification";
import TurmsNotification = im.turms.proto.TurmsNotification;
export default class MessageService {
    private static readonly DEFAULT_REQUEST_TIMEOUT;
    private _stateStore;
    private _requestTimeout;
    private _minRequestInterval?;
    private _onNotificationListeners;
    private _requestMap;
    constructor(stateStore: StateStore, requestTimeout?: number, minRequestInterval?: number);
    addOnNotificationListener(listener: (notification: ParsedNotification) => void): void;
    private _notifyOnNotificationListeners;
    sendRequest(message: im.turms.proto.ITurmsRequest): Promise<TurmsNotification>;
    didReceiveNotification(notification: TurmsNotification): void;
}
