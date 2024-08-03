@testable import TurmsClient
import XCTest

class ConversationServiceTests: XCTestCase {
    private static let USER_ID: Int64 = 1
    private static let RELATED_USER_ID: Int64 = 2
    private static let GROUP_ID: Int64 = 1
    private var turmsClient: TurmsClient!

    func test_e2e() async throws {
        // Set up
        continueAfterFailure = false
        turmsClient = TurmsClient(Config.HOST, Config.PORT)
        try await wait(await self.turmsClient.userService.login(userId: ConversationServiceTests.USER_ID, password: "123"))

        // Update
        let service = turmsClient.conversationService!
        try await assertCompleted("updatePrivateConversationReadDate_shouldSucceed", await service.updatePrivateConversationReadDate(ConversationServiceTests.RELATED_USER_ID))
        try await assertCompleted("updateGroupConversationReadDate_shouldSucceed", await service.updateGroupConversationReadDate(ConversationServiceTests.GROUP_ID))
        try await assertCompleted("updatePrivateConversationTypingStatus_shouldSucceed", await service.updatePrivateConversationTypingStatus(ConversationServiceTests.RELATED_USER_ID))
        try await assertCompleted("updateGroupConversationTypingStatus_shouldSucceed", await service.updateGroupConversationTypingStatus(ConversationServiceTests.GROUP_ID))

        // Query
        try await assertCompleted("queryPrivateConversations_shouldReturnNotEmptyConversations") {
            let conversations = try await service.queryPrivateConversations([ConversationServiceTests.RELATED_USER_ID])
            XCTAssertFalse(conversations.data.isEmpty)
        }
        try await assertCompleted("queryGroupConversations_shouldReturnNotEmptyConversations") {
            let conversations = try await service.queryGroupConversations([ConversationServiceTests.GROUP_ID])
            XCTAssertFalse(conversations.data.isEmpty)
        }

        // Tear down
        try await wait(await self.turmsClient.driver.disconnect())
    }
}
