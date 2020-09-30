import Foundation

public class TurmsClient {
    public private(set) var driver: TurmsDriver
    public private(set) var userService: UserService!
    public private(set) var groupService: GroupService!
    public private(set) var messageService: MessageService!
    public private(set) var storageService: StorageService!
    public private(set) var notificationService: NotificationService!

    public init(_ wsUrl: String? = nil, connectTimeout: TimeInterval? = nil, requestTimeout: TimeInterval? = nil, minRequestInterval: TimeInterval? = nil, heartbeatInterval: TimeInterval? = nil, ackMessageInterval: TimeInterval? = nil, storageServerUrl: String? = nil, storePassword: Bool? = nil) {
        driver = TurmsDriver(
            wsUrl: wsUrl,
            connectTimeout: connectTimeout,
            requestTimeout: requestTimeout,
            minRequestInterval: minRequestInterval,
            heartbeatInterval: heartbeatInterval,
            storePassword: storePassword)
        userService = UserService(self)
        groupService = GroupService(self)
        messageService = MessageService(self, ackMessageInterval)
        storageService = StorageService(self, storageServerUrl: storageServerUrl)
        notificationService = NotificationService(self)
    }
}
