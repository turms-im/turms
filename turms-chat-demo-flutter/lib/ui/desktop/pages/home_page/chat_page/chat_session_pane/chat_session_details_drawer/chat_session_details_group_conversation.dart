part of 'chat_session_details_drawer.dart';

const _avatarSize = TAvatarSize.small;
const _participantItemElementSpacing = 8.0;
const _participantItemSpacing = Sizes.sizedBoxH4;

class ChatSessionDetailsGroupConversation extends ConsumerStatefulWidget {
  const ChatSessionDetailsGroupConversation(
      {super.key, required this.conversation});

  final GroupConversation conversation;

  @override
  ConsumerState<ChatSessionDetailsGroupConversation> createState() =>
      _ChatSessionDetailsGroupConversationState();
}

class _ChatSessionDetailsGroupConversationState
    extends ConsumerState<ChatSessionDetailsGroupConversation> {
  @override
  Widget build(BuildContext context) {
    final theme = context.theme;
    final appThemeExtension = theme.appThemeExtension;
    final appLocalizations = ref.watch(appLocalizationsViewModel);
    final loggedInUser = ref.watch(loggedInUserViewModel)!;
    final conversation = widget.conversation;
    final conversationId = conversation.id;
    final conversationSettings =
        ref.watch(idToConversationSettingsViewModel)[conversationId];

    const divider = THorizontalDivider();
    final contact = conversation.contact;
    final intro = contact.intro;

    final locale = appLocalizations.localeName;
    final members = contact.members;
    final memberToIndex =
        ComparableUtils.sortByStringsAsMap(locale, members, (m) => m.name);
    members.sort(
      (a, b) {
        if (a.isAdmin) {
          if (b.isAdmin) {
            return memberToIndex[a]!.compareTo(memberToIndex[b]!);
          } else {
            return -1;
          }
        } else if (b.isAdmin) {
          return 1;
        } else {
          return memberToIndex[a]!.compareTo(memberToIndex[b]!);
        }
      },
    );
    final isCurrentUserAdmin =
        members.any((m) => m.isAdmin && m.userId == loggedInUser.userId);

    return Column(
      children: [
        isCurrentUserAdmin
            ? _ChatSessionDetailsGroupConversationName(
                groupName: contact.name,
              )
            : SelectionArea(
                contextMenuBuilder: buildContextMenuForSelectableRegion,
                child: Text(
                  contact.name,
                  maxLines: 1,
                  overflow: TextOverflow.ellipsis,
                )),
        if (intro.isNotBlank) ...[
          Sizes.sizedBoxH8,
          SizedBox(
            child: SelectionArea(
              contextMenuBuilder: buildContextMenuForSelectableRegion,
              child: Text(
                intro,
                maxLines: 3,
                overflow: TextOverflow.ellipsis,
                style: appThemeExtension.descriptionTextStyle,
              ),
            ),
          )
        ],
        Sizes.sizedBoxH8,
        divider,
        Sizes.sizedBoxH8,
        Row(
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          children: [
            Text(appLocalizations.pin),
            TSwitch(
              value: conversationSettings?.pinned ?? false,
              onChanged: (value) {
                ref.read(conversationServiceProvider)!.updateSettingPinned(
                      conversationId: conversationId,
                      newValue: value,
                      contact: contact,
                    );
              },
            ),
          ],
        ),
        Sizes.sizedBoxH4,
        Row(
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          children: [
            Text(appLocalizations.muteNotifications),
            TSwitch(
              value:
                  conversationSettings?.enableNewMessageNotification ?? false,
              onChanged: (value) {
                ref
                    .read(conversationServiceProvider)!
                    .updateSettingEnableNewMessageNotification(
                      conversationId: conversationId,
                      newValue: value,
                      contact: contact,
                    );
              },
            ),
          ],
        ),
        Sizes.sizedBoxH4,
        divider,
        Sizes.sizedBoxH8,
        Expanded(
            child: _ChatSessionDetailsGroupConversationMemberList(members)),
        Sizes.sizedBoxH8,
        divider,
        SizedBox(
          width: double.infinity,
          child: TTextButton(
            containerPadding: Sizes.paddingV8,
            containerColor: Colors.transparent,
            containerColorHovered: Colors.transparent,
            text: appLocalizations.clearChatHistory,
            textStyle: appThemeExtension.dangerTextStyle,
          ),
        ),
        divider,
        SizedBox(
          width: double.infinity,
          child: TTextButton(
            containerPadding: Sizes.paddingV8,
            containerColor: Colors.transparent,
            containerColorHovered: Colors.transparent,
            text: appLocalizations.leaveGroup,
            textStyle: appThemeExtension.dangerTextStyle,
          ),
        )
      ],
    );
  }
}

class _ChatSessionDetailsGroupConversationMemberList
    extends ConsumerStatefulWidget {
  const _ChatSessionDetailsGroupConversationMemberList(this.members);

  final List<GroupMember> members;

  @override
  ConsumerState createState() =>
      _ChatSessionDetailsGroupConversationMemberListState();
}

class _ChatSessionDetailsGroupConversationMemberListState
    extends ConsumerState<_ChatSessionDetailsGroupConversationMemberList> {
  String _searchText = '';

  @override
  Widget build(BuildContext context) {
    final theme = context.theme;
    final appThemeExtension = theme.appThemeExtension;
    final appLocalizations = ref.watch(appLocalizationsViewModel);

    final isSearching = _searchText.isNotBlank;
    final matchedMembers = isSearching
        ? widget.members.expand<_StyledMember>((member) {
            final nameTextSpans = TextUtils.highlightSearchText(
                text: member.name,
                searchText: _searchText,
                searchTextStyle: appThemeExtension.highlightTextStyle);
            if (nameTextSpans.length == 1) {
              return [];
            }
            return [
              _StyledMember(member: member, nameTextSpans: nameTextSpans)
            ];
          }).toList()
        : widget.members
            .map((member) => _StyledMember(
                member: member, nameTextSpans: [TextSpan(text: member.name)]))
            .toList();

    final matchedMemberCount = matchedMembers.length;
    final matchedMemberIdToIndex = {
      for (var i = 0; i < matchedMemberCount; i++)
        matchedMembers[i].member.userId: i
    };
    return Column(
      children: [
        TSearchBar(
          hintText: appLocalizations.search,
          onChanged: (value) {
            _searchText = value;
            setState(() {});
          },
        ),
        Sizes.sizedBoxH8,
        if (!isSearching) ...[
          _buildAddParticipantItem(
            theme,
            appLocalizations.addNewMember,
            () {
              // TODO
            },
          ),
          _participantItemSpacing
        ],
        Expanded(
            child: isSearching && matchedMemberCount == 0
                ? Center(
                    child: Text(
                    appLocalizations.noMatchingGroupMembersFound,
                    style: appThemeExtension.descriptionTextStyle,
                  ))
                : ListView.separated(
                    // Used to not overlay on the scrollbar
                    padding: const EdgeInsets.only(right: 12),
                    itemCount: matchedMemberCount,
                    findChildIndexCallback: (key) =>
                        matchedMemberIdToIndex[(key as ValueKey<Int64>).value],
                    itemBuilder: (context, index) {
                      final item = matchedMembers[index];
                      final member = item.member;
                      return Row(
                        spacing: _participantItemElementSpacing,
                        children: [
                          UserProfilePopup(
                              user: member,
                              popupAnchor: Alignment.topRight,
                              size: _avatarSize),
                          Expanded(
                              child: Text.rich(
                            TextSpan(children: item.nameTextSpans),
                            overflow: TextOverflow.ellipsis,
                            maxLines: 1,
                            softWrap: false,
                          )),
                          if (member.isAdmin)
                            Icon(
                              Symbols.supervisor_account_rounded,
                              size: 22,
                              color: Colors.yellow.shade800,
                            )
                        ],
                      );
                    },
                    separatorBuilder: (BuildContext context, int index) =>
                        _participantItemSpacing,
                  ))
      ],
    );
  }
}

Row _buildAddParticipantItem(
        ThemeData theme, String hint, VoidCallback onTap) =>
    Row(
      spacing: _participantItemElementSpacing,
      children: [
        TIconButton(
          iconData: Symbols.person_add_rounded,
          containerSize: Size.square(_avatarSize.containerSize),
          iconSize: 20,
          iconColor: Colors.grey.shade600,
          containerBorder: Border.all(color: theme.dividerColor),
          containerBorderHovered: Border.all(color: theme.primaryColor),
          onTap: onTap,
        ),
        Flexible(child: Text(hint))
      ],
    );

class _ChatSessionDetailsGroupConversationName extends ConsumerStatefulWidget {
  const _ChatSessionDetailsGroupConversationName({required this.groupName});

  final String groupName;

  @override
  ConsumerState<_ChatSessionDetailsGroupConversationName> createState() =>
      _ChatSessionDetailsGroupConversationNameState();
}

class _ChatSessionDetailsGroupConversationNameState
    extends ConsumerState<_ChatSessionDetailsGroupConversationName> {
  TextEditingController? _textEditingController;
  bool _editingGroupName = false;
  bool _isHovered = false;

  @override
  void dispose() {
    _textEditingController?.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) => _editingGroupName
      ? TTextField(
          textEditingController: _textEditingController!,
          autofocus: true,
          onSubmitted: (value) async {
            if (value.isBlank || value == widget.groupName) {
              _editingGroupName = false;
              setState(() {});
              return;
            }
            unawaited(ref.read(groupServiceProvider)!.updateGroupName(value));
            _editingGroupName = false;
            setState(() {});
          },
        )
      : MouseRegion(
          onEnter: (_) => setState(() => _isHovered = true),
          onExit: (_) => setState(() => _isHovered = false),
          child: Row(
            children: [
              Flexible(
                  child: SelectionArea(
                      contextMenuBuilder: buildContextMenuForSelectableRegion,
                      child: Text(
                        widget.groupName,
                        maxLines: 1,
                        overflow: TextOverflow.ellipsis,
                      ))),
              if (_isHovered)
                MouseRegion(
                  cursor: SystemMouseCursors.click,
                  child: GestureDetector(
                    onTap: () {
                      final name = widget.groupName;
                      _textEditingController ??=
                          TextEditingController(text: name)
                            ..selection = TextSelection(
                                baseOffset: 0, extentOffset: name.length);
                      _editingGroupName = true;
                      setState(() {});
                    },
                    child: const Icon(
                      Symbols.edit_rounded,
                      size: 18,
                    ),
                  ),
                )
            ],
          ),
        );
}

class _StyledMember {
  const _StyledMember({required this.member, required this.nameTextSpans});

  final GroupMember member;
  final List<TextSpan> nameTextSpans;
}
