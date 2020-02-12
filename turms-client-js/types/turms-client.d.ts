import TurmsDriver from "./driver/turms-driver";
import UserService from "./service/user-service";
import GroupService from "./service/group-service";
import MessageService from "./service/message-service";
import NotificationService from "./service/notification-service";
import InputFileReader from "./util/input-file-reader";
declare class TurmsClient {
    private readonly _driver;
    private readonly _userService;
    private readonly _groupService;
    private readonly _messageService;
    private readonly _notificationService;
    constructor(url?: string, connectionTimeout?: number, requestTimeout?: number, minRequestsInterval?: number, httpUrl?: string, queryReasonWhenLoginFailed?: boolean, queryReasonWhenDisconnected?: boolean);
    get driver(): TurmsDriver;
    get userService(): UserService;
    get groupService(): GroupService;
    get messageService(): MessageService;
    get notificationService(): NotificationService;
    static InputFileReader(): InputFileReader;
}
export default TurmsClient;
