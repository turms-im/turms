import PromiseKit
@testable import TurmsClient
import XCTest

class GroupServiceTests: XCTestCase {
    var turmsClient: TurmsClient!

    override func setUp() {
        continueAfterFailure = false
        turmsClient = TurmsClient(Config.WS_URL)
        TestUtil.wait(turmsClient.driver.connect(userId: 1, password: "123"))
    }

    override func tearDown() {
        TestUtil.wait(turmsClient.driver.disconnect())
    }

    func test_system() {
        let groupMemberId: Int64 = 3
        let groupInvitationInviteeId: Int64 = 4
        let groupSuccessorId: Int64 = 1
        let groupBlacklistedUserId: Int64 = 5
        var groupId: Int64?
        var groupQuestionId: Int64?
        var groupJoinRequestId: Int64?
        var groupInvitationId: Int64?

        // Create
        TestUtil.assertCompleted("createGroup_shouldReturnGroupId", turmsClient.groupService.createGroup(name: "name", intro: "intro", announcement: "announcement", minimumScore: 10).done {
            groupId = $0
        })
        TestUtil.assertCompleted("addGroupJoinQuestion_shouldReturnQuestionId", turmsClient.groupService.addGroupJoinQuestion(groupId: groupId!, question: "question", answers: ["answer1", "answer2"], score: 10).done {
            groupQuestionId = $0
        })
        TestUtil.assertCompleted("createJoinRequest_shouldReturnJoinRequestId", turmsClient.groupService.createJoinRequest(groupId: groupId!, content: "content").done {
            groupJoinRequestId = $0
        })
        TestUtil.assertCompleted("addGroupMember_shouldSucceed", turmsClient.groupService.addGroupMember(groupId: groupId!, userId: groupMemberId, name: "name", role: .member))
        TestUtil.assertCompleted("blacklistUser_shouldSucceed", turmsClient.groupService.blacklistUser(groupId: groupId!, userId: groupBlacklistedUserId))
        TestUtil.assertCompleted("createInvitation_shouldReturnInvitationId", turmsClient.groupService.createInvitation(groupId: groupId!, inviteeId: groupInvitationInviteeId, content: "content").done {
            groupInvitationId = $0
        })

        // Update
        TestUtil.assertCompleted("updateGroup_shouldSucceed", turmsClient.groupService.updateGroup(groupId: groupId!, groupName: "name", intro: "intro", announcement: "announcement", minimumScore: 10))
        TestUtil.assertCompleted("muteGroup_shouldSucceed", turmsClient.groupService.muteGroup(groupId: groupId!, muteEndDate: Date()))
        TestUtil.assertCompleted("unmuteGroup_shouldSucceed", turmsClient.groupService.unmuteGroup(groupId!))
        TestUtil.assertCompleted("updateGroupJoinQuestion_shouldSucceed", turmsClient.groupService.updateGroupJoinQuestion(questionId: groupQuestionId!, question: "new-question", answers: ["answer"]))
        TestUtil.assertCompleted("updateGroupMemberInfo_shouldSucceed", turmsClient.groupService.updateGroupMemberInfo(groupId: groupId!, memberId: groupMemberId, name: "myname"))
        TestUtil.assertCompleted("muteGroupMember_shouldSucceed", turmsClient.groupService.muteGroupMember(groupId: groupId!, memberId: groupMemberId, muteEndDate: Date(timeIntervalSinceNow: 100000)))
        TestUtil.assertCompleted("unmuteGroupMember_shouldSucceed", turmsClient.groupService.unmuteGroupMember(groupId: groupId!, memberId: groupMemberId))

        // Query
        TestUtil.assertCompleted("queryGroup_shouldReturnGroupWithVersion", turmsClient.groupService.queryGroup(groupId: groupId!).done {
            XCTAssertEqual(groupId!, $0!.group.id.value)
        })
        TestUtil.assertCompleted("queryJoinedGroupIds_shouldEqualNewGroupId", turmsClient.groupService.queryJoinedGroupIds().done {
            XCTAssert($0!.values.contains(groupId!))
        })
        TestUtil.assertCompleted("queryJoinedGroupInfos_shouldEqualNewGroupId", turmsClient.groupService.queryJoinedGroupInfos().done {
            let groupIds = $0!.groups.map { $0.id.value }
            XCTAssert(groupIds.contains(groupId!))
        })
        TestUtil.assertCompleted("queryBlacklistedUserIds_shouldEqualBlacklistedUserId", turmsClient.groupService.queryBlacklistedUserIds(groupId: groupId!).done {
            XCTAssertEqual(groupBlacklistedUserId, $0!.values[0])
        })
        TestUtil.assertCompleted("queryBlacklistedUserInfos_shouldEqualBlacklistedUserId", turmsClient.groupService.queryBlacklistedUserInfos(groupId: groupId!).done {
            XCTAssertEqual(groupBlacklistedUserId, $0!.userInfos[0].id.value)
        })
        TestUtil.assertCompleted("queryInvitations_shouldEqualNewInvitationId", turmsClient.groupService.queryInvitations(groupId: groupId!).done {
            XCTAssertEqual(groupInvitationId, $0!.groupInvitations[0].id.value)
        })
        TestUtil.assertCompleted("queryJoinRequests_shouldEqualNewJoinRequestId", turmsClient.groupService.queryJoinRequests(groupId: groupId!).done {
            XCTAssertEqual(groupJoinRequestId, $0!.groupJoinRequests[0].id.value)
        })
        TestUtil.assertCompleted("queryGroupJoinQuestionsRequest_shouldEqualNewGroupQuestionId", turmsClient.groupService.queryGroupJoinQuestionsRequest(groupId: groupId!, withAnswers: true).done {
            XCTAssertEqual(groupQuestionId, $0!.groupJoinQuestions[0].id.value)
        })
        TestUtil.assertCompleted("queryGroupMembers_shouldEqualNewMemberId", turmsClient.groupService.queryGroupMembers(groupId: groupId!, withStatus: true).done {
            XCTAssertEqual(groupMemberId, $0!.groupMembers[1].userID.value)
        })
        TestUtil.assertCompleted("queryGroupMembersByMemberIds_shouldEqualNewMemberId", turmsClient.groupService.queryGroupMembersByMemberIds(groupId: groupId!, memberIds: [groupMemberId], withStatus: true).done {
            XCTAssertEqual(groupMemberId, $0!.groupMembers[0].userID.value)
        })
        TestUtil.assertCompleted("answerGroupQuestions_shouldReturnAnswerResult", turmsClient.groupService.answerGroupQuestions([groupQuestionId!: "answer"]).recover { error -> Promise<GroupJoinQuestionsAnswerResult> in
            if let businessError = error as? TurmsBusinessError, businessError.code == .alreadyGroupMember {
                return Promise.value(GroupJoinQuestionsAnswerResult())
            } else {
                throw error
            }
        })

        // Delete

        TestUtil.assertCompleted("removeGroupMember_shouldSucceed", turmsClient.groupService.removeGroupMember(groupId: groupId!, memberId: groupMemberId))
        TestUtil.assertCompleted("deleteGroupJoinQuestion_shouldSucceed", turmsClient.groupService.deleteGroupJoinQuestion(groupQuestionId!))
        TestUtil.assertCompleted("unblacklistUser_shouldSucceed", turmsClient.groupService.unblacklistUser(groupId: groupId!, userId: groupBlacklistedUserId))
        TestUtil.assertCompleted("deleteInvitation_shouldSucceedOrThrowDisabledFunction", turmsClient.groupService.deleteInvitation(groupInvitationId!).recover { error -> Promise<Void> in
            if let businessError = error as? TurmsBusinessError, businessError.code == .disabledFunction {
                return Promise.value(())
            } else {
                throw error
            }
        })
        TestUtil.assertCompleted("deleteJoinRequest_shouldSucceedOrThrowDisabledFunction", turmsClient.groupService.deleteJoinRequest(groupJoinRequestId!).recover { error -> Promise<Void> in
            if let businessError = error as? TurmsBusinessError, businessError.code == .disabledFunction {
                return Promise.value(())
            } else {
                throw error
            }
        })
        TestUtil.assertCompleted("quitGroup_shouldSucceed", turmsClient.groupService.quitGroup(groupId: groupId!, quitAfterTransfer: false))
        var readyToDeleteGroupId: Int64!
        TestUtil.wait(turmsClient.groupService.createGroup(name: "readyToDelete").done {
            readyToDeleteGroupId = $0
        })
        TestUtil.assertCompleted("deleteGroup_shouldSucceed", turmsClient.groupService.deleteGroup(readyToDeleteGroupId))

        // Lowest-priority Update
        TestUtil.assertCompleted("transferOwnership_shouldSucceed", turmsClient.groupService.transferOwnership(groupId: groupId!, successorId: groupSuccessorId, quitAfterTransfer: true))
    }
}
