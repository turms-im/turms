//
//  Generated code. Do not modify.
//  source: model/user/nearby_user.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:convert' as $convert;
import 'dart:core' as $core;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use nearbyUserDescriptor instead')
const NearbyUser$json = {
  '1': 'NearbyUser',
  '2': [
    {'1': 'user_id', '3': 1, '4': 1, '5': 3, '10': 'userId'},
    {
      '1': 'device_type',
      '3': 2,
      '4': 1,
      '5': 14,
      '6': '.im.turms.proto.DeviceType',
      '9': 0,
      '10': 'deviceType',
      '17': true
    },
    {
      '1': 'info',
      '3': 3,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.UserInfo',
      '9': 1,
      '10': 'info',
      '17': true
    },
    {
      '1': 'distance',
      '3': 4,
      '4': 1,
      '5': 5,
      '9': 2,
      '10': 'distance',
      '17': true
    },
    {
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
  '8': [
    {'1': '_device_type'},
    {'1': '_info'},
    {'1': '_distance'},
    {'1': '_location'},
  ],
};

/// Descriptor for `NearbyUser`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List nearbyUserDescriptor = $convert.base64Decode(
    'CgpOZWFyYnlVc2VyEhcKB3VzZXJfaWQYASABKANSBnVzZXJJZBJACgtkZXZpY2VfdHlwZRgCIA'
    'EoDjIaLmltLnR1cm1zLnByb3RvLkRldmljZVR5cGVIAFIKZGV2aWNlVHlwZYgBARIxCgRpbmZv'
    'GAMgASgLMhguaW0udHVybXMucHJvdG8uVXNlckluZm9IAVIEaW5mb4gBARIfCghkaXN0YW5jZR'
    'gEIAEoBUgCUghkaXN0YW5jZYgBARI9Cghsb2NhdGlvbhgFIAEoCzIcLmltLnR1cm1zLnByb3Rv'
    'LlVzZXJMb2NhdGlvbkgDUghsb2NhdGlvbogBAUIOCgxfZGV2aWNlX3R5cGVCBwoFX2luZm9CCw'
    'oJX2Rpc3RhbmNlQgsKCV9sb2NhdGlvbg==');
