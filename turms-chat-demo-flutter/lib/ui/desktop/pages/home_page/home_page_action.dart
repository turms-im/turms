import 'package:flutter/services.dart';
import 'package:flutter/widgets.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

import '../../../../domain/user/models/index.dart';
import '../../../../domain/user/models/user_setting.dart';
import 'about_page/about_page.dart';
import 'home_page_tab.dart';
import 'settings_page/settings_page.dart';
import 'shared_view_models/home_page_tab_view_model.dart';

enum HomePageAction {
  showChatPage(
      userSetting: UserSetting.shortcutShowChatPage,
      defaultShortcutActivator:
          SingleActivator(LogicalKeyboardKey.digit1, alt: true)),
  showContactsPage(
      userSetting: UserSetting.shortcutShowContactsPage,
      defaultShortcutActivator:
          SingleActivator(LogicalKeyboardKey.digit2, alt: true)),
  showFilesPage(
      userSetting: UserSetting.shortcutShowFilesPage,
      defaultShortcutActivator:
          SingleActivator(LogicalKeyboardKey.digit3, alt: true)),
  showSettingsDialog(
      userSetting: UserSetting.shortcutShowSettingsDialog,
      defaultShortcutActivator:
          SingleActivator(LogicalKeyboardKey.digit4, alt: true)),
  showAboutDialog(
      userSetting: UserSetting.shortcutShowAboutDialog,
      defaultShortcutActivator:
          SingleActivator(LogicalKeyboardKey.digit5, alt: true));

  const HomePageAction({
    required this.userSetting,
    required this.defaultShortcutActivator,
  });

  final UserSetting<ShortcutActivator, Uint8List> userSetting;
  final ShortcutActivator defaultShortcutActivator;
}

extension HomePageActionExtension on HomePageAction {
  void trigger({required BuildContext context, required WidgetRef ref}) {
    switch (this) {
      case HomePageAction.showChatPage:
        ref.read(homePageTabViewModel.notifier).state = HomePageTab.chat;
        break;
      case HomePageAction.showContactsPage:
        ref.read(homePageTabViewModel.notifier).state = HomePageTab.contacts;
        break;
      case HomePageAction.showFilesPage:
        ref.read(homePageTabViewModel.notifier).state = HomePageTab.files;
        break;
      case HomePageAction.showSettingsDialog:
        showSettingsDialog(context);
        break;
      case HomePageAction.showAboutDialog:
        showAppAboutDialog(context);
        break;
    }
  }
}
