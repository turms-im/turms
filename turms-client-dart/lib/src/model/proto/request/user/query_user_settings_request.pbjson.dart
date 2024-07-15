//
//  Generated code. Do not modify.
//  source: request/user/query_user_settings_request.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:convert' as $convert;
import 'dart:core' as $core;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use queryUserSettingsRequestDescriptor instead')
const QueryUserSettingsRequest$json = {
  '1': 'QueryUserSettingsRequest',
  '2': [
    {'1': 'names', '3': 1, '4': 3, '5': 9, '10': 'names'},
    {
      '1': 'last_updated_date_start',
      '3': 2,
      '4': 1,
      '5': 3,
      '9': 0,
      '10': 'lastUpdatedDateStart',
      '17': true
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
  '8': [
    {'1': '_last_updated_date_start'},
  ],
};

/// Descriptor for `QueryUserSettingsRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List queryUserSettingsRequestDescriptor = $convert.base64Decode(
    'ChhRdWVyeVVzZXJTZXR0aW5nc1JlcXVlc3QSFAoFbmFtZXMYASADKAlSBW5hbWVzEjoKF2xhc3'
    'RfdXBkYXRlZF9kYXRlX3N0YXJ0GAIgASgDSABSFGxhc3RVcGRhdGVkRGF0ZVN0YXJ0iAEBEkIK'
    'EWN1c3RvbV9hdHRyaWJ1dGVzGA8gAygLMhUuaW0udHVybXMucHJvdG8uVmFsdWVSEGN1c3RvbU'
    'F0dHJpYnV0ZXNCGgoYX2xhc3RfdXBkYXRlZF9kYXRlX3N0YXJ0');
