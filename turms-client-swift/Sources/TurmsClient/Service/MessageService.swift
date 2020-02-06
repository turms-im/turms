import Foundation
import PromiseKit

public class MessageService {
    private weak var turmsClient: TurmsClient!

    init(_ turmsClient: TurmsClient) {
        self.turmsClient = turmsClient
    }

    public func sendMessage(
        chatType: ChatType,
        toId: Int64,
        deliveryDate: Date,
        text: String? = nil,
        records: [[UInt8]]? = nil,
        burnAfter: Int32? = nil) throws -> Promise<Int64> {
        if Validator.areAllNil(text, records) {
            throw TurmsBusinessError(.illegalArguments)
        }
        return turmsClient.driver
            .send(RequestBuilder.newInstance()
                .request("createMessageRequest")
                .field("chatType", chatType)
                .field("toId", toId)
                .field("deliveryDate", deliveryDate)
                .wrappedField("text", text)
                .field("records", records)
                .wrappedField("burnAfter", burnAfter))
            .map { $0.data.ids.values[0] }
    }

    public func forwardMessage(
        messageId: Int64,
        chatType: ChatType,
        toId: Int64) -> Promise<Int64> {
        return turmsClient.driver
            .send(RequestBuilder.newInstance()
                .request("createMessageRequest")
                .wrappedField("messageId", messageId)
                .field("chatType", chatType)
                .field("toId", toId))
            .map { $0.data.ids.values[0] }
    }

    public func updateSentMessage(
        messageId: Int64,
        text: String? = nil,
        records: [[UInt8]]? = nil) -> Promise<Void> {
        if Validator.areAllNil(text, records) {
            return Promise.value(())
        }
        return turmsClient.driver
            .send(RequestBuilder.newInstance()
                .request("updateMessageRequest")
                .field("messageId", messageId)
                .wrappedField("text", text)
                .field("records", records))
            .map { _ in () }
    }

    public func queryMessages(
        ids: [Int64]? = nil,
        chatType: ChatType?,
        areSystemMessages: Bool? = nil,
        fromId: Int64? = nil,
        deliveryDateAfter: Date? = nil,
        deliveryDateBefore: Date? = nil,
        deliveryStatus: MessageDeliveryStatus? = nil,
        size: Int32 = 50) -> Promise<[Message]> {
        return turmsClient.driver
            .send(RequestBuilder.newInstance()
                .request("queryMessagesRequest")
                .wrappedField("ids", ids)
                .field("chatType", chatType)
                .wrappedField("areSystemMessages", areSystemMessages)
                .wrappedField("fromId", fromId)
                .wrappedField("deliveryDateAfter", deliveryDateAfter)
                .wrappedField("deliveryDateBefore", deliveryDateBefore)
                .wrappedField("size", size)
                .field("deliveryStatus", deliveryStatus))
            .map { $0.data.messages.messages }
    }

    public func queryPendingMessagesWithTotal(size: Int32 = 1) -> Promise<[MessagesWithTotal]> {
        return turmsClient.driver
            .send(RequestBuilder.newInstance()
                .request("queryPendingMessagesWithTotalRequest")
                .wrappedField("size", size))
            .map { $0.data.messagesWithTotalList.messagesWithTotalList }
    }

    public func queryMessageStatus(messageId: Int64) -> Promise<[MessageStatus]> {
        return turmsClient.driver
            .send(RequestBuilder.newInstance()
                .request("queryMessageStatusesRequest")
                .field("messageId", messageId))
            .map { $0.data.messageStatuses.messageStatuses }
    }

    public func recallMessage(messageId: Int64, recallDate: Date = Date()) -> Promise<Void> {
        return turmsClient.driver
            .send(RequestBuilder.newInstance()
                .request("updateMessageRequest")
                .field("messageId", messageId)
                .wrappedField("recallDate", recallDate))
            .map { _ in () }
    }

    public func readMessage(messageId: Int64, readDate: Date = Date()) -> Promise<Void> {
        return turmsClient.driver
            .send(RequestBuilder.newInstance()
                .request("updateMessageRequest")
                .field("messageId", messageId)
                .wrappedField("readDate", readDate))
            .map { _ in () }
    }

    public func markMessageUnread(messageId: Int64) -> Promise<Void> {
        return readMessage(
            messageId: messageId,
            readDate: Date(timeIntervalSince1970: 0))
    }

    public func updateTypingStatusRequest(chatType: ChatType, toId: Int64) -> Promise<Void> {
        return turmsClient.driver
            .send(RequestBuilder.newInstance()
                .request("updateTypingStatusRequest")
                .field("chatType", chatType)
                .field("toId", toId))
            .map { _ in () }
    }

    public static func generateLocationRecord(latitude: Float, longitude: Float, locationName: String? = nil, address: String? = nil) -> Data {
        return try! UserLocation.with {
            $0.latitude = latitude
            $0.longitude = longitude
            if locationName != nil {
                $0.name.value = locationName!
            }
            if address != nil {
                $0.address.value = address!
            }
        }.serializedData()
    }

    public static func generateAudioRecordByDescription(url: String, duration: Int32? = nil, format: String? = nil, size: Int32? = nil) -> Data {
        return try! AudioFile.with {
            $0.description_p.url = url
            if duration != nil {
                $0.description_p.duration.value = duration!
            }
            if format != nil {
                $0.description_p.format.value = format!
            }
            if size != nil {
                $0.description_p.size.value = size!
            }
        }.serializedData()
    }

    public static func generateAudioRecordByData(data: Data) -> Data {
        return try! AudioFile.with {
            $0.data.value = data
        }.serializedData()
    }

    public static func generateVideoRecordByDescription(url: String, duration: Int32? = nil, format: String? = nil, size: Int32? = nil) -> Data {
        return try! VideoFile.with {
            $0.description_p.url = url
            if duration != nil {
                $0.description_p.duration.value = duration!
            }
            if format != nil {
                $0.description_p.format.value = format!
            }
            if size != nil {
                $0.description_p.size.value = size!
            }
        }.serializedData()
    }

    public static func generateVideoRecordByData(data: Data) -> Data {
        return try! VideoFile.with {
            $0.data.value = data
        }.serializedData()
    }

    public static func generateImageRecordByData(data: Data) -> Data {
        return try! ImageFile.with {
            $0.data.value = data
        }.serializedData()
    }

    public static func generateImageRecordByDescription(url: String, fileSize: Int32? = nil, imageSize: Int32? = nil, original: Bool? = nil) -> Data {
        return try! ImageFile.with {
            $0.description_p.url = url
            if fileSize != nil {
                $0.description_p.fileSize.value = fileSize!
            }
            if imageSize != nil {
                $0.description_p.imageSize.value = imageSize!
            }
            if original != nil {
                $0.description_p.original.value = original!
            }
        }.serializedData()
    }

    public static func generateFileRecordByDate(data: Data) -> Data {
        return try! File.with {
            $0.data.value = data
        }.serializedData()
    }

    public static func generateFileRecordByDescription(url: String, format: String? = nil, size: Int32? = nil) -> Data {
        return try! File.with {
            $0.description_p.url = url
            if format != nil {
                $0.description_p.format.value = format!
            }
            if size != nil {
                $0.description_p.size.value = size!
            }
        }.serializedData()
    }
}
