import { im } from "../../model/proto-bundle";
import StateStore from "../state-store";
import { ParsedNotification } from "../../model/parsed-notification";
import TurmsNotification = im.turms.proto.TurmsNotification;
export default class MessageService {
    private _stateStore;
    private _minRequestsInterval?;
    private _onNotificationListeners;
    private _requestMap;
    private _requestTimeout?;
    constructor(stateStore: StateStore, requestTimeout?: number, minRequestsInterval?: number);
    addOnNotificationListener(listener: (notification: ParsedNotification) => void): void;
    private _notifyOnNotificationListener;
    sendRequest(message: im.turms.proto.ITurmsRequest): Promise<TurmsNotification>;
    triggerOnNotificationReceived(notification: TurmsNotification): void;
}
