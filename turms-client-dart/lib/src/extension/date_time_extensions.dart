import 'dart:core';

import 'package:fixnum/fixnum.dart';

extension DateTimeExtensions on DateTime {
  Int64 toInt64() => Int64(millisecondsSinceEpoch);
}
