//
//  Generated code. Do not modify.
//  source: request/conversation/update_conversation_settings_request.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:convert' as $convert;
import 'dart:core' as $core;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use updateConversationSettingsRequestDescriptor instead')
const UpdateConversationSettingsRequest$json = {
  '1': 'UpdateConversationSettingsRequest',
  '2': [
    {
      '1': 'user_id',
      '3': 1,
      '4': 1,
      '5': 3,
      '9': 0,
      '10': 'userId',
      '17': true
    },
    {
      '1': 'group_id',
      '3': 2,
      '4': 1,
      '5': 3,
      '9': 1,
      '10': 'groupId',
      '17': true
    },
    {
      '1': 'settings',
      '3': 3,
      '4': 3,
      '5': 11,
      '6': '.im.turms.proto.UpdateConversationSettingsRequest.SettingsEntry',
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
  '3': [UpdateConversationSettingsRequest_SettingsEntry$json],
  '8': [
    {'1': '_user_id'},
    {'1': '_group_id'},
  ],
};

@$core.Deprecated('Use updateConversationSettingsRequestDescriptor instead')
const UpdateConversationSettingsRequest_SettingsEntry$json = {
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

/// Descriptor for `UpdateConversationSettingsRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List updateConversationSettingsRequestDescriptor = $convert.base64Decode(
    'CiFVcGRhdGVDb252ZXJzYXRpb25TZXR0aW5nc1JlcXVlc3QSHAoHdXNlcl9pZBgBIAEoA0gAUg'
    'Z1c2VySWSIAQESHgoIZ3JvdXBfaWQYAiABKANIAVIHZ3JvdXBJZIgBARJbCghzZXR0aW5ncxgD'
    'IAMoCzI/LmltLnR1cm1zLnByb3RvLlVwZGF0ZUNvbnZlcnNhdGlvblNldHRpbmdzUmVxdWVzdC'
    '5TZXR0aW5nc0VudHJ5UghzZXR0aW5ncxJCChFjdXN0b21fYXR0cmlidXRlcxgPIAMoCzIVLmlt'
    'LnR1cm1zLnByb3RvLlZhbHVlUhBjdXN0b21BdHRyaWJ1dGVzGlIKDVNldHRpbmdzRW50cnkSEA'
    'oDa2V5GAEgASgJUgNrZXkSKwoFdmFsdWUYAiABKAsyFS5pbS50dXJtcy5wcm90by5WYWx1ZVIF'
    'dmFsdWU6AjgBQgoKCF91c2VyX2lkQgsKCV9ncm91cF9pZA==');
