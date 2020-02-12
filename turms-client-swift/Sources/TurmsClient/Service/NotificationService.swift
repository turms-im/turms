import Foundation

public class NotificationService {
    private weak var turmsClient: TurmsClient!
    public var onNotification: ((TurmsRequest, Int64?) -> Void)?

    init(_ turmsClient: TurmsClient) {
        self.turmsClient = turmsClient
        self.turmsClient.driver
            .onNotificationListeners
            .append {
                if self.onNotification != nil, $0.hasRelayedRequest {
                    let requesterId: Int64? = $0.hasRequesterID ? $0.requesterID.value : nil
                    self.onNotification!($0.relayedRequest, requesterId)
                }
            }
    }
}
