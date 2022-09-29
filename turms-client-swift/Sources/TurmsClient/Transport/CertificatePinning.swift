import Foundation

public enum PinningState {
    case success
    case failed(Error?)
}

public protocol CertificatePinning {
    func evaluateTrust(trust: SecTrust, completion: (PinningState) -> Void)
}
