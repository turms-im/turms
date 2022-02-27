import PromiseKit
@testable import TurmsClient
import XCTest

class GroupServiceTests: XCTestCase {
    var turmsClient: TurmsClient!

    override func setUp() {
        continueAfterFailure = false
        turmsClient = TurmsClient(Config.WS_URL)
        wait(turmsClient.userService.login(userId: 1, password: "123"))
    }

    override func tearDown() {
        wait(turmsClient.userService.logout())
    }

    func test_e2e() {
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
        assertCompleted("createGroup_shouldReturnGroupId", service.createGroup(name: "name", intro: "intro", announcement: "announcement", minimumScore: 10).done {
            groupId = $0
        })
        assertCompleted("addGroupJoinQuestion_shouldReturnQuestionId", service.addGroupJoinQuestion(groupId: groupId!, question: "question", answers: ["answer1", "answer2"], score: 10).done {
            groupQuestionId = $0
        })
        assertCompleted("createJoinRequest_shouldReturnJoinRequestId", service.createJoinRequest(groupId: groupId!, content: "content").done {
            groupJoinRequestId = $0
        })
        assertCompleted("addGroupMember_shouldSucceed", service.addGroupMember(groupId: groupId!, userId: groupMemberId, name: "name", role: .member))
        assertCompleted("blockUser_shouldSucceed", service.blockUser(groupId: groupId!, userId: groupBlockedUserId))
        assertCompleted("createInvitation_shouldReturnInvitationId", service.createInvitation(groupId: groupId!, inviteeId: groupInvitationInviteeId, content: "content").done {
            groupInvitationId = $0
        })

        // Update
        assertCompleted("updateGroup_shouldSucceed", service.updateGroup(groupId: groupId!, groupName: "name", intro: "intro", announcement: "announcement", minimumScore: 10))
        assertCompleted("muteGroup_shouldSucceed", service.muteGroup(groupId: groupId!, muteEndDate: Date()))
        assertCompleted("unmuteGroup_shouldSucceed", service.unmuteGroup(groupId!))
        assertCompleted("updateGroupJoinQuestion_shouldSucceed", service.updateGroupJoinQuestion(questionId: groupQuestionId!, question: "new-question", answers: ["answer"]))
        assertCompleted("updateGroupMemberInfo_shouldSucceed", service.updateGroupMemberInfo(groupId: groupId!, memberId: groupMemberId, name: "myname"))
        assertCompleted("muteGroupMember_shouldSucceed", service.muteGroupMember(groupId: groupId!, memberId: groupMemberId, muteEndDate: Date(timeIntervalSinceNow: 100_000)))
        assertCompleted("unmuteGroupMember_shouldSucceed", service.unmuteGroupMember(groupId: groupId!, memberId: groupMemberId))

        // Query
        assertCompleted("queryGroup_shouldReturnGroupWithVersion", service.queryGroup(groupId: groupId!).done {
            XCTAssertEqual(groupId!, $0!.group.id)
        })
        assertCompleted("queryJoinedGroupIds_shouldEqualNewGroupId", service.queryJoinedGroupIds().done {
            XCTAssert($0!.values.contains(groupId!))
        })
        assertCompleted("queryJoinedGroupInfos_shouldEqualNewGroupId", service.queryJoinedGroupInfos().done {
            let groupIds = $0!.groups.map {
                $0.id
            }
            XCTAssert(groupIds.contains(groupId!))
        })
        assertCompleted("queryBlockedUserIds_shouldEqualBlockedUserId", service.queryBlockedUserIds(groupId: groupId!).done {
            XCTAssertEqual(groupBlockedUserId, $0!.values[0])
        })
        assertCompleted("queryBlockedUserInfos_shouldEqualBlockedUserId", service.queryBlockedUserInfos(groupId: groupId!).done {
            XCTAssertEqual(groupBlockedUserId, $0!.userInfos[0].id)
        })
        assertCompleted("queryInvitations_shouldEqualNewInvitationId", service.queryInvitations(groupId: groupId!).done {
            XCTAssertEqual(groupInvitationId, $0!.groupInvitations[0].id)
        })
        assertCompleted("queryJoinRequests_shouldEqualNewJoinRequestId", service.queryJoinRequests(groupId: groupId!).done {
            XCTAssertEqual(groupJoinRequestId, $0!.groupJoinRequests[0].id)
        })
        assertCompleted("queryGroupJoinQuestionsRequest_shouldEqualNewGroupQuestionId", service.queryGroupJoinQuestionsRequest(groupId: groupId!, withAnswers: true).done {
            XCTAssertEqual(groupQuestionId, $0!.groupJoinQuestions[0].id)
        })
        assertCompleted("queryGroupMembers_shouldEqualNewMemberId", service.queryGroupMembers(groupId: groupId!, withStatus: true).done {
            XCTAssertEqual(groupMemberId, $0!.groupMembers[1].userID)
        })
        assertCompleted("queryGroupMembersByMemberIds_shouldEqualNewMemberId", service.queryGroupMembersByMemberIds(groupId: groupId!, memberIds: [groupMemberId], withStatus: true).done {
            XCTAssertEqual(groupMemberId, $0!.groupMembers[0].userID)
        })
        assertCompleted("answerGroupQuestions_shouldReturnAnswerResult", service.answerGroupQuestions([groupQuestionId!: "answer"]).recover { error -> Promise<GroupJoinQuestionsAnswerResult> in
            if let businessError = error as? TurmsBusinessError, businessError.code == TurmsStatusCode.memberCannotAnswerGroupQuestion.rawValue {
                return Promise.value(GroupJoinQuestionsAnswerResult())
            } else {
                throw error
            }
        })

        // Delete

        assertCompleted("removeGroupMember_shouldSucceed", service.removeGroupMember(groupId: groupId!, memberId: groupMemberId))
        assertCompleted("deleteGroupJoinQuestion_shouldSucceed", service.deleteGroupJoinQuestion(groupQuestionId!))
        assertCompleted("unblockUser_shouldSucceed", service.unblockUser(groupId: groupId!, userId: groupBlockedUserId))
        assertCompleted("deleteInvitation_shouldSucceedOrThrowDisabledFunction", service.deleteInvitation(groupInvitationId!).recover { error -> Promise<Void> in
            if let businessError = error as? TurmsBusinessError, businessError.code == TurmsStatusCode.recallingGroupInvitationIsDisabled.rawValue {
                return Promise.value(())
            } else {
                throw error
            }
        })
        assertCompleted("deleteJoinRequest_shouldSucceedOrThrowDisabledFunction", service.deleteJoinRequest(groupJoinRequestId!).recover { error -> Promise<Void> in
            if let businessError = error as? TurmsBusinessError, businessError.code == TurmsStatusCode.recallingGroupJoinRequestIsDisabled.rawValue {
                return Promise.value(())
            } else {
                throw error
            }
        })
        assertCompleted("quitGroup_shouldSucceed", service.quitGroup(groupId: groupId!, quitAfterTransfer: false))
        var readyToDeleteGroupId: Int64!
        wait(service.createGroup(name: "readyToDelete").done {
            readyToDeleteGroupId = $0
        })
        assertCompleted("deleteGroup_shouldSucceed", service.deleteGroup(readyToDeleteGroupId))

        // Lowest-priority Update
        assertCompleted("transferOwnership_shouldSucceed", service.transferOwnership(groupId: groupId!, successorId: groupSuccessorId, quitAfterTransfer: true))
    }
}
