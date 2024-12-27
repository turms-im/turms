import 'package:flutter/material.dart';

import '../../../infra/exception/stackful_exception.dart';
import '../../../infra/shortcut/shortcut.dart';
import '../../../infra/sqlite/user_database.dart';
import 'setting_action_on_close.dart';
import 'user_setting.dart';

class UserSettings {
  const UserSettings(this._settingToValue);

  static (UserSettings, StackfulException?) fromTableData(
      List<UserSettingTableData> records) {
    // don't use const as the map should be mutable.
    // ignore: prefer_const_constructors
    final settings = UserSettings({});
    StackfulException? exception;
    for (final record in records) {
      final setting = UserSetting.fromId(record.id);
      final recordValue = record.value;
      if (setting == null) {
        continue;
      }
      try {
        _setSetting(settings, setting, recordValue.rawSqlValue);
      } on Exception catch (e, s) {
        exception ??= StackfulException(
            cause: Exception('Failed to set the user settings'),
            stackTrace: s,
            suppressed: []);
        exception.addSuppressed(StackfulException(
            cause: Exception(
                'Failed to set the user setting "${setting.name}" with the value "$recordValue"'),
            stackTrace: s,
            suppressed: [e]));
      }
    }
    return (settings, exception);
  }

  static void _setSetting(UserSettings settings,
      UserSetting<dynamic, dynamic> setting, Object sqlValue) {
    if (setting == UserSetting.launchOnStartup) {
      return;
    }
    final value = setting.convertSqlToValue(sqlValue);
    switch (setting) {
      case UserSetting.actionOnClose:
        settings.actionOnClose = value as SettingActionOnClose;
        break;
      case UserSetting.checkForUpdatesAutomatically:
        settings.checkForUpdatesAutomatically = value as bool;
        break;
      case UserSetting.launchOnStartup:
        // do nothing.
        // The setting should be detected according to whether autostart is enabled in system settings,
        // so it should not follow the stored setting.
        break;
      case UserSetting.locale:
        settings.locale = value as Locale;
        break;
      case UserSetting.newMessageNotification:
        settings.newMessageNotification = value as bool;
        break;
      case UserSetting.shortcutShowChatPage:
        settings.shortcutShowChatPage =
            value == null ? null : Shortcut(value as ShortcutActivator, true);
        break;
      case UserSetting.shortcutShowContactsPage:
        settings.shortcutShowContactsPage =
            value == null ? null : Shortcut(value as ShortcutActivator, true);
        break;
      case UserSetting.shortcutShowFilesPage:
        settings.shortcutShowFilesPage =
            value == null ? null : Shortcut(value as ShortcutActivator, true);
        break;
      case UserSetting.shortcutShowSettingsDialog:
        settings.shortcutShowSettingsDialog =
            value == null ? null : Shortcut(value as ShortcutActivator, true);
        break;
      case UserSetting.shortcutShowAboutDialog:
        settings.shortcutShowAboutDialog =
            value == null ? null : Shortcut(value as ShortcutActivator, true);
        break;
      case UserSetting.theme:
        settings.theme = value as ThemeMode;
        break;
      case UserSetting.messageRecentSearch:
        settings.messageRecentSearch = value as List<String>;
        break;
    }
  }

  final Map<UserSetting<dynamic, dynamic>, Object?> _settingToValue;

  SettingActionOnClose? get actionOnClose =>
      _settingToValue[UserSetting.actionOnClose] as SettingActionOnClose?;

  set actionOnClose(SettingActionOnClose? value) {
    _settingToValue[UserSetting.actionOnClose] = value;
  }

  bool? get checkForUpdatesAutomatically =>
      _settingToValue[UserSetting.checkForUpdatesAutomatically] as bool?;

  set checkForUpdatesAutomatically(bool? value) {
    _settingToValue[UserSetting.checkForUpdatesAutomatically] = value;
  }

  bool? get launchOnStartup =>
      _settingToValue[UserSetting.launchOnStartup] as bool?;

  set launchOnStartup(bool? value) {
    _settingToValue[UserSetting.launchOnStartup] = value;
  }

  Locale? get locale => _settingToValue[UserSetting.locale] as Locale?;

  set locale(Locale? value) => _settingToValue[UserSetting.locale] = value;

  bool? get newMessageNotification =>
      _settingToValue[UserSetting.newMessageNotification] as bool?;

  set newMessageNotification(bool? value) =>
      _settingToValue[UserSetting.newMessageNotification] = value;

  Shortcut get shortcutShowChatPage =>
      _settingToValue[UserSetting.shortcutShowChatPage] as Shortcut? ??
      Shortcut.unset;

  set shortcutShowChatPage(Shortcut? value) =>
      _settingToValue[UserSetting.shortcutShowChatPage] = value;

  Shortcut get shortcutShowContactsPage =>
      _settingToValue[UserSetting.shortcutShowContactsPage] as Shortcut? ??
      Shortcut.unset;

  set shortcutShowContactsPage(Shortcut? value) =>
      _settingToValue[UserSetting.shortcutShowContactsPage] = value;

  Shortcut get shortcutShowFilesPage =>
      _settingToValue[UserSetting.shortcutShowFilesPage] as Shortcut? ??
      Shortcut.unset;

  set shortcutShowFilesPage(Shortcut? value) =>
      _settingToValue[UserSetting.shortcutShowFilesPage] = value;

  Shortcut get shortcutShowSettingsDialog =>
      _settingToValue[UserSetting.shortcutShowSettingsDialog] as Shortcut? ??
      Shortcut.unset;

  set shortcutShowSettingsDialog(Shortcut? value) =>
      _settingToValue[UserSetting.shortcutShowSettingsDialog] = value;

  Shortcut get shortcutShowAboutDialog =>
      _settingToValue[UserSetting.shortcutShowAboutDialog] as Shortcut? ??
      Shortcut.unset;

  set shortcutShowAboutDialog(Shortcut? value) =>
      _settingToValue[UserSetting.shortcutShowAboutDialog] = value;

  ThemeMode? get theme => _settingToValue[UserSetting.theme] as ThemeMode?;

  set theme(ThemeMode? value) => _settingToValue[UserSetting.theme] = value;

  List<String>? get messageRecentSearch =>
      _settingToValue[UserSetting.messageRecentSearch] as List<String>?;

  set messageRecentSearch(List<String>? value) =>
      _settingToValue[UserSetting.messageRecentSearch] = value;
}
