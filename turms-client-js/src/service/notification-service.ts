import TurmsClient from '../turms-client';
import Notification from '../model/notification';

export default class NotificationService {

    private _turmsClient: TurmsClient;
    private _notificationListeners: ((notification: Notification) => void)[] = [];

    constructor(turmsClient: TurmsClient) {
        this._turmsClient = turmsClient;
        this._turmsClient.driver
            .addNotificationListener(notification => {
                const isBusinessNotification = notification.relayedRequest
                    && !notification.relayedRequest.createMessageRequest
                    && !notification.closeStatus;
                if (isBusinessNotification) {
                    const n = new Notification(new Date(parseInt(notification.timestamp)), notification.relayedRequest);
                    this._notificationListeners.forEach(listener => listener(n));
                }
            });
    }

    addNotificationListener(listener: (notification: Notification) => void): void {
        this._notificationListeners.push(listener);
    }

    removeNotificationListener(listener: (notification: Notification) => void): void {
        this._notificationListeners = this._notificationListeners
            .filter(cur => cur !== listener);
    }

}