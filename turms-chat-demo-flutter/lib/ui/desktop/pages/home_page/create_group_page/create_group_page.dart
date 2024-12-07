import 'package:fixnum/fixnum.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:material_symbols_icons/symbols.dart';

import '../../../../../domain/group/services/group_service.dart';
import '../../../../../domain/user/models/contact.dart';
import '../../../../../infra/built_in_types/built_in_type_helpers.dart';
import '../../../../../infra/ui/text_utils.dart';
import '../../../../l10n/app_localizations.dart';
import '../../../../l10n/view_models/app_localizations_view_model.dart';
import '../../../../themes/index.dart';
import '../../../components/index.dart';
import '../../app.dart';
import '../contacts_page/view_models/contacts_view_model.dart';

class CreateGroupPage extends ConsumerStatefulWidget {
  const CreateGroupPage({super.key, required this.selectedContactIds});

  final Set<Int64> selectedContactIds;

  @override
  ConsumerState createState() => _CreateGroupPageState();
}

class _CreateGroupPageState extends ConsumerState<CreateGroupPage> {
  Set<Int64> _selectedUserContactIds = {};
  final List<UserContact> _selectedUserContacts = [];

  bool _isCreating = false;
  String _searchText = '';

  @override
  void initState() {
    super.initState();
    _selectedUserContactIds = widget.selectedContactIds.toSet();
  }

  @override
  Widget build(BuildContext context) {
    final theme = context.theme;
    final appThemeExtension = theme.appThemeExtension;
    final appLocalizations = ref.watch(appLocalizationsViewModel);
    final userContacts = ref.watch(userContactsViewModel);
    return Padding(
      padding: Sizes.paddingV8H16,
      child: Column(children: [
        Text(appLocalizations.createGroup),
        Sizes.sizedBoxH16,
        Expanded(
          child: DecoratedBox(
            decoration: BoxDecoration(
                border: Border.all(color: theme.dividerColor),
                borderRadius: Sizes.borderRadiusCircular4),
            child: Padding(
              padding: const EdgeInsets.symmetric(horizontal: 1),
              child: Column(
                children: [
                  IntrinsicHeight(
                    child: Row(
                      children: [
                        Expanded(
                            child: Padding(
                          padding: Sizes.paddingH8,
                          child: TSearchBar(
                            hintText: appLocalizations.search,
                            onChanged: _updateSearchText,
                          ),
                        )),
                        const TVerticalDivider(),
                        Expanded(
                          child: Text(
                            textAlign: TextAlign.center,
                            appLocalizations.selectedContacts,
                          ),
                        ),
                      ],
                    ),
                  ),
                  Expanded(
                    child: Row(
                      children: [
                        Expanded(
                            child: _buildContacts(
                                appThemeExtension, userContacts)),
                        const TVerticalDivider(),
                        Expanded(
                            child: _buildSelectedContacts(
                                appThemeExtension, appLocalizations))
                      ],
                    ),
                  ),
                ],
              ),
            ),
          ),
        ),
        Sizes.sizedBoxH12,
        _buildActions(context, theme, appLocalizations)
      ]),
    );
  }

  void close() {
    popTopIfNameMatched(createGroupDialogRouteName);
  }

  Future<void> _createGroup() async {
    _isCreating = true;
    setState(() {});
    await ref.read(groupServiceProvider)!.createGroup();
    _isCreating = false;
    close();
  }

  void _addSelectedContact(UserContact userContact) {
    if (_selectedUserContactIds.add(userContact.userId)) {
      _selectedUserContacts.add(userContact);
      setState(() {});
    }
  }

  void _removeSelectedContact(UserContact userContact) {
    if (_selectedUserContactIds.remove(userContact.userId)) {
      _selectedUserContacts.remove(userContact);
      setState(() {});
    }
  }

  void _updateSearchText(String value) {
    _searchText = value.toLowerCase().trim();
    setState(() {});
  }

  ListView _buildContacts(
      AppThemeExtension appThemeExtension, List<UserContact> userContacts) {
    final isSearching = _searchText.isNotBlank;
    final matchedUserContacts = isSearching
        ? userContacts.expand<(UserContact, List<TextSpan>)>((contact) {
            final spans = TextUtils.highlightSearchText(
                text: contact.name,
                searchText: _searchText,
                searchTextStyle: appThemeExtension.highlightTextStyle);
            if (spans.length == 1) {
              return [];
            }
            return [(contact, spans)];
          }).toList()
        : userContacts
            .map((contact) => (
                  contact,
                  [
                    TextSpan(text: contact.name),
                  ]
                ))
            .toList();

    final itemCount = matchedUserContacts.length;
    final matchedContactRecordIdToIndex = {
      for (var i = 0; i < itemCount; i++) matchedUserContacts[i].$1.recordId: i
    };
    return ListView.builder(
        itemCount: matchedUserContacts.length,
        findChildIndexCallback: (key) =>
            matchedContactRecordIdToIndex[(key as ValueKey<String>).value],
        itemBuilder: (BuildContext context, int index) {
          final (userContact, spans) = matchedUserContacts[index];
          return TListTile(
            key: Key(userContact.recordId),
            backgroundColor: Colors.white,
            padding: Sizes.paddingH8,
            height: 40,
            child: Row(
              spacing: 8,
              children: [
                TSimpleCheckbox(
                    value: _selectedUserContactIds.contains(userContact.userId),
                    onChanged: (value) {
                      if (value) {
                        _addSelectedContact(userContact);
                      } else {
                        _removeSelectedContact(userContact);
                      }
                    }),
                TAvatar(
                  id: userContact.id,
                  name: userContact.name,
                  size: TAvatarSize.small,
                ),
                Flexible(
                  child: Text.rich(
                    TextSpan(
                      children: spans,
                    ),
                    // userContact.name,
                    maxLines: 1,
                    overflow: TextOverflow.ellipsis,
                  ),
                ),
              ],
            ),
          );
        });
  }

  Widget _buildSelectedContacts(
      AppThemeExtension appThemeExtension, AppLocalizations appLocalizations) {
    final itemCount = _selectedUserContacts.length;
    final selectedUserContactIdToIndex = {
      for (var i = 0; i < itemCount; i++) _selectedUserContacts[i].userId: i
    };
    return ListView.builder(
        itemCount: itemCount,
        findChildIndexCallback: (key) =>
            selectedUserContactIdToIndex[(key as ValueKey<Int64>).value],
        itemBuilder: (BuildContext context, int index) {
          final userContact = _selectedUserContacts[index];
          return TListTile(
            key: ValueKey(userContact.id),
            backgroundColor: Colors.white,
            padding: Sizes.paddingH8,
            height: 40,
            child: Row(
              children: [
                TAvatar(
                  id: userContact.id,
                  name: userContact.name,
                  size: TAvatarSize.small,
                ),
                Sizes.sizedBoxW8,
                Expanded(
                  child: Text(
                    userContact.name,
                    // userContact.name,
                    maxLines: 1,
                    overflow: TextOverflow.ellipsis,
                  ),
                ),
                TIconButton(
                  iconData: Symbols.close_rounded,
                  iconColor: appThemeExtension.descriptionTextStyle.color!,
                  iconSize: 16,
                  addContainer: false,
                  onTap: () {
                    _removeSelectedContact(userContact);
                  },
                )
              ],
            ),
          );
        });
  }

  Widget _buildActions(BuildContext context, ThemeData theme,
          AppLocalizations appLocalizations) =>
      Row(
        mainAxisAlignment: MainAxisAlignment.end,
        spacing: 16,
        children: [
          TTextButton.outlined(
            theme: theme,
            text: appLocalizations.cancel,
            containerPadding: Sizes.paddingV4H8,
            containerWidth: 64,
            onTap: close,
          ),
          TTextButton(
            isLoading: _isCreating,
            disabled: _selectedUserContactIds.length <= 1,
            text: appLocalizations.create,
            containerPadding: Sizes.paddingV4H8,
            containerWidth: 64,
            onTap: _createGroup,
          )
        ],
      );
}

const createGroupDialogRouteName = '/create-group-dialog';

Future<void> showCreateGroupDialog({
  required BuildContext context,
  Set<Int64> selectedUserIds = const {},
}) =>
    showSimpleTDialog(
        routeName: createGroupDialogRouteName,
        context: context,
        child: CreateGroupPage(
          selectedContactIds: selectedUserIds,
        ));
