//
//  Generated code. Do not modify.
//  source: model/user/user_settings.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:convert' as $convert;
import 'dart:core' as $core;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use userSettingsDescriptor instead')
const UserSettings$json = {
  '1': 'UserSettings',
  '2': [
    {
      '1': 'settings',
      '3': 1,
      '4': 3,
      '5': 11,
      '6': '.im.turms.proto.UserSettings.SettingsEntry',
      '10': 'settings'
    },
    {
      '1': 'last_updated_date',
      '3': 2,
      '4': 1,
      '5': 3,
      '9': 0,
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
  '3': [UserSettings_SettingsEntry$json],
  '8': [
    {'1': '_last_updated_date'},
  ],
};

@$core.Deprecated('Use userSettingsDescriptor instead')
const UserSettings_SettingsEntry$json = {
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

/// Descriptor for `UserSettings`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List userSettingsDescriptor = $convert.base64Decode(
    'CgxVc2VyU2V0dGluZ3MSRgoIc2V0dGluZ3MYASADKAsyKi5pbS50dXJtcy5wcm90by5Vc2VyU2'
    'V0dGluZ3MuU2V0dGluZ3NFbnRyeVIIc2V0dGluZ3MSLwoRbGFzdF91cGRhdGVkX2RhdGUYAiAB'
    'KANIAFIPbGFzdFVwZGF0ZWREYXRliAEBEkIKEWN1c3RvbV9hdHRyaWJ1dGVzGA8gAygLMhUuaW'
    '0udHVybXMucHJvdG8uVmFsdWVSEGN1c3RvbUF0dHJpYnV0ZXMaUgoNU2V0dGluZ3NFbnRyeRIQ'
    'CgNrZXkYASABKAlSA2tleRIrCgV2YWx1ZRgCIAEoCzIVLmltLnR1cm1zLnByb3RvLlZhbHVlUg'
    'V2YWx1ZToCOAFCFAoSX2xhc3RfdXBkYXRlZF9kYXRl');
