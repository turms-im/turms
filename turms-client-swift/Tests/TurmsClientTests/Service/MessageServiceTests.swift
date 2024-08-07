@testable import TurmsClient
import XCTest

class MessageServiceTests: XCTestCase {
    private static let SENDER_ID: Int64 = 1
    private static let RECIPIENT_ID: Int64 = 2
    private static let GROUP_MEMBER_ID: Int64 = 3
    private static let TARGET_GROUP_ID: Int64 = 1
    private var senderClient: TurmsClient!
    private var recipientClient: TurmsClient!
    private var groupMemberClient: TurmsClient!

    func test_e2e() async throws {
        var privateMessageId: Int64?
        var groupMessageId: Int64?

        // Set up
        continueAfterFailure = false
        senderClient = TurmsClient(Config.HOST, Config.PORT)
        recipientClient = TurmsClient(Config.HOST, Config.PORT)
        groupMemberClient = TurmsClient(Config.HOST, Config.PORT)
        try await wait(await self.senderClient.userService.login(userId: MessageServiceTests.SENDER_ID, password: "123"))
        try await wait(await self.recipientClient.userService.login(userId: MessageServiceTests.RECIPIENT_ID, password: "123"))
        try await wait(await self.groupMemberClient.userService.login(userId: MessageServiceTests.GROUP_MEMBER_ID, password: "123"))

        let service = senderClient.messageService!

        // Create
        try await assertCompleted("sendPrivateMessage_shouldReturnMessageId") {
            let result = try await service.sendMessage(isGroupMessage: false, targetId: MessageServiceTests.RECIPIENT_ID, deliveryDate: Date(), text: "hello")
            privateMessageId = result.data
        }
        try await assertCompleted("sendGroupMessage_shouldReturnMessageId") {
            let result = try await service.sendMessage(isGroupMessage: true, targetId: MessageServiceTests.TARGET_GROUP_ID, deliveryDate: Date(), text: "hello")
            groupMessageId = result.data
        }
        try await assertCompleted("forwardPrivateMessage_shouldReturnForwardedMessageId", await service.forwardMessage(messageId: privateMessageId!, isGroupMessage: false, targetId: MessageServiceTests.RECIPIENT_ID))
        try await assertCompleted("forwardGroupMessage_shouldReturnForwardedMessageId", await service.forwardMessage(messageId: groupMessageId!, isGroupMessage: true, targetId: MessageServiceTests.TARGET_GROUP_ID))

        // Update
        try await assertCompleted("recallMessage_shouldSucceed", await service.recallMessage(messageId: groupMessageId!))
        try await assertCompleted("updateSentMessage_shouldSucceed", await service.updateSentMessage(messageId: privateMessageId!, text: "I have modified the message"))

        // Query
        try await assertCompleted("queryMessages_shouldReturnNotEmptyMessages") {
            let messages = try await self.recipientClient.messageService.queryMessages(areGroupMessages: false, fromIds: [MessageServiceTests.SENDER_ID], maxCount: 10)
            XCTAssertFalse(messages.data.isEmpty)
        }
        try await assertCompleted("queryMessagesWithTotal_shouldReturnNotEmptyMessagesWithTotal") {
            let messages = try await service.queryMessagesWithTotal(areGroupMessages: false, fromIds: [MessageServiceTests.SENDER_ID], maxCount: 1)
            XCTAssertFalse(messages.data.isEmpty)
        }

        // Tear down
        try await wait(await self.senderClient.driver.disconnect())
        try await wait(await self.recipientClient.driver.disconnect())
        try await wait(await self.groupMemberClient.driver.disconnect())
    }

    // Util

    func test_generateLocationRecord() {
        let data = MessageService.generateLocationRecord(latitude: 1, longitude: 1, details: ["name": "value"])
        XCTAssertNotNil(data)
    }

    func test_generateAudioRecordByDescription() {
        let data = MessageService.generateAudioRecordByDescription(url: "https://abc.com")
        XCTAssertNotNil(data)
    }

    func test_generateAudioRecordByData() {
        let data = MessageService.generateAudioRecordByData(Data(bytes: [1, 2, 3], count: 3))
        XCTAssertNotNil(data)
    }

    func test_generateVideoRecordByDescription() {
        let data = MessageService.generateVideoRecordByDescription(url: "https://abc.com")
        XCTAssertNotNil(data)
    }

    func test_generateVideoRecordByData() {
        let data = MessageService.generateVideoRecordByData(Data(bytes: [1, 2, 3], count: 3))
        XCTAssertNotNil(data)
    }

    func test_generateImageRecordByData() {
        let data = MessageService.generateImageRecordByData(Data(bytes: [1, 2, 3], count: 3))
        XCTAssertNotNil(data)
    }

    func test_generateFileRecordByDate() {
        let data = MessageService.generateFileRecordByDate(Data(bytes: [1, 2, 3], count: 3))
        XCTAssertNotNil(data)
    }

    func test_generateImageRecordByDescription() {
        let data = MessageService.generateImageRecordByDescription(url: "https://abc.com")
        XCTAssertNotNil(data)
    }

    func test_generateFileRecordByDescription() {
        let data = MessageService.generateFileRecordByDescription(url: "https://abc.com")
        XCTAssertNotNil(data)
    }
}
