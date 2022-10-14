import Foundation

public struct Notification {
    public let timestamp: Date
    public let relayedRequest: TurmsRequest

    public init(timestamp: Date, relayedRequest: TurmsRequest) {
        self.timestamp = timestamp
        self.relayedRequest = relayedRequest
    }
}
