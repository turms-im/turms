public class TurmsClient {
    public let driver: TurmsDriver
    public private(set) var userService: UserService!
    public private(set) var groupService: GroupService!
    public private(set) var messageService: MessageService!
    public private(set) var notificationService: NotificationService!

    public init(_ url: String?, connectionTimeout: Int? = nil, minRequestsInterval: Int? = nil) {
        driver = TurmsDriver(
            url,
            connectionTimeout: connectionTimeout,
            minRequestsInterval: minRequestsInterval)
        userService = UserService(self)
        groupService = GroupService(self)
        messageService = MessageService(self)
        notificationService = NotificationService(self)
    }
}
