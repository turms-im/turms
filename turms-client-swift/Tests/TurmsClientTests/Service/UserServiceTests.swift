import PromiseKit
@testable import TurmsClient
import XCTest

class UserServiceTests: XCTestCase {
    var turmsClient: TurmsClient!

    override func setUp() {
        continueAfterFailure = false
        turmsClient = TurmsClient(Config.WS_URL)
    }

    override func tearDown() {
        TestUtil.wait(turmsClient.driver.disconnect())
    }

    func test_system() {
        var relationshipGroupIndex: Int32!
        let userStatus = UserStatus.busy
        // Login
        TestUtil.assertCompleted("login_shouldSucceed", turmsClient.userService.login(userId: 1, password: "123"))
        TestUtil.wait(turmsClient.userService.logout())
        TestUtil.assertCompleted("relogin_shouldSucceed", turmsClient.userService.relogin())

        // Create
        TestUtil.assertCompleted("createRelationship_shouldSucceed", turmsClient.userService.createRelationship(userId: 10, isBlocked: true).recover { error -> Promise<Void> in
            if let businessError = error as? TurmsBusinessException, businessError.code == .relationshipHasEstablished {
                return Promise.value(())
            } else {
                throw error
            }
        })
        TestUtil.assertCompleted("createFriendRelationship_shouldSucceed", turmsClient.userService.createFriendRelationship(userId: 10).recover { error -> Promise<Void> in
            if let businessError = error as? TurmsBusinessException, businessError.code == .relationshipHasEstablished {
                return Promise.value(())
            } else {
                throw error
            }
        })
        TestUtil.assertCompleted("createBlacklistedUserRelationship_shouldSucceed", turmsClient.userService.createBlacklistedUserRelationship(userId: 10).recover { error -> Promise<Void> in
            if let businessError = error as? TurmsBusinessException, businessError.code == .relationshipHasEstablished {
                return Promise.value(())
            } else {
                throw error
            }
        })
        TestUtil.assertCompleted("sendFriendRequest_shouldReturnFriendRequestId", turmsClient.userService.sendFriendRequest(recipientId: 11, content: "content").recover { error -> Promise<Int64> in
            if let businessError = error as? TurmsBusinessException, businessError.code == .friendRequestHasExisted {
                return Promise.value(0)
            } else {
                throw error
            }
        })
        TestUtil.assertCompleted("createRelationshipGroup_shouldReturnRelationshipGroupIndex", turmsClient.userService.createRelationshipGroup("newGroup").done {
            relationshipGroupIndex = $0
        })

        // Update
        TestUtil.assertCompleted("updateUserOnlineStatus_shouldSucceed", turmsClient.userService.updateUserOnlineStatus(userStatus))
        TestUtil.assertCompleted("updatePassword_shouldSucceed", turmsClient.userService.updatePassword("123"))
        TestUtil.assertCompleted("updateProfile_shouldSucceed", turmsClient.userService.updateProfile(name: "123", intro: "123"))
        TestUtil.assertCompleted("updateRelationship_shouldSucceed", turmsClient.userService.updateRelationship(relatedUserId: 10, groupIndex: 1))
        TestUtil.assertCompleted("replyFriendRequest_shouldSucceed", turmsClient.userService.replyFriendRequest(requestId: 10, responseAction: .accept, reason: "reason"))
        TestUtil.assertCompleted("updateRelationshipGroup_shouldSucceed", turmsClient.userService.updateRelationshipGroup(groupIndex: relationshipGroupIndex, newName: "newGroupName"))
        TestUtil.assertCompleted("moveRelatedUserToGroup_shouldSucceed", turmsClient.userService.moveRelatedUserToGroup(relatedUserId: 2, groupIndex: 1))
        TestUtil.wait(turmsClient.userService.moveRelatedUserToGroup(relatedUserId: 2, groupIndex: 0))
        TestUtil.assertCompleted("updateLocation_shouldSucceed", turmsClient.userService.updateLocation(latitude: 2, longitude: 2))

        // Query

        TestUtil.assertCompleted("queryUserProfile_shouldReturnUserInfoWithVersion", turmsClient.userService.queryUserProfile(userId: 1))
        TestUtil.assertCompleted("queryUserIdsNearby_shouldReturnUsersIds", turmsClient.userService.queryUserIdsNearby(latitude: 1, longitude: 1))
        TestUtil.assertCompleted("queryUserSessionIdsNearby_shouldReturnUsersIds", turmsClient.userService.queryUserSessionIdsNearby(latitude: 1, longitude: 1))
        TestUtil.assertCompleted("queryUsersInfosNearby_shouldReturnUsersInfos", turmsClient.userService.queryUsersInfosNearby(latitude: 1, longitude: 1))
        TestUtil.assertCompleted("queryUsersOnlineStatusRequest_shouldUsersOnlineStatus", turmsClient.userService.queryUsersOnlineStatusRequest([1]).done {
            XCTAssertEqual($0[0].userStatus, userStatus)
        })
        TestUtil.assertCompleted("queryRelationships_shouldReturnUserRelationshipsWithVersion", turmsClient.userService.queryRelationships(relatedUsersIds: [2]))
        TestUtil.assertCompleted("queryRelatedUsersIds_shouldReturnRelatedUsersIds", turmsClient.userService.queryRelatedUsersIds())
        TestUtil.assertCompleted("queryFriends_shouldReturnFriendRelationships", turmsClient.userService.queryFriends())
        TestUtil.assertCompleted("queryBlacklistedUsers_shouldReturnBlacklist", turmsClient.userService.queryBlacklistedUsers())
        TestUtil.assertCompleted("queryFriendRequests_shouldReturnFriendRequests", turmsClient.userService.queryFriendRequests(true))
        TestUtil.assertCompleted("queryRelationshipGroups_shouldReturnRelationshipGroups", turmsClient.userService.queryRelationshipGroups())

        // Delete
        TestUtil.assertCompleted("deleteRelationship_shouldSucceed", turmsClient.userService.deleteRelationship(relatedUserId: 10))
        TestUtil.assertCompleted("deleteRelationshipGroups_shouldSucceed", turmsClient.userService.deleteRelationshipGroups(groupIndex: relationshipGroupIndex))
    }
}
