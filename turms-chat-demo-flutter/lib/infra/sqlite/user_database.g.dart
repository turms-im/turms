// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'user_database.dart';

// ignore_for_file: type=lint
class $ConversationSettingTableTable extends ConversationSettingTable
    with
        TableInfo<
          $ConversationSettingTableTable,
          ConversationSettingTableData
        > {
  @override
  final GeneratedDatabase attachedDatabase;
  final String? _alias;
  $ConversationSettingTableTable(this.attachedDatabase, [this._alias]);
  static const VerificationMeta _contactIdMeta = const VerificationMeta(
    'contactId',
  );
  @override
  late final GeneratedColumn<BigInt> contactId = GeneratedColumn<BigInt>(
    'contact_id',
    aliasedName,
    false,
    type: DriftSqlType.bigInt,
    requiredDuringInsert: true,
  );
  static const VerificationMeta _isGroupConversationMeta =
      const VerificationMeta('isGroupConversation');
  @override
  late final GeneratedColumn<bool> isGroupConversation = GeneratedColumn<bool>(
    'is_group_conversation',
    aliasedName,
    false,
    type: DriftSqlType.bool,
    requiredDuringInsert: true,
    defaultConstraints: GeneratedColumn.constraintIsAlways(
      'CHECK ("is_group_conversation" IN (0, 1))',
    ),
  );
  static const VerificationMeta _settingIdMeta = const VerificationMeta(
    'settingId',
  );
  @override
  late final GeneratedColumn<int> settingId = GeneratedColumn<int>(
    'setting_id',
    aliasedName,
    false,
    type: DriftSqlType.int,
    requiredDuringInsert: true,
  );
  static const VerificationMeta _valueMeta = const VerificationMeta('value');
  @override
  late final GeneratedColumn<DriftAny> value = GeneratedColumn<DriftAny>(
    'value',
    aliasedName,
    false,
    type: DriftSqlType.any,
    requiredDuringInsert: true,
  );
  static const VerificationMeta _createdDateMeta = const VerificationMeta(
    'createdDate',
  );
  @override
  late final GeneratedColumn<DateTime> createdDate = GeneratedColumn<DateTime>(
    'created_date',
    aliasedName,
    false,
    type: DriftSqlType.dateTime,
    requiredDuringInsert: true,
  );
  static const VerificationMeta _lastModifiedDateMeta = const VerificationMeta(
    'lastModifiedDate',
  );
  @override
  late final GeneratedColumn<DateTime> lastModifiedDate =
      GeneratedColumn<DateTime>(
        'last_modified_date',
        aliasedName,
        false,
        type: DriftSqlType.dateTime,
        requiredDuringInsert: true,
      );
  @override
  List<GeneratedColumn> get $columns => [
    contactId,
    isGroupConversation,
    settingId,
    value,
    createdDate,
    lastModifiedDate,
  ];
  @override
  String get aliasedName => _alias ?? actualTableName;
  @override
  String get actualTableName => $name;
  static const String $name = 'conversation_setting';
  @override
  VerificationContext validateIntegrity(
    Insertable<ConversationSettingTableData> instance, {
    bool isInserting = false,
  }) {
    final context = VerificationContext();
    final data = instance.toColumns(true);
    if (data.containsKey('contact_id')) {
      context.handle(
        _contactIdMeta,
        contactId.isAcceptableOrUnknown(data['contact_id']!, _contactIdMeta),
      );
    } else if (isInserting) {
      context.missing(_contactIdMeta);
    }
    if (data.containsKey('is_group_conversation')) {
      context.handle(
        _isGroupConversationMeta,
        isGroupConversation.isAcceptableOrUnknown(
          data['is_group_conversation']!,
          _isGroupConversationMeta,
        ),
      );
    } else if (isInserting) {
      context.missing(_isGroupConversationMeta);
    }
    if (data.containsKey('setting_id')) {
      context.handle(
        _settingIdMeta,
        settingId.isAcceptableOrUnknown(data['setting_id']!, _settingIdMeta),
      );
    } else if (isInserting) {
      context.missing(_settingIdMeta);
    }
    if (data.containsKey('value')) {
      context.handle(
        _valueMeta,
        value.isAcceptableOrUnknown(data['value']!, _valueMeta),
      );
    } else if (isInserting) {
      context.missing(_valueMeta);
    }
    if (data.containsKey('created_date')) {
      context.handle(
        _createdDateMeta,
        createdDate.isAcceptableOrUnknown(
          data['created_date']!,
          _createdDateMeta,
        ),
      );
    } else if (isInserting) {
      context.missing(_createdDateMeta);
    }
    if (data.containsKey('last_modified_date')) {
      context.handle(
        _lastModifiedDateMeta,
        lastModifiedDate.isAcceptableOrUnknown(
          data['last_modified_date']!,
          _lastModifiedDateMeta,
        ),
      );
    } else if (isInserting) {
      context.missing(_lastModifiedDateMeta);
    }
    return context;
  }

  @override
  Set<GeneratedColumn> get $primaryKey => {
    contactId,
    isGroupConversation,
    settingId,
  };
  @override
  ConversationSettingTableData map(
    Map<String, dynamic> data, {
    String? tablePrefix,
  }) {
    final effectivePrefix = tablePrefix != null ? '$tablePrefix.' : '';
    return ConversationSettingTableData(
      contactId: attachedDatabase.typeMapping.read(
        DriftSqlType.bigInt,
        data['${effectivePrefix}contact_id'],
      )!,
      isGroupConversation: attachedDatabase.typeMapping.read(
        DriftSqlType.bool,
        data['${effectivePrefix}is_group_conversation'],
      )!,
      settingId: attachedDatabase.typeMapping.read(
        DriftSqlType.int,
        data['${effectivePrefix}setting_id'],
      )!,
      value: attachedDatabase.typeMapping.read(
        DriftSqlType.any,
        data['${effectivePrefix}value'],
      )!,
      createdDate: attachedDatabase.typeMapping.read(
        DriftSqlType.dateTime,
        data['${effectivePrefix}created_date'],
      )!,
      lastModifiedDate: attachedDatabase.typeMapping.read(
        DriftSqlType.dateTime,
        data['${effectivePrefix}last_modified_date'],
      )!,
    );
  }

  @override
  $ConversationSettingTableTable createAlias(String alias) {
    return $ConversationSettingTableTable(attachedDatabase, alias);
  }

  @override
  bool get withoutRowId => true;
  @override
  bool get isStrict => true;
}

class ConversationSettingTableData extends DataClass
    implements Insertable<ConversationSettingTableData> {
  /// This can be either a group ID or a user (recipient) ID.
  final BigInt contactId;
  final bool isGroupConversation;
  final int settingId;
  final DriftAny value;
  final DateTime createdDate;
  final DateTime lastModifiedDate;
  const ConversationSettingTableData({
    required this.contactId,
    required this.isGroupConversation,
    required this.settingId,
    required this.value,
    required this.createdDate,
    required this.lastModifiedDate,
  });
  @override
  Map<String, Expression> toColumns(bool nullToAbsent) {
    final map = <String, Expression>{};
    map['contact_id'] = Variable<BigInt>(contactId);
    map['is_group_conversation'] = Variable<bool>(isGroupConversation);
    map['setting_id'] = Variable<int>(settingId);
    map['value'] = Variable<DriftAny>(value);
    map['created_date'] = Variable<DateTime>(createdDate);
    map['last_modified_date'] = Variable<DateTime>(lastModifiedDate);
    return map;
  }

  ConversationSettingTableCompanion toCompanion(bool nullToAbsent) {
    return ConversationSettingTableCompanion(
      contactId: Value(contactId),
      isGroupConversation: Value(isGroupConversation),
      settingId: Value(settingId),
      value: Value(value),
      createdDate: Value(createdDate),
      lastModifiedDate: Value(lastModifiedDate),
    );
  }

  factory ConversationSettingTableData.fromJson(
    Map<String, dynamic> json, {
    ValueSerializer? serializer,
  }) {
    serializer ??= driftRuntimeOptions.defaultSerializer;
    return ConversationSettingTableData(
      contactId: serializer.fromJson<BigInt>(json['contactId']),
      isGroupConversation: serializer.fromJson<bool>(
        json['isGroupConversation'],
      ),
      settingId: serializer.fromJson<int>(json['settingId']),
      value: serializer.fromJson<DriftAny>(json['value']),
      createdDate: serializer.fromJson<DateTime>(json['createdDate']),
      lastModifiedDate: serializer.fromJson<DateTime>(json['lastModifiedDate']),
    );
  }
  @override
  Map<String, dynamic> toJson({ValueSerializer? serializer}) {
    serializer ??= driftRuntimeOptions.defaultSerializer;
    return <String, dynamic>{
      'contactId': serializer.toJson<BigInt>(contactId),
      'isGroupConversation': serializer.toJson<bool>(isGroupConversation),
      'settingId': serializer.toJson<int>(settingId),
      'value': serializer.toJson<DriftAny>(value),
      'createdDate': serializer.toJson<DateTime>(createdDate),
      'lastModifiedDate': serializer.toJson<DateTime>(lastModifiedDate),
    };
  }

  ConversationSettingTableData copyWith({
    BigInt? contactId,
    bool? isGroupConversation,
    int? settingId,
    DriftAny? value,
    DateTime? createdDate,
    DateTime? lastModifiedDate,
  }) => ConversationSettingTableData(
    contactId: contactId ?? this.contactId,
    isGroupConversation: isGroupConversation ?? this.isGroupConversation,
    settingId: settingId ?? this.settingId,
    value: value ?? this.value,
    createdDate: createdDate ?? this.createdDate,
    lastModifiedDate: lastModifiedDate ?? this.lastModifiedDate,
  );
  ConversationSettingTableData copyWithCompanion(
    ConversationSettingTableCompanion data,
  ) {
    return ConversationSettingTableData(
      contactId: data.contactId.present ? data.contactId.value : this.contactId,
      isGroupConversation: data.isGroupConversation.present
          ? data.isGroupConversation.value
          : this.isGroupConversation,
      settingId: data.settingId.present ? data.settingId.value : this.settingId,
      value: data.value.present ? data.value.value : this.value,
      createdDate: data.createdDate.present
          ? data.createdDate.value
          : this.createdDate,
      lastModifiedDate: data.lastModifiedDate.present
          ? data.lastModifiedDate.value
          : this.lastModifiedDate,
    );
  }

  @override
  String toString() {
    return (StringBuffer('ConversationSettingTableData(')
          ..write('contactId: $contactId, ')
          ..write('isGroupConversation: $isGroupConversation, ')
          ..write('settingId: $settingId, ')
          ..write('value: $value, ')
          ..write('createdDate: $createdDate, ')
          ..write('lastModifiedDate: $lastModifiedDate')
          ..write(')'))
        .toString();
  }

  @override
  int get hashCode => Object.hash(
    contactId,
    isGroupConversation,
    settingId,
    value,
    createdDate,
    lastModifiedDate,
  );
  @override
  bool operator ==(Object other) =>
      identical(this, other) ||
      (other is ConversationSettingTableData &&
          other.contactId == this.contactId &&
          other.isGroupConversation == this.isGroupConversation &&
          other.settingId == this.settingId &&
          other.value == this.value &&
          other.createdDate == this.createdDate &&
          other.lastModifiedDate == this.lastModifiedDate);
}

class ConversationSettingTableCompanion
    extends UpdateCompanion<ConversationSettingTableData> {
  final Value<BigInt> contactId;
  final Value<bool> isGroupConversation;
  final Value<int> settingId;
  final Value<DriftAny> value;
  final Value<DateTime> createdDate;
  final Value<DateTime> lastModifiedDate;
  const ConversationSettingTableCompanion({
    this.contactId = const Value.absent(),
    this.isGroupConversation = const Value.absent(),
    this.settingId = const Value.absent(),
    this.value = const Value.absent(),
    this.createdDate = const Value.absent(),
    this.lastModifiedDate = const Value.absent(),
  });
  ConversationSettingTableCompanion.insert({
    required BigInt contactId,
    required bool isGroupConversation,
    required int settingId,
    required DriftAny value,
    required DateTime createdDate,
    required DateTime lastModifiedDate,
  }) : contactId = Value(contactId),
       isGroupConversation = Value(isGroupConversation),
       settingId = Value(settingId),
       value = Value(value),
       createdDate = Value(createdDate),
       lastModifiedDate = Value(lastModifiedDate);
  static Insertable<ConversationSettingTableData> custom({
    Expression<BigInt>? contactId,
    Expression<bool>? isGroupConversation,
    Expression<int>? settingId,
    Expression<DriftAny>? value,
    Expression<DateTime>? createdDate,
    Expression<DateTime>? lastModifiedDate,
  }) {
    return RawValuesInsertable({
      if (contactId != null) 'contact_id': contactId,
      if (isGroupConversation != null)
        'is_group_conversation': isGroupConversation,
      if (settingId != null) 'setting_id': settingId,
      if (value != null) 'value': value,
      if (createdDate != null) 'created_date': createdDate,
      if (lastModifiedDate != null) 'last_modified_date': lastModifiedDate,
    });
  }

  ConversationSettingTableCompanion copyWith({
    Value<BigInt>? contactId,
    Value<bool>? isGroupConversation,
    Value<int>? settingId,
    Value<DriftAny>? value,
    Value<DateTime>? createdDate,
    Value<DateTime>? lastModifiedDate,
  }) {
    return ConversationSettingTableCompanion(
      contactId: contactId ?? this.contactId,
      isGroupConversation: isGroupConversation ?? this.isGroupConversation,
      settingId: settingId ?? this.settingId,
      value: value ?? this.value,
      createdDate: createdDate ?? this.createdDate,
      lastModifiedDate: lastModifiedDate ?? this.lastModifiedDate,
    );
  }

  @override
  Map<String, Expression> toColumns(bool nullToAbsent) {
    final map = <String, Expression>{};
    if (contactId.present) {
      map['contact_id'] = Variable<BigInt>(contactId.value);
    }
    if (isGroupConversation.present) {
      map['is_group_conversation'] = Variable<bool>(isGroupConversation.value);
    }
    if (settingId.present) {
      map['setting_id'] = Variable<int>(settingId.value);
    }
    if (value.present) {
      map['value'] = Variable<DriftAny>(value.value);
    }
    if (createdDate.present) {
      map['created_date'] = Variable<DateTime>(createdDate.value);
    }
    if (lastModifiedDate.present) {
      map['last_modified_date'] = Variable<DateTime>(lastModifiedDate.value);
    }
    return map;
  }

  @override
  String toString() {
    return (StringBuffer('ConversationSettingTableCompanion(')
          ..write('contactId: $contactId, ')
          ..write('isGroupConversation: $isGroupConversation, ')
          ..write('settingId: $settingId, ')
          ..write('value: $value, ')
          ..write('createdDate: $createdDate, ')
          ..write('lastModifiedDate: $lastModifiedDate')
          ..write(')'))
        .toString();
  }
}

class $LogEntryTableTable extends LogEntryTable
    with TableInfo<$LogEntryTableTable, LogEntryTableData> {
  @override
  final GeneratedDatabase attachedDatabase;
  final String? _alias;
  $LogEntryTableTable(this.attachedDatabase, [this._alias]);
  static const VerificationMeta _idMeta = const VerificationMeta('id');
  @override
  late final GeneratedColumn<int> id = GeneratedColumn<int>(
    'id',
    aliasedName,
    false,
    hasAutoIncrement: true,
    type: DriftSqlType.int,
    requiredDuringInsert: false,
    defaultConstraints: GeneratedColumn.constraintIsAlways(
      'PRIMARY KEY AUTOINCREMENT',
    ),
  );
  static const VerificationMeta _levelMeta = const VerificationMeta('level');
  @override
  late final GeneratedColumnWithTypeConverter<LogLevel, int> level =
      GeneratedColumn<int>(
        'level',
        aliasedName,
        false,
        type: DriftSqlType.int,
        requiredDuringInsert: true,
      ).withConverter<LogLevel>($LogEntryTableTable.$converterlevel);
  static const VerificationMeta _createdDateMeta = const VerificationMeta(
    'createdDate',
  );
  @override
  late final GeneratedColumn<DateTime> createdDate = GeneratedColumn<DateTime>(
    'created_date',
    aliasedName,
    false,
    type: DriftSqlType.dateTime,
    requiredDuringInsert: true,
  );
  static const VerificationMeta _messageMeta = const VerificationMeta(
    'message',
  );
  @override
  late final GeneratedColumn<String> message = GeneratedColumn<String>(
    'message',
    aliasedName,
    false,
    type: DriftSqlType.string,
    requiredDuringInsert: true,
  );
  @override
  List<GeneratedColumn> get $columns => [id, level, createdDate, message];
  @override
  String get aliasedName => _alias ?? actualTableName;
  @override
  String get actualTableName => $name;
  static const String $name = 'log_entry';
  @override
  VerificationContext validateIntegrity(
    Insertable<LogEntryTableData> instance, {
    bool isInserting = false,
  }) {
    final context = VerificationContext();
    final data = instance.toColumns(true);
    if (data.containsKey('id')) {
      context.handle(_idMeta, id.isAcceptableOrUnknown(data['id']!, _idMeta));
    }
    context.handle(_levelMeta, const VerificationResult.success());
    if (data.containsKey('created_date')) {
      context.handle(
        _createdDateMeta,
        createdDate.isAcceptableOrUnknown(
          data['created_date']!,
          _createdDateMeta,
        ),
      );
    } else if (isInserting) {
      context.missing(_createdDateMeta);
    }
    if (data.containsKey('message')) {
      context.handle(
        _messageMeta,
        message.isAcceptableOrUnknown(data['message']!, _messageMeta),
      );
    } else if (isInserting) {
      context.missing(_messageMeta);
    }
    return context;
  }

  @override
  Set<GeneratedColumn> get $primaryKey => {id};
  @override
  LogEntryTableData map(Map<String, dynamic> data, {String? tablePrefix}) {
    final effectivePrefix = tablePrefix != null ? '$tablePrefix.' : '';
    return LogEntryTableData(
      id: attachedDatabase.typeMapping.read(
        DriftSqlType.int,
        data['${effectivePrefix}id'],
      )!,
      level: $LogEntryTableTable.$converterlevel.fromSql(
        attachedDatabase.typeMapping.read(
          DriftSqlType.int,
          data['${effectivePrefix}level'],
        )!,
      ),
      createdDate: attachedDatabase.typeMapping.read(
        DriftSqlType.dateTime,
        data['${effectivePrefix}created_date'],
      )!,
      message: attachedDatabase.typeMapping.read(
        DriftSqlType.string,
        data['${effectivePrefix}message'],
      )!,
    );
  }

  @override
  $LogEntryTableTable createAlias(String alias) {
    return $LogEntryTableTable(attachedDatabase, alias);
  }

  static TypeConverter<LogLevel, int> $converterlevel = LogLevelConverter();
  @override
  bool get isStrict => true;
}

class LogEntryTableData extends DataClass
    implements Insertable<LogEntryTableData> {
  final int id;
  final LogLevel level;
  final DateTime createdDate;
  final String message;
  const LogEntryTableData({
    required this.id,
    required this.level,
    required this.createdDate,
    required this.message,
  });
  @override
  Map<String, Expression> toColumns(bool nullToAbsent) {
    final map = <String, Expression>{};
    map['id'] = Variable<int>(id);
    {
      map['level'] = Variable<int>(
        $LogEntryTableTable.$converterlevel.toSql(level),
      );
    }
    map['created_date'] = Variable<DateTime>(createdDate);
    map['message'] = Variable<String>(message);
    return map;
  }

  LogEntryTableCompanion toCompanion(bool nullToAbsent) {
    return LogEntryTableCompanion(
      id: Value(id),
      level: Value(level),
      createdDate: Value(createdDate),
      message: Value(message),
    );
  }

  factory LogEntryTableData.fromJson(
    Map<String, dynamic> json, {
    ValueSerializer? serializer,
  }) {
    serializer ??= driftRuntimeOptions.defaultSerializer;
    return LogEntryTableData(
      id: serializer.fromJson<int>(json['id']),
      level: serializer.fromJson<LogLevel>(json['level']),
      createdDate: serializer.fromJson<DateTime>(json['createdDate']),
      message: serializer.fromJson<String>(json['message']),
    );
  }
  @override
  Map<String, dynamic> toJson({ValueSerializer? serializer}) {
    serializer ??= driftRuntimeOptions.defaultSerializer;
    return <String, dynamic>{
      'id': serializer.toJson<int>(id),
      'level': serializer.toJson<LogLevel>(level),
      'createdDate': serializer.toJson<DateTime>(createdDate),
      'message': serializer.toJson<String>(message),
    };
  }

  LogEntryTableData copyWith({
    int? id,
    LogLevel? level,
    DateTime? createdDate,
    String? message,
  }) => LogEntryTableData(
    id: id ?? this.id,
    level: level ?? this.level,
    createdDate: createdDate ?? this.createdDate,
    message: message ?? this.message,
  );
  LogEntryTableData copyWithCompanion(LogEntryTableCompanion data) {
    return LogEntryTableData(
      id: data.id.present ? data.id.value : this.id,
      level: data.level.present ? data.level.value : this.level,
      createdDate: data.createdDate.present
          ? data.createdDate.value
          : this.createdDate,
      message: data.message.present ? data.message.value : this.message,
    );
  }

  @override
  String toString() {
    return (StringBuffer('LogEntryTableData(')
          ..write('id: $id, ')
          ..write('level: $level, ')
          ..write('createdDate: $createdDate, ')
          ..write('message: $message')
          ..write(')'))
        .toString();
  }

  @override
  int get hashCode => Object.hash(id, level, createdDate, message);
  @override
  bool operator ==(Object other) =>
      identical(this, other) ||
      (other is LogEntryTableData &&
          other.id == this.id &&
          other.level == this.level &&
          other.createdDate == this.createdDate &&
          other.message == this.message);
}

class LogEntryTableCompanion extends UpdateCompanion<LogEntryTableData> {
  final Value<int> id;
  final Value<LogLevel> level;
  final Value<DateTime> createdDate;
  final Value<String> message;
  const LogEntryTableCompanion({
    this.id = const Value.absent(),
    this.level = const Value.absent(),
    this.createdDate = const Value.absent(),
    this.message = const Value.absent(),
  });
  LogEntryTableCompanion.insert({
    this.id = const Value.absent(),
    required LogLevel level,
    required DateTime createdDate,
    required String message,
  }) : level = Value(level),
       createdDate = Value(createdDate),
       message = Value(message);
  static Insertable<LogEntryTableData> custom({
    Expression<int>? id,
    Expression<int>? level,
    Expression<DateTime>? createdDate,
    Expression<String>? message,
  }) {
    return RawValuesInsertable({
      if (id != null) 'id': id,
      if (level != null) 'level': level,
      if (createdDate != null) 'created_date': createdDate,
      if (message != null) 'message': message,
    });
  }

  LogEntryTableCompanion copyWith({
    Value<int>? id,
    Value<LogLevel>? level,
    Value<DateTime>? createdDate,
    Value<String>? message,
  }) {
    return LogEntryTableCompanion(
      id: id ?? this.id,
      level: level ?? this.level,
      createdDate: createdDate ?? this.createdDate,
      message: message ?? this.message,
    );
  }

  @override
  Map<String, Expression> toColumns(bool nullToAbsent) {
    final map = <String, Expression>{};
    if (id.present) {
      map['id'] = Variable<int>(id.value);
    }
    if (level.present) {
      map['level'] = Variable<int>(
        $LogEntryTableTable.$converterlevel.toSql(level.value),
      );
    }
    if (createdDate.present) {
      map['created_date'] = Variable<DateTime>(createdDate.value);
    }
    if (message.present) {
      map['message'] = Variable<String>(message.value);
    }
    return map;
  }

  @override
  String toString() {
    return (StringBuffer('LogEntryTableCompanion(')
          ..write('id: $id, ')
          ..write('level: $level, ')
          ..write('createdDate: $createdDate, ')
          ..write('message: $message')
          ..write(')'))
        .toString();
  }
}

class $UserSettingTableTable extends UserSettingTable
    with TableInfo<$UserSettingTableTable, UserSettingTableData> {
  @override
  final GeneratedDatabase attachedDatabase;
  final String? _alias;
  $UserSettingTableTable(this.attachedDatabase, [this._alias]);
  static const VerificationMeta _idMeta = const VerificationMeta('id');
  @override
  late final GeneratedColumn<int> id = GeneratedColumn<int>(
    'id',
    aliasedName,
    false,
    type: DriftSqlType.int,
    requiredDuringInsert: true,
  );
  static const VerificationMeta _valueMeta = const VerificationMeta('value');
  @override
  late final GeneratedColumn<DriftAny> value = GeneratedColumn<DriftAny>(
    'value',
    aliasedName,
    false,
    type: DriftSqlType.any,
    requiredDuringInsert: true,
  );
  static const VerificationMeta _createdDateMeta = const VerificationMeta(
    'createdDate',
  );
  @override
  late final GeneratedColumn<DateTime> createdDate = GeneratedColumn<DateTime>(
    'created_date',
    aliasedName,
    false,
    type: DriftSqlType.dateTime,
    requiredDuringInsert: true,
  );
  static const VerificationMeta _lastModifiedDateMeta = const VerificationMeta(
    'lastModifiedDate',
  );
  @override
  late final GeneratedColumn<DateTime> lastModifiedDate =
      GeneratedColumn<DateTime>(
        'last_modified_date',
        aliasedName,
        false,
        type: DriftSqlType.dateTime,
        requiredDuringInsert: true,
      );
  @override
  List<GeneratedColumn> get $columns => [
    id,
    value,
    createdDate,
    lastModifiedDate,
  ];
  @override
  String get aliasedName => _alias ?? actualTableName;
  @override
  String get actualTableName => $name;
  static const String $name = 'user_setting';
  @override
  VerificationContext validateIntegrity(
    Insertable<UserSettingTableData> instance, {
    bool isInserting = false,
  }) {
    final context = VerificationContext();
    final data = instance.toColumns(true);
    if (data.containsKey('id')) {
      context.handle(_idMeta, id.isAcceptableOrUnknown(data['id']!, _idMeta));
    } else if (isInserting) {
      context.missing(_idMeta);
    }
    if (data.containsKey('value')) {
      context.handle(
        _valueMeta,
        value.isAcceptableOrUnknown(data['value']!, _valueMeta),
      );
    } else if (isInserting) {
      context.missing(_valueMeta);
    }
    if (data.containsKey('created_date')) {
      context.handle(
        _createdDateMeta,
        createdDate.isAcceptableOrUnknown(
          data['created_date']!,
          _createdDateMeta,
        ),
      );
    } else if (isInserting) {
      context.missing(_createdDateMeta);
    }
    if (data.containsKey('last_modified_date')) {
      context.handle(
        _lastModifiedDateMeta,
        lastModifiedDate.isAcceptableOrUnknown(
          data['last_modified_date']!,
          _lastModifiedDateMeta,
        ),
      );
    } else if (isInserting) {
      context.missing(_lastModifiedDateMeta);
    }
    return context;
  }

  @override
  Set<GeneratedColumn> get $primaryKey => {id};
  @override
  UserSettingTableData map(Map<String, dynamic> data, {String? tablePrefix}) {
    final effectivePrefix = tablePrefix != null ? '$tablePrefix.' : '';
    return UserSettingTableData(
      id: attachedDatabase.typeMapping.read(
        DriftSqlType.int,
        data['${effectivePrefix}id'],
      )!,
      value: attachedDatabase.typeMapping.read(
        DriftSqlType.any,
        data['${effectivePrefix}value'],
      )!,
      createdDate: attachedDatabase.typeMapping.read(
        DriftSqlType.dateTime,
        data['${effectivePrefix}created_date'],
      )!,
      lastModifiedDate: attachedDatabase.typeMapping.read(
        DriftSqlType.dateTime,
        data['${effectivePrefix}last_modified_date'],
      )!,
    );
  }

  @override
  $UserSettingTableTable createAlias(String alias) {
    return $UserSettingTableTable(attachedDatabase, alias);
  }

  @override
  bool get withoutRowId => true;
  @override
  bool get isStrict => true;
}

class UserSettingTableData extends DataClass
    implements Insertable<UserSettingTableData> {
  final int id;
  final DriftAny value;
  final DateTime createdDate;
  final DateTime lastModifiedDate;
  const UserSettingTableData({
    required this.id,
    required this.value,
    required this.createdDate,
    required this.lastModifiedDate,
  });
  @override
  Map<String, Expression> toColumns(bool nullToAbsent) {
    final map = <String, Expression>{};
    map['id'] = Variable<int>(id);
    map['value'] = Variable<DriftAny>(value);
    map['created_date'] = Variable<DateTime>(createdDate);
    map['last_modified_date'] = Variable<DateTime>(lastModifiedDate);
    return map;
  }

  UserSettingTableCompanion toCompanion(bool nullToAbsent) {
    return UserSettingTableCompanion(
      id: Value(id),
      value: Value(value),
      createdDate: Value(createdDate),
      lastModifiedDate: Value(lastModifiedDate),
    );
  }

  factory UserSettingTableData.fromJson(
    Map<String, dynamic> json, {
    ValueSerializer? serializer,
  }) {
    serializer ??= driftRuntimeOptions.defaultSerializer;
    return UserSettingTableData(
      id: serializer.fromJson<int>(json['id']),
      value: serializer.fromJson<DriftAny>(json['value']),
      createdDate: serializer.fromJson<DateTime>(json['createdDate']),
      lastModifiedDate: serializer.fromJson<DateTime>(json['lastModifiedDate']),
    );
  }
  @override
  Map<String, dynamic> toJson({ValueSerializer? serializer}) {
    serializer ??= driftRuntimeOptions.defaultSerializer;
    return <String, dynamic>{
      'id': serializer.toJson<int>(id),
      'value': serializer.toJson<DriftAny>(value),
      'createdDate': serializer.toJson<DateTime>(createdDate),
      'lastModifiedDate': serializer.toJson<DateTime>(lastModifiedDate),
    };
  }

  UserSettingTableData copyWith({
    int? id,
    DriftAny? value,
    DateTime? createdDate,
    DateTime? lastModifiedDate,
  }) => UserSettingTableData(
    id: id ?? this.id,
    value: value ?? this.value,
    createdDate: createdDate ?? this.createdDate,
    lastModifiedDate: lastModifiedDate ?? this.lastModifiedDate,
  );
  UserSettingTableData copyWithCompanion(UserSettingTableCompanion data) {
    return UserSettingTableData(
      id: data.id.present ? data.id.value : this.id,
      value: data.value.present ? data.value.value : this.value,
      createdDate: data.createdDate.present
          ? data.createdDate.value
          : this.createdDate,
      lastModifiedDate: data.lastModifiedDate.present
          ? data.lastModifiedDate.value
          : this.lastModifiedDate,
    );
  }

  @override
  String toString() {
    return (StringBuffer('UserSettingTableData(')
          ..write('id: $id, ')
          ..write('value: $value, ')
          ..write('createdDate: $createdDate, ')
          ..write('lastModifiedDate: $lastModifiedDate')
          ..write(')'))
        .toString();
  }

  @override
  int get hashCode => Object.hash(id, value, createdDate, lastModifiedDate);
  @override
  bool operator ==(Object other) =>
      identical(this, other) ||
      (other is UserSettingTableData &&
          other.id == this.id &&
          other.value == this.value &&
          other.createdDate == this.createdDate &&
          other.lastModifiedDate == this.lastModifiedDate);
}

class UserSettingTableCompanion extends UpdateCompanion<UserSettingTableData> {
  final Value<int> id;
  final Value<DriftAny> value;
  final Value<DateTime> createdDate;
  final Value<DateTime> lastModifiedDate;
  const UserSettingTableCompanion({
    this.id = const Value.absent(),
    this.value = const Value.absent(),
    this.createdDate = const Value.absent(),
    this.lastModifiedDate = const Value.absent(),
  });
  UserSettingTableCompanion.insert({
    required int id,
    required DriftAny value,
    required DateTime createdDate,
    required DateTime lastModifiedDate,
  }) : id = Value(id),
       value = Value(value),
       createdDate = Value(createdDate),
       lastModifiedDate = Value(lastModifiedDate);
  static Insertable<UserSettingTableData> custom({
    Expression<int>? id,
    Expression<DriftAny>? value,
    Expression<DateTime>? createdDate,
    Expression<DateTime>? lastModifiedDate,
  }) {
    return RawValuesInsertable({
      if (id != null) 'id': id,
      if (value != null) 'value': value,
      if (createdDate != null) 'created_date': createdDate,
      if (lastModifiedDate != null) 'last_modified_date': lastModifiedDate,
    });
  }

  UserSettingTableCompanion copyWith({
    Value<int>? id,
    Value<DriftAny>? value,
    Value<DateTime>? createdDate,
    Value<DateTime>? lastModifiedDate,
  }) {
    return UserSettingTableCompanion(
      id: id ?? this.id,
      value: value ?? this.value,
      createdDate: createdDate ?? this.createdDate,
      lastModifiedDate: lastModifiedDate ?? this.lastModifiedDate,
    );
  }

  @override
  Map<String, Expression> toColumns(bool nullToAbsent) {
    final map = <String, Expression>{};
    if (id.present) {
      map['id'] = Variable<int>(id.value);
    }
    if (value.present) {
      map['value'] = Variable<DriftAny>(value.value);
    }
    if (createdDate.present) {
      map['created_date'] = Variable<DateTime>(createdDate.value);
    }
    if (lastModifiedDate.present) {
      map['last_modified_date'] = Variable<DateTime>(lastModifiedDate.value);
    }
    return map;
  }

  @override
  String toString() {
    return (StringBuffer('UserSettingTableCompanion(')
          ..write('id: $id, ')
          ..write('value: $value, ')
          ..write('createdDate: $createdDate, ')
          ..write('lastModifiedDate: $lastModifiedDate')
          ..write(')'))
        .toString();
  }
}

abstract class _$UserDatabase extends GeneratedDatabase {
  _$UserDatabase(QueryExecutor e) : super(e);
  $UserDatabaseManager get managers => $UserDatabaseManager(this);
  late final $ConversationSettingTableTable conversationSettingTable =
      $ConversationSettingTableTable(this);
  late final $LogEntryTableTable logEntryTable = $LogEntryTableTable(this);
  late final $UserSettingTableTable userSettingTable = $UserSettingTableTable(
    this,
  );
  @override
  Iterable<TableInfo<Table, Object?>> get allTables =>
      allSchemaEntities.whereType<TableInfo<Table, Object?>>();
  @override
  List<DatabaseSchemaEntity> get allSchemaEntities => [
    conversationSettingTable,
    logEntryTable,
    userSettingTable,
  ];
}

typedef $$ConversationSettingTableTableCreateCompanionBuilder =
    ConversationSettingTableCompanion Function({
      required BigInt contactId,
      required bool isGroupConversation,
      required int settingId,
      required DriftAny value,
      required DateTime createdDate,
      required DateTime lastModifiedDate,
    });
typedef $$ConversationSettingTableTableUpdateCompanionBuilder =
    ConversationSettingTableCompanion Function({
      Value<BigInt> contactId,
      Value<bool> isGroupConversation,
      Value<int> settingId,
      Value<DriftAny> value,
      Value<DateTime> createdDate,
      Value<DateTime> lastModifiedDate,
    });

class $$ConversationSettingTableTableFilterComposer
    extends Composer<_$UserDatabase, $ConversationSettingTableTable> {
  $$ConversationSettingTableTableFilterComposer({
    required super.$db,
    required super.$table,
    super.joinBuilder,
    super.$addJoinBuilderToRootComposer,
    super.$removeJoinBuilderFromRootComposer,
  });
  ColumnFilters<BigInt> get contactId => $composableBuilder(
    column: $table.contactId,
    builder: (column) => ColumnFilters(column),
  );

  ColumnFilters<bool> get isGroupConversation => $composableBuilder(
    column: $table.isGroupConversation,
    builder: (column) => ColumnFilters(column),
  );

  ColumnFilters<int> get settingId => $composableBuilder(
    column: $table.settingId,
    builder: (column) => ColumnFilters(column),
  );

  ColumnFilters<DriftAny> get value => $composableBuilder(
    column: $table.value,
    builder: (column) => ColumnFilters(column),
  );

  ColumnFilters<DateTime> get createdDate => $composableBuilder(
    column: $table.createdDate,
    builder: (column) => ColumnFilters(column),
  );

  ColumnFilters<DateTime> get lastModifiedDate => $composableBuilder(
    column: $table.lastModifiedDate,
    builder: (column) => ColumnFilters(column),
  );
}

class $$ConversationSettingTableTableOrderingComposer
    extends Composer<_$UserDatabase, $ConversationSettingTableTable> {
  $$ConversationSettingTableTableOrderingComposer({
    required super.$db,
    required super.$table,
    super.joinBuilder,
    super.$addJoinBuilderToRootComposer,
    super.$removeJoinBuilderFromRootComposer,
  });
  ColumnOrderings<BigInt> get contactId => $composableBuilder(
    column: $table.contactId,
    builder: (column) => ColumnOrderings(column),
  );

  ColumnOrderings<bool> get isGroupConversation => $composableBuilder(
    column: $table.isGroupConversation,
    builder: (column) => ColumnOrderings(column),
  );

  ColumnOrderings<int> get settingId => $composableBuilder(
    column: $table.settingId,
    builder: (column) => ColumnOrderings(column),
  );

  ColumnOrderings<DriftAny> get value => $composableBuilder(
    column: $table.value,
    builder: (column) => ColumnOrderings(column),
  );

  ColumnOrderings<DateTime> get createdDate => $composableBuilder(
    column: $table.createdDate,
    builder: (column) => ColumnOrderings(column),
  );

  ColumnOrderings<DateTime> get lastModifiedDate => $composableBuilder(
    column: $table.lastModifiedDate,
    builder: (column) => ColumnOrderings(column),
  );
}

class $$ConversationSettingTableTableAnnotationComposer
    extends Composer<_$UserDatabase, $ConversationSettingTableTable> {
  $$ConversationSettingTableTableAnnotationComposer({
    required super.$db,
    required super.$table,
    super.joinBuilder,
    super.$addJoinBuilderToRootComposer,
    super.$removeJoinBuilderFromRootComposer,
  });
  GeneratedColumn<BigInt> get contactId =>
      $composableBuilder(column: $table.contactId, builder: (column) => column);

  GeneratedColumn<bool> get isGroupConversation => $composableBuilder(
    column: $table.isGroupConversation,
    builder: (column) => column,
  );

  GeneratedColumn<int> get settingId =>
      $composableBuilder(column: $table.settingId, builder: (column) => column);

  GeneratedColumn<DriftAny> get value =>
      $composableBuilder(column: $table.value, builder: (column) => column);

  GeneratedColumn<DateTime> get createdDate => $composableBuilder(
    column: $table.createdDate,
    builder: (column) => column,
  );

  GeneratedColumn<DateTime> get lastModifiedDate => $composableBuilder(
    column: $table.lastModifiedDate,
    builder: (column) => column,
  );
}

class $$ConversationSettingTableTableTableManager
    extends
        RootTableManager<
          _$UserDatabase,
          $ConversationSettingTableTable,
          ConversationSettingTableData,
          $$ConversationSettingTableTableFilterComposer,
          $$ConversationSettingTableTableOrderingComposer,
          $$ConversationSettingTableTableAnnotationComposer,
          $$ConversationSettingTableTableCreateCompanionBuilder,
          $$ConversationSettingTableTableUpdateCompanionBuilder,
          (
            ConversationSettingTableData,
            BaseReferences<
              _$UserDatabase,
              $ConversationSettingTableTable,
              ConversationSettingTableData
            >,
          ),
          ConversationSettingTableData,
          PrefetchHooks Function()
        > {
  $$ConversationSettingTableTableTableManager(
    _$UserDatabase db,
    $ConversationSettingTableTable table,
  ) : super(
        TableManagerState(
          db: db,
          table: table,
          createFilteringComposer: () =>
              $$ConversationSettingTableTableFilterComposer(
                $db: db,
                $table: table,
              ),
          createOrderingComposer: () =>
              $$ConversationSettingTableTableOrderingComposer(
                $db: db,
                $table: table,
              ),
          createComputedFieldComposer: () =>
              $$ConversationSettingTableTableAnnotationComposer(
                $db: db,
                $table: table,
              ),
          updateCompanionCallback:
              ({
                Value<BigInt> contactId = const Value.absent(),
                Value<bool> isGroupConversation = const Value.absent(),
                Value<int> settingId = const Value.absent(),
                Value<DriftAny> value = const Value.absent(),
                Value<DateTime> createdDate = const Value.absent(),
                Value<DateTime> lastModifiedDate = const Value.absent(),
              }) => ConversationSettingTableCompanion(
                contactId: contactId,
                isGroupConversation: isGroupConversation,
                settingId: settingId,
                value: value,
                createdDate: createdDate,
                lastModifiedDate: lastModifiedDate,
              ),
          createCompanionCallback:
              ({
                required BigInt contactId,
                required bool isGroupConversation,
                required int settingId,
                required DriftAny value,
                required DateTime createdDate,
                required DateTime lastModifiedDate,
              }) => ConversationSettingTableCompanion.insert(
                contactId: contactId,
                isGroupConversation: isGroupConversation,
                settingId: settingId,
                value: value,
                createdDate: createdDate,
                lastModifiedDate: lastModifiedDate,
              ),
          withReferenceMapper: (p0) => p0
              .map((e) => (e.readTable(table), BaseReferences(db, table, e)))
              .toList(),
          prefetchHooksCallback: null,
        ),
      );
}

typedef $$ConversationSettingTableTableProcessedTableManager =
    ProcessedTableManager<
      _$UserDatabase,
      $ConversationSettingTableTable,
      ConversationSettingTableData,
      $$ConversationSettingTableTableFilterComposer,
      $$ConversationSettingTableTableOrderingComposer,
      $$ConversationSettingTableTableAnnotationComposer,
      $$ConversationSettingTableTableCreateCompanionBuilder,
      $$ConversationSettingTableTableUpdateCompanionBuilder,
      (
        ConversationSettingTableData,
        BaseReferences<
          _$UserDatabase,
          $ConversationSettingTableTable,
          ConversationSettingTableData
        >,
      ),
      ConversationSettingTableData,
      PrefetchHooks Function()
    >;
typedef $$LogEntryTableTableCreateCompanionBuilder =
    LogEntryTableCompanion Function({
      Value<int> id,
      required LogLevel level,
      required DateTime createdDate,
      required String message,
    });
typedef $$LogEntryTableTableUpdateCompanionBuilder =
    LogEntryTableCompanion Function({
      Value<int> id,
      Value<LogLevel> level,
      Value<DateTime> createdDate,
      Value<String> message,
    });

class $$LogEntryTableTableFilterComposer
    extends Composer<_$UserDatabase, $LogEntryTableTable> {
  $$LogEntryTableTableFilterComposer({
    required super.$db,
    required super.$table,
    super.joinBuilder,
    super.$addJoinBuilderToRootComposer,
    super.$removeJoinBuilderFromRootComposer,
  });
  ColumnFilters<int> get id => $composableBuilder(
    column: $table.id,
    builder: (column) => ColumnFilters(column),
  );

  ColumnWithTypeConverterFilters<LogLevel, LogLevel, int> get level =>
      $composableBuilder(
        column: $table.level,
        builder: (column) => ColumnWithTypeConverterFilters(column),
      );

  ColumnFilters<DateTime> get createdDate => $composableBuilder(
    column: $table.createdDate,
    builder: (column) => ColumnFilters(column),
  );

  ColumnFilters<String> get message => $composableBuilder(
    column: $table.message,
    builder: (column) => ColumnFilters(column),
  );
}

class $$LogEntryTableTableOrderingComposer
    extends Composer<_$UserDatabase, $LogEntryTableTable> {
  $$LogEntryTableTableOrderingComposer({
    required super.$db,
    required super.$table,
    super.joinBuilder,
    super.$addJoinBuilderToRootComposer,
    super.$removeJoinBuilderFromRootComposer,
  });
  ColumnOrderings<int> get id => $composableBuilder(
    column: $table.id,
    builder: (column) => ColumnOrderings(column),
  );

  ColumnOrderings<int> get level => $composableBuilder(
    column: $table.level,
    builder: (column) => ColumnOrderings(column),
  );

  ColumnOrderings<DateTime> get createdDate => $composableBuilder(
    column: $table.createdDate,
    builder: (column) => ColumnOrderings(column),
  );

  ColumnOrderings<String> get message => $composableBuilder(
    column: $table.message,
    builder: (column) => ColumnOrderings(column),
  );
}

class $$LogEntryTableTableAnnotationComposer
    extends Composer<_$UserDatabase, $LogEntryTableTable> {
  $$LogEntryTableTableAnnotationComposer({
    required super.$db,
    required super.$table,
    super.joinBuilder,
    super.$addJoinBuilderToRootComposer,
    super.$removeJoinBuilderFromRootComposer,
  });
  GeneratedColumn<int> get id =>
      $composableBuilder(column: $table.id, builder: (column) => column);

  GeneratedColumnWithTypeConverter<LogLevel, int> get level =>
      $composableBuilder(column: $table.level, builder: (column) => column);

  GeneratedColumn<DateTime> get createdDate => $composableBuilder(
    column: $table.createdDate,
    builder: (column) => column,
  );

  GeneratedColumn<String> get message =>
      $composableBuilder(column: $table.message, builder: (column) => column);
}

class $$LogEntryTableTableTableManager
    extends
        RootTableManager<
          _$UserDatabase,
          $LogEntryTableTable,
          LogEntryTableData,
          $$LogEntryTableTableFilterComposer,
          $$LogEntryTableTableOrderingComposer,
          $$LogEntryTableTableAnnotationComposer,
          $$LogEntryTableTableCreateCompanionBuilder,
          $$LogEntryTableTableUpdateCompanionBuilder,
          (
            LogEntryTableData,
            BaseReferences<
              _$UserDatabase,
              $LogEntryTableTable,
              LogEntryTableData
            >,
          ),
          LogEntryTableData,
          PrefetchHooks Function()
        > {
  $$LogEntryTableTableTableManager(_$UserDatabase db, $LogEntryTableTable table)
    : super(
        TableManagerState(
          db: db,
          table: table,
          createFilteringComposer: () =>
              $$LogEntryTableTableFilterComposer($db: db, $table: table),
          createOrderingComposer: () =>
              $$LogEntryTableTableOrderingComposer($db: db, $table: table),
          createComputedFieldComposer: () =>
              $$LogEntryTableTableAnnotationComposer($db: db, $table: table),
          updateCompanionCallback:
              ({
                Value<int> id = const Value.absent(),
                Value<LogLevel> level = const Value.absent(),
                Value<DateTime> createdDate = const Value.absent(),
                Value<String> message = const Value.absent(),
              }) => LogEntryTableCompanion(
                id: id,
                level: level,
                createdDate: createdDate,
                message: message,
              ),
          createCompanionCallback:
              ({
                Value<int> id = const Value.absent(),
                required LogLevel level,
                required DateTime createdDate,
                required String message,
              }) => LogEntryTableCompanion.insert(
                id: id,
                level: level,
                createdDate: createdDate,
                message: message,
              ),
          withReferenceMapper: (p0) => p0
              .map((e) => (e.readTable(table), BaseReferences(db, table, e)))
              .toList(),
          prefetchHooksCallback: null,
        ),
      );
}

typedef $$LogEntryTableTableProcessedTableManager =
    ProcessedTableManager<
      _$UserDatabase,
      $LogEntryTableTable,
      LogEntryTableData,
      $$LogEntryTableTableFilterComposer,
      $$LogEntryTableTableOrderingComposer,
      $$LogEntryTableTableAnnotationComposer,
      $$LogEntryTableTableCreateCompanionBuilder,
      $$LogEntryTableTableUpdateCompanionBuilder,
      (
        LogEntryTableData,
        BaseReferences<_$UserDatabase, $LogEntryTableTable, LogEntryTableData>,
      ),
      LogEntryTableData,
      PrefetchHooks Function()
    >;
typedef $$UserSettingTableTableCreateCompanionBuilder =
    UserSettingTableCompanion Function({
      required int id,
      required DriftAny value,
      required DateTime createdDate,
      required DateTime lastModifiedDate,
    });
typedef $$UserSettingTableTableUpdateCompanionBuilder =
    UserSettingTableCompanion Function({
      Value<int> id,
      Value<DriftAny> value,
      Value<DateTime> createdDate,
      Value<DateTime> lastModifiedDate,
    });

class $$UserSettingTableTableFilterComposer
    extends Composer<_$UserDatabase, $UserSettingTableTable> {
  $$UserSettingTableTableFilterComposer({
    required super.$db,
    required super.$table,
    super.joinBuilder,
    super.$addJoinBuilderToRootComposer,
    super.$removeJoinBuilderFromRootComposer,
  });
  ColumnFilters<int> get id => $composableBuilder(
    column: $table.id,
    builder: (column) => ColumnFilters(column),
  );

  ColumnFilters<DriftAny> get value => $composableBuilder(
    column: $table.value,
    builder: (column) => ColumnFilters(column),
  );

  ColumnFilters<DateTime> get createdDate => $composableBuilder(
    column: $table.createdDate,
    builder: (column) => ColumnFilters(column),
  );

  ColumnFilters<DateTime> get lastModifiedDate => $composableBuilder(
    column: $table.lastModifiedDate,
    builder: (column) => ColumnFilters(column),
  );
}

class $$UserSettingTableTableOrderingComposer
    extends Composer<_$UserDatabase, $UserSettingTableTable> {
  $$UserSettingTableTableOrderingComposer({
    required super.$db,
    required super.$table,
    super.joinBuilder,
    super.$addJoinBuilderToRootComposer,
    super.$removeJoinBuilderFromRootComposer,
  });
  ColumnOrderings<int> get id => $composableBuilder(
    column: $table.id,
    builder: (column) => ColumnOrderings(column),
  );

  ColumnOrderings<DriftAny> get value => $composableBuilder(
    column: $table.value,
    builder: (column) => ColumnOrderings(column),
  );

  ColumnOrderings<DateTime> get createdDate => $composableBuilder(
    column: $table.createdDate,
    builder: (column) => ColumnOrderings(column),
  );

  ColumnOrderings<DateTime> get lastModifiedDate => $composableBuilder(
    column: $table.lastModifiedDate,
    builder: (column) => ColumnOrderings(column),
  );
}

class $$UserSettingTableTableAnnotationComposer
    extends Composer<_$UserDatabase, $UserSettingTableTable> {
  $$UserSettingTableTableAnnotationComposer({
    required super.$db,
    required super.$table,
    super.joinBuilder,
    super.$addJoinBuilderToRootComposer,
    super.$removeJoinBuilderFromRootComposer,
  });
  GeneratedColumn<int> get id =>
      $composableBuilder(column: $table.id, builder: (column) => column);

  GeneratedColumn<DriftAny> get value =>
      $composableBuilder(column: $table.value, builder: (column) => column);

  GeneratedColumn<DateTime> get createdDate => $composableBuilder(
    column: $table.createdDate,
    builder: (column) => column,
  );

  GeneratedColumn<DateTime> get lastModifiedDate => $composableBuilder(
    column: $table.lastModifiedDate,
    builder: (column) => column,
  );
}

class $$UserSettingTableTableTableManager
    extends
        RootTableManager<
          _$UserDatabase,
          $UserSettingTableTable,
          UserSettingTableData,
          $$UserSettingTableTableFilterComposer,
          $$UserSettingTableTableOrderingComposer,
          $$UserSettingTableTableAnnotationComposer,
          $$UserSettingTableTableCreateCompanionBuilder,
          $$UserSettingTableTableUpdateCompanionBuilder,
          (
            UserSettingTableData,
            BaseReferences<
              _$UserDatabase,
              $UserSettingTableTable,
              UserSettingTableData
            >,
          ),
          UserSettingTableData,
          PrefetchHooks Function()
        > {
  $$UserSettingTableTableTableManager(
    _$UserDatabase db,
    $UserSettingTableTable table,
  ) : super(
        TableManagerState(
          db: db,
          table: table,
          createFilteringComposer: () =>
              $$UserSettingTableTableFilterComposer($db: db, $table: table),
          createOrderingComposer: () =>
              $$UserSettingTableTableOrderingComposer($db: db, $table: table),
          createComputedFieldComposer: () =>
              $$UserSettingTableTableAnnotationComposer($db: db, $table: table),
          updateCompanionCallback:
              ({
                Value<int> id = const Value.absent(),
                Value<DriftAny> value = const Value.absent(),
                Value<DateTime> createdDate = const Value.absent(),
                Value<DateTime> lastModifiedDate = const Value.absent(),
              }) => UserSettingTableCompanion(
                id: id,
                value: value,
                createdDate: createdDate,
                lastModifiedDate: lastModifiedDate,
              ),
          createCompanionCallback:
              ({
                required int id,
                required DriftAny value,
                required DateTime createdDate,
                required DateTime lastModifiedDate,
              }) => UserSettingTableCompanion.insert(
                id: id,
                value: value,
                createdDate: createdDate,
                lastModifiedDate: lastModifiedDate,
              ),
          withReferenceMapper: (p0) => p0
              .map((e) => (e.readTable(table), BaseReferences(db, table, e)))
              .toList(),
          prefetchHooksCallback: null,
        ),
      );
}

typedef $$UserSettingTableTableProcessedTableManager =
    ProcessedTableManager<
      _$UserDatabase,
      $UserSettingTableTable,
      UserSettingTableData,
      $$UserSettingTableTableFilterComposer,
      $$UserSettingTableTableOrderingComposer,
      $$UserSettingTableTableAnnotationComposer,
      $$UserSettingTableTableCreateCompanionBuilder,
      $$UserSettingTableTableUpdateCompanionBuilder,
      (
        UserSettingTableData,
        BaseReferences<
          _$UserDatabase,
          $UserSettingTableTable,
          UserSettingTableData
        >,
      ),
      UserSettingTableData,
      PrefetchHooks Function()
    >;

class $UserDatabaseManager {
  final _$UserDatabase _db;
  $UserDatabaseManager(this._db);
  $$ConversationSettingTableTableTableManager get conversationSettingTable =>
      $$ConversationSettingTableTableTableManager(
        _db,
        _db.conversationSettingTable,
      );
  $$LogEntryTableTableTableManager get logEntryTable =>
      $$LogEntryTableTableTableManager(_db, _db.logEntryTable);
  $$UserSettingTableTableTableManager get userSettingTable =>
      $$UserSettingTableTableTableManager(_db, _db.userSettingTable);
}
