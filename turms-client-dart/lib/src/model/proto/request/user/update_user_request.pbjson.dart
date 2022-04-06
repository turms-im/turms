///
//  Generated code. Do not modify.
//  source: request/user/update_user_request.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,unnecessary_const,non_constant_identifier_names,library_prefixes,unused_import,unused_shown_name,return_of_invalid_type,unnecessary_this,prefer_final_fields,deprecated_member_use_from_same_package

import 'dart:core' as $core;
import 'dart:convert' as $convert;
import 'dart:typed_data' as $typed_data;
@$core.Deprecated('Use updateUserRequestDescriptor instead')
const UpdateUserRequest$json = const {
  '1': 'UpdateUserRequest',
  '2': const [
    const {'1': 'password', '3': 1, '4': 1, '5': 9, '9': 0, '10': 'password', '17': true},
    const {'1': 'name', '3': 2, '4': 1, '5': 9, '9': 1, '10': 'name', '17': true},
    const {'1': 'intro', '3': 3, '4': 1, '5': 9, '9': 2, '10': 'intro', '17': true},
    const {'1': 'profile_access_strategy', '3': 4, '4': 1, '5': 14, '6': '.im.turms.proto.ProfileAccessStrategy', '9': 3, '10': 'profileAccessStrategy', '17': true},
  ],
  '8': const [
    const {'1': '_password'},
    const {'1': '_name'},
    const {'1': '_intro'},
    const {'1': '_profile_access_strategy'},
  ],
};

/// Descriptor for `UpdateUserRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List updateUserRequestDescriptor = $convert.base64Decode('ChFVcGRhdGVVc2VyUmVxdWVzdBIfCghwYXNzd29yZBgBIAEoCUgAUghwYXNzd29yZIgBARIXCgRuYW1lGAIgASgJSAFSBG5hbWWIAQESGQoFaW50cm8YAyABKAlIAlIFaW50cm+IAQESYgoXcHJvZmlsZV9hY2Nlc3Nfc3RyYXRlZ3kYBCABKA4yJS5pbS50dXJtcy5wcm90by5Qcm9maWxlQWNjZXNzU3RyYXRlZ3lIA1IVcHJvZmlsZUFjY2Vzc1N0cmF0ZWd5iAEBQgsKCV9wYXNzd29yZEIHCgVfbmFtZUIICgZfaW50cm9CGgoYX3Byb2ZpbGVfYWNjZXNzX3N0cmF0ZWd5');
