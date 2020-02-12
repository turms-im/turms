import TurmsClient from "../turms-client";
export default class NotificationService {
    private _turmsClient;
    private _onNotification?;
    get onNotification(): (notification: any, requesterId: number) => void;
    set onNotification(value: (notification: any, requesterId: number) => void);
    constructor(turmsClient: TurmsClient);
}
