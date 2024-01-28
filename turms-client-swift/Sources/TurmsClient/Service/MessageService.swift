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
                    for listener in self.messageListeners {
                        listener(message, addition)
                    }
                }
            }
    }

    /// Add a message listener that will be triggered when a new message arrives.
    /// Note: To listen to notifications excluding messages,
    /// use ``NotificationService/addNotificationListener`` instead.
    func addMessageListener(_ listener: @escaping (Message, MessageAddition) -> Void) {
        messageListeners.append(listener)
    }

    /// Send a message.
    ///
    /// Common Scenarios:
    /// * A client can call ``addMessageListener`` to listen to incoming new messages.
    ///
    /// Authorization:
    ///
    /// For private messages,
    /// * If the server property `turms.service.message.allow-send-messages-to-oneself`
    ///   is true (false by default), the logged-in user can send messages to itself.
    ///   Otherwise, throws ``ResponseError`` with the code ``ResponseStatusCode/sendingMessagesToOneselfIsDisabled``.
    /// * If the server property `turms.service.message.allow-send-messages-to-stranger`
    ///   is true (true by default), the logged-in user can send messages to strangers
    ///   (has no relationship with the logged-in user).
    /// * If the logged-in user has blocked the target user,
    ///   throws ``ResponseError`` with the code ``ResponseStatusCode/blockedUserSendPrivateMessage``.
    ///
    /// For group messages,
    /// * If the logged-in user has blocked the target group,
    ///   throws ``ResponseError`` with the code ``ResponseStatusCode/blockedUserSendGroupMessage``.
    /// * If the logged-in user is not a group member, and the group does NOT allow non-members to send messages,
    ///   throws ``ResponseError`` with the code ``ResponseStatusCode/notSpeakableGroupGuestToSendMessage``.
    /// * If the logged-in user has been muted,
    ///   throws ``ResponseError`` with the code ``ResponseStatusCode/mutedGroupMemberSendMessage``.
    /// * If the target group has been deleted,
    ///   throws ``ResponseError`` with the code ``ResponseStatusCode/sendMessageToInactiveGroup``.
    /// * If the target group has been muted,
    ///   throws ``ResponseError`` with the code ``ResponseStatusCode/sendMessageToMutedGroup``.
    ///
    /// Notifications:
    /// * If the server property `turms.service.notification.message-created.notify-message-recipients`
    ///   is true (true by default), a new message notification will be sent to the message recipients actively.
    /// * If the server property `turms.service.notification.message-created.notify-requester-other-online-sessions`
    ///   is true (true by default), a new message notification will be sent to all other online sessions of the logged-in user actively.
    ///
    /// - Parameters:
    ///   - isGroupMessage: Whether the message is a group message.
    ///   - targetId: The target ID.
    ///     If `isGroupMessage` is true, the target ID is the group ID.
    ///     If `isGroupMessage` is false, the target ID is the user ID.
    ///   - deliveryDate: The delivery date.
    ///     Note that `deliveryDate` will only work if the server property
    ///     `turms.service.message.time-type` is `client_time` (`local_server_time` by default).
    ///   - text: The message text.
    ///     `text` can be anything you want. e.g. Markdown, base64 encoded bytes.
    ///     Note that if `text` is null, `records` must not be null.
    ///   - records: The message records.
    ///     `records` can be anything you want. e.g. base64 encoded images (it is highly not recommended).
    ///     Note that if `records` is null, `text` must not be null.
    ///   - burnAfter: The burn after the specified time.
    ///     Note that Turms server and client do NOT implement the `burn after` feature,
    ///     and they just store and pass `burnAfter` between server and clients.
    ///   - preMessageId: The pre-message ID.
    ///     `preMessageId` is mainly used to arrange the order of messages on UI.
    ///     If what you want is to ensure every message will not be lost, even if the server crashes,
    ///     you can set the server property `turms.service.message.use-conversation-id` to true
    ///     (false by default).
    ///
    /// - Returns: The message ID.
    ///
    /// - Throws: ``ResponseError`` if an error occurs.
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

    /// Forward a message.
    /// In other words, create and send a new message based on a existing message
    /// to the target user or group.
    ///
    /// Authorization:
    /// * If the logged-in user is not allowed to view the message with `messageId`,
    ///   throws ``ResponseError`` with the code ``ResponseStatusCode/notMessageRecipientOrSenderToForwardMessage``.
    ///
    /// Notifications:
    /// * If the server property `turms.service.notification.message-created.notify-message-recipients`
    ///   is true (true by default), a new message notification will be sent to the message recipients actively.
    /// * If the server property `turms.service.notification.message-created.notify-requester-other-online-sessions`
    ///   is true (true by default), a new message notification will be sent to all other online sessions of the logged-in user actively.
    ///
    /// - Parameters:
    ///   - messageId: The message ID for copying.
    ///   - isGroupMessage: Whether the message is a group message.
    ///   - targetId: The target ID.
    ///     If `isGroupMessage` is true, the target ID is the group ID.
    ///     If `isGroupMessage` is false, the target ID is the user ID.
    ///
    /// - Returns: The message ID.
    ///
    /// - Throws: ``ResponseError`` if an error occurs.
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

    /// Update a sent message.
    ///
    /// Authorization:
    /// * If the server property `turms.service.message.allow-send-messages-to-oneself`
    ///   is true (true by default), the logged-in user can update sent messages.
    ///   Otherwise, throws ``ResponseError`` with the code ``ResponseStatusCode/updatingMessageBySenderIsDisabled``.
    /// * If the message is not sent by the logged-in user,
    ///   throws ``ResponseError`` with the code ``ResponseStatusCode/notSenderToUpdateMessage``.
    /// * If the message is group message, and is sent by the logged-in user but the group
    ///   has been deleted,
    ///   throws ``ResponseError`` with the code ``ResponseStatusCode/updateMessageOfNonexistentGroup``.
    /// * If the message is group message, and the group type has disabled updating messages,
    ///   throws ``ResponseError`` with the code ``ResponseStatusCode/updatingGroupMessageBySenderIsDisabled``.
    ///
    /// Notifications:
    /// * If the server property `turms.service.notification.message-updated.notify-message-recipients`
    ///   is true (true by default), a message update notification will be sent to the message recipients actively.
    /// * If the server property `turms.service.notification.message-updated.notify-requester-other-online-sessions`
    ///   is true (true by default), a message update notification will be sent to all other online sessions of the logged-in user actively.
    ///
    /// - Parameters:
    ///   - messageId: The sent message ID.
    ///   - text: The new message text.
    ///     If null, the message text will not be changed.
    ///   - records: The new message records.
    ///     If null, the message records will not be changed.
    ///
    /// - Throws: ``ResponseError`` if an error occurs.
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

    /// Find messages.
    ///
    /// - Parameters:
    ///   - ids: The message IDs for querying.
    ///   - areGroupMessages: Whether the messages are group messages.
    ///     If the logged-in user is not a group member,
    ///     throws ``ResponseError`` with the code ``ResponseStatusCode/notGroupMemberToQueryGroupMessages``.
    ///     TODO: guest users of some group types should be able to query messages.
    ///   - areSystemMessages: Whether the messages are system messages.
    ///   - fromIds: The from IDs.
    ///     If `areGroupMessages` is true, the from ID is the group ID.
    ///     If `areGroupMessages` is false, the from ID is the user ID.
    ///   - deliveryDateStart: The start delivery date for querying.
    ///   - deliveryDateEnd: The end delivery date for querying.
    ///   - maxCount: The maximum count for querying.
    ///   - descending: Whether the messages are sorted in descending order.
    ///
    /// - Returns: List of messages.
    /// Note that the list only contains messages in which the logged-in user
    /// has permission to view.
    /// If the logged-in user has no permission to view specified messages,
    /// these messages will be filtered out on the server, and no error will be thrown,
    /// except for ``ResponseStatusCode/notGroupMemberToQueryGroupMessages`` mentioned above.
    ///
    /// - Throws: ``ResponseError`` if an error occurs.
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

    /// Find the pair of messages and the total count for each conversation.
    ///
    /// - Parameters:
    ///   - ids: The message IDs for querying.
    ///   - areGroupMessages: Whether the messages are group messages.
    ///   - areSystemMessages: Whether the messages are system messages.
    ///     If the logged-in user is not a group member,
    ///     throws ``ResponseError`` with the code ``ResponseStatusCode/notGroupMemberToQueryGroupMessages``.
    ///     TODO: guest users of some group types should be able to query messages.
    ///   - fromIds: The from IDs.
    ///     If `areGroupMessages` is true, the from ID is the group ID.
    ///     If `areGroupMessages` is false, the from ID is the user ID.
    ///   - deliveryDateStart: The start delivery date for querying.
    ///   - deliveryDateEnd: The end delivery date for querying.
    ///   - maxCount: The maximum count for querying.
    ///   - descending: Whether the messages are sorted in descending order.
    ///
    /// - Returns: List of the pair of messages and the total count for each conversation.
    /// Note that the list only contains messages in which the logged-in user
    /// has permission to view.
    /// If the logged-in user has no permission to view specified messages,
    /// these messages will be filtered out on the server, and no error will be thrown,
    /// except for ``ResponseStatusCode/notGroupMemberToQueryGroupMessages`` mentioned above.
    ///
    /// - Throws: ``ResponseError`` if an error occurs.
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

    /// Recall a message.
    ///
    /// Authorization:
    /// * If the server property `turms.service.message.allow-recall-message`
    ///   is true (true by default), the logged-in user can recall sent messages.
    ///   Otherwise, throws ``ResponseError`` with the code ``ResponseStatusCode/recallingMessageIsDisabled``.
    /// * If the message does not exist,
    ///   throws ``ResponseError`` with the code ``ResponseStatusCode/recallNonexistentMessage``.
    /// * If the message is group message, but the group has been deleted,
    ///   throws ``ResponseError`` with the code ``ResponseStatusCode/recallMessageOfNonexistentGroup``.
    ///
    /// Common Scenarios:
    /// * A client can call ``addMessageListener`` to listen to recalled messages.
    ///   The listener will receive a non-empty ``MessageAddition/recalledMessageIds`` when a message is recalled.
    ///
    /// - Parameters:
    ///   - messageId: The message ID.
    ///   - recallDate: The recall date.
    ///     If null, the current date will be used.
    ///
    /// - Throws: ``ResponseError`` if an error occurs.
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
