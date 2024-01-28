import Foundation
import PromiseKit

public class GroupService {
    private weak var turmsClient: TurmsClient!

    public init(_ turmsClient: TurmsClient) {
        self.turmsClient = turmsClient
    }

    /// Create a new group.
    /// The logged-in user will become the group creator and owner.
    ///
    /// Common Scenarios:
    /// * To add new group members, you can use methods like ``addGroupMembers``.
    ///
    /// Authorization:
    /// * If the groups owned by the logged-in user has exceeded the limit,
    ///   throws ``ResponseError`` with the code ``ResponseStatusCode/maxOwnedGroupsReached``.
    ///
    /// Notifications:
    /// * If the server property `turms.service.notification.group-created.notify-requester-other-online-sessions`
    ///   is true (true by default), the server will send a create group notification to all other online sessions of the logged-in user actively.
    ///
    /// - Parameters:
    ///   - name: The group name.
    ///   - intro: The group introduction.
    ///   - announcement: The group announcement.
    ///   - minScore: The group minimum score that a non-member user needs to acquire
    ///     to join the group when answering group questions.
    ///   - typeId: The group type ID.
    ///     If null, the default group type configured in turms-service will be used.
    ///
    ///     Authorization:
    ///     * If the group type ID does not exist,
    ///       throws ``ResponseError`` with the code ``ResponseStatusCode/createGroupWithNonexistentGroupType``.
    ///     * If the logged-in user does not have the permission to create the group with `typeId`,
    ///       throws ``ResponseError`` with the code ``ResponseStatusCode/noPermissionToCreateGroupWithGroupType``.
    ///
    /// - Returns: The group ID.
    ///
    /// - Throws: ``ResponseError`` if an error occurs.
    public func createGroup(
        name: String,
        intro: String? = nil,
        announcement: String? = nil,
        minScore: Int32? = nil,
        muteEndDate: Date? = nil,
        typeId: Int64? = nil
    ) -> Promise<Response<Int64>> {
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
                    if let v = minScore {
                        $0.minScore = v
                    }
                    if let v = muteEndDate {
                        $0.muteEndDate = v.toMillis()
                    }
                    if let v = typeId {
                        $0.typeID = v
                    }
                }
            }
            .map {
                try $0.toResponse {
                    try $0.getLongOrThrow()
                }
            }
    }

    /// Delete the target group.
    ///
    /// Authorization:
    /// * If the logged-in user is not the group owner, or the target group does not exist,
    ///   throws ``ResponseError`` with the code ``ResponseStatusCode/notGroupOwnerToDeleteGroup``.
    ///
    /// Notifications:
    /// * If the server property `turms.service.notification.group-deleted.notify-requester-other-online-sessions`
    ///   is true (true by default),
    ///   the server will send a delete group notification to all other online sessions of the logged-in user actively.
    /// * If the server property `turms.service.notification.group-deleted.notify-group-members`
    ///   is true (true by default),
    ///   the server will send a delete group notification to all group members of the target group.
    ///
    /// - Throws: ``ResponseError`` if an error occurs.
    public func deleteGroup(_ groupId: Int64) -> Promise<Response<Void>> {
        return turmsClient.driver
            .send {
                $0.deleteGroupRequest = .with {
                    $0.groupID = groupId
                }
            }
            .map {
                try $0.toResponse()
            }
    }

    /// Update the target group.
    ///
    /// Notifications:
    /// * If the server property `turms.service.notification.group-updated.notify-requester-other-online-sessions`
    ///   the server will send an update group notification to all other online sessions of the logged-in user actively.
    /// * If the server property `turms.service.notification.group-updated.notify-group-members`
    ///   is true (true by default),
    ///   the server will send an update group notification to all group members of the target group actively.
    ///
    /// - Parameters:
    ///   - groupId: The target group ID to find the group for updating.
    ///   - name: The new group name.
    ///     If null, the group name will not be changed.
    ///
    ///     Authorization:
    ///     * Whether the logged-in user can change the group name depends on the group type.
    ///       If not null and the logged-in user does NOT have the permission to change the group name,
    ///       throws ``ResponseError`` with the code ``ResponseStatusCode/notGroupMemberToUpdateGroupInfo``
    ///       or ``ResponseStatusCode/notGroupOwnerOrManagerToUpdateGroupInfo``
    ///       or ``ResponseStatusCode/notGroupOwnerToUpdateGroupInfo``.
    ///   - intro: The new group introduction.
    ///     If null, the group introduction will not be changed.
    ///
    ///     Authorization:
    ///     * Whether the logged-in user can change the group introduction depends on the group type.
    ///       If not null and the logged-in user does NOT have the permission to change the group introduction,
    ///       throws ``ResponseError`` with the code ``ResponseStatusCode/notGroupMemberToUpdateGroupInfo``
    ///       or ``ResponseStatusCode/notGroupOwnerOrManagerToUpdateGroupInfo``
    ///       or ``ResponseStatusCode/notGroupOwnerToUpdateGroupInfo``.
    ///   - announcement: The new group announcement.
    ///     If null, the group announcement will not be changed.
    ///
    ///     Authorization:
    ///     * Whether the logged-in user can change the group announcement depends on the group type.
    ///       If not null and the logged-in user does NOT have the permission to change the group announcement,
    ///       throws ``ResponseError`` with the code ``ResponseStatusCode/notGroupMemberToUpdateGroupInfo``
    ///       or ``ResponseStatusCode/notGroupOwnerOrManagerToUpdateGroupInfo``
    ///       or ``ResponseStatusCode/notGroupOwnerToUpdateGroupInfo``.
    ///   - minScore: The new group minimum score that a non-member user needs to acquire
    ///     to join the group when answering group questions.
    ///     If null, the group minimum score will not be changed.
    ///
    ///     Authorization:
    ///     * Whether the logged-in user can change the group minimum score depends on the group type.
    ///       If not null and the logged-in user does NOT have the permission to change the group minimum score,
    ///       throws ``ResponseError`` with the code ``ResponseStatusCode/notGroupMemberToUpdateGroupInfo``
    ///       or ``ResponseStatusCode/notGroupOwnerOrManagerToUpdateGroupInfo``
    ///       or ``ResponseStatusCode/notGroupOwnerToUpdateGroupInfo``.
    ///   - typeId: The new group type ID.
    ///     If null, the group type ID will not be changed.
    ///
    ///     Authorization:
    ///     * If the server property `turms.service.group.allow-group-owner-change-group-type`
    ///       is true (false by default), the logged-in user can change the group type.
    ///       Otherwise, throws ``ResponseError`` with the code ``ResponseStatusCode/updatingGroupTypeIsDisabled``.
    ///     * If the logged-in user is not the group owner,
    ///       throws ``ResponseError`` with the code ``ResponseStatusCode/notGroupOwnerToUpdateGroupType``.
    ///     * If the logged-in user is not allowed to use the group type,
    ///       throws ``ResponseError`` with the code ``ResponseStatusCode/noPermissionToUpdateGroupToGroupType``.
    ///     * If `typeId` doesn't exist, throws ``ResponseError`` with the code ``ResponseStatusCode/updateGroupToNonexistentGroupType``.
    ///   - muteEndDate: The new group mute end date.
    ///     Before the group mute end date, the group members will not be able
    ///     to send messages.
    ///
    ///     Authorization:
    ///     * Only the group owner or group managers can mute or unmute the group.
    ///       If the logged-in user is not the owner or manager of the group,
    ///       ``ResponseError`` with the code ``ResponseStatusCode/notGroupOwnerOrManagerToMuteGroupMember``
    ///       will be thrown.
    ///   - successorId: The new successor ID.
    ///     If the logged-in user is the owner of the group, they must transfer the group ownership to the `successorId`,
    ///     throws ``ResponseError`` with the code ``ResponseStatusCode/groupOwnerQuitWithoutSpecifyingSuccessor`` otherwise.
    ///     And the successor will become the group owner.
    ///     The successor must already be a member of the group, throws ``ResponseError`` with the code
    ///     ``ResponseStatusCode/groupSuccessorNotGroupMember`` otherwise.
    ///   - quitAfterTransfer: Whether to quit the group after transfer the group ownership.
    ///     If false, the logged-in user will become a normal group member (not the group admin).
    ///     If null, the value will not be changed.
    ///
    ///     Authorization:
    ///     * If the logged-in user is not the owner of the group,
    ///       throws ``ResponseError`` with the code ``ResponseStatusCode/notGroupOwnerToTransferGroup``.
    ///
    /// - Throws: ``ResponseError`` if an error occurs.
    public func updateGroup(
        groupId: Int64,
        name: String? = nil,
        intro: String? = nil,
        announcement: String? = nil,
        minScore: Int32? = nil,
        typeId: Int64? = nil,
        muteEndDate: Date? = nil,
        successorId: Int64? = nil,
        quitAfterTransfer: Bool? = nil
    ) -> Promise<Response<Void>> {
        if Validator.areAllNil(
            name,
            intro,
            announcement,
            minScore,
            typeId,
            muteEndDate,
            successorId
        ) {
            return Promise.value(Response.empty())
        }
        return turmsClient.driver
            .send {
                $0.updateGroupRequest = .with {
                    $0.groupID = groupId
                    if let v = name {
                        $0.name = v
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
                    if let v = minScore {
                        $0.minScore = v
                    }
                    if let v = typeId {
                        $0.typeID = v
                    }
                    if let v = successorId {
                        $0.successorID = v
                    }
                    if let v = quitAfterTransfer {
                        $0.quitAfterTransfer = v
                    }
                }
            }
            .map {
                try $0.toResponse()
            }
    }

    /// Transfer the group ownership.
    ///
    /// Notifications:
    /// * If the server property `turms.service.notification.group-updated.notify-requester-other-online-sessions`
    ///   the server will send a update group notification to all other online sessions of the logged-in user actively.
    /// * If the server property `turms.service.notification.group-updated.notify-group-members`
    ///   is true (true by default),
    ///   the server will send a update group notification to all group members of the target group actively.
    ///
    /// - Parameters:
    ///   - groupId: The target group ID to find the group for updating.
    ///   - successorId: The new successor ID.
    ///     If the logged-in user is the owner of the group, they must transfer the group ownership to the `successorId`,
    ///     throws ``ResponseError`` with the code ``ResponseStatusCode/groupOwnerQuitWithoutSpecifyingSuccessor`` otherwise.
    ///     And the successor will become the group owner.
    ///     The successor must already be a member of the group, throws ``ResponseError`` with the code
    ///     ``ResponseStatusCode/groupSuccessorNotGroupMember`` otherwise.
    ///   - quitAfterTransfer: Whether to quit the group after transfer the group ownership.
    ///     If false, the logged-in user will become a normal group member (not the group admin).
    ///     If null, the value will not be changed.
    ///     Authorization: If the logged-in user is not the owner of the group,
    ///     throws ``ResponseError`` with the code ``ResponseStatusCode/notGroupOwnerToTransferGroup``.
    ///
    /// - Throws: ``ResponseError`` if an error occurs.
    public func transferOwnership(groupId: Int64, successorId: Int64, quitAfterTransfer: Bool = false) -> Promise<Response<Void>> {
        return turmsClient.driver
            .send {
                $0.updateGroupRequest = .with {
                    $0.groupID = groupId
                    $0.successorID = successorId
                    $0.quitAfterTransfer = quitAfterTransfer
                }
            }
            .map {
                try $0.toResponse()
            }
    }

    /// Mute the target group.
    ///
    /// Notifications:
    /// * If the server property `turms.service.notification.group-updated.notify-requester-other-online-sessions`
    ///   the server will send a update group notification to all other online sessions of the logged-in user actively.
    /// * If the server property `turms.service.notification.group-updated.notify-group-members`
    ///   is true (true by default),
    ///   the server will send a update group notification to all group members of the target group actively.
    ///
    /// - Parameters:
    ///   - groupId: The target group ID to find the group for updating.
    ///   - muteEndDate: The new group mute end date.
    ///     Before the group mute end date, the group members will not be able
    ///     to send messages.
    ///
    ///     Authorization:
    ///     * Only the group owner or group managers can mute or unmute the group.
    ///       If the logged-in user is not the owner or manager of the group,
    ///       throws ``ResponseError`` with the code ``ResponseStatusCode/notGroupOwnerOrManagerToMuteGroupMember``.
    ///
    /// - Throws: ``ResponseError`` if an error occurs.
    public func muteGroup(groupId: Int64, muteEndDate: Date) -> Promise<Response<Void>> {
        return turmsClient.driver
            .send {
                $0.updateGroupRequest = .with {
                    $0.groupID = groupId
                    $0.muteEndDate = muteEndDate.toMillis()
                }
            }
            .map {
                try $0.toResponse()
            }
    }

    /// Unmute the target group.
    ///
    /// Authorization:
    /// * Only the group owner or group managers can mute or unmute the group.
    ///   If the logged-in user is not the owner or manager of the group,
    ///   throws ``ResponseError`` with the code ``ResponseStatusCode/notGroupOwnerOrManagerToMuteGroupMember``.
    ///
    /// Notifications:
    /// * If the server property `turms.service.notification.group-updated.notify-requester-other-online-sessions`
    ///   the server will send a update group notification to all other online sessions of the logged-in user actively.
    /// * If the server property `turms.service.notification.group-updated.notify-group-members`
    ///   is true (true by default),
    ///   the server will send a update group notification to all group members of the target group actively.
    ///
    /// - Parameters:
    ///   - groupId: The target group ID to find the group for updating.
    ///
    /// - Throws: ``ResponseError`` if an error occurs.
    public func unmuteGroup(_ groupId: Int64) -> Promise<Response<Void>> {
        return muteGroup(groupId: groupId, muteEndDate: Date(timeIntervalSince1970: 0))
    }

    /// Find groups.
    ///
    /// - Parameters:
    ///   - groupIds: The target group IDs for finding groups.
    ///   - lastUpdatedDate: The last updated date of groups on local.
    ///     The server will only return groups that are updated after `lastUpdatedDate`.
    ///     If null, all groups will be returned.
    ///
    /// - Returns: A list of groups.
    ///
    /// - Throws: ``ResponseError`` if an error occurs.
    public func queryGroups(groupIds: Set<Int64>, lastUpdatedDate: Date? = nil) -> Promise<Response<[Group]>> {
        if groupIds.isEmpty {
            return Promise.value(Response.emptyArray())
        }
        return turmsClient.driver
            .send {
                $0.queryGroupsRequest = .with {
                    $0.groupIds = Array(groupIds)
                    if let v = lastUpdatedDate {
                        $0.lastUpdatedDate = v.toMillis()
                    }
                }
            }
            .map {
                try $0.toResponse {
                    $0.groupsWithVersion.groups
                }
            }
    }

    /// Find group IDs that the logged-in user has joined.
    ///
    /// - Parameters:
    ///   - lastUpdatedDate: The last updated date of group IDs that the logged-in user has joined stored locally.
    ///     The server will only return group IDs that are updated after `lastUpdatedDate`.
    ///     If null, all group IDs will be returned.
    ///
    /// - Returns: A list of group IDs and the version.
    /// Note: The version can be used to update the last updated date on local.
    ///
    /// - Throws: ``ResponseError`` if an error occurs.
    public func queryJoinedGroupIds(_ lastUpdatedDate: Date? = nil) -> Promise<Response<LongsWithVersion?>> {
        return turmsClient.driver
            .send {
                $0.queryJoinedGroupIdsRequest = .with {
                    if let v = lastUpdatedDate {
                        $0.lastUpdatedDate = v.toMillis()
                    }
                }
            }
            .map {
                try $0.toResponse {
                    try $0.kind?.getKindData(LongsWithVersion.self)
                }
            }
    }

    /// Find groups that the logged-in user has joined.
    ///
    /// - Parameters:
    ///   - lastUpdatedDate: The last updated date of groups that the logged-in user has joined stored locally.
    ///     The server will only return groups that are updated after `lastUpdatedDate`.
    ///     If null, all groups will be returned.
    ///
    /// - Throws: ``ResponseError`` if an error occurs.
    public func queryJoinedGroupInfos(_ lastUpdatedDate: Date? = nil) -> Promise<Response<GroupsWithVersion?>> {
        return turmsClient.driver
            .send {
                $0.queryJoinedGroupInfosRequest = .with {
                    if let v = lastUpdatedDate {
                        $0.lastUpdatedDate = v.toMillis()
                    }
                }
            }
            .map {
                try $0.toResponse {
                    try $0.kind?.getKindData(GroupsWithVersion.self)
                }
            }
    }

    /// Add group join/membership questions.
    ///
    /// Authorization:
    /// * Only the group owner or group managers can add group membership questions.
    ///   Otherwise, throws ``ResponseError`` with the code ``ResponseStatusCode/notGroupOwnerOrManagerToCreateGroupQuestion``.
    /// * Only the group that use `question` as the join strategy can add group membership questions.
    ///   Otherwise, throws ``ResponseError`` with the code
    ///   ``ResponseStatusCode/createGroupQuestionForGroupUsingJoinRequest``
    ///   or ``ResponseStatusCode/createGroupQuestionForGroupUsingInvitation``
    ///   or ``ResponseStatusCode/createGroupQuestionForGroupUsingMembershipRequest``.
    /// * If the group has been deleted,
    ///   throws ``ResponseError`` with the code ``ResponseStatusCode/createGroupQuestionForInactiveGroup``.
    ///
    /// - Parameters:
    ///   - groupId: The target group ID.
    ///   - questions: The group membership questions.
    ///
    /// - Returns: New group questions IDs.
    ///
    /// - Throws: ``ResponseError`` if an error occurs.
    public func addGroupJoinQuestions(groupId: Int64, questions: [NewGroupJoinQuestion]) -> Promise<Response<[Int64]>> {
        if questions.isEmpty {
            return Promise.value(Response.emptyArray())
        }
        return turmsClient.driver
            .send {
                $0.createGroupJoinQuestionsRequest = try .with {
                    $0.groupID = groupId
                    $0.questions = try questions.map { question in
                        try .with { builder in
                            guard let answers = question.answers.array as? [String] else {
                                throw ResponseError(
                                    code: .illegalArgument,
                                    reason: "The answer of group must be a string"
                                )
                            }
                            if answers.isEmpty {
                                throw ResponseError(
                                    code: .illegalArgument,
                                    reason: "The answers of group must not be empty"
                                )
                            }
                            builder.question = question.question
                            builder.answers = answers
                            builder.score = question.score
                        }
                    }
                }
            }
            .map {
                try $0.toResponse {
                    $0.longsWithVersion.longs
                }
            }
    }

    /// Delete group join/membership questions.
    ///
    /// Authorization:
    /// * Only the group owner or group managers can delete group membership questions.
    ///   Otherwise, throws ``ResponseError`` with the code ``ResponseStatusCode/notGroupOwnerOrManagerToDeleteGroupQuestion``.
    ///
    /// - Parameters:
    ///   - groupId: The target group ID.
    ///   - questionIds: The group membership question IDs.
    ///
    /// - Throws: ``ResponseError`` if an error occurs.
    public func deleteGroupJoinQuestions(groupId: Int64, questionIds: [Int64]) -> Promise<Response<Void>> {
        if questionIds.isEmpty {
            return Promise.value(Response.empty())
        }
        return turmsClient.driver
            .send {
                $0.deleteGroupJoinQuestionsRequest = .with {
                    $0.groupID = groupId
                    $0.questionIds = questionIds
                }
            }
            .map {
                try $0.toResponse()
            }
    }

    /// Update group join/membership questions.
    ///
    /// Authorization:
    /// * Only the group owner or group managers can update group membership questions.
    ///   Otherwise, throws ``ResponseError`` with the code ``ResponseStatusCode/notGroupOwnerOrManagerToUpdateGroupQuestion``.
    ///
    /// - Parameters:
    ///   - questionId: The target question ID.
    ///   - question: The question.
    ///     If null, the question will not be updated.
    ///   - answers: The answers.
    ///     If null, the answers will not be updated.
    ///   - score: The score.
    ///     If null, the score will not be updated.
    ///
    /// - Throws: ``ResponseError`` if an error occurs.
    public func updateGroupJoinQuestion(questionId: Int64, question: String? = nil, answers: [String]? = nil, score: Int32? = nil) -> Promise<Response<Void>> {
        if Validator.areAllNil(question, answers, score) {
            return Promise.value(Response.empty())
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
            .map {
                try $0.toResponse()
            }
    }

    // Group Blocklist

    /// Block a user in the group.
    /// If the logged-in user is a group member, the server will delete the group member automatically.
    ///
    /// Authorization:
    /// * Only the group owner or group managers can block users.
    ///   Otherwise, throws ``ResponseError`` with the code ``ResponseStatusCode/notGroupOwnerOrManagerToAddBlockedUser``.
    /// * If the logged-in user trys to block themselves,
    ///   throws ``ResponseError`` with the code ``ResponseStatusCode/cannotBlockOneself``.
    ///
    /// Notifications:
    /// * If the server property `turms.service.notification.group-blocked-user-added.notify-requester-other-online-sessions`
    ///   is true (true by default), the server will send a block user notification to all other online sessions of the logged-in user actively.
    /// * If the server property `turms.service.notification.group-blocked-user-added.notify-blocked-user`,
    ///   is true (false by default), the server will send a block user notification to the target user actively.
    /// * If the server property `turms.service.notification.group-blocked-user-added.notify-group-members`
    ///   is true (false by default), the server will send a block user notification to all group members of the target group actively.
    ///
    /// - Parameters:
    ///   - groupId: The target group ID.
    ///   - userId: The target user ID.
    ///
    /// - Throws: ``ResponseError`` if an error occurs.
    public func blockUser(groupId: Int64, userId: Int64) -> Promise<Response<Void>> {
        return turmsClient.driver
            .send {
                $0.createGroupBlockedUserRequest = .with {
                    $0.userID = userId
                    $0.groupID = groupId
                }
            }
            .map {
                try $0.toResponse()
            }
    }

    /// Unblock a user in the group.
    ///
    /// Authorization:
    /// * Only the group owner or group managers can unblock users.
    ///   Otherwise, throws ``ResponseError`` with the code ``ResponseStatusCode/notGroupOwnerOrManagerToRemoveBlockedUser``.
    ///
    /// Notifications:
    /// * If the server property `turms.service.notification.group-blocked-user-removed.notify-requester-other-online-sessions`
    ///   is true (true by default), the server will send a unblock user notification to all other online sessions of the logged-in user actively.
    /// * If the server property `turms.service.notification.group-blocked-user-removed.notify-blocked-user`,
    ///   is true (false by default), the server will send a unblock user notification to the target user actively.
    /// * If the server property `turms.service.notification.group-blocked-user-removed.notify-group-members`
    ///   is true (false by default), the server will send a unblock user notification to all group members of the target group actively.
    ///
    /// - Parameters:
    ///   - groupId: The target group ID.
    ///   - userId: The target user ID.
    ///
    /// - Throws: ``ResponseError`` if an error occurs.
    public func unblockUser(groupId: Int64, userId: Int64) -> Promise<Response<Void>> {
        return turmsClient.driver
            .send {
                $0.deleteGroupBlockedUserRequest = .with {
                    $0.groupID = groupId
                    $0.userID = userId
                }
            }
            .map {
                try $0.toResponse()
            }
    }

    /// Find blocked user IDs.
    ///
    /// - Parameters:
    ///   - groupId: The target group ID.
    ///   - lastUpdatedDate: The last updated date of blocked user IDs stored locally.
    ///     The server will only return blocked user IDs that are updated after `lastUpdatedDate`.
    ///     If null, all blocked user IDs will be returned.
    ///
    /// - Throws: ``ResponseError`` if an error occurs.
    public func queryBlockedUserIds(
        groupId: Int64,
        lastUpdatedDate: Date? = nil
    ) -> Promise<Response<LongsWithVersion?>> {
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
                try $0.toResponse {
                    try $0.kind?.getKindData(LongsWithVersion.self)
                }
            }
    }

    /// Find blocked user infos.
    ///
    /// - Parameters:
    ///   - groupId: The target group ID.
    ///   - lastUpdatedDate: The last updated date of blocked user infos stored locally.
    ///     The server will only return blocked user infos that are updated after `lastUpdatedDate`.
    ///     If null, all blocked user infos will be returned.
    ///
    /// - Throws: ``ResponseError`` if an error occurs.
    public func queryBlockedUserInfos(
        groupId: Int64,
        lastUpdatedDate: Date? = nil
    ) -> Promise<Response<UserInfosWithVersion?>> {
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
                try $0.toResponse {
                    try $0.kind?.getKindData(UserInfosWithVersion.self)
                }
            }
    }

    // Group Enrollment

    /// Create an invitation.
    ///
    /// Authorization:
    /// * If `inviteeId` is already a group member,
    ///   throws ``ResponseError`` with the code ``ResponseStatusCode/sendGroupInvitationToGroupMember``.
    /// * Depending on the group join strategy, if the group do not use the invitation strategy
    ///   throws ``ResponseError`` with the code
    ///   ``ResponseStatusCode/notGroupOwnerToSendGroupInvitation``,
    ///   ``ResponseStatusCode/notGroupOwnerOrManagerToSendGroupInvitation``,
    ///   or ``ResponseStatusCode/notGroupMemberToSendGroupInvitation``.
    /// * If the group allows adding users as new group members without users' approval,
    ///   throws ``ResponseError`` with the code ``ResponseStatusCode/sendGroupInvitationToGroupNotRequiringUsersApproval``.
    /// * If the group does not exist,
    ///   throws ``ResponseError`` with the code ``ResponseStatusCode/addUserToInactiveGroup``.
    ///
    /// Notifications:
    /// * If the server property `turms.service.notification.group-invitation-added.notify-requester-other-online-sessions`
    ///   is true (true by default), the server will send a new invitation notification to all other online sessions of the logged-in user actively.
    /// * If the server property `turms.service.notification.group-invitation-added.notify-group-owner-and-managers`
    ///   is true (true by default), the server will send a new invitation notification to the group owner and managers actively.
    /// * If the server property `turms.service.notification.group-invitation-added.notify-group-members`,
    ///   is true (false by default), the server will send a new invitation notification to all group members of the target group actively.
    /// * If the server property `turms.service.notification.group-invitation-added.notify-invitee`,
    ///   is true (true by default), the server will send a new invitation notification to the target user actively.
    ///
    /// - Parameters:
    ///   - groupId: The target group ID.
    ///   - inviteeId: The target user ID.
    ///   - content: The invitation content.
    ///
    /// - Returns: The invitation ID.
    ///
    /// - Throws: ``ResponseError`` if an error occurs.
    public func createInvitation(groupId: Int64, inviteeId: Int64, content: String) -> Promise<Response<Int64>> {
        return turmsClient.driver
            .send {
                $0.createGroupInvitationRequest = .with {
                    $0.groupID = groupId
                    $0.inviteeID = inviteeId
                    $0.content = content
                }
            }
            .map {
                try $0.toResponse {
                    try $0.getLongOrThrow()
                }
            }
    }

    /// Delete/Recall an invitation.
    ///
    /// Authorization:
    /// * If the server property `turms.service.group.invitation.allow-recall-pending-invitation-by-sender`
    ///   is true (false by default), the logged-in user can recall pending invitations sent by themselves.
    ///   Otherwise, throws ``ResponseError``.
    /// * If the server property `turms.service.group.invitation.allow-recall-pending-invitation-by-owner-and-manager`
    ///   is true (false by default), the logged-in user can recall pending invitations only if they are the group owner or manager of the invitation.
    ///   Otherwise, throws ``ResponseError``.
    /// * For the above two cases, the following codes will be thrown according to different properties:
    ///   ``ResponseStatusCode/recallingGroupInvitationIsDisabled`` if the above two properties are false.
    ///   ``ResponseStatusCode/notGroupOwnerOrManagerToRecallGroupInvitation``,
    ///   ``ResponseStatusCode/notGroupOwnerOrManagerOrSenderToRecallGroupInvitation``
    /// * If the group invitation is not pending (e.g. expired, accepted, deleted, etc),
    ///   throws ``ResponseError`` with the code ``ResponseStatusCode/recallNonPendingGroupInvitation``.
    ///
    /// Notifications:
    /// * If the server property `turms.service.notification.group-invitation-recalled.notify-requester-other-online-sessions`
    ///   is true (true by default), the server will send a delete invitation notification to all other online sessions of the logged-in user actively.
    /// * If the server property `turms.service.notification.group-invitation-recalled.notify-group-owner-and-managers`
    ///   is true (true by default), the server will send a delete invitation notification to the group owner and managers actively.
    /// * If the server property `turms.service.notification.group-invitation-recalled.notify-group-members`,
    ///   is true (false by default), the server will send a delete invitation notification to all group members of the target group actively.
    /// * If the server property `turms.service.notification.group-invitation-recalled.notify-invitee`,
    ///   is true (true by default), the server will send a delete invitation notification to the target user actively.
    ///
    /// - Throws: ``ResponseError`` if an error occurs.
    public func deleteInvitation(_ invitationId: Int64) -> Promise<Response<Void>> {
        return turmsClient.driver
            .send {
                $0.deleteGroupInvitationRequest = .with {
                    $0.invitationID = invitationId
                }
            }
            .map {
                try $0.toResponse()
            }
    }

    /// Reply to a group invitation.
    ///
    /// If the logged-in user accepts an invitation sent by a group,
    /// the user will become a group member automatically.
    ///
    /// Authorization:
    /// * If the logged-in user is not the invitee of the group invitation,
    ///   throws ``ResponseError`` with the code ``ResponseStatusCode/notInviteeToUpdateGroupInvitation``.
    /// * If the group invitation is not pending (e.g. expired, accepted, deleted, etc),
    ///   throws ``ResponseError`` with the code ``ResponseStatusCode/updateNonPendingGroupInvitation``.
    ///
    /// Notifications:
    /// * If the server property `turms.service.notification.group-invitation-replied.notify-requester-other-online-sessions`,
    ///   is true (true by default), the server will send a reply group invitation notification to all other online sessions of the logged-in user actively.
    /// * If the server property `turms.service.notification.group-invitation-replied.notify-group-invitation-inviter`,
    ///   is true (true by default), the server will send a reply group invitation notification to the group join request sender actively.
    /// * If the server property `turms.service.notification.group-invitation-replied.notify-group-members`,
    ///   is true (false by default), the server will send a reply group invitation notification to all group members of the target group actively.
    /// * If the server property `turms.service.notification.group-invitation-replied.notify-group-owner-and-managers`,
    ///   is true (true by default), the server will send a reply group invitation notification to the group owner and managers actively.
    ///
    /// - Parameters:
    ///   - invitationId: The invitation ID.
    ///   - responseAction: The response action.
    ///   - reason: The reason of the response.
    ///
    /// - Throws: ``ResponseError`` if an error occurs.
    public func replyInvitation(invitationId: Int64, responseAction: ResponseAction, reason: String? = nil) -> Promise<Response<Void>> {
        return turmsClient.driver
            .send {
                $0.updateGroupInvitationRequest = .with {
                    $0.invitationID = invitationId
                    $0.responseAction = responseAction
                    if let v = reason {
                        $0.reason = v
                    }
                }
            }
            .map {
                try $0.toResponse()
            }
    }

    /// Find invitations.
    ///
    /// - Parameters:
    ///   - groupId: The target group ID.
    ///   - lastUpdatedDate: The last updated date of invitations stored locally.
    ///     The server will only return groups that are updated after `lastUpdatedDate`.
    ///     If null, all group IDs will be returned.
    ///
    /// - Returns: A list of invitation IDs and the version.
    /// Note: The version can be used to update the last updated date stored locally.
    ///
    /// - Throws: ``ResponseError`` if an error occurs.
    public func queryInvitations(groupId: Int64, lastUpdatedDate: Date? = nil) -> Promise<Response<GroupInvitationsWithVersion?>> {
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
                try $0.toResponse {
                    try $0.kind?.getKindData(GroupInvitationsWithVersion.self)
                }
            }
    }

    /// Find invitations.
    ///
    /// - Parameters:
    ///   - areSentByMe: Whether the invitations are sent by me.
    ///   - lastUpdatedDate: The last updated date of invitations stored locally.
    ///     The server will only return invitations that are updated after `lastUpdatedDate`.
    ///     If null, all invitations will be returned.
    ///
    /// - Returns: A list of invitation IDs and the version.
    /// Note: The version can be used to update the last updated date stored locally.
    ///
    /// - Throws: ``ResponseError`` if an error occurs.
    public func queryInvitations(areSentByMe: Bool, lastUpdatedDate: Date? = nil) -> Promise<Response<GroupInvitationsWithVersion?>> {
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
                try $0.toResponse {
                    try $0.kind?.getKindData(GroupInvitationsWithVersion.self)
                }
            }
    }

    /// Create a group join/membership request.
    ///
    /// Authorization:
    /// * If the logged-in user has been blocked by the group,
    ///   throws ``ResponseError`` with the code ``ResponseStatusCode/blockedUserSendGroupJoinRequest``.
    /// * If the logged-in user trys to send a join request to the group
    ///   in which they are already a member,
    ///   throws ``ResponseError`` with the code ``ResponseStatusCode/groupMemberSendGroupJoinRequest``.
    /// * If the group does not allow group join requests,
    ///   throws ``ResponseError`` with the code:
    ///   ``ResponseStatusCode/sendGroupJoinRequestToGroupUsingInvitation``,
    ///   ``ResponseStatusCode/sendGroupJoinRequestToGroupUsingMembershipRequest``,
    ///   or ``ResponseStatusCode/sendGroupJoinRequestToGroupUsingQuestion``.
    ///
    /// Notifications:
    /// * If the server property `turms.service.notification.group-join-request-created.notify-requester-other-online-sessions`
    ///   is true (true by default), the server will send a group membership request notification to all other online sessions of the logged-in user actively.
    /// * If the server property `turms.service.notification.group-join-request-created.notify-group-owner-and-managers`,
    ///   is true (true by default), the server will send a group membership request notification to the group owner and managers actively.
    /// * If the server property `turms.service.notification.group-join-request-created.notify-group-members`
    ///   is true (false by default), the server will send a group membership request notification to all group members of the target group actively.
    ///
    /// - Parameters:
    ///   - groupId: The target group ID.
    ///   - content: The request content.
    ///
    /// - Returns: The request ID.
    ///
    /// - Throws: ``ResponseError`` if an error occurs.
    public func createJoinRequest(groupId: Int64, content: String) -> Promise<Response<Int64>> {
        return turmsClient.driver
            .send {
                $0.createGroupJoinRequestRequest = .with {
                    $0.groupID = groupId
                    $0.content = content
                }
            }
            .map {
                try $0.toResponse {
                    try $0.getLongOrThrow()
                }
            }
    }

    /// Delete/Recall a group join/membership request.
    ///
    /// Authorization:
    /// * If the server property `turms.service.group.join-request.allow-recall-pending-join-request-by-sender`
    ///   is true (false by default), the logged-in user can recall pending join requests sent by themselves.
    ///   Otherwise, throws ``ResponseError`` with the code ``ResponseStatusCode/recallingGroupJoinRequestIsDisabled``.
    /// * If the logged-in user is not the sender of the group join request,
    ///   throws ``ResponseError`` with the code ``ResponseStatusCode/notSenderToRecallGroupJoinRequest``.
    /// * If the group join request is not pending (e.g. expired, accepted, deleted, etc),
    ///   throws ``ResponseError`` with the code ``ResponseStatusCode/recallNonPendingGroupJoinRequest``.
    ///
    /// Notifications:
    /// * If the server property `turms.service.notification.group-join-request-recalled.notify-requester-other-online-sessions`
    ///   is true (true by default), the server will send a delete join request notification to all other online sessions of the logged-in user actively.
    /// * If the server property `turms.service.notification.group-join-request-recalled.notify-group-owner-and-managers`
    ///   is true (true by default), the server will send a delete join request notification to the group owner and managers actively.
    /// * If the server property `turms.service.notification.group-join-request-recalled.notify-group-members`,
    ///   is true (false by default), the server will send a delete join request notification to all group members of the target group actively.
    ///
    /// - Throws: ``ResponseError`` if an error occurs.
    public func deleteJoinRequest(_ requestId: Int64) -> Promise<Response<Void>> {
        return turmsClient.driver
            .send {
                $0.deleteGroupJoinRequestRequest = .with {
                    $0.requestID = requestId
                }
            }
            .map {
                try $0.toResponse()
            }
    }

    /// Reply a group join/membership request.
    ///
    /// If the logged-in user accepts/approves a join request sent by a user,
    /// the user will become a group member automatically.
    ///
    /// Authorization:
    /// 1. If the logged-in user is not the group owner or manager of the group,
    /// throws ``ResponseError`` with the code ``ResponseStatusCode/notGroupOwnerOrManagerToUpdateGroupJoinRequest``.
    /// 2. If the group join request is not pending (e.g. expired, accepted, deleted, etc),
    /// throws ``ResponseError`` with the code ``ResponseStatusCode/updateNonPendingGroupJoinRequest``.
    ///
    /// Notifications:
    /// * If the server property `turms.service.notification.group-join-request-replied.notify-requester-other-online-sessions`,
    ///   is true (true by default), the server will send a reply group join request notification to all other online sessions of the logged-in user actively.
    /// * If the server property `turms.service.notification.group-join-request-replied.notify-group-join-request-sender`,
    ///   is true (true by default), the server will send a reply group join request notification to the group join request sender actively.
    /// * If the server property `turms.service.notification.group-join-request-replied.notify-group-members`,
    ///   is true (false by default), the server will send a reply group join request notification to all group members of the target group actively.
    /// * If the server property `turms.service.notification.group-join-request-replied.notify-group-owner-and-managers`,
    ///   is true (true by default), the server will send a reply group join request notification to the group owner and managers actively.
    ///
    /// - Parameters:
    ///   - requestId: The target group join request ID.
    ///   - responseAction: The response action.
    ///   - reason: The reason of the response.
    ///
    /// - Throws: ``ResponseError`` if an error occurs.
    public func replyJoinRequest(requestId: Int64, responseAction: ResponseAction, reason: String? = nil) -> Promise<Response<Void>> {
        return turmsClient.driver
            .send {
                $0.updateGroupJoinRequestRequest = .with {
                    $0.requestID = requestId
                    $0.responseAction = responseAction
                    if let v = reason {
                        $0.reason = v
                    }
                }
            }
            .map {
                try $0.toResponse()
            }
    }

    /// Find group join/membership requests.
    ///
    /// - Parameters:
    ///   - groupId: The target group ID.
    ///   - lastUpdatedDate: The last updated date of requests stored locally.
    ///     The server will only return requests that are updated after `lastUpdatedDate`.
    ///     If null, all requests will be returned.
    ///
    /// - Returns: A list of request IDs and the version.
    /// Note: The version can be used to update the last updated date stored locally.
    ///
    /// - Throws: ``ResponseError`` if an error occurs.
    public func queryJoinRequests(groupId: Int64, lastUpdatedDate: Date? = nil) -> Promise<Response<GroupJoinRequestsWithVersion?>> {
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
                try $0.toResponse {
                    try $0.kind?.getKindData(GroupJoinRequestsWithVersion.self)
                }
            }
    }

    /// Find group join/membership requests sent by the logged-in user.
    ///
    /// - Parameters:
    ///   - lastUpdatedDate: The last updated date of requests stored locally.
    ///     The server will only return requests that are updated after `lastUpdatedDate`.
    ///     If null, all requests will be returned.
    ///
    /// - Returns: A list of request IDs and the version.
    /// Note: The version can be used to update the last updated date stored locally.
    ///
    /// - Throws: ``ResponseError`` if an error occurs.
    public func querySentJoinRequests(lastUpdatedDate: Date? = nil) -> Promise<Response<GroupJoinRequestsWithVersion?>> {
        return turmsClient.driver
            .send {
                $0.queryGroupJoinRequestsRequest = .with {
                    if let v = lastUpdatedDate {
                        $0.lastUpdatedDate = v.toMillis()
                    }
                }
            }
            .map {
                try $0.toResponse {
                    try $0.kind?.getKindData(GroupJoinRequestsWithVersion.self)
                }
            }
    }

    /// Find group join/membership questions.
    ///
    /// Authorization:
    /// * Only the owner and managers have the right to fetch questions with answers
    ///
    /// - Parameters:
    ///   - groupId: The target group ID.
    ///   - withAnswers: Whether to return the answers.
    ///   - lastUpdatedDate: The last updated date of questions stored locally.
    ///     The server will only return questions that are updated after `lastUpdatedDate`.
    ///     If null, all questions will be returned.
    ///
    /// - Returns: A list of question IDs and the version.
    /// Note: The version can be used to update the last updated date stored locally.
    ///
    /// - Throws: ``ResponseError`` if an error occurs.
    public func queryGroupJoinQuestions(
        groupId: Int64,
        withAnswers: Bool = false,
        lastUpdatedDate: Date? = nil
    ) -> Promise<Response<GroupJoinQuestionsWithVersion?>> {
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
                try $0.toResponse {
                    try $0.kind?.getKindData(GroupJoinQuestionsWithVersion.self)
                }
            }
    }

    /// Answer group join/membership questions, and join the group automatically
    /// if the logged-in user has answered some questions correctly
    /// and acquire the minimum score to join.
    ///
    /// - Parameters:
    ///   - questionIdToAnswer: The map of question ID to answer.
    ///
    /// - Returns: The group membership questions answer result.
    ///
    /// - Throws: ``ResponseError`` if an error occurs.
    public func answerGroupQuestions(_ questionIdToAnswer: [Int64: String]) -> Promise<Response<GroupJoinQuestionsAnswerResult>> {
        if questionIdToAnswer.isEmpty {
            return Promise.value(Response.value(GroupJoinQuestionsAnswerResult()))
        }
        return turmsClient.driver
            .send {
                $0.checkGroupJoinQuestionsAnswersRequest = .with {
                    $0.questionIDToAnswer = questionIdToAnswer
                }
            }
            .map {
                try $0.toResponse {
                    let result = try $0.kind?.getKindData(GroupJoinQuestionsAnswerResult.self)
                    if let value = result {
                        return value
                    } else {
                        throw ResponseError(code: ResponseStatusCode.invalidResponse)
                    }
                }
            }
    }

    // Group Member

    /// Add group members.
    ///
    /// Authorization:
    /// * If the group is inactive,
    ///   throws ``ResponseError`` with the code ``ResponseStatusCode/addUserToInactiveGroup``.
    /// * If the group has reached the maximum number of group members,
    ///   throws ``ResponseError`` with the code ``ResponseStatusCode/addUserToGroupWithSizeLimitReached``.
    /// * If the group doesn't allow add users as group members directly,
    ///   throws ``ResponseError`` with the code ``ResponseStatusCode/addUserToGroupRequiringUsersApproval``.
    /// * When the logged-in user tries to add themselves as a group member,
    ///   they will become a group member if the group uses member requests as the join strategy.
    ///   Otherwise, throws the following codes according to different join strategies:
    ///   ``ResponseStatusCode/userJoinGroupWithoutAcceptingGroupInvitation``,
    ///   ``ResponseStatusCode/userJoinGroupWithoutAnsweringGroupQuestion``,
    ///   ``ResponseStatusCode/userJoinGroupWithoutSendingGroupJoinRequest``.
    /// * If the logged-in user has no permission to add new group members,
    ///   throws ``ResponseError`` with one of the following codes:
    ///   ``ResponseStatusCode/notGroupOwnerToAddGroupMember``,
    ///   ``ResponseStatusCode/notGroupOwnerOrManagerToAddGroupMember``,
    ///   ``ResponseStatusCode/notGroupMemberToAddGroupMember``,
    ///   ``ResponseStatusCode/notGroupOwnerToAddGroupManager``.
    /// * If `userIds` contains a blocked user ID,
    ///   throws ``ResponseError`` with the code ``ResponseStatusCode/addBlockedUserToGroup``.
    ///
    /// Notifications:
    /// * If the server property `turms.service.notification.group-member-added.notify-requester-other-online-sessions`
    ///   is true (true by default), the server will send a add group member notification to all other online sessions of the logged-in user actively.
    /// * If the server property `turms.service.notification.group-member-added.notify-added-group-member`,
    ///   is true (true by default), the server will send a add group member notification to all other online sessions of the added group member.
    /// * If the server property `turms.service.notification.group-member-added.notify-other-group-members`,
    ///   is true (true by default), the server will send a add group member notification to all other online sessions of the other group members.
    ///
    /// - Parameters:
    ///   - groupId: The target group ID.
    ///   - userIds: The target user IDs.
    ///   - name: The name of the group member.
    ///   - role: The role of the group member.
    ///   - muteEndDate: The mute end date of the group member.
    ///
    /// - Throws: ``ResponseError`` if an error occurs.
    public func addGroupMembers(
        groupId: Int64,
        userIds: [Int64],
        name: String? = nil,
        role: GroupMemberRole? = nil,
        muteEndDate: Date? = nil
    ) -> Promise<Response<Void>> {
        if userIds.isEmpty {
            return Promise.value(Response.empty())
        }
        return turmsClient.driver
            .send {
                $0.createGroupMembersRequest = .with {
                    $0.groupID = groupId
                    $0.userIds = userIds
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
            .map {
                try $0.toResponse()
            }
    }

    /// Join a group.
    ///
    /// Authorization:
    /// * When the logged-in user tries to add themselves as a group member,
    ///   they will become a group member if the group uses member requests as the join strategy.
    ///   Otherwise, throws the following codes according to different join strategies:
    ///   ``ResponseStatusCode/userJoinGroupWithoutAcceptingGroupInvitation``,
    ///   ``ResponseStatusCode/userJoinGroupWithoutAnsweringGroupQuestion``,
    ///   ``ResponseStatusCode/userJoinGroupWithoutSendingGroupJoinRequest``.
    ///
    /// Notifications:
    /// * If the server property `turms.service.notification.group-member-added.notify-requester-other-online-sessions`
    ///   is true (true by default), the server will send a add group member notification to all other online sessions of the logged-in user actively.
    /// * If the server property `turms.service.notification.group-member-added.notify-added-group-member`,
    ///   is true (true by default), the server will send a add group member notification to all other online sessions of the added group member.
    /// * If the server property `turms.service.notification.group-member-added.notify-other-group-members`,
    ///   is true (true by default), the server will send a add group member notification to all other online sessions of the other group members.
    ///
    /// - Parameters:
    ///   - groupId: The target group ID.
    ///   - name: The name as the group member.
    ///
    /// - Throws: ``ResponseError`` if an error occurs.
    public func joinGroup(groupId: Int64, name: String? = nil) -> Promise<Response<Void>> {
        guard let info = turmsClient.userService.userInfo else {
            return Promise(error: ResponseError(code: .clientSessionHasBeenClosed))
        }
        return addGroupMembers(groupId: groupId, userIds: [info.userId], name: name)
    }

    /// Quit a group.
    ///
    /// Notifications:
    /// * If the server property `turms.service.notification.group-member-removed.notify-requester-other-online-sessions`,
    ///   is true (true by default), the server will send a delete group member notification to all other online sessions of the logged-in user actively.
    /// * If the server property `turms.service.notification.group-member-removed.notify-other-group-members`,
    ///   is true (true by default), the server will send a delete group member notification to all other group members of the group actively.
    ///
    /// - Parameters:
    ///   - groupId: The target group ID.
    ///   - successorId: The new successor ID.
    ///     If the logged-in user is the owner of the group, they must transfer the group ownership to the `successorId`,
    ///     throws ``ResponseError`` with the code ``ResponseStatusCode/groupOwnerQuitWithoutSpecifyingSuccessor`` otherwise.
    ///     And the successor will become the group owner.
    ///     The successor must already be a member of the group, throws ``ResponseError`` with the code
    ///     ``ResponseStatusCode/groupSuccessorNotGroupMember`` otherwise.
    ///   - quitAfterTransfer: Whether to quit the group after transfer the group ownership.
    ///     If false, the logged-in user will become a normal group member (not the group admin).
    ///     If null, the value will not be changed.
    ///
    ///     Authorization:
    ///     * If the logged-in user is not the owner of the group,
    ///       ``ResponseError`` with the code ``ResponseStatusCode/notGroupOwnerToTransferGroup`` will be thrown.
    ///
    /// - Throws: ``ResponseError`` if an error occurs.
    public func quitGroup(groupId: Int64, successorId: Int64? = nil, quitAfterTransfer: Bool? = nil) -> Promise<Response<Void>> {
        return turmsClient.driver
            .send {
                $0.deleteGroupMembersRequest = .with {
                    $0.groupID = groupId
                    $0.memberIds = [turmsClient.userService.userInfo!.userId]
                    if let v = successorId {
                        $0.successorID = v
                    }
                    if let v = quitAfterTransfer {
                        $0.quitAfterTransfer = v
                    }
                }
            }
            .map {
                try $0.toResponse()
            }
    }

    /// Remove group members.
    ///
    /// Authorization:
    /// * If the logged-in user is not the group owner or manager of the group,
    ///   throws ``ResponseError`` with the code ``ResponseStatusCode/notGroupOwnerOrManagerToRemoveGroupMember``.
    ///
    /// Notifications:
    /// * If the server property `turms.service.notification.group-member-removed.notify-requester-other-online-sessions`,
    ///   is true (true by default), the server will send a delete group member notification to all other online sessions of the logged-in user actively.
    /// * If the server property `turms.service.notification.group-member-removed.notify-removed-group-member`,
    ///   is true (true by default), the server will send a delete group member notification to the removed group member actively.
    /// * If the server property `turms.service.notification.group-member-removed.notify-other-group-members`,
    ///   is true (true by default), the server will send a delete group member notification to all other group members of the group actively.
    ///
    /// - Parameters:
    ///   - groupId: The target group ID.
    ///   - memberIds: The target member IDs.
    ///
    /// - Throws: ``ResponseError`` if an error occurs.
    public func removeGroupMembers(groupId: Int64, memberIds: [Int64]) -> Promise<Response<Void>> {
        if memberIds.isEmpty {
            return Promise.value(Response.empty())
        }
        return turmsClient.driver
            .send {
                $0.deleteGroupMembersRequest = .with {
                    $0.groupID = groupId
                    $0.memberIds = memberIds
                }
            }
            .map {
                try $0.toResponse()
            }
    }

    /// Remove a group member.
    ///
    /// Authorization:
    /// * If the logged-in user is not the group owner or manager of the group,
    ///   throws ``ResponseError`` with the code ``ResponseStatusCode/notGroupOwnerOrManagerToRemoveGroupMember``.
    ///
    /// Notifications:
    /// * If the server property `turms.service.notification.group-member-removed.notify-requester-other-online-sessions`,
    ///   is true (true by default), the server will send a delete group member notification to all other online sessions of the logged-in user actively.
    /// * If the server property `turms.service.notification.group-member-removed.notify-removed-group-member`,
    ///   is true (true by default), the server will send a delete group member notification to the removed group member actively.
    /// * If the server property `turms.service.notification.group-member-removed.notify-other-group-members`,
    ///   is true (true by default), the server will send a delete group member notification to all other group members of the group actively.
    ///
    /// - Parameters:
    ///   - groupId: The target group ID.
    ///   - memberId: The target member ID.
    ///
    /// - Throws: ``ResponseError`` if an error occurs.
    public func removeGroupMember(groupId: Int64, memberId: Int64) -> Promise<Response<Void>> {
        return removeGroupMembers(groupId: groupId, memberIds: [memberId])
    }

    /// Update group member info.
    ///
    /// Authorization:
    /// * If the logged-in user is not the group owner or manager of the group,
    ///   throws ``ResponseError`` with the code ``ResponseStatusCode/notGroupOwnerOrManagerToUpdateGroupInfo``.
    ///
    /// Notifications:
    /// * If the server property `turms.service.notification.group-member-info-updated.notify-requester-other-online-sessions`,
    ///   is true (true by default), the server will send a update group member info notification to all other online sessions of the logged-in user actively.
    /// * If the server property `turms.service.notification.group-member-info-updated.notify-updated-group-member`,
    ///   is true (false by default), the server will send a update group member info notification to the updated group member actively.
    /// * If the server property `turms.service.notification.group-member-info-updated.notify-other-group-members`,
    ///   is true (false by default), the server will send a update group member info notification to all other group members of the group actively.
    ///
    /// - Parameters:
    ///   - groupId: The target group ID.
    ///   - memberId: The target member ID.
    ///   - name: The new name of the group member.
    ///     If null, the name will not be updated.
    ///   - role: The new role of the group member.
    ///     If null, the role will not be updated.
    ///   - muteEndDate: The new mute end date of the group member.
    ///     If null, the mute end date will not be updated.
    ///
    ///     Authorization:
    ///     * If the logged-in user is not the group owner or manager of the group,
    ///       throws ``ResponseError`` with the code ``ResponseStatusCode/notGroupOwnerOrManagerToMuteGroupMember``
    ///     * If the logged-in user is not the group owner,
    ///       throws ``ResponseError`` with the code ``ResponseStatusCode/muteGroupMemberWithRoleEqualToOrHigherThanRequester``.
    ///
    /// - Throws: ``ResponseError`` if an error occurs.
    public func updateGroupMemberInfo(
        groupId: Int64,
        memberId: Int64,
        name: String? = nil,
        role: GroupMemberRole? = nil,
        muteEndDate: Date? = nil
    ) -> Promise<Response<Void>> {
        if Validator.areAllNil(name, role, muteEndDate) {
            return Promise.value(Response.empty())
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
            .map {
                try $0.toResponse()
            }
    }

    /// Mute group member.
    ///
    /// Authorization:
    /// * If the logged-in user is not the group owner or manager of the group,
    ///   throws ``ResponseError`` with the code ``ResponseStatusCode/notGroupOwnerOrManagerToMuteGroupMember``
    /// * If the logged-in user is not the group owner,
    ///   throws ``ResponseError`` with the code ``ResponseStatusCode/muteGroupMemberWithRoleEqualToOrHigherThanRequester``.
    ///
    /// Notifications:
    /// * If the server property `turms.service.notification.group-member-info-updated.notify-requester-other-online-sessions`,
    ///   is true (true by default), the server will send a update group member info notification to all other online sessions of the logged-in user actively.
    /// * If the server property `turms.service.notification.group-member-info-updated.notify-updated-group-member`,
    ///   is true (false by default), the server will send a update group member info notification to the updated group member actively.
    /// * If the server property `turms.service.notification.group-member-info-updated.notify-other-group-members`,
    ///   is true (false by default), the server will send a update group member info notification to all other group members of the group actively.
    ///
    /// - Parameters:
    ///   - groupId: The target group ID.
    ///   - memberId: The target member ID.
    ///   - muteEndDate: The new mute end date of the group member.
    ///
    /// - Throws: ``ResponseError`` if an error occurs.
    public func muteGroupMember(groupId: Int64, memberId: Int64, muteEndDate: Date) -> Promise<Response<Void>> {
        return updateGroupMemberInfo(
            groupId: groupId,
            memberId: memberId,
            muteEndDate: muteEndDate
        )
    }

    /// Unmute group member.
    ///
    /// Authorization:
    /// * If the logged-in user is not the group owner or manager of the group,
    ///   throws ``ResponseError`` with the code ``ResponseStatusCode/notGroupOwnerOrManagerToMuteGroupMember``
    /// * If the logged-in user is not the group owner,
    ///   throws ``ResponseError`` with the code ``ResponseStatusCode/muteGroupMemberWithRoleEqualToOrHigherThanRequester``.
    ///
    /// Notifications:
    /// * If the server property `turms.service.notification.group-member-info-updated.notify-requester-other-online-sessions`,
    ///   is true (true by default), the server will send a update group member info notification to all other online sessions of the logged-in user actively.
    /// * If the server property `turms.service.notification.group-member-info-updated.notify-updated-group-member`,
    ///   is true (false by default), the server will send a update group member info notification to the updated group member actively.
    /// * If the server property `turms.service.notification.group-member-info-updated.notify-other-group-members`,
    ///   is true (false by default), the server will send a update group member info notification to all other group members of the group actively.
    ///
    /// - Parameters:
    ///   - groupId: The target group ID.
    ///   - memberId: The target member ID.
    ///
    /// - Throws: ``ResponseError`` if an error occurs.
    public func unmuteGroupMember(groupId: Int64, memberId: Int64) -> Promise<Response<Void>> {
        return muteGroupMember(
            groupId: groupId,
            memberId: memberId,
            muteEndDate: Date(timeIntervalSince1970: 0)
        )
    }

    /// Find group members.
    ///
    /// - Parameters:
    ///   - groupId: The target group ID.
    ///   - withStatus: Whether to return the session status of the group members.
    ///   - lastUpdatedDate: The last updated date of the group members stored locally.
    ///     The server will only return group members that are updated after `lastUpdatedDate`.
    ///     If null, all group members will be returned.
    ///
    /// - Returns: Group members and the version.
    /// Note: The version can be used to update the last updated date stored locally.
    ///
    /// - Throws: ``ResponseError`` if an error occurs.
    public func queryGroupMembers(groupId: Int64, withStatus: Bool = false, lastUpdatedDate: Date? = nil) -> Promise<Response<GroupMembersWithVersion?>> {
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
                try $0.toResponse {
                    try $0.kind?.getKindData(GroupMembersWithVersion.self)
                }
            }
    }

    /// Find group members.
    ///
    /// - Parameters:
    ///   - groupId: The target group ID.
    ///   - memberIds: The target member IDs.
    ///   - withStatus: Whether to return the session status of the group members.
    ///
    /// - Returns: Group members and the version.
    /// Note: The version can be used to update the last updated date stored locally.
    ///
    /// - Throws: ``ResponseError`` if an error occurs.
    public func queryGroupMembersByMemberIds(groupId: Int64, memberIds: [Int64], withStatus: Bool = false) -> Promise<Response<GroupMembersWithVersion?>> {
        return turmsClient.driver
            .send {
                $0.queryGroupMembersRequest = .with {
                    $0.groupID = groupId
                    $0.memberIds = memberIds
                    $0.withStatus = withStatus
                }
            }
            .map {
                try $0.toResponse {
                    try $0.kind?.getKindData(GroupMembersWithVersion.self)
                }
            }
    }
}