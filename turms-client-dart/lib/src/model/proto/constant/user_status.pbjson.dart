//
//  Generated code. Do not modify.
//  source: constant/user_status.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:convert' as $convert;
import 'dart:core' as $core;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use userStatusDescriptor instead')
const UserStatus$json = {
  '1': 'UserStatus',
  '2': [
    {'1': 'AVAILABLE', '2': 0},
    {'1': 'OFFLINE', '2': 1},
    {'1': 'INVISIBLE', '2': 2},
    {'1': 'BUSY', '2': 3},
    {'1': 'DO_NOT_DISTURB', '2': 4},
    {'1': 'AWAY', '2': 5},
    {'1': 'BE_RIGHT_BACK', '2': 6},
  ],
};

/// Descriptor for `UserStatus`. Decode as a `google.protobuf.EnumDescriptorProto`.
final $typed_data.Uint8List userStatusDescriptor = $convert.base64Decode(
    'CgpVc2VyU3RhdHVzEg0KCUFWQUlMQUJMRRAAEgsKB09GRkxJTkUQARINCglJTlZJU0lCTEUQAh'
    'IICgRCVVNZEAMSEgoORE9fTk9UX0RJU1RVUkIQBBIICgRBV0FZEAUSEQoNQkVfUklHSFRfQkFD'
    'SxAG');
