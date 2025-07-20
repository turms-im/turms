import 'package:drift/drift.dart';
import 'package:fixnum/fixnum.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

import '../../../infra/built_in_types/built_in_type_helpers.dart';
import '../../../infra/sqlite/user_message_database.dart';
import '../../../ui/desktop/pages/home_page/chat_page/chat_session_pane/message.dart';
import '../models/message_type.dart';

final _expressionCountAll = countAll();

class MessageRepository {
  MessageRepository(this._userMessageDatabase);

  final UserMessageDatabase _userMessageDatabase;

  Future<void> upsertMessages({required List<ChatMessage> messages}) async {
    final messagesToInsert = messages.map((message) {
      final isGroupMessage = message.isGroupMessage;
      return _getMessageTableCompanion(
        message.messageId,
        isGroupMessage,
        isGroupMessage ? message.groupId! : message.recipientId!,
        message.senderId,
        message.timestamp,
        message.text,
        message.records,
        message.type,
      );
    }).toList();
    await _userMessageDatabase.batch((batch) {
      batch.insertAll(
        _userMessageDatabase.messageTable,
        messagesToInsert,
        mode: InsertMode.insertOrReplace,
      );
    });
  }

  Future<void> upsertMessage({required ChatMessage message}) async {
    final isGroupMessage = message.isGroupMessage;
    return upsert(
      messageId: message.messageId,
      isGroupMessage: isGroupMessage,
      toId: isGroupMessage ? message.groupId! : message.recipientId!,
      senderId: message.senderId,
      messageType: message.type,
      text: message.text,
      records: message.records,
      createdDate: message.timestamp,
    );
  }

  Future<void> upsert({
    required Int64 messageId,
    required bool isGroupMessage,
    required Int64 toId,
    required Int64 senderId,
    required MessageType messageType,
    String? text,
    List<Uint8List>? records,
    required DateTime createdDate,
  }) async {
    await _userMessageDatabase
        .into(_userMessageDatabase.messageTable)
        .insertOnConflictUpdate(
          _getMessageTableCompanion(
            messageId,
            isGroupMessage,
            toId,
            senderId,
            createdDate,
            text,
            records,
            messageType,
          ),
        );
  }

  MessageTableCompanion _getMessageTableCompanion(
    Int64 messageId,
    bool isGroupMessage,
    Int64 contactId,
    Int64 senderId,
    DateTime createdDate,
    String? text,
    List<Uint8List>? records,
    MessageType messageType,
  ) => MessageTableCompanion.insert(
    id: messageId,
    isGroupMessage: isGroupMessage,
    contactId: contactId,
    senderId: senderId,
    createdDate: createdDate,
    txt: text == null ? const Value.absent() : Value(text),
    records: records == null ? const Value.absent() : Value(records),
    type: messageType,
  );

  Future<int> delete({
    List<Int64>? ids,
    Int64? idEnd,
    Int64? groupId,
    List<Int64>? participantIds,
  }) async {
    final tbl = _userMessageDatabase.messageTable;
    final statement = _userMessageDatabase.delete(tbl)
      ..where(
        (t) => Expression.and([
          if (ids != null) t.id.isIn(ids.map((e) => e.toBigInt()).toList()),
          if (idEnd != null) t.id.isSmallerThanValue(idEnd.toBigInt()),
          if (groupId != null) ...[
            tbl.contactId.equalsValue(groupId),
            tbl.isGroupMessage.equals(true),
          ] else if (participantIds != null)
            Expression.or([
              Expression.and([
                tbl.contactId.equalsValue(participantIds[0]),
                tbl.senderId.equalsValue(participantIds[1]),
                tbl.isGroupMessage.equals(false),
              ]),
              Expression.and([
                tbl.contactId.equalsValue(participantIds[1]),
                tbl.senderId.equalsValue(participantIds[0]),
                tbl.isGroupMessage.equals(false),
              ]),
            ]),
        ]),
      );
    return statement.go();
  }

  Future<List<CountAndSearchLatestMessageResult>> countAndSearchLatestMessage({
    Int64? idStart,
    Int64? senderId,
    Int64? groupId,
    List<Int64>? participantIds,
    String? text,
    MessageType? messageType,
    DateTime? createdDateStart,
    DateTime? createdDateEnd,
    required int limit,
  }) async {
    final messageTable = _userMessageDatabase.messageTable;
    final select = messageTable.select().addColumns([_expressionCountAll])
      ..groupBy([messageTable.contactId, messageTable.isGroupMessage])
      ..where(
        _buildExpressionGroupIdOrParticipantIds(
          messageTable,
          idStart,
          groupId,
          participantIds,
          text,
          messageType,
          createdDateStart,
          createdDateEnd,
        ),
      )
      ..orderBy([
        OrderingTerm(
          expression: messageTable.createdDate,
          mode: OrderingMode.desc,
        ),
        // Order by id to ensure that the messages are in a consistent order
        // even the timestamp of the messages is the same.
        OrderingTerm(expression: messageTable.id),
      ])
      ..limit(limit);
    final records = await select.get();
    return records.map((record) {
      final message = record.readTable(messageTable);
      final count = record.read(_expressionCountAll)!;
      return CountAndSearchLatestMessageResult(message: message, count: count);
    }).toList();
  }

  Future<int> count({
    Int64? idStart,
    Int64? senderId,
    Int64? groupId,
    List<Int64>? participantIds,
    String? text,
    MessageType? messageType,
    DateTime? createdDateStart,
    DateTime? createdDateEnd,
    required int limit,
  }) async {
    assert(
      groupId == null
          ? (participantIds != null && participantIds.isNotEmpty)
          : (participantIds == null || participantIds.isEmpty),
    );
    final messageTable = _userMessageDatabase.messageTable;
    final count = messageTable.count(
      where: (tbl) => _buildExpressionGroupIdOrParticipantIds(
        tbl,
        idStart,
        groupId,
        participantIds,
        text,
        messageType,
        createdDateStart,
        createdDateEnd,
      ),
    );
    return count.getSingle();
  }

  Future<List<MessageTableData>> selectMessages({
    Int64? idStart,
    Int64? senderId,
    Int64? groupId,
    List<Int64>? participantIds,
    String? text,
    MessageType? messageType,
    DateTime? createdDateStart,
    DateTime? createdDateEnd,
    required int limit,
  }) async {
    assert(
      groupId == null
          ? (participantIds != null && participantIds.isNotEmpty)
          : (participantIds == null || participantIds.isEmpty),
    );
    final statement = _userMessageDatabase.select(_userMessageDatabase.messageTable)
      ..where(
        (tbl) => _buildExpressionGroupIdOrParticipantIds(
          tbl,
          idStart,
          groupId,
          participantIds,
          text,
          messageType,
          createdDateStart,
          createdDateEnd,
        ),
      )
      ..orderBy([
        (record) => OrderingTerm(
          expression: record.createdDate,
          mode: OrderingMode.desc,
        ),
        (record) =>
            // Order by id to ensure that the messages are in a consistent order
            // even the timestamp of the messages is the same.
            OrderingTerm(expression: record.id),
      ])
      ..limit(limit);
    return statement.get();
  }

  Future<List<MessageTableData>> selectAll() =>
      _userMessageDatabase.select(_userMessageDatabase.messageTable).get();

  Expression<bool> _buildExpressionGroupIdOrParticipantIds(
    $MessageTableTable tbl,
    Int64? idStart,
    Int64? groupId,
    List<Int64>? participantIds,
    String? text,
    MessageType? messageType,
    DateTime? createdDateStart,
    DateTime? createdDateEnd,
  ) => Expression.and([
    if (idStart != null) tbl.id.isBiggerThanValue(idStart.toBigInt()),
    if (groupId != null) ...[
      tbl.contactId.equalsValue(groupId),
      tbl.isGroupMessage.equals(true),
    ] else if (participantIds != null)
      Expression.or([
        Expression.and([
          tbl.contactId.equalsValue(participantIds[0]),
          tbl.senderId.equalsValue(participantIds[1]),
          tbl.isGroupMessage.equals(false),
        ]),
        Expression.and([
          tbl.contactId.equalsValue(participantIds[1]),
          tbl.senderId.equalsValue(participantIds[0]),
          tbl.isGroupMessage.equals(false),
        ]),
      ]),
    if (text != null) tbl.txt.contains(text),
    if (messageType != null) tbl.type.equalsValue(messageType),
    if (createdDateStart != null)
      tbl.createdDate.isBiggerOrEqualValue(createdDateStart),
    if (createdDateEnd != null)
      tbl.createdDate.isSmallerOrEqualValue(createdDateEnd),
  ]);
}

class CountAndSearchLatestMessageResult {
  const CountAndSearchLatestMessageResult({
    required this.message,
    required this.count,
  });

  final MessageTableData message;
  final int count;
}

final messageRepositoryProvider = StateProvider<MessageRepository?>(
  (ref) => null,
);
