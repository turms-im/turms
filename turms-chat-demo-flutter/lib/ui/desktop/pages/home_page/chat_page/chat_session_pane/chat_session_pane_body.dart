import 'package:collection/collection.dart';
import 'package:fixnum/fixnum.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:intl/intl.dart';

import '../../../../../../domain/conversation/models/conversation.dart';
import '../../../../../../domain/message/models/message_delivery_status.dart';
import '../../../../../../domain/message/models/message_group.dart';
import '../../../../../../domain/message/models/message_type.dart';
import '../../../../../../domain/message/services/message_service.dart';
import '../../../../../../domain/user/models/user.dart';
import '../../../../../../domain/user/services/user_service.dart';
import '../../../../../../domain/user/view_models/logged_in_user_info_view_model.dart';
import '../../../../../../infra/random/random_utils.dart';
import '../../../../../l10n/app_localizations.dart';
import '../../../../../l10n/view_models/app_localizations_view_model.dart';
import '../../../../../l10n/view_models/date_format_view_models.dart';
import '../../../../../themes/index.dart';
import '../../../../components/index.dart';
import '../../../../components/t_menu/t_context_menu.dart';
import '../view_models/selected_conversation_view_model.dart';
import 'message.dart';
import 'message_bubble/message_bubble.dart';

const _loadMoreDistanceThreshold = 8;

class ChatSessionPaneBody extends ConsumerStatefulWidget {
  const ChatSessionPaneBody(this.selectedConversation, {super.key});

  final Conversation selectedConversation;

  @override
  ConsumerState<ChatSessionPaneBody> createState() =>
      _ChatSessionPaneBodyState();
}

const _chatSessionItemLoadingIndicatorId = Int64.MIN_VALUE;
const _chatSessionItemLoadingIndicatorKey =
    ValueKey(_chatSessionItemLoadingIndicatorId);

class _ChatSessionPaneBodyState extends ConsumerState<ChatSessionPaneBody> {
  final _selectableRegionController = TSelectableRegionController();
  final _scrollController = ScrollController();

  bool _isLoading = false;
  bool _isAllMessagesLoaded = false;

  @override
  void initState() {
    super.initState();
    _scrollController.addListener(_onScrolled);
  }

  @override
  void dispose() {
    _scrollController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    final appThemeExtension = context.appThemeExtension;
    final selectedConversation = widget.selectedConversation;
    final messages = selectedConversation.messages;
    return ColoredBox(
        color: appThemeExtension.homePageBackgroundColor,
        child: messages.isEmpty
            ? null
            : _buildMessageBubbles(selectedConversation));
  }

  void _onScrolled() {
    // Hide the context menu when scrolling because:
    // 1. If we let the context menu follow the movable message item,
    // the menu can be scrolled out of the viewport, it is weird.
    // 2. If we make the context menu fixed,
    // it will be confusing to identity which message the context menu is for
    // when the target message is scrolled out of the viewport.
    // As a result, we hide the context menu when scrolling.
    _selectableRegionController.hideContextMenu?.call();
    _loadMoreIfScrollToTop();
  }

  void _loadMoreIfScrollToTop() {
    final position = _scrollController.position;
    if (!_isAllMessagesLoaded &&
        position.maxScrollExtent - position.pixels <
            _loadMoreDistanceThreshold) {
      _loadMoreMessages();
    }
  }

  /// We don't use sealed classes to limit possible values
  /// for better performance (don't need to create new objects).
  List<_ChatSessionItem> _generateItems(List<ChatMessage> messages) {
    final items = <_ChatSessionItem>[const _ChatSessionItemLoadingIndicator()];
    final messageCount = messages.length;
    if (messageCount == 0) {
      return items;
    }
    ChatMessage? lastMessage;
    MessageGroup? lastMessageGroup;
    assert(() {
      final sortedMessages = List<ChatMessage>.from(messages)
        ..sort(
          (a, b) {
            final result = a.timestamp.compareTo(b.timestamp);
            if (result == 0) {
              // Used to ensure a stable sort.
              return a.messageId.compareTo(b.messageId);
            }
            return result;
          },
        );
      return const ListEquality<ChatMessage>().equals(sortedMessages, messages);
    }(),
        "The messages should have been sorted by date so that we don't need to sort them again and again when building the list");
    final lastMessageIndex = messageCount - 1;
    for (var i = 0; i < messageCount; i++) {
      final message = messages[i];
      final timestamp = message.timestamp;
      if (lastMessage == null) {
        items.add(_ChatSessionItemDaySeparator(timestamp));
        if (i == lastMessageIndex) {
          items.add(_ChatSessionItemMessage(message));
          return items;
        }
      } else {
        final lastMessageTimestamp = lastMessage.timestamp;
        assert(lastMessageTimestamp.timeZoneName == timestamp.timeZoneName,
            'The timestamp of messages should have the same time zone name');
        if (DateUtils.isSameDay(lastMessageTimestamp, timestamp)) {
          if (lastMessage.type == MessageType.text &&
              message.type == MessageType.text &&
              lastMessage.senderId == message.senderId &&
              lastMessageTimestamp.hour == timestamp.hour &&
              lastMessageTimestamp.minute == timestamp.minute) {
            if (lastMessageGroup == null) {
              lastMessageGroup = MessageGroup([lastMessage, message]);
              items.add(_ChatSessionItemMessageGroup(lastMessageGroup));
            } else {
              lastMessageGroup.addMessage(message);
            }
            if (i == lastMessageIndex) {
              return items;
            }
          } else {
            if (lastMessageGroup == null ||
                lastMessage.messageId !=
                    lastMessageGroup.messages.last.messageId) {
              items.add(_ChatSessionItemMessage(lastMessage));
            }
            lastMessageGroup = null;
          }
        } else {
          items.add(_ChatSessionItemDaySeparator(timestamp));
          if (lastMessageGroup == null ||
              lastMessage.messageId !=
                  lastMessageGroup.messages.last.messageId) {
            items.add(_ChatSessionItemMessage(lastMessage));
          }
          lastMessageGroup = null;
        }
        if (i == lastMessageIndex) {
          if (lastMessageGroup == null ||
              message.messageId != lastMessageGroup.messages.last.messageId) {
            items.add(_ChatSessionItemMessage(message));
          }
          return items;
        }
      }
      lastMessage = message;
    }
    throw AssertionError('Unreachable code reached');
  }

  Widget _buildMessageBubbles(Conversation conversation) {
    final appLocalizations = ref.watch(appLocalizationsViewModel);
    final loggedInUser = ref.watch(loggedInUserViewModel)!;
    final dateFormat = ref.watch(dateFormatViewModel_yMd);
    final items = _generateItems(conversation.messages);
    final itemCount = items.length;
    final itemIdToIndex = {for (var i = 0; i < itemCount; i++) items[i].id: i};
    final yesterday = DateTime.now().subtract(const Duration(days: 1));
    return TSelectionArea(
      controller: _selectableRegionController,
      contextMenuBuilder: buildContextMenuForTSelectableRegion,
      child: TSelectionContainer(
        visible: false,
        child: LayoutBuilder(
          // TODO: Use CustomScrollView
          builder: (context, constraints) => ListView.builder(
            padding: Sizes.paddingV8H16,
            controller: _scrollController,
            reverse: true,
            itemCount: itemCount,
            findChildIndexCallback: (key) {
              final index = itemIdToIndex[(key as ValueKey<Int64>).value];
              if (index == null) {
                return null;
              }
              return _getActualIndex(itemCount, index);
            },
            itemBuilder: (context, index) {
              final actualIndex = _getActualIndex(itemCount, index);
              final item = items[actualIndex];
              return switch (item) {
                _ChatSessionItemLoadingIndicator() => _buildLoadingIndicator(),
                _ChatSessionItemDaySeparator(:final datetime) =>
                  _buildDaySeparator(
                      item, yesterday, datetime, appLocalizations, dateFormat),
                _ChatSessionItemMessage(:final message) => _buildMessages(
                    conversation,
                    [message],
                    loggedInUser,
                    item,
                    constraints.maxWidth),
                _ChatSessionItemMessageGroup(:final messageGroup) =>
                  _buildMessages(conversation, messageGroup.messages,
                      loggedInUser, item, constraints.maxWidth)
              };
            },
          ),
        ),
      ),
    );
  }

  int _getActualIndex(int itemCount, int index) => itemCount - index - 1;

  SingleChildRenderObjectWidget _buildLoadingIndicator() {
    if (_isLoading) {
      return const Padding(
        key: _chatSessionItemLoadingIndicatorKey,
        padding: Sizes.paddingV8,
        child: CupertinoActivityIndicator(radius: 8),
      );
    } else if (_isAllMessagesLoaded) {
      return const Center(
        key: _chatSessionItemLoadingIndicatorKey,
        child: Text('No more messages'),
      );
    } else {
      // TODO
      return const Center(
        key: _chatSessionItemLoadingIndicatorKey,
        child: Text('Load More Messages'),
      );
    }
  }

  Padding _buildDaySeparator(
          _ChatSessionItemDaySeparator item,
          DateTime yesterday,
          DateTime datetime,
          AppLocalizations appLocalizations,
          DateFormat dateFormat) =>
      Padding(
        key: ValueKey(item.id),
        padding: Sizes.paddingV16,
        child: Center(
          child: DecoratedBox(
            decoration: const BoxDecoration(
                color: Color.fromARGB(255, 218, 218, 218),
                borderRadius: Sizes.borderRadiusCircular4),
            child: Padding(
              padding: Sizes.paddingV2H4,
              child: Text(
                DateUtils.isSameDay(yesterday, datetime)
                    ? appLocalizations.yesterday
                    : dateFormat.format(datetime),
                style: const TextStyle(color: Colors.white, fontSize: 12),
              ),
            ),
          ),
        ),
      );

  MessageBubble _buildMessages(
      Conversation conversation,
      List<ChatMessage> messages,
      User loggedInUser,
      _ChatSessionItem item,
      double availableWidth) {
    final message = messages.first;
    final user = _getMessageSender(conversation, message, loggedInUser);
    return MessageBubble(
      key: ValueKey(item.id),
      currentUser: loggedInUser,
      sender: user,
      messages: messages,
      availableWidth: availableWidth,
      onRetry: message.sentByMe
          ? () async {
              await _sendMessage(conversation, message);
            }
          : null,
    );
  }

  Future<void> _sendMessage(
      Conversation conversation, ChatMessage message) async {
    final now = DateTime.now();
    final previousMessageId = message.messageId;
    final fakeMessageId = -RandomUtils.nextUniquePositiveInt64();
    // Note that: the timestamp may be different from the one the recipients received.
    // TODO: Use the server time for reference,
    //  especially when the device time is not correct.
    final DateTime tempTimestamp;
    final lastMessageTimestamp = conversation.messages.lastOrNull?.timestamp;
    if (lastMessageTimestamp == null ||
        lastMessageTimestamp.compareTo(now) < 0) {
      tempTimestamp = now;
    } else {
      tempTimestamp = lastMessageTimestamp.add(const Duration(milliseconds: 1));
    }
    message = message.copyWith(
        messageId: fakeMessageId,
        timestamp: tempTimestamp,
        status: MessageDeliveryStatus.delivering);
    final selectedConversationController =
        ref.read(selectedConversationViewModel.notifier)
          ..removeMessage(previousMessageId)
          ..addMessage(message);
    setState(() {});

    final sentMessage = await ref
        .read(messageServiceProvider)!
        .sendMessage(message.text!, message);

    // TODO: handle the case when the controller has already been changed.
    selectedConversationController.replaceMessage(
        fakeMessageId,
        message.copyWith(
            messageId: sentMessage.messageId,
            status: sentMessage.status,
            // Note that this will update the timestamp UI of the message
            // if the received timestamp is different from the fake one,
            // which is expected to ensure the timestamp is consistent with the server and recipients.
            timestamp: sentMessage.timestamp));
  }

  User _getMessageSender(
      Conversation conversation, ChatMessage message, User loggedInUser) {
    if (message.sentByMe) {
      return loggedInUser;
    } else if (conversation is UserConversation) {
      return conversation.contact;
    } else {
      return ref.read(userServiceProvider)!.queryUsers(message.senderId);
    }
  }

  Future<void> _loadMoreMessages() async {
    if (_isLoading) {
      return;
    }
    _isLoading = true;
    setState(() {});
    final messages =
        await ref.read(messageServiceProvider)!.queryMoreMessages();
    // TODO: add messages to the conversation.
    _isLoading = false;
    if (messages.isEmpty) {
      _isAllMessagesLoaded = true;
    }
    if (!mounted) {
      return;
    }
    setState(() {});
  }

  void _scrollToBottom() {
    _scrollController.animateTo(
      _scrollController.position.minScrollExtent,
      duration: const Duration(milliseconds: 200),
      curve: Curves.easeOut,
    );
  }
}

sealed class _ChatSessionItem {
  const _ChatSessionItem(this.id);

  final Int64 id;
}

class _ChatSessionItemLoadingIndicator extends _ChatSessionItem {
  const _ChatSessionItemLoadingIndicator()
      : super(_chatSessionItemLoadingIndicatorId);
}

class _ChatSessionItemDaySeparator extends _ChatSessionItem {
  _ChatSessionItemDaySeparator(this.datetime)
      : super(Int64(-datetime.millisecondsSinceEpoch));

  final DateTime datetime;
}

class _ChatSessionItemMessage extends _ChatSessionItem {
  _ChatSessionItemMessage(this.message) : super(message.messageId);

  final ChatMessage message;
}

class _ChatSessionItemMessageGroup extends _ChatSessionItem {
  _ChatSessionItemMessageGroup(this.messageGroup)
      : super((-messageGroup.messages.first.messageId) | (Int64(1) << 62));

  final MessageGroup messageGroup;
}
