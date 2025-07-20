import '../../../infra/built_in_types/built_in_type_helpers.dart';

/// [D] for the dart value type;
/// [S] for the SQL value type.
enum ConversationSetting<D, S> {
  pinned<bool, int>(0),
  enableNewMessageNotification<bool, int>(1);

  const ConversationSetting(this.id);

  final int id;

  static ConversationSetting<dynamic, dynamic>? fromId(int id) =>
      _idToSetting[id];

  S? convertValueToSql(D value) =>
      switch (this) {
            ConversationSetting.pinned => (value as bool).toInt(),
            ConversationSetting.enableNewMessageNotification =>
              (value as bool).toInt(),
          }
          as S?;

  D? convertSqlToValue(S value) =>
      switch (this) {
            ConversationSetting.pinned => (value as int).toBool(),
            ConversationSetting.enableNewMessageNotification =>
              (value as int).toBool(),
          }
          as D?;
}

final _idToSetting = {
  for (final record in ConversationSetting.values) record.id: record,
};
