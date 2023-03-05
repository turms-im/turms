///
//  Generated code. Do not modify.
//  source: model/user/nearby_user.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,constant_identifier_names,deprecated_member_use_from_same_package,directives_ordering,library_prefixes,non_constant_identifier_names,prefer_final_fields,return_of_invalid_type,unnecessary_const,unnecessary_import,unnecessary_this,unused_import,unused_shown_name

import 'dart:core' as $core;
import 'dart:convert' as $convert;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use nearbyUserDescriptor instead')
const NearbyUser$json = const {
  '1': 'NearbyUser',
  '2': const [
    const {'1': 'user_id', '3': 1, '4': 1, '5': 3, '10': 'userId'},
    const {
      '1': 'device_type',
      '3': 2,
      '4': 1,
      '5': 14,
      '6': '.im.turms.proto.DeviceType',
      '9': 0,
      '10': 'deviceType',
      '17': true
    },
    const {
      '1': 'info',
      '3': 3,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.UserInfo',
      '9': 1,
      '10': 'info',
      '17': true
    },
    const {
      '1': 'distance',
      '3': 4,
      '4': 1,
      '5': 5,
      '9': 2,
      '10': 'distance',
      '17': true
    },
    const {
      '1': 'location',
      '3': 5,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.UserLocation',
      '9': 3,
      '10': 'location',
      '17': true
    },
  ],
  '8': const [
    const {'1': '_device_type'},
    const {'1': '_info'},
    const {'1': '_distance'},
    const {'1': '_location'},
  ],
};

/// Descriptor for `NearbyUser`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List nearbyUserDescriptor = $convert.base64Decode(
    'CgpOZWFyYnlVc2VyEhcKB3VzZXJfaWQYASABKANSBnVzZXJJZBJACgtkZXZpY2VfdHlwZRgCIAEoDjIaLmltLnR1cm1zLnByb3RvLkRldmljZVR5cGVIAFIKZGV2aWNlVHlwZYgBARIxCgRpbmZvGAMgASgLMhguaW0udHVybXMucHJvdG8uVXNlckluZm9IAVIEaW5mb4gBARIfCghkaXN0YW5jZRgEIAEoBUgCUghkaXN0YW5jZYgBARI9Cghsb2NhdGlvbhgFIAEoCzIcLmltLnR1cm1zLnByb3RvLlVzZXJMb2NhdGlvbkgDUghsb2NhdGlvbogBAUIOCgxfZGV2aWNlX3R5cGVCBwoFX2luZm9CCwoJX2Rpc3RhbmNlQgsKCV9sb2NhdGlvbg==');
