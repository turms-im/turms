import 'package:fixnum/fixnum.dart' show Int64;

import '../../turms_client.dart';
import '../extension/date_time_extensions.dart';
import '../model/model/conversation/group_conversation.pb.dart';
import '../model/model/conversation/private_conversation.pb.dart';
import '../model/request/conversation/query_conversations_request.pb.dart';
import '../model/request/conversation/update_conversation_request.pb.dart';
import '../model/request/conversation/update_typing_status_request.pb.dart';

class ConversationService {
  final TurmsClient _turmsClient;

  ConversationService(this._turmsClient);

  Future<List<PrivateConversation>> queryPrivateConversations(
      List<Int64> targetIds) async {
    if (targetIds.isEmpty) {
      return [];
    }
    final n = await _turmsClient.driver
        .send(QueryConversationsRequest(targetIds: targetIds));
    return n.data.conversations.privateConversations;
  }

  Future<List<GroupConversation>> queryGroupConversations(
      List<Int64> groupIds) async {
    if (groupIds.isEmpty) {
      return [];
    }
    final n = await _turmsClient.driver
        .send(QueryConversationsRequest(groupIds: groupIds));
    return n.data.conversations.groupConversations;
  }

  Future<void> updatePrivateConversationReadDate(Int64 targetId,
      {DateTime? readDate}) async {
    readDate = readDate ?? DateTime.now();
    await _turmsClient.driver.send(UpdateConversationRequest(
        targetId: targetId, readDate: readDate.toInt64()));
  }

  Future<void> updateGroupConversationReadDate(Int64 groupId,
      {DateTime? readDate}) async {
    readDate = readDate ?? DateTime.now();
    await _turmsClient.driver.send(UpdateConversationRequest(
        groupId: groupId, readDate: readDate.toInt64()));
  }

  Future<void> updatePrivateConversationTypingStatus(Int64 targetId) async {
    await _turmsClient.driver
        .send(UpdateTypingStatusRequest(toId: targetId, isGroupMessage: false));
  }

  Future<void> updateGroupConversationTypingStatus(Int64 groupId) async {
    await _turmsClient.driver
        .send(UpdateTypingStatusRequest(toId: groupId, isGroupMessage: true));
  }
}
