import 'package:fixnum/fixnum.dart' show Int64;

import '../../turms_client.dart';
import '../extension/int_extensions.dart';
import '../extension/notification_extensions.dart';

class GroupService {
  final TurmsClient _turmsClient;

  GroupService(this._turmsClient);

  Future<Response<Int64>> createGroup(String name,
      {String? intro,
      String? announcement,
      int? minimumScore,
      DateTime? muteEndDate,
      Int64? groupTypeId}) async {
    final n = await _turmsClient.driver.send(CreateGroupRequest(
        name: name,
        intro: intro,
        announcement: announcement,
        minimumScore: minimumScore,
        muteEndDate: muteEndDate?.toInt64(),
        groupTypeId: groupTypeId));
    return n.toResponse((data) => data.getFirstIdOrThrow());
  }

  Future<Response<void>> deleteGroup(Int64 groupId) async {
    final n =
        await _turmsClient.driver.send(DeleteGroupRequest(groupId: groupId));
    return n.toNullResponse();
  }

  Future<Response<void>> updateGroup(Int64 groupId,
      {String? groupName,
      String? intro,
      String? announcement,
      int? minimumScore,
      Int64? groupTypeId,
      DateTime? muteEndDate,
      Int64? successorId,
      bool? quitAfterTransfer}) async {
    if ([
      groupName,
      intro,
      announcement,
      minimumScore,
      groupTypeId,
      muteEndDate,
      successorId
    ].areAllNull) {
      return Response.nullValue();
    }
    final n = await _turmsClient.driver.send(UpdateGroupRequest(
        groupId: groupId,
        groupName: groupName,
        intro: intro,
        announcement: announcement,
        muteEndDate: muteEndDate?.toInt64(),
        minimumScore: minimumScore,
        groupTypeId: groupTypeId,
        successorId: successorId,
        quitAfterTransfer: quitAfterTransfer));
    return n.toNullResponse();
  }

  Future<Response<void>> transferOwnership(Int64 groupId, Int64 successorId,
          {bool quitAfterTransfer = false}) =>
      updateGroup(groupId,
          successorId: successorId, quitAfterTransfer: quitAfterTransfer);

  Future<Response<void>> muteGroup(Int64 groupId, DateTime muteEndDate) =>
      updateGroup(groupId, muteEndDate: muteEndDate);

  Future<Response<void>> unmuteGroup(Int64 groupId) =>
      muteGroup(groupId, DateTime(0));

  Future<Response<GroupWithVersion?>> queryGroup(Int64 groupId,
      {DateTime? lastUpdatedDate}) async {
    final n = await _turmsClient.driver.send(QueryGroupRequest(
        groupId: groupId, lastUpdatedDate: lastUpdatedDate?.toInt64()));
    return n.toResponse((data) {
      final groupsWithVersion = data.groupsWithVersion;
      if (groupsWithVersion.groups.isEmpty) {
        return null;
      }
      final date = groupsWithVersion.hasLastUpdatedDate()
          ? groupsWithVersion.lastUpdatedDate.toDateTime()
          : null;
      return GroupWithVersion(groupsWithVersion.groups[0], date);
    });
  }

  Future<Response<Int64ValuesWithVersion?>> queryJoinedGroupIds(
      {DateTime? lastUpdatedDate}) async {
    final n = await _turmsClient.driver.send(QueryJoinedGroupIdsRequest(
        lastUpdatedDate: lastUpdatedDate == null
            ? null
            : Int64(lastUpdatedDate.millisecondsSinceEpoch)));
    return n.toResponse(
        (data) => data.hasIdsWithVersion() ? data.idsWithVersion : null);
  }

  Future<Response<GroupsWithVersion?>> queryJoinedGroupInfos(
      {DateTime? lastUpdatedDate}) async {
    final n = await _turmsClient.driver.send(QueryJoinedGroupInfosRequest(
        lastUpdatedDate: lastUpdatedDate?.toInt64()));
    return n.toResponse(
        (data) => data.hasGroupsWithVersion() ? data.groupsWithVersion : null);
  }

  Future<Response<Int64>> addGroupJoinQuestion(
      Int64 groupId, String question, Set<String> answers, int score) async {
    if (answers.isEmpty) {
      throw ResponseException.fromCodeAndReason(
          ResponseStatusCode.illegalArgument, '"answers" must not be empty');
    }
    final n = await _turmsClient.driver.send(CreateGroupJoinQuestionRequest(
        groupId: groupId, question: question, answers: answers, score: score));
    return n.toResponse((data) => data.getFirstIdOrThrow());
  }

  Future<Response<void>> deleteGroupJoinQuestion(Int64 questionId) async {
    final n = await _turmsClient.driver
        .send(DeleteGroupJoinQuestionRequest(questionId: questionId));
    return n.toNullResponse();
  }

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
  Future<Response<void>> blockUser(Int64 groupId, Int64 userId) async {
    final n = await _turmsClient.driver
        .send(CreateGroupBlockedUserRequest(userId: userId, groupId: groupId));
    return n.toNullResponse();
  }

  Future<Response<void>> unblockUser(Int64 groupId, Int64 userId) async {
    final n = await _turmsClient.driver
        .send(DeleteGroupBlockedUserRequest(groupId: groupId, userId: userId));
    return n.toNullResponse();
  }

  Future<Response<Int64ValuesWithVersion?>> queryBlockedUserIds(Int64 groupId,
      {DateTime? lastUpdatedDate}) async {
    final n = await _turmsClient.driver.send(QueryGroupBlockedUserIdsRequest(
        groupId: groupId, lastUpdatedDate: lastUpdatedDate?.toInt64()));
    return n.toResponse(
        (data) => data.hasIdsWithVersion() ? data.idsWithVersion : null);
  }

  Future<Response<UsersInfosWithVersion?>> queryBlockedUserInfos(Int64 groupId,
      {DateTime? lastUpdatedDate}) async {
    final n = await _turmsClient.driver.send(QueryGroupBlockedUserInfosRequest(
        groupId: groupId, lastUpdatedDate: lastUpdatedDate?.toInt64()));
    return n.toResponse((data) =>
        data.hasUsersInfosWithVersion() ? data.usersInfosWithVersion : null);
  }

  // Group Enrollment
  Future<Response<Int64>> createInvitation(
      Int64 groupId, Int64 inviteeId, String content) async {
    final n = await _turmsClient.driver.send(CreateGroupInvitationRequest(
        groupId: groupId, inviteeId: inviteeId, content: content));
    return n.toResponse((data) => data.getFirstIdOrThrow());
  }

  Future<Response<void>> deleteInvitation(Int64 invitationId) async {
    final n = await _turmsClient.driver
        .send(DeleteGroupInvitationRequest(invitationId: invitationId));
    return n.toNullResponse();
  }

  Future<Response<GroupInvitationsWithVersion?>> queryInvitationsByGroupId(
      Int64 groupId,
      {DateTime? lastUpdatedDate}) async {
    final n = await _turmsClient.driver.send(QueryGroupInvitationsRequest(
        groupId: groupId, lastUpdatedDate: lastUpdatedDate?.toInt64()));
    return n.toResponse((data) => data.hasGroupInvitationsWithVersion()
        ? data.groupInvitationsWithVersion
        : null);
  }

  Future<Response<GroupInvitationsWithVersion?>> queryInvitations(
      bool areSentByMe,
      {DateTime? lastUpdatedDate}) async {
    final n = await _turmsClient.driver.send(QueryGroupInvitationsRequest(
        areSentByMe: areSentByMe, lastUpdatedDate: lastUpdatedDate?.toInt64()));
    return n.toResponse((data) => data.hasGroupInvitationsWithVersion()
        ? data.groupInvitationsWithVersion
        : null);
  }

  Future<Response<Int64>> createJoinRequest(
      Int64 groupId, String content) async {
    final n = await _turmsClient.driver.send(
        CreateGroupJoinRequestRequest(groupId: groupId, content: content));
    return n.toResponse((data) => data.getFirstIdOrThrow());
  }

  Future<Response<void>> deleteJoinRequest(Int64 requestId) async {
    final n = await _turmsClient.driver
        .send(DeleteGroupJoinRequestRequest(requestId: requestId));
    return n.toNullResponse();
  }

  Future<Response<GroupJoinRequestsWithVersion?>> queryJoinRequests(
      Int64 groupId,
      {DateTime? lastUpdatedDate}) async {
    final n = await _turmsClient.driver.send(QueryGroupJoinRequestsRequest(
        groupId: groupId, lastUpdatedDate: lastUpdatedDate?.toInt64()));
    return n.toResponse((data) => data.hasGroupJoinRequestsWithVersion()
        ? data.groupJoinRequestsWithVersion
        : null);
  }

  Future<Response<GroupJoinRequestsWithVersion?>> querySentJoinRequests(
      {DateTime? lastUpdatedDate}) async {
    final n = await _turmsClient.driver.send(QueryGroupJoinRequestsRequest(
        lastUpdatedDate: lastUpdatedDate?.toInt64()));
    return n.toResponse((data) => data.hasGroupJoinRequestsWithVersion()
        ? data.groupJoinRequestsWithVersion
        : null);
  }

  /// Note: Only the owner and managers have the right to fetch questions with answers
  Future<Response<GroupJoinQuestionsWithVersion?>>
      queryGroupJoinQuestionsRequest(Int64 groupId,
          {bool withAnswers = false, DateTime? lastUpdatedDate}) async {
    final n = await _turmsClient.driver.send(QueryGroupJoinQuestionsRequest(
        groupId: groupId,
        withAnswers: withAnswers,
        lastUpdatedDate: lastUpdatedDate?.toInt64()));
    return n.toResponse((data) => data.hasGroupJoinQuestionsWithVersion()
        ? data.groupJoinQuestionsWithVersion
        : null);
  }

  Future<Response<GroupJoinQuestionsAnswerResult?>> answerGroupQuestions(
      Map<Int64, String> questionIdToAnswer) async {
    if (questionIdToAnswer.isEmpty) {
      throw ResponseException.fromCodeAndReason(
          ResponseStatusCode.illegalArgument,
          '"questionIdToAnswer" must not be empty');
    }
    final n = await _turmsClient.driver.send(
        CheckGroupJoinQuestionsAnswersRequest(
            questionIdToAnswer: questionIdToAnswer));
    if (!n.data.hasGroupJoinQuestionAnswerResult()) {
      throw ResponseException.fromCodeAndReason(
          ResponseStatusCode.invalidResponse,
          'Missing group join question answer result');
    }
    return n.toResponse((data) => data.groupJoinQuestionAnswerResult);
  }

  // Group Member

  Future<Response<void>> addGroupMember(Int64 groupId, Int64 userId,
      {String? name, GroupMemberRole? role, DateTime? muteEndDate}) async {
    final n = await _turmsClient.driver.send(CreateGroupMemberRequest(
        groupId: groupId,
        userId: userId,
        name: name,
        role: role,
        muteEndDate: muteEndDate?.toInt64()));
    return n.toNullResponse();
  }

  Future<Response<void>> quitGroup(Int64 groupId,
      {Int64? successorId, bool? quitAfterTransfer}) async {
    final n = await _turmsClient.driver.send(DeleteGroupMemberRequest(
        groupId: groupId,
        memberId: _turmsClient.userService.userInfo?.userId,
        successorId: successorId,
        quitAfterTransfer: quitAfterTransfer));
    return n.toNullResponse();
  }

  Future<Response<void>> removeGroupMember(
      Int64 groupId, Int64 memberId) async {
    final n = await _turmsClient.driver
        .send(DeleteGroupMemberRequest(groupId: groupId, memberId: memberId));
    return n.toNullResponse();
  }

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

  Future<Response<void>> muteGroupMember(
          Int64 groupId, Int64 memberId, DateTime muteEndDate) =>
      updateGroupMemberInfo(groupId, memberId, muteEndDate: muteEndDate);

  Future<Response<void>> unmuteGroupMember(Int64 groupId, Int64 memberId) =>
      muteGroupMember(
          groupId, memberId, DateTime.fromMillisecondsSinceEpoch(0));

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
