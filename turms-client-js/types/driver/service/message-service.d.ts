import { im } from "../../model/proto-bundle";
import StateStore from "../state-store";
import TurmsNotification = im.turms.proto.TurmsNotification;
export default class MessageService {
    private _stateStore;
    private _requestMap;
    private _requestTimeout?;
    constructor(stateStore: StateStore, requestTimeout?: number);
    sendRequest(message: im.turms.proto.ITurmsRequest, minRequestsInterval: number): Promise<TurmsNotification>;
    triggerOnNotificationReceived(requestId: number, notification: TurmsNotification): void;
}
