//
//  Generated code. Do not modify.
//  source: request/user/update_user_settings_request.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:convert' as $convert;
import 'dart:core' as $core;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use updateUserSettingsRequestDescriptor instead')
const UpdateUserSettingsRequest$json = {
  '1': 'UpdateUserSettingsRequest',
  '2': [
    {
      '1': 'settings',
      '3': 1,
      '4': 3,
      '5': 11,
      '6': '.im.turms.proto.UpdateUserSettingsRequest.SettingsEntry',
      '10': 'settings'
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
  '3': [UpdateUserSettingsRequest_SettingsEntry$json],
};

@$core.Deprecated('Use updateUserSettingsRequestDescriptor instead')
const UpdateUserSettingsRequest_SettingsEntry$json = {
  '1': 'SettingsEntry',
  '2': [
    {'1': 'key', '3': 1, '4': 1, '5': 9, '10': 'key'},
    {
      '1': 'value',
      '3': 2,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.Value',
      '10': 'value'
    },
  ],
  '7': {'7': true},
};

/// Descriptor for `UpdateUserSettingsRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List updateUserSettingsRequestDescriptor = $convert.base64Decode(
    'ChlVcGRhdGVVc2VyU2V0dGluZ3NSZXF1ZXN0ElMKCHNldHRpbmdzGAEgAygLMjcuaW0udHVybX'
    'MucHJvdG8uVXBkYXRlVXNlclNldHRpbmdzUmVxdWVzdC5TZXR0aW5nc0VudHJ5UghzZXR0aW5n'
    'cxJCChFjdXN0b21fYXR0cmlidXRlcxgPIAMoCzIVLmltLnR1cm1zLnByb3RvLlZhbHVlUhBjdX'
    'N0b21BdHRyaWJ1dGVzGlIKDVNldHRpbmdzRW50cnkSEAoDa2V5GAEgASgJUgNrZXkSKwoFdmFs'
    'dWUYAiABKAsyFS5pbS50dXJtcy5wcm90by5WYWx1ZVIFdmFsdWU6AjgB');
