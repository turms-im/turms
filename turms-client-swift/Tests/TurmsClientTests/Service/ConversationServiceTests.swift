import PromiseKit
@testable import TurmsClient
import XCTest

class ConversationServiceTests: XCTestCase {
    static let USER_ID: Int64 = 1
    static let RELATED_USER_ID: Int64 = 2
    static let GROUP_ID: Int64 = 1
    var turmsClient: TurmsClient!

    func test_e2e() {
        // Set up
        continueAfterFailure = false
        turmsClient = TurmsClient(Config.HOST, Config.PORT)
        wait(turmsClient.userService.login(userId: ConversationServiceTests.USER_ID, password: "123"))

        // Update
        let service = turmsClient.conversationService!
        assertCompleted("updatePrivateConversationReadDate_shouldSucceed", service.updatePrivateConversationReadDate(ConversationServiceTests.RELATED_USER_ID))
        assertCompleted("updateGroupConversationReadDate_shouldSucceed", service.updateGroupConversationReadDate(ConversationServiceTests.GROUP_ID))
        assertCompleted("updatePrivateConversationTypingStatus_shouldSucceed", service.updatePrivateConversationTypingStatus(ConversationServiceTests.RELATED_USER_ID))
        assertCompleted("updateGroupConversationTypingStatus_shouldSucceed", service.updateGroupConversationTypingStatus(ConversationServiceTests.GROUP_ID))

        // Query
        assertCompleted("queryPrivateConversations_shouldReturnNotEmptyConversations", service.queryPrivateConversations([ConversationServiceTests.RELATED_USER_ID]).done {
            XCTAssertFalse($0.data.isEmpty)
        })
        assertCompleted("queryGroupConversations_shouldReturnNotEmptyConversations", service.queryGroupConversations([ConversationServiceTests.GROUP_ID]).done {
            XCTAssertFalse($0.data.isEmpty)
        })

        // Tear down
        wait(turmsClient.driver.disconnect())
    }
}
