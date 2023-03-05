///
//  Generated code. Do not modify.
//  source: request/user/update_user_request.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,constant_identifier_names,deprecated_member_use_from_same_package,directives_ordering,library_prefixes,non_constant_identifier_names,prefer_final_fields,return_of_invalid_type,unnecessary_const,unnecessary_import,unnecessary_this,unused_import,unused_shown_name

import 'dart:core' as $core;
import 'dart:convert' as $convert;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use updateUserRequestDescriptor instead')
const UpdateUserRequest$json = const {
  '1': 'UpdateUserRequest',
  '2': const [
    const {
      '1': 'password',
      '3': 1,
      '4': 1,
      '5': 9,
      '9': 0,
      '10': 'password',
      '17': true
    },
    const {
      '1': 'name',
      '3': 2,
      '4': 1,
      '5': 9,
      '9': 1,
      '10': 'name',
      '17': true
    },
    const {
      '1': 'intro',
      '3': 3,
      '4': 1,
      '5': 9,
      '9': 2,
      '10': 'intro',
      '17': true
    },
    const {
      '1': 'profile_picture',
      '3': 4,
      '4': 1,
      '5': 9,
      '9': 3,
      '10': 'profilePicture',
      '17': true
    },
    const {
      '1': 'profile_access_strategy',
      '3': 5,
      '4': 1,
      '5': 14,
      '6': '.im.turms.proto.ProfileAccessStrategy',
      '9': 4,
      '10': 'profileAccessStrategy',
      '17': true
    },
  ],
  '8': const [
    const {'1': '_password'},
    const {'1': '_name'},
    const {'1': '_intro'},
    const {'1': '_profile_picture'},
    const {'1': '_profile_access_strategy'},
  ],
};

/// Descriptor for `UpdateUserRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List updateUserRequestDescriptor = $convert.base64Decode(
    'ChFVcGRhdGVVc2VyUmVxdWVzdBIfCghwYXNzd29yZBgBIAEoCUgAUghwYXNzd29yZIgBARIXCgRuYW1lGAIgASgJSAFSBG5hbWWIAQESGQoFaW50cm8YAyABKAlIAlIFaW50cm+IAQESLAoPcHJvZmlsZV9waWN0dXJlGAQgASgJSANSDnByb2ZpbGVQaWN0dXJliAEBEmIKF3Byb2ZpbGVfYWNjZXNzX3N0cmF0ZWd5GAUgASgOMiUuaW0udHVybXMucHJvdG8uUHJvZmlsZUFjY2Vzc1N0cmF0ZWd5SARSFXByb2ZpbGVBY2Nlc3NTdHJhdGVneYgBAUILCglfcGFzc3dvcmRCBwoFX25hbWVCCAoGX2ludHJvQhIKEF9wcm9maWxlX3BpY3R1cmVCGgoYX3Byb2ZpbGVfYWNjZXNzX3N0cmF0ZWd5');
