import 'package:fixnum/fixnum.dart' show Int64;

import '../../turms_client.dart';
import '../extension/notification_extensions.dart';
import '../model/proto/request/group/enrollment/update_group_invitation_request.pb.dart';
import '../model/proto/request/group/enrollment/update_group_join_request_request.pb.dart';

class GroupService {
  final TurmsClient _turmsClient;

  GroupService(this._turmsClient);

  /// Create a new group.
  /// The logged-in user will become the group creator and owner.
  ///
  /// Common Scenarios:
  /// * To add new group members, you can use methods like [addGroupMembers].
  ///
  /// Authorization:
  /// * If the groups owned by the logged-in user has exceeded the limit,
  ///   throws [ResponseException] with the code [ResponseStatusCode.maxOwnedGroupsReached].
  ///
  /// Notifications:
  /// * If the server property `turms.service.notification.group-created.notify-requester-other-online-sessions`
  ///   is true (true by default), the server will send a create group notification to all other online sessions of the logged-in user actively.
  ///
  /// **Params**:
  /// * `name`: The group name.
  /// * `intro`: The group introduction.
  /// * `announcement`: The group announcement.
  /// * `minScore`: The group minimum score that a non-member user needs to acquire
  /// to join the group when answering group questions.
  /// * `typeId`: The group type ID.
  /// If null, the default group type configured in turms-service will be used.
  ///
  /// Authorization:
  /// * If the group type ID does not exist,
  ///   throws [ResponseException] with the code [ResponseStatusCode.createGroupWithNonexistentGroupType].
  /// * If the logged-in user does not have the permission to create the group with [typeId],
  ///   throws [ResponseException] with the code [ResponseStatusCode.noPermissionToCreateGroupWithGroupType].
  ///
  /// **Returns**: The group ID.
  ///
  /// **Throws**: [ResponseException] if an error occurs.
  Future<Response<Int64>> createGroup(String name,
      {String? intro,
      String? announcement,
      int? minScore,
      DateTime? muteEndDate,
      Int64? typeId}) async {
    final n = await _turmsClient.driver.send(CreateGroupRequest(
        name: name,
        intro: intro,
        announcement: announcement,
        minScore: minScore,
        muteEndDate: muteEndDate?.toInt64(),
        typeId: typeId));
    return n.toResponse((data) => data.getLongOrThrow());
  }

  /// Delete the target group.
  ///
  /// Authorization:
  /// * If the logged-in user is not the group owner, or the target group does not exist,
  ///   throws [ResponseException] with the code [ResponseStatusCode.notGroupOwnerToDeleteGroup].
  ///
  /// Notifications:
  /// * If the server property `turms.service.notification.group-deleted.notify-requester-other-online-sessions`
  ///   is true (true by default),
  ///   the server will send a delete group notification to all other online sessions of the logged-in user actively.
  /// * If the server property `turms.service.notification.group-deleted.notify-group-members`
  ///   is true (true by default),
  ///   the server will send a delete group notification to all group members of the target group.
  ///
  /// **Throws**: [ResponseException] if an error occurs.
  Future<Response<void>> deleteGroup(Int64 groupId) async {
    final n =
        await _turmsClient.driver.send(DeleteGroupRequest(groupId: groupId));
    return n.toNullResponse();
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
  /// **Params**:
  /// * `groupId`: The target group ID to find the group for updating.
  /// * `name`: The new group name.
  /// If null, the group name will not be changed.
  ///
  /// Authorization:
  /// * Whether the logged-in user can change the group name depends on the group type.
  ///   If not null and the logged-in user does NOT have the permission to change the group name,
  ///   throws [ResponseException] with the code [ResponseStatusCode.notGroupMemberToUpdateGroupInfo]
  ///   or [ResponseStatusCode.notGroupOwnerOrManagerToUpdateGroupInfo]
  ///   or [ResponseStatusCode.notGroupOwnerToUpdateGroupInfo].
  /// * `intro`: The new group introduction.
  /// If null, the group introduction will not be changed.
  ///
  /// Authorization:
  /// * Whether the logged-in user can change the group introduction depends on the group type.
  ///   If not null and the logged-in user does NOT have the permission to change the group introduction,
  ///   throws [ResponseException] with the code [ResponseStatusCode.notGroupMemberToUpdateGroupInfo]
  ///   or [ResponseStatusCode.notGroupOwnerOrManagerToUpdateGroupInfo]
  ///   or [ResponseStatusCode.notGroupOwnerToUpdateGroupInfo].
  /// * `announcement`: The new group announcement.
  /// If null, the group announcement will not be changed.
  ///
  /// Authorization:
  /// * Whether the logged-in user can change the group announcement depends on the group type.
  ///   If not null and the logged-in user does NOT have the permission to change the group announcement,
  ///   throws [ResponseException] with the code [ResponseStatusCode.notGroupMemberToUpdateGroupInfo]
  ///   or [ResponseStatusCode.notGroupOwnerOrManagerToUpdateGroupInfo]
  ///   or [ResponseStatusCode.notGroupOwnerToUpdateGroupInfo].
  /// * `minScore`: The new group minimum score that a non-member user needs to acquire
  /// to join the group when answering group questions.
  /// If null, the group minimum score will not be changed.
  ///
  /// Authorization:
  /// * Whether the logged-in user can change the group minimum score depends on the group type.
  ///   If not null and the logged-in user does NOT have the permission to change the group minimum score,
  ///   throws [ResponseException] with the code [ResponseStatusCode.notGroupMemberToUpdateGroupInfo]
  ///   or [ResponseStatusCode.notGroupOwnerOrManagerToUpdateGroupInfo]
  ///   or [ResponseStatusCode.notGroupOwnerToUpdateGroupInfo].
  /// * `typeId`: The new group type ID.
  /// If null, the group type ID will not be changed.
  ///
  /// Authorization:
  /// * If the server property `turms.service.group.allow-group-owner-change-group-type`
  ///   is true (false by default), the logged-in user can change the group type.
  ///   Otherwise, throws [ResponseException] with the code [ResponseStatusCode.updatingGroupTypeIsDisabled].
  /// * If the logged-in user is not the group owner,
  ///   throws [ResponseException] with the code [ResponseStatusCode.notGroupOwnerToUpdateGroupType].
  /// * If the logged-in user is not allowed to use the group type,
  ///   throws [ResponseException] with the code [ResponseStatusCode.noPermissionToUpdateGroupToGroupType].
  /// * If [typeId] doesn't exist, throws [ResponseException] with the code [ResponseStatusCode.updateGroupToNonexistentGroupType].
  /// * `muteEndDate`: The new group mute end date.
  /// Before the group mute end date, the group members will not be able
  /// to send messages.
  ///
  /// Authorization:
  /// * Only the group owner or group managers can mute or unmute the group.
  ///   If the logged-in user is not the owner or manager of the group,
  ///   [ResponseException] with the code [ResponseStatusCode.notGroupOwnerOrManagerToMuteGroupMember]
  ///   will be thrown.
  /// * `successorId`: The new successor ID.
  /// If the logged-in user is the owner of the group, they must transfer the group ownership to the [successorId],
  /// throws [ResponseException] with the code [ResponseStatusCode.groupOwnerQuitWithoutSpecifyingSuccessor] otherwise.
  /// And the successor will become the group owner.
  /// The successor must already be a member of the group, throws [ResponseException] with the code
  /// [ResponseStatusCode.groupSuccessorNotGroupMember] otherwise.
  /// * `quitAfterTransfer`: Whether to quit the group after transfer the group ownership.
  /// If false, the logged-in user will become a normal group member (not the group admin).
  /// If null, the value will not be changed.
  ///
  /// Authorization:
  /// * If the logged-in user is not the owner of the group,
  ///   throws [ResponseException] with the code [ResponseStatusCode.notGroupOwnerToTransferGroup].
  ///
  /// **Throws**: [ResponseException] if an error occurs.
  Future<Response<void>> updateGroup(Int64 groupId,
      {String? name,
      String? intro,
      String? announcement,
      int? minScore,
      Int64? typeId,
      DateTime? muteEndDate,
      Int64? successorId,
      bool? quitAfterTransfer}) async {
    if ([name, intro, announcement, minScore, typeId, muteEndDate, successorId]
        .areAllNull) {
      return Response.nullValue();
    }
    final n = await _turmsClient.driver.send(UpdateGroupRequest(
        groupId: groupId,
        name: name,
        intro: intro,
        announcement: announcement,
        muteEndDate: muteEndDate?.toInt64(),
        minScore: minScore,
        typeId: typeId,
        successorId: successorId,
        quitAfterTransfer: quitAfterTransfer));
    return n.toNullResponse();
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
  /// **Params**:
  /// * `groupId`: The target group ID to find the group for updating.
  /// * `successorId`: The new successor ID.
  /// If the logged-in user is the owner of the group, they must transfer the group ownership to the [successorId],
  /// throws [ResponseException] with the code [ResponseStatusCode.groupOwnerQuitWithoutSpecifyingSuccessor] otherwise.
  /// And the successor will become the group owner.
  /// The successor must already be a member of the group, throws [ResponseException] with the code
  /// [ResponseStatusCode.groupSuccessorNotGroupMember] otherwise.
  /// * `quitAfterTransfer`: Whether to quit the group after transfer the group ownership.
  /// If false, the logged-in user will become a normal group member (not the group admin).
  /// If null, the value will not be changed.
  /// Authorization: If the logged-in user is not the owner of the group,
  /// throws [ResponseException] with the code [ResponseStatusCode.notGroupOwnerToTransferGroup].
  ///
  /// **Throws**: [ResponseException] if an error occurs.
  Future<Response<void>> transferOwnership(Int64 groupId, Int64 successorId,
          {bool quitAfterTransfer = false}) =>
      updateGroup(groupId,
          successorId: successorId, quitAfterTransfer: quitAfterTransfer);

  /// Mute the target group.
  ///
  /// Notifications:
  /// * If the server property `turms.service.notification.group-updated.notify-requester-other-online-sessions`
  ///   the server will send a update group notification to all other online sessions of the logged-in user actively.
  /// * If the server property `turms.service.notification.group-updated.notify-group-members`
  ///   is true (true by default),
  ///   the server will send a update group notification to all group members of the target group actively.
  ///
  /// **Params**:
  /// * `groupId`: The target group ID to find the group for updating.
  /// * `muteEndDate`: The new group mute end date.
  /// Before the group mute end date, the group members will not be able
  /// to send messages.
  ///
  /// Authorization:
  /// * Only the group owner or group managers can mute or unmute the group.
  ///   If the logged-in user is not the owner or manager of the group,
  ///   throws [ResponseException] with the code [ResponseStatusCode.notGroupOwnerOrManagerToMuteGroupMember].
  ///
  /// **Throws**: [ResponseException] if an error occurs.
  Future<Response<void>> muteGroup(Int64 groupId, DateTime muteEndDate) =>
      updateGroup(groupId, muteEndDate: muteEndDate);

  /// Unmute the target group.
  ///
  /// Authorization:
  /// * Only the group owner or group managers can mute or unmute the group.
  ///   If the logged-in user is not the owner or manager of the group,
  ///   throws [ResponseException] with the code [ResponseStatusCode.notGroupOwnerOrManagerToMuteGroupMember].
  ///
  /// Notifications:
  /// * If the server property `turms.service.notification.group-updated.notify-requester-other-online-sessions`
  ///   the server will send a update group notification to all other online sessions of the logged-in user actively.
  /// * If the server property `turms.service.notification.group-updated.notify-group-members`
  ///   is true (true by default),
  ///   the server will send a update group notification to all group members of the target group actively.
  ///
  /// **Params**:
  /// * `groupId`: The target group ID to find the group for updating.
  ///
  /// **Throws**: [ResponseException] if an error occurs.
  Future<Response<void>> unmuteGroup(Int64 groupId) =>
      muteGroup(groupId, DateTime(0));

  /// Find groups.
  ///
  /// **Params**:
  /// * `groupIds`: The target group IDs for finding groups.
  /// * `lastUpdatedDate`: The last updated date of groups on local.
  /// The server will only return groups that are updated after [lastUpdatedDate].
  /// If null, all groups will be returned.
  ///
  /// **Returns**: A list of groups.
  ///
  /// **Throws**: [ResponseException] if an error occurs.
  Future<Response<List<Group>>> queryGroups(Set<Int64> groupIds,
      {DateTime? lastUpdatedDate}) async {
    if (groupIds.isEmpty) {
      return Future.value(Response.emptyList());
    }
    final n = await _turmsClient.driver.send(QueryGroupsRequest(
        groupIds: groupIds, lastUpdatedDate: lastUpdatedDate?.toInt64()));
    return n.toResponse((data) => data.groupsWithVersion.groups);
  }

  /// Find group IDs that the logged-in user has joined.
  ///
  /// **Params**:
  /// * `lastUpdatedDate`: The last updated date of group IDs that the logged-in user has joined stored locally.
  /// The server will only return group IDs that are updated after [lastUpdatedDate].
  /// If null, all group IDs will be returned.
  ///
  /// **Returns**: A list of group IDs and the version.
  /// Note: The version can be used to update the last updated date on local.
  ///
  /// **Throws**: [ResponseException] if an error occurs.
  Future<Response<LongsWithVersion?>> queryJoinedGroupIds(
      {DateTime? lastUpdatedDate}) async {
    final n = await _turmsClient.driver.send(QueryJoinedGroupIdsRequest(
        lastUpdatedDate: lastUpdatedDate == null
            ? null
            : Int64(lastUpdatedDate.millisecondsSinceEpoch)));
    return n.toResponse(
        (data) => data.hasLongsWithVersion() ? data.longsWithVersion : null);
  }

  /// Find groups that the logged-in user has joined.
  ///
  /// **Params**:
  /// * `lastUpdatedDate`: The last updated date of groups that the logged-in user has joined stored locally.
  /// The server will only return groups that are updated after [lastUpdatedDate].
  /// If null, all groups will be returned.
  ///
  /// **Throws**: [ResponseException] if an error occurs.
  Future<Response<GroupsWithVersion?>> queryJoinedGroupInfos(
      {DateTime? lastUpdatedDate}) async {
    final n = await _turmsClient.driver.send(QueryJoinedGroupInfosRequest(
        lastUpdatedDate: lastUpdatedDate?.toInt64()));
    return n.toResponse(
        (data) => data.hasGroupsWithVersion() ? data.groupsWithVersion : null);
  }

  /// Add group join/membership questions.
  ///
  /// Authorization:
  /// * Only the group owner or group managers can add group membership questions.
  ///   Otherwise, throws [ResponseException] with the code [ResponseStatusCode.notGroupOwnerOrManagerToCreateGroupQuestion].
  /// * Only the group that use `question` as the join strategy can add group membership questions.
  ///   Otherwise, throws [ResponseException] with the code
  ///   [ResponseStatusCode.createGroupQuestionForGroupUsingJoinRequest]
  ///   or [ResponseStatusCode.createGroupQuestionForGroupUsingInvitation]
  ///   or [ResponseStatusCode.createGroupQuestionForGroupUsingMembershipRequest].
  /// * If the group has been deleted,
  ///   throws [ResponseException] with the code [ResponseStatusCode.createGroupQuestionForInactiveGroup].
  ///
  /// **Params**:
  /// * `groupId`: The target group ID.
  /// * `questions`: The group membership questions.
  ///
  /// **Returns**: New group questions IDs.
  ///
  /// **Throws**: [ResponseException] if an error occurs.
  Future<Response<List<Int64>>> addGroupJoinQuestions(
      Int64 groupId, List<NewGroupJoinQuestion> questions) async {
    if (questions.isEmpty) {
      return Future.value(Response.emptyList());
    }
    final newQuestions = questions.map((question) {
      if (question.answers.isEmpty) {
        throw ResponseException(
            code: ResponseStatusCode.illegalArgument,
            reason: 'The answers of group must not be empty');
      }
      return GroupJoinQuestion(
          question: question.question,
          answers: question.answers,
          score: question.score);
    });
    final n = await _turmsClient.driver.send(CreateGroupJoinQuestionsRequest(
        groupId: groupId, questions: newQuestions));
    return n.toResponse((data) => data.longsWithVersion.longs);
  }

  /// Delete group join/membership questions.
  ///
  /// Authorization:
  /// * Only the group owner or group managers can delete group membership questions.
  ///   Otherwise, throws [ResponseException] with the code [ResponseStatusCode.notGroupOwnerOrManagerToDeleteGroupQuestion].
  ///
  /// **Params**:
  /// * `groupId`: The target group ID.
  /// * `questionIds`: The group membership question IDs.
  ///
  /// **Throws**: [ResponseException] if an error occurs.
  Future<Response<void>> deleteGroupJoinQuestions(
      Int64 groupId, Set<Int64> questionIds) async {
    final n = await _turmsClient.driver.send(DeleteGroupJoinQuestionsRequest(
        groupId: groupId, questionIds: questionIds));
    return n.toNullResponse();
  }

  /// Update group join/membership questions.
  ///
  /// Authorization:
  /// * Only the group owner or group managers can update group membership questions.
  ///   Otherwise, throws [ResponseException] with the code [ResponseStatusCode.notGroupOwnerOrManagerToUpdateGroupQuestion].
  ///
  /// **Params**:
  /// * `questionId`: The target question ID.
  /// * `question`: The question.
  /// If null, the question will not be updated.
  /// * `answers`: The answers.
  /// If null, the answers will not be updated.
  /// * `score`: The score.
  /// If null, the score will not be updated.
  ///
  /// **Throws**: [ResponseException] if an error occurs.
  Future<Response<void>> updateGroupJoinQuestion(Int64 questionId,
      {String? question, Set<String>? answers, int? score}) async {
    if ([question, answers, score].areAllNull) {
      return Response.nullValue();
    }
    final n = await _turmsClient.driver.send(UpdateGroupJoinQuestionRequest(
        questionId: questionId,
        question: question,
        answers: answers,
        score: score));
    return n.toNullResponse();
  }

  // Group Blocklist

  /// Block a user in the group.
  /// If the logged-in user is a group member, the server will delete the group member automatically.
  ///
  /// Authorization:
  /// * Only the group owner or group managers can block users.
  ///   Otherwise, throws [ResponseException] with the code [ResponseStatusCode.notGroupOwnerOrManagerToAddBlockedUser].
  /// * If the logged-in user trys to block themselves,
  ///   throws [ResponseException] with the code [ResponseStatusCode.cannotBlockOneself].
  ///
  /// Notifications:
  /// * If the server property `turms.service.notification.group-blocked-user-added.notify-requester-other-online-sessions`
  ///   is true (true by default), the server will send a block user notification to all other online sessions of the logged-in user actively.
  /// * If the server property `turms.service.notification.group-blocked-user-added.notify-blocked-user`,
  ///   is true (false by default), the server will send a block user notification to the target user actively.
  /// * If the server property `turms.service.notification.group-blocked-user-added.notify-group-members`
  ///   is true (false by default), the server will send a block user notification to all group members of the target group actively.
  ///
  /// **Params**:
  /// * `groupId`: The target group ID.
  /// * `userId`: The target user ID.
  ///
  /// **Throws**: [ResponseException] if an error occurs.
  Future<Response<void>> blockUser(Int64 groupId, Int64 userId) async {
    final n = await _turmsClient.driver
        .send(CreateGroupBlockedUserRequest(userId: userId, groupId: groupId));
    return n.toNullResponse();
  }

  /// Unblock a user in the group.
  ///
  /// Authorization:
  /// * Only the group owner or group managers can unblock users.
  ///   Otherwise, throws [ResponseException] with the code [ResponseStatusCode.notGroupOwnerOrManagerToRemoveBlockedUser].
  ///
  /// Notifications:
  /// * If the server property `turms.service.notification.group-blocked-user-removed.notify-requester-other-online-sessions`
  ///   is true (true by default), the server will send a unblock user notification to all other online sessions of the logged-in user actively.
  /// * If the server property `turms.service.notification.group-blocked-user-removed.notify-blocked-user`,
  ///   is true (false by default), the server will send a unblock user notification to the target user actively.
  /// * If the server property `turms.service.notification.group-blocked-user-removed.notify-group-members`
  ///   is true (false by default), the server will send a unblock user notification to all group members of the target group actively.
  ///
  /// **Params**:
  /// * `groupId`: The target group ID.
  /// * `userId`: The target user ID.
  ///
  /// **Throws**: [ResponseException] if an error occurs.
  Future<Response<void>> unblockUser(Int64 groupId, Int64 userId) async {
    final n = await _turmsClient.driver
        .send(DeleteGroupBlockedUserRequest(groupId: groupId, userId: userId));
    return n.toNullResponse();
  }

  /// Find blocked user IDs.
  ///
  /// **Params**:
  /// * `groupId`: The target group ID.
  /// * `lastUpdatedDate`: The last updated date of blocked user IDs stored locally.
  /// The server will only return blocked user IDs that are updated after [lastUpdatedDate].
  /// If null, all blocked user IDs will be returned.
  ///
  /// **Throws**: [ResponseException] if an error occurs.
  Future<Response<LongsWithVersion?>> queryBlockedUserIds(Int64 groupId,
      {DateTime? lastUpdatedDate}) async {
    final n = await _turmsClient.driver.send(QueryGroupBlockedUserIdsRequest(
        groupId: groupId, lastUpdatedDate: lastUpdatedDate?.toInt64()));
    return n.toResponse(
        (data) => data.hasLongsWithVersion() ? data.longsWithVersion : null);
  }

  /// Find blocked user infos.
  ///
  /// **Params**:
  /// * `groupId`: The target group ID.
  /// * `lastUpdatedDate`: The last updated date of blocked user infos stored locally.
  /// The server will only return blocked user infos that are updated after [lastUpdatedDate].
  /// If null, all blocked user infos will be returned.
  ///
  /// **Throws**: [ResponseException] if an error occurs.
  Future<Response<UserInfosWithVersion?>> queryBlockedUserInfos(Int64 groupId,
      {DateTime? lastUpdatedDate}) async {
    final n = await _turmsClient.driver.send(QueryGroupBlockedUserInfosRequest(
        groupId: groupId, lastUpdatedDate: lastUpdatedDate?.toInt64()));
    return n.toResponse((data) =>
        data.hasUserInfosWithVersion() ? data.userInfosWithVersion : null);
  }

  // Group Enrollment

  /// Create an invitation.
  ///
  /// Authorization:
  /// * If [inviteeId] is already a group member,
  ///   throws [ResponseException] with the code [ResponseStatusCode.sendGroupInvitationToGroupMember].
  /// * Depending on the group join strategy, if the group do not use the invitation strategy
  ///   throws [ResponseException] with the code
  ///   [ResponseStatusCode.notGroupOwnerToSendGroupInvitation],
  ///   [ResponseStatusCode.notGroupOwnerOrManagerToSendGroupInvitation],
  ///   or [ResponseStatusCode.notGroupMemberToSendGroupInvitation].
  /// * If the group allows adding users as new group members without users' approval,
  ///   throws [ResponseException] with the code [ResponseStatusCode.sendGroupInvitationToGroupNotRequiringUsersApproval].
  /// * If the group does not exist,
  ///   throws [ResponseException] with the code [ResponseStatusCode.addUserToInactiveGroup].
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
  /// **Params**:
  /// * `groupId`: The target group ID.
  /// * `inviteeId`: The target user ID.
  /// * `content`: The invitation content.
  ///
  /// **Returns**: The invitation ID.
  ///
  /// **Throws**: [ResponseException] if an error occurs.
  Future<Response<Int64>> createInvitation(
      Int64 groupId, Int64 inviteeId, String content) async {
    final n = await _turmsClient.driver.send(CreateGroupInvitationRequest(
        groupId: groupId, inviteeId: inviteeId, content: content));
    return n.toResponse((data) => data.getLongOrThrow());
  }

  /// Delete/Recall an invitation.
  ///
  /// Authorization:
  /// * If the server property `turms.service.group.invitation.allow-recall-pending-invitation-by-sender`
  ///   is true (false by default), the logged-in user can recall pending invitations sent by themselves.
  ///   Otherwise, throws [ResponseException].
  /// * If the server property `turms.service.group.invitation.allow-recall-pending-invitation-by-owner-and-manager`
  ///   is true (false by default), the logged-in user can recall pending invitations only if they are the group owner or manager of the invitation.
  ///   Otherwise, throws [ResponseException].
  /// * For the above two cases, the following codes will be thrown according to different properties:
  ///   [ResponseStatusCode.recallingGroupInvitationIsDisabled] if the above two properties are false.
  ///   [ResponseStatusCode.notGroupOwnerOrManagerToRecallGroupInvitation],
  ///   [ResponseStatusCode.notGroupOwnerOrManagerOrSenderToRecallGroupInvitation]
  /// * If the group invitation is not pending (e.g. expired, accepted, deleted, etc),
  ///   throws [ResponseException] with the code [ResponseStatusCode.recallNonPendingGroupInvitation].
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
  /// **Throws**: [ResponseException] if an error occurs.
  Future<Response<void>> deleteInvitation(Int64 invitationId) async {
    final n = await _turmsClient.driver
        .send(DeleteGroupInvitationRequest(invitationId: invitationId));
    return n.toNullResponse();
  }

  /// Reply to a group invitation.
  ///
  /// If the logged-in user accepts an invitation sent by a group,
  /// the user will become a group member automatically.
  ///
  /// Authorization:
  /// * If the logged-in user is not the invitee of the group invitation,
  ///   throws [ResponseException] with the code [ResponseStatusCode.notInviteeToUpdateGroupInvitation].
  /// * If the group invitation is not pending (e.g. expired, accepted, deleted, etc),
  ///   throws [ResponseException] with the code [ResponseStatusCode.updateNonPendingGroupInvitation].
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
  /// **Params**:
  /// * `invitationId`: The invitation ID.
  /// * `responseAction`: The response action.
  /// * `reason`: The reason of the response.
  ///
  /// **Throws**: [ResponseException] if an error occurs.
  Future<Response<void>> replyInvitation(
      Int64 invitationId, ResponseAction responseAction,
      {String? reason}) async {
    final n = await _turmsClient.driver.send(UpdateGroupInvitationRequest(
        invitationId: invitationId,
        responseAction: responseAction,
        reason: reason));
    return n.toResponse((data) {});
  }

  /// Find invitations.
  ///
  /// **Params**:
  /// * `groupId`: The target group ID.
  /// * `lastUpdatedDate`: The last updated date of invitations stored locally.
  /// The server will only return groups that are updated after [lastUpdatedDate].
  /// If null, all group IDs will be returned.
  ///
  /// **Returns**: A list of invitation IDs and the version.
  /// Note: The version can be used to update the last updated date stored locally.
  ///
  /// **Throws**: [ResponseException] if an error occurs.
  Future<Response<GroupInvitationsWithVersion?>> queryInvitationsByGroupId(
      Int64 groupId,
      {DateTime? lastUpdatedDate}) async {
    final n = await _turmsClient.driver.send(QueryGroupInvitationsRequest(
        groupId: groupId, lastUpdatedDate: lastUpdatedDate?.toInt64()));
    return n.toResponse((data) => data.hasGroupInvitationsWithVersion()
        ? data.groupInvitationsWithVersion
        : null);
  }

  /// Find invitations.
  ///
  /// **Params**:
  /// * `areSentByMe`: Whether the invitations are sent by me.
  /// * `lastUpdatedDate`: The last updated date of invitations stored locally.
  /// The server will only return invitations that are updated after [lastUpdatedDate].
  /// If null, all invitations will be returned.
  ///
  /// **Returns**: A list of invitation IDs and the version.
  /// Note: The version can be used to update the last updated date stored locally.
  ///
  /// **Throws**: [ResponseException] if an error occurs.
  Future<Response<GroupInvitationsWithVersion?>> queryInvitations(
      bool areSentByMe,
      {DateTime? lastUpdatedDate}) async {
    final n = await _turmsClient.driver.send(QueryGroupInvitationsRequest(
        areSentByMe: areSentByMe, lastUpdatedDate: lastUpdatedDate?.toInt64()));
    return n.toResponse((data) => data.hasGroupInvitationsWithVersion()
        ? data.groupInvitationsWithVersion
        : null);
  }

  /// Create a group join/membership request.
  ///
  /// Authorization:
  /// * If the logged-in user has been blocked by the group,
  ///   throws [ResponseException] with the code [ResponseStatusCode.blockedUserSendGroupJoinRequest].
  /// * If the logged-in user trys to send a join request to the group
  ///   in which they are already a member,
  ///   throws [ResponseException] with the code [ResponseStatusCode.groupMemberSendGroupJoinRequest].
  /// * If the group does not allow group join requests,
  ///   throws [ResponseException] with the code:
  ///   [ResponseStatusCode.sendGroupJoinRequestToGroupUsingInvitation],
  ///   [ResponseStatusCode.sendGroupJoinRequestToGroupUsingMembershipRequest],
  ///   or [ResponseStatusCode.sendGroupJoinRequestToGroupUsingQuestion].
  ///
  /// Notifications:
  /// * If the server property `turms.service.notification.group-join-request-created.notify-requester-other-online-sessions`
  ///   is true (true by default), the server will send a group membership request notification to all other online sessions of the logged-in user actively.
  /// * If the server property `turms.service.notification.group-join-request-created.notify-group-owner-and-managers`,
  ///   is true (true by default), the server will send a group membership request notification to the group owner and managers actively.
  /// * If the server property `turms.service.notification.group-join-request-created.notify-group-members`
  ///   is true (false by default), the server will send a group membership request notification to all group members of the target group actively.
  ///
  /// **Params**:
  /// * `groupId`: The target group ID.
  /// * `content`: The request content.
  ///
  /// **Returns**: The request ID.
  ///
  /// **Throws**: [ResponseException] if an error occurs.
  Future<Response<Int64>> createJoinRequest(
      Int64 groupId, String content) async {
    final n = await _turmsClient.driver.send(
        CreateGroupJoinRequestRequest(groupId: groupId, content: content));
    return n.toResponse((data) => data.getLongOrThrow());
  }

  /// Delete/Recall a group join/membership request.
  ///
  /// Authorization:
  /// * If the server property `turms.service.group.join-request.allow-recall-pending-join-request-by-sender`
  ///   is true (false by default), the logged-in user can recall pending join requests sent by themselves.
  ///   Otherwise, throws [ResponseException] with the code [ResponseStatusCode.recallingGroupJoinRequestIsDisabled].
  /// * If the logged-in user is not the sender of the group join request,
  ///   throws [ResponseException] with the code [ResponseStatusCode.notSenderToRecallGroupJoinRequest].
  /// * If the group join request is not pending (e.g. expired, accepted, deleted, etc),
  ///   throws [ResponseException] with the code [ResponseStatusCode.recallNonPendingGroupJoinRequest].
  ///
  /// Notifications:
  /// * If the server property `turms.service.notification.group-join-request-recalled.notify-requester-other-online-sessions`
  ///   is true (true by default), the server will send a delete join request notification to all other online sessions of the logged-in user actively.
  /// * If the server property `turms.service.notification.group-join-request-recalled.notify-group-owner-and-managers`
  ///   is true (true by default), the server will send a delete join request notification to the group owner and managers actively.
  /// * If the server property `turms.service.notification.group-join-request-recalled.notify-group-members`,
  ///   is true (false by default), the server will send a delete join request notification to all group members of the target group actively.
  ///
  /// **Throws**: [ResponseException] if an error occurs.
  Future<Response<void>> deleteJoinRequest(Int64 requestId) async {
    final n = await _turmsClient.driver
        .send(DeleteGroupJoinRequestRequest(requestId: requestId));
    return n.toNullResponse();
  }

  /// Reply a group join/membership request.
  ///
  /// If the logged-in user accepts/approves a join request sent by a user,
  /// the user will become a group member automatically.
  ///
  /// Authorization:
  /// 1. If the logged-in user is not the group owner or manager of the group,
  /// throws [ResponseException] with the code [ResponseStatusCode.notGroupOwnerOrManagerToUpdateGroupJoinRequest].
  /// 2. If the group join request is not pending (e.g. expired, accepted, deleted, etc),
  /// throws [ResponseException] with the code [ResponseStatusCode.updateNonPendingGroupJoinRequest].
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
  /// **Params**:
  /// * `requestId`: The target group join request ID.
  /// * `responseAction`: The response action.
  /// * `reason`: The reason of the response.
  ///
  /// **Throws**: [ResponseException] if an error occurs.
  Future<Response<void>> replyJoinRequest(
      Int64 requestId, ResponseAction responseAction,
      {String? reason}) async {
    final n = await _turmsClient.driver.send(UpdateGroupJoinRequestRequest(
        requestId: requestId, responseAction: responseAction, reason: reason));
    return n.toResponse((data) {});
  }

  /// Find group join/membership requests.
  ///
  /// **Params**:
  /// * `groupId`: The target group ID.
  /// * `lastUpdatedDate`: The last updated date of requests stored locally.
  /// The server will only return requests that are updated after [lastUpdatedDate].
  /// If null, all requests will be returned.
  ///
  /// **Returns**: A list of request IDs and the version.
  /// Note: The version can be used to update the last updated date stored locally.
  ///
  /// **Throws**: [ResponseException] if an error occurs.
  Future<Response<GroupJoinRequestsWithVersion?>> queryJoinRequests(
      Int64 groupId,
      {DateTime? lastUpdatedDate}) async {
    final n = await _turmsClient.driver.send(QueryGroupJoinRequestsRequest(
        groupId: groupId, lastUpdatedDate: lastUpdatedDate?.toInt64()));
    return n.toResponse((data) => data.hasGroupJoinRequestsWithVersion()
        ? data.groupJoinRequestsWithVersion
        : null);
  }

  /// Find group join/membership requests sent by the logged-in user.
  ///
  /// **Params**:
  /// * `lastUpdatedDate`: The last updated date of requests stored locally.
  /// The server will only return requests that are updated after [lastUpdatedDate].
  /// If null, all requests will be returned.
  ///
  /// **Returns**: A list of request IDs and the version.
  /// Note: The version can be used to update the last updated date stored locally.
  ///
  /// **Throws**: [ResponseException] if an error occurs.
  Future<Response<GroupJoinRequestsWithVersion?>> querySentJoinRequests(
      {DateTime? lastUpdatedDate}) async {
    final n = await _turmsClient.driver.send(QueryGroupJoinRequestsRequest(
        lastUpdatedDate: lastUpdatedDate?.toInt64()));
    return n.toResponse((data) => data.hasGroupJoinRequestsWithVersion()
        ? data.groupJoinRequestsWithVersion
        : null);
  }

  /// Find group join/membership questions.
  ///
  /// Authorization:
  /// * Only the owner and managers have the right to fetch questions with answers
  ///
  /// **Params**:
  /// * `groupId`: The target group ID.
  /// * `withAnswers`: Whether to return the answers.
  /// * `lastUpdatedDate`: The last updated date of questions stored locally.
  /// The server will only return questions that are updated after [lastUpdatedDate].
  /// If null, all questions will be returned.
  ///
  /// **Returns**: A list of question IDs and the version.
  /// Note: The version can be used to update the last updated date stored locally.
  ///
  /// **Throws**: [ResponseException] if an error occurs.
  Future<Response<GroupJoinQuestionsWithVersion?>> queryGroupJoinQuestions(
      Int64 groupId,
      {bool withAnswers = false,
      DateTime? lastUpdatedDate}) async {
    final n = await _turmsClient.driver.send(QueryGroupJoinQuestionsRequest(
        groupId: groupId,
        withAnswers: withAnswers,
        lastUpdatedDate: lastUpdatedDate?.toInt64()));
    return n.toResponse((data) => data.hasGroupJoinQuestionsWithVersion()
        ? data.groupJoinQuestionsWithVersion
        : null);
  }

  /// Answer group join/membership questions, and join the group automatically
  /// if the logged-in user has answered some questions correctly
  /// and acquire the minimum score to join.
  ///
  /// **Params**:
  /// * `questionIdToAnswer`: The map of question ID to answer.
  ///
  /// **Returns**: The group membership questions answer result.
  ///
  /// **Throws**: [ResponseException] if an error occurs.
  Future<Response<GroupJoinQuestionsAnswerResult?>> answerGroupQuestions(
      Map<Int64, String> questionIdToAnswer) async {
    if (questionIdToAnswer.isEmpty) {
      throw ResponseException(
          code: ResponseStatusCode.illegalArgument,
          reason: '`questionIdToAnswer` must not be empty');
    }
    final n = await _turmsClient.driver.send(
        CheckGroupJoinQuestionsAnswersRequest(
            questionIdToAnswer: questionIdToAnswer));
    if (!n.data.hasGroupJoinQuestionAnswerResult()) {
      throw ResponseException(
          code: ResponseStatusCode.invalidResponse,
          reason: 'Missing group join question answer result');
    }
    return n.toResponse((data) => data.groupJoinQuestionAnswerResult);
  }

  // Group Member

  /// Add group members.
  ///
  /// Authorization:
  /// * If the group is inactive,
  ///   throws [ResponseException] with the code [ResponseStatusCode.addUserToInactiveGroup].
  /// * If the group has reached the maximum number of group members,
  ///   throws [ResponseException] with the code [ResponseStatusCode.addUserToGroupWithSizeLimitReached].
  /// * If the group doesn't allow add users as group members directly,
  ///   throws [ResponseException] with the code [ResponseStatusCode.addUserToGroupRequiringUsersApproval].
  /// * When the logged-in user tries to add themselves as a group member,
  ///   they will become a group member if the group uses member requests as the join strategy.
  ///   Otherwise, throws the following codes according to different join strategies:
  ///   [ResponseStatusCode.userJoinGroupWithoutAcceptingGroupInvitation],
  ///   [ResponseStatusCode.userJoinGroupWithoutAnsweringGroupQuestion],
  ///   [ResponseStatusCode.userJoinGroupWithoutSendingGroupJoinRequest].
  /// * If the logged-in user has no permission to add new group members,
  ///   throws [ResponseException] with one of the following codes:
  ///   [ResponseStatusCode.notGroupOwnerToAddGroupMember],
  ///   [ResponseStatusCode.notGroupOwnerOrManagerToAddGroupMember],
  ///   [ResponseStatusCode.notGroupMemberToAddGroupMember],
  ///   [ResponseStatusCode.notGroupOwnerToAddGroupManager].
  /// * If [userIds] contains a blocked user ID,
  ///   throws [ResponseException] with the code [ResponseStatusCode.addBlockedUserToGroup].
  ///
  /// Notifications:
  /// * If the server property `turms.service.notification.group-member-added.notify-requester-other-online-sessions`
  ///   is true (true by default), the server will send a add group member notification to all other online sessions of the logged-in user actively.
  /// * If the server property `turms.service.notification.group-member-added.notify-added-group-member`,
  ///   is true (true by default), the server will send a add group member notification to all other online sessions of the added group member.
  /// * If the server property `turms.service.notification.group-member-added.notify-other-group-members`,
  ///   is true (true by default), the server will send a add group member notification to all other online sessions of the other group members.
  ///
  /// **Params**:
  /// * `groupId`: The target group ID.
  /// * `userIds`: The target user IDs.
  /// * `name`: The name of the group member.
  /// * `role`: The role of the group member.
  /// * `muteEndDate`: The mute end date of the group member.
  ///
  /// **Throws**: [ResponseException] if an error occurs.
  Future<Response<void>> addGroupMembers(Int64 groupId, Set<Int64> userIds,
      {String? name, GroupMemberRole? role, DateTime? muteEndDate}) async {
    if (userIds.isEmpty) {
      return Future.value(Response.nullValue());
    }
    final n = await _turmsClient.driver.send(CreateGroupMembersRequest(
        groupId: groupId,
        userIds: userIds,
        name: name,
        role: role,
        muteEndDate: muteEndDate?.toInt64()));
    return n.toNullResponse();
  }

  /// Join a group.
  ///
  /// Authorization:
  /// * When the logged-in user tries to add themselves as a group member,
  ///   they will become a group member if the group uses member requests as the join strategy.
  ///   Otherwise, throws the following codes according to different join strategies:
  ///   [ResponseStatusCode.userJoinGroupWithoutAcceptingGroupInvitation],
  ///   [ResponseStatusCode.userJoinGroupWithoutAnsweringGroupQuestion],
  ///   [ResponseStatusCode.userJoinGroupWithoutSendingGroupJoinRequest].
  ///
  /// Notifications:
  /// * If the server property `turms.service.notification.group-member-added.notify-requester-other-online-sessions`
  ///   is true (true by default), the server will send a add group member notification to all other online sessions of the logged-in user actively.
  /// * If the server property `turms.service.notification.group-member-added.notify-added-group-member`,
  ///   is true (true by default), the server will send a add group member notification to all other online sessions of the added group member.
  /// * If the server property `turms.service.notification.group-member-added.notify-other-group-members`,
  ///   is true (true by default), the server will send a add group member notification to all other online sessions of the other group members.
  ///
  /// **Params**:
  /// * `groupId`: The target group ID.
  /// * `name`: The name as the group member.
  ///
  /// **Throws**: [ResponseException] if an error occurs.
  Future<Response<void>> joinGroup(Int64 groupId, {String? name}) async {
    final info = _turmsClient.userService.userInfo;
    if (info == null) {
      throw ResponseException(
          code: ResponseStatusCode.clientSessionHasBeenClosed);
    }
    return addGroupMembers(groupId, {info.userId}, name: name);
  }

  /// Quit a group.
  ///
  /// Notifications:
  /// * If the server property `turms.service.notification.group-member-removed.notify-requester-other-online-sessions`,
  ///   is true (true by default), the server will send a delete group member notification to all other online sessions of the logged-in user actively.
  /// * If the server property `turms.service.notification.group-member-removed.notify-other-group-members`,
  ///   is true (true by default), the server will send a delete group member notification to all other group members of the group actively.
  ///
  /// **Params**:
  /// * `groupId`: The target group ID.
  /// * `successorId`: The new successor ID.
  /// If the logged-in user is the owner of the group, they must transfer the group ownership to the [successorId],
  /// throws [ResponseException] with the code [ResponseStatusCode.groupOwnerQuitWithoutSpecifyingSuccessor] otherwise.
  /// And the successor will become the group owner.
  /// The successor must already be a member of the group, throws [ResponseException] with the code
  /// [ResponseStatusCode.groupSuccessorNotGroupMember] otherwise.
  /// * `quitAfterTransfer`: Whether to quit the group after transfer the group ownership.
  /// If false, the logged-in user will become a normal group member (not the group admin).
  /// If null, the value will not be changed.
  ///
  /// Authorization:
  /// * If the logged-in user is not the owner of the group,
  ///   [ResponseException] with the code [ResponseStatusCode.notGroupOwnerToTransferGroup] will be thrown.
  ///
  /// **Throws**: [ResponseException] if an error occurs.
  Future<Response<void>> quitGroup(Int64 groupId,
      {Int64? successorId, bool? quitAfterTransfer}) async {
    final n = await _turmsClient.driver.send(DeleteGroupMembersRequest(
        groupId: groupId,
        memberIds: [_turmsClient.userService.userInfo!.userId],
        successorId: successorId,
        quitAfterTransfer: quitAfterTransfer));
    return n.toNullResponse();
  }

  /// Remove group members.
  ///
  /// Authorization:
  /// * If the logged-in user is not the group owner or manager of the group,
  ///   throws [ResponseException] with the code [ResponseStatusCode.notGroupOwnerOrManagerToRemoveGroupMember].
  ///
  /// Notifications:
  /// * If the server property `turms.service.notification.group-member-removed.notify-requester-other-online-sessions`,
  ///   is true (true by default), the server will send a delete group member notification to all other online sessions of the logged-in user actively.
  /// * If the server property `turms.service.notification.group-member-removed.notify-removed-group-member`,
  ///   is true (true by default), the server will send a delete group member notification to the removed group member actively.
  /// * If the server property `turms.service.notification.group-member-removed.notify-other-group-members`,
  ///   is true (true by default), the server will send a delete group member notification to all other group members of the group actively.
  ///
  /// **Params**:
  /// * `groupId`: The target group ID.
  /// * `memberIds`: The target member IDs.
  ///
  /// **Throws**: [ResponseException] if an error occurs.
  Future<Response<void>> removeGroupMembers(
      Int64 groupId, Set<Int64> memberIds) async {
    if (memberIds.isEmpty) {
      return Future.value(Response.nullValue());
    }
    final n = await _turmsClient.driver.send(
        DeleteGroupMembersRequest(groupId: groupId, memberIds: memberIds));
    return n.toNullResponse();
  }

  /// Update group member info.
  ///
  /// Authorization:
  /// * If the logged-in user is not the group owner or manager of the group,
  ///   throws [ResponseException] with the code [ResponseStatusCode.notGroupOwnerOrManagerToUpdateGroupInfo].
  ///
  /// Notifications:
  /// * If the server property `turms.service.notification.group-member-info-updated.notify-requester-other-online-sessions`,
  ///   is true (true by default), the server will send a update group member info notification to all other online sessions of the logged-in user actively.
  /// * If the server property `turms.service.notification.group-member-info-updated.notify-updated-group-member`,
  ///   is true (false by default), the server will send a update group member info notification to the updated group member actively.
  /// * If the server property `turms.service.notification.group-member-info-updated.notify-other-group-members`,
  ///   is true (false by default), the server will send a update group member info notification to all other group members of the group actively.
  ///
  /// **Params**:
  /// * `groupId`: The target group ID.
  /// * `memberId`: The target member ID.
  /// * `name`: The new name of the group member.
  /// If null, the name will not be updated.
  /// * `role`: The new role of the group member.
  /// If null, the role will not be updated.
  /// * `muteEndDate`: The new mute end date of the group member.
  /// If null, the mute end date will not be updated.
  ///
  /// Authorization:
  /// * If the logged-in user is not the group owner or manager of the group,
  ///   throws [ResponseException] with the code [ResponseStatusCode.notGroupOwnerOrManagerToMuteGroupMember]
  /// * If the logged-in user is not the group owner,
  ///   throws [ResponseException] with the code [ResponseStatusCode.muteGroupMemberWithRoleEqualToOrHigherThanRequester].
  ///
  /// **Throws**: [ResponseException] if an error occurs.
  Future<Response<void>> updateGroupMemberInfo(Int64 groupId, Int64 memberId,
      {String? name, GroupMemberRole? role, DateTime? muteEndDate}) async {
    if ([name, role, muteEndDate].areAllNull) {
      return Response.nullValue();
    }
    final n = await _turmsClient.driver.send(UpdateGroupMemberRequest(
        groupId: groupId,
        memberId: memberId,
        name: name,
        role: role,
        muteEndDate: muteEndDate?.toInt64()));
    return n.toNullResponse();
  }

  /// Mute group member.
  ///
  /// Authorization:
  /// * If the logged-in user is not the group owner or manager of the group,
  ///   throws [ResponseException] with the code [ResponseStatusCode.notGroupOwnerOrManagerToMuteGroupMember]
  /// * If the logged-in user is not the group owner,
  ///   throws [ResponseException] with the code [ResponseStatusCode.muteGroupMemberWithRoleEqualToOrHigherThanRequester].
  ///
  /// Notifications:
  /// * If the server property `turms.service.notification.group-member-info-updated.notify-requester-other-online-sessions`,
  ///   is true (true by default), the server will send a update group member info notification to all other online sessions of the logged-in user actively.
  /// * If the server property `turms.service.notification.group-member-info-updated.notify-updated-group-member`,
  ///   is true (false by default), the server will send a update group member info notification to the updated group member actively.
  /// * If the server property `turms.service.notification.group-member-info-updated.notify-other-group-members`,
  ///   is true (false by default), the server will send a update group member info notification to all other group members of the group actively.
  ///
  /// **Params**:
  /// * `groupId`: The target group ID.
  /// * `memberId`: The target member ID.
  /// * `muteEndDate`: The new mute end date of the group member.
  ///
  /// **Throws**: [ResponseException] if an error occurs.
  Future<Response<void>> muteGroupMember(
          Int64 groupId, Int64 memberId, DateTime muteEndDate) =>
      updateGroupMemberInfo(groupId, memberId, muteEndDate: muteEndDate);

  /// Unmute group member.
  ///
  /// Authorization:
  /// * If the logged-in user is not the group owner or manager of the group,
  ///   throws [ResponseException] with the code [ResponseStatusCode.notGroupOwnerOrManagerToMuteGroupMember]
  /// * If the logged-in user is not the group owner,
  ///   throws [ResponseException] with the code [ResponseStatusCode.muteGroupMemberWithRoleEqualToOrHigherThanRequester].
  ///
  /// Notifications:
  /// * If the server property `turms.service.notification.group-member-info-updated.notify-requester-other-online-sessions`,
  ///   is true (true by default), the server will send a update group member info notification to all other online sessions of the logged-in user actively.
  /// * If the server property `turms.service.notification.group-member-info-updated.notify-updated-group-member`,
  ///   is true (false by default), the server will send a update group member info notification to the updated group member actively.
  /// * If the server property `turms.service.notification.group-member-info-updated.notify-other-group-members`,
  ///   is true (false by default), the server will send a update group member info notification to all other group members of the group actively.
  ///
  /// **Params**:
  /// * `groupId`: The target group ID.
  /// * `memberId`: The target member ID.
  ///
  /// **Throws**: [ResponseException] if an error occurs.
  Future<Response<void>> unmuteGroupMember(Int64 groupId, Int64 memberId) =>
      muteGroupMember(
          groupId, memberId, DateTime.fromMillisecondsSinceEpoch(0));

  /// Find group members.
  ///
  /// **Params**:
  /// * `groupId`: The target group ID.
  /// * `withStatus`: Whether to return the session status of the group members.
  /// * `lastUpdatedDate`: The last updated date of the group members stored locally.
  /// The server will only return group members that are updated after [lastUpdatedDate].
  /// If null, all group members will be returned.
  ///
  /// **Returns**: Group members and the version.
  /// Note: The version can be used to update the last updated date stored locally.
  ///
  /// **Throws**: [ResponseException] if an error occurs.
  Future<Response<GroupMembersWithVersion?>> queryGroupMembers(Int64 groupId,
      {bool withStatus = false, DateTime? lastUpdatedDate}) async {
    final n = await _turmsClient.driver.send(QueryGroupMembersRequest(
        groupId: groupId,
        lastUpdatedDate: lastUpdatedDate?.toInt64(),
        withStatus: withStatus));
    return n.toResponse((data) => data.hasGroupMembersWithVersion()
        ? data.groupMembersWithVersion
        : null);
  }

  /// Find group members.
  ///
  /// **Params**:
  /// * `groupId`: The target group ID.
  /// * `memberIds`: The target member IDs.
  /// * `withStatus`: Whether to return the session status of the group members.
  ///
  /// **Returns**: Group members and the version.
  /// Note: The version can be used to update the last updated date stored locally.
  ///
  /// **Throws**: [ResponseException] if an error occurs.
  Future<Response<GroupMembersWithVersion?>> queryGroupMembersByMemberIds(
      Int64 groupId, List<Int64> memberIds,
      {bool withStatus = false}) async {
    final n = await _turmsClient.driver.send(QueryGroupMembersRequest(
        groupId: groupId, memberIds: memberIds, withStatus: withStatus));
    return n.toResponse((data) => data.hasGroupMembersWithVersion()
        ? data.groupMembersWithVersion
        : null);
  }
}
