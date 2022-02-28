import 'dart:core';

import 'package:fixnum/fixnum.dart' show Int64;

class MessageAddition {
  final bool isMentioned;
  final Set<Int64> mentionedUserIds;
  final Set<Int64> recalledMessageIds;

  MessageAddition(
      this.isMentioned, this.mentionedUserIds, this.recalledMessageIds);
}
