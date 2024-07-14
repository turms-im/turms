//
//  Generated code. Do not modify.
//  source: request/user/update_user_online_status_request.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:convert' as $convert;
import 'dart:core' as $core;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use updateUserOnlineStatusRequestDescriptor instead')
const UpdateUserOnlineStatusRequest$json = {
  '1': 'UpdateUserOnlineStatusRequest',
  '2': [
    {
      '1': 'device_types',
      '3': 1,
      '4': 3,
      '5': 14,
      '6': '.im.turms.proto.DeviceType',
      '10': 'deviceTypes'
    },
    {
      '1': 'user_status',
      '3': 2,
      '4': 1,
      '5': 14,
      '6': '.im.turms.proto.UserStatus',
      '10': 'userStatus'
    },
    {
      '1': 'custom_attributes',
      '3': 15,
      '4': 3,
      '5': 11,
      '6': '.im.turms.proto.Value',
      '10': 'customAttributes'
    },
  ],
};

/// Descriptor for `UpdateUserOnlineStatusRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List updateUserOnlineStatusRequestDescriptor = $convert.base64Decode(
    'Ch1VcGRhdGVVc2VyT25saW5lU3RhdHVzUmVxdWVzdBI9CgxkZXZpY2VfdHlwZXMYASADKA4yGi'
    '5pbS50dXJtcy5wcm90by5EZXZpY2VUeXBlUgtkZXZpY2VUeXBlcxI7Cgt1c2VyX3N0YXR1cxgC'
    'IAEoDjIaLmltLnR1cm1zLnByb3RvLlVzZXJTdGF0dXNSCnVzZXJTdGF0dXMSQgoRY3VzdG9tX2'
    'F0dHJpYnV0ZXMYDyADKAsyFS5pbS50dXJtcy5wcm90by5WYWx1ZVIQY3VzdG9tQXR0cmlidXRl'
    'cw==');
