import 'package:drift/drift.dart';
import 'package:fixnum/fixnum.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

import '../../../infra/built_in_types/built_in_type_helpers.dart';
import '../../../infra/sqlite/user_database.dart';
import '../models/conversation_setting.dart';

class ConversationSettingRepository {
  ConversationSettingRepository(this._userDatabase);

  final UserDatabase _userDatabase;

  Future<void> upsert({
    required Int64 contactId,
    required bool isGroupConversation,
    required ConversationSetting<dynamic, dynamic> setting,
    required dynamic settingValue,
  }) async {
    final sqlValue = setting.convertValueToSql(settingValue) as Object;
    final now = DateTime.now();
    await _userDatabase
        .into(_userDatabase.conversationSettingTable)
        .insert(
          ConversationSettingTableCompanion.insert(
            contactId: contactId.toBigInt(),
            isGroupConversation: isGroupConversation,
            settingId: setting.id,
            value: DriftAny(sqlValue),
            createdDate: now,
            lastModifiedDate: now,
          ),
          onConflict: DoUpdate(
            (old) => ConversationSettingTableCompanion.custom(
              value: Constant(DriftAny(sqlValue)),
              lastModifiedDate: Constant(now),
            ),
          ),
        );
  }

  Future<int> delete({
    required Int64 userId,
    required Int64 contactId,
    required bool isGroupConversation,
    required ConversationSetting<dynamic, dynamic> setting,
  }) async {
    final delete = _userDatabase.delete(_userDatabase.conversationSettingTable)
      ..where(
        (t) => Expression.and([
          t.contactId.equals(contactId.toBigInt()),
          t.isGroupConversation.equals(isGroupConversation),
          t.settingId.equals(setting.id),
        ]),
      );
    return delete.go();
  }

  Future<List<ConversationSettingTableData>> selectAll() =>
      _userDatabase.select(_userDatabase.conversationSettingTable).get();
}

final conversationSettingRepositoryProvider =
    StateProvider<ConversationSettingRepository?>((ref) => null);
