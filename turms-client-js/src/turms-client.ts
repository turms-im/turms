import { version } from '../package.json';

import TurmsDriver from './driver/turms-driver';

import UserService from './service/user-service';
import GroupService from './service/group-service';
import ConversationService from './service/conversation-service';
import MessageService from './service/message-service';
import NotificationService from './service/notification-service';
import StorageService from './service/storage-service';

import InputFileReader from './util/input-file-reader';

import ClientOptions from './client-options';

class TurmsClient {
    static readonly version = version;

    private readonly _driver: TurmsDriver;
    private readonly _userService: UserService;
    private readonly _groupService: GroupService;
    private readonly _conversationService: ConversationService;
    private readonly _messageService: MessageService;
    private readonly _storageService: StorageService;
    private readonly _notificationService: NotificationService;

    constructor(options?: ClientOptions);

    constructor(
        wsUrl?: string,
        connectionTimeout?: number,
        requestTimeout?: number,
        minRequestInterval?: number,
        heartbeatInterval?: number,
        storageServerUrl?: string);

    constructor(
        wsUrlOrOptions?: string | ClientOptions,
        connectionTimeout?: number,
        requestTimeout?: number,
        minRequestInterval?: number,
        heartbeatInterval?: number,
        storageServerUrl?: string) {
        if (typeof wsUrlOrOptions === 'object') {
            connectionTimeout = wsUrlOrOptions.connectionTimeout;
            requestTimeout = wsUrlOrOptions.requestTimeout;
            minRequestInterval = wsUrlOrOptions.minRequestInterval;
            heartbeatInterval = wsUrlOrOptions.heartbeatInterval;
            storageServerUrl = wsUrlOrOptions.storageServerUrl;
            wsUrlOrOptions = wsUrlOrOptions.wsUrl;
        }
        this._driver = new TurmsDriver(
            wsUrlOrOptions,
            connectionTimeout,
            requestTimeout,
            minRequestInterval,
            heartbeatInterval);
        this._userService = new UserService(this);
        this._groupService = new GroupService(this);
        this._conversationService = new ConversationService(this);
        this._messageService = new MessageService(this);
        this._storageService = new StorageService(this, storageServerUrl);
        this._notificationService = new NotificationService(this);
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

    get conversationService(): ConversationService {
        return this._conversationService;
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

    close(): Promise<void> {
        return this.driver.close();
    }

    // Util
    static InputFileReader(): InputFileReader {
        return InputFileReader;
    }
}

// We don't use the following export to have a consistent
// export in cjs, esm and iife without confusing users
// export {
//     TurmsClient,
//     InputFileReader
// };
export default TurmsClient;