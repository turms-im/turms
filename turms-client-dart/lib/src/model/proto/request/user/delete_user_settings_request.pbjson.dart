//
//  Generated code. Do not modify.
//  source: request/user/delete_user_settings_request.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:convert' as $convert;
import 'dart:core' as $core;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use deleteUserSettingsRequestDescriptor instead')
const DeleteUserSettingsRequest$json = {
  '1': 'DeleteUserSettingsRequest',
  '2': [
    {'1': 'names', '3': 1, '4': 3, '5': 9, '10': 'names'},
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

/// Descriptor for `DeleteUserSettingsRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List deleteUserSettingsRequestDescriptor = $convert.base64Decode(
    'ChlEZWxldGVVc2VyU2V0dGluZ3NSZXF1ZXN0EhQKBW5hbWVzGAEgAygJUgVuYW1lcxJCChFjdX'
    'N0b21fYXR0cmlidXRlcxgPIAMoCzIVLmltLnR1cm1zLnByb3RvLlZhbHVlUhBjdXN0b21BdHRy'
    'aWJ1dGVz');
