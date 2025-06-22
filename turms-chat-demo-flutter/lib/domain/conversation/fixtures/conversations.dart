import 'dart:math';

import 'package:fixnum/fixnum.dart';

import '../../../infra/random/random_utils.dart';
import '../../../ui/desktop/pages/home_page/chat_page/chat_session_pane/message.dart';
import '../../common/fixtures/fixtures.dart';
import '../../message/models/message_delivery_status.dart';
import '../../user/fixtures/contacts.dart';
import '../../user/models/index.dart';
import '../models/conversation.dart';

// We prefer the same output for each run,
// so we use the same seed.
final _random = Random(123456789);

const _deliveryStatuses = MessageDeliveryStatus.values;

extension FixturesExtensions on Fixtures {
  List<Conversation> getConversations(User loggedInUser) {
    final loggedInUserId = loggedInUser.userId;
    var messageId = Int64.ZERO;
    final now = DateTime.now();
    return getContacts(loggedInUser).map((contact) {
      switch (contact) {
        case UserContact():
          final timestamps = <DateTime>[];
          final rawMessages = userContactIdToMessages[contact.userId]!;
          final count = rawMessages.length;
          var date = now;
          for (var i = 0; i < count; i++) {
            date = date.subtract(
              Duration(seconds: _random.nextInt(60 * 60 * 24 * 30)),
            );
            timestamps.add(date);
          }
          return UserConversation(
            contact: contact,
            unreadMessageCount: rawMessages.isEmpty
                ? 0
                : _random.nextInt(rawMessages.length),
            messages: rawMessages.indexed.map((item) {
              final (messageIndex, message) = item;
              final sentByMe = _random.nextBool();
              return ChatMessage.parse(
                text: message,
                messageId: --messageId,
                senderId: sentByMe ? loggedInUserId : contact.userId,
                sentByMe: sentByMe,
                recipientId: sentByMe ? contact.userId : loggedInUserId,
                isGroupMessage: false,
                timestamp: timestamps[count - messageIndex - 1],
                status: sentByMe
                    ? _deliveryStatuses[_random.nextInt(
                        _deliveryStatuses.length,
                      )]
                    : MessageDeliveryStatus.delivered,
              );
            }).toList(),
          );
        case GroupContact():
          final memberIds = contact.members
              .map((member) => member.userId)
              .toList();
          final memberCount = memberIds.length;
          final maxMessageCount = RandomUtils.nextInt() % 20;
          final messages = <ChatMessage>[];
          var date = now;
          final timestamps = <DateTime>[];
          for (var i = 0; i < maxMessageCount; i++) {
            date = date.subtract(
              Duration(seconds: _random.nextInt(60 * 60 * 24 * 30)),
            );
            timestamps.add(date);
          }
          for (var i = 0; i < maxMessageCount; i++) {
            final memberId = memberIds[RandomUtils.nextInt() % memberCount];
            final rawMessages = userContactIdToMessages[memberId] ?? [];
            final messageCount = rawMessages.length;
            if (messageCount == 0) {
              continue;
            }
            final rawMessage =
                rawMessages[RandomUtils.nextInt() % messageCount];
            messages.add(
              ChatMessage.parse(
                text: rawMessage,
                messageId: --messageId,
                groupId: contact.groupId,
                senderId: memberId,
                sentByMe: loggedInUserId == memberId,
                isGroupMessage: true,
                timestamp: timestamps[maxMessageCount - i - 1],
                status: MessageDeliveryStatus.delivered,
              ),
            );
          }
          return GroupConversation(
            contact: contact,
            unreadMessageCount: messages.isEmpty
                ? 0
                : _random.nextInt(messages.length),
            messages: messages,
          );
        case SystemContact():
          throw UnimplementedError();
      }
    }).toList();
  }
}
