import 'package:fixnum/fixnum.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:material_symbols_icons/symbols.dart';

import '../../../../../domain/group/services/group_service.dart';
import '../../../../../domain/user/models/index.dart';
import '../../../../../domain/user/services/user_service.dart';
import '../../../../../infra/data/t_async_data.dart';
import '../../../../l10n/app_localizations.dart';
import '../../../../l10n/view_models/app_localizations_view_model.dart';
import '../../../../themes/sizes.dart';
import '../../../components/index.dart';
import 'friend_request_page.dart';
import 'relationship_info_tile.dart';

const safeAreaPaddingHorizontal = 24.0;

Future<void> showNewRelationshipDialog(
        BuildContext context, bool showAddContactPage) =>
    showCustomTDialog(
        routeName: '/new-relationship-dialog',
        context: context,
        child: NewRelationshipPage(showAddContactPage: showAddContactPage));

class NewRelationshipPage extends ConsumerStatefulWidget {
  const NewRelationshipPage({super.key, required this.showAddContactPage});

  final bool showAddContactPage;

  @override
  ConsumerState<NewRelationshipPage> createState() =>
      _NewRelationshipPageState();
}

class _NewRelationshipPageState extends ConsumerState<NewRelationshipPage>
    with SingleTickerProviderStateMixin {
  late TextEditingController _searchTextController;
  late TabController _tabController;

  TAsyncData<List<UserContact>> _userContacts = const TAsyncData();
  TAsyncData<List<GroupContact>> _groupContacts = const TAsyncData();
  String _userContactSearchText = '';
  String _groupContactSearchText = '';

  late _SearchType _searchType;

  @override
  void initState() {
    super.initState();
    _searchTextController = TextEditingController();
    _tabController = TabController(length: 2, vsync: this)
      ..addListener(
        () {
          if (_tabController.index == 0) {
            _searchType = _SearchType.user;
            _searchTextController.text = _userContactSearchText;
          } else {
            _searchType = _SearchType.group;
            _searchTextController.text = _groupContactSearchText;
          }
          setState(() {});
        },
      );
    if (widget.showAddContactPage) {
      _searchType = _SearchType.user;
      _tabController.index = 0;
    } else {
      _searchType = _SearchType.group;
      _tabController.index = 1;
    }
  }

  @override
  void dispose() {
    _searchTextController.dispose();
    _tabController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    final appLocalizations = ref.watch(appLocalizationsViewModel);
    return _buildView(appLocalizations);
  }

  Future<void> _search<T>(_SearchType searchType, String value,
      ValueChanged<TAsyncData<List<T>>> contactsDataListener) async {
    final num = Int64.tryParseInt(value);
    if (num == null) {
      contactsDataListener(const TAsyncData(value: []));
      return;
    }
    contactsDataListener(const TAsyncData(isLoading: true));
    switch (searchType) {
      case _SearchType.user:
        final searchResult =
            await ref.read(userServiceProvider)!.searchUserContacts(num, value);
        contactsDataListener(TAsyncData(value: searchResult as List<T>));
        break;
      case _SearchType.group:
        final searchResult = await ref
            .read(groupServiceProvider)!
            .searchGroupContacts(num, value);
        contactsDataListener(TAsyncData(value: searchResult as List<T>));
        break;
    }
    if (!mounted) {
      return;
    }
    setState(() {});
  }

  Future<void> _searchUser(String value) => _search<UserContact>(
        _SearchType.user,
        value,
        (value) {
          _userContacts = value;
          setState(() {});
        },
      );

  Future<void> _searchGroup(String value) => _search<GroupContact>(
        _SearchType.group,
        value,
        (value) {
          _groupContacts = value;
          setState(() {});
        },
      );

  void _openFriendRequestDialog(Contact contact) {
    showFriendRequestDialog(context, contact);
  }

  void _openGroupJoinRequestDialog(Contact contact) {
    // TODO
    showFriendRequestDialog(context, contact);
  }

  void _updateSearchText(String value) {
    if (_searchType == _SearchType.user) {
      _userContactSearchText = value;
    } else {
      _groupContactSearchText = value;
    }
  }
}

extension _NewRelationshipPageView on _NewRelationshipPageState {
  Widget _buildView(AppLocalizations appLocalizations) => SizedBox(
        width: Sizes.dialogWidthMedium,
        height: Sizes.dialogHeightMedium,
        child: Stack(
          children: [
            Positioned.fill(
              child: _buildPage(appLocalizations),
            ),
            const TTitleBar(
              displayCloseOnly: true,
              popOnCloseTapped: true,
            )
          ],
        ),
      );

  Column _buildPage(AppLocalizations appLocalizations) => Column(children: [
        Sizes.sizedBoxH16,
        TabBar(
          isScrollable: true,
          tabAlignment: TabAlignment.start,
          padding: const EdgeInsets.symmetric(horizontal: 8),
          dividerHeight: 0,
          controller: _tabController,
          tabs: [
            Tab(
              text: appLocalizations.addContact,
              height: 40,
            ),
            Tab(
              text: appLocalizations.joinGroup,
              height: 40,
            )
          ],
        ),
        Sizes.sizedBoxH16,
        Padding(
          padding:
              const EdgeInsets.symmetric(horizontal: safeAreaPaddingHorizontal),
          child: TSearchBar(
            textEditingController: _searchTextController,
            hintText: appLocalizations.search,
            autofocus: true,
            keepFocusOnSubmit: true,
            onChanged: _updateSearchText,
            onSubmitted: (value) {
              if (_searchType == _SearchType.user) {
                _searchUser(value);
              } else {
                _searchGroup(value);
              }
              _updateSearchText(value);
            },
          ),
        ),
        Sizes.sizedBoxH16,
        Expanded(
          child: TabBarView(controller: _tabController, children: [
            _buildSearchResultView(false, _userContacts),
            _buildSearchResultView(true, _groupContacts),
          ]),
        ),
      ]);

  Widget _buildSearchResultView(
      bool isGroupContact, TAsyncData<List<Contact>> contactsData) {
    if (contactsData.isLoading) {
      return const Center(
        child: SizedBox(
          height: 24,
          width: 24,
          child: CircularProgressIndicator(
            color: Colors.blue,
          ),
        ),
      );
    }
    final contacts = contactsData.value ?? [];
    final contactCount = contacts.length;
    final idToIndex = {
      for (var i = 0; i < contactCount; i++) contacts[i].recordId: i
    };
    return contacts.isEmpty
        ? contactsData.isInitialized
            ? const TEmptyResult(
                icon: Symbols.person_rounded,
              )
            : const TEmpty()
        : ListView.builder(
            itemCount: contactCount,
            findChildIndexCallback: (key) =>
                idToIndex[(key as ValueKey<String>).value],
            itemBuilder: (context, index) {
              final contact = contacts[index];
              return RelationshipInfoTile(
                key: ValueKey(contact.recordId),
                isGroup: isGroupContact,
                contact: contact,
                onTap: () {
                  if (isGroupContact) {
                    _openGroupJoinRequestDialog(contact);
                  } else {
                    _openFriendRequestDialog(contact);
                  }
                },
              );
            });
  }
}

enum _SearchType {
  user,
  group,
}
