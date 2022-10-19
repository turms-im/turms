import PromiseKit
@testable import TurmsClient
import XCTest

class MessageServiceTests: XCTestCase {
    static let SENDER_ID: Int64 = 1
    static let RECIPIENT_ID: Int64 = 2
    static let GROUP_MEMBER_ID: Int64 = 3
    static let TARGET_GROUP_ID: Int64 = 1
    var senderClient: TurmsClient!
    var recipientClient: TurmsClient!
    var groupMemberClient: TurmsClient!

    func test_e2e() {
        var privateMessageId: Int64?
        var groupMessageId: Int64?

        // Set up
        continueAfterFailure = false
        senderClient = TurmsClient(Config.HOST, Config.PORT)
        recipientClient = TurmsClient(Config.HOST, Config.PORT)
        groupMemberClient = TurmsClient(Config.HOST, Config.PORT)
        wait(senderClient.userService.login(userId: MessageServiceTests.SENDER_ID, password: "123"))
        wait(recipientClient.userService.login(userId: MessageServiceTests.RECIPIENT_ID, password: "123"))
        wait(groupMemberClient.userService.login(userId: MessageServiceTests.GROUP_MEMBER_ID, password: "123"))

        let service = senderClient.messageService!

        // Create
        assertCompleted("sendPrivateMessage_shouldReturnMessageId", service.sendMessage(isGroupMessage: false, targetId: MessageServiceTests.RECIPIENT_ID, deliveryDate: Date(), text: "hello").done {
            privateMessageId = $0.data
        })
        assertCompleted("sendGroupMessage_shouldReturnMessageId", service.sendMessage(isGroupMessage: true, targetId: MessageServiceTests.TARGET_GROUP_ID, deliveryDate: Date(), text: "hello").done {
            groupMessageId = $0.data
        })
        assertCompleted("forwardPrivateMessage_shouldReturnForwardedMessageId", service.forwardMessage(messageId: privateMessageId!, isGroupMessage: false, targetId: MessageServiceTests.RECIPIENT_ID))
        assertCompleted("forwardGroupMessage_shouldReturnForwardedMessageId", service.forwardMessage(messageId: groupMessageId!, isGroupMessage: true, targetId: MessageServiceTests.TARGET_GROUP_ID))

        // Update
        assertCompleted("recallMessage_shouldSucceed", service.recallMessage(messageId: groupMessageId!))
        assertCompleted("updateSentMessage_shouldSucceed", service.updateSentMessage(messageId: privateMessageId!, text: "I have modified the message"))

        // Query
        assertCompleted("queryMessages_shouldReturnNotEmptyMessages", recipientClient.messageService.queryMessages(areGroupMessages: false, fromIds: [MessageServiceTests.SENDER_ID], maxCount: 10).done {
            XCTAssertFalse($0.data.isEmpty)
        })
        assertCompleted("queryMessagesWithTotal_shouldReturnNotEmptyMessagesWithTotal", service.queryMessagesWithTotal(areGroupMessages: false, fromIds: [MessageServiceTests.SENDER_ID], maxCount: 1).done {
            XCTAssertFalse($0.data.isEmpty)
        })

        // Tear down
        wait(senderClient.driver.disconnect())
        wait(recipientClient.driver.disconnect())
        wait(groupMemberClient.driver.disconnect())
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
