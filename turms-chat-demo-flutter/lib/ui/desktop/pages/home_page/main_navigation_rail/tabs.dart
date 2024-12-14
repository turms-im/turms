import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:material_symbols_icons/symbols.dart';

import '../../../../../domain/user/view_models/logged_in_user_info_view_model.dart';
import '../../../../../infra/keyboard/shortcut_extensions.dart';
import '../../../../l10n/app_localizations.dart';
import '../../../../l10n/view_models/app_localizations_view_model.dart';
import '../../../../themes/index.dart';
import '../../../components/index.dart';
import '../about_page/about_page.dart';
import '../action_to_shortcut_view_model.dart';
import '../chat_page/view_models/conversations_data_view_model.dart';
import '../home_page_action.dart';
import '../home_page_tab.dart';
import '../settings_page/settings_page.dart';
import '../shared_view_models/current_home_page_tab_view_model.dart';

class Tabs extends ConsumerStatefulWidget {
  const Tabs({super.key});

  @override
  ConsumerState createState() => _TabsState();
}

class _TabsState extends ConsumerState<Tabs> {
  @override
  Widget build(BuildContext context) {
    final theme = context.theme;
    final appThemeExtension = theme.appThemeExtension;

    final currentHomePageTab = ref.watch(currentHomePageTabViewModel);
    final isChatTabSelected = currentHomePageTab == HomePageTab.chat;
    final isContactsTabSelected = currentHomePageTab == HomePageTab.contacts;
    final isFilesTabSelected = currentHomePageTab == HomePageTab.files;
    final appLocalizations = ref.watch(appLocalizationsViewModel);
    final actionToShortcut = ref.watch(actionToShortcutViewModel);

    final shortcutShowChatPage =
        actionToShortcut[HomePageAction.showChatPage]?.shortcutActivator;
    final shortcutShowContactsPage =
        actionToShortcut[HomePageAction.showContactsPage]?.shortcutActivator;
    final shortcutShowFilesPage =
        actionToShortcut[HomePageAction.showFilesPage]?.shortcutActivator;
    return Column(mainAxisAlignment: MainAxisAlignment.spaceBetween, children: [
      Column(spacing: 4, children: [
        _TabChatButton(
          theme: theme,
          appThemeExtension: appThemeExtension,
          appLocalizations: appLocalizations,
          isSelected: isChatTabSelected,
          shortcutShowChatPage: shortcutShowChatPage,
        ),
        TIconButton(
          iconData: Symbols.person_rounded,
          iconFill: isContactsTabSelected,
          iconSize: 26,
          iconWeight: isContactsTabSelected ? 400 : 300,
          tooltip: shortcutShowContactsPage == null
              ? appLocalizations.contacts
              : '${appLocalizations.contacts} (${shortcutShowContactsPage.description})',
          onTap: () => ref.read(currentHomePageTabViewModel.notifier).state =
              HomePageTab.contacts,
          iconColor: isContactsTabSelected
              ? theme.primaryColor
              : appThemeExtension.mainNavigationRailIconColor,
        ),
        TIconButton(
          iconData: Symbols.description_rounded,
          iconFill: isFilesTabSelected,
          iconSize: 26,
          iconWeight: isFilesTabSelected ? 400 : 300,
          tooltip: shortcutShowFilesPage == null
              ? appLocalizations.files
              : '${appLocalizations.files} (${shortcutShowFilesPage.description})',
          onTap: () => ref.read(currentHomePageTabViewModel.notifier).state =
              HomePageTab.files,
          iconColor: isFilesTabSelected
              ? theme.primaryColor
              : appThemeExtension.mainNavigationRailIconColor,
        ),
      ]),
      TMenuPopup(
        offset: const Offset(
            Sizes.mainNavigationRailWidth / 2 +
                Sizes.mainNavigationRailElementPopupOffsetX,
            0),
        padding: Sizes.paddingV8H16,
        targetAnchor: Alignment.topCenter,
        followerAnchor: Alignment.bottomLeft,
        constrainFollowerWithTargetWidth: false,
        entries: [
          TMenuEntry(
            value: 0,
            label: appLocalizations.settings,
            onSelected: () {
              showSettingsDialog(context);
            },
          ),
          TMenuEntry(
            value: 1,
            label: appLocalizations.about,
            onSelected: () {
              showAppAboutDialog(context);
            },
          ),
          TMenuEntry.separator,
          TMenuEntry(
            value: 2,
            label: appLocalizations.logOut,
            onSelected: () {
              // TODO: Reset all states related to the logged-in user.
              ref.read(loggedInUserViewModel.notifier).state = null;
            },
          ),
        ],
        anchor: TIconButton(
          iconData: Symbols.menu_rounded,
          iconSize: 26,
          iconWeight: 300,
          tooltip: appLocalizations.settings,
          iconColor: appThemeExtension.mainNavigationRailIconColor,
        ),
      ),
    ]);
  }
}

class _TabChatButton extends ConsumerStatefulWidget {
  const _TabChatButton({
    required this.theme,
    required this.appThemeExtension,
    required this.appLocalizations,
    required this.isSelected,
    required this.shortcutShowChatPage,
  });

  final ThemeData theme;
  final AppThemeExtension appThemeExtension;
  final AppLocalizations appLocalizations;
  final bool isSelected;
  final ShortcutActivator? shortcutShowChatPage;

  @override
  ConsumerState createState() => _TabChatButtonState();
}

class _TabChatButtonState extends ConsumerState<_TabChatButton> {
  @override
  Widget build(BuildContext context) {
    final appLocalizations = widget.appLocalizations;
    final shortcutShowChatPage = widget.shortcutShowChatPage;
    final conversations = ref.watch(conversationsDataViewModel).value ?? [];
    final hasUnreadMessages = conversations
        .any((conversation) => conversation.unreadMessageCount > 0);
    return TIconButton(
        iconData: Symbols.chat_rounded,
        iconFill: widget.isSelected,
        iconSize: 26,
        iconWeight: widget.isSelected ? 400 : 300,
        tooltip: shortcutShowChatPage == null
            ? appLocalizations.chats
            : '${appLocalizations.chats} (${shortcutShowChatPage.description})',
        // Don't show the unread message count because
        // the number will make some users feel anxious.
        showBadge: hasUnreadMessages,
        onTap: () => ref.read(currentHomePageTabViewModel.notifier).state =
            HomePageTab.chat,
        iconColor: widget.isSelected
            ? widget.theme.primaryColor
            : widget.appThemeExtension.mainNavigationRailIconColor);
  }
}