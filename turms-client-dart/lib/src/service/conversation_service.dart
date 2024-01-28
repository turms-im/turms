import 'package:fixnum/fixnum.dart' show Int64;

import '../../turms_client.dart';

class ConversationService {
  final TurmsClient _turmsClient;

  ConversationService(this._turmsClient);

  /// Find private conversations between the logged-in user and another user.
  ///
  /// Common Scenarios:
  /// * If you want to find all private conversations between
  ///   the current logged-in user and other users,
  ///   you can call methods like [UserService.queryRelatedUserIds],
  ///   [UserService.queryFriends] to get all user IDs first,
  ///   and pass these user IDs as [targetIds] to get all private conversations.
  /// * The returned [PrivateConversation] does NOT contain messages.
  ///   To find messages in conversations, you can use methods like
  ///   [MessageService.queryMessages] and [MessageService.queryMessagesWithTotal].
  ///
  /// **Params**:
  /// * `targetIds`: The target user IDs.
  /// If empty, an empty list of conversations is returned.
  ///
  /// **Returns**: A list of private conversations.
  /// Note that the list only contains conversations in which the logged-in user is a participant.
  /// If the logged-in user is not a participant of a specified conversation,
  /// these conversations will be filtered out on the server, and no error will be thrown.
  ///
  /// **Throws**: [ResponseException] if an error occurs.
  Future<Response<List<PrivateConversation>>> queryPrivateConversations(
      List<Int64> targetIds) async {
    if (targetIds.isEmpty) {
      return Response.emptyList();
    }
    final n = await _turmsClient.driver
        .send(QueryConversationsRequest(targetIds: targetIds));
    return n.toResponse((data) => data.conversations.privateConversations);
  }

  /// Find group conversations in which the logged-in user is a member.
  ///
  /// Common Scenarios:
  /// * If you want to find all group conversations between
  ///   the current logged-in user and groups in which the logged-in user is a member,
  ///   you can call methods like [GroupService.queryJoinedGroupIds],
  ///   [GroupService.queryJoinedGroupInfos] to get all group IDs first,
  ///   and pass these group IDs as [groupIds] to get all group conversations.
  /// * [GroupConversation] does NOT contain messages.
  ///   To find messages in conversations, you can use methods like
  ///   [MessageService.queryMessages] and [MessageService.queryMessagesWithTotal].
  ///
  /// **Params**:
  /// * `groupIds`: The target group IDs.
  /// If empty, an empty list of conversations is returned.
  ///
  /// **Returns**: A list of group conversations.
  /// Note that the list only contains conversations in which the logged-in user is a participant.
  /// If the logged-in user is not a participant of a specified conversation,
  /// these conversations will be filtered out on the server, and no error will be thrown.
  ///
  /// **Throws**: [ResponseException] if an error occurs.
  Future<Response<List<GroupConversation>>> queryGroupConversations(
      List<Int64> groupIds) async {
    if (groupIds.isEmpty) {
      return Response.emptyList();
    }
    final n = await _turmsClient.driver
        .send(QueryConversationsRequest(groupIds: groupIds));
    return n.toResponse((data) => data.conversations.groupConversations);
  }

  /// Update the read date of the target private conversation.
  ///
  /// Common Scenarios:
  /// * To find the read date of a conversation actively (if no notification is received from the server),
  ///   you can call [queryPrivateConversations].
  ///
  /// Authorization:
  /// * If the server property `turms.service.conversation.read-receipt.enabled`
  ///   is false (true by default), throws [ResponseException] with the code [ResponseStatusCode.updatingReadDateIsDisabled].
  ///
  /// Notifications:
  /// * If the server property `turms.service.notification.private-conversation-read-date-updated.notify-contact`
  ///   is true (false by default),
  ///   the server will send a read date update notification to the participant actively.
  /// * If the server property `turms.service.notification.private-conversation-read-date-updated.notify-requester-other-online-sessions`
  ///   is true (true by default),
  ///   the server will send a read date update notification to all other online sessions of the logged-in user actively.
  ///
  /// **Params**:
  /// * `targetId`: The target user ID.
  /// * `readDate`: The read date.
  /// If null, the current time is used.
  ///
  /// **Throws**: [ResponseException] if an error occurs.
  Future<Response<void>> updatePrivateConversationReadDate(Int64 targetId,
      {DateTime? readDate}) async {
    readDate = readDate ?? DateTime.now();
    final n = await _turmsClient.driver.send(UpdateConversationRequest(
        targetId: targetId, readDate: readDate.toInt64()));
    return n.toNullResponse();
  }

  /// Update the read date of the target group conversation.
  ///
  /// Common Scenarios:
  /// * To find the read date of a conversation actively (if no notification is received from the server),
  ///   you can call [queryGroupConversations].
  ///
  /// Authorization:
  /// * If the server property `turms.service.conversation.read-receipt.enabled`
  ///   is false (true by default), throws [ResponseException] with the code [ResponseStatusCode.updatingReadDateIsDisabled].
  /// * If the target group doesn't exist, throws [ResponseException] with the code [ResponseStatusCode.updatingReadDateOfNonexistentGroupConversation].
  /// * If the target group has disabled read receipts, throws [ResponseException] with the code [ResponseStatusCode.updatingReadDateIsDisabledByGroup].
  /// * If the logged-in user is not a member of the target group, throws [ResponseException] with the code [ResponseStatusCode.notGroupMemberToUpdateReadDateOfGroupConversation].
  ///
  /// Notifications:
  /// * If the server property `turms.service.notification.group-conversation-read-date-updated.notify-other-group-members`
  ///   is true (false by default),
  ///   the server will send a read date update notification to all participants actively.
  /// * If the server property `turms.service.notification.group-conversation-read-date-updated.notify-requester-other-online-sessions`
  ///   is true (true by default),
  ///   the server will send a read date update notification to all other online sessions of the logged-in user actively.
  ///
  /// **Params**:
  /// * `groupId`: The target group ID.
  /// * `readDate`: The read date.
  /// If null, the current time is used.
  ///
  /// **Throws**: [ResponseException] if an error occurs.
  Future<Response<void>> updateGroupConversationReadDate(Int64 groupId,
      {DateTime? readDate}) async {
    readDate = readDate ?? DateTime.now();
    final n = await _turmsClient.driver.send(UpdateConversationRequest(
        groupId: groupId, readDate: readDate.toInt64()));
    return n.toNullResponse();
  }

  /// Update the typing status of the target private conversation.
  ///
  /// Authorization:
  /// * If the server property `turms.service.conversation.typing-status.enabled`
  ///   is true (true by default), throws [ResponseException] with the code [ResponseStatusCode.updatingTypingStatusIsDisabled].
  /// * If the logged-in user is not a friend of [targetId], throws [ResponseException] with the code [ResponseStatusCode.notFriendToSendTypingStatus].
  ///
  /// Notifications:
  /// * If the server property `turms.service.conversation.typing-status.enabled`
  ///   is true (true by default),
  ///   the server will send a typing status update notification to the participant actively.
  ///
  /// **Params**:
  /// * `targetId`: The target user ID.
  ///
  /// **Throws**: [ResponseException] if an error occurs.
  Future<Response<void>> updatePrivateConversationTypingStatus(
      Int64 targetId) async {
    final n = await _turmsClient.driver
        .send(UpdateTypingStatusRequest(toId: targetId, isGroupMessage: false));
    return n.toNullResponse();
  }

  /// Update the typing status of the target group conversation.
  ///
  /// Authorization:
  /// * If the server property `turms.service.conversation.typing-status.enabled`
  ///   is true (true by default), throws [ResponseException] with the code [ResponseStatusCode.updatingTypingStatusIsDisabled].
  /// * If the logged-in user is not a member of the target group, throws [ResponseException] with the code [ResponseStatusCode.notGroupMemberToSendTypingStatus].
  ///
  /// Notifications:
  /// * If the server property `turms.service.conversation.typing-status.enabled`
  ///   is true (true by default),
  ///   the server will send a typing status update notification to all participants actively.
  ///
  /// **Params**:
  /// * `groupId`: The target group ID.
  ///
  /// **Throws**: [ResponseException] if an error occurs.
  Future<Response<void>> updateGroupConversationTypingStatus(
      Int64 groupId) async {
    final n = await _turmsClient.driver
        .send(UpdateTypingStatusRequest(toId: groupId, isGroupMessage: true));
    return n.toNullResponse();
  }
}
