import TurmsDriver from "./driver/turms-driver";
import UserService from "./service/user-service";
import GroupService from "./service/group-service";
import MessageService from "./service/message-service";
import NotificationService from "./service/notification-service";
import InputFileReader from "./util/input-file-reader";
import StorageService from "./service/storage-service";
import ClientOptions from "./client-options";
declare class TurmsClient {
    private readonly _driver;
    private readonly _userService;
    private readonly _groupService;
    private readonly _messageService;
    private readonly _storageService;
    private readonly _notificationService;
    constructor(wsUrlOrOptions?: string | ClientOptions, connectionTimeout?: number, requestTimeout?: number, minRequestInterval?: number, heartbeatInterval?: number, ackMessageInterval?: number, storageServerUrl?: string, httpUrl?: string, queryReasonWhenLoginFailed?: boolean, queryReasonWhenDisconnected?: boolean, storePassword?: boolean);
    get driver(): TurmsDriver;
    get userService(): UserService;
    get groupService(): GroupService;
    get messageService(): MessageService;
    get storageService(): StorageService;
    get notificationService(): NotificationService;
    static InputFileReader(): InputFileReader;
}
export default TurmsClient;
