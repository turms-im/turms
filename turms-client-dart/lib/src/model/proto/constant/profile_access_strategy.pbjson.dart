//
//  Generated code. Do not modify.
//  source: constant/profile_access_strategy.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:convert' as $convert;
import 'dart:core' as $core;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use profileAccessStrategyDescriptor instead')
const ProfileAccessStrategy$json = {
  '1': 'ProfileAccessStrategy',
  '2': [
    {'1': 'ALL', '2': 0},
    {'1': 'ALL_EXCEPT_BLOCKED_USERS', '2': 1},
    {'1': 'FRIENDS', '2': 2},
  ],
};

/// Descriptor for `ProfileAccessStrategy`. Decode as a `google.protobuf.EnumDescriptorProto`.
final $typed_data.Uint8List profileAccessStrategyDescriptor = $convert.base64Decode(
    'ChVQcm9maWxlQWNjZXNzU3RyYXRlZ3kSBwoDQUxMEAASHAoYQUxMX0VYQ0VQVF9CTE9DS0VEX1'
    'VTRVJTEAESCwoHRlJJRU5EUxAC');
