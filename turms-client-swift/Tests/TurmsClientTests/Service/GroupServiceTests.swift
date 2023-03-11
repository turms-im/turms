import PromiseKit
@testable import TurmsClient
import XCTest

class GroupServiceTests: XCTestCase {
    var turmsClient: TurmsClient!

    override func setUp() {
        continueAfterFailure = false
        turmsClient = TurmsClient(Config.HOST, Config.PORT)
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
        assertCompleted("createGroup_shouldReturnGroupId", service.createGroup(name: "name", intro: "intro", announcement: "announcement", minScore: 10).done {
            groupId = $0.data
        })
        assertCompleted("addGroupJoinQuestions_shouldReturnQuestionIds", service.addGroupJoinQuestions(groupId: groupId!, questions: [NewGroupJoinQuestion(question: "question", answers: ["answer1", "answer2"], score: 10)]).done {
            groupQuestionId = $0.data[0]
        })
        assertCompleted("createJoinRequest_shouldReturnJoinRequestId", service.createJoinRequest(groupId: groupId!, content: "content").done {
            groupJoinRequestId = $0.data
        })
        assertCompleted("addGroupMembers_shouldSucceed", service.addGroupMembers(groupId: groupId!, userIds: [groupMemberId], name: "name", role: .member))
        assertCompleted("blockUser_shouldSucceed", service.blockUser(groupId: groupId!, userId: groupBlockedUserId))
        assertCompleted("createInvitation_shouldReturnInvitationId", service.createInvitation(groupId: groupId!, inviteeId: groupInvitationInviteeId, content: "content").done {
            groupInvitationId = $0.data
        })

        // Update
        assertCompleted("updateGroup_shouldSucceed", service.updateGroup(groupId: groupId!, name: "name", intro: "intro", announcement: "announcement", minScore: 10))
        assertCompleted("muteGroup_shouldSucceed", service.muteGroup(groupId: groupId!, muteEndDate: Date()))
        assertCompleted("unmuteGroup_shouldSucceed", service.unmuteGroup(groupId!))
        assertCompleted("updateGroupJoinQuestion_shouldSucceed", service.updateGroupJoinQuestion(questionId: groupQuestionId!, question: "new-question", answers: ["answer"]))
        assertCompleted("updateGroupMemberInfo_shouldSucceed", service.updateGroupMemberInfo(groupId: groupId!, memberId: groupMemberId, name: "myname"))
        assertCompleted("muteGroupMember_shouldSucceed", service.muteGroupMember(groupId: groupId!, memberId: groupMemberId, muteEndDate: Date(timeIntervalSinceNow: 100_000)))
        assertCompleted("unmuteGroupMember_shouldSucceed", service.unmuteGroupMember(groupId: groupId!, memberId: groupMemberId))

        // Query
        assertCompleted("queryGroups_shouldReturnGroups", service.queryGroups(groupIds: [groupId!]).done {
            XCTAssertEqual(groupId!, $0.data[0].id)
        })
        assertCompleted("queryJoinedGroupIds_shouldEqualNewGroupId", service.queryJoinedGroupIds().done {
            XCTAssert($0.data!.longs.contains(groupId!))
        })
        assertCompleted("queryJoinedGroupInfos_shouldEqualNewGroupId", service.queryJoinedGroupInfos().done {
            let groupIds = $0.data!.groups.map {
                $0.id
            }
            XCTAssert(groupIds.contains(groupId!))
        })
        assertCompleted("queryBlockedUserIds_shouldEqualBlockedUserId", service.queryBlockedUserIds(groupId: groupId!).done {
            XCTAssertEqual(groupBlockedUserId, $0.data!.longs[0])
        })
        assertCompleted("queryBlockedUserInfos_shouldEqualBlockedUserId", service.queryBlockedUserInfos(groupId: groupId!).done {
            XCTAssertEqual(groupBlockedUserId, $0.data!.userInfos[0].id)
        })
        assertCompleted("queryInvitations_shouldEqualNewInvitationId", service.queryInvitations(groupId: groupId!).done {
            XCTAssertEqual(groupInvitationId, $0.data!.groupInvitations[0].id)
        })
        assertCompleted("queryJoinRequests_shouldEqualNewJoinRequestId", service.queryJoinRequests(groupId: groupId!).done {
            XCTAssertEqual(groupJoinRequestId, $0.data!.groupJoinRequests[0].id)
        })
        assertCompleted("queryGroupJoinQuestions_shouldEqualNewGroupQuestionId", service.queryGroupJoinQuestions(groupId: groupId!, withAnswers: true).done {
            XCTAssertEqual(groupQuestionId, $0.data!.groupJoinQuestions[0].id)
        })
        assertCompleted("queryGroupMembers_shouldEqualNewMemberId", service.queryGroupMembers(groupId: groupId!, withStatus: true).done {
            XCTAssertEqual(groupMemberId, $0.data!.groupMembers[1].userID)
        })
        assertCompleted("queryGroupMembersByMemberIds_shouldEqualNewMemberId", service.queryGroupMembersByMemberIds(groupId: groupId!, memberIds: [groupMemberId], withStatus: true).done {
            XCTAssertEqual(groupMemberId, $0.data!.groupMembers[0].userID)
        })
        assertCompleted("answerGroupQuestions_shouldReturnAnswerResult", service.answerGroupQuestions([groupQuestionId!: "answer"]).recover { error -> Promise<Response<GroupJoinQuestionsAnswerResult>> in
            if let businessError = error as? ResponseError, businessError.code == ResponseStatusCode.memberCannotAnswerGroupQuestion.rawValue {
                return Promise.value(Response.value(GroupJoinQuestionsAnswerResult()))
            } else {
                throw error
            }
        })

        // Delete

        assertCompleted("removeGroupMember_shouldSucceed", service.removeGroupMember(groupId: groupId!, memberId: groupMemberId))
        assertCompleted("deleteGroupJoinQuestions_shouldSucceed", service.deleteGroupJoinQuestions(groupId: groupId!, questionIds: [groupQuestionId!]))
        assertCompleted("unblockUser_shouldSucceed", service.unblockUser(groupId: groupId!, userId: groupBlockedUserId))
        assertCompleted("deleteInvitation_shouldSucceedOrThrowDisabledFunction", service.deleteInvitation(groupInvitationId!).recover { error -> Promise<Response<Void>> in
            if let businessError = error as? ResponseError, businessError.code == ResponseStatusCode.recallingGroupInvitationIsDisabled.rawValue {
                return Promise.value(Response.empty())
            } else {
                throw error
            }
        })
        assertCompleted("deleteJoinRequest_shouldSucceedOrThrowDisabledFunction", service.deleteJoinRequest(groupJoinRequestId!).recover { error -> Promise<Response<Void>> in
            if let businessError = error as? ResponseError, businessError.code == ResponseStatusCode.recallingGroupJoinRequestIsDisabled.rawValue {
                return Promise.value(Response.empty())
            } else {
                throw error
            }
        })
        assertCompleted("quitGroup_shouldSucceed", service.quitGroup(groupId: groupId!, quitAfterTransfer: false))
        var readyToDeleteGroupId: Int64!
        wait(service.createGroup(name: "readyToDelete").done {
            readyToDeleteGroupId = $0.data
        })
        assertCompleted("deleteGroup_shouldSucceed", service.deleteGroup(readyToDeleteGroupId))

        // Lowest-priority Update
        assertCompleted("transferOwnership_shouldSucceed", service.transferOwnership(groupId: groupId!, successorId: groupSuccessorId, quitAfterTransfer: true))
    }
}
