import Foundation
import Network
import PromiseKit

public enum TCPTransportError: Error {
    case invalidRequest
}

@available(macOS 10.14, iOS 12.0, watchOS 5.0, tvOS 12.0, *)
public class TcpClient {
    private var connection: NWConnection?
    private let queue = DispatchQueue(label: "im.turms.turmsclient.transport", attributes: [])
    private var isOpen = false
    private var metrics = TcpMetrics()

    private var onClosed: (_ error: Error?) -> Void
    private var onDataReceived: (_ data: Data) throws -> Void

    public init(onClosed: @escaping (_ error: Error?) -> Void, onDataReceived: @escaping (Data) throws -> Void) {
        self.onClosed = onClosed
        self.onDataReceived = onDataReceived
    }

    public func connect(host: String, port: UInt16, useTls: Bool = false, timeout: Double? = nil, certificatePinning: CertificatePinning? = nil) -> Promise<Void> {
        return Promise<Void> { seal in
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
            guard let connection = connection else {
                seal.reject(RuntimeError("Connection is nil unexpectedly"))
                return
            }
            connection.stateUpdateHandler = { [weak self] newState in
                guard let s = self else {
                    return
                }
                switch newState {
                case .ready:
                    if #available(macOS 10.15, iOS 13.0, watchOS 6.0, tvOS 13.0, *) {
                        connection.requestEstablishmentReport(queue: s.queue) { report in
                            guard let report = report else {
                                s.isOpen = true
                                s.readLoop()
                                seal.fulfill_()
                                return
                            }
                            s.metrics.connectTime = report.handshakes.max { handshake1, handshake2 in handshake1.handshakeDuration < handshake2.handshakeDuration }?.handshakeDuration
                            s.metrics.addressResolverTime = report.resolutions.max { resolution1, resolution2 in resolution1.duration < resolution2.duration }?.duration
                            s.isOpen = true
                            s.readLoop()
                            seal.fulfill_()
                        }
                    } else {
                        s.isOpen = true
                        s.readLoop()
                        seal.fulfill_()
                    }
                case .cancelled:
                    seal.reject(RuntimeError("\(newState)"))
                    s.onClose()
                case let .failed(error):
                    seal.reject(RuntimeError("\(newState)"))
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
        connection!.receive(minimumIncompleteLength: 1, maximumLength: 4096, completion: { [weak self] data, context, isComplete, error in
            guard let s = self else {
                return
            }
            if let data = data {
                s.metrics.dataReceived += data.count
                do {
                    try s.onDataReceived(data)
                } catch {
                    s.close(error)
                    return
                }
            }
            if let context = context, context.isFinal, isComplete {
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

    public func write(_ data: Data, _ completion: ((_ error: NWError?) -> Void)? = nil) {
        connection!.send(content: data, completion: .contentProcessed { [weak self] error in
            guard let s = self else {
                return
            }
            if let e = error {
                s.close(e)
            } else {
                s.metrics.dataSent += data.count
            }
            completion?(error)
        })
    }

    public func writeVarIntLengthAndBytes(_ data: Data, completion: ((_ error: NWError?) -> Void)? = nil) {
        var list = encodeVarInt(data.count)
        list.append(data)
        write(list, completion)
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
            do {
                onClosed(error)
            } catch {
                // TODO: log
            }
        }
        metrics = TcpMetrics()
        isOpen = false
    }
}
