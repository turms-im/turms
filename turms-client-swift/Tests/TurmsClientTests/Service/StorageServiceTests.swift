import PromiseKit
@testable import TurmsClient
import XCTest

class StorageServiceTests: XCTestCase {
    var turmsClient: TurmsClient!
    static let USER_ID: Int64 = 1
    static let GROUP_ID: Int64 = 1
    static let PROFILE_PICTURE = Data([0, 1, 2, 3, 4])
    static let ATTACHMENT: Data = Data([0, 1, 2, 3, 4])

    override func setUp() {
        continueAfterFailure = false
        turmsClient = TurmsClient(Config.WS_URL, storageServerUrl: Config.STORAGE_SERVER_URL)
        TestUtil.wait(turmsClient.userService.login(userId: StorageServiceTests.USER_ID, password: "123"))
    }

    override func tearDown() {
        TestUtil.wait(turmsClient.driver.disconnect())
    }

    func test_system() {
        var messageId: Int64!
        // Create
        TestUtil.assertCompleted("uploadProfilePicture_shouldReturnUrl", turmsClient.storageService.uploadProfilePicture(StorageServiceTests.PROFILE_PICTURE))
        TestUtil.assertCompleted("uploadGroupProfilePicture_shouldReturnUrl", turmsClient.storageService.uploadGroupProfilePicture(data: StorageServiceTests.PROFILE_PICTURE, groupId: StorageServiceTests.GROUP_ID))

        TestUtil.assertCompleted("uploadAttachment_sendMessage_shouldReturnUrl", turmsClient.messageService.sendMessage(isGroupMessage: false, targetId: 2, text: "I've attached a picture").done {
            messageId = $0
        })
        TestUtil.assertCompleted("uploadAttachment_shouldReturnUrl", turmsClient.storageService.uploadAttachment(messageId: messageId, data: StorageServiceTests.ATTACHMENT))

        // Query
        TestUtil.assertCompleted("queryProfilePicture_shouldEqualUploadedPicture", turmsClient.storageService.queryProfilePicture(StorageServiceTests.USER_ID).done {
            XCTAssertEqual(StorageServiceTests.PROFILE_PICTURE, $0)
        })
        TestUtil.assertCompleted("queryGroupProfilePicture_shouldEqualUploadedPicture", turmsClient.storageService.queryGroupProfilePicture(StorageServiceTests.GROUP_ID).done {
            XCTAssertEqual(StorageServiceTests.PROFILE_PICTURE, $0)
        })
        TestUtil.assertCompleted("queryAttachment_shouldEqualUploadedAttachment", turmsClient.storageService.queryAttachment(messageId: messageId!).done {
            XCTAssertEqual(StorageServiceTests.ATTACHMENT, $0)
        })

        // Delete
        TestUtil.assertCompleted("deleteProfile_shouldSucceed", turmsClient.storageService.deleteProfile())
        TestUtil.assertCompleted("deleteGroupProfile_shouldSucceed", turmsClient.storageService.deleteGroupProfile(StorageServiceTests.GROUP_ID))
    }
}
