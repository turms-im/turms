import Foundation

public class NotificationService {
    private weak var turmsClient: TurmsClient!
    public var onNotification: ((TurmsRequest) -> Void)?

    init(_ turmsClient: TurmsClient) {
        self.turmsClient = turmsClient
        self.turmsClient.driver
            .onNotificationListeners
            .append {
                if self.onNotification != nil, $0.hasRelayedRequest {
                    self.onNotification!($0.relayedRequest)
                }
            }
    }
}
