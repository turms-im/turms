///
//  Generated code. Do not modify.
//  source: constant/profile_access_strategy.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,constant_identifier_names,deprecated_member_use_from_same_package,directives_ordering,library_prefixes,non_constant_identifier_names,prefer_final_fields,return_of_invalid_type,unnecessary_const,unnecessary_import,unnecessary_this,unused_import,unused_shown_name

import 'dart:core' as $core;
import 'dart:convert' as $convert;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use profileAccessStrategyDescriptor instead')
const ProfileAccessStrategy$json = const {
  '1': 'ProfileAccessStrategy',
  '2': const [
    const {'1': 'ALL', '2': 0},
    const {'1': 'ALL_EXCEPT_BLOCKED_USERS', '2': 1},
    const {'1': 'FRIENDS', '2': 2},
  ],
};

/// Descriptor for `ProfileAccessStrategy`. Decode as a `google.protobuf.EnumDescriptorProto`.
final $typed_data.Uint8List profileAccessStrategyDescriptor = $convert.base64Decode(
    'ChVQcm9maWxlQWNjZXNzU3RyYXRlZ3kSBwoDQUxMEAASHAoYQUxMX0VYQ0VQVF9CTE9DS0VEX1VTRVJTEAESCwoHRlJJRU5EUxAC');
