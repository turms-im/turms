import Foundation

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

    private var disconnectContinuations: [UnsafeContinuation<Void, Never>] = []

    private var onConnectedListeners: [() -> Void] = []
    private var onDisconnectedListeners: [(Error?) -> Void] = []
    private var messageListeners: [(Data) -> Void] = []

    private let lock = Lock()
    private let decoder = MessageDecoder()

    init(stateStore: StateStore, host: String? = nil, port: UInt16? = nil, connectTimeout: TimeInterval? = nil) {
        initialHost = host ?? "127.0.0.1"
        initialPort = port ?? 11510
        initialConnectTimeout = connectTimeout ?? 30
        super.init(stateStore)
    }

    private func resetStates() {
        fulfillDisconnectContinuations()
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
        for onConnectedListener in onConnectedListeners {
            onConnectedListener()
        }
    }

    private func notifyOnDisconnectedListeners(_ error: Error?) {
        for onDisconnectedListener in onDisconnectedListeners {
            onDisconnectedListener(error)
        }
    }

    private func notifyMessageListeners(_ message: Data) {
        for messageListener in messageListeners {
            messageListener(message)
        }
    }

    private func fulfillDisconnectContinuations() {
        lock.locked {
            repeat {
                disconnectContinuations.popLast()?.resume()
            } while !disconnectContinuations.isEmpty
        }
    }

    // Connection

    public func connect(host: String? = nil, port: UInt16? = nil, connectTimeout _: TimeInterval? = nil, useTls: Bool? = false, certificatePinning: CertificatePinning? = nil) async throws {
        if stateStore.isConnected {
            throw ResponseError(code: .clientSessionAlreadyEstablished)
        }
        resetStates()
        let tcp = TcpClient(onClosed: { [weak self] error in
            self?.onSocketClosed(error)
        }, onDataReceived: { [weak self] data in
            guard let s = self else { return }
            let messages = try s.decoder.decodeMessages(data)
            for message in messages {
                s.notifyMessageListeners(message)
            }
        })
        try await tcp.connect(host: host ?? initialHost, port: port ?? initialPort, useTls: useTls ?? false, certificatePinning: certificatePinning)
        onSocketOpened()
        stateStore.tcp = tcp
    }

    public func disconnect() async {
        await withUnsafeContinuation { continuation in
            if !stateStore.isConnected {
                return continuation.resume()
            }
            lock.locked {
                disconnectContinuations.append(continuation)
            }
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
        fulfillDisconnectContinuations()
        notifyOnDisconnectedListeners(error)
    }

    // Base methods

    override func close() async {
        return await disconnect()
    }
}