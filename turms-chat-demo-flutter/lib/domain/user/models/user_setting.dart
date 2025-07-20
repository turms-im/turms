import 'dart:convert';
import 'dart:typed_data';

import 'package:flutter/material.dart';

import '../../../infra/built_in_types/built_in_type_helpers.dart';
import '../../../infra/keyboard/shortcut_extensions.dart';
import '../../../infra/keyboard/shortcut_utils.dart';
import '../../../ui/l10n/locale_utils.dart';
import 'setting_action_on_close.dart';

/// [D] for the dart value type;
/// [S] for the SQL value type.
enum UserSetting<D, S> {
  // TODO: update order
  actionOnClose<SettingActionOnClose, int>(0),
  checkForUpdatesAutomatically<bool, int>(1),
  launchOnStartup<Never, Never>(2),
  locale<Locale, String>(3),
  newMessageNotification<bool, int>(4),
  shortcutShowChatPage<ShortcutActivator, Uint8List>(5),
  shortcutShowContactsPage<ShortcutActivator, Uint8List>(6),
  shortcutShowFilesPage<ShortcutActivator, Uint8List>(7),
  shortcutShowSettingsDialog<ShortcutActivator, Uint8List>(8),
  shortcutShowAboutDialog<ShortcutActivator, Uint8List>(9),
  theme<ThemeMode, int>(10),
  messageRecentSearch<List<String>, String>(11);

  const UserSetting(this.id);

  final int id;

  static UserSetting<dynamic, dynamic>? fromId(int id) => _idToSetting[id];

  S? convertValueToSql(D value) =>
      switch (this) {
            UserSetting.actionOnClose => (value as SettingActionOnClose).id,
            UserSetting.checkForUpdatesAutomatically => (value as bool).toInt(),
            UserSetting.launchOnStartup => throw UnsupportedError(
              'Should not be called',
            ),
            UserSetting.locale => (value as Locale).toLanguageTag(),
            UserSetting.newMessageNotification => (value as bool).toInt(),
            UserSetting.shortcutShowChatPage ||
            UserSetting.shortcutShowContactsPage ||
            UserSetting.shortcutShowFilesPage ||
            UserSetting.shortcutShowSettingsDialog ||
            UserSetting.shortcutShowAboutDialog =>
              value == null ? null : (value as ShortcutActivator).toSqlBlob(),
            UserSetting.theme => switch (value as ThemeMode) {
              ThemeMode.system => 0,
              ThemeMode.light => 1,
              ThemeMode.dark => 2,
            },
            UserSetting.messageRecentSearch => jsonEncode(
              value as List<String>,
            ),
          }
          as S?;

  D? convertSqlToValue(S value) =>
      switch (this) {
            UserSetting.actionOnClose => SettingActionOnClose.fromId(
              value as int,
            ),
            UserSetting.checkForUpdatesAutomatically => (value as int).toBool(),
            UserSetting.launchOnStartup => throw UnsupportedError(
              'Should not be called',
            ),
            UserSetting.locale => LocaleUtils.fromLanguageTag(value as String),
            UserSetting.newMessageNotification => (value as int).toBool(),
            UserSetting.shortcutShowChatPage ||
            UserSetting.shortcutShowContactsPage ||
            UserSetting.shortcutShowFilesPage ||
            UserSetting.shortcutShowSettingsDialog ||
            UserSetting.shortcutShowAboutDialog => ShortcutUtils.fromSqlBlob(
              value as Uint8List,
            ),
            UserSetting.theme => switch (value as int) {
              0 => ThemeMode.system,
              1 => ThemeMode.light,
              2 => ThemeMode.dark,
              _ => throw UnsupportedError('Unknown theme mode: $value'),
            },
            UserSetting.messageRecentSearch =>
              jsonDecode(value as String) as List<String>,
          }
          as D?;
}

final _idToSetting = {
  for (final record in UserSetting.values) record.id: record,
};
