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

    func test_system() {
        var privateMessageId: Int64?
        var groupMessageId: Int64?

        // Set up
        continueAfterFailure = false
        senderClient = TurmsClient(Config.WS_URL)
        recipientClient = TurmsClient(Config.WS_URL)
        groupMemberClient = TurmsClient(Config.WS_URL)
        TestUtil.wait(senderClient.driver.connect(userId: MessageServiceTests.SENDER_ID, password: "123"))
        TestUtil.wait(recipientClient.driver.connect(userId: MessageServiceTests.RECIPIENT_ID, password: "123"))
        TestUtil.wait(groupMemberClient.driver.connect(userId: MessageServiceTests.GROUP_MEMBER_ID, password: "123"))

        // Create
        TestUtil.assertCompleted("sendPrivateMessage_shouldReturnMessageId", senderClient.messageService.sendMessage(isGroupMessage: false, targetId: MessageServiceTests.RECIPIENT_ID, deliveryDate: Date(), text: "hello").done {
            privateMessageId = $0
        })
        TestUtil.assertCompleted("sendGroupMessage_shouldReturnMessageId", senderClient.messageService.sendMessage(isGroupMessage: true, targetId: MessageServiceTests.TARGET_GROUP_ID, deliveryDate: Date(), text: "hello").done {
            groupMessageId = $0
        })
        TestUtil.assertCompleted("forwardPrivateMessage_shouldReturnForwardedMessageId", senderClient.messageService.forwardMessage(messageId: privateMessageId!, isGroupMessage: false, targetId: MessageServiceTests.RECIPIENT_ID))
        TestUtil.assertCompleted("forwardGroupMessage_shouldReturnForwardedMessageId", senderClient.messageService.forwardMessage(messageId: groupMessageId!, isGroupMessage: true, targetId: MessageServiceTests.TARGET_GROUP_ID))

        // Update
        TestUtil.assertCompleted("recallMessage_shouldSucceed", senderClient.messageService.recallMessage(messageId: groupMessageId!))
        TestUtil.assertCompleted("updateSentMessage_shouldSucceed", senderClient.messageService.updateSentMessage(messageId: privateMessageId!, text: "I have modified the message"))
        TestUtil.assertCompleted("readMessage_shouldSucceed", recipientClient.messageService.readMessage(messageId: privateMessageId!))
        TestUtil.assertCompleted("markMessageUnread_shouldSucceed", recipientClient.messageService.markMessageUnread(privateMessageId!))
        TestUtil.assertCompleted("updateTypingStatus_shouldSucceed", senderClient.messageService.updateTypingStatusRequest(isGroupMessage: false, toId: privateMessageId!))

        // Query
        TestUtil.assertCompleted("queryMessages_shouldReturnNotEmptyMessages", recipientClient.messageService.queryMessages(areGroupMessages: false, fromId: MessageServiceTests.SENDER_ID, size: 10).done {
            XCTAssertFalse($0.isEmpty)
        })
        TestUtil.assertCompleted("queryPendingMessagesWithTotal_shouldReturnNotEmptyPendingMessagesWithTotal", senderClient.messageService.queryPendingMessagesWithTotal().done {
            XCTAssertFalse($0.isEmpty)
        })
        TestUtil.assertCompleted("queryMessageStatus_shouldReturnNotEmptyMessageStatus", senderClient.messageService.queryMessageStatus(groupMessageId!).then { statusesOfMember1 in
            self.groupMemberClient.messageService.queryMessageStatus(groupMessageId!).done { statusesOfMember2 in
                XCTAssertTrue(statusesOfMember1[0].messageID.value == statusesOfMember2[0].messageID.value)
            }
        })

        // Tear down
        TestUtil.wait(senderClient.driver.disconnect())
        TestUtil.wait(recipientClient.driver.disconnect())
        TestUtil.wait(groupMemberClient.driver.disconnect())
    }

    // Util

    func test_generateLocationRecord() {
        let data = MessageService.generateLocationRecord(latitude: 1, longitude: 1, locationName: "name", address: "address")
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
