import 'package:fixnum/fixnum.dart';
import 'package:flutter/material.dart';
import 'package:flutter/rendering.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:intl/intl.dart';

import '../../../../../../../domain/conversation/models/conversation.dart';
import '../../../../../../../domain/message/models/message_type.dart';
import '../../../../../../../domain/message/services/message_service.dart';
import '../../../../../../../domain/user/models/contact.dart';
import '../../../../../../../domain/user/models/group_member.dart';
import '../../../../../../../domain/user/view_models/logged_in_user_info_view_model.dart';
import '../../../../../../../infra/data/t_async_data.dart';
import '../../../../../../l10n/app_localizations.dart';
import '../../../../../../l10n/view_models/app_localizations_view_model.dart';
import '../../../../../../l10n/view_models/date_format_view_models.dart';
import '../../../../../../themes/app_theme_extension.dart';
import '../../../../../../themes/sizes.dart';
import '../../../../../components/index.dart';
import '../message.dart';

const searchResultLimit = 20;

class SingleChatHistoryPage extends ConsumerStatefulWidget {
  const SingleChatHistoryPage({super.key, required this.conversation});

  final Conversation conversation;

  @override
  ConsumerState<SingleChatHistoryPage> createState() =>
      _SingleChatHistoryPageState();
}

class _SingleChatHistoryPageState extends ConsumerState<SingleChatHistoryPage>
    with SingleTickerProviderStateMixin {
  late ScrollController _scrollController;
  List<ChatMessage> _foundMessages = [];
  TAsyncData<List<ChatMessage>> _loadedMessagesData = const TAsyncData();

  Int64? _loggedInUserId;

  String? _searchConditionText;
  MessageType? _searchConditionMessageType;

  Conversation? _lastConversation;
  ChatMessage? _lastFoundMessage;
  bool _hasMoreMessages = false;
  Future<void>? _currentFindMessagesTask;

  @override
  void initState() {
    super.initState();
    _scrollController = ScrollController()
      ..addListener(() async {
        final position = _scrollController.position;
        if (position.userScrollDirection != ScrollDirection.reverse) {
          return;
        }
        if (position.atEdge) {
          final currentLoadTask = _currentFindMessagesTask;
          if (currentLoadTask != null) {
            await currentLoadTask;
          } else if (_hasMoreMessages) {
            await _findMoreMessages();
          }
          final foundMessages = _foundMessages;
          if (foundMessages.length > (_loadedMessagesData.value?.length ?? 0)) {
            _loadedMessagesData = TAsyncData(value: foundMessages.toList());
            if (mounted) {
              setState(() {});
            }
          }
        } else if (position.maxScrollExtent - position.pixels < 64 * 3 &&
            _hasMoreMessages &&
            _currentFindMessagesTask == null) {
          await _findMoreMessages();
        }
      });
  }

  @override
  void dispose() {
    _scrollController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    final appLocalizations = ref.watch(appLocalizationsViewModel);
    _loggedInUserId = ref.watch(loggedInUserViewModel)!.userId;
    final appThemeExtension = context.appThemeExtension;
    return SizedBox(
      width: Sizes.dialogWidthMedium,
      height: Sizes.dialogHeightMedium,
      child: Column(
        children: [
          Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: [
              Flexible(
                child: Padding(
                  padding: const EdgeInsets.symmetric(horizontal: 12),
                  child: Text(
                    widget.conversation.contact.name,
                    style: appThemeExtension.dialogTitleTextStyleMedium,
                    overflow: TextOverflow.ellipsis,
                    maxLines: 1,
                  ),
                ),
              ),
              const TTitleBar(
                usePositioned: false,
                displayCloseOnly: true,
                popOnCloseTapped: true,
              ),
            ],
          ),
          Sizes.sizedBoxH8,
          Padding(
            padding: const EdgeInsets.symmetric(horizontal: 24),
            child: TSearchBar(
              hintText: appLocalizations.search,
              autofocus: true,
              keepFocusOnSubmit: true,
              onChanged: _search,
              onSubmitted: _search,
              debounceTimeout: const Duration(milliseconds: 500),
            ),
          ),
          Sizes.sizedBoxH12,
          Flexible(
            child: _buildSearchResultTabView(),
          ),
        ],
      ),
    );
  }

  Widget _buildSearchResultTabView() {
    assert(!_loadedMessagesData.isLoading,
        '_foundMessagesData must not be loading');
    final loadedMessages = _loadedMessagesData.value;
    if (loadedMessages == null) {
      return const TEmpty();
    } else if (loadedMessages.isEmpty) {
      return const TEmptyResult();
    }
    final appLocalizations = ref.watch(appLocalizationsViewModel);
    // ignore: non_constant_identifier_names
    final dateFormat_yMdjm = ref.watch(dateFormatViewModel_yMdjm);
    // ignore: non_constant_identifier_names
    final dateFormat_Mdjm = ref.watch(dateFormatViewModel_Mdjm);
    // ignore: non_constant_identifier_names
    final dateFormat_jm = ref.watch(dateFormatViewModel_jm);
    final messageIdToIndex = {
      for (final (index, message) in loadedMessages.indexed)
        message.messageId: index
    };
    final contact = widget.conversation.contact;
    final groupMemberIdToMember = contact is GroupContact
        ? Map<Int64, GroupMember>.fromIterable(
            contact.members,
            key: (member) => (member as GroupMember).userId,
            value: (member) => member as GroupMember,
          )
        : null;
    final now = DateTime.now();
    final count = loadedMessages.length;
    return ListView.builder(
        shrinkWrap: true,
        controller: _scrollController,
        itemCount: count,
        reverse: true,
        findChildIndexCallback: (key) {
          final messageId = (key as ValueKey).value as Int64;
          return messageIdToIndex[messageId];
        },
        itemBuilder: (context, index) {
          final message = loadedMessages[index];
          switch (contact) {
            case SystemContact():
              return _buildMessageTile(
                  appLocalizations: appLocalizations,
                  dateFormat_yMdjm: dateFormat_yMdjm,
                  dateFormat_Mdjm: dateFormat_Mdjm,
                  dateFormat_jm: dateFormat_jm,
                  now: now,
                  id: contact.id,
                  senderName: contact.name,
                  senderImage: contact.image,
                  message: message);
            case UserContact():
              return _buildMessageTile(
                  appLocalizations: appLocalizations,
                  dateFormat_yMdjm: dateFormat_yMdjm,
                  dateFormat_Mdjm: dateFormat_Mdjm,
                  dateFormat_jm: dateFormat_jm,
                  now: now,
                  id: contact.id,
                  senderName: contact.name,
                  senderImage: contact.image,
                  message: message);
            case GroupContact():
              final groupMember = groupMemberIdToMember![message.senderId];
              if (groupMember == null) {
                return _buildMessageTile(
                    appLocalizations: appLocalizations,
                    dateFormat_yMdjm: dateFormat_yMdjm,
                    dateFormat_Mdjm: dateFormat_Mdjm,
                    dateFormat_jm: dateFormat_jm,
                    now: now,
                    id: contact.id,
                    senderName: message.senderId.toString(),
                    // TODO: use default avatar
                    message: message);
              }
              return _buildMessageTile(
                  appLocalizations: appLocalizations,
                  dateFormat_yMdjm: dateFormat_yMdjm,
                  dateFormat_Mdjm: dateFormat_Mdjm,
                  dateFormat_jm: dateFormat_jm,
                  now: now,
                  id: contact.id,
                  senderName: groupMember.name,
                  senderImage: groupMember.image,
                  message: message);
          }
        });
  }

  Widget _buildMessageTile({
    required AppLocalizations appLocalizations,
    // ignore: non_constant_identifier_names
    required DateFormat dateFormat_yMdjm,
    // ignore: non_constant_identifier_names
    required DateFormat dateFormat_Mdjm,
    // ignore: non_constant_identifier_names
    required DateFormat dateFormat_jm,
    required DateTime now,
    required Int64 id,
    required String senderName,
    ImageProvider<Object>? senderImage,
    required ChatMessage message,
  }) {
    final timestamp = message.timestamp;
    return TListTile(
        key: ValueKey(message.messageId),
        height: null,
        padding: const EdgeInsets.symmetric(horizontal: 24, vertical: 16),
        backgroundColor: Colors.white,
        child: Row(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            TAvatar(id: id, name: senderName, image: senderImage),
            Sizes.sizedBoxW8,
            Expanded(
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Row(
                    spacing: 8,
                    children: [
                      Text(senderName),
                      Text(
                        now.year == timestamp.year
                            ? (now.month == timestamp.month &&
                                    now.day == timestamp.day)
                                ? dateFormat_jm.format(timestamp)
                                : dateFormat_Mdjm.format(timestamp)
                            : dateFormat_yMdjm.format(timestamp),
                      )
                    ],
                  ),
                  // Sizes.sizedBoxH8,
                  // TODO
                  Text(message.text!),
                ],
              ),
            ),
          ],
        ));
  }

  Future<void> _search(String text) async {
    final loggedInUserId = _loggedInUserId!;
    // Note that there is no need to dispose the loading status.
    final conversation = widget.conversation;
    final (groupId, participantIds) = switch (conversation) {
      final GroupConversation c => (c.contact.groupId, null),
      final UserConversation c => (null, [loggedInUserId, c.contact.userId]),
      final SystemConversation c => (null, [loggedInUserId, loggedInUserId]),
    };
    var newFoundMessages =
        await ref.read(messageServiceProvider)!.searchMessages(
              loggedInUserId: loggedInUserId,
              groupId: groupId,
              participantIds: participantIds,
              text: text,
              limit: searchResultLimit + 1,
              messageType: _searchConditionMessageType,
            );
    final count = newFoundMessages.length;
    _hasMoreMessages = count > searchResultLimit;
    if (count > searchResultLimit) {
      newFoundMessages = newFoundMessages.sublist(0, searchResultLimit);
    }
    _lastConversation = conversation;
    _searchConditionText = text;
    _lastFoundMessage = newFoundMessages.lastOrNull;
    _foundMessages = newFoundMessages;
    _loadedMessagesData = TAsyncData(value: newFoundMessages.toList());
    if (mounted) {
      if (_scrollController.hasClients) {
        _scrollController.jumpTo(0);
      }
      setState(() {});
    }
  }

  Future<void> _findMoreMessages() async {
    final userId = _loggedInUserId;
    if (userId == null) {
      return;
    }
    final currentFindMessagesTask = _findMoreMessages0(userId);
    _currentFindMessagesTask = currentFindMessagesTask;
    try {
      await currentFindMessagesTask;
    } finally {
      _currentFindMessagesTask = null;
    }
  }

  Future<void> _findMoreMessages0(Int64 loggedInUserId) async {
    // Note that there is no need to dispose the loading status.
    final (groupId, participantIds) = switch (_lastConversation!) {
      final GroupConversation c => (c.contact.groupId, null),
      final UserConversation c => (null, [loggedInUserId, c.contact.userId]),
      final SystemConversation c => (null, [loggedInUserId, loggedInUserId]),
    };
    var newFoundMessages =
        await ref.read(messageServiceProvider)!.searchMessages(
              loggedInUserId: loggedInUserId,
              idStart: _lastFoundMessage?.messageId,
              groupId: groupId,
              participantIds: participantIds,
              text: _searchConditionText,
              messageType: _searchConditionMessageType,
              limit: searchResultLimit + 1,
              createdDateEnd: _lastFoundMessage?.timestamp,
            );
    final count = newFoundMessages.length;
    _hasMoreMessages = count > searchResultLimit;
    if (count > searchResultLimit) {
      newFoundMessages = newFoundMessages.sublist(0, searchResultLimit);
    }
    _lastFoundMessage = newFoundMessages.lastOrNull;
    _foundMessages.addAll(newFoundMessages);
  }
}

Future<void> showSingleChatHistoryDialog(
        {required BuildContext context, required Conversation conversation}) =>
    showCustomTDialog(
        routeName: '/single-chat-history-dialog',
        context: context,
        child: SingleChatHistoryPage(conversation: conversation));
