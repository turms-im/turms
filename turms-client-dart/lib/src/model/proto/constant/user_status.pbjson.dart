///
//  Generated code. Do not modify.
//  source: constant/user_status.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,constant_identifier_names,deprecated_member_use_from_same_package,directives_ordering,library_prefixes,non_constant_identifier_names,prefer_final_fields,return_of_invalid_type,unnecessary_const,unnecessary_import,unnecessary_this,unused_import,unused_shown_name

import 'dart:core' as $core;
import 'dart:convert' as $convert;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use userStatusDescriptor instead')
const UserStatus$json = const {
  '1': 'UserStatus',
  '2': const [
    const {'1': 'AVAILABLE', '2': 0},
    const {'1': 'OFFLINE', '2': 1},
    const {'1': 'INVISIBLE', '2': 2},
    const {'1': 'BUSY', '2': 3},
    const {'1': 'DO_NOT_DISTURB', '2': 4},
    const {'1': 'AWAY', '2': 5},
    const {'1': 'BE_RIGHT_BACK', '2': 6},
  ],
};

/// Descriptor for `UserStatus`. Decode as a `google.protobuf.EnumDescriptorProto`.
final $typed_data.Uint8List userStatusDescriptor = $convert.base64Decode(
    'CgpVc2VyU3RhdHVzEg0KCUFWQUlMQUJMRRAAEgsKB09GRkxJTkUQARINCglJTlZJU0lCTEUQAhIICgRCVVNZEAMSEgoORE9fTk9UX0RJU1RVUkIQBBIICgRBV0FZEAUSEQoNQkVfUklHSFRfQkFDSxAG');
