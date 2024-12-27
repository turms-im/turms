// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'app_database.dart';

// ignore_for_file: type=lint
class $AppSettingTableTable extends AppSettingTable
    with TableInfo<$AppSettingTableTable, AppSettingTableData> {
  @override
  final GeneratedDatabase attachedDatabase;
  final String? _alias;
  $AppSettingTableTable(this.attachedDatabase, [this._alias]);
  static const VerificationMeta _idMeta = const VerificationMeta('id');
  @override
  late final GeneratedColumn<int> id = GeneratedColumn<int>(
      'id', aliasedName, false,
      type: DriftSqlType.int, requiredDuringInsert: true);
  static const VerificationMeta _valueMeta = const VerificationMeta('value');
  @override
  late final GeneratedColumn<DriftAny> value = GeneratedColumn<DriftAny>(
      'value', aliasedName, false,
      type: DriftSqlType.any, requiredDuringInsert: true);
  static const VerificationMeta _createdDateMeta =
      const VerificationMeta('createdDate');
  @override
  late final GeneratedColumn<DateTime> createdDate = GeneratedColumn<DateTime>(
      'created_date', aliasedName, false,
      type: DriftSqlType.dateTime, requiredDuringInsert: true);
  static const VerificationMeta _lastModifiedDateMeta =
      const VerificationMeta('lastModifiedDate');
  @override
  late final GeneratedColumn<DateTime> lastModifiedDate =
      GeneratedColumn<DateTime>('last_modified_date', aliasedName, false,
          type: DriftSqlType.dateTime, requiredDuringInsert: true);
  @override
  List<GeneratedColumn> get $columns =>
      [id, value, createdDate, lastModifiedDate];
  @override
  String get aliasedName => _alias ?? actualTableName;
  @override
  String get actualTableName => $name;
  static const String $name = 'app_setting';
  @override
  VerificationContext validateIntegrity(
      Insertable<AppSettingTableData> instance,
      {bool isInserting = false}) {
    final context = VerificationContext();
    final data = instance.toColumns(true);
    if (data.containsKey('id')) {
      context.handle(_idMeta, id.isAcceptableOrUnknown(data['id']!, _idMeta));
    } else if (isInserting) {
      context.missing(_idMeta);
    }
    if (data.containsKey('value')) {
      context.handle(
          _valueMeta, value.isAcceptableOrUnknown(data['value']!, _valueMeta));
    } else if (isInserting) {
      context.missing(_valueMeta);
    }
    if (data.containsKey('created_date')) {
      context.handle(
          _createdDateMeta,
          createdDate.isAcceptableOrUnknown(
              data['created_date']!, _createdDateMeta));
    } else if (isInserting) {
      context.missing(_createdDateMeta);
    }
    if (data.containsKey('last_modified_date')) {
      context.handle(
          _lastModifiedDateMeta,
          lastModifiedDate.isAcceptableOrUnknown(
              data['last_modified_date']!, _lastModifiedDateMeta));
    } else if (isInserting) {
      context.missing(_lastModifiedDateMeta);
    }
    return context;
  }

  @override
  Set<GeneratedColumn> get $primaryKey => {id};
  @override
  AppSettingTableData map(Map<String, dynamic> data, {String? tablePrefix}) {
    final effectivePrefix = tablePrefix != null ? '$tablePrefix.' : '';
    return AppSettingTableData(
      id: attachedDatabase.typeMapping
          .read(DriftSqlType.int, data['${effectivePrefix}id'])!,
      value: attachedDatabase.typeMapping
          .read(DriftSqlType.any, data['${effectivePrefix}value'])!,
      createdDate: attachedDatabase.typeMapping
          .read(DriftSqlType.dateTime, data['${effectivePrefix}created_date'])!,
      lastModifiedDate: attachedDatabase.typeMapping.read(
          DriftSqlType.dateTime, data['${effectivePrefix}last_modified_date'])!,
    );
  }

  @override
  $AppSettingTableTable createAlias(String alias) {
    return $AppSettingTableTable(attachedDatabase, alias);
  }

  @override
  bool get withoutRowId => true;
  @override
  bool get isStrict => true;
}

class AppSettingTableData extends DataClass
    implements Insertable<AppSettingTableData> {
  final int id;
  final DriftAny value;
  final DateTime createdDate;
  final DateTime lastModifiedDate;
  const AppSettingTableData(
      {required this.id,
      required this.value,
      required this.createdDate,
      required this.lastModifiedDate});
  @override
  Map<String, Expression> toColumns(bool nullToAbsent) {
    final map = <String, Expression>{};
    map['id'] = Variable<int>(id);
    map['value'] = Variable<DriftAny>(value);
    map['created_date'] = Variable<DateTime>(createdDate);
    map['last_modified_date'] = Variable<DateTime>(lastModifiedDate);
    return map;
  }

  AppSettingTableCompanion toCompanion(bool nullToAbsent) {
    return AppSettingTableCompanion(
      id: Value(id),
      value: Value(value),
      createdDate: Value(createdDate),
      lastModifiedDate: Value(lastModifiedDate),
    );
  }

  factory AppSettingTableData.fromJson(Map<String, dynamic> json,
      {ValueSerializer? serializer}) {
    serializer ??= driftRuntimeOptions.defaultSerializer;
    return AppSettingTableData(
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

  AppSettingTableData copyWith(
          {int? id,
          DriftAny? value,
          DateTime? createdDate,
          DateTime? lastModifiedDate}) =>
      AppSettingTableData(
        id: id ?? this.id,
        value: value ?? this.value,
        createdDate: createdDate ?? this.createdDate,
        lastModifiedDate: lastModifiedDate ?? this.lastModifiedDate,
      );
  AppSettingTableData copyWithCompanion(AppSettingTableCompanion data) {
    return AppSettingTableData(
      id: data.id.present ? data.id.value : this.id,
      value: data.value.present ? data.value.value : this.value,
      createdDate:
          data.createdDate.present ? data.createdDate.value : this.createdDate,
      lastModifiedDate: data.lastModifiedDate.present
          ? data.lastModifiedDate.value
          : this.lastModifiedDate,
    );
  }

  @override
  String toString() {
    return (StringBuffer('AppSettingTableData(')
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
      (other is AppSettingTableData &&
          other.id == this.id &&
          other.value == this.value &&
          other.createdDate == this.createdDate &&
          other.lastModifiedDate == this.lastModifiedDate);
}

class AppSettingTableCompanion extends UpdateCompanion<AppSettingTableData> {
  final Value<int> id;
  final Value<DriftAny> value;
  final Value<DateTime> createdDate;
  final Value<DateTime> lastModifiedDate;
  const AppSettingTableCompanion({
    this.id = const Value.absent(),
    this.value = const Value.absent(),
    this.createdDate = const Value.absent(),
    this.lastModifiedDate = const Value.absent(),
  });
  AppSettingTableCompanion.insert({
    required int id,
    required DriftAny value,
    required DateTime createdDate,
    required DateTime lastModifiedDate,
  })  : id = Value(id),
        value = Value(value),
        createdDate = Value(createdDate),
        lastModifiedDate = Value(lastModifiedDate);
  static Insertable<AppSettingTableData> custom({
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

  AppSettingTableCompanion copyWith(
      {Value<int>? id,
      Value<DriftAny>? value,
      Value<DateTime>? createdDate,
      Value<DateTime>? lastModifiedDate}) {
    return AppSettingTableCompanion(
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
    return (StringBuffer('AppSettingTableCompanion(')
          ..write('id: $id, ')
          ..write('value: $value, ')
          ..write('createdDate: $createdDate, ')
          ..write('lastModifiedDate: $lastModifiedDate')
          ..write(')'))
        .toString();
  }
}

class $UserLoginInfoTableTable extends UserLoginInfoTable
    with TableInfo<$UserLoginInfoTableTable, UserLoginInfoTableData> {
  @override
  final GeneratedDatabase attachedDatabase;
  final String? _alias;
  $UserLoginInfoTableTable(this.attachedDatabase, [this._alias]);
  static const VerificationMeta _userIdMeta = const VerificationMeta('userId');
  @override
  late final GeneratedColumnWithTypeConverter<Int64, BigInt> userId =
      GeneratedColumn<BigInt>('user_id', aliasedName, false,
              type: DriftSqlType.bigInt, requiredDuringInsert: true)
          .withConverter<Int64>($UserLoginInfoTableTable.$converteruserId);
  static const VerificationMeta _passwordMeta =
      const VerificationMeta('password');
  @override
  late final GeneratedColumn<String> password = GeneratedColumn<String>(
      'password', aliasedName, false,
      type: DriftSqlType.string, requiredDuringInsert: true);
  static const VerificationMeta _createdDateMeta =
      const VerificationMeta('createdDate');
  @override
  late final GeneratedColumn<DateTime> createdDate = GeneratedColumn<DateTime>(
      'created_date', aliasedName, false,
      type: DriftSqlType.dateTime, requiredDuringInsert: true);
  static const VerificationMeta _lastModifiedDateMeta =
      const VerificationMeta('lastModifiedDate');
  @override
  late final GeneratedColumn<DateTime> lastModifiedDate =
      GeneratedColumn<DateTime>('last_modified_date', aliasedName, false,
          type: DriftSqlType.dateTime, requiredDuringInsert: true);
  @override
  List<GeneratedColumn> get $columns =>
      [userId, password, createdDate, lastModifiedDate];
  @override
  String get aliasedName => _alias ?? actualTableName;
  @override
  String get actualTableName => $name;
  static const String $name = 'user_login_info';
  @override
  VerificationContext validateIntegrity(
      Insertable<UserLoginInfoTableData> instance,
      {bool isInserting = false}) {
    final context = VerificationContext();
    final data = instance.toColumns(true);
    context.handle(_userIdMeta, const VerificationResult.success());
    if (data.containsKey('password')) {
      context.handle(_passwordMeta,
          password.isAcceptableOrUnknown(data['password']!, _passwordMeta));
    } else if (isInserting) {
      context.missing(_passwordMeta);
    }
    if (data.containsKey('created_date')) {
      context.handle(
          _createdDateMeta,
          createdDate.isAcceptableOrUnknown(
              data['created_date']!, _createdDateMeta));
    } else if (isInserting) {
      context.missing(_createdDateMeta);
    }
    if (data.containsKey('last_modified_date')) {
      context.handle(
          _lastModifiedDateMeta,
          lastModifiedDate.isAcceptableOrUnknown(
              data['last_modified_date']!, _lastModifiedDateMeta));
    } else if (isInserting) {
      context.missing(_lastModifiedDateMeta);
    }
    return context;
  }

  @override
  Set<GeneratedColumn> get $primaryKey => {userId};
  @override
  UserLoginInfoTableData map(Map<String, dynamic> data, {String? tablePrefix}) {
    final effectivePrefix = tablePrefix != null ? '$tablePrefix.' : '';
    return UserLoginInfoTableData(
      userId: $UserLoginInfoTableTable.$converteruserId.fromSql(attachedDatabase
          .typeMapping
          .read(DriftSqlType.bigInt, data['${effectivePrefix}user_id'])!),
      password: attachedDatabase.typeMapping
          .read(DriftSqlType.string, data['${effectivePrefix}password'])!,
      createdDate: attachedDatabase.typeMapping
          .read(DriftSqlType.dateTime, data['${effectivePrefix}created_date'])!,
      lastModifiedDate: attachedDatabase.typeMapping.read(
          DriftSqlType.dateTime, data['${effectivePrefix}last_modified_date'])!,
    );
  }

  @override
  $UserLoginInfoTableTable createAlias(String alias) {
    return $UserLoginInfoTableTable(attachedDatabase, alias);
  }

  static TypeConverter<Int64, BigInt> $converteruserId = const Int64Converter();
  @override
  bool get withoutRowId => true;
  @override
  bool get isStrict => true;
}

class UserLoginInfoTableData extends DataClass
    implements Insertable<UserLoginInfoTableData> {
  final Int64 userId;
  final String password;
  final DateTime createdDate;
  final DateTime lastModifiedDate;
  const UserLoginInfoTableData(
      {required this.userId,
      required this.password,
      required this.createdDate,
      required this.lastModifiedDate});
  @override
  Map<String, Expression> toColumns(bool nullToAbsent) {
    final map = <String, Expression>{};
    {
      map['user_id'] = Variable<BigInt>(
          $UserLoginInfoTableTable.$converteruserId.toSql(userId));
    }
    map['password'] = Variable<String>(password);
    map['created_date'] = Variable<DateTime>(createdDate);
    map['last_modified_date'] = Variable<DateTime>(lastModifiedDate);
    return map;
  }

  UserLoginInfoTableCompanion toCompanion(bool nullToAbsent) {
    return UserLoginInfoTableCompanion(
      userId: Value(userId),
      password: Value(password),
      createdDate: Value(createdDate),
      lastModifiedDate: Value(lastModifiedDate),
    );
  }

  factory UserLoginInfoTableData.fromJson(Map<String, dynamic> json,
      {ValueSerializer? serializer}) {
    serializer ??= driftRuntimeOptions.defaultSerializer;
    return UserLoginInfoTableData(
      userId: serializer.fromJson<Int64>(json['userId']),
      password: serializer.fromJson<String>(json['password']),
      createdDate: serializer.fromJson<DateTime>(json['createdDate']),
      lastModifiedDate: serializer.fromJson<DateTime>(json['lastModifiedDate']),
    );
  }
  @override
  Map<String, dynamic> toJson({ValueSerializer? serializer}) {
    serializer ??= driftRuntimeOptions.defaultSerializer;
    return <String, dynamic>{
      'userId': serializer.toJson<Int64>(userId),
      'password': serializer.toJson<String>(password),
      'createdDate': serializer.toJson<DateTime>(createdDate),
      'lastModifiedDate': serializer.toJson<DateTime>(lastModifiedDate),
    };
  }

  UserLoginInfoTableData copyWith(
          {Int64? userId,
          String? password,
          DateTime? createdDate,
          DateTime? lastModifiedDate}) =>
      UserLoginInfoTableData(
        userId: userId ?? this.userId,
        password: password ?? this.password,
        createdDate: createdDate ?? this.createdDate,
        lastModifiedDate: lastModifiedDate ?? this.lastModifiedDate,
      );
  UserLoginInfoTableData copyWithCompanion(UserLoginInfoTableCompanion data) {
    return UserLoginInfoTableData(
      userId: data.userId.present ? data.userId.value : this.userId,
      password: data.password.present ? data.password.value : this.password,
      createdDate:
          data.createdDate.present ? data.createdDate.value : this.createdDate,
      lastModifiedDate: data.lastModifiedDate.present
          ? data.lastModifiedDate.value
          : this.lastModifiedDate,
    );
  }

  @override
  String toString() {
    return (StringBuffer('UserLoginInfoTableData(')
          ..write('userId: $userId, ')
          ..write('password: $password, ')
          ..write('createdDate: $createdDate, ')
          ..write('lastModifiedDate: $lastModifiedDate')
          ..write(')'))
        .toString();
  }

  @override
  int get hashCode =>
      Object.hash(userId, password, createdDate, lastModifiedDate);
  @override
  bool operator ==(Object other) =>
      identical(this, other) ||
      (other is UserLoginInfoTableData &&
          other.userId == this.userId &&
          other.password == this.password &&
          other.createdDate == this.createdDate &&
          other.lastModifiedDate == this.lastModifiedDate);
}

class UserLoginInfoTableCompanion
    extends UpdateCompanion<UserLoginInfoTableData> {
  final Value<Int64> userId;
  final Value<String> password;
  final Value<DateTime> createdDate;
  final Value<DateTime> lastModifiedDate;
  const UserLoginInfoTableCompanion({
    this.userId = const Value.absent(),
    this.password = const Value.absent(),
    this.createdDate = const Value.absent(),
    this.lastModifiedDate = const Value.absent(),
  });
  UserLoginInfoTableCompanion.insert({
    required Int64 userId,
    required String password,
    required DateTime createdDate,
    required DateTime lastModifiedDate,
  })  : userId = Value(userId),
        password = Value(password),
        createdDate = Value(createdDate),
        lastModifiedDate = Value(lastModifiedDate);
  static Insertable<UserLoginInfoTableData> custom({
    Expression<BigInt>? userId,
    Expression<String>? password,
    Expression<DateTime>? createdDate,
    Expression<DateTime>? lastModifiedDate,
  }) {
    return RawValuesInsertable({
      if (userId != null) 'user_id': userId,
      if (password != null) 'password': password,
      if (createdDate != null) 'created_date': createdDate,
      if (lastModifiedDate != null) 'last_modified_date': lastModifiedDate,
    });
  }

  UserLoginInfoTableCompanion copyWith(
      {Value<Int64>? userId,
      Value<String>? password,
      Value<DateTime>? createdDate,
      Value<DateTime>? lastModifiedDate}) {
    return UserLoginInfoTableCompanion(
      userId: userId ?? this.userId,
      password: password ?? this.password,
      createdDate: createdDate ?? this.createdDate,
      lastModifiedDate: lastModifiedDate ?? this.lastModifiedDate,
    );
  }

  @override
  Map<String, Expression> toColumns(bool nullToAbsent) {
    final map = <String, Expression>{};
    if (userId.present) {
      map['user_id'] = Variable<BigInt>(
          $UserLoginInfoTableTable.$converteruserId.toSql(userId.value));
    }
    if (password.present) {
      map['password'] = Variable<String>(password.value);
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
    return (StringBuffer('UserLoginInfoTableCompanion(')
          ..write('userId: $userId, ')
          ..write('password: $password, ')
          ..write('createdDate: $createdDate, ')
          ..write('lastModifiedDate: $lastModifiedDate')
          ..write(')'))
        .toString();
  }
}

abstract class _$AppDatabase extends GeneratedDatabase {
  _$AppDatabase(QueryExecutor e) : super(e);
  $AppDatabaseManager get managers => $AppDatabaseManager(this);
  late final $AppSettingTableTable appSettingTable =
      $AppSettingTableTable(this);
  late final $UserLoginInfoTableTable userLoginInfoTable =
      $UserLoginInfoTableTable(this);
  @override
  Iterable<TableInfo<Table, Object?>> get allTables =>
      allSchemaEntities.whereType<TableInfo<Table, Object?>>();
  @override
  List<DatabaseSchemaEntity> get allSchemaEntities =>
      [appSettingTable, userLoginInfoTable];
}

typedef $$AppSettingTableTableCreateCompanionBuilder = AppSettingTableCompanion
    Function({
  required int id,
  required DriftAny value,
  required DateTime createdDate,
  required DateTime lastModifiedDate,
});
typedef $$AppSettingTableTableUpdateCompanionBuilder = AppSettingTableCompanion
    Function({
  Value<int> id,
  Value<DriftAny> value,
  Value<DateTime> createdDate,
  Value<DateTime> lastModifiedDate,
});

class $$AppSettingTableTableFilterComposer
    extends Composer<_$AppDatabase, $AppSettingTableTable> {
  $$AppSettingTableTableFilterComposer({
    required super.$db,
    required super.$table,
    super.joinBuilder,
    super.$addJoinBuilderToRootComposer,
    super.$removeJoinBuilderFromRootComposer,
  });
  ColumnFilters<int> get id => $composableBuilder(
      column: $table.id, builder: (column) => ColumnFilters(column));

  ColumnFilters<DriftAny> get value => $composableBuilder(
      column: $table.value, builder: (column) => ColumnFilters(column));

  ColumnFilters<DateTime> get createdDate => $composableBuilder(
      column: $table.createdDate, builder: (column) => ColumnFilters(column));

  ColumnFilters<DateTime> get lastModifiedDate => $composableBuilder(
      column: $table.lastModifiedDate,
      builder: (column) => ColumnFilters(column));
}

class $$AppSettingTableTableOrderingComposer
    extends Composer<_$AppDatabase, $AppSettingTableTable> {
  $$AppSettingTableTableOrderingComposer({
    required super.$db,
    required super.$table,
    super.joinBuilder,
    super.$addJoinBuilderToRootComposer,
    super.$removeJoinBuilderFromRootComposer,
  });
  ColumnOrderings<int> get id => $composableBuilder(
      column: $table.id, builder: (column) => ColumnOrderings(column));

  ColumnOrderings<DriftAny> get value => $composableBuilder(
      column: $table.value, builder: (column) => ColumnOrderings(column));

  ColumnOrderings<DateTime> get createdDate => $composableBuilder(
      column: $table.createdDate, builder: (column) => ColumnOrderings(column));

  ColumnOrderings<DateTime> get lastModifiedDate => $composableBuilder(
      column: $table.lastModifiedDate,
      builder: (column) => ColumnOrderings(column));
}

class $$AppSettingTableTableAnnotationComposer
    extends Composer<_$AppDatabase, $AppSettingTableTable> {
  $$AppSettingTableTableAnnotationComposer({
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
      column: $table.createdDate, builder: (column) => column);

  GeneratedColumn<DateTime> get lastModifiedDate => $composableBuilder(
      column: $table.lastModifiedDate, builder: (column) => column);
}

class $$AppSettingTableTableTableManager extends RootTableManager<
    _$AppDatabase,
    $AppSettingTableTable,
    AppSettingTableData,
    $$AppSettingTableTableFilterComposer,
    $$AppSettingTableTableOrderingComposer,
    $$AppSettingTableTableAnnotationComposer,
    $$AppSettingTableTableCreateCompanionBuilder,
    $$AppSettingTableTableUpdateCompanionBuilder,
    (
      AppSettingTableData,
      BaseReferences<_$AppDatabase, $AppSettingTableTable, AppSettingTableData>
    ),
    AppSettingTableData,
    PrefetchHooks Function()> {
  $$AppSettingTableTableTableManager(
      _$AppDatabase db, $AppSettingTableTable table)
      : super(TableManagerState(
          db: db,
          table: table,
          createFilteringComposer: () =>
              $$AppSettingTableTableFilterComposer($db: db, $table: table),
          createOrderingComposer: () =>
              $$AppSettingTableTableOrderingComposer($db: db, $table: table),
          createComputedFieldComposer: () =>
              $$AppSettingTableTableAnnotationComposer($db: db, $table: table),
          updateCompanionCallback: ({
            Value<int> id = const Value.absent(),
            Value<DriftAny> value = const Value.absent(),
            Value<DateTime> createdDate = const Value.absent(),
            Value<DateTime> lastModifiedDate = const Value.absent(),
          }) =>
              AppSettingTableCompanion(
            id: id,
            value: value,
            createdDate: createdDate,
            lastModifiedDate: lastModifiedDate,
          ),
          createCompanionCallback: ({
            required int id,
            required DriftAny value,
            required DateTime createdDate,
            required DateTime lastModifiedDate,
          }) =>
              AppSettingTableCompanion.insert(
            id: id,
            value: value,
            createdDate: createdDate,
            lastModifiedDate: lastModifiedDate,
          ),
          withReferenceMapper: (p0) => p0
              .map((e) => (e.readTable(table), BaseReferences(db, table, e)))
              .toList(),
          prefetchHooksCallback: null,
        ));
}

typedef $$AppSettingTableTableProcessedTableManager = ProcessedTableManager<
    _$AppDatabase,
    $AppSettingTableTable,
    AppSettingTableData,
    $$AppSettingTableTableFilterComposer,
    $$AppSettingTableTableOrderingComposer,
    $$AppSettingTableTableAnnotationComposer,
    $$AppSettingTableTableCreateCompanionBuilder,
    $$AppSettingTableTableUpdateCompanionBuilder,
    (
      AppSettingTableData,
      BaseReferences<_$AppDatabase, $AppSettingTableTable, AppSettingTableData>
    ),
    AppSettingTableData,
    PrefetchHooks Function()>;
typedef $$UserLoginInfoTableTableCreateCompanionBuilder
    = UserLoginInfoTableCompanion Function({
  required Int64 userId,
  required String password,
  required DateTime createdDate,
  required DateTime lastModifiedDate,
});
typedef $$UserLoginInfoTableTableUpdateCompanionBuilder
    = UserLoginInfoTableCompanion Function({
  Value<Int64> userId,
  Value<String> password,
  Value<DateTime> createdDate,
  Value<DateTime> lastModifiedDate,
});

class $$UserLoginInfoTableTableFilterComposer
    extends Composer<_$AppDatabase, $UserLoginInfoTableTable> {
  $$UserLoginInfoTableTableFilterComposer({
    required super.$db,
    required super.$table,
    super.joinBuilder,
    super.$addJoinBuilderToRootComposer,
    super.$removeJoinBuilderFromRootComposer,
  });
  ColumnWithTypeConverterFilters<Int64, Int64, BigInt> get userId =>
      $composableBuilder(
          column: $table.userId,
          builder: (column) => ColumnWithTypeConverterFilters(column));

  ColumnFilters<String> get password => $composableBuilder(
      column: $table.password, builder: (column) => ColumnFilters(column));

  ColumnFilters<DateTime> get createdDate => $composableBuilder(
      column: $table.createdDate, builder: (column) => ColumnFilters(column));

  ColumnFilters<DateTime> get lastModifiedDate => $composableBuilder(
      column: $table.lastModifiedDate,
      builder: (column) => ColumnFilters(column));
}

class $$UserLoginInfoTableTableOrderingComposer
    extends Composer<_$AppDatabase, $UserLoginInfoTableTable> {
  $$UserLoginInfoTableTableOrderingComposer({
    required super.$db,
    required super.$table,
    super.joinBuilder,
    super.$addJoinBuilderToRootComposer,
    super.$removeJoinBuilderFromRootComposer,
  });
  ColumnOrderings<BigInt> get userId => $composableBuilder(
      column: $table.userId, builder: (column) => ColumnOrderings(column));

  ColumnOrderings<String> get password => $composableBuilder(
      column: $table.password, builder: (column) => ColumnOrderings(column));

  ColumnOrderings<DateTime> get createdDate => $composableBuilder(
      column: $table.createdDate, builder: (column) => ColumnOrderings(column));

  ColumnOrderings<DateTime> get lastModifiedDate => $composableBuilder(
      column: $table.lastModifiedDate,
      builder: (column) => ColumnOrderings(column));
}

class $$UserLoginInfoTableTableAnnotationComposer
    extends Composer<_$AppDatabase, $UserLoginInfoTableTable> {
  $$UserLoginInfoTableTableAnnotationComposer({
    required super.$db,
    required super.$table,
    super.joinBuilder,
    super.$addJoinBuilderToRootComposer,
    super.$removeJoinBuilderFromRootComposer,
  });
  GeneratedColumnWithTypeConverter<Int64, BigInt> get userId =>
      $composableBuilder(column: $table.userId, builder: (column) => column);

  GeneratedColumn<String> get password =>
      $composableBuilder(column: $table.password, builder: (column) => column);

  GeneratedColumn<DateTime> get createdDate => $composableBuilder(
      column: $table.createdDate, builder: (column) => column);

  GeneratedColumn<DateTime> get lastModifiedDate => $composableBuilder(
      column: $table.lastModifiedDate, builder: (column) => column);
}

class $$UserLoginInfoTableTableTableManager extends RootTableManager<
    _$AppDatabase,
    $UserLoginInfoTableTable,
    UserLoginInfoTableData,
    $$UserLoginInfoTableTableFilterComposer,
    $$UserLoginInfoTableTableOrderingComposer,
    $$UserLoginInfoTableTableAnnotationComposer,
    $$UserLoginInfoTableTableCreateCompanionBuilder,
    $$UserLoginInfoTableTableUpdateCompanionBuilder,
    (
      UserLoginInfoTableData,
      BaseReferences<_$AppDatabase, $UserLoginInfoTableTable,
          UserLoginInfoTableData>
    ),
    UserLoginInfoTableData,
    PrefetchHooks Function()> {
  $$UserLoginInfoTableTableTableManager(
      _$AppDatabase db, $UserLoginInfoTableTable table)
      : super(TableManagerState(
          db: db,
          table: table,
          createFilteringComposer: () =>
              $$UserLoginInfoTableTableFilterComposer($db: db, $table: table),
          createOrderingComposer: () =>
              $$UserLoginInfoTableTableOrderingComposer($db: db, $table: table),
          createComputedFieldComposer: () =>
              $$UserLoginInfoTableTableAnnotationComposer(
                  $db: db, $table: table),
          updateCompanionCallback: ({
            Value<Int64> userId = const Value.absent(),
            Value<String> password = const Value.absent(),
            Value<DateTime> createdDate = const Value.absent(),
            Value<DateTime> lastModifiedDate = const Value.absent(),
          }) =>
              UserLoginInfoTableCompanion(
            userId: userId,
            password: password,
            createdDate: createdDate,
            lastModifiedDate: lastModifiedDate,
          ),
          createCompanionCallback: ({
            required Int64 userId,
            required String password,
            required DateTime createdDate,
            required DateTime lastModifiedDate,
          }) =>
              UserLoginInfoTableCompanion.insert(
            userId: userId,
            password: password,
            createdDate: createdDate,
            lastModifiedDate: lastModifiedDate,
          ),
          withReferenceMapper: (p0) => p0
              .map((e) => (e.readTable(table), BaseReferences(db, table, e)))
              .toList(),
          prefetchHooksCallback: null,
        ));
}

typedef $$UserLoginInfoTableTableProcessedTableManager = ProcessedTableManager<
    _$AppDatabase,
    $UserLoginInfoTableTable,
    UserLoginInfoTableData,
    $$UserLoginInfoTableTableFilterComposer,
    $$UserLoginInfoTableTableOrderingComposer,
    $$UserLoginInfoTableTableAnnotationComposer,
    $$UserLoginInfoTableTableCreateCompanionBuilder,
    $$UserLoginInfoTableTableUpdateCompanionBuilder,
    (
      UserLoginInfoTableData,
      BaseReferences<_$AppDatabase, $UserLoginInfoTableTable,
          UserLoginInfoTableData>
    ),
    UserLoginInfoTableData,
    PrefetchHooks Function()>;

class $AppDatabaseManager {
  final _$AppDatabase _db;
  $AppDatabaseManager(this._db);
  $$AppSettingTableTableTableManager get appSettingTable =>
      $$AppSettingTableTableTableManager(_db, _db.appSettingTable);
  $$UserLoginInfoTableTableTableManager get userLoginInfoTable =>
      $$UserLoginInfoTableTableTableManager(_db, _db.userLoginInfoTable);
}
