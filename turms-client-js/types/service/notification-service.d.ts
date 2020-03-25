import TurmsClient from "../turms-client";
export default class NotificationService {
    private _turmsClient;
    private _onNotification?;
    get onNotification(): (notification: any) => void;
    set onNotification(value: (notification: any) => void);
    constructor(turmsClient: TurmsClient);
}
