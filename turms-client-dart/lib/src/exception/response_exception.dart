/*
 * Copyright (C) 2019 The Turms Project
 * https://github.com/turms-im/turms
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import 'package:fixnum/fixnum.dart';

import '../model/proto/notification/turms_notification.pb.dart';

class ResponseException implements Exception {
  final Int64? requestId;
  final int code;
  final String? reason;
  final Object? cause;
  final StackTrace? stackTrace;

  ResponseException(
      {this.requestId,
      required this.code,
      this.reason,
      this.cause,
      this.stackTrace});

  ResponseException.fromNotification(TurmsNotification notification)
      : this(
            requestId:
                notification.hasRequestId() ? notification.requestId : null,
            code: notification.code,
            reason: notification.hasReason() ? notification.reason : null);

  @override
  int get hashCode =>
      requestId.hashCode ^
      code.hashCode ^
      reason.hashCode ^
      cause.hashCode ^
      stackTrace.hashCode;

  @override
  bool operator ==(Object other) =>
      identical(this, other) ||
      other is ResponseException &&
          runtimeType == other.runtimeType &&
          requestId == other.requestId &&
          code == other.code &&
          reason == other.reason &&
          cause == other.cause &&
          stackTrace == other.stackTrace;

  @override
  String toString() => '$requestId:$code:$reason:$cause:$stackTrace';
}
