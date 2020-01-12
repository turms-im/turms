import TurmsDriver from "./driver/turms-driver";
import UserService from "./service/user-service";
import GroupService from "./service/group-service";
import MessageService from "./service/message-service";
import InputFileReader from "./util/input-file-reader";

//TODO: Support load .proto files dynamically.
class TurmsClient {
    private readonly _driver: TurmsDriver;
    private readonly _userService: UserService;
    private readonly _groupService: GroupService;
    private readonly _messageService: MessageService;

    constructor(
        url?: string,
        connectionTimeout?: number,
        requestTimeout?: number,
        minRequestsInterval?: number,
        httpUrl?: string,
        queryReasonWhenLoginFailed?: boolean,
        queryReasonWhenDisconnected?: boolean) {
        this._driver = new TurmsDriver(
            this,
            url,
            connectionTimeout,
            requestTimeout,
            minRequestsInterval,
            httpUrl,
            queryReasonWhenLoginFailed,
            queryReasonWhenDisconnected);
        this._userService = new UserService(this);
        this._groupService = new GroupService(this);
        this._messageService = new MessageService(this);
    }

    //Driver
    get driver(): TurmsDriver {
        return this._driver;
    }

    //Service
    get userService(): UserService {
        return this._userService;
    }

    get groupService(): GroupService {
        return this._groupService;
    }

    get messageService(): MessageService {
        return this._messageService;
    }

    //Util
    static InputFileReader(): InputFileReader {
        return InputFileReader;
    }
}

const root = window || global;
if (root) {
    Object.defineProperty(root, 'TurmsClient', {
        configurable: false,
        writable: false,
        enumerable: false,
        value: TurmsClient
    });
} else {
    console.error('Make sure to run turms-client in either browser or nodejs');
}

export default TurmsClient;
