import 'dart:typed_data';

import 'package:fixnum/fixnum.dart' show Int64;

import '../../turms_client.dart';
import '../extension/notification_extensions.dart';

typedef MentionedUserIdsParser = Set<Int64> Function(Message message);
typedef MessageListener = void Function(
    Message message, MessageAddition addition);

class MessageService {
  /// Format: `@{userId}`
  /// Example: `@{123}`, `I need to talk with @{123} and @{321}`
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

  /// Add a message listener that will be triggered when a new message arrives.
  /// Note: To listen to notifications excluding messages,
  /// use [NotificationService.addNotificationListener] instead.
  void addMessageListener(MessageListener listener) =>
      _messageListeners.add(listener);

  /// Remove a message listener.
  void removeMessageListener(MessageListener listener) =>
      _messageListeners.remove(listener);

  /// Send a message.
  ///
  /// Common Scenarios:
  /// * A client can call [addMessageListener] to listen to incoming new messages.
  ///
  /// Authorization:
  ///
  /// For private messages,
  /// * If the server property `turms.service.message.allow-send-messages-to-oneself`
  ///   is true (false by default), the logged-in user can send messages to itself.
  ///   Otherwise, throws [ResponseException] with the code [ResponseStatusCode.sendingMessagesToOneselfIsDisabled].
  /// * If the server property `turms.service.message.allow-send-messages-to-stranger`
  ///   is true (true by default), the logged-in user can send messages to strangers
  ///   (has no relationship with the logged-in user).
  /// * If the logged-in user has blocked the target user,
  ///   throws [ResponseException] with the code [ResponseStatusCode.blockedUserSendPrivateMessage].
  ///
  /// For group messages,
  /// * If the logged-in user has blocked the target group,
  ///   throws [ResponseException] with the code [ResponseStatusCode.blockedUserSendGroupMessage].
  /// * If the logged-in user is not a group member, and the group does NOT allow non-members to send messages,
  ///   throws [ResponseException] with the code [ResponseStatusCode.notSpeakableGroupGuestToSendMessage].
  /// * If the logged-in user has been muted,
  ///   throws [ResponseException] with the code [ResponseStatusCode.mutedGroupMemberSendMessage].
  /// * If the target group has been deleted,
  ///   throws [ResponseException] with the code [ResponseStatusCode.sendMessageToInactiveGroup].
  /// * If the target group has been muted,
  ///   throws [ResponseException] with the code [ResponseStatusCode.sendMessageToMutedGroup].
  ///
  /// Notifications:
  /// * If the server property `turms.service.notification.message-created.notify-message-recipients`
  ///   is true (true by default), a new message notification will be sent to the message recipients actively.
  /// * If the server property `turms.service.notification.message-created.notify-requester-other-online-sessions`
  ///   is true (true by default), a new message notification will be sent to all other online sessions of the logged-in user actively.
  ///
  /// **Params**:
  /// * `isGroupMessage`: Whether the message is a group message.
  /// * `targetId`: The target ID.
  /// If [isGroupMessage] is true, the target ID is the group ID.
  /// If [isGroupMessage] is false, the target ID is the user ID.
  /// * `deliveryDate`: The delivery date.
  /// Note that [deliveryDate] will only work if the server property
  /// `turms.service.message.time-type` is `client_time` (`local_server_time` by default).
  /// * `text`: The message text.
  /// [text] can be anything you want. e.g. Markdown, base64 encoded bytes.
  /// Note that if [text] is null, [records] must not be null.
  /// * `records`: The message records.
  /// [records] can be anything you want. e.g. base64 encoded images (it is highly not recommended).
  /// Note that if [records] is null, [text] must not be null.
  /// * `burnAfter`: The burn after the specified time.
  /// Note that Turms server and client do NOT implement the `burn after` feature,
  /// and they just store and pass [burnAfter] between server and clients.
  /// * `preMessageId`: The pre-message ID.
  /// [preMessageId] is mainly used to arrange the order of messages on UI.
  /// If what you want is to ensure every message will not be lost, even if the server crashes,
  /// you can set the server property `turms.service.message.use-conversation-id` to true
  /// (false by default).
  ///
  /// **Returns**: The message ID.
  ///
  /// **Throws**: [ResponseException] if an error occurs.
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

  /// Forward a message.
  /// In other words, create and send a new message based on a existing message
  /// to the target user or group.
  ///
  /// Authorization:
  /// * If the logged-in user is not allowed to view the message with [messageId],
  ///   throws [ResponseException] with the code [ResponseStatusCode.notMessageRecipientOrSenderToForwardMessage].
  ///
  /// Notifications:
  /// * If the server property `turms.service.notification.message-created.notify-message-recipients`
  ///   is true (true by default), a new message notification will be sent to the message recipients actively.
  /// * If the server property `turms.service.notification.message-created.notify-requester-other-online-sessions`
  ///   is true (true by default), a new message notification will be sent to all other online sessions of the logged-in user actively.
  ///
  /// **Params**:
  /// * `messageId`: The message ID for copying.
  /// * `isGroupMessage`: Whether the message is a group message.
  /// * `targetId`: The target ID.
  /// If [isGroupMessage] is true, the target ID is the group ID.
  /// If [isGroupMessage] is false, the target ID is the user ID.
  ///
  /// **Returns**: The message ID.
  ///
  /// **Throws**: [ResponseException] if an error occurs.
  Future<Response<Int64>> forwardMessage(
      Int64 messageId, bool isGroupMessage, Int64 targetId) async {
    final n = await _turmsClient.driver.send(CreateMessageRequest(
        messageId: messageId,
        groupId: isGroupMessage ? targetId : null,
        recipientId: !isGroupMessage ? targetId : null));
    return n.toResponse((data) => data.getLongOrThrow());
  }

  /// Update a sent message.
  ///
  /// Authorization:
  /// * If the server property `turms.service.message.allow-send-messages-to-oneself`
  ///   is true (true by default), the logged-in user can update sent messages.
  ///   Otherwise, throws [ResponseException] with the code [ResponseStatusCode.updatingMessageBySenderIsDisabled].
  /// * If the message is not sent by the logged-in user,
  ///   throws [ResponseException] with the code [ResponseStatusCode.notSenderToUpdateMessage].
  /// * If the message is group message, and is sent by the logged-in user but the group
  ///   has been deleted,
  ///   throws [ResponseException] with the code [ResponseStatusCode.updateMessageOfNonexistentGroup].
  /// * If the message is group message, and the group type has disabled updating messages,
  ///   throws [ResponseException] with the code [ResponseStatusCode.updatingGroupMessageBySenderIsDisabled].
  ///
  /// Notifications:
  /// * If the server property `turms.service.notification.message-updated.notify-message-recipients`
  ///   is true (true by default), a message update notification will be sent to the message recipients actively.
  /// * If the server property `turms.service.notification.message-updated.notify-requester-other-online-sessions`
  ///   is true (true by default), a message update notification will be sent to all other online sessions of the logged-in user actively.
  ///
  /// **Params**:
  /// * `messageId`: The sent message ID.
  /// * `text`: The new message text.
  /// If null, the message text will not be changed.
  /// * `records`: The new message records.
  /// If null, the message records will not be changed.
  ///
  /// **Throws**: [ResponseException] if an error occurs.
  Future<Response<void>> updateSentMessage(Int64 messageId,
      {String? text, List<Uint8List>? records}) async {
    if ([text, records].areAllNull) {
      return Response.nullValue();
    }
    final n = await _turmsClient.driver.send(UpdateMessageRequest(
        messageId: messageId, text: text, records: records));
    return n.toNullResponse();
  }

  /// Find messages.
  ///
  /// **Params**:
  /// * `ids`: The message IDs for querying.
  /// * `areGroupMessages`: Whether the messages are group messages.
  /// If the logged-in user is not a group member,
  /// throws [ResponseException] with the code [ResponseStatusCode.notGroupMemberToQueryGroupMessages].
  /// TODO: guest users of some group types should be able to query messages.
  /// * `areSystemMessages`: Whether the messages are system messages.
  /// * `fromIds`: The from IDs.
  /// If [areGroupMessages] is true, the from ID is the group ID.
  /// If [areGroupMessages] is false, the from ID is the user ID.
  /// * `deliveryDateStart`: The start delivery date for querying.
  /// * `deliveryDateEnd`: The end delivery date for querying.
  /// * `maxCount`: The maximum count for querying.
  /// * `descending`: Whether the messages are sorted in descending order.
  ///
  /// **Returns**: List of messages.
  /// Note that the list only contains messages in which the logged-in user
  /// has permission to view.
  /// If the logged-in user has no permission to view specified messages,
  /// these messages will be filtered out on the server, and no error will be thrown,
  /// except for [ResponseStatusCode.notGroupMemberToQueryGroupMessages] mentioned above.
  ///
  /// **Throws**: [ResponseException] if an error occurs.
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

  /// Find the pair of messages and the total count for each conversation.
  ///
  /// **Params**:
  /// * `ids`: The message IDs for querying.
  /// * `areGroupMessages`: Whether the messages are group messages.
  /// * `areSystemMessages`: Whether the messages are system messages.
  /// If the logged-in user is not a group member,
  /// throws [ResponseException] with the code [ResponseStatusCode.notGroupMemberToQueryGroupMessages].
  /// TODO: guest users of some group types should be able to query messages.
  /// * `fromIds`: The from IDs.
  /// If [areGroupMessages] is true, the from ID is the group ID.
  /// If [areGroupMessages] is false, the from ID is the user ID.
  /// * `deliveryDateStart`: The start delivery date for querying.
  /// * `deliveryDateEnd`: The end delivery date for querying.
  /// * `maxCount`: The maximum count for querying.
  /// * `descending`: Whether the messages are sorted in descending order.
  ///
  /// **Returns**: List of the pair of messages and the total count for each conversation.
  /// Note that the list only contains messages in which the logged-in user
  /// has permission to view.
  /// If the logged-in user has no permission to view specified messages,
  /// these messages will be filtered out on the server, and no error will be thrown,
  /// except for [ResponseStatusCode.notGroupMemberToQueryGroupMessages] mentioned above.
  ///
  /// **Throws**: [ResponseException] if an error occurs.
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

  /// Recall a message.
  ///
  /// Authorization:
  /// * If the server property `turms.service.message.allow-recall-message`
  ///   is true (true by default), the logged-in user can recall sent messages.
  ///   Otherwise, throws [ResponseException] with the code [ResponseStatusCode.recallingMessageIsDisabled].
  /// * If the message does not exist,
  ///   throws [ResponseException] with the code [ResponseStatusCode.recallNonexistentMessage].
  /// * If the message is group message, but the group has been deleted,
  ///   throws [ResponseException] with the code [ResponseStatusCode.recallMessageOfNonexistentGroup].
  ///
  /// Common Scenarios:
  /// * A client can call [addMessageListener] to listen to recalled messages.
  ///   The listener will receive a non-empty [MessageAddition.recalledMessageIds] when a message is recalled.
  ///
  /// **Params**:
  /// * `messageId`: The message ID.
  /// * `recallDate`: The recall date.
  /// If null, the current date will be used.
  ///
  /// **Throws**: [ResponseException] if an error occurs.
  Future<Response<void>> recallMessage(Int64 messageId,
      {DateTime? recallDate}) async {
    final n = await _turmsClient.driver.send(UpdateMessageRequest(
        messageId: messageId,
        recallDate:
            Int64((recallDate ?? DateTime.now()).millisecondsSinceEpoch)));
    return n.toNullResponse();
  }

  /// Check if the mention feature is enabled.
  bool isMentionEnabled() => _mentionedUserIdsParser != null;

  /// Enable the mention feature.
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
