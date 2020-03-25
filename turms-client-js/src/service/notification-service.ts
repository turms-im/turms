import TurmsClient from "../turms-client";
import NotificationUtil from "../util/notification-util";

export default class NotificationService {
    private _turmsClient: TurmsClient;
    private _onNotification?: (notification: any) => void;

    get onNotification(): (notification: any) => void {
        return this._onNotification;
    }

    set onNotification(value: (notification: any) => void) {
        this._onNotification = value;
    }

    constructor(turmsClient: TurmsClient) {
        this._turmsClient = turmsClient;
        this._turmsClient.driver
            .onNotificationListeners
            .push(notification => {
                if (this._onNotification != null && notification.relayedRequest) {
                    this._onNotification(NotificationUtil.transform(notification.relayedRequest));
                }
                return null;
            });
    }
}
