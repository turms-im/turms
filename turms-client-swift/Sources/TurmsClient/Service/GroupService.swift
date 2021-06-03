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
        groupTypeId: Int64? = nil) -> Promise<Int64> {
        return turmsClient.driver
            .send {
                $0.createGroupRequest = .with {
                    $0.name = name
                    if let v = intro {
                        $0.intro = v
                    }
                    if let v = announcement {
                        $0.announcement = v
                    }
                    if let v = minimumScore {
                        $0.minimumScore = v
                    }
                    if let v = muteEndDate {
                        $0.muteEndDate = v.toMillis()
                    }
                    if let v = groupTypeId {
                        $0.groupTypeID = v
                    }
                }
            }
            .map {
                try $0.getFirstId()
            }
    }

    public func deleteGroup(_ groupId: Int64) -> Promise<Void> {
        return turmsClient.driver
            .send {
                $0.deleteGroupRequest = .with {
                    $0.groupID = groupId
                }
            }
            .asVoid()
    }

    public func updateGroup(
        groupId: Int64,
        groupName: String? = nil,
        intro: String? = nil,
        announcement: String? = nil,
        minimumScore: Int32? = nil,
        groupTypeId: Int64? = nil,
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
            .send {
                $0.updateGroupRequest = .with {
                    $0.groupID = groupId
                    if let v = groupName {
                        $0.groupName = v
                    }
                    if let v = intro {
                        $0.intro = v
                    }
                    if let v = announcement {
                        $0.announcement = v
                    }
                    if let v = muteEndDate {
                        $0.muteEndDate = v.toMillis()
                    }
                    if let v = minimumScore {
                        $0.minimumScore = v
                    }
                    if let v = groupTypeId {
                        $0.groupTypeID = v
                    }
                    if let v = successorId {
                        $0.successorID = v
                    }
                    if let v = quitAfterTransfer {
                        $0.quitAfterTransfer = v
                    }
                }
            }
            .asVoid()
    }

    public func transferOwnership(groupId: Int64, successorId: Int64, quitAfterTransfer: Bool = false) -> Promise<Void> {
        return turmsClient.driver
            .send {
                $0.updateGroupRequest = .with {
                    $0.groupID = groupId
                    $0.successorID = successorId
                    $0.quitAfterTransfer = quitAfterTransfer
                }
            }
            .asVoid()
    }

    public func muteGroup(groupId: Int64, muteEndDate: Date) -> Promise<Void> {
        return turmsClient.driver
            .send {
                $0.updateGroupRequest = .with {
                    $0.groupID = groupId
                    $0.muteEndDate = muteEndDate.toMillis()
                }
            }
            .asVoid()
    }

    public func unmuteGroup(_ groupId: Int64) -> Promise<Void> {
        return muteGroup(groupId: groupId, muteEndDate: Date(timeIntervalSince1970: 0))
    }

    public func queryGroup(groupId: Int64, lastUpdatedDate: Date? = nil) -> Promise<GroupWithVersion?> {
        return turmsClient.driver
            .send {
                $0.queryGroupRequest = .with {
                    $0.groupID = groupId
                    if let v = lastUpdatedDate {
                        $0.lastUpdatedDate = v.toMillis()
                    }
                }
            }
            .map {
                try GroupWithVersion.from($0)
            }
    }

    public func queryJoinedGroupIds(_ lastUpdatedDate: Date? = nil) -> Promise<Int64ValuesWithVersion?> {
        return turmsClient.driver
            .send {
                $0.queryJoinedGroupIdsRequest = .with {
                    if let v = lastUpdatedDate {
                        $0.lastUpdatedDate = v.toMillis()
                    }
                }
            }
            .map {
                try $0.data.kind?.getKindData(Int64ValuesWithVersion.self)
            }
    }

    public func queryJoinedGroupInfos(_ lastUpdatedDate: Date? = nil) -> Promise<GroupsWithVersion?> {
        return turmsClient.driver
            .send {
                $0.queryJoinedGroupInfosRequest = .with {
                    if let v = lastUpdatedDate {
                        $0.lastUpdatedDate = v.toMillis()
                    }
                }
            }
            .map {
                try $0.data.kind?.getKindData(GroupsWithVersion.self)
            }
    }

    public func addGroupJoinQuestion(groupId: Int64, question: String, answers: [String], score: Int32) -> Promise<Int64> {
        return turmsClient.driver
            .send {
                $0.createGroupJoinQuestionRequest = .with {
                    $0.groupID = groupId
                    $0.question = question
                    $0.answers = answers
                    $0.score = score
                }
            }
            .map {
                try $0.getFirstId()
            }
    }

    public func deleteGroupJoinQuestion(_ questionId: Int64) -> Promise<Void> {
        return turmsClient.driver
            .send {
                $0.deleteGroupJoinQuestionRequest = .with {
                    $0.questionID = questionId
                }
            }
            .asVoid()
    }

    public func updateGroupJoinQuestion(questionId: Int64, question: String? = nil, answers: [String]? = nil, score: Int32? = nil) -> Promise<Void> {
        if Validator.areAllNil(question, answers, score) {
            return Promise.value(())
        }
        return turmsClient.driver
            .send {
                $0.updateGroupJoinQuestionRequest = .with {
                    $0.questionID = questionId
                    if let v = question {
                        $0.question = v
                    }
                    if let v = answers {
                        $0.answers = v
                    }
                    if let v = score {
                        $0.score = v
                    }
                }
            }
            .asVoid()
    }

    // Group Blocklist
    public func blockUser(groupId: Int64, userId: Int64) -> Promise<Void> {
        return turmsClient.driver
            .send {
                $0.createGroupBlockedUserRequest = .with {
                    $0.userID = userId
                    $0.groupID = groupId
                }
            }
            .asVoid()
    }

    public func unblockUser(groupId: Int64, userId: Int64) -> Promise<Void> {
        return turmsClient.driver
            .send {
                $0.deleteGroupBlockedUserRequest = .with {
                    $0.groupID = groupId
                    $0.userID = userId
                }
            }
            .asVoid()
    }

    public func queryBlockedUserIds(
        groupId: Int64,
        lastUpdatedDate: Date? = nil) -> Promise<Int64ValuesWithVersion?> {
        return turmsClient.driver
            .send {
                $0.queryGroupBlockedUserIdsRequest = .with {
                    $0.groupID = groupId
                    if let v = lastUpdatedDate {
                        $0.lastUpdatedDate = v.toMillis()
                    }
                }
            }
            .map {
                try $0.data.kind?.getKindData(Int64ValuesWithVersion.self)
            }
    }

    public func queryBlockedUserInfos(
        groupId: Int64,
        lastUpdatedDate: Date? = nil) -> Promise<UsersInfosWithVersion?> {
        return turmsClient.driver
            .send {
                $0.queryGroupBlockedUserInfosRequest = .with {
                    $0.groupID = groupId
                    if let v = lastUpdatedDate {
                        $0.lastUpdatedDate = v.toMillis()
                    }
                }
            }
            .map {
                try $0.data.kind?.getKindData(UsersInfosWithVersion.self)
            }
    }

    // Group Enrollment
    public func createInvitation(groupId: Int64, inviteeId: Int64, content: String) -> Promise<Int64> {
        return turmsClient.driver
            .send {
                $0.createGroupInvitationRequest = .with {
                    $0.groupID = groupId
                    $0.inviteeID = inviteeId
                    $0.content = content
                }
            }
            .map {
                try $0.getFirstId()
            }
    }

    public func deleteInvitation(_ invitationId: Int64) -> Promise<Void> {
        return turmsClient.driver
            .send {
                $0.deleteGroupInvitationRequest = .with {
                    $0.invitationID = invitationId
                }
            }
            .asVoid()
    }

    public func queryInvitations(groupId: Int64, lastUpdatedDate: Date? = nil) -> Promise<GroupInvitationsWithVersion?> {
        return turmsClient.driver
            .send {
                $0.queryGroupInvitationsRequest = .with {
                    $0.groupID = groupId
                    if let v = lastUpdatedDate {
                        $0.lastUpdatedDate = v.toMillis()
                    }
                }
            }
            .map {
                try $0.data.kind?.getKindData(GroupInvitationsWithVersion.self)
            }
    }

    public func queryInvitations(areSentByMe: Bool, lastUpdatedDate: Date? = nil) -> Promise<GroupInvitationsWithVersion?> {
        return turmsClient.driver
            .send {
                $0.queryGroupInvitationsRequest = .with {
                    $0.areSentByMe = areSentByMe
                    if let v = lastUpdatedDate {
                        $0.lastUpdatedDate = v.toMillis()
                    }
                }
            }
            .map {
                try $0.data.kind?.getKindData(GroupInvitationsWithVersion.self)
            }
    }

    public func createJoinRequest(groupId: Int64, content: String) -> Promise<Int64> {
        return turmsClient.driver
            .send {
                $0.createGroupJoinRequestRequest = .with {
                    $0.groupID = groupId
                    $0.content = content
                }
            }
            .map {
                try $0.getFirstId()
            }
    }

    public func deleteJoinRequest(_ requestId: Int64) -> Promise<Void> {
        return turmsClient.driver
            .send {
                $0.deleteGroupJoinRequestRequest = .with {
                    $0.requestID = requestId
                }
            }
            .asVoid()
    }

    public func queryJoinRequests(groupId: Int64, lastUpdatedDate: Date? = nil) -> Promise<GroupJoinRequestsWithVersion?> {
        return turmsClient.driver
            .send {
                $0.queryGroupJoinRequestsRequest = .with {
                    $0.groupID = groupId
                    if let v = lastUpdatedDate {
                        $0.lastUpdatedDate = v.toMillis()
                    }
                }
            }
            .map {
                try $0.data.kind?.getKindData(GroupJoinRequestsWithVersion.self)
            }
    }

    public func querySentJoinRequests(lastUpdatedDate: Date? = nil) -> Promise<GroupJoinRequestsWithVersion?> {
        return turmsClient.driver
            .send {
                $0.queryGroupJoinRequestsRequest = .with {
                    if let v = lastUpdatedDate {
                        $0.lastUpdatedDate = v.toMillis()
                    }
                }
            }
            .map {
                try $0.data.kind?.getKindData(GroupJoinRequestsWithVersion.self)
            }
    }

    /**
     * Note: Only the owner and managers have the right to fetch questions with answers
     */
    public func queryGroupJoinQuestionsRequest(
        groupId: Int64,
        withAnswers: Bool = false,
        lastUpdatedDate: Date? = nil) -> Promise<GroupJoinQuestionsWithVersion?> {
        return turmsClient.driver
            .send {
                $0.queryGroupJoinQuestionsRequest = .with {
                    $0.groupID = groupId
                    $0.withAnswers = withAnswers
                    if let v = lastUpdatedDate {
                        $0.lastUpdatedDate = v.toMillis()
                    }
                }
            }
            .map {
                try $0.data.kind?.getKindData(GroupJoinQuestionsWithVersion.self)
            }
    }

    public func answerGroupQuestions(_ questionIdAndAnswer: [Int64: String]) -> Promise<GroupJoinQuestionsAnswerResult> {
        if questionIdAndAnswer.isEmpty {
            return Promise.value(GroupJoinQuestionsAnswerResult())
        }
        return turmsClient.driver
            .send {
                $0.checkGroupJoinQuestionsAnswersRequest = .with {
                    $0.questionIDAndAnswer = questionIdAndAnswer
                }
            }
            .map {
                let result = try $0.data.kind?.getKindData(GroupJoinQuestionsAnswerResult.self)
                if let value = result {
                    return value
                } else {
                    throw TurmsBusinessError(TurmsStatusCode.invalidResponse)
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
            .send {
                $0.createGroupMemberRequest = .with {
                    $0.groupID = groupId
                    $0.userID = userId
                    if let v = name {
                        $0.name = v
                    }
                    if let v = role {
                        $0.role = v
                    }
                    if let v = muteEndDate {
                        $0.muteEndDate = v.toMillis()
                    }
                }
            }
            .asVoid()
    }

    public func quitGroup(groupId: Int64, successorId: Int64? = nil, quitAfterTransfer: Bool? = nil) -> Promise<Void> {
        return turmsClient.driver
            .send {
                $0.deleteGroupMemberRequest = .with {
                    $0.groupID = groupId
                    $0.memberID = turmsClient.userService.userInfo!.userId
                    if let v = successorId {
                        $0.successorID = v
                    }
                    if let v = quitAfterTransfer {
                        $0.quitAfterTransfer = v
                    }
                }
            }
            .asVoid()
    }

    public func removeGroupMember(groupId: Int64, memberId: Int64) -> Promise<Void> {
        return turmsClient.driver
            .send {
                $0.deleteGroupMemberRequest = .with {
                    $0.groupID = groupId
                    $0.memberID = memberId
                }
            }
            .asVoid()
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
            .send {
                $0.updateGroupMemberRequest = .with {
                    $0.groupID = groupId
                    $0.memberID = memberId
                    if let v = name {
                        $0.name = v
                    }
                    if let v = role {
                        $0.role = v
                    }
                    if let v = muteEndDate {
                        $0.muteEndDate = v.toMillis()
                    }
                }
            }
            .asVoid()
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
            .send {
                $0.queryGroupMembersRequest = .with {
                    $0.groupID = groupId
                    if let v = lastUpdatedDate {
                        $0.lastUpdatedDate = v.toMillis()
                    }
                    $0.withStatus = withStatus
                }
            }
            .map {
                try $0.data.kind?.getKindData(GroupMembersWithVersion.self)
            }
    }

    public func queryGroupMembersByMemberIds(groupId: Int64, memberIds: [Int64], withStatus: Bool = false) -> Promise<GroupMembersWithVersion?> {
        return turmsClient.driver
            .send {
                $0.queryGroupMembersRequest = .with {
                    $0.groupID = groupId
                    $0.memberIds = memberIds
                    $0.withStatus = withStatus
                }
            }
            .map {
                try $0.data.kind?.getKindData(GroupMembersWithVersion.self)
            }
    }

}