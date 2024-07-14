//
//  Generated code. Do not modify.
//  source: model/conversation/conversation_settings.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:convert' as $convert;
import 'dart:core' as $core;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use conversationSettingsDescriptor instead')
const ConversationSettings$json = {
  '1': 'ConversationSettings',
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
      '6': '.im.turms.proto.ConversationSettings.SettingsEntry',
      '10': 'settings'
    },
    {
      '1': 'last_updated_date',
      '3': 4,
      '4': 1,
      '5': 3,
      '9': 2,
      '10': 'lastUpdatedDate',
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
  '3': [ConversationSettings_SettingsEntry$json],
  '8': [
    {'1': '_user_id'},
    {'1': '_group_id'},
    {'1': '_last_updated_date'},
  ],
};

@$core.Deprecated('Use conversationSettingsDescriptor instead')
const ConversationSettings_SettingsEntry$json = {
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

/// Descriptor for `ConversationSettings`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List conversationSettingsDescriptor = $convert.base64Decode(
    'ChRDb252ZXJzYXRpb25TZXR0aW5ncxIcCgd1c2VyX2lkGAEgASgDSABSBnVzZXJJZIgBARIeCg'
    'hncm91cF9pZBgCIAEoA0gBUgdncm91cElkiAEBEk4KCHNldHRpbmdzGAMgAygLMjIuaW0udHVy'
    'bXMucHJvdG8uQ29udmVyc2F0aW9uU2V0dGluZ3MuU2V0dGluZ3NFbnRyeVIIc2V0dGluZ3MSLw'
    'oRbGFzdF91cGRhdGVkX2RhdGUYBCABKANIAlIPbGFzdFVwZGF0ZWREYXRliAEBEkIKEWN1c3Rv'
    'bV9hdHRyaWJ1dGVzGA8gAygLMhUuaW0udHVybXMucHJvdG8uVmFsdWVSEGN1c3RvbUF0dHJpYn'
    'V0ZXMaUgoNU2V0dGluZ3NFbnRyeRIQCgNrZXkYASABKAlSA2tleRIrCgV2YWx1ZRgCIAEoCzIV'
    'LmltLnR1cm1zLnByb3RvLlZhbHVlUgV2YWx1ZToCOAFCCgoIX3VzZXJfaWRCCwoJX2dyb3VwX2'
    'lkQhQKEl9sYXN0X3VwZGF0ZWRfZGF0ZQ==');
