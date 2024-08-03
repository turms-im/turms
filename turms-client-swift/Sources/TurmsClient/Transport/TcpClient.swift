import Foundation
import Network

public enum TCPTransportError: Error {
    case invalidRequest
}

public class TcpClient {
    private var connection: NWConnection?
    private let queue = DispatchQueue(label: "im.turms.turmsclient.transport")
    private var isOpen = false
    private var metrics = TcpMetrics()

    private var onClosed: (_ error: Error?) -> Void
    private var onDataReceived: (_ data: Data) throws -> Void

    public init(onClosed: @escaping (_ error: Error?) -> Void, onDataReceived: @escaping (Data) throws -> Void) {
        self.onClosed = onClosed
        self.onDataReceived = onDataReceived
    }

    public func connect(host: String, port: UInt16, useTls: Bool = false, timeout: Double? = nil, certificatePinning: CertificatePinning? = nil) async throws {
        return try await withUnsafeThrowingContinuation { continuation in
            let options = NWProtocolTCP.Options()
            if let t = timeout {
                options.connectionTimeout = Int(t.rounded(.up))
            }
            options.noDelay = true
            options.enableFastOpen = true

            let tlsOptions = useTls ? NWProtocolTLS.Options() : nil
            if let tlsOpts = tlsOptions {
                sec_protocol_options_set_verify_block(tlsOpts.securityProtocolOptions, { _, sec_trust, sec_protocol_verify_complete in
                    let trust = sec_trust_copy_ref(sec_trust).takeRetainedValue()
                    guard let pinner = certificatePinning else {
                        sec_protocol_verify_complete(true)
                        return
                    }
                    pinner.evaluateTrust(trust: trust, completion: { state in
                        switch state {
                        case .success:
                            sec_protocol_verify_complete(true)
                        case .failed:
                            sec_protocol_verify_complete(false)
                        }
                    })
                }, queue)
            }
            let parameters = NWParameters(tls: tlsOptions, tcp: options)
            connection = NWConnection(host: NWEndpoint.Host.name(host, nil), port: NWEndpoint.Port(rawValue: port)!, using: parameters)
            guard let connection else {
                return continuation.resume(throwing: RuntimeError("Connection is nil unexpectedly"))
            }
            connection.stateUpdateHandler = { [weak self] newState in
                guard let s = self else {
                    return
                }
                switch newState {
                case .ready:
                    connection.requestEstablishmentReport(queue: s.queue) { report in
                        guard let report else {
                            s.isOpen = true
                            s.readLoop()
                            return continuation.resume()
                        }
                        s.metrics.connectTime = report.handshakes.max { handshake1, handshake2 in handshake1.handshakeDuration < handshake2.handshakeDuration }?.handshakeDuration
                        s.metrics.addressResolverTime = report.resolutions.max { resolution1, resolution2 in resolution1.duration < resolution2.duration }?.duration
                        s.isOpen = true
                        s.readLoop()
                        continuation.resume()
                    }
                case .cancelled:
                    continuation.resume(throwing: RuntimeError("\(newState)"))
                    s.onClose()
                case let .failed(error):
                    continuation.resume(throwing: RuntimeError("\(newState)"))
                    s.onClose(error)
                case .waiting, .setup, .preparing:
                    break
                @unknown default:
                    break
                }
            }
            connection.start(queue: queue)
        }
    }

    private func readLoop() {
        if !isOpen {
            return
        }
        guard let connection else {
            return
        }
        connection.receive(minimumIncompleteLength: 1, maximumLength: 4096, completion: { [weak self] data, context, isComplete, error in
            guard let s = self else {
                return
            }
            if let data {
                s.metrics.dataReceived += data.count
                do {
                    try s.onDataReceived(data)
                } catch {
                    s.close(error)
                    return
                }
            }
            if let context, context.isFinal, isComplete {
                s.close()
                return
            }
            if error == nil {
                s.readLoop()
            } else {
                s.close(error)
            }
        })
    }

    public func write(_ data: Data) async throws {
        guard let connection else {
            throw RuntimeError("The connection is not open")
        }
        return try await withUnsafeThrowingContinuation { continuation in
            connection.send(content: data, completion: .contentProcessed { [weak self] error in
                guard let s = self else {
                    return
                }
                if let e = error {
                    s.close(e)
                    continuation.resume(throwing: e)
                } else {
                    s.metrics.dataSent += data.count
                    continuation.resume()
                }
            })
        }
    }

    public func writeVarIntLengthAndBytes(_ data: Data) async throws {
        var list = encodeVarInt(data.count)
        list.append(data)
        try await write(list)
    }

    private func encodeVarInt(_ value: Int) -> Data {
        var data = Data()
        var v = value
        for _ in 0 ..< 5 {
            if (v >> 7) == 0 {
                data.append(UInt8(v & 0x7F))
                break
            } else {
                data.append(UInt8(v | 0x80))
            }
            v >>= 7
        }
        return data
    }

    public func close(_: Error? = nil) {
        connection?.cancel()
        onClose()
    }

    private func onClose(_ error: Error? = nil) {
        if isOpen {
            onClosed(error)
        }
        metrics = TcpMetrics()
        isOpen = false
    }
}