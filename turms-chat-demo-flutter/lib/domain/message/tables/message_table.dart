import 'package:drift/drift.dart';

import '../../../infra/sqlite/converter/int64_converter.dart';
import '../../../infra/sqlite/converter/uint8_matrix_converter.dart';
import '../models/message_type.dart';

@TableIndex(name: 'sender_id', columns: {#senderId})
@TableIndex(name: 'contact_id', columns: {#contactId})
@TableIndex(name: 'created_date', columns: {#createdDate})
class MessageTable extends Table {
  late final id = int64().map(const Int64Converter())();

  late final isGroupMessage = boolean()();

  late final senderId = int64().map(const Int64Converter())();

  /// 1. This can be either a group ID or a user (recipient) ID.
  /// 2. We use [contactId] instead of the columns [groupId] and [recipientId],
  /// so we don't need to creat two indexes.
  late final contactId = int64().map(const Int64Converter())();

  @JsonKey('text')
  late final txt = text().named('text').nullable()();

  late final records = blob().map(const Uint8MatrixConverter()).nullable()();

  late final type = intEnum<MessageType>()();

  late final createdDate = dateTime()();

  @override
  String get tableName => 'message';

  @override
  Set<Column> get primaryKey => {id};

  @override
  bool get withoutRowId => true;

  @override
  bool get isStrict => true;
}
