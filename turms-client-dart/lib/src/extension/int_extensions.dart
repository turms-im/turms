import 'dart:core';

import 'package:fixnum/fixnum.dart';

extension Int64Extensions on Int64 {
  DateTime toDateTime() => DateTime.fromMillisecondsSinceEpoch(toInt());
}

extension IntExtensions on int {
  Int64 toInt64() => Int64(this);
}
