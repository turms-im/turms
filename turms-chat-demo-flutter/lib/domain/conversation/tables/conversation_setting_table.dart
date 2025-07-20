import 'package:drift/drift.dart';
import 'package:fixnum/fixnum.dart';

import '../../../infra/built_in_types/built_in_type_helpers.dart';
import '../../../infra/collection/list_holder.dart';
import '../../../infra/sqlite/user_database.dart';
import '../models/conversation.dart';

/// We don't use the columns: a nullable [groupId] and a nullable [userId]
/// as the primary key as SQLite doesn't allow a primary key contains a null value,
/// and you can validate by running the following query:
/// ```sql
///create table my_test
/// (
///     id1 INT default null,
///     id2 INT default null,
///     primary key (id1, id2)
/// ) without rowid , strict;
///
/// -- This query will fail with the following error in SQLite 3.45.1:
/// -- [19] [SQLITE_CONSTRAINT_NOTNULL] A NOT NULL constraint failed (NOT NULL constraint failed: my_test.id2)
/// INSERT INTO my_test VALUES (1,null);
/// ```
/// The table should only be used for development purposes without the server.
/// The client should fetch user settings dynamically from the server in
/// production.
class ConversationSettingTable extends Table {
  /// This can be either a group ID or a user (recipient) ID.
  late final contactId = int64()();

  late final isGroupConversation = boolean()();

  late final settingId = integer()();

  late final value = sqliteAny()();

  late final createdDate = dateTime()();

  late final lastModifiedDate = dateTime()();

  @override
  String get tableName => 'conversation_setting';

  @override
  Set<Column> get primaryKey => {contactId, isGroupConversation, settingId};

  @override
  bool get withoutRowId => true;

  @override
  bool get isStrict => true;
}

extension ConversationSettingTableDataExtension
    on ConversationSettingTableData {
  IntListHolder get conversationId {
    final id = contactId.toInt64();
    Int64? userId;
    Int64? groupId;
    if (isGroupConversation) {
      groupId = id;
    } else {
      userId = id;
    }
    return Conversation.generateId(userId: userId, groupId: groupId);
  }
}
