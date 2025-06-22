import 'package:fixnum/fixnum.dart';

import '../../../infra/collection/list_holder.dart';
import '../../../ui/desktop/pages/home_page/chat_page/chat_session_pane/message.dart';
import '../../user/models/contact.dart';

part './group_conversation.dart';

part './private_conversation.dart';

part './user_conversation.dart';

part './system_conversation.dart';

sealed class Conversation {
  factory Conversation.from({
    required Contact contact,
    required List<ChatMessage> messages,
  }) => switch (contact) {
    UserContact() => UserConversation(contact: contact, messages: messages),
    GroupContact() => GroupConversation(contact: contact, messages: messages),
    SystemContact() => SystemConversation(contact: contact, messages: messages),
  };

  Conversation({
    required this.id,
    required this.messages,
    this.unreadMessageCount = 0,
    this.draft,
  });

  final IntListHolder id;

  /// Note that the messages should be sorted by timestamp in ascending order.
  final List<ChatMessage> messages;
  int unreadMessageCount;
  String? draft;

  abstract final Contact contact;

  bool hasSameContact(Contact contact);

  static IntListHolder generateId({
    Int64? userId,
    Int64? groupId,
    SystemContactType? systemContactType,
  }) {
    assert(
      (userId != null) ^ (groupId != null) ^ (systemContactType != null),
      'Only one parameter should be not null',
    );
    final Int64 contactId;
    final int idFlag;
    if (userId != null) {
      contactId = userId;
      idFlag = 0;
    } else if (groupId != null) {
      contactId = groupId;
      idFlag = 1;
    } else {
      contactId = Int64(systemContactType!.id);
      idFlag = 2;
    }
    return ListHolder(
      List<int>.filled(9, 0)
        ..setAll(0, contactId.toInt64().toBytes())
        ..[8] = idFlag,
    );
  }
}
