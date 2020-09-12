import Foundation

public class TurmsClient {
    public private(set) var driver: TurmsDriver!
    public private(set) var userService: UserService!
    public private(set) var groupService: GroupService!
    public private(set) var messageService: MessageService!
    public private(set) var storageService: StorageService!
    public private(set) var notificationService: NotificationService!

    public init(_ url: String? = nil, connectTimeout: TimeInterval? = nil, minRequestsInterval: TimeInterval? = nil, storageServerUrl: String? = nil) {
        driver = TurmsDriver(
            url: url,
            connectTimeout: connectTimeout,
            minRequestsInterval: minRequestsInterval)
        userService = UserService(self)
        groupService = GroupService(self)
        messageService = MessageService(self)
        storageService = StorageService(self, storageServerUrl: storageServerUrl)
        notificationService = NotificationService(self)
    }
}
