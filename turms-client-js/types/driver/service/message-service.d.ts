import { im } from '../../model/proto-bundle';
import StateStore from '../state-store';
import { ParsedNotification } from '../../model/parsed-notification';
import BaseService from './base-service';
import TurmsNotification = im.turms.proto.TurmsNotification;
export default class MessageService extends BaseService {
    private static readonly DEFAULT_REQUEST_TIMEOUT;
    private readonly _requestTimeout;
    private readonly _minRequestInterval?;
    private _notificationListeners;
    private _requestMap;
    constructor(stateStore: StateStore, requestTimeout?: number, minRequestInterval?: number);
    addNotificationListener(listener: (notification: ParsedNotification) => void): void;
    removeNotificationListener(listener: (notification: ParsedNotification) => void): void;
    private _notifyNotificationListeners;
    sendRequest(message: im.turms.proto.ITurmsRequest): Promise<TurmsNotification>;
    didReceiveNotification(notification: TurmsNotification): void;
    private _rejectRequestPromises;
    close(): Promise<void>;
    onDisconnected(): void;
}
