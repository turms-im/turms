import 'dart:typed_data';

import 'package:fixnum/fixnum.dart';

import '../../../../../../domain/message/models/message_delivery_status.dart';
import '../../../../../../domain/message/models/message_type.dart';
import '../../../../../../domain/message/services/message_service.dart';
import '../../../../../../infra/sqlite/user_message_database.dart';

class ChatMessage {
  factory ChatMessage.fromMessageTableData(
      MessageTableData data, Int64 loggedInUserId) {
    final senderId = data.senderId.toInt64();
    final isGroupMessage = data.isGroupMessage;
    final contactId = data.contactId;
    return ChatMessage.parse(
        text: data.txt,
        records: data.records,
        messageId: data.id.toInt64(),
        groupId: isGroupMessage ? contactId : null,
        recipientId: isGroupMessage ? null : contactId,
        senderId: senderId,
        sentByMe: senderId == loggedInUserId,
        isGroupMessage: isGroupMessage,
        timestamp: data.createdDate,
        // We only store sent messages in the database,
        // so we hardcode it to keep the logic simple.
        status: MessageDeliveryStatus.delivered);
  }

  factory ChatMessage.parse({
    String? text,
    List<Uint8List>? records,
    required Int64 messageId,
    Int64? groupId,
    Int64? recipientId,
    required Int64 senderId,
    required bool sentByMe,
    required bool isGroupMessage,
    required DateTime timestamp,
    required MessageDeliveryStatus status,
  }) {
    final info = MessageService.parseMessageInfo(text);
    return ChatMessage(
      type: info.type,
      messageId: messageId,
      text: text,
      records: records,
      groupId: groupId,
      recipientId: recipientId,
      senderId: senderId,
      sentByMe: sentByMe,
      isGroupMessage: isGroupMessage,
      timestamp: timestamp,
      status: status,
      originalUrl: info.originalUrl,
      originalWidth: info.originalWidth,
      originalHeight: info.originalHeight,
      mentionAll: info.mentionAll ?? false,
      mentionedUserIds: info.mentionedUserIds ?? const {},
    );
  }

  const ChatMessage(
      {required this.type,
      required this.messageId,
      this.groupId,
      this.recipientId,
      required this.senderId,
      required this.sentByMe,
      required this.isGroupMessage,
      this.text,
      this.records,
      required this.timestamp,
      required this.status,
      required this.mentionAll,
      required this.mentionedUserIds,
      this.originalUrl,
      this.originalWidth,
      this.originalHeight});

  final MessageType type;
  final Int64 messageId;
  final Int64? groupId;
  final Int64? recipientId;
  final Int64 senderId;
  final bool sentByMe;
  final bool isGroupMessage;
  final String? text;
  final List<Uint8List>? records;

  // final List<ChatMessageSpan> spans;
  final DateTime timestamp;
  final MessageDeliveryStatus status;

  final bool mentionAll;
  final Set<Int64> mentionedUserIds;

  final String? originalUrl;
  final double? originalWidth;
  final double? originalHeight;

  ChatMessage copyWith({
    MessageType? type,
    Int64? messageId,
    Int64? groupId,
    Int64? recipientId,
    Int64? senderId,
    bool? sentByMe,
    bool? isGroupMessage,
    String? text,
    List<Uint8List>? records,
    DateTime? timestamp,
    MessageDeliveryStatus? status,
    bool? mentionAll,
    Set<Int64>? mentionedUserIds,
    String? originalUrl,
    double? originalWidth,
    double? originalHeight,
  }) =>
      ChatMessage(
        type: type ?? this.type,
        messageId: messageId ?? this.messageId,
        groupId: groupId ?? this.groupId,
        recipientId: recipientId ?? this.recipientId,
        senderId: senderId ?? this.senderId,
        sentByMe: sentByMe ?? this.sentByMe,
        isGroupMessage: isGroupMessage ?? this.isGroupMessage,
        text: text ?? this.text,
        records: records ?? this.records,
        timestamp: timestamp ?? this.timestamp,
        status: status ?? this.status,
        mentionAll: mentionAll ?? this.mentionAll,
        mentionedUserIds: mentionedUserIds ?? this.mentionedUserIds,
        originalUrl: originalUrl ?? this.originalUrl,
        originalWidth: originalWidth ?? this.originalWidth,
        originalHeight: originalHeight ?? this.originalHeight,
      );
}

enum ChatMessageSpanType { plain, emoji }

class ChatMessageSpan {
  const ChatMessageSpan({
    required this.type,
    required this.text,
  });

  final ChatMessageSpanType type;
  final String text;
}
