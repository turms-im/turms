@testable import TurmsClient
import XCTest

class StorageServiceTests: XCTestCase {
    private var turmsClient: TurmsClient!
    private static let USER_ID: Int64 = 1
    private static let GROUP_ID: Int64 = 1
    private static let MEDIA_TYPE = "image/png"
    private static let PROFILE_PICTURE = Data([0, 1, 2, 3, 4])
    private static let ATTACHMENT: Data = .init([0, 1, 2, 3, 4])

    override func setUp() async throws {
        continueAfterFailure = false
        turmsClient = TurmsClient(Config.HOST, Config.PORT, storageServerUrl: Config.STORAGE_SERVER_URL)
        try await wait(await self.turmsClient.userService.login(userId: StorageServiceTests.USER_ID, password: "123"))
    }

    override func tearDown() async throws {
        try await wait(await self.turmsClient.userService.logout())
    }

    func test_e2e() async throws {
        var attachmentId: Int64!
        // Create
        try await assertCompleted("uploadUserProfilePicture_shouldReturnUploadResult", await self.turmsClient.storageService.uploadUserProfilePicture(data: StorageServiceTests.PROFILE_PICTURE, mediaType: StorageServiceTests.MEDIA_TYPE))
        try await assertCompleted("uploadGroupProfilePicture_shouldReturnUploadResult", await self.turmsClient.storageService.uploadGroupProfilePicture(groupId: StorageServiceTests.GROUP_ID, data: StorageServiceTests.PROFILE_PICTURE, mediaType: StorageServiceTests.MEDIA_TYPE))

        try await assertCompleted("uploadMessageAttachment_shouldReturnUploadResult") {
            let result = try await self.turmsClient.storageService.uploadMessageAttachment(data: StorageServiceTests.ATTACHMENT, mediaType: StorageServiceTests.MEDIA_TYPE)
            attachmentId = result.data.resourceIdNum!
        }

        // Query
        try await assertCompleted("queryUserProfilePicture_shouldEqualUploadedPicture") {
            let result = try await self.turmsClient.storageService.queryUserProfilePicture(userId: StorageServiceTests.USER_ID)
            XCTAssertEqual(StorageServiceTests.PROFILE_PICTURE, result.data.data)
        }
        try await assertCompleted("queryGroupProfilePicture_shouldEqualUploadedPicture") {
            let result = try await self.turmsClient.storageService.queryGroupProfilePicture(groupId: StorageServiceTests.GROUP_ID)
            XCTAssertEqual(StorageServiceTests.PROFILE_PICTURE, result.data.data)
        }
        try await assertCompleted("queryMessageAttachment_shouldEqualUploadedAttachment") {
            let result = try await self.turmsClient.storageService.queryMessageAttachment(attachmentIdNum: attachmentId)
            XCTAssertEqual(StorageServiceTests.ATTACHMENT, result.data.data)
        }

        // Delete
        try await assertCompleted("deleteUserProfilePicture_shouldSucceed", await self.turmsClient.storageService.deleteUserProfilePicture())
        try await assertCompleted("deleteGroupProfilePicture_shouldSucceed", await self.turmsClient.storageService.deleteGroupProfilePicture(groupId: StorageServiceTests.GROUP_ID))
    }
}
