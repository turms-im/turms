import PromiseKit
@testable import TurmsClient
import XCTest

class ConversationServiceTests: XCTestCase {
    static let USER_ID: Int64 = 1
    static let RELATED_USER_ID: Int64 = 2
    static let GROUP_ID: Int64 = 1
    var turmsClient: TurmsClient!

    func test_system() {
        // Set up
        continueAfterFailure = false
        turmsClient = TurmsClient(Config.WS_URL)
        TestUtil.wait(turmsClient.driver.connect(userId: ConversationServiceTests.USER_ID, password: "123"))

        // Update
        TestUtil.assertCompleted("updatePrivateConversationReadDate_shouldSucceed", turmsClient.conversationService.updatePrivateConversationReadDate(ConversationServiceTests.RELATED_USER_ID))
        TestUtil.assertCompleted("updateGroupConversationReadDate_shouldSucceed", turmsClient.conversationService.updateGroupConversationReadDate(ConversationServiceTests.GROUP_ID))
        TestUtil.assertCompleted("updatePrivateConversationTypingStatus_shouldSucceed", turmsClient.conversationService.updatePrivateConversationTypingStatus(ConversationServiceTests.RELATED_USER_ID))
        TestUtil.assertCompleted("updateGroupConversationTypingStatus_shouldSucceed", turmsClient.conversationService.updateGroupConversationTypingStatus(ConversationServiceTests.GROUP_ID))

        // Query
        TestUtil.assertCompleted("queryPrivateConversations_shouldReturnNotEmptyConversations", turmsClient.conversationService.queryPrivateConversations([ConversationServiceTests.RELATED_USER_ID]).done {
            XCTAssertFalse($0.isEmpty)
            })
        TestUtil.assertCompleted("queryGroupConversations_shouldReturnNotEmptyConversations", turmsClient.conversationService.queryGroupConversations([ConversationServiceTests.GROUP_ID]).done {
            XCTAssertFalse($0.isEmpty)
        })

        // Tear down
        TestUtil.wait(turmsClient.driver.disconnect())
    }
}
