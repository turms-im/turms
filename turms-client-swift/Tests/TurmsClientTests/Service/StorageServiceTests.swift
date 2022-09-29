import PromiseKit
@testable import TurmsClient
import XCTest

class StorageServiceTests: XCTestCase {
    var turmsClient: TurmsClient!
    static let USER_ID: Int64 = 1
    static let GROUP_ID: Int64 = 1
    static let PROFILE_PICTURE = Data([0, 1, 2, 3, 4])
    static let ATTACHMENT: Data = .init([0, 1, 2, 3, 4])

    override func setUp() {
        continueAfterFailure = false
        turmsClient = TurmsClient(Config.HOST, Config.PORT, storageServerUrl: Config.STORAGE_SERVER_URL)
        wait(turmsClient.userService.login(userId: StorageServiceTests.USER_ID, password: "123"))
    }

    override func tearDown() {
        wait(turmsClient.userService.logout())
    }

    func test_e2e() {
        var messageId: Int64!
        // Create
        assertCompleted("uploadProfilePicture_shouldReturnUrl", turmsClient.storageService.uploadProfilePicture(StorageServiceTests.PROFILE_PICTURE))
        assertCompleted("uploadGroupProfilePicture_shouldReturnUrl", turmsClient.storageService.uploadGroupProfilePicture(data: StorageServiceTests.PROFILE_PICTURE, groupId: StorageServiceTests.GROUP_ID))

        assertCompleted("uploadAttachment_sendMessage_shouldReturnUrl", turmsClient.messageService.sendMessage(isGroupMessage: false, targetId: 2, text: "I've attached a picture").done {
            messageId = $0.data
        })
        assertCompleted("uploadAttachment_shouldReturnUrl", turmsClient.storageService.uploadAttachment(messageId: messageId, data: StorageServiceTests.ATTACHMENT))

        // Query
        assertCompleted("queryProfilePicture_shouldEqualUploadedPicture", turmsClient.storageService.queryProfilePicture(StorageServiceTests.USER_ID).done {
            XCTAssertEqual(StorageServiceTests.PROFILE_PICTURE, $0.data)
        })
        assertCompleted("queryGroupProfilePicture_shouldEqualUploadedPicture", turmsClient.storageService.queryGroupProfilePicture(StorageServiceTests.GROUP_ID).done {
            XCTAssertEqual(StorageServiceTests.PROFILE_PICTURE, $0.data)
        })
        assertCompleted("queryAttachment_shouldEqualUploadedAttachment", turmsClient.storageService.queryAttachment(messageId: messageId!).done {
            XCTAssertEqual(StorageServiceTests.ATTACHMENT, $0.data)
        })

        // Delete
        assertCompleted("deleteProfile_shouldSucceed", turmsClient.storageService.deleteProfile())
        assertCompleted("deleteGroupProfile_shouldSucceed", turmsClient.storageService.deleteGroupProfile(StorageServiceTests.GROUP_ID))
    }
}
