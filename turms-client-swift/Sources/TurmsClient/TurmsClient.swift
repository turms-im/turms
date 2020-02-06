public class TurmsClient {
    public let driver: TurmsDriver
    public private(set) var userService: UserService!
    public private(set) var groupService: GroupService!
    public private(set) var messageService: MessageService!

    public init(url: String?, connectionTimeout: Int?, minRequestsInterval: Int?) {
        driver = TurmsDriver(
            url: url,
            connectionTimeout: connectionTimeout,
            minRequestsInterval: minRequestsInterval)
        userService = UserService(self)
        groupService = GroupService(self)
        messageService = MessageService(self)
    }
}
