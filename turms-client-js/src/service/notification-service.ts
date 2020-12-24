import TurmsClient from "../turms-client";

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
            .addOnNotificationListener(notification => {
                if (this._onNotification != null && notification.relayedRequest && !notification.relayedRequest.createMessageRequest) {
                    this._onNotification(notification.relayedRequest);
                }
                return null;
            });
    }
}
