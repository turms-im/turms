import Foundation

public struct TcpMetrics {
    var connectTime: TimeInterval?
    var addressResolverTime: TimeInterval?

    var dataReceived = 0
    var dataSent = 0
}
