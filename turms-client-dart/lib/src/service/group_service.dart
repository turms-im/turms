import 'package:fixnum/fixnum.dart' show Int64;

import '../../turms_client.dart';
import '../extension/notification_extensions.dart';

class GroupService {
  final TurmsClient _turmsClient;

  GroupService(this._turmsClient);

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

  Future<Response<void>> deleteGroup(Int64 groupId) async {
    final n =
        await _turmsClient.driver.send(DeleteGroupRequest(groupId: groupId));
    return n.toNullResponse();
  }

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

  Future<Response<void>> transferOwnership(Int64 groupId, Int64 successorId,
          {bool quitAfterTransfer = false}) =>
      updateGroup(groupId,
          successorId: successorId, quitAfterTransfer: quitAfterTransfer);

  Future<Response<void>> muteGroup(Int64 groupId, DateTime muteEndDate) =>
      updateGroup(groupId, muteEndDate: muteEndDate);

  Future<Response<void>> unmuteGroup(Int64 groupId) =>
      muteGroup(groupId, DateTime(0));

  Future<Response<List<Group>>> queryGroups(Set<Int64> groupIds,
      {DateTime? lastUpdatedDate}) async {
    if (groupIds.isEmpty) {
      return Future.value(Response.emptyList());
    }
    final n = await _turmsClient.driver.send(QueryGroupsRequest(
        groupIds: groupIds, lastUpdatedDate: lastUpdatedDate?.toInt64()));
    return n.toResponse((data) => data.groupsWithVersion.groups);
  }

  Future<Response<LongsWithVersion?>> queryJoinedGroupIds(
      {DateTime? lastUpdatedDate}) async {
    final n = await _turmsClient.driver.send(QueryJoinedGroupIdsRequest(
        lastUpdatedDate: lastUpdatedDate == null
            ? null
            : Int64(lastUpdatedDate.millisecondsSinceEpoch)));
    return n.toResponse(
        (data) => data.hasLongsWithVersion() ? data.longsWithVersion : null);
  }

  Future<Response<GroupsWithVersion?>> queryJoinedGroupInfos(
      {DateTime? lastUpdatedDate}) async {
    final n = await _turmsClient.driver.send(QueryJoinedGroupInfosRequest(
        lastUpdatedDate: lastUpdatedDate?.toInt64()));
    return n.toResponse(
        (data) => data.hasGroupsWithVersion() ? data.groupsWithVersion : null);
  }

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

  Future<Response<void>> deleteGroupJoinQuestions(
      Int64 groupId, Set<Int64> questionIds) async {
    final n = await _turmsClient.driver.send(DeleteGroupJoinQuestionsRequest(
        groupId: groupId, questionIds: questionIds));
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

  Future<Response<LongsWithVersion?>> queryBlockedUserIds(Int64 groupId,
      {DateTime? lastUpdatedDate}) async {
    final n = await _turmsClient.driver.send(QueryGroupBlockedUserIdsRequest(
        groupId: groupId, lastUpdatedDate: lastUpdatedDate?.toInt64()));
    return n.toResponse(
        (data) => data.hasLongsWithVersion() ? data.longsWithVersion : null);
  }

  Future<Response<UserInfosWithVersion?>> queryBlockedUserInfos(Int64 groupId,
      {DateTime? lastUpdatedDate}) async {
    final n = await _turmsClient.driver.send(QueryGroupBlockedUserInfosRequest(
        groupId: groupId, lastUpdatedDate: lastUpdatedDate?.toInt64()));
    return n.toResponse((data) =>
        data.hasUserInfosWithVersion() ? data.userInfosWithVersion : null);
  }

  // Group Enrollment
  Future<Response<Int64>> createInvitation(
      Int64 groupId, Int64 inviteeId, String content) async {
    final n = await _turmsClient.driver.send(CreateGroupInvitationRequest(
        groupId: groupId, inviteeId: inviteeId, content: content));
    return n.toResponse((data) => data.getLongOrThrow());
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
    return n.toResponse((data) => data.getLongOrThrow());
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

  Future<Response<GroupJoinQuestionsAnswerResult?>> answerGroupQuestions(
      Map<Int64, String> questionIdToAnswer) async {
    if (questionIdToAnswer.isEmpty) {
      throw ResponseException(
          code: ResponseStatusCode.illegalArgument,
          reason: '"questionIdToAnswer" must not be empty');
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

  Future<Response<void>> joinGroup(Int64 groupId, {String? name}) async {
    final info = _turmsClient.userService.userInfo;
    if (info == null) {
      throw ResponseException(
          code: ResponseStatusCode.clientSessionHasBeenClosed);
    }
    return addGroupMembers(groupId, {info.userId}, name: name);
  }

  Future<Response<void>> quitGroup(Int64 groupId,
      {Int64? successorId, bool? quitAfterTransfer}) async {
    final n = await _turmsClient.driver.send(DeleteGroupMembersRequest(
        groupId: groupId,
        memberIds: [_turmsClient.userService.userInfo!.userId],
        successorId: successorId,
        quitAfterTransfer: quitAfterTransfer));
    return n.toNullResponse();
  }

  Future<Response<void>> removeGroupMembers(
      Int64 groupId, Set<Int64> memberIds) async {
    if (memberIds.isEmpty) {
      return Future.value(Response.nullValue());
    }
    final n = await _turmsClient.driver.send(
        DeleteGroupMembersRequest(groupId: groupId, memberIds: memberIds));
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
