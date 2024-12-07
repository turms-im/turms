import 'package:flutter_riverpod/flutter_riverpod.dart';

import '../../../../domain/user/view_models/user_settings_view_model.dart';
import '../../../../infra/shortcut/shortcut.dart';
import 'home_page_action.dart';

final actionToShortcutViewModel =
    StateProvider<Map<HomePageAction, Shortcut>>((ref) {
  final actionToShortcut = <HomePageAction, Shortcut>{};
  final userSettings = ref.watch(userSettingsViewModel);
  // We allow the user to unset shortcuts,
  // so don't assign default shortcut here.
  if (userSettings == null) {
    return actionToShortcut;
  }
  actionToShortcut[HomePageAction.showChatPage] =
      userSettings.shortcutShowChatPage;
  actionToShortcut[HomePageAction.showContactsPage] =
      userSettings.shortcutShowContactsPage;
  actionToShortcut[HomePageAction.showFilesPage] =
      userSettings.shortcutShowFilesPage;
  actionToShortcut[HomePageAction.showSettingsDialog] =
      userSettings.shortcutShowSettingsDialog;
  actionToShortcut[HomePageAction.showAboutDialog] =
      userSettings.shortcutShowAboutDialog;
  return actionToShortcut;
});
