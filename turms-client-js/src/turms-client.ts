import TurmsDriver from "./driver/turms-driver";
import UserService from "./service/user-service";
import GroupService from "./service/group-service";
import MessageService from "./service/message-service";
import NotificationService from "./service/notification-service";
import InputFileReader from "./util/input-file-reader";
import StorageService from "./service/storage-service";
import ClientOptions from "./client-options";

class TurmsClient {
    private readonly _driver: TurmsDriver;
    private readonly _userService: UserService;
    private readonly _groupService: GroupService;
    private readonly _messageService: MessageService;
    private readonly _storageService: StorageService;
    private readonly _notificationService: NotificationService;

    constructor(
        wsUrlOrOptions?: string | ClientOptions,
        connectionTimeout?: number,
        requestTimeout?: number,
        minRequestInterval?: number,
        heartbeatInterval?: number,
        ackMessageInterval?: number,
        storageServerUrl?: string,
        httpUrl?: string,
        queryReasonWhenLoginFailed?: boolean,
        queryReasonWhenDisconnected?: boolean,
        storePassword?: boolean) {
        if (typeof wsUrlOrOptions === 'object') {
            return new TurmsClient(
                wsUrlOrOptions.wsUrl,
                wsUrlOrOptions.connectionTimeout,
                wsUrlOrOptions.requestTimeout,
                wsUrlOrOptions.minRequestInterval,
                wsUrlOrOptions.heartbeatInterval,
                wsUrlOrOptions.ackMessageInterval,
                wsUrlOrOptions.storageServerUrl,
                wsUrlOrOptions.httpUrl,
                wsUrlOrOptions.queryReasonWhenLoginFailed,
                wsUrlOrOptions.queryReasonWhenDisconnected,
                wsUrlOrOptions.storePassword);
        } else {
            this._driver = new TurmsDriver(
                wsUrlOrOptions,
                connectionTimeout,
                requestTimeout,
                minRequestInterval,
                heartbeatInterval,
                httpUrl,
                queryReasonWhenLoginFailed,
                queryReasonWhenDisconnected,
                storePassword);
            this._userService = new UserService(this);
            this._groupService = new GroupService(this);
            this._messageService = new MessageService(this, ackMessageInterval);
            this._storageService = new StorageService(this, storageServerUrl);
            this._notificationService = new NotificationService(this);
        }
    }

    // Driver
    get driver(): TurmsDriver {
        return this._driver;
    }

    // Service
    get userService(): UserService {
        return this._userService;
    }

    get groupService(): GroupService {
        return this._groupService;
    }

    get messageService(): MessageService {
        return this._messageService;
    }

    get storageService(): StorageService {
        return this._storageService;
    }

    get notificationService(): NotificationService {
        return this._notificationService;
    }

    // Util
    static InputFileReader(): InputFileReader {
        return InputFileReader;
    }
}

let root;
if (typeof window !== 'undefined') {
    root = window;
} else if (typeof global !== 'undefined') {
    root = global;
}
if (root) {
    Object.defineProperty(root, 'TurmsClient', {
        configurable: false,
        writable: false,
        enumerable: false,
        value: TurmsClient
    });
} else {
    console.error('Make sure to run turms-client in either browser or Node.js');
}

export default TurmsClient;
