@testable import TurmsClient
import XCTest

class GroupServiceTests: XCTestCase {
    private var turmsClient: TurmsClient!

    override func setUp() async throws {
        continueAfterFailure = false
        turmsClient = TurmsClient(Config.HOST, Config.PORT)
        try await wait(await self.turmsClient.userService.login(userId: 1, password: "123"))
    }

    override func tearDown() async throws {
        try await wait(await self.turmsClient.userService.logout())
    }

    func test_e2e() async throws {
        let groupMemberId: Int64 = 3
        let groupInvitationInviteeId: Int64 = 4
        let groupSuccessorId: Int64 = 1
        let groupBlockedUserId: Int64 = 5
        var groupId: Int64?
        var groupQuestionId: Int64?
        var groupJoinRequestId: Int64?
        var groupInvitationId: Int64?

        let service = turmsClient.groupService!

        // Create
        try await assertCompleted("createGroup_shouldReturnGroupId") {
            let result = try await service.createGroup(name: "name", intro: "intro", announcement: "announcement", minScore: 10)
            groupId = result.data
        }
        try await assertCompleted("addGroupJoinQuestions_shouldReturnQuestionIds") {
            let result = try await service.addGroupJoinQuestions(groupId: groupId!, questions: [NewGroupJoinQuestion(question: "question", answers: ["answer1", "answer2"], score: 10)])
            groupQuestionId = result.data[0]
        }
        try await assertCompleted("createJoinRequest_shouldReturnJoinRequestId") {
            let result = try await service.createJoinRequest(groupId: groupId!, content: "content")
            groupJoinRequestId = result.data
        }
        try await assertCompleted("addGroupMembers_shouldSucceed", await service.addGroupMembers(groupId: groupId!, userIds: [groupMemberId], name: "name", role: .member))
        try await assertCompleted("blockUser_shouldSucceed", await service.blockUser(groupId: groupId!, userId: groupBlockedUserId))
        try await assertCompleted("createInvitation_shouldReturnInvitationId") {
            let result = try await service.createInvitation(groupId: groupId!, inviteeId: groupInvitationInviteeId, content: "content")
            groupInvitationId = result.data
        }

        // Update
        try await assertCompleted("updateGroup_shouldSucceed", await service.updateGroup(groupId: groupId!, name: "name", intro: "intro", announcement: "announcement", minScore: 10))
        try await assertCompleted("muteGroup_shouldSucceed", await service.muteGroup(groupId: groupId!, muteEndDate: Date()))
        try await assertCompleted("unmuteGroup_shouldSucceed", await service.unmuteGroup(groupId!))
        try await assertCompleted("updateGroupJoinQuestion_shouldSucceed", await service.updateGroupJoinQuestion(questionId: groupQuestionId!, question: "new-question", answers: ["answer"]))
        try await assertCompleted("updateGroupMemberInfo_shouldSucceed", await service.updateGroupMemberInfo(groupId: groupId!, memberId: groupMemberId, name: "myname"))
        try await assertCompleted("muteGroupMember_shouldSucceed", await service.muteGroupMember(groupId: groupId!, memberId: groupMemberId, muteEndDate: Date(timeIntervalSinceNow: 100_000)))
        try await assertCompleted("unmuteGroupMember_shouldSucceed", await service.unmuteGroupMember(groupId: groupId!, memberId: groupMemberId))

        // Query
        try await assertCompleted("queryGroups_shouldReturnGroups") {
            let groups = try await service.queryGroups(groupIds: [groupId!])
            XCTAssertEqual(groupId!, groups.data[0].id)
        }
        try await assertCompleted("queryJoinedGroupIds_shouldEqualNewGroupId") {
            let joinedGroupIds = try await service.queryJoinedGroupIds()
            XCTAssert(joinedGroupIds.data!.longs.contains(groupId!))
        }
        try await assertCompleted("queryJoinedGroupInfos_shouldEqualNewGroupId") {
            let joinedGroupInfos = try await service.queryJoinedGroupInfos()
            let groupIds = joinedGroupInfos.data!.groups.map {
                $0.id
            }
            XCTAssert(groupIds.contains(groupId!))
        }
        try await assertCompleted("queryBlockedUserIds_shouldEqualBlockedUserId") {
            let blockedUserIds = try await service.queryBlockedUserIds(groupId: groupId!)
            XCTAssertEqual(groupBlockedUserId, blockedUserIds.data!.longs[0])
        }
        try await assertCompleted("queryBlockedUserInfos_shouldEqualBlockedUserId") {
            let blockedUserInfos = try await service.queryBlockedUserInfos(groupId: groupId!)
            XCTAssertEqual(groupBlockedUserId, blockedUserInfos.data!.userInfos[0].id)
        }
        try await assertCompleted("queryInvitations_shouldEqualNewInvitationId") {
            let invitations = try await service.queryInvitations(groupId: groupId!)
            XCTAssertEqual(groupInvitationId, invitations.data!.groupInvitations[0].id)
        }
        try await assertCompleted("queryJoinRequests_shouldEqualNewJoinRequestId") {
            let joinRequests = try await service.queryJoinRequests(groupId: groupId!)
            XCTAssertEqual(groupJoinRequestId, joinRequests.data!.groupJoinRequests[0].id)
        }
        try await assertCompleted("queryGroupJoinQuestions_shouldEqualNewGroupQuestionId") {
            let groupJoinQuestions = try await service.queryGroupJoinQuestions(groupId: groupId!, withAnswers: true)
            XCTAssertEqual(groupQuestionId, groupJoinQuestions.data!.groupJoinQuestions[0].id)
        }
        try await assertCompleted("queryGroupMembers_shouldEqualNewMemberId") {
            let groupMembers = try await service.queryGroupMembers(groupId: groupId!, withStatus: true)
            XCTAssertEqual(groupMemberId, groupMembers.data!.groupMembers[1].userID)
        }
        try await assertCompleted("queryGroupMembersByMemberIds_shouldEqualNewMemberId") {
            let groupMembers = try await service.queryGroupMembersByMemberIds(groupId: groupId!, memberIds: [groupMemberId], withStatus: true)
            XCTAssertEqual(groupMemberId, groupMembers.data!.groupMembers[0].userID)
        }
        try await assertCompleted("answerGroupQuestions_shouldReturnAnswerResult") {
            do {
                let result = try await service.answerGroupQuestions([groupQuestionId!: "answer"])
            } catch {
                guard let businessError = error as? ResponseError, businessError.code == ResponseStatusCode.groupMemberAnswerGroupQuestion.rawValue else {
                    throw error
                }
            }
        }

        // Delete

        try await assertCompleted("removeGroupMember_shouldSucceed", await service.removeGroupMember(groupId: groupId!, memberId: groupMemberId))
        try await assertCompleted("deleteGroupJoinQuestions_shouldSucceed", await service.deleteGroupJoinQuestions(groupId: groupId!, questionIds: [groupQuestionId!]))
        try await assertCompleted("unblockUser_shouldSucceed", await service.unblockUser(groupId: groupId!, userId: groupBlockedUserId))
        try await assertCompleted("deleteInvitation_shouldSucceedOrThrowDisabledFunction") {
            do {
                let result = try await service.deleteInvitation(groupInvitationId!)
            } catch {
                guard let businessError = error as? ResponseError, businessError.code == ResponseStatusCode.recallingGroupInvitationIsDisabled.rawValue else {
                    throw error
                }
            }
        }
        try await assertCompleted("deleteJoinRequest_shouldSucceedOrThrowDisabledFunction") {
            do {
                let result = try await service.deleteJoinRequest(groupJoinRequestId!)
            } catch {
                guard let businessError = error as? ResponseError, businessError.code == ResponseStatusCode.recallingGroupJoinRequestIsDisabled.rawValue else {
                    throw error
                }
            }
        }
        try await assertCompleted("quitGroup_shouldSucceed", await service.quitGroup(groupId: groupId!, quitAfterTransfer: false))
        var readyToDeleteGroupId: Int64!
        try await wait {
            let result = try await service.createGroup(name: "readyToDelete")
            readyToDeleteGroupId = result.data
        }
        try await assertCompleted("deleteGroup_shouldSucceed", await service.deleteGroup(readyToDeleteGroupId))

        // Lowest-priority Update
        try await assertCompleted("transferOwnership_shouldSucceed", await service.transferOwnership(groupId: groupId!, successorId: groupSuccessorId, quitAfterTransfer: true))
    }
}
