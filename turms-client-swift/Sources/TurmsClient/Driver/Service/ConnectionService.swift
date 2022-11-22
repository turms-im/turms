import Foundation
import PromiseKit

private class MessageDecoder {
    private static let maxReadBufferCapacity = 8 * 1024 * 1024

    private var readIndex = 0
    private var tempPayloadLength = 0
    private var payloadLength: Int?

    private var readBuffer = Data()

    func decodeMessages(_ data: Data) throws -> [Data] {
        if (readBuffer.count + data.count) > MessageDecoder.maxReadBufferCapacity {
            throw RuntimeError("The read buffer has exceeded the maximum size \(MessageDecoder.maxReadBufferCapacity)")
        }
        readBuffer.append(data)
        var messages: [Data] = []
        while true {
            let message = try tryReadMessage()
            guard let msg = message else {
                break
            }
            messages.append(msg)
        }
        return messages
    }

    func clear() {
        readIndex = 0
        tempPayloadLength = 0
        payloadLength = nil
        readBuffer.removeAll()
    }

    private func tryReadMessage() throws -> Data? {
        guard let length = try tryReadVarInt() else {
            return nil
        }
        payloadLength = length
        let end = readIndex + payloadLength!
        if readBuffer.count < end {
            return nil
        }
        let message = readBuffer.subdata(in: readIndex ..< end)
        readBuffer.removeSubrange(0 ..< end)
        readIndex = 0
        payloadLength = nil
        return message
    }

    private func tryReadVarInt() throws -> Int? {
        let length = readBuffer.count
        while readIndex < 5 {
            if readIndex >= length {
                return nil
            }
            let byte = readBuffer[readIndex]
            tempPayloadLength |= (Int(byte) & 0x7F) << (7 * readIndex)
            readIndex += 1
            if byte & 0x80 == 0 {
                let length = tempPayloadLength
                tempPayloadLength = 0
                return length
            }
        }
        throw RuntimeError("VarInt input too big")
    }
}

public class ConnectionService: BaseService {
    private let initialHost: String
    private let initialPort: UInt16
    private let initialConnectTimeout: TimeInterval

    private var disconnectPromises: [Resolver<Void>] = []

    private var onConnectedListeners: [() -> Void] = []
    private var onDisconnectedListeners: [(Error?) -> Void] = []
    private var messageListeners: [(Data) -> Void] = []

    private let decoder = MessageDecoder()

    init(stateStore: StateStore, host: String? = nil, port: UInt16? = nil, connectTimeout: TimeInterval? = nil) {
        initialHost = host ?? "127.0.0.1"
        initialPort = port ?? 11510
        initialConnectTimeout = connectTimeout ?? 30
        super.init(stateStore)
    }

    private func resetStates() {
        fulfillDisconnectPromises()
    }

    // Listeners

    func addOnConnectedListener(_ listener: @escaping () -> Void) {
        onConnectedListeners.append(listener)
    }

    func addOnDisconnectedListener(_ listener: @escaping (Error?) -> Void) {
        onDisconnectedListeners.append(listener)
    }

    func addMessageListener(_ listener: @escaping (Data) -> Void) {
        messageListeners.append(listener)
    }

    private func notifyOnConnectedListeners() {
        onConnectedListeners.forEach {
            $0()
        }
    }

    private func notifyOnDisconnectedListeners(_ error: Error?) {
        onDisconnectedListeners.forEach {
            $0(error)
        }
    }

    private func notifyMessageListeners(_ message: Data) {
        messageListeners.forEach {
            $0(message)
        }
    }

    private func fulfillDisconnectPromises() {
        repeat {
            disconnectPromises.popLast()?.fulfill(())
        } while !disconnectPromises.isEmpty
    }

    // Connection

    public func connect(host: String? = nil, port: UInt16? = nil, connectTimeout _: TimeInterval? = nil, useTls: Bool? = false, certificatePinning: CertificatePinning? = nil) -> Promise<Void> {
        return Promise { seal in
            if stateStore.isConnected {
                seal.reject(ResponseError(code: .clientSessionAlreadyEstablished))
                return
            }
            resetStates()
            let tcp = TcpClient(onClosed: { [weak self] error in
                self?.onSocketClosed(error)
            }, onDataReceived: { [weak self] data in
                guard let s = self else { return }
                let messages = try s.decoder.decodeMessages(data)
                messages.forEach { message in
                    s.notifyMessageListeners(message)
                }
            })
            tcp.connect(host: host ?? initialHost, port: port ?? initialPort, useTls: useTls ?? false, certificatePinning: certificatePinning)
                .done { [weak self] in
                    self?.onSocketOpened()
                    seal.fulfill_()
                }.catch { error in
                    seal.reject(error)
                }
            stateStore.tcp = tcp
        }
    }

    public func disconnect() -> Promise<Void> {
        return Promise { seal in
            if stateStore.isConnected {
                seal.fulfill(())
                return
            }
            disconnectPromises.append(seal)
            stateStore.tcp!.close()
        }
    }

    // Lifecycle hooks

    private func onSocketOpened() {
        stateStore.isConnected = true
        notifyOnConnectedListeners()
    }

    private func onSocketClosed(_ error: Error?) {
        decoder.clear()
        stateStore.isConnected = false
        notifyOnDisconnectedListeners(error)
    }

    // Base methods

    override func close() -> Promise<Void> {
        return disconnect()
    }
}
