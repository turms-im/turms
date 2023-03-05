///
//  Generated code. Do not modify.
//  source: request/user/update_user_online_status_request.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,constant_identifier_names,deprecated_member_use_from_same_package,directives_ordering,library_prefixes,non_constant_identifier_names,prefer_final_fields,return_of_invalid_type,unnecessary_const,unnecessary_import,unnecessary_this,unused_import,unused_shown_name

import 'dart:core' as $core;
import 'dart:convert' as $convert;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use updateUserOnlineStatusRequestDescriptor instead')
const UpdateUserOnlineStatusRequest$json = const {
  '1': 'UpdateUserOnlineStatusRequest',
  '2': const [
    const {
      '1': 'device_types',
      '3': 1,
      '4': 3,
      '5': 14,
      '6': '.im.turms.proto.DeviceType',
      '10': 'deviceTypes'
    },
    const {
      '1': 'user_status',
      '3': 2,
      '4': 1,
      '5': 14,
      '6': '.im.turms.proto.UserStatus',
      '10': 'userStatus'
    },
  ],
};

/// Descriptor for `UpdateUserOnlineStatusRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List updateUserOnlineStatusRequestDescriptor =
    $convert.base64Decode(
        'Ch1VcGRhdGVVc2VyT25saW5lU3RhdHVzUmVxdWVzdBI9CgxkZXZpY2VfdHlwZXMYASADKA4yGi5pbS50dXJtcy5wcm90by5EZXZpY2VUeXBlUgtkZXZpY2VUeXBlcxI7Cgt1c2VyX3N0YXR1cxgCIAEoDjIaLmltLnR1cm1zLnByb3RvLlVzZXJTdGF0dXNSCnVzZXJTdGF0dXM=');
