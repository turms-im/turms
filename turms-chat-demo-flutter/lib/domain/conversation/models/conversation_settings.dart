import '../../../infra/collection/list_holder.dart';
import '../../../infra/exception/stackful_exception.dart';
import '../../../infra/sqlite/user_database.dart';
import '../tables/conversation_setting_table.dart';
import 'conversation_setting.dart';

class ConversationSettings {
  const ConversationSettings(this._settingToValue);

  static (Map<IntListHolder, ConversationSettings>, StackfulException?)
  fromTableData(List<ConversationSettingTableData> records) {
    final idToSettings = <IntListHolder, ConversationSettings>{};
    StackfulException? exception;
    for (final record in records) {
      final setting = ConversationSetting.fromId(record.settingId);
      final recordValue = record.value;
      if (setting == null) {
        continue;
      }
      final settings = idToSettings.putIfAbsent(
        record.conversationId,
        () =>
            // don't use const as the map should be mutable.
            // ignore: prefer_const_constructors
            ConversationSettings({}),
      );
      try {
        _setSetting(settings, setting, recordValue.rawSqlValue);
      } on Exception catch (e, s) {
        exception ??= StackfulException(
          cause: Exception('Failed to set the conversation settings'),
          stackTrace: s,
          suppressed: [],
        );
        exception.addSuppressed(
          StackfulException(
            cause: Exception(
              'Failed to set the conversation setting "${setting.name}" with the value "$recordValue"',
            ),
            stackTrace: s,
            suppressed: [e],
          ),
        );
      }
    }
    return (idToSettings, exception);
  }

  static void _setSetting(
    ConversationSettings settings,
    ConversationSetting<dynamic, dynamic> setting,
    Object sqlValue,
  ) {
    final value = setting.convertSqlToValue(sqlValue);
    switch (setting) {
      case ConversationSetting.pinned:
        settings.pinned = value as bool;
        break;
      case ConversationSetting.enableNewMessageNotification:
        settings.enableNewMessageNotification = value as bool;
        break;
    }
  }

  final Map<ConversationSetting<dynamic, dynamic>, Object?> _settingToValue;

  bool? get pinned => _settingToValue[ConversationSetting.pinned] as bool?;

  set pinned(bool? value) {
    _settingToValue[ConversationSetting.pinned] = value;
  }

  bool? get enableNewMessageNotification =>
      _settingToValue[ConversationSetting.enableNewMessageNotification]
          as bool?;

  set enableNewMessageNotification(bool? value) {
    _settingToValue[ConversationSetting.enableNewMessageNotification] = value;
  }
}
