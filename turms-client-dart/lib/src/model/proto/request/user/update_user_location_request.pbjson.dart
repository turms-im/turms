//
//  Generated code. Do not modify.
//  source: request/user/update_user_location_request.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:convert' as $convert;
import 'dart:core' as $core;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use updateUserLocationRequestDescriptor instead')
const UpdateUserLocationRequest$json = {
  '1': 'UpdateUserLocationRequest',
  '2': [
    {'1': 'latitude', '3': 1, '4': 1, '5': 2, '10': 'latitude'},
    {'1': 'longitude', '3': 2, '4': 1, '5': 2, '10': 'longitude'},
    {
      '1': 'details',
      '3': 3,
      '4': 3,
      '5': 11,
      '6': '.im.turms.proto.UpdateUserLocationRequest.DetailsEntry',
      '10': 'details'
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
  '3': [UpdateUserLocationRequest_DetailsEntry$json],
};

@$core.Deprecated('Use updateUserLocationRequestDescriptor instead')
const UpdateUserLocationRequest_DetailsEntry$json = {
  '1': 'DetailsEntry',
  '2': [
    {'1': 'key', '3': 1, '4': 1, '5': 9, '10': 'key'},
    {'1': 'value', '3': 2, '4': 1, '5': 9, '10': 'value'},
  ],
  '7': {'7': true},
};

/// Descriptor for `UpdateUserLocationRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List updateUserLocationRequestDescriptor = $convert.base64Decode(
    'ChlVcGRhdGVVc2VyTG9jYXRpb25SZXF1ZXN0EhoKCGxhdGl0dWRlGAEgASgCUghsYXRpdHVkZR'
    'IcCglsb25naXR1ZGUYAiABKAJSCWxvbmdpdHVkZRJQCgdkZXRhaWxzGAMgAygLMjYuaW0udHVy'
    'bXMucHJvdG8uVXBkYXRlVXNlckxvY2F0aW9uUmVxdWVzdC5EZXRhaWxzRW50cnlSB2RldGFpbH'
    'MSQgoRY3VzdG9tX2F0dHJpYnV0ZXMYDyADKAsyFS5pbS50dXJtcy5wcm90by5WYWx1ZVIQY3Vz'
    'dG9tQXR0cmlidXRlcxo6CgxEZXRhaWxzRW50cnkSEAoDa2V5GAEgASgJUgNrZXkSFAoFdmFsdW'
    'UYAiABKAlSBXZhbHVlOgI4AQ==');
