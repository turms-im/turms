import 'package:collection/collection.dart';
import 'package:fixnum/fixnum.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/scheduler.dart';
import 'package:flutter/services.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

import '../../../../../../domain/user/models/contact.dart';
import '../../../../../../domain/user/services/user_service.dart';
import '../../../../../../infra/data/t_async_data.dart';
import '../../../../../../infra/ui/text_utils.dart';
import '../../../../../domain/user/models/relationship_group.dart';
import '../../../../../infra/navigation/navigation_utils.dart';
import '../../../../../infra/ui/scroll_utils.dart';
import '../../../../l10n/app_localizations.dart';
import '../../../../l10n/view_models/app_localizations_view_model.dart';
import '../../../../themes/app_theme_extension.dart';
import '../../../../themes/sizes.dart';
import '../../../components/index.dart';
import 'contact_tile.dart';
import 'view_models/contacts_view_model.dart';
import 'view_models/relationship_groups_view_model.dart';
import 'view_models/selected_contact_view_model.dart';

class SubNavigationRail extends ConsumerStatefulWidget {
  const SubNavigationRail({super.key});

  @override
  ConsumerState<SubNavigationRail> createState() => _SubNavigationRailState();
}

class _SubNavigationRailState extends ConsumerState<SubNavigationRail> {
  late AppLocalizations _appLocalizations;
  late TextEditingController _searchBarTextEditingController;
  late FocusNode _searchBarFocusNode;
  late ScrollController _scrollControllerForNormal;
  late ScrollController _scrollControllerForSearch;

  final Set<_RelationshipGroupContactKey> _relationshipGroupContactKeys = {};
  final Map<Int64, TAccordionController> _relationshipGroupIdToController = {};
  final Set<_ContactKey> _contactKeysForSearch = {};
  final Map<int, _RelationshipGroupContactKey> _relationshipContactIndexToKey =
      {};
  TAsyncData<List<Contact>> _contactsData = const TAsyncData();
  TAsyncData<List<RelationshipGroup>> _relationshipGroupsData =
      const TAsyncData();
  int? _highlightedContactTileItemIndex;

  final _SearchData _searchData = _SearchData();

  @override
  void initState() {
    super.initState();
    _searchBarTextEditingController = TextEditingController();
    _searchBarFocusNode = FocusNode()
      ..addListener(
          _updateHighlightedContactTileItemIndexOnSearchBarFocusChanged);
    _scrollControllerForNormal = ScrollController();
    _scrollControllerForSearch = ScrollController();
    ref
      ..listenManual(
        fireImmediately: true,
        appLocalizationsViewModel,
        (previous, next) {
          _appLocalizations = next;
          if (_contactsData.isInitialized) {
            _contactsData = _contactsData.copyWith(
                value: ref.read(userServiceProvider)!.getSystemContacts(next) +
                    _contactsData.value!);
            if (_searchData.isSearching) {
              _onSearchTextUpdated(_searchData.searchText);
            } else {
              setState(() {});
            }
          }
        },
      )
      ..listenManual(
        fireImmediately: true,
        contactsDataViewModel,
        (previous, next) {
          if (next.isInitialized) {
            final appLocalizations = ref.read(appLocalizationsViewModel);
            final newContacts = ref
                    .read(userServiceProvider)!
                    .getSystemContacts(appLocalizations) +
                next.value!;
            _contactsData = next.copyWith(value: newContacts);
          } else {
            _contactsData = next;
          }
          if (_searchData.isSearching) {
            _onSearchTextUpdated(_searchData.searchText);
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
    _scrollControllerForNormal.dispose();
    _scrollControllerForSearch.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    final selectedContact = ref.watch(selectedContactViewModel);
    _relationshipGroupsData = ref.watch(relationshipGroupsDataViewModel);

    var index = 0;
    for (final group
        in _relationshipGroupsData.value ?? <RelationshipGroup>[]) {
      for (final contact in group.contacts) {
        _relationshipContactIndexToKey[index++] = _RelationshipGroupContactKey(
            groupId: group.id, recordId: contact.recordId);
      }
    }

    final theme = context.theme;
    final appThemeExtension = theme.appThemeExtension;

    return _buildView(
        theme, appThemeExtension, _appLocalizations, selectedContact);
  }

  void _selectContact(Contact contact) {
    ref.read(selectedContactViewModel.notifier).state = contact;
  }

  void _onSearchTextUpdated(String value) {
    final newSearchText = value.toLowerCase().trim();
    final newIsSearching = newSearchText.isNotEmpty;
    _highlightedContactTileItemIndex = null;

    _searchData.isSearching = newIsSearching;
    if (newIsSearching) {
      _searchData
        ..isSearching = true
        ..searchText = newSearchText
        ..searchResults = (_contactsData.value ?? [])
            .where((contact) => TextUtils.shouldHighlightText(
                text: contact.name, searchText: newSearchText))
            .toList();
    } else {
      _searchData.reset();
    }
    setState(() {});
  }

  void _updateHighlightedContactTileItemIndexOnSearchBarFocusChanged() {
    if (_searchBarFocusNode.hasFocus) {
      final _selectedContact = ref.read(selectedContactViewModel);
      final selectedConversationRecordId = _selectedContact?.recordId;
      if (selectedConversationRecordId == null) {
        _highlightedContactTileItemIndex = null;
        setState(() {});
      } else if (_relationshipGroupsData.isInitialized) {
        var i = 0;
        var found = false;
        for (final group in _relationshipGroupsData.value!) {
          for (final contact in group.contacts) {
            if (contact.recordId == selectedConversationRecordId) {
              _highlightedContactTileItemIndex = i;
              setState(() {});
              found = true;
              break;
            }
            i++;
          }
          if (found) {
            break;
          }
        }
      }
    } else {
      _highlightedContactTileItemIndex = null;
      setState(() {});
    }
  }

  KeyEventResult _onKeyEvent(FocusNode node, KeyEvent event) {
    final isSearching = _searchData.isSearching;
    final total = isSearching
        ? _searchData.searchResults.length
        : _relationshipContactIndexToKey.length;
    final (result, newIndex) = NavigationUtils.navigateByKeyEvent(
        event, total, _highlightedContactTileItemIndex);
    if (result == KeyEventResult.handled) {
      if (_highlightedContactTileItemIndex != newIndex) {
        _highlightedContactTileItemIndex = newIndex!;
        setState(() {});
        if (isSearching) {
          _scrollToByContactKey(newIndex,
              _ContactKey(_searchData.searchResults[newIndex].recordId));
        } else {
          _scrollToByRelationshipGroupContactKey(
              _relationshipContactIndexToKey[newIndex]!);
        }
      }
      return KeyEventResult.handled;
    } else if (event.logicalKey == LogicalKeyboardKey.enter) {
      if (_highlightedContactTileItemIndex case final itemIndex?) {
        if (_searchData.isSearching) {
          _selectConversationWhenSearching(
              _searchData.searchResults[itemIndex].recordId);
        } else {
          _selectConversationWhenNotSearching(
              _relationshipContactIndexToKey[itemIndex]!.recordId);
        }
      }
      return KeyEventResult.handled;
    } else if (event.logicalKey == LogicalKeyboardKey.arrowLeft) {
      if (_highlightedContactTileItemIndex case final itemIndex?) {
        if (!_searchData.isSearching) {
          final controller = _relationshipGroupIdToController[
              _relationshipContactIndexToKey[itemIndex]!.groupId];
          if (controller != null) {
            controller.close?.call();
          }
        }
      }
      return KeyEventResult.handled;
    } else {
      return KeyEventResult.ignored;
    }
  }

  void _scrollToByContactKey(int itemIndex, _ContactKey key) {
    SchedulerBinding.instance.addPostFrameCallback((_) {
      final found = _contactKeysForSearch.lookup(key);
      if (found == null) {
        return;
      }
      final currentContext = found.currentContext;
      if (currentContext == null || !currentContext.mounted) {
        return;
      }
      ScrollUtils.ensureVisible(
          controller: _scrollControllerForSearch,
          viewportDimension:
              _scrollControllerForSearch.position.viewportDimension,
          itemOffset: Sizes.conversationTileHeight * itemIndex,
          itemHeight: Sizes.conversationTileHeight);
    });
  }

  void _scrollToByRelationshipGroupContactKey(
      _RelationshipGroupContactKey relationshipGroupContactKey) {
    var isOpening = false;
    final controller =
        _relationshipGroupIdToController[relationshipGroupContactKey.groupId];
    assert(controller != null);
    if (controller != null) {
      isOpening = controller.open?.call(onOpenCompleted: () {
            _scrollToByRelationshipGroupContactKey0(
                relationshipGroupContactKey);
          }) ??
          false;
    }
    if (!isOpening) {
      SchedulerBinding.instance.addPostFrameCallback((_) {
        _scrollToByRelationshipGroupContactKey0(relationshipGroupContactKey);
      });
    }
  }

  void _scrollToByRelationshipGroupContactKey0(
      _RelationshipGroupContactKey relationshipGroupContactKey) {
    final found =
        _relationshipGroupContactKeys.lookup(relationshipGroupContactKey);
    if (found == null) {
      return;
    }
    final currentContext = found.currentContext;
    if (currentContext == null || !currentContext.mounted) {
      return;
    }
    final renderBox = currentContext.findRenderObject() as RenderBox;
    ScrollUtils.ensureRenderBoxVisible(renderBox: renderBox);
  }

  void _selectConversationWhenNotSearching(String recordId) {
    final contact = (_contactsData.value ?? []).firstWhereOrNull(
      (element) => element.recordId == recordId,
    );
    if (contact == null) {
      return;
    }
    _selectContact(contact);
  }

  void _selectConversationWhenSearching(String recordId) {
    final contact = (_contactsData.value ?? []).firstWhereOrNull(
      (element) => element.recordId == recordId,
    );
    if (contact == null) {
      return;
    }
    _selectContact(contact);
    _searchBarTextEditingController.clear();
    _onSearchTextUpdated('');
  }
}

extension _SubNavigationRailStateView on _SubNavigationRailState {
  Widget _buildView(ThemeData theme, AppThemeExtension appThemeExtension,
          AppLocalizations appLocalizations, Contact? selectedContact) =>
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
                  _buildSearchBar(appThemeExtension, appLocalizations),
                  if (_contactsData.isLoading ||
                      _relationshipGroupsData.isLoading)
                    _buildLoadingIndicator(appThemeExtension),
                  Expanded(
                    child: _searchData.isSearching
                        ? _buildContactTiles(appThemeExtension,
                            appLocalizations, selectedContact)
                        // TODO: use builder
                        : ListView(
                            cacheExtent: 128,
                            controller: _scrollControllerForNormal,
                            children: _buildRelationshipGroups(appLocalizations,
                                selectedContact, _relationshipGroupsData),
                          ),
                  ),
                ],
              )),
        ),
      );

  Widget _buildLoadingIndicator(AppThemeExtension appThemeExtension) =>
      SizedBox(
        height: 40,
        child: ColoredBox(
          color: appThemeExtension
              .subNavigationRailLoadingIndicatorBackgroundColor,
          child: const Center(
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
              child: TSearchBar(
                textEditingController: _searchBarTextEditingController,
                focusNode: _searchBarFocusNode,
                hintText: appLocalizations.search,
                onChanged: _onSearchTextUpdated,
              ),
            ),
          ),
        ),
      );

  Widget _buildContactTiles(AppThemeExtension appThemeExtension,
      AppLocalizations appLocalizations, Contact? selectedContact) {
    final selectedContactRecordId = selectedContact?.recordId;
    final matchedContacts =
        _searchData.searchResults.expand<_StyledContact>((contact) {
      final nameTextSpans = TextUtils.highlightSearchText(
          text: contact.name,
          searchText: _searchData.searchText,
          searchTextStyle:
              appThemeExtension.conversationTileHighlightedTextStyle);
      if (nameTextSpans.length == 1) {
        return [];
      }
      return [_StyledContact(contact: contact, nameTextSpans: nameTextSpans)];
    }).toList();

    final itemCount = matchedContacts.length;
    final contactRecordIdToIndex = {
      for (var i = 0; i < itemCount; i++) matchedContacts[i].contact.recordId: i
    };
    _contactKeysForSearch.clear();
    return ListView.builder(
        controller: _scrollControllerForSearch,
        addAutomaticKeepAlives: false,
        itemCount: itemCount,
        findChildIndexCallback: (key) =>
            contactRecordIdToIndex[(key as _ContactKey).value],
        prototypeItem: ContactTile(
          contact: UserContact(userId: Int64.MIN_VALUE, name: ''),
          nameTextSpans: [],
          isSearching: true,
          highlighted: false,
          selected: false,
          onTap: () {},
        ),
        itemBuilder: (BuildContext context, int index) {
          final _StyledContact(:contact, :nameTextSpans) =
              matchedContacts[index];
          final contactKey = _ContactKey(contact.recordId);
          if (!_contactKeysForSearch.add(contactKey)) {
            throw AssertionError('Duplicate contact key: $contactKey');
          }
          return ContactTile(
            key: contactKey,
            contact: contact,
            nameTextSpans: nameTextSpans,
            isSearching: true,
            highlighted: _highlightedContactTileItemIndex == index,
            selected: contact.recordId == selectedContactRecordId,
            onTap: () {
              _selectContact(contact);
            },
          );
        });
  }

  List<Widget> _buildRelationshipGroups(
      AppLocalizations appLocalizations,
      Contact? selectedContact,
      TAsyncData<List<RelationshipGroup>> relationshipGroupsData) {
    final selectedContactRecordId = selectedContact?.recordId;
    // final widgets = ref
    //     .read(userServiceProvider)!
    //     .getSystemContacts(appLocalizations)
    //     .map<Widget>((contact) => ContactTile(
    //           contact: contact,
    //           nameTextSpans: [],
    //           isSearching: false,
    //           selected: contact.recordId == selectedContactRecordId,
    //           onTap: () {
    //             selectContact(contact);
    //           },
    //         ))
    //     .toList();

    _relationshipGroupContactKeys.clear();
    _relationshipGroupIdToController.clear();
    final widgets = <Widget>[];
    // TODO: Load contacts lazily if there are more than 20 contacts.
    final groups = relationshipGroupsData.value ?? [];
    var index = 0;
    for (final group in groups) {
      final entry = _buildRelationshipGroup(
          index, group.id, group.name, group.contacts, selectedContactRecordId);
      index = entry.$2;
      widgets.add(entry.$1);
    }

    return widgets;
  }

  (Widget, int) _buildRelationshipGroup(int startIndex, Int64 groupId,
      String name, List<Contact> contacts, String? selectedContactRecordId) {
    assert(_relationshipGroupIdToController[groupId] == null);
    final controller = TAccordionController();
    _relationshipGroupIdToController[groupId] = controller;
    final widget = TAccordion(
      controller: controller,
      titleChild: Row(
        spacing: 4,
        children: [
          Text(name),
          Text('(${contacts.length})'),
        ],
      ),
      // TODO: Use ListView for better performance
      contentChild: Column(
        children: contacts.map((contact) {
          final relationshipGroupContactKey = _RelationshipGroupContactKey(
              groupId: groupId, recordId: contact.recordId);
          if (!_relationshipGroupContactKeys.add(relationshipGroupContactKey)) {
            throw AssertionError(
                'Duplicate relationship group contact key: $relationshipGroupContactKey');
          }
          return ContactTile(
            key: relationshipGroupContactKey,
            contact: contact,
            highlighted: _highlightedContactTileItemIndex == startIndex++,
            selected: contact.recordId == selectedContactRecordId,
            onTap: () {
              _selectContact(contact);
            },
            nameTextSpans: [],
            isSearching: false,
          );
        }).toList(),
      ),
    );
    return (widget, startIndex);
  }
}

class _SearchData {
  bool isSearching = false;
  String searchText = '';
  List<Contact> searchResults = [];

  void reset() {
    isSearching = false;
    searchText = '';
    searchResults = [];
  }
}

class _StyledContact {
  const _StyledContact({required this.contact, required this.nameTextSpans});

  final Contact contact;
  final List<TextSpan> nameTextSpans;
}

class _RelationshipGroupContactKey extends GlobalObjectKey {
  const _RelationshipGroupContactKey({
    required this.groupId,
    required this.recordId,
  }) : super('$groupId:$recordId');

  final Int64 groupId;
  final String recordId;

  @override
  int get hashCode => Object.hash(groupId, recordId);

  @override
  bool operator ==(Object other) {
    if (identical(this, other)) {
      return true;
    }
    return other is _RelationshipGroupContactKey &&
        other.groupId == groupId &&
        other.recordId == recordId;
  }

  @override
  String toString() => '_RelationshipGroupContactKey($value)';
}

class _ContactKey extends GlobalObjectKey {
  const _ContactKey(String recordId) : super(recordId);

  @override
  int get hashCode => value.hashCode;

  @override
  bool operator ==(Object other) {
    if (identical(this, other)) {
      return true;
    }
    return other is _ContactKey && other.value == value;
  }

  @override
  String toString() => '_ContactKey($value)';
}
