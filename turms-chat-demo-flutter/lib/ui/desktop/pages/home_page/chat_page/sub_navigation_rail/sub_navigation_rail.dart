import 'dart:async';

import 'package:async/async.dart';
import 'package:fixnum/fixnum.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/rendering.dart';
import 'package:flutter/scheduler.dart';
import 'package:flutter/services.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:material_symbols_icons/symbols.dart';

import '../../../../../../domain/conversation/models/conversation.dart';
import '../../../../../../domain/conversation/services/conversation_service.dart';
import '../../../../../../domain/message/models/message_delivery_status.dart';
import '../../../../../../domain/message/repositories/message_repository.dart';
import '../../../../../../domain/user/models/contact.dart';
import '../../../../../../domain/user/view_models/logged_in_user_info_view_model.dart';
import '../../../../../../infra/collection/list_holder.dart';
import '../../../../../../infra/data/t_async_data.dart';
import '../../../../../../infra/navigation/navigation_utils.dart';
import '../../../../../../infra/ui/scroll_utils.dart';
import '../../../../../../infra/ui/text_utils.dart';
import '../../../../../l10n/app_localizations.dart';
import '../../../../../l10n/view_models/app_localizations_view_model.dart';
import '../../../../../themes/app_theme_extension.dart';
import '../../../../../themes/sizes.dart';
import '../../../../components/index.dart';
import '../../contacts_page/view_models/contacts_view_model.dart';
import '../../create_group_page/create_group_page.dart';
import '../../new_relationship_page/new_relationship_page.dart';
import '../chat_session_pane/message.dart';
import '../view_models/conversations_data_view_model.dart';
import '../view_models/selected_conversation_view_model.dart';
import 'conversation_tile.dart';
import 'conversation_tiles.dart';

class SubNavigationRail extends ConsumerStatefulWidget {
  const SubNavigationRail({super.key});

  @override
  ConsumerState<SubNavigationRail> createState() => _SubNavigationRailState();
}

class _SubNavigationRailState extends ConsumerState<SubNavigationRail> {
  late TextEditingController _searchBarTextEditingController;
  late FocusNode _searchBarFocusNode;
  CancelableOperation<void>? _searchConversationTask;
  late ScrollController _conversationTilesScrollController;
  BuildContext? _conversationTilesBuildContext;

  late TAsyncData<List<Conversation>> _conversationsData;
  List<ConversationTileItem> _conversationTileItems = [];
  Conversation? _selectedConversation;
  int? _highlightedConversationTileItemIndex;

  late Int64 _loggedInUserId;

  final _SearchData _searchData = _SearchData();

  @override
  void initState() {
    super.initState();
    _searchBarTextEditingController = TextEditingController();
    _searchBarFocusNode = FocusNode()
      ..addListener(
          _updateHighlightedConversationTileItemIndexOnSearchBarFocusChanged);
    _conversationTilesScrollController = ScrollController();
    ref.listenManual(
      fireImmediately: true,
      conversationsDataViewModel,
      (previous, next) {
        _conversationsData = next;
        if (_searchData.isSearching) {
          _onSearchTextUpdated(true, _searchData.searchText);
        } else {
          setState(() {});
        }
      },
    );
  }

  @override
  void dispose() {
    _searchBarTextEditingController.dispose();
    _searchBarFocusNode.dispose();
    _conversationTilesScrollController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    final appLocalizations = ref.watch(appLocalizationsViewModel);
    _selectedConversation = ref.watch(selectedConversationViewModel);
    _loggedInUserId = ref.watch(loggedInUserViewModel)!.userId;

    final theme = context.theme;
    final appThemeExtension = theme.appThemeExtension;
    final previousSelectedConversationId = _selectedConversation?.id;
    final conversationId = _selectedConversation?.id;
    final conversations = _conversationsData.value ?? [];
    if (conversationId != null &&
        previousSelectedConversationId != conversationId) {
      final conversationIndex =
          conversations.indexWhere((element) => element.id == conversationId);
      if (conversationIndex >= 0) {
        _scrollTo(conversationIndex);
      }
    }

    final searchData = _searchData;
    if (searchData.isSearching) {
      _conversationTileItems = searchData.searchResults
          .expand<ConversationTileItemForSearchMode>((item) {
        final latestMessage = item.latestMessage;
        final nameTextSpans = TextUtils.highlightSearchText(
            text: item.contact.name,
            searchText: searchData.searchText,
            searchTextStyle:
                appThemeExtension.conversationTileHighlightedTextStyle);
        final messageTextSpans = switch (item.count) {
          0 => <TextSpan>[],
          1 => TextUtils.highlightSearchText(
              // The UI only show found text messages,
              // so the text should not be null.
              text: item.latestMessage.text!,
              searchText: searchData.searchText,
              searchTextStyle: context
                  .appThemeExtension.conversationTileHighlightedTextStyle),
          _ => [TextSpan(text: appLocalizations.relatedMessages(item.count))]
        };
        return [
          ConversationTileItemForSearchMode(
            conversationId: item.conversationId,
            contact: item.contact,
            latestMessage: latestMessage,
            count: item.count,
            nameTextSpans: nameTextSpans,
            messageTextSpans: messageTextSpans,
          )
        ];
      }).toList();
    } else {
      _conversationTileItems = conversations
          .expand<ConversationTileItemForNormalMode>((conversation) => [
                ConversationTileItemForNormalMode(
                  conversation: conversation,
                  nameTextSpans: [TextSpan(text: conversation.contact.name)],
                )
              ])
          .toList();
    }
    return _buildView(theme, appThemeExtension, appLocalizations);
  }

  void _scrollTo(int itemIndex) {
    SchedulerBinding.instance.addPostFrameCallback((_) {
      final itemContext = _conversationTilesBuildContext;
      if (itemContext == null) {
        return;
      }
      final renderObject =
          itemContext.findRenderObject() as RenderSliverFixedExtentBoxAdaptor;
      final itemHeight = renderObject.itemExtent!;
      ScrollUtils.ensureVisible(
          controller: _conversationTilesScrollController,
          viewportDimension: renderObject.constraints.viewportMainAxisExtent,
          itemOffset: itemHeight * itemIndex,
          itemHeight: itemHeight);
    });
  }

  void _selectConversationWhenNotSearching(Conversation conversation) {
    conversation.unreadMessageCount = 0;
    ref.read(selectedConversationViewModel.notifier).update(conversation);
    unawaited(ref
        .read(conversationServiceProvider)!
        .resetSharedUnreadMessageCount(
            userId: conversation is UserConversation
                ? conversation.contact.userId
                : conversation is SystemConversation
                    ? _loggedInUserId
                    : null,
            groupId: conversation is GroupConversation
                ? conversation.contact.groupId
                : null));
  }

  void _selectConversationWhenSearching(IntListHolder conversationId) {
    _searchBarTextEditingController.clear();
    _onSearchTextUpdated(false, '');
    // TODO: showChatHistoryDialog
  }

  void _onConversationDeleted(IntListHolder conversationId) {
    _onSearchTextUpdated(true, _searchData.searchText);
  }

  Future<void> _onSearchTextUpdated(bool forceUpdate, String value) async {
    const searchResultLimit = 20;
    final newSearchText = value.toLowerCase().trim();
    final newIsSearching = newSearchText.isNotEmpty;
    _highlightedConversationTileItemIndex = null;

    if (newIsSearching) {
      if (forceUpdate || newSearchText != _searchData.searchText) {
        final contacts = ref.read(contactsDataViewModel).value ?? [];
        final contactIdToContact = {
          for (final contact in contacts)
            (contact.id, contact is GroupContact): contact,
        };
        _searchData.searchText = newSearchText;
        await _searchConversationTask?.cancel();
        _searchConversationTask = CancelableOperation.fromFuture(Future(
          () => ref
              .read(messageRepositoryProvider)!
              .countAndSearchLatestMessage(
                  text: newSearchText, limit: searchResultLimit + 1),
        )).then(
          (results) {
            _searchData
              ..isSearching = true
              ..hasMoreSearchResultItems = results.length > searchResultLimit
              ..searchResults =
                  results.take(searchResultLimit).expand<_SearchResul>(
                (result) {
                  final messageRecord = result.message;
                  final isGroupMessage = messageRecord.isGroupMessage;
                  final contact = contactIdToContact[(
                    messageRecord.contactId,
                    isGroupMessage
                  )];
                  if (contact == null) {
                    return [];
                  }
                  final senderId = messageRecord.senderId;
                  final message = ChatMessage.parse(
                      messageId: messageRecord.id,
                      senderId: senderId,
                      sentByMe: senderId == _loggedInUserId,
                      isGroupMessage: isGroupMessage,
                      text: messageRecord.txt,
                      timestamp: messageRecord.createdDate,
                      status: MessageDeliveryStatus.delivered);
                  return [
                    _SearchResul(
                        conversationId: Conversation.generateId(
                          groupId: isGroupMessage ? contact.id : null,
                          userId: isGroupMessage ? null : contact.id,
                        ),
                        contact: contact,
                        latestMessage: message,
                        count: result.count)
                  ];
                },
              ).toList();
            if (mounted) {
              setState(() {});
            }
            return null;
          },
        );
      }
    } else {
      _searchData.reset();
      setState(() {});
    }
  }

  void _onSearchSubmitted() {
    final itemIndex = _highlightedConversationTileItemIndex;
    final items = _conversationTileItems;
    if (itemIndex != null && itemIndex < items.length) {
      final item = items[itemIndex];
      _selectConversationWhenSearching(item.conversationId);
    }
    setState(() {});
  }

  void _updateHighlightedConversationTileItemIndexOnSearchBarFocusChanged() {
    if (_searchBarFocusNode.hasFocus) {
      final selectedConversationId = _selectedConversation?.id;
      if (selectedConversationId == null) {
        _highlightedConversationTileItemIndex = null;
        setState(() {});
      } else {
        final selectedConversationTileItemIndex =
            _conversationTileItems.indexWhere(
                (item) => item.conversationId == selectedConversationId);
        if (selectedConversationTileItemIndex >= 0) {
          _highlightedConversationTileItemIndex =
              selectedConversationTileItemIndex;
          setState(() {});
        }
      }
    } else {
      _highlightedConversationTileItemIndex = null;
      setState(() {});
    }
  }

  KeyEventResult _onKeyEvent(FocusNode node, KeyEvent event) {
    final (result, newIndex) = NavigationUtils.navigateByKeyEvent(event,
        _conversationTileItems.length, _highlightedConversationTileItemIndex);
    if (result == KeyEventResult.handled) {
      if (_highlightedConversationTileItemIndex != newIndex) {
        _highlightedConversationTileItemIndex = newIndex!;
        setState(() {});
        _scrollTo(newIndex);
      }
      return KeyEventResult.handled;
    } else if (event.logicalKey == LogicalKeyboardKey.enter) {
      if (_highlightedConversationTileItemIndex case final itemIndex?) {
        if (_searchData.isSearching) {
          _selectConversationWhenSearching(
              _searchData.searchResults[itemIndex].conversationId);
        } else {
          _selectConversationWhenNotSearching(
              _conversationsData.value![itemIndex]);
        }
      }
      return KeyEventResult.handled;
    } else {
      return KeyEventResult.ignored;
    }
  }
}

extension _SubNavigationRailView on _SubNavigationRailState {
  Widget _buildView(ThemeData theme, AppThemeExtension appThemeExtension,
          AppLocalizations appLocalizations) =>
      Focus(
        onKeyEvent: _onKeyEvent,
        child: Padding(
          padding: EdgeInsets.only(
              right: Sizes.subNavigationRailDividerSize.thickness),
          child: ColoredBox(
            color: appThemeExtension.tileBackgroundColor,
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                // todo: use independent widget
                _buildSearchBar(appThemeExtension, appLocalizations),
                if (_conversationsData.isLoading) _buildLoadingIndicator(),
                _buildConversationTiles()
              ],
            ),
          ),
        ),
      );

  Widget _buildLoadingIndicator() => const SizedBox(
        height: 40,
        child: ColoredBox(
          color: Color.fromARGB(255, 237, 237, 237),
          child: Align(
            alignment: AlignmentDirectional.center,
            child: CupertinoActivityIndicator(radius: 8),
          ),
        ),
      );

  Widget _buildSearchBar(AppThemeExtension appThemeExtension,
          AppLocalizations appLocalizations) =>
      SizedBox(
        height: Sizes.homePageHeaderHeight,
        child: ColoredBox(
          color: appThemeExtension.subNavigationRailSearchBarBackgroundColor,
          child: Padding(
            padding: Sizes.subNavigationRailPadding,
            child: Center(
              child: Row(
                spacing: 8,
                children: [
                  Expanded(
                    // todo: adapt height
                    child: TSearchBar(
                      textEditingController: _searchBarTextEditingController,
                      focusNode: _searchBarFocusNode,
                      hintText: appLocalizations.search,
                      onChanged: (value) => _onSearchTextUpdated(false, value),
                      onSubmitted: (_) => _onSearchSubmitted(),
                    ),
                  ),
                  TMenuPopup(
                      constrainFollowerWithTargetWidth: false,
                      targetAnchor: Alignment.bottomLeft,
                      followerAnchor: Alignment.topLeft,
                      offset: const Offset(0, 8),
                      entries: [
                        TMenuEntry(
                          value: 0,
                          label: appLocalizations.addContact,
                          onSelected: () {
                            showNewRelationshipDialog(context, true);
                          },
                        ),
                        TMenuEntry(
                          value: 1,
                          label: appLocalizations.joinGroup,
                          onSelected: () {
                            showNewRelationshipDialog(context, false);
                          },
                        ),
                        TMenuEntry(
                          value: 2,
                          label: appLocalizations.createGroup,
                          onSelected: () {
                            showCreateGroupDialog(context: context);
                          },
                        ),
                      ],
                      anchor: const TIconButton(
                        iconData: Symbols.add_rounded,
                        iconSize: 20,
                        // todo: adapt height
                        containerSize: Size(30, 30),
                        containerColor: Color.fromARGB(255, 226, 226, 226),
                        containerColorHovered:
                            Color.fromARGB(255, 209, 209, 209),
                      ))
                ],
              ),
            ),
          ),
        ),
      );

  Widget _buildConversationTiles() => Expanded(
          child: ConversationTiles(
        conversationTileItems: _conversationTileItems,
        highlightedConversationTileItemIndex:
            _highlightedConversationTileItemIndex,
        selectedConversationId: _selectedConversation?.id,
        conversationTilesScrollController: _conversationTilesScrollController,
        onConversationTilesBuildContextUpdated: (context) {
          _conversationTilesBuildContext ??= context;
        },
        onConversationTileItemSelected: (item) {
          switch (item) {
            case ConversationTileItemForNormalMode():
              _selectConversationWhenNotSearching(item.conversation);
            case ConversationTileItemForSearchMode():
              _selectConversationWhenSearching(item.conversationId);
          }
        },
        onConversationDeleted: _onConversationDeleted,
      ));
}

class _SearchData {
  bool isSearching = false;
  String searchText = '';
  List<_SearchResul> searchResults = [];

  // TODO: show "load more" button
  bool hasMoreSearchResultItems = false;

  void reset() {
    isSearching = false;
    searchText = '';
    searchResults = [];
    hasMoreSearchResultItems = false;
  }
}

class _SearchResul {
  const _SearchResul(
      {required this.conversationId,
      required this.contact,
      required this.latestMessage,
      required this.count});

  final IntListHolder conversationId;
  final Contact contact;
  final ChatMessage latestMessage;
  final int count;
}
