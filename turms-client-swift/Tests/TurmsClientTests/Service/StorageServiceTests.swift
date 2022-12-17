import PromiseKit
@testable import TurmsClient
import XCTest

class StorageServiceTests: XCTestCase {
    var turmsClient: TurmsClient!
    static let USER_ID: Int64 = 1
    static let GROUP_ID: Int64 = 1
    static let MEDIA_TYPE = "image/png"
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
        var attachmentId: Int64!
        // Create
        assertCompleted("uploadUserProfilePicture_shouldReturnUploadResult", turmsClient.storageService.uploadUserProfilePicture(data: StorageServiceTests.PROFILE_PICTURE, mediaType: StorageServiceTests.MEDIA_TYPE))
        assertCompleted("uploadGroupProfilePicture_shouldReturnUploadResult", turmsClient.storageService.uploadGroupProfilePicture(groupId: StorageServiceTests.GROUP_ID, data: StorageServiceTests.PROFILE_PICTURE, mediaType: StorageServiceTests.MEDIA_TYPE))

        assertCompleted("uploadMessageAttachment_shouldReturnUploadResult", turmsClient.storageService.uploadMessageAttachment(data: StorageServiceTests.ATTACHMENT, mediaType: StorageServiceTests.MEDIA_TYPE).done {
            attachmentId = $0.data.resourceIdNum!
        })

        // Query
        assertCompleted("queryUserProfilePicture_shouldEqualUploadedPicture", turmsClient.storageService.queryUserProfilePicture(userId: StorageServiceTests.USER_ID).done {
            XCTAssertEqual(StorageServiceTests.PROFILE_PICTURE, $0.data.data)
        })
        assertCompleted("queryGroupProfilePicture_shouldEqualUploadedPicture", turmsClient.storageService.queryGroupProfilePicture(groupId: StorageServiceTests.GROUP_ID).done {
            XCTAssertEqual(StorageServiceTests.PROFILE_PICTURE, $0.data.data)
        })
        assertCompleted("queryMessageAttachment_shouldEqualUploadedAttachment", turmsClient.storageService.queryMessageAttachment(attachmentIdNum: attachmentId).done {
            XCTAssertEqual(StorageServiceTests.ATTACHMENT, $0.data.data)
        })

        // Delete
        assertCompleted("deleteUserProfilePicture_shouldSucceed", turmsClient.storageService.deleteUserProfilePicture())
        assertCompleted("deleteGroupProfilePicture_shouldSucceed", turmsClient.storageService.deleteGroupProfilePicture(groupId: StorageServiceTests.GROUP_ID))
    }
}
