///
//  Generated code. Do not modify.
//  source: request/user/update_user_location_request.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,constant_identifier_names,deprecated_member_use_from_same_package,directives_ordering,library_prefixes,non_constant_identifier_names,prefer_final_fields,return_of_invalid_type,unnecessary_const,unnecessary_import,unnecessary_this,unused_import,unused_shown_name

import 'dart:core' as $core;
import 'dart:convert' as $convert;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use updateUserLocationRequestDescriptor instead')
const UpdateUserLocationRequest$json = const {
  '1': 'UpdateUserLocationRequest',
  '2': const [
    const {'1': 'latitude', '3': 1, '4': 1, '5': 2, '10': 'latitude'},
    const {'1': 'longitude', '3': 2, '4': 1, '5': 2, '10': 'longitude'},
    const {
      '1': 'details',
      '3': 3,
      '4': 3,
      '5': 11,
      '6': '.im.turms.proto.UpdateUserLocationRequest.DetailsEntry',
      '10': 'details'
    },
  ],
  '3': const [UpdateUserLocationRequest_DetailsEntry$json],
};

@$core.Deprecated('Use updateUserLocationRequestDescriptor instead')
const UpdateUserLocationRequest_DetailsEntry$json = const {
  '1': 'DetailsEntry',
  '2': const [
    const {'1': 'key', '3': 1, '4': 1, '5': 9, '10': 'key'},
    const {'1': 'value', '3': 2, '4': 1, '5': 9, '10': 'value'},
  ],
  '7': const {'7': true},
};

/// Descriptor for `UpdateUserLocationRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List updateUserLocationRequestDescriptor =
    $convert.base64Decode(
        'ChlVcGRhdGVVc2VyTG9jYXRpb25SZXF1ZXN0EhoKCGxhdGl0dWRlGAEgASgCUghsYXRpdHVkZRIcCglsb25naXR1ZGUYAiABKAJSCWxvbmdpdHVkZRJQCgdkZXRhaWxzGAMgAygLMjYuaW0udHVybXMucHJvdG8uVXBkYXRlVXNlckxvY2F0aW9uUmVxdWVzdC5EZXRhaWxzRW50cnlSB2RldGFpbHMaOgoMRGV0YWlsc0VudHJ5EhAKA2tleRgBIAEoCVIDa2V5EhQKBXZhbHVlGAIgASgJUgV2YWx1ZToCOAE=');
