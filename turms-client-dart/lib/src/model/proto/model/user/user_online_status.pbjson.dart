///
//  Generated code. Do not modify.
//  source: model/user/user_online_status.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,constant_identifier_names,deprecated_member_use_from_same_package,directives_ordering,library_prefixes,non_constant_identifier_names,prefer_final_fields,return_of_invalid_type,unnecessary_const,unnecessary_import,unnecessary_this,unused_import,unused_shown_name

import 'dart:core' as $core;
import 'dart:convert' as $convert;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use userOnlineStatusDescriptor instead')
const UserOnlineStatus$json = const {
  '1': 'UserOnlineStatus',
  '2': const [
    const {'1': 'user_id', '3': 1, '4': 1, '5': 3, '10': 'userId'},
    const {
      '1': 'user_status',
      '3': 2,
      '4': 1,
      '5': 14,
      '6': '.im.turms.proto.UserStatus',
      '10': 'userStatus'
    },
    const {
      '1': 'using_device_types',
      '3': 3,
      '4': 3,
      '5': 14,
      '6': '.im.turms.proto.DeviceType',
      '10': 'usingDeviceTypes'
    },
  ],
};

/// Descriptor for `UserOnlineStatus`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List userOnlineStatusDescriptor = $convert.base64Decode(
    'ChBVc2VyT25saW5lU3RhdHVzEhcKB3VzZXJfaWQYASABKANSBnVzZXJJZBI7Cgt1c2VyX3N0YXR1cxgCIAEoDjIaLmltLnR1cm1zLnByb3RvLlVzZXJTdGF0dXNSCnVzZXJTdGF0dXMSSAoSdXNpbmdfZGV2aWNlX3R5cGVzGAMgAygOMhouaW0udHVybXMucHJvdG8uRGV2aWNlVHlwZVIQdXNpbmdEZXZpY2VUeXBlcw==');
