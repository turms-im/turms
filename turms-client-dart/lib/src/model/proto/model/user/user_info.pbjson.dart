//
//  Generated code. Do not modify.
//  source: model/user/user_info.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:convert' as $convert;
import 'dart:core' as $core;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use userInfoDescriptor instead')
const UserInfo$json = {
  '1': 'UserInfo',
  '2': [
    {'1': 'id', '3': 1, '4': 1, '5': 3, '9': 0, '10': 'id', '17': true},
    {'1': 'name', '3': 2, '4': 1, '5': 9, '9': 1, '10': 'name', '17': true},
    {'1': 'intro', '3': 3, '4': 1, '5': 9, '9': 2, '10': 'intro', '17': true},
    {
      '1': 'profile_picture',
      '3': 4,
      '4': 1,
      '5': 9,
      '9': 3,
      '10': 'profilePicture',
      '17': true
    },
    {
      '1': 'profile_access_strategy',
      '3': 5,
      '4': 1,
      '5': 14,
      '6': '.im.turms.proto.ProfileAccessStrategy',
      '9': 4,
      '10': 'profileAccessStrategy',
      '17': true
    },
    {
      '1': 'registration_date',
      '3': 6,
      '4': 1,
      '5': 3,
      '9': 5,
      '10': 'registrationDate',
      '17': true
    },
    {
      '1': 'last_updated_date',
      '3': 7,
      '4': 1,
      '5': 3,
      '9': 6,
      '10': 'lastUpdatedDate',
      '17': true
    },
    {'1': 'active', '3': 8, '4': 1, '5': 8, '9': 7, '10': 'active', '17': true},
    {
      '1': 'user_defined_attributes',
      '3': 9,
      '4': 3,
      '5': 11,
      '6': '.im.turms.proto.UserInfo.UserDefinedAttributesEntry',
      '10': 'userDefinedAttributes'
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
  '3': [UserInfo_UserDefinedAttributesEntry$json],
  '8': [
    {'1': '_id'},
    {'1': '_name'},
    {'1': '_intro'},
    {'1': '_profile_picture'},
    {'1': '_profile_access_strategy'},
    {'1': '_registration_date'},
    {'1': '_last_updated_date'},
    {'1': '_active'},
  ],
};

@$core.Deprecated('Use userInfoDescriptor instead')
const UserInfo_UserDefinedAttributesEntry$json = {
  '1': 'UserDefinedAttributesEntry',
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

/// Descriptor for `UserInfo`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List userInfoDescriptor = $convert.base64Decode(
    'CghVc2VySW5mbxITCgJpZBgBIAEoA0gAUgJpZIgBARIXCgRuYW1lGAIgASgJSAFSBG5hbWWIAQ'
    'ESGQoFaW50cm8YAyABKAlIAlIFaW50cm+IAQESLAoPcHJvZmlsZV9waWN0dXJlGAQgASgJSANS'
    'DnByb2ZpbGVQaWN0dXJliAEBEmIKF3Byb2ZpbGVfYWNjZXNzX3N0cmF0ZWd5GAUgASgOMiUuaW'
    '0udHVybXMucHJvdG8uUHJvZmlsZUFjY2Vzc1N0cmF0ZWd5SARSFXByb2ZpbGVBY2Nlc3NTdHJh'
    'dGVneYgBARIwChFyZWdpc3RyYXRpb25fZGF0ZRgGIAEoA0gFUhByZWdpc3RyYXRpb25EYXRliA'
    'EBEi8KEWxhc3RfdXBkYXRlZF9kYXRlGAcgASgDSAZSD2xhc3RVcGRhdGVkRGF0ZYgBARIbCgZh'
    'Y3RpdmUYCCABKAhIB1IGYWN0aXZliAEBEmsKF3VzZXJfZGVmaW5lZF9hdHRyaWJ1dGVzGAkgAy'
    'gLMjMuaW0udHVybXMucHJvdG8uVXNlckluZm8uVXNlckRlZmluZWRBdHRyaWJ1dGVzRW50cnlS'
    'FXVzZXJEZWZpbmVkQXR0cmlidXRlcxJCChFjdXN0b21fYXR0cmlidXRlcxgPIAMoCzIVLmltLn'
    'R1cm1zLnByb3RvLlZhbHVlUhBjdXN0b21BdHRyaWJ1dGVzGl8KGlVzZXJEZWZpbmVkQXR0cmli'
    'dXRlc0VudHJ5EhAKA2tleRgBIAEoCVIDa2V5EisKBXZhbHVlGAIgASgLMhUuaW0udHVybXMucH'
    'JvdG8uVmFsdWVSBXZhbHVlOgI4AUIFCgNfaWRCBwoFX25hbWVCCAoGX2ludHJvQhIKEF9wcm9m'
    'aWxlX3BpY3R1cmVCGgoYX3Byb2ZpbGVfYWNjZXNzX3N0cmF0ZWd5QhQKEl9yZWdpc3RyYXRpb2'
    '5fZGF0ZUIUChJfbGFzdF91cGRhdGVkX2RhdGVCCQoHX2FjdGl2ZQ==');
