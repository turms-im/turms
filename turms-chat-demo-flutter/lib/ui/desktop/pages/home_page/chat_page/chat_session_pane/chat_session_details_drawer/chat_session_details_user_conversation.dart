part of 'chat_session_details_drawer.dart';

class ChatSessionDetailsUserConversation extends ConsumerStatefulWidget {
  const ChatSessionDetailsUserConversation(
      {super.key, required this.conversation});

  final UserConversation conversation;

  @override
  ConsumerState<ChatSessionDetailsUserConversation> createState() =>
      _ChatSessionDetailsUserConversationState();
}

class _ChatSessionDetailsUserConversationState
    extends ConsumerState<ChatSessionDetailsUserConversation> {
  @override
  Widget build(BuildContext context) {
    final theme = context.theme;
    final appLocalizations = ref.watch(appLocalizationsViewModel);
    final loggedInUser = ref.watch(loggedInUserViewModel)!;
    final conversation = widget.conversation;
    final conversationId = conversation.id;
    final conversationSettings =
        ref.watch(idToConversationSettingsViewModel)[conversationId];
    const divider = THorizontalDivider();
    final contact = conversation.contact;
    return Column(
      children: [
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
        _buildAddParticipantItem(theme, appLocalizations.createGroup, () {
          showCreateGroupDialog(
              context: context, selectedUserIds: {contact.userId});
        }),
        _participantItemSpacing,
        _buildParticipantItem(loggedInUser),
        _participantItemSpacing,
        _buildParticipantItem(contact),
        const Spacer(),
        divider,
        SizedBox(
          width: double.infinity,
          child: TTextButton(
            containerPadding: Sizes.paddingV8,
            containerColor: Colors.transparent,
            containerColorHovered: Colors.transparent,
            text: appLocalizations.clearChatHistory,
            textStyle: context.appThemeExtension.dangerTextStyle,
          ),
        )
      ],
    );
  }

  Row _buildParticipantItem(User user) => Row(
        spacing: _participantItemElementSpacing,
        children: [
          UserProfilePopup(
              user: user, popupAnchor: Alignment.topRight, size: _avatarSize),
          Expanded(
              child: Text(
            user.name,
            overflow: TextOverflow.ellipsis,
            maxLines: 1,
            softWrap: false,
          )),
        ],
      );
}
