import Foundation

public class NotificationService {
    private weak var turmsClient: TurmsClient!
    public var notificationListeners: [(Notification) -> Void] = []

    init(_ turmsClient: TurmsClient) {
        self.turmsClient = turmsClient
        self.turmsClient.driver
            .addNotificationListener { [weak self] notification in
                guard notification.hasRelayedRequest, !notification.hasCloseStatus else {
                    return
                }
                if case .createMessageRequest = notification.relayedRequest.kind {
                    return
                }
                let n = Notification(timestamp: Date(timeIntervalSince1970: TimeInterval(notification.timestamp)), relayedRequest: notification.relayedRequest)
                self?.notificationListeners.forEach {
                    $0(n)
                }
            }
    }

    public func addNotificationListener(listener: @escaping (Notification) -> Void) {
        notificationListeners.append(listener)
    }
}
