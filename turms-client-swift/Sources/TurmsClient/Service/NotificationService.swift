import Foundation

public class NotificationService {
    private weak var turmsClient: TurmsClient!
    public var onNotification: ((TurmsRequest) -> Void)?

    init(_ turmsClient: TurmsClient) {
        self.turmsClient = turmsClient
        self.turmsClient.driver
            .addOnNotificationListener {
                if self.onNotification != nil, $0.hasRelayedRequest {
                    guard case .createMessageRequest = $0.relayedRequest.kind else {
                        self.onNotification!($0.relayedRequest)
                        return
                    }
                }
            }
    }
}
