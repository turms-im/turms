import TurmsClient from '../turms-client';
import { ParsedRelayedRequest } from '../model/parsed-notification';
export default class NotificationService {
    private _turmsClient;
    private _notificationListeners;
    addNotificationListener(listener: (notification: ParsedRelayedRequest) => void): void;
    removeNotificationListener(listener: (notification: ParsedRelayedRequest) => void): void;
    constructor(turmsClient: TurmsClient);
}
