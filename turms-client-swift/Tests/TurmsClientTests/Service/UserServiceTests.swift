import PromiseKit
@testable import TurmsClient
import XCTest

class UserServiceTests: XCTestCase {
    var turmsClient: TurmsClient!

    override func setUp() {
        continueAfterFailure = false
        turmsClient = TurmsClient(Config.HOST, Config.PORT)
    }

    override func tearDown() {
        wait(turmsClient.userService.logout())
    }

    func test_e2e() {
        var relationshipGroupIndex: Int32!
        let userStatus = UserStatus.busy
        let service = turmsClient.userService!

        // Login
        assertCompleted("login_shouldSucceed", service.login(userId: 1, password: "123"))

        // Create
        assertCompleted("createRelationship_shouldSucceed", service.createRelationship(userId: 10, isBlocked: true).recover { error -> Promise<Response<Void>> in
            if let businessError = error as? ResponseError, businessError.code == ResponseStatusCode.createExistingRelationship.rawValue {
                return Promise.value(Response.empty())
            } else {
                throw error
            }
        })
        assertCompleted("createFriendRelationship_shouldSucceed", service.createFriendRelationship(userId: 10).recover { error -> Promise<Response<Void>> in
            if let businessError = error as? ResponseError, businessError.code == ResponseStatusCode.createExistingRelationship.rawValue {
                return Promise.value(Response.empty())
            } else {
                throw error
            }
        })
        assertCompleted("createBlockedUserRelationship_shouldSucceed", service.createBlockedUserRelationship(userId: 10).recover { error -> Promise<Response<Void>> in
            if let businessError = error as? ResponseError, businessError.code == ResponseStatusCode.createExistingRelationship.rawValue {
                return Promise.value(Response.empty())
            } else {
                throw error
            }
        })
        assertCompleted("sendFriendRequest_shouldReturnFriendRequestId", service.sendFriendRequest(recipientId: 11, content: "content").recover { error -> Promise<Response<Int64>> in
            if let businessError = error as? ResponseError, businessError.code == ResponseStatusCode.createExistingFriendRequest.rawValue {
                return Promise.value(Response.value(0))
            } else {
                throw error
            }
        })
        assertCompleted("createRelationshipGroup_shouldReturnRelationshipGroupIndex", service.createRelationshipGroup("newGroup").done {
            relationshipGroupIndex = $0.data
        })

        // Update
        assertCompleted("updateUserOnlineStatus_shouldSucceed", service.updateUserOnlineStatus(userStatus))
        assertCompleted("updatePassword_shouldSucceed", service.updatePassword("123"))
        assertCompleted("updateProfile_shouldSucceed", service.updateProfile(name: "123", intro: "123"))
        assertCompleted("updateRelationship_shouldSucceed", service.updateRelationship(relatedUserId: 10, groupIndex: 1))
        assertCompleted("replyFriendRequest_shouldSucceed", service.replyFriendRequest(requestId: 10, responseAction: .accept, reason: "reason"))
        assertCompleted("updateRelationshipGroup_shouldSucceed", service.updateRelationshipGroup(groupIndex: relationshipGroupIndex, newName: "newGroupName"))
        assertCompleted("moveRelatedUserToGroup_shouldSucceed", service.moveRelatedUserToGroup(relatedUserId: 2, groupIndex: 1))
        wait(service.moveRelatedUserToGroup(relatedUserId: 2, groupIndex: 0))
        assertCompleted("updateLocation_shouldSucceed", service.updateLocation(latitude: 2, longitude: 2))

        // Query

        assertCompleted("queryUserProfiles_shouldReturnUserInfos", service.queryUserProfiles(userIds: [1]))
        assertCompleted("queryNearbyUsers_shouldReturnNearbyUsers", service.queryNearbyUsers(latitude: 1, longitude: 1))
        assertCompleted("queryUserOnlineStatusesRequest_shouldUserOnlineStatuses", service.queryUserOnlineStatusesRequest([1]).done {
            XCTAssertEqual($0.data[0].userStatus, userStatus)
        })
        assertCompleted("queryRelationships_shouldReturnUserRelationshipsWithVersion", service.queryRelationships(relatedUserIds: [2]))
        assertCompleted("queryRelatedUserIds_shouldReturnRelatedUserIds", service.queryRelatedUserIds())
        assertCompleted("queryFriends_shouldReturnFriendRelationships", service.queryFriends())
        assertCompleted("queryBlockedUsers_shouldReturnRelationshipsWithBlockedUsers", service.queryBlockedUsers())
        assertCompleted("queryFriendRequests_shouldReturnFriendRequests", service.queryFriendRequests(true))
        assertCompleted("queryRelationshipGroups_shouldReturnRelationshipGroups", service.queryRelationshipGroups())

        // Delete
        assertCompleted("deleteRelationship_shouldSucceed", service.deleteRelationship(relatedUserId: 10))
        assertCompleted("deleteRelationshipGroups_shouldSucceed", service.deleteRelationshipGroups(groupIndex: relationshipGroupIndex))

        // Logout
        wait(service.logout())
    }
}
