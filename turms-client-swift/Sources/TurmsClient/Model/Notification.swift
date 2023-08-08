import Foundation

public struct Notification {
    public let timestamp: Date
    public let requesterId: Int64
    public let relayedRequest: TurmsRequest

    public init(timestamp: Date, requesterId: Int64, relayedRequest: TurmsRequest) {
        self.timestamp = timestamp
        self.requesterId = requesterId
        self.relayedRequest = relayedRequest
    }
}
