///
//  Generated code. Do not modify.
//  source: request/user/update_user_online_status_request.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,unnecessary_const,non_constant_identifier_names,library_prefixes,unused_import,unused_shown_name,return_of_invalid_type,unnecessary_this,prefer_final_fields,deprecated_member_use_from_same_package

import 'dart:core' as $core;
import 'dart:convert' as $convert;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use updateUserOnlineStatusRequestDescriptor instead')
const UpdateUserOnlineStatusRequest$json = const {
  '1': 'UpdateUserOnlineStatusRequest',
  '2': const [
    const {
      '1': 'user_status',
      '3': 1,
      '4': 1,
      '5': 14,
      '6': '.im.turms.proto.UserStatus',
      '10': 'userStatus'
    },
    const {
      '1': 'device_types',
      '3': 2,
      '4': 3,
      '5': 14,
      '6': '.im.turms.proto.DeviceType',
      '10': 'deviceTypes'
    },
  ],
};

/// Descriptor for `UpdateUserOnlineStatusRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List updateUserOnlineStatusRequestDescriptor =
    $convert.base64Decode(
        'Ch1VcGRhdGVVc2VyT25saW5lU3RhdHVzUmVxdWVzdBI7Cgt1c2VyX3N0YXR1cxgBIAEoDjIaLmltLnR1cm1zLnByb3RvLlVzZXJTdGF0dXNSCnVzZXJTdGF0dXMSPQoMZGV2aWNlX3R5cGVzGAIgAygOMhouaW0udHVybXMucHJvdG8uRGV2aWNlVHlwZVILZGV2aWNlVHlwZXM=');
