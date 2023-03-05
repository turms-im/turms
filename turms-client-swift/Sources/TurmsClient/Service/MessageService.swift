import Foundation
import PromiseKit

public class MessageService {
    /**
     * Format: "@{userId}"
     * Example: "@{123}", "I need to talk with @{123} and @{321}"
     */
    private static let DEFAULT_MENTIONED_USER_IDS_REGEX = try! NSRegularExpression(pattern: "@\\{(\\d+?)\\}", options: [])
    private static let DEFAULT_MENTIONED_USER_IDS_PARSER: (_ message: Message) -> [Int64] = {
        if !$0.hasText {
            return []
        }
        let text = $0.text
        let results = DEFAULT_MENTIONED_USER_IDS_REGEX.matches(in: text, range: NSRange(text.startIndex..., in: text))
        let userIds = results.compactMap {
            let groupRange = Range($0.range(at: 1), in: text)!
            let group = text[groupRange]
            return Int64(group)
        }
        return Array(NSOrderedSet(array: userIds)) as! [Int64]
    }

    private weak var turmsClient: TurmsClient!
    private var mentionedUserIdsParser: ((Message) -> [Int64])?
    public var messageListeners: [(Message, MessageAddition) -> Void] = []

    init(_ turmsClient: TurmsClient) {
        self.turmsClient = turmsClient
        self.turmsClient.driver
            .addNotificationListener {
                if !self.messageListeners.isEmpty, $0.hasRelayedRequest, case let .createMessageRequest(request) = $0.relayedRequest.kind {
                    let message = MessageService.createMessage2Message($0.requesterID, request)
                    let addition = self.parseMessageAddition(message)
                    self.messageListeners.forEach { listener in
                        listener(message, addition)
                    }
                }
            }
    }

    func addMessageListener(_ listener: @escaping (Message, MessageAddition) -> Void) {
        messageListeners.append(listener)
    }

    public func sendMessage(
        isGroupMessage: Bool,
        targetId: Int64,
        deliveryDate: Date? = nil,
        text: String? = nil,
        records: [Data]? = nil,
        burnAfter: Int32? = nil,
        preMessageId: Int64? = nil
    ) -> Promise<Response<Int64>> {
        if Validator.areAllNil(text, records) {
            return Promise(error: ResponseError(
                code: ResponseStatusCode.illegalArgument,
                reason: "text and records must not all be null"
            ))
        }
        return turmsClient.driver
            .send {
                $0.createMessageRequest = .with {
                    if isGroupMessage {
                        $0.groupID = targetId
                    } else {
                        $0.recipientID = targetId
                    }
                    if let v = deliveryDate {
                        $0.deliveryDate = v.toMillis()
                    }
                    if let v = text {
                        $0.text = v
                    }
                    if let v = records {
                        $0.records = v
                    }
                    if let v = burnAfter {
                        $0.burnAfter = v
                    }
                    if let v = preMessageId {
                        $0.preMessageID = v
                    }
                }
            }
            .map {
                try $0.toResponse {
                    try $0.getLongOrThrow()
                }
            }
    }

    public func forwardMessage(
        messageId: Int64,
        isGroupMessage: Bool,
        targetId: Int64
    ) -> Promise<Response<Int64>> {
        return turmsClient.driver
            .send {
                $0.createMessageRequest = .with {
                    $0.messageID = messageId
                    if isGroupMessage {
                        $0.groupID = targetId
                    } else {
                        $0.recipientID = targetId
                    }
                }
            }
            .map {
                try $0.toResponse {
                    try $0.getLongOrThrow()
                }
            }
    }

    public func updateSentMessage(
        messageId: Int64,
        text: String? = nil,
        records: [Data]? = nil
    ) -> Promise<Response<Void>> {
        if Validator.areAllNil(text, records) {
            return Promise.value(Response.empty())
        }
        return turmsClient.driver
            .send {
                $0.updateMessageRequest = .with {
                    $0.messageID = messageId
                    if let v = text {
                        $0.text = v
                    }
                    if let v = records {
                        $0.records = v
                    }
                }
            }
            .map {
                try $0.toResponse()
            }
    }

    public func queryMessages(
        ids: [Int64]? = nil,
        areGroupMessages: Bool? = nil,
        areSystemMessages: Bool? = nil,
        fromIds: [Int64]? = nil,
        deliveryDateStart: Date? = nil,
        deliveryDateEnd: Date? = nil,
        maxCount: Int32 = 50,
        descending: Bool? = nil
    ) -> Promise<Response<[Message]>> {
        return turmsClient.driver
            .send {
                $0.queryMessagesRequest = .with {
                    if let v = ids {
                        $0.ids = v
                    }
                    if let v = areGroupMessages {
                        $0.areGroupMessages = v
                    }
                    if let v = areSystemMessages {
                        $0.areSystemMessages = v
                    }
                    if let v = fromIds {
                        $0.fromIds = v
                    }
                    if let v = deliveryDateStart {
                        $0.deliveryDateStart = v.toMillis()
                    }
                    if let v = deliveryDateEnd {
                        $0.deliveryDateEnd = v.toMillis()
                    }
                    $0.maxCount = maxCount
                    if let v = descending, v {
                        $0.descending = true
                    }
                    $0.withTotal = false
                }
            }
            .map {
                try $0.toResponse {
                    $0.messages.messages
                }
            }
    }

    public func queryMessagesWithTotal(
        ids: [Int64]? = nil,
        areGroupMessages: Bool? = nil,
        areSystemMessages: Bool? = nil,
        fromIds: [Int64]? = nil,
        deliveryDateStart: Date? = nil,
        deliveryDateEnd: Date? = nil,
        maxCount: Int32 = 1,
        descending: Bool? = nil
    ) -> Promise<Response<[MessagesWithTotal]>> {
        return turmsClient.driver
            .send {
                $0.queryMessagesRequest = .with {
                    if let v = ids {
                        $0.ids = v
                    }
                    if let v = areGroupMessages {
                        $0.areGroupMessages = v
                    }
                    if let v = areSystemMessages {
                        $0.areSystemMessages = v
                    }
                    if let v = fromIds {
                        $0.fromIds = v
                    }
                    if let v = deliveryDateStart {
                        $0.deliveryDateStart = v.toMillis()
                    }
                    if let v = deliveryDateEnd {
                        $0.deliveryDateEnd = v.toMillis()
                    }
                    $0.maxCount = maxCount
                    if let v = descending, v {
                        $0.descending = true
                    }
                    $0.withTotal = true
                }
            }
            .map {
                try $0.toResponse {
                    $0.messagesWithTotalList.messagesWithTotalList
                }
            }
    }

    public func recallMessage(messageId: Int64, recallDate: Date = Date()) -> Promise<Response<Void>> {
        return turmsClient.driver
            .send {
                $0.updateMessageRequest = .with {
                    $0.messageID = messageId
                    $0.recallDate = recallDate.toMillis()
                }
            }
            .map {
                try $0.toResponse()
            }
    }

    public func isMentionEnabled() -> Bool {
        return mentionedUserIdsParser != nil
    }

    public func enableMention(mentionedUserIdsParser: ((Message) -> [Int64])?) {
        if mentionedUserIdsParser != nil {
            self.mentionedUserIdsParser = mentionedUserIdsParser
        } else if self.mentionedUserIdsParser == nil {
            self.mentionedUserIdsParser = MessageService.DEFAULT_MENTIONED_USER_IDS_PARSER
        }
    }

    public static func generateLocationRecord(latitude: Float, longitude: Float, details: [String: String]? = nil) -> Data {
        return try! UserLocation.with {
            $0.latitude = latitude
            $0.longitude = longitude
            if let v = details {
                $0.details = v
            }
        }
        .serializedData()
    }

    public static func generateAudioRecordByDescription(url: String, duration: Int32? = nil, format: String? = nil, size: Int32? = nil) -> Data {
        return try! AudioFile.with {
            $0.description_p.url = url
            if let v = duration {
                $0.description_p.duration = v
            }
            if let v = format {
                $0.description_p.format = v
            }
            if let v = size {
                $0.description_p.size = v
            }
        }
        .serializedData()
    }

    public static func generateAudioRecordByData(_ data: Data) -> Data {
        return try! AudioFile.with {
            $0.data = data
        }
        .serializedData()
    }

    public static func generateVideoRecordByDescription(url: String, duration: Int32? = nil, format: String? = nil, size: Int32? = nil) -> Data {
        return try! VideoFile.with {
            $0.description_p.url = url
            if let v = duration {
                $0.description_p.duration = v
            }
            if let v = format {
                $0.description_p.format = v
            }
            if let v = size {
                $0.description_p.size = v
            }
        }
        .serializedData()
    }

    public static func generateVideoRecordByData(_ data: Data) -> Data {
        return try! VideoFile.with {
            $0.data = data
        }
        .serializedData()
    }

    public static func generateImageRecordByData(_ data: Data) -> Data {
        return try! ImageFile.with {
            $0.data = data
        }
        .serializedData()
    }

    public static func generateImageRecordByDescription(url: String, fileSize: Int32? = nil, imageSize: Int32? = nil, original: Bool? = nil) -> Data {
        return try! ImageFile.with {
            $0.description_p.url = url
            if let v = fileSize {
                $0.description_p.fileSize = v
            }
            if let v = imageSize {
                $0.description_p.imageSize = v
            }
            if let v = original {
                $0.description_p.original = v
            }
        }
        .serializedData()
    }

    public static func generateFileRecordByDate(_ data: Data) -> Data {
        return try! File.with {
            $0.data = data
        }
        .serializedData()
    }

    public static func generateFileRecordByDescription(url: String, format: String? = nil, size: Int32? = nil) -> Data {
        return try! File.with {
            $0.description_p.url = url
            if let v = format {
                $0.description_p.format = v
            }
            if let v = size {
                $0.description_p.size = v
            }
        }
        .serializedData()
    }

    private func parseMessageAddition(_ message: Message) -> MessageAddition {
        let mentionedUserIds = mentionedUserIdsParser?(message) ?? []
        let userId = turmsClient.userService.userInfo?.userId
        let isMentioned = userId != nil ? mentionedUserIds.contains(userId!) : false
        let records = message.records
        var systemMessageType: BuiltinSystemMessageType?
        if message.isSystemMessage, !records.isEmpty {
            let data = records[0]
            if !data.isEmpty {
                systemMessageType = BuiltinSystemMessageType(rawValue: Int(data[0]))
            }
        }
        var recalledMessageIds: [Int64] = []
        if systemMessageType == BuiltinSystemMessageType.recallMessage {
            let size = records.count
            for i in 1 ... (size - 1) {
                let id = records[i].withUnsafeBytes {
                    $0.load(as: Int64.self)
                }
                recalledMessageIds.append(id)
            }
        }
        return MessageAddition(isMentioned: isMentioned, mentionedUserIds: mentionedUserIds, recalledMessageIds: recalledMessageIds)
    }

    private static func createMessage2Message(_ requesterId: Int64, _ request: CreateMessageRequest) -> Message {
        return Message.with {
            if request.hasMessageID {
                $0.id = request.messageID
            }
            $0.isSystemMessage = request.isSystemMessage
            $0.deliveryDate = request.deliveryDate
            if request.hasText {
                $0.text = request.text
            }
            if request.records.count > 0 {
                $0.records = request.records
            }
            $0.senderID = requesterId
            if request.hasGroupID {
                $0.groupID = request.groupID
            }
            if request.hasRecipientID {
                $0.recipientID = request.recipientID
            }
        }
    }
}
