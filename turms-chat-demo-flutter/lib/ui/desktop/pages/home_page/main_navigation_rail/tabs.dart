import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:material_symbols_icons/symbols.dart';

import '../../../../../domain/user/view_models/logged_in_user_info_view_model.dart';
import '../../../../../infra/keyboard/shortcut_extensions.dart';
import '../../../../l10n/view_models/app_localizations_view_model.dart';

import '../../../../themes/index.dart';
import '../../../components/index.dart';
import '../about_page/about_page.dart';
import '../action_to_shortcut_view_model.dart';
import '../home_page_action.dart';
import '../home_page_tab.dart';
import '../settings_page/settings_page.dart';
import '../shared_view_models/home_page_tab_view_model.dart';

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

    final homePageTab = ref.watch(homePageTabViewModel);
    final isChatTab = homePageTab == HomePageTab.chat;
    final isContactsTab = homePageTab == HomePageTab.contacts;
    final isFilesTab = homePageTab == HomePageTab.files;
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
        TIconButton(
          iconData: Symbols.chat_rounded,
          iconFill: isChatTab,
          iconSize: 26,
          iconWeight: isChatTab ? 400 : 300,
          tooltip: shortcutShowChatPage == null
              ? appLocalizations.chats
              : '${appLocalizations.chats} (${shortcutShowChatPage.description})',
          onTap: () =>
              ref.read(homePageTabViewModel.notifier).state = HomePageTab.chat,
          iconColor: isChatTab
              ? theme.primaryColor
              : appThemeExtension.mainNavigationRailIconColor,
        ),
        TIconButton(
          iconData: Symbols.person_rounded,
          iconFill: isContactsTab,
          iconSize: 26,
          iconWeight: isContactsTab ? 400 : 300,
          tooltip: shortcutShowContactsPage == null
              ? appLocalizations.contacts
              : '${appLocalizations.contacts} (${shortcutShowContactsPage.description})',
          onTap: () => ref.read(homePageTabViewModel.notifier).state =
              HomePageTab.contacts,
          iconColor: isContactsTab
              ? theme.primaryColor
              : appThemeExtension.mainNavigationRailIconColor,
        ),
        TIconButton(
          iconData: Symbols.description_rounded,
          iconFill: isFilesTab,
          iconSize: 26,
          iconWeight: isFilesTab ? 400 : 300,
          tooltip: shortcutShowFilesPage == null
              ? appLocalizations.files
              : '${appLocalizations.files} (${shortcutShowFilesPage.description})',
          onTap: () =>
              ref.read(homePageTabViewModel.notifier).state = HomePageTab.files,
          iconColor: isFilesTab
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
