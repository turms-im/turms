///
//  Generated code. Do not modify.
//  source: model/user/user_info.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,unnecessary_const,non_constant_identifier_names,library_prefixes,unused_import,unused_shown_name,return_of_invalid_type,unnecessary_this,prefer_final_fields,deprecated_member_use_from_same_package

import 'dart:core' as $core;
import 'dart:convert' as $convert;
import 'dart:typed_data' as $typed_data;
@$core.Deprecated('Use userInfoDescriptor instead')
const UserInfo$json = const {
  '1': 'UserInfo',
  '2': const [
    const {'1': 'id', '3': 1, '4': 1, '5': 3, '9': 0, '10': 'id', '17': true},
    const {'1': 'name', '3': 2, '4': 1, '5': 9, '9': 1, '10': 'name', '17': true},
    const {'1': 'intro', '3': 3, '4': 1, '5': 9, '9': 2, '10': 'intro', '17': true},
    const {'1': 'registration_date', '3': 4, '4': 1, '5': 3, '9': 3, '10': 'registrationDate', '17': true},
    const {'1': 'active', '3': 5, '4': 1, '5': 8, '9': 4, '10': 'active', '17': true},
    const {'1': 'profile_access_strategy', '3': 6, '4': 1, '5': 14, '6': '.im.turms.proto.ProfileAccessStrategy', '9': 5, '10': 'profileAccessStrategy', '17': true},
  ],
  '8': const [
    const {'1': '_id'},
    const {'1': '_name'},
    const {'1': '_intro'},
    const {'1': '_registration_date'},
    const {'1': '_active'},
    const {'1': '_profile_access_strategy'},
  ],
};

/// Descriptor for `UserInfo`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List userInfoDescriptor = $convert.base64Decode('CghVc2VySW5mbxITCgJpZBgBIAEoA0gAUgJpZIgBARIXCgRuYW1lGAIgASgJSAFSBG5hbWWIAQESGQoFaW50cm8YAyABKAlIAlIFaW50cm+IAQESMAoRcmVnaXN0cmF0aW9uX2RhdGUYBCABKANIA1IQcmVnaXN0cmF0aW9uRGF0ZYgBARIbCgZhY3RpdmUYBSABKAhIBFIGYWN0aXZliAEBEmIKF3Byb2ZpbGVfYWNjZXNzX3N0cmF0ZWd5GAYgASgOMiUuaW0udHVybXMucHJvdG8uUHJvZmlsZUFjY2Vzc1N0cmF0ZWd5SAVSFXByb2ZpbGVBY2Nlc3NTdHJhdGVneYgBAUIFCgNfaWRCBwoFX25hbWVCCAoGX2ludHJvQhQKEl9yZWdpc3RyYXRpb25fZGF0ZUIJCgdfYWN0aXZlQhoKGF9wcm9maWxlX2FjY2Vzc19zdHJhdGVneQ==');
