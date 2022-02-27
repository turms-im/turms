import Foundation

public class NotificationService {
    private weak var turmsClient: TurmsClient!
    public var notificationListeners: [(TurmsRequest) -> Void] = []

    init(_ turmsClient: TurmsClient) {
        self.turmsClient = turmsClient
        self.turmsClient.driver
            .addNotificationListener { [weak self] n in
                guard n.hasRelayedRequest, !n.hasCloseStatus else {
                    return
                }
                if case .createMessageRequest = n.relayedRequest.kind {
                    return
                }
                self?.notificationListeners.forEach {
                    $0(n.relayedRequest)
                }
            }
    }

    public func addNotificationListener(listener: @escaping (TurmsRequest) -> Void) {
        notificationListeners.append(listener)
    }
}
