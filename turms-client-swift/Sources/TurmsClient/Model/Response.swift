import Foundation

public struct Response<T> {
    public let timestamp: Date
    public let requestId: Int64?
    public let code: Int
    public let data: T

    init(timestamp: Date, requestId: Int64? = nil, code: Int, data: T = () as! T) {
        self.timestamp = timestamp
        self.requestId = requestId
        self.code = code
        self.data = data
    }

    func withData<T>(_ data: T) -> Response<T> {
        return Response<T>(timestamp: timestamp, requestId: requestId, code: code, data: data)
    }

    static func value(_ data: T) -> Response<T> {
        return Response(timestamp: Date(), code: ResponseStatusCode.ok.rawValue, data: data)
    }

    static func empty() -> Response<T> {
        return Response(timestamp: Date(), code: ResponseStatusCode.ok.rawValue, data: () as! T)
    }

    static func emptyArray() -> Response<T> {
        return Response(timestamp: Date(), code: ResponseStatusCode.ok.rawValue, data: [] as! T)
    }

    static func fromNotification(_ notification: TurmsNotification, _ dataTransformer: ((TurmsNotification.DataMessage) throws -> T)? = nil) throws -> Response<T> {
        if !notification.hasCode {
            throw ResponseError(
                code: .invalidNotification,
                reason: "Could not parse a success response from a notification without code"
            )
        }
        if notification.isError {
            throw ResponseError(
                code: .invalidNotification,
                reason: "Could not parse a success response from non-success notification"
            )
        }
        var data: T
        do {
            data = try dataTransformer?(notification.data) ?? () as! T
        } catch {
            throw ResponseError(
                code: .invalidNotification,
                reason: "Failed to transform notification data: \(notification.data)",
                cause: error
            )
        }
        return Response(
            timestamp: Date(timeIntervalSince1970: TimeInterval(notification.timestamp)),
            requestId: notification.hasRequestID ? notification.requestID : nil,
            code: Int(notification.code),
            data: data
        )
    }
}
