import Foundation
import PromiseKit

public class GroupService {
    private weak var turmsClient: TurmsClient!

    public init(_ turmsClient: TurmsClient) {
        self.turmsClient = turmsClient
    }

    public func createGroup(
        name: String,
        intro: String? = nil,
        announcement: String? = nil,
        minimumScore: Int32? = nil,
        muteEndDate: Date? = nil,
        groupTypeId: Int32? = nil) -> Promise<Int64> {
        return turmsClient.driver
            .send { $0.request("createGroupRequest")
                .field("name", name)
                .field("intro", intro)
                .field("announcement", announcement)
                .field("minimumScore", minimumScore)
                .field("muteEndDate", muteEndDate)
                .field("groupTypeId", groupTypeId)
            }
            .map { try NotificationUtil.getFirstId($0) }
    }

    public func deleteGroup(_ groupId: Int64) -> Promise<Void> {
        return turmsClient.driver
            .send { $0
                .request("deleteGroupRequest")
                .field("groupId", groupId)
            }
            .map { _ in () }
    }

    public func updateGroup(
        groupId: Int64,
        groupName: String? = nil,
        intro: String? = nil,
        announcement: String? = nil,
        minimumScore: Int32? = nil,
        groupTypeId: Int32? = nil,
        muteEndDate: Date? = nil,
        successorId: Int64? = nil,
        quitAfterTransfer: Bool? = nil) -> Promise<Void> {
        if Validator.areAllNil(
            groupName,
            intro,
            announcement,
            minimumScore,
            groupTypeId,
            muteEndDate,
            successorId) {
            return Promise.value(())
        }
        return turmsClient.driver
            .send { $0
                .request("updateGroupRequest")
                .field("groupId", groupId)
                .field("groupName", groupName)
                .field("intro", intro)
                .field("announcement", announcement)
                .field("muteEndDate", muteEndDate)
                .field("minimumScore", minimumScore)
                .field("groupTypeId", groupTypeId)
                .field("successorId", successorId)
                .field("quitAfterTransfer", quitAfterTransfer)
            }
            .map { _ in () }
    }

    public func transferOwnership(groupId: Int64, successorId: Int64, quitAfterTransfer: Bool = false) -> Promise<Void> {
        return turmsClient.driver
            .send { $0
                .request("updateGroupRequest")
                .field("groupId", groupId)
                .field("successorId", successorId)
                .field("quitAfterTransfer", quitAfterTransfer)
            }
            .map { _ in () }
    }

    public func muteGroup(groupId: Int64, muteEndDate: Date) -> Promise<Void> {
        return turmsClient.driver
            .send { $0
                .request("updateGroupRequest")
                .field("groupId", groupId)
                .field("muteEndDate", muteEndDate)
            }
            .map { _ in () }
    }

    public func unmuteGroup(_ groupId: Int64) -> Promise<Void> {
        return muteGroup(groupId: groupId, muteEndDate: Date(timeIntervalSince1970: 0))
    }

    public func queryGroup(groupId: Int64, lastUpdatedDate: Date? = nil) -> Promise<GroupWithVersion?> {
        return turmsClient.driver
            .send { $0
                .request("queryGroupRequest")
                .field("groupId", groupId)
                .field("lastUpdatedDate", lastUpdatedDate)
            }
            .map { try GroupWithVersion.from($0) }
    }

    public func queryJoinedGroupsIds(_ lastUpdatedDate: Date? = nil) -> Promise<Int64ValuesWithVersion?> {
        return turmsClient.driver
            .send { $0
                .request("queryJoinedGroupsIdsRequest")
                .field("lastUpdatedDate", lastUpdatedDate)
            }
            .map { try $0.data.kind?.getKindData(Int64ValuesWithVersion.self) }
    }

    public func queryJoinedGroupsInfos(_ lastUpdatedDate: Date? = nil) -> Promise<GroupsWithVersion?> {
        return turmsClient.driver
            .send { $0
                .request("queryJoinedGroupsInfosRequest")
                .field("lastUpdatedDate", lastUpdatedDate)
            }
            .map { try $0.data.kind?.getKindData(GroupsWithVersion.self) }
    }

    public func addGroupJoinQuestion(groupId: Int64, question: String, answers: [String], score: Int32) -> Promise<Int64> {
        return turmsClient.driver
            .send { $0
                .request("createGroupJoinQuestionRequest")
                .field("groupId", groupId)
                .field("question", question)
                .field("answers", answers)
                .field("score", score)
            }
            .map { try NotificationUtil.getFirstId($0) }
    }

    public func deleteGroupJoinQuestion(_ questionId: Int64) -> Promise<Void> {
        return turmsClient.driver
            .send { $0
                .request("deleteGroupJoinQuestionRequest")
                .field("questionId", questionId)
            }
            .map { _ in () }
    }

    public func updateGroupJoinQuestion(questionId: Int64, question: String? = nil, answers: [String]? = nil, score: Int32? = nil) -> Promise<Void> {
        if Validator.areAllNil(question, answers, score) {
            return Promise.value(())
        }
        return turmsClient.driver
            .send { $0
                .request("updateGroupJoinQuestionRequest")
                .field("questionId", questionId)
                .field("question", question)
                .field("answers", answers)
                .field("score", score)
            }
            .map { _ in () }
    }

    // Group Blacklist
    public func blacklistUser(groupId: Int64, userId: Int64) -> Promise<Void> {
        return turmsClient.driver
            .send { $0
                .request("createGroupBlacklistedUserRequest")
                .field("blacklistedUserId", userId)
                .field("groupId", groupId)
            }
            .map { _ in () }
    }

    public func unblacklistUser(groupId: Int64, userId: Int64) -> Promise<Void> {
        return turmsClient.driver
            .send { $0
                .request("deleteGroupBlacklistedUserRequest")
                .field("groupId", groupId)
                .field("unblacklistedUserId", userId)
            }
            .map { _ in () }
    }

    public func queryBlacklistedUsersIds(
        groupId: Int64,
        lastUpdatedDate: Date? = nil) -> Promise<Int64ValuesWithVersion?> {
        return turmsClient.driver
            .send { $0
                .request("queryGroupBlacklistedUsersIdsRequest")
                .field("groupId", groupId)
                .field("lastUpdatedDate", lastUpdatedDate)
            }
            .map { try $0.data.kind?.getKindData(Int64ValuesWithVersion.self) }
    }

    public func queryBlacklistedUsersInfos(
        groupId: Int64,
        lastUpdatedDate: Date? = nil) -> Promise<UsersInfosWithVersion?> {
        return turmsClient.driver
            .send { $0
                .request("queryGroupBlacklistedUsersInfosRequest")
                .field("groupId", groupId)
                .field("lastUpdatedDate", lastUpdatedDate)
            }
            .map { try $0.data.kind?.getKindData(UsersInfosWithVersion.self) }
    }

    // Group Enrollment
    public func createInvitation(groupId: Int64, inviteeId: Int64, content: String) -> Promise<Int64> {
        return turmsClient.driver
            .send { $0
                .request("createGroupInvitationRequest")
                .field("groupId", groupId)
                .field("inviteeId", inviteeId)
                .field("content", content)
            }
            .map { try NotificationUtil.getFirstId($0) }
    }

    public func deleteInvitation(_ invitationId: Int64) -> Promise<Void> {
        return turmsClient.driver
            .send { $0
                .request("deleteGroupInvitationRequest")
                .field("invitationId", invitationId)
            }
            .map { _ in () }
    }

    public func queryInvitations(groupId: Int64, lastUpdatedDate: Date? = nil) -> Promise<GroupInvitationsWithVersion?> {
        return turmsClient.driver
            .send { $0
                .request("queryGroupInvitationsRequest")
                .field("groupId", groupId)
                .field("lastUpdatedDate", lastUpdatedDate)
            }
            .map { try $0.data.kind?.getKindData(GroupInvitationsWithVersion.self) }
    }

    public func queryInvitations(areSentByMe: Bool, lastUpdatedDate: Date? = nil) -> Promise<GroupInvitationsWithVersion?> {
        return turmsClient.driver
            .send { $0
                .request("queryGroupInvitationsRequest")
                .field("areSentByMe", areSentByMe)
                .field("lastUpdatedDate", lastUpdatedDate)
            }
            .map { try $0.data.kind?.getKindData(GroupInvitationsWithVersion.self) }
    }

    public func createJoinRequest(groupId: Int64, content: String) -> Promise<Int64> {
        return turmsClient.driver
            .send { $0
                .request("createGroupJoinRequestRequest")
                .field("groupId", groupId)
                .field("content", content)
            }
            .map { try NotificationUtil.getFirstId($0) }
    }

    public func deleteJoinRequest(_ requestId: Int64) -> Promise<Void> {
        return turmsClient.driver
            .send { $0
                .request("deleteGroupJoinRequestRequest")
                .field("requestId", requestId)
            }
            .map { _ in () }
    }

    public func queryJoinRequests(groupId: Int64, lastUpdatedDate: Date? = nil) -> Promise<GroupJoinRequestsWithVersion?> {
        return turmsClient.driver
            .send { $0
                .request("queryGroupJoinRequestsRequest")
                .field("groupId", groupId)
                .field("lastUpdatedDate", lastUpdatedDate)
            }
            .map { try $0.data.kind?.getKindData(GroupJoinRequestsWithVersion.self) }
    }

    public func querySentJoinRequests(lastUpdatedDate: Date? = nil) -> Promise<GroupJoinRequestsWithVersion?> {
        return turmsClient.driver
            .send { $0
                .request("queryGroupJoinRequestsRequest")
                .field("lastUpdatedDate", lastUpdatedDate)
            }
            .map { try $0.data.kind?.getKindData(GroupJoinRequestsWithVersion.self) }
    }

    /**
     * Note: Only the owner and managers have the right to fetch questions with answers
     */
    public func queryGroupJoinQuestionsRequest(
        groupId: Int64,
        withAnswers: Bool = false,
        lastUpdatedDate: Date? = nil) -> Promise<GroupJoinQuestionsWithVersion?> {
        return turmsClient.driver
            .send { $0
                .request("queryGroupJoinQuestionsRequest")
                .field("groupId", groupId)
                .field("withAnswers", withAnswers)
                .field("lastUpdatedDate", lastUpdatedDate)
            }
            .map { try $0.data.kind?.getKindData(GroupJoinQuestionsWithVersion.self) }
    }

    public func answerGroupQuestions(_ questionIdsAndAnswers: [Int64: String]) -> Promise<GroupJoinQuestionsAnswerResult> {
        if questionIdsAndAnswers.isEmpty {
            return Promise.value(GroupJoinQuestionsAnswerResult())
        }
        var map = [String: String]()
        questionIdsAndAnswers.forEach { key, value in
            map.updateValue(value, forKey: String(key))
        }
        return turmsClient.driver
            .send { $0
                .request("checkGroupJoinQuestionsAnswersRequest")
                .field("questionIdAndAnswer", map)
            }
            .map {
                let result = try $0.data.kind?.getKindData(GroupJoinQuestionsAnswerResult.self)
                if let value = result {
                    return value
                } else {
                    throw TurmsBusinessException(.missingData)
                }
            }
    }

    // Group Member
    public func addGroupMember(
        groupId: Int64,
        userId: Int64,
        name: String? = nil,
        role: GroupMemberRole? = nil,
        muteEndDate: Date? = nil) -> Promise<Void> {
        return turmsClient.driver
            .send { $0
                .request("createGroupMemberRequest")
                .field("groupId", groupId)
                .field("userId", userId)
                .field("name", name)
                .field("role", role)
                .field("muteEndDate", muteEndDate)
            }
            .map { _ in () }
    }

    public func quitGroup(groupId: Int64, successorId: Int64? = nil, quitAfterTransfer: Bool? = nil) -> Promise<Void> {
        return turmsClient.driver
            .send { $0
                .request("deleteGroupMemberRequest")
                .field("groupId", groupId)
                .field("groupMemberId", turmsClient.userService.userId)
                .field("successorId", successorId)
                .field("quitAfterTransfer", quitAfterTransfer)
            }
            .map { _ in () }
    }

    public func removeGroupMember(groupId: Int64, memberId: Int64) -> Promise<Void> {
        return turmsClient.driver
            .send { $0
                .request("deleteGroupMemberRequest")
                .field("groupId", groupId)
                .field("groupMemberId", memberId)
            }
            .map { _ in () }
    }

    public func updateGroupMemberInfo(
        groupId: Int64,
        memberId: Int64,
        name: String? = nil,
        role: GroupMemberRole? = nil,
        muteEndDate: Date? = nil) -> Promise<Void> {
        if Validator.areAllNil(name, role, muteEndDate) {
            return Promise.value(())
        }
        return turmsClient.driver
            .send { $0
                .request("updateGroupMemberRequest")
                .field("groupId", groupId)
                .field("memberId", memberId)
                .field("name", name)
                .field("role", role)
                .field("muteEndDate", muteEndDate)
            }
            .map { _ in () }
    }

    public func muteGroupMember(groupId: Int64, memberId: Int64, muteEndDate: Date) -> Promise<Void> {
        return updateGroupMemberInfo(
            groupId: groupId,
            memberId: memberId,
            muteEndDate: muteEndDate)
    }

    public func unmuteGroupMember(groupId: Int64, memberId: Int64) -> Promise<Void> {
        return muteGroupMember(
            groupId: groupId,
            memberId: memberId,
            muteEndDate: Date(timeIntervalSince1970: 0))
    }

    public func queryGroupMembers(groupId: Int64, withStatus: Bool = false, lastUpdatedDate: Date? = nil) -> Promise<GroupMembersWithVersion?> {
        return turmsClient.driver
            .send { $0
                .request("queryGroupMembersRequest")
                .field("groupId", groupId)
                .field("lastUpdatedDate", lastUpdatedDate)
                .field("withStatus", withStatus)
            }
            .map { try $0.data.kind?.getKindData(GroupMembersWithVersion.self) }
    }

    public func queryGroupMembersByMembersIds(groupId: Int64, membersIds: [Int64], withStatus: Bool = false) -> Promise<GroupMembersWithVersion?> {
        return turmsClient.driver
            .send { $0
                .request("queryGroupMembersRequest")
                .field("groupId", groupId)
                .field("groupMembersIds", membersIds)
                .field("withStatus", withStatus)
            }
            .map { try $0.data.kind?.getKindData(GroupMembersWithVersion.self) }
    }
}
