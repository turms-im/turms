import 'package:fixnum/fixnum.dart' show Int64;

import '../../turms_client.dart';

class ConversationService {
  final TurmsClient _turmsClient;

  ConversationService(this._turmsClient);

  Future<Response<List<PrivateConversation>>> queryPrivateConversations(
      List<Int64> targetIds) async {
    if (targetIds.isEmpty) {
      return Response.emptyList();
    }
    final n = await _turmsClient.driver
        .send(QueryConversationsRequest(targetIds: targetIds));
    return n.toResponse((data) => data.conversations.privateConversations);
  }

  Future<Response<List<GroupConversation>>> queryGroupConversations(
      List<Int64> groupIds) async {
    if (groupIds.isEmpty) {
      return Response.emptyList();
    }
    final n = await _turmsClient.driver
        .send(QueryConversationsRequest(groupIds: groupIds));
    return n.toResponse((data) => data.conversations.groupConversations);
  }

  Future<Response<void>> updatePrivateConversationReadDate(Int64 targetId,
      {DateTime? readDate}) async {
    readDate = readDate ?? DateTime.now();
    final n = await _turmsClient.driver.send(UpdateConversationRequest(
        targetId: targetId, readDate: readDate.toInt64()));
    return n.toNullResponse();
  }

  Future<Response<void>> updateGroupConversationReadDate(Int64 groupId,
      {DateTime? readDate}) async {
    readDate = readDate ?? DateTime.now();
    final n = await _turmsClient.driver.send(UpdateConversationRequest(
        groupId: groupId, readDate: readDate.toInt64()));
    return n.toNullResponse();
  }

  Future<Response<void>> updatePrivateConversationTypingStatus(
      Int64 targetId) async {
    final n = await _turmsClient.driver
        .send(UpdateTypingStatusRequest(toId: targetId, isGroupMessage: false));
    return n.toNullResponse();
  }

  Future<Response<void>> updateGroupConversationTypingStatus(
      Int64 groupId) async {
    final n = await _turmsClient.driver
        .send(UpdateTypingStatusRequest(toId: groupId, isGroupMessage: true));
    return n.toNullResponse();
  }
}
