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

  ResponseException(this.requestId, this.code, this.reason);

  ResponseException.fromCode(int code) : this(null, code, null);

  ResponseException.fromCodeAndReason(int code, String? reason)
      : this(null, code, reason);

  ResponseException.fromNotification(TurmsNotification notification)
      : this(
            notification.hasRequestId() ? notification.requestId : null,
            notification.code,
            notification.hasReason() ? notification.reason : null);

  @override
  String toString() => '$requestId:$code:$reason';
}
