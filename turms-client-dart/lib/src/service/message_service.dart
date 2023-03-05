import 'dart:typed_data';

import 'package:fixnum/fixnum.dart' show Int64;

import '../../turms_client.dart';
import '../extension/notification_extensions.dart';

typedef MentionedUserIdsParser = Set<Int64> Function(Message message);
typedef MessageListener = void Function(
    Message message, MessageAddition addition);

class MessageService {
  /// Format: "@{userId}"
  /// Example: "@{123}", "I need to talk with @{123} and @{321}"
  static final RegExp _defaultMentionedUserIdsParserRegex =
      RegExp(r'@{(\d+?)}', multiLine: true);

  final TurmsClient _turmsClient;
  MentionedUserIdsParser? _mentionedUserIdsParser;
  final List<MessageListener> _messageListeners = [];

  MessageService(this._turmsClient) {
    _turmsClient.driver.addNotificationListener((notification) {
      if (_messageListeners.isNotEmpty &&
          notification.hasRelayedRequest() &&
          notification.relayedRequest.hasCreateMessageRequest()) {
        final request = notification.relayedRequest.createMessageRequest;
        final message =
            _createMessageRequest2Message(notification.requesterId, request);
        final addition = _parseMessageAddition(message);
        for (final listener in _messageListeners) {
          listener.call(message, addition);
        }
      }
    });
  }

  Set<Int64> _defaultMentionedUserIdsParser(Message message) {
    if (!message.hasText()) {
      return {};
    }
    final userIds = <Int64>{};
    for (final matches
        in _defaultMentionedUserIdsParserRegex.allMatches(message.text)) {
      try {
        userIds.add(Int64.parseInt(matches.group(1)!));
      } on FormatException catch (_) {}
    }
    return userIds;
  }

  void addMessageListener(MessageListener listener) =>
      _messageListeners.add(listener);

  void removeMessageListener(MessageListener listener) =>
      _messageListeners.remove(listener);

  Future<Response<Int64>> sendMessage(bool isGroupMessage, Int64 targetId,
      {DateTime? deliveryDate,
      String? text,
      List<Uint8List>? records,
      int? burnAfter,
      Int64? preMessageId}) async {
    if (text == null && (records?.isEmpty ?? true)) {
      throw ResponseException(
          code: ResponseStatusCode.illegalArgument,
          reason: '"text" and "records" must not all be null');
    }
    final n = await _turmsClient.driver.send(CreateMessageRequest(
        groupId: isGroupMessage ? targetId : null,
        recipientId: !isGroupMessage ? targetId : null,
        deliveryDate: deliveryDate?.toInt64(),
        text: text,
        records: records,
        burnAfter: burnAfter,
        preMessageId: preMessageId));
    return n.toResponse((data) => data.getLongOrThrow());
  }

  Future<Response<Int64>> forwardMessage(
      Int64 messageId, bool isGroupMessage, Int64 targetId) async {
    final n = await _turmsClient.driver.send(CreateMessageRequest(
        messageId: messageId,
        groupId: isGroupMessage ? targetId : null,
        recipientId: !isGroupMessage ? targetId : null));
    return n.toResponse((data) => data.getLongOrThrow());
  }

  Future<Response<void>> updateSentMessage(Int64 messageId,
      {String? text, List<Uint8List>? records}) async {
    if ([text, records].areAllNull) {
      return Response.nullValue();
    }
    final n = await _turmsClient.driver.send(UpdateMessageRequest(
        messageId: messageId, text: text, records: records));
    return n.toNullResponse();
  }

  Future<Response<List<Message>>> queryMessages(
      {Set<Int64>? ids,
      bool? areGroupMessages,
      bool? areSystemMessages,
      Set<Int64>? fromIds,
      DateTime? deliveryDateStart,
      DateTime? deliveryDateEnd,
      int maxCount = 50,
      bool? descending}) async {
    final n = await _turmsClient.driver.send(QueryMessagesRequest(
        ids: ids,
        areGroupMessages: areGroupMessages,
        areSystemMessages: areSystemMessages,
        fromIds: fromIds,
        deliveryDateStart: deliveryDateStart?.toInt64(),
        deliveryDateEnd: deliveryDateEnd?.toInt64(),
        maxCount: maxCount,
        withTotal: false,
        descending: descending != null && descending ? true : null));
    return n.toResponse((data) => data.messages.messages);
  }

  Future<Response<List<MessagesWithTotal>>> queryMessagesWithTotal(
      {Set<Int64>? ids,
      bool? areGroupMessages,
      bool? areSystemMessages,
      Set<Int64>? fromIds,
      DateTime? deliveryDateStart,
      DateTime? deliveryDateEnd,
      int maxCount = 1,
      bool? descending}) async {
    final n = await _turmsClient.driver.send(QueryMessagesRequest(
        ids: ids,
        areGroupMessages: areGroupMessages,
        areSystemMessages: areSystemMessages,
        fromIds: fromIds,
        deliveryDateStart: deliveryDateStart?.toInt64(),
        deliveryDateEnd: deliveryDateEnd?.toInt64(),
        maxCount: maxCount,
        withTotal: true,
        descending: descending != null && descending ? true : null));
    return n
        .toResponse((data) => data.messagesWithTotalList.messagesWithTotalList);
  }

  Future<Response<void>> recallMessage(Int64 messageId,
      {DateTime? recallDate}) async {
    final n = await _turmsClient.driver.send(UpdateMessageRequest(
        messageId: messageId,
        recallDate:
            Int64((recallDate ?? DateTime.now()).millisecondsSinceEpoch)));
    return n.toNullResponse();
  }

  bool isMentionEnabled() => _mentionedUserIdsParser != null;

  void enableMention({MentionedUserIdsParser? mentionedUserIdsParser}) {
    _mentionedUserIdsParser = mentionedUserIdsParser ??
        _mentionedUserIdsParser ??
        _defaultMentionedUserIdsParser;
  }

  static Uint8List generateLocationRecord(double latitude, double longitude,
          {Map<String, String>? details}) =>
      UserLocation(latitude: latitude, longitude: longitude, details: details)
          .writeToBuffer();

  static Uint8List generateAudioRecordByDescription(String url,
          {int? duration, String? format, int? size}) =>
      AudioFile(
          description: AudioFile_Description(
        url: url,
        duration: duration,
        format: format,
        size: size,
      )).writeToBuffer();

  static Uint8List generateAudioRecordByData(Uint8List data) =>
      AudioFile(data: data).writeToBuffer();

  static Uint8List generateVideoRecordByDescription(String url,
          {int? duration, String? format, int? size}) =>
      VideoFile(
          description: VideoFile_Description(
        url: url,
        duration: duration,
        format: format,
        size: size,
      )).writeToBuffer();

  static Uint8List generateVideoRecordByData(Uint8List data) =>
      VideoFile(data: data).writeToBuffer();

  static Uint8List generateImageRecordByData(Uint8List data) =>
      ImageFile(data: data).writeToBuffer();

  static Uint8List generateImageRecordByDescription(String url,
          {int? fileSize, int? imageSize, bool? original}) =>
      ImageFile(
              description: ImageFile_Description(
                  url: url,
                  fileSize: fileSize,
                  imageSize: imageSize,
                  original: original))
          .writeToBuffer();

  static Uint8List generateFileRecordByDate(Uint8List data) =>
      File(data: data).writeToBuffer();

  static Uint8List generateFileRecordByDescription(String url,
          {String? format, int? size}) =>
      File(description: File_Description(url: url, format: format, size: size))
          .writeToBuffer();

  MessageAddition _parseMessageAddition(Message message) {
    final mentionedUserIds = _mentionedUserIdsParser?.call(message) ?? {};
    final isMentioned =
        mentionedUserIds.contains(_turmsClient.userService.userInfo?.userId);
    final records = message.records;
    var systemMessageType = BuiltinSystemMessageType.normal;
    final recalledMessageIds = <Int64>{};
    if (message.isSystemMessage && records.isNotEmpty) {
      final bytes = records[0];
      if (bytes.isNotEmpty) {
        systemMessageType = BuiltinSystemMessageType.values
            .firstWhere((element) => element.index == bytes[0]);
        if (systemMessageType == BuiltinSystemMessageType.recallMessage) {
          final size = records.length;
          for (var i = 1; i < size; i++) {
            // Note that it will be truncated into the range of
            // -2^53 and 2^53 in JavaScript.
            final id = ByteData.view(Uint8List.fromList(records[i]).buffer)
                .getInt64(0);
            recalledMessageIds.add(Int64(id));
          }
        }
      }
    }
    return MessageAddition(isMentioned, mentionedUserIds, recalledMessageIds);
  }

  Message _createMessageRequest2Message(
          Int64 requesterId, CreateMessageRequest request) =>
      Message(
          id: request.messageId,
          isSystemMessage: request.isSystemMessage,
          deliveryDate: request.deliveryDate,
          text: request.text,
          records: request.records,
          senderId: requesterId,
          groupId: request.groupId,
          recipientId: request.recipientId);
}
