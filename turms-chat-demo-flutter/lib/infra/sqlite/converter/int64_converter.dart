import 'package:drift/drift.dart';
import 'package:fixnum/fixnum.dart';

import '../../built_in_types/built_in_type_helpers.dart';

class Int64Converter extends TypeConverter<Int64, BigInt> {
  const Int64Converter();

  @override
  Int64 fromSql(BigInt fromDb) => fromDb.toInt64();

  @override
  BigInt toSql(Int64 value) => value.toBigInt();
}
