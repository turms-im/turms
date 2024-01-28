//
//  Generated code. Do not modify.
//  source: model/user/user_location.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:convert' as $convert;
import 'dart:core' as $core;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use userLocationDescriptor instead')
const UserLocation$json = {
  '1': 'UserLocation',
  '2': [
    {'1': 'latitude', '3': 1, '4': 1, '5': 2, '10': 'latitude'},
    {'1': 'longitude', '3': 2, '4': 1, '5': 2, '10': 'longitude'},
    {
      '1': 'timestamp',
      '3': 3,
      '4': 1,
      '5': 3,
      '9': 0,
      '10': 'timestamp',
      '17': true
    },
    {
      '1': 'details',
      '3': 4,
      '4': 3,
      '5': 11,
      '6': '.im.turms.proto.UserLocation.DetailsEntry',
      '10': 'details'
    },
  ],
  '3': [UserLocation_DetailsEntry$json],
  '8': [
    {'1': '_timestamp'},
  ],
};

@$core.Deprecated('Use userLocationDescriptor instead')
const UserLocation_DetailsEntry$json = {
  '1': 'DetailsEntry',
  '2': [
    {'1': 'key', '3': 1, '4': 1, '5': 9, '10': 'key'},
    {'1': 'value', '3': 2, '4': 1, '5': 9, '10': 'value'},
  ],
  '7': {'7': true},
};

/// Descriptor for `UserLocation`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List userLocationDescriptor = $convert.base64Decode(
    'CgxVc2VyTG9jYXRpb24SGgoIbGF0aXR1ZGUYASABKAJSCGxhdGl0dWRlEhwKCWxvbmdpdHVkZR'
    'gCIAEoAlIJbG9uZ2l0dWRlEiEKCXRpbWVzdGFtcBgDIAEoA0gAUgl0aW1lc3RhbXCIAQESQwoH'
    'ZGV0YWlscxgEIAMoCzIpLmltLnR1cm1zLnByb3RvLlVzZXJMb2NhdGlvbi5EZXRhaWxzRW50cn'
    'lSB2RldGFpbHMaOgoMRGV0YWlsc0VudHJ5EhAKA2tleRgBIAEoCVIDa2V5EhQKBXZhbHVlGAIg'
    'ASgJUgV2YWx1ZToCOAFCDAoKX3RpbWVzdGFtcA==');
