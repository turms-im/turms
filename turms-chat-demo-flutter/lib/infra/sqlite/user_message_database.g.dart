// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'user_message_database.dart';

// ignore_for_file: type=lint
class $MessageTableTable extends MessageTable
    with TableInfo<$MessageTableTable, MessageTableData> {
  @override
  final GeneratedDatabase attachedDatabase;
  final String? _alias;
  $MessageTableTable(this.attachedDatabase, [this._alias]);
  static const VerificationMeta _idMeta = const VerificationMeta('id');
  @override
  late final GeneratedColumnWithTypeConverter<Int64, BigInt> id =
      GeneratedColumn<BigInt>('id', aliasedName, false,
              type: DriftSqlType.bigInt, requiredDuringInsert: true)
          .withConverter<Int64>($MessageTableTable.$converterid);
  static const VerificationMeta _isGroupMessageMeta =
      const VerificationMeta('isGroupMessage');
  @override
  late final GeneratedColumn<bool> isGroupMessage = GeneratedColumn<bool>(
      'is_group_message', aliasedName, false,
      type: DriftSqlType.bool,
      requiredDuringInsert: true,
      defaultConstraints: GeneratedColumn.constraintIsAlways(
          'CHECK ("is_group_message" IN (0, 1))'));
  static const VerificationMeta _senderIdMeta =
      const VerificationMeta('senderId');
  @override
  late final GeneratedColumnWithTypeConverter<Int64, BigInt> senderId =
      GeneratedColumn<BigInt>('sender_id', aliasedName, false,
              type: DriftSqlType.bigInt, requiredDuringInsert: true)
          .withConverter<Int64>($MessageTableTable.$convertersenderId);
  static const VerificationMeta _contactIdMeta =
      const VerificationMeta('contactId');
  @override
  late final GeneratedColumnWithTypeConverter<Int64, BigInt> contactId =
      GeneratedColumn<BigInt>('contact_id', aliasedName, false,
              type: DriftSqlType.bigInt, requiredDuringInsert: true)
          .withConverter<Int64>($MessageTableTable.$convertercontactId);
  static const VerificationMeta _txtMeta = const VerificationMeta('txt');
  @override
  late final GeneratedColumn<String> txt = GeneratedColumn<String>(
      'text', aliasedName, true,
      type: DriftSqlType.string, requiredDuringInsert: false);
  static const VerificationMeta _recordsMeta =
      const VerificationMeta('records');
  @override
  late final GeneratedColumnWithTypeConverter<List<Uint8List>?, Uint8List>
      records = GeneratedColumn<Uint8List>('records', aliasedName, true,
              type: DriftSqlType.blob, requiredDuringInsert: false)
          .withConverter<List<Uint8List>?>(
              $MessageTableTable.$converterrecordsn);
  static const VerificationMeta _typeMeta = const VerificationMeta('type');
  @override
  late final GeneratedColumnWithTypeConverter<MessageType, int> type =
      GeneratedColumn<int>('type', aliasedName, false,
              type: DriftSqlType.int, requiredDuringInsert: true)
          .withConverter<MessageType>($MessageTableTable.$convertertype);
  static const VerificationMeta _createdDateMeta =
      const VerificationMeta('createdDate');
  @override
  late final GeneratedColumn<DateTime> createdDate = GeneratedColumn<DateTime>(
      'created_date', aliasedName, false,
      type: DriftSqlType.dateTime, requiredDuringInsert: true);
  @override
  List<GeneratedColumn> get $columns => [
        id,
        isGroupMessage,
        senderId,
        contactId,
        txt,
        records,
        type,
        createdDate
      ];
  @override
  String get aliasedName => _alias ?? actualTableName;
  @override
  String get actualTableName => $name;
  static const String $name = 'message';
  @override
  VerificationContext validateIntegrity(Insertable<MessageTableData> instance,
      {bool isInserting = false}) {
    final context = VerificationContext();
    final data = instance.toColumns(true);
    context.handle(_idMeta, const VerificationResult.success());
    if (data.containsKey('is_group_message')) {
      context.handle(
          _isGroupMessageMeta,
          isGroupMessage.isAcceptableOrUnknown(
              data['is_group_message']!, _isGroupMessageMeta));
    } else if (isInserting) {
      context.missing(_isGroupMessageMeta);
    }
    context.handle(_senderIdMeta, const VerificationResult.success());
    context.handle(_contactIdMeta, const VerificationResult.success());
    if (data.containsKey('text')) {
      context.handle(
          _txtMeta, txt.isAcceptableOrUnknown(data['text']!, _txtMeta));
    }
    context.handle(_recordsMeta, const VerificationResult.success());
    context.handle(_typeMeta, const VerificationResult.success());
    if (data.containsKey('created_date')) {
      context.handle(
          _createdDateMeta,
          createdDate.isAcceptableOrUnknown(
              data['created_date']!, _createdDateMeta));
    } else if (isInserting) {
      context.missing(_createdDateMeta);
    }
    return context;
  }

  @override
  Set<GeneratedColumn> get $primaryKey => {id};
  @override
  MessageTableData map(Map<String, dynamic> data, {String? tablePrefix}) {
    final effectivePrefix = tablePrefix != null ? '$tablePrefix.' : '';
    return MessageTableData(
      id: $MessageTableTable.$converterid.fromSql(attachedDatabase.typeMapping
          .read(DriftSqlType.bigInt, data['${effectivePrefix}id'])!),
      isGroupMessage: attachedDatabase.typeMapping
          .read(DriftSqlType.bool, data['${effectivePrefix}is_group_message'])!,
      senderId: $MessageTableTable.$convertersenderId.fromSql(attachedDatabase
          .typeMapping
          .read(DriftSqlType.bigInt, data['${effectivePrefix}sender_id'])!),
      contactId: $MessageTableTable.$convertercontactId.fromSql(attachedDatabase
          .typeMapping
          .read(DriftSqlType.bigInt, data['${effectivePrefix}contact_id'])!),
      txt: attachedDatabase.typeMapping
          .read(DriftSqlType.string, data['${effectivePrefix}text']),
      records: $MessageTableTable.$converterrecordsn.fromSql(attachedDatabase
          .typeMapping
          .read(DriftSqlType.blob, data['${effectivePrefix}records'])),
      type: $MessageTableTable.$convertertype.fromSql(attachedDatabase
          .typeMapping
          .read(DriftSqlType.int, data['${effectivePrefix}type'])!),
      createdDate: attachedDatabase.typeMapping
          .read(DriftSqlType.dateTime, data['${effectivePrefix}created_date'])!,
    );
  }

  @override
  $MessageTableTable createAlias(String alias) {
    return $MessageTableTable(attachedDatabase, alias);
  }

  static TypeConverter<Int64, BigInt> $converterid = const Int64Converter();
  static TypeConverter<Int64, BigInt> $convertersenderId =
      const Int64Converter();
  static TypeConverter<Int64, BigInt> $convertercontactId =
      const Int64Converter();
  static TypeConverter<List<Uint8List>, Uint8List> $converterrecords =
      const Uint8MatrixConverter();
  static TypeConverter<List<Uint8List>?, Uint8List?> $converterrecordsn =
      NullAwareTypeConverter.wrap($converterrecords);
  static JsonTypeConverter2<MessageType, int, int> $convertertype =
      const EnumIndexConverter<MessageType>(MessageType.values);
  @override
  bool get withoutRowId => true;
  @override
  bool get isStrict => true;
}

class MessageTableData extends DataClass
    implements Insertable<MessageTableData> {
  final Int64 id;
  final bool isGroupMessage;
  final Int64 senderId;

  /// 1. This can be either a group ID or a user (recipient) ID.
  /// 2. We use [contactId] instead of the columns [groupId] and [recipientId],
  /// so we don't need to creat two indexes.
  final Int64 contactId;
  final String? txt;
  final List<Uint8List>? records;
  final MessageType type;
  final DateTime createdDate;
  const MessageTableData(
      {required this.id,
      required this.isGroupMessage,
      required this.senderId,
      required this.contactId,
      this.txt,
      this.records,
      required this.type,
      required this.createdDate});
  @override
  Map<String, Expression> toColumns(bool nullToAbsent) {
    final map = <String, Expression>{};
    {
      map['id'] = Variable<BigInt>($MessageTableTable.$converterid.toSql(id));
    }
    map['is_group_message'] = Variable<bool>(isGroupMessage);
    {
      map['sender_id'] = Variable<BigInt>(
          $MessageTableTable.$convertersenderId.toSql(senderId));
    }
    {
      map['contact_id'] = Variable<BigInt>(
          $MessageTableTable.$convertercontactId.toSql(contactId));
    }
    if (!nullToAbsent || txt != null) {
      map['text'] = Variable<String>(txt);
    }
    if (!nullToAbsent || records != null) {
      map['records'] = Variable<Uint8List>(
          $MessageTableTable.$converterrecordsn.toSql(records));
    }
    {
      map['type'] =
          Variable<int>($MessageTableTable.$convertertype.toSql(type));
    }
    map['created_date'] = Variable<DateTime>(createdDate);
    return map;
  }

  MessageTableCompanion toCompanion(bool nullToAbsent) {
    return MessageTableCompanion(
      id: Value(id),
      isGroupMessage: Value(isGroupMessage),
      senderId: Value(senderId),
      contactId: Value(contactId),
      txt: txt == null && nullToAbsent ? const Value.absent() : Value(txt),
      records: records == null && nullToAbsent
          ? const Value.absent()
          : Value(records),
      type: Value(type),
      createdDate: Value(createdDate),
    );
  }

  factory MessageTableData.fromJson(Map<String, dynamic> json,
      {ValueSerializer? serializer}) {
    serializer ??= driftRuntimeOptions.defaultSerializer;
    return MessageTableData(
      id: serializer.fromJson<Int64>(json['id']),
      isGroupMessage: serializer.fromJson<bool>(json['isGroupMessage']),
      senderId: serializer.fromJson<Int64>(json['senderId']),
      contactId: serializer.fromJson<Int64>(json['contactId']),
      txt: serializer.fromJson<String?>(json['text']),
      records: serializer.fromJson<List<Uint8List>?>(json['records']),
      type: $MessageTableTable.$convertertype
          .fromJson(serializer.fromJson<int>(json['type'])),
      createdDate: serializer.fromJson<DateTime>(json['createdDate']),
    );
  }
  @override
  Map<String, dynamic> toJson({ValueSerializer? serializer}) {
    serializer ??= driftRuntimeOptions.defaultSerializer;
    return <String, dynamic>{
      'id': serializer.toJson<Int64>(id),
      'isGroupMessage': serializer.toJson<bool>(isGroupMessage),
      'senderId': serializer.toJson<Int64>(senderId),
      'contactId': serializer.toJson<Int64>(contactId),
      'text': serializer.toJson<String?>(txt),
      'records': serializer.toJson<List<Uint8List>?>(records),
      'type': serializer
          .toJson<int>($MessageTableTable.$convertertype.toJson(type)),
      'createdDate': serializer.toJson<DateTime>(createdDate),
    };
  }

  MessageTableData copyWith(
          {Int64? id,
          bool? isGroupMessage,
          Int64? senderId,
          Int64? contactId,
          Value<String?> txt = const Value.absent(),
          Value<List<Uint8List>?> records = const Value.absent(),
          MessageType? type,
          DateTime? createdDate}) =>
      MessageTableData(
        id: id ?? this.id,
        isGroupMessage: isGroupMessage ?? this.isGroupMessage,
        senderId: senderId ?? this.senderId,
        contactId: contactId ?? this.contactId,
        txt: txt.present ? txt.value : this.txt,
        records: records.present ? records.value : this.records,
        type: type ?? this.type,
        createdDate: createdDate ?? this.createdDate,
      );
  MessageTableData copyWithCompanion(MessageTableCompanion data) {
    return MessageTableData(
      id: data.id.present ? data.id.value : this.id,
      isGroupMessage: data.isGroupMessage.present
          ? data.isGroupMessage.value
          : this.isGroupMessage,
      senderId: data.senderId.present ? data.senderId.value : this.senderId,
      contactId: data.contactId.present ? data.contactId.value : this.contactId,
      txt: data.txt.present ? data.txt.value : this.txt,
      records: data.records.present ? data.records.value : this.records,
      type: data.type.present ? data.type.value : this.type,
      createdDate:
          data.createdDate.present ? data.createdDate.value : this.createdDate,
    );
  }

  @override
  String toString() {
    return (StringBuffer('MessageTableData(')
          ..write('id: $id, ')
          ..write('isGroupMessage: $isGroupMessage, ')
          ..write('senderId: $senderId, ')
          ..write('contactId: $contactId, ')
          ..write('txt: $txt, ')
          ..write('records: $records, ')
          ..write('type: $type, ')
          ..write('createdDate: $createdDate')
          ..write(')'))
        .toString();
  }

  @override
  int get hashCode => Object.hash(
      id, isGroupMessage, senderId, contactId, txt, records, type, createdDate);
  @override
  bool operator ==(Object other) =>
      identical(this, other) ||
      (other is MessageTableData &&
          other.id == this.id &&
          other.isGroupMessage == this.isGroupMessage &&
          other.senderId == this.senderId &&
          other.contactId == this.contactId &&
          other.txt == this.txt &&
          other.records == this.records &&
          other.type == this.type &&
          other.createdDate == this.createdDate);
}

class MessageTableCompanion extends UpdateCompanion<MessageTableData> {
  final Value<Int64> id;
  final Value<bool> isGroupMessage;
  final Value<Int64> senderId;
  final Value<Int64> contactId;
  final Value<String?> txt;
  final Value<List<Uint8List>?> records;
  final Value<MessageType> type;
  final Value<DateTime> createdDate;
  const MessageTableCompanion({
    this.id = const Value.absent(),
    this.isGroupMessage = const Value.absent(),
    this.senderId = const Value.absent(),
    this.contactId = const Value.absent(),
    this.txt = const Value.absent(),
    this.records = const Value.absent(),
    this.type = const Value.absent(),
    this.createdDate = const Value.absent(),
  });
  MessageTableCompanion.insert({
    required Int64 id,
    required bool isGroupMessage,
    required Int64 senderId,
    required Int64 contactId,
    this.txt = const Value.absent(),
    this.records = const Value.absent(),
    required MessageType type,
    required DateTime createdDate,
  })  : id = Value(id),
        isGroupMessage = Value(isGroupMessage),
        senderId = Value(senderId),
        contactId = Value(contactId),
        type = Value(type),
        createdDate = Value(createdDate);
  static Insertable<MessageTableData> custom({
    Expression<BigInt>? id,
    Expression<bool>? isGroupMessage,
    Expression<BigInt>? senderId,
    Expression<BigInt>? contactId,
    Expression<String>? txt,
    Expression<Uint8List>? records,
    Expression<int>? type,
    Expression<DateTime>? createdDate,
  }) {
    return RawValuesInsertable({
      if (id != null) 'id': id,
      if (isGroupMessage != null) 'is_group_message': isGroupMessage,
      if (senderId != null) 'sender_id': senderId,
      if (contactId != null) 'contact_id': contactId,
      if (txt != null) 'text': txt,
      if (records != null) 'records': records,
      if (type != null) 'type': type,
      if (createdDate != null) 'created_date': createdDate,
    });
  }

  MessageTableCompanion copyWith(
      {Value<Int64>? id,
      Value<bool>? isGroupMessage,
      Value<Int64>? senderId,
      Value<Int64>? contactId,
      Value<String?>? txt,
      Value<List<Uint8List>?>? records,
      Value<MessageType>? type,
      Value<DateTime>? createdDate}) {
    return MessageTableCompanion(
      id: id ?? this.id,
      isGroupMessage: isGroupMessage ?? this.isGroupMessage,
      senderId: senderId ?? this.senderId,
      contactId: contactId ?? this.contactId,
      txt: txt ?? this.txt,
      records: records ?? this.records,
      type: type ?? this.type,
      createdDate: createdDate ?? this.createdDate,
    );
  }

  @override
  Map<String, Expression> toColumns(bool nullToAbsent) {
    final map = <String, Expression>{};
    if (id.present) {
      map['id'] =
          Variable<BigInt>($MessageTableTable.$converterid.toSql(id.value));
    }
    if (isGroupMessage.present) {
      map['is_group_message'] = Variable<bool>(isGroupMessage.value);
    }
    if (senderId.present) {
      map['sender_id'] = Variable<BigInt>(
          $MessageTableTable.$convertersenderId.toSql(senderId.value));
    }
    if (contactId.present) {
      map['contact_id'] = Variable<BigInt>(
          $MessageTableTable.$convertercontactId.toSql(contactId.value));
    }
    if (txt.present) {
      map['text'] = Variable<String>(txt.value);
    }
    if (records.present) {
      map['records'] = Variable<Uint8List>(
          $MessageTableTable.$converterrecordsn.toSql(records.value));
    }
    if (type.present) {
      map['type'] =
          Variable<int>($MessageTableTable.$convertertype.toSql(type.value));
    }
    if (createdDate.present) {
      map['created_date'] = Variable<DateTime>(createdDate.value);
    }
    return map;
  }

  @override
  String toString() {
    return (StringBuffer('MessageTableCompanion(')
          ..write('id: $id, ')
          ..write('isGroupMessage: $isGroupMessage, ')
          ..write('senderId: $senderId, ')
          ..write('contactId: $contactId, ')
          ..write('txt: $txt, ')
          ..write('records: $records, ')
          ..write('type: $type, ')
          ..write('createdDate: $createdDate')
          ..write(')'))
        .toString();
  }
}

abstract class _$UserMessageDatabase extends GeneratedDatabase {
  _$UserMessageDatabase(QueryExecutor e) : super(e);
  $UserMessageDatabaseManager get managers => $UserMessageDatabaseManager(this);
  late final $MessageTableTable messageTable = $MessageTableTable(this);
  late final Index senderId =
      Index('sender_id', 'CREATE INDEX sender_id ON message (sender_id)');
  late final Index contactId =
      Index('contact_id', 'CREATE INDEX contact_id ON message (contact_id)');
  late final Index createdDate = Index(
      'created_date', 'CREATE INDEX created_date ON message (created_date)');
  @override
  Iterable<TableInfo<Table, Object?>> get allTables =>
      allSchemaEntities.whereType<TableInfo<Table, Object?>>();
  @override
  List<DatabaseSchemaEntity> get allSchemaEntities =>
      [messageTable, senderId, contactId, createdDate];
}

typedef $$MessageTableTableCreateCompanionBuilder = MessageTableCompanion
    Function({
  required Int64 id,
  required bool isGroupMessage,
  required Int64 senderId,
  required Int64 contactId,
  Value<String?> txt,
  Value<List<Uint8List>?> records,
  required MessageType type,
  required DateTime createdDate,
});
typedef $$MessageTableTableUpdateCompanionBuilder = MessageTableCompanion
    Function({
  Value<Int64> id,
  Value<bool> isGroupMessage,
  Value<Int64> senderId,
  Value<Int64> contactId,
  Value<String?> txt,
  Value<List<Uint8List>?> records,
  Value<MessageType> type,
  Value<DateTime> createdDate,
});

class $$MessageTableTableFilterComposer
    extends Composer<_$UserMessageDatabase, $MessageTableTable> {
  $$MessageTableTableFilterComposer({
    required super.$db,
    required super.$table,
    super.joinBuilder,
    super.$addJoinBuilderToRootComposer,
    super.$removeJoinBuilderFromRootComposer,
  });
  ColumnWithTypeConverterFilters<Int64, Int64, BigInt> get id =>
      $composableBuilder(
          column: $table.id,
          builder: (column) => ColumnWithTypeConverterFilters(column));

  ColumnFilters<bool> get isGroupMessage => $composableBuilder(
      column: $table.isGroupMessage,
      builder: (column) => ColumnFilters(column));

  ColumnWithTypeConverterFilters<Int64, Int64, BigInt> get senderId =>
      $composableBuilder(
          column: $table.senderId,
          builder: (column) => ColumnWithTypeConverterFilters(column));

  ColumnWithTypeConverterFilters<Int64, Int64, BigInt> get contactId =>
      $composableBuilder(
          column: $table.contactId,
          builder: (column) => ColumnWithTypeConverterFilters(column));

  ColumnFilters<String> get txt => $composableBuilder(
      column: $table.txt, builder: (column) => ColumnFilters(column));

  ColumnWithTypeConverterFilters<List<Uint8List>?, List<Uint8List>, Uint8List>
      get records => $composableBuilder(
          column: $table.records,
          builder: (column) => ColumnWithTypeConverterFilters(column));

  ColumnWithTypeConverterFilters<MessageType, MessageType, int> get type =>
      $composableBuilder(
          column: $table.type,
          builder: (column) => ColumnWithTypeConverterFilters(column));

  ColumnFilters<DateTime> get createdDate => $composableBuilder(
      column: $table.createdDate, builder: (column) => ColumnFilters(column));
}

class $$MessageTableTableOrderingComposer
    extends Composer<_$UserMessageDatabase, $MessageTableTable> {
  $$MessageTableTableOrderingComposer({
    required super.$db,
    required super.$table,
    super.joinBuilder,
    super.$addJoinBuilderToRootComposer,
    super.$removeJoinBuilderFromRootComposer,
  });
  ColumnOrderings<BigInt> get id => $composableBuilder(
      column: $table.id, builder: (column) => ColumnOrderings(column));

  ColumnOrderings<bool> get isGroupMessage => $composableBuilder(
      column: $table.isGroupMessage,
      builder: (column) => ColumnOrderings(column));

  ColumnOrderings<BigInt> get senderId => $composableBuilder(
      column: $table.senderId, builder: (column) => ColumnOrderings(column));

  ColumnOrderings<BigInt> get contactId => $composableBuilder(
      column: $table.contactId, builder: (column) => ColumnOrderings(column));

  ColumnOrderings<String> get txt => $composableBuilder(
      column: $table.txt, builder: (column) => ColumnOrderings(column));

  ColumnOrderings<Uint8List> get records => $composableBuilder(
      column: $table.records, builder: (column) => ColumnOrderings(column));

  ColumnOrderings<int> get type => $composableBuilder(
      column: $table.type, builder: (column) => ColumnOrderings(column));

  ColumnOrderings<DateTime> get createdDate => $composableBuilder(
      column: $table.createdDate, builder: (column) => ColumnOrderings(column));
}

class $$MessageTableTableAnnotationComposer
    extends Composer<_$UserMessageDatabase, $MessageTableTable> {
  $$MessageTableTableAnnotationComposer({
    required super.$db,
    required super.$table,
    super.joinBuilder,
    super.$addJoinBuilderToRootComposer,
    super.$removeJoinBuilderFromRootComposer,
  });
  GeneratedColumnWithTypeConverter<Int64, BigInt> get id =>
      $composableBuilder(column: $table.id, builder: (column) => column);

  GeneratedColumn<bool> get isGroupMessage => $composableBuilder(
      column: $table.isGroupMessage, builder: (column) => column);

  GeneratedColumnWithTypeConverter<Int64, BigInt> get senderId =>
      $composableBuilder(column: $table.senderId, builder: (column) => column);

  GeneratedColumnWithTypeConverter<Int64, BigInt> get contactId =>
      $composableBuilder(column: $table.contactId, builder: (column) => column);

  GeneratedColumn<String> get txt =>
      $composableBuilder(column: $table.txt, builder: (column) => column);

  GeneratedColumnWithTypeConverter<List<Uint8List>?, Uint8List> get records =>
      $composableBuilder(column: $table.records, builder: (column) => column);

  GeneratedColumnWithTypeConverter<MessageType, int> get type =>
      $composableBuilder(column: $table.type, builder: (column) => column);

  GeneratedColumn<DateTime> get createdDate => $composableBuilder(
      column: $table.createdDate, builder: (column) => column);
}

class $$MessageTableTableTableManager extends RootTableManager<
    _$UserMessageDatabase,
    $MessageTableTable,
    MessageTableData,
    $$MessageTableTableFilterComposer,
    $$MessageTableTableOrderingComposer,
    $$MessageTableTableAnnotationComposer,
    $$MessageTableTableCreateCompanionBuilder,
    $$MessageTableTableUpdateCompanionBuilder,
    (
      MessageTableData,
      BaseReferences<_$UserMessageDatabase, $MessageTableTable,
          MessageTableData>
    ),
    MessageTableData,
    PrefetchHooks Function()> {
  $$MessageTableTableTableManager(
      _$UserMessageDatabase db, $MessageTableTable table)
      : super(TableManagerState(
          db: db,
          table: table,
          createFilteringComposer: () =>
              $$MessageTableTableFilterComposer($db: db, $table: table),
          createOrderingComposer: () =>
              $$MessageTableTableOrderingComposer($db: db, $table: table),
          createComputedFieldComposer: () =>
              $$MessageTableTableAnnotationComposer($db: db, $table: table),
          updateCompanionCallback: ({
            Value<Int64> id = const Value.absent(),
            Value<bool> isGroupMessage = const Value.absent(),
            Value<Int64> senderId = const Value.absent(),
            Value<Int64> contactId = const Value.absent(),
            Value<String?> txt = const Value.absent(),
            Value<List<Uint8List>?> records = const Value.absent(),
            Value<MessageType> type = const Value.absent(),
            Value<DateTime> createdDate = const Value.absent(),
          }) =>
              MessageTableCompanion(
            id: id,
            isGroupMessage: isGroupMessage,
            senderId: senderId,
            contactId: contactId,
            txt: txt,
            records: records,
            type: type,
            createdDate: createdDate,
          ),
          createCompanionCallback: ({
            required Int64 id,
            required bool isGroupMessage,
            required Int64 senderId,
            required Int64 contactId,
            Value<String?> txt = const Value.absent(),
            Value<List<Uint8List>?> records = const Value.absent(),
            required MessageType type,
            required DateTime createdDate,
          }) =>
              MessageTableCompanion.insert(
            id: id,
            isGroupMessage: isGroupMessage,
            senderId: senderId,
            contactId: contactId,
            txt: txt,
            records: records,
            type: type,
            createdDate: createdDate,
          ),
          withReferenceMapper: (p0) => p0
              .map((e) => (e.readTable(table), BaseReferences(db, table, e)))
              .toList(),
          prefetchHooksCallback: null,
        ));
}

typedef $$MessageTableTableProcessedTableManager = ProcessedTableManager<
    _$UserMessageDatabase,
    $MessageTableTable,
    MessageTableData,
    $$MessageTableTableFilterComposer,
    $$MessageTableTableOrderingComposer,
    $$MessageTableTableAnnotationComposer,
    $$MessageTableTableCreateCompanionBuilder,
    $$MessageTableTableUpdateCompanionBuilder,
    (
      MessageTableData,
      BaseReferences<_$UserMessageDatabase, $MessageTableTable,
          MessageTableData>
    ),
    MessageTableData,
    PrefetchHooks Function()>;

class $UserMessageDatabaseManager {
  final _$UserMessageDatabase _db;
  $UserMessageDatabaseManager(this._db);
  $$MessageTableTableTableManager get messageTable =>
      $$MessageTableTableTableManager(_db, _db.messageTable);
}
