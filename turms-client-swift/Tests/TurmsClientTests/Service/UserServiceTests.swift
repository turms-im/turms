@testable import TurmsClient
import XCTest

class UserServiceTests: XCTestCase {
    private var turmsClient: TurmsClient!

    override func setUp() {
        continueAfterFailure = false
        turmsClient = TurmsClient(Config.HOST, Config.PORT)
    }

    override func tearDown() async throws {
        try await wait(await self.turmsClient.userService.logout())
    }

    func test_e2e() async throws {
        var relationshipGroupIndex: Int32!
        let userStatus = UserStatus.busy
        let service = turmsClient.userService!

        // Login
        try await assertCompleted("login_shouldSucceed", await service.login(userId: 1, password: "123"))

        // Create
        try await assertCompleted("createRelationship_shouldSucceed") {
            do {
                let result = try await service.createRelationship(userId: 10, isBlocked: true)
            } catch {
                guard let businessError = error as? ResponseError, businessError.code == ResponseStatusCode.createExistingRelationship.rawValue else {
                    throw error
                }
            }
        }
        try await assertCompleted("createFriendRelationship_shouldSucceed") {
            do {
                let result = try await service.createFriendRelationship(userId: 10)
            } catch {
                guard let businessError = error as? ResponseError, businessError.code == ResponseStatusCode.createExistingRelationship.rawValue else {
                    throw error
                }
            }
        }
        try await assertCompleted("createBlockedUserRelationship_shouldSucceed") {
            do {
                let result = try await service.createBlockedUserRelationship(userId: 10)
            } catch {
                guard let businessError = error as? ResponseError, businessError.code == ResponseStatusCode.createExistingRelationship.rawValue else {
                    throw error
                }
            }
        }
        try await assertCompleted("sendFriendRequest_shouldReturnFriendRequestId") {
            do {
                let result = try await service.sendFriendRequest(recipientId: 11, content: "content")
            } catch {
                guard let businessError = error as? ResponseError, businessError.code == ResponseStatusCode.createExistingFriendRequest.rawValue else {
                    throw error
                }
            }
        }
        try await assertCompleted("createRelationshipGroup_shouldReturnRelationshipGroupIndex") {
            let result = try await service.createRelationshipGroup("newGroup")
            relationshipGroupIndex = result.data
        }

        // Update
        try await assertCompleted("updateUserOnlineStatus_shouldSucceed", await service.updateUserOnlineStatus(userStatus))
        try await assertCompleted("updatePassword_shouldSucceed", await service.updatePassword("123"))
        try await assertCompleted("updateProfile_shouldSucceed", await service.updateProfile(name: "123", intro: "123"))
        try await assertCompleted("updateRelationship_shouldSucceed", await service.updateRelationship(relatedUserId: 10, groupIndex: 1))
        try await assertCompleted("replyFriendRequest_shouldSucceed", await service.replyFriendRequest(requestId: 10, responseAction: .accept, reason: "reason"))
        try await assertCompleted("updateRelationshipGroup_shouldSucceed", await service.updateRelationshipGroup(groupIndex: relationshipGroupIndex, newName: "newGroupName"))
        try await assertCompleted("moveRelatedUserToGroup_shouldSucceed", await service.moveRelatedUserToGroup(relatedUserId: 2, groupIndex: 1))
        try await wait(await service.moveRelatedUserToGroup(relatedUserId: 2, groupIndex: 0))
        try await assertCompleted("updateLocation_shouldSucceed", await service.updateLocation(latitude: 2, longitude: 2))

        // Query

        try await assertCompleted("queryUserProfiles_shouldReturnUserInfos", await service.queryUserProfiles(userIds: [1]))
        try await assertCompleted("queryNearbyUsers_shouldReturnNearbyUsers", await service.queryNearbyUsers(latitude: 1, longitude: 1))
        try await assertCompleted("queryUserOnlineStatusesRequest_shouldUserOnlineStatuses") {
            let result = try await service.queryUserOnlineStatusesRequest([1])
            XCTAssertEqual(result.data[0].userStatus, userStatus)
        }
        try await assertCompleted("queryRelationships_shouldReturnUserRelationshipsWithVersion", await service.queryRelationships(relatedUserIds: [2]))
        try await assertCompleted("queryRelatedUserIds_shouldReturnRelatedUserIds", await service.queryRelatedUserIds())
        try await assertCompleted("queryFriends_shouldReturnFriendRelationships", await service.queryFriends())
        try await assertCompleted("queryBlockedUsers_shouldReturnRelationshipsWithBlockedUsers", await service.queryBlockedUsers())
        try await assertCompleted("queryFriendRequests_shouldReturnFriendRequests", await service.queryFriendRequests(true))
        try await assertCompleted("queryRelationshipGroups_shouldReturnRelationshipGroups", await service.queryRelationshipGroups())

        // Delete
        try await assertCompleted("deleteRelationship_shouldSucceed", await service.deleteRelationship(relatedUserId: 10))
        try await assertCompleted("deleteRelationshipGroups_shouldSucceed", await service.deleteRelationshipGroups(groupIndex: relationshipGroupIndex))

        // Logout
        try await wait(await service.logout())
    }
}
