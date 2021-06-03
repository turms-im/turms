import Foundation
import PromiseKit

public class TurmsClient {
    public private(set) var driver: TurmsDriver
    public private(set) var userService: UserService!
    public private(set) var groupService: GroupService!
    public private(set) var conversationService: ConversationService!
    public private(set) var messageService: MessageService!
    public private(set) var storageService: StorageService!
    public private(set) var notificationService: NotificationService!

    public init(_ wsUrl: String? = nil, connectTimeout: TimeInterval? = nil, requestTimeout: TimeInterval? = nil, minRequestInterval: TimeInterval? = nil, heartbeatInterval: TimeInterval? = nil, storageServerUrl: String? = nil) {
        driver = TurmsDriver(
            wsUrl: wsUrl,
            connectTimeout: connectTimeout,
            requestTimeout: requestTimeout,
            minRequestInterval: minRequestInterval,
            heartbeatInterval: heartbeatInterval)
        userService = UserService(self)
        groupService = GroupService(self)
        conversationService = ConversationService(self)
        messageService = MessageService(self)
        storageService = StorageService(self, storageServerUrl: storageServerUrl)
        notificationService = NotificationService(self)
    }

    public func close() -> Promise<()> {
        return driver.close();
    }

}
