//
//  Generated code. Do not modify.
//  source: request/user/update_user_request.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:convert' as $convert;
import 'dart:core' as $core;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use updateUserRequestDescriptor instead')
const UpdateUserRequest$json = {
  '1': 'UpdateUserRequest',
  '2': [
    {
      '1': 'password',
      '3': 1,
      '4': 1,
      '5': 9,
      '9': 0,
      '10': 'password',
      '17': true
    },
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
      '1': 'user_defined_attributes',
      '3': 6,
      '4': 3,
      '5': 11,
      '6': '.im.turms.proto.UpdateUserRequest.UserDefinedAttributesEntry',
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
  '3': [UpdateUserRequest_UserDefinedAttributesEntry$json],
  '8': [
    {'1': '_password'},
    {'1': '_name'},
    {'1': '_intro'},
    {'1': '_profile_picture'},
    {'1': '_profile_access_strategy'},
  ],
};

@$core.Deprecated('Use updateUserRequestDescriptor instead')
const UpdateUserRequest_UserDefinedAttributesEntry$json = {
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

/// Descriptor for `UpdateUserRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List updateUserRequestDescriptor = $convert.base64Decode(
    'ChFVcGRhdGVVc2VyUmVxdWVzdBIfCghwYXNzd29yZBgBIAEoCUgAUghwYXNzd29yZIgBARIXCg'
    'RuYW1lGAIgASgJSAFSBG5hbWWIAQESGQoFaW50cm8YAyABKAlIAlIFaW50cm+IAQESLAoPcHJv'
    'ZmlsZV9waWN0dXJlGAQgASgJSANSDnByb2ZpbGVQaWN0dXJliAEBEmIKF3Byb2ZpbGVfYWNjZX'
    'NzX3N0cmF0ZWd5GAUgASgOMiUuaW0udHVybXMucHJvdG8uUHJvZmlsZUFjY2Vzc1N0cmF0ZWd5'
    'SARSFXByb2ZpbGVBY2Nlc3NTdHJhdGVneYgBARJ0Chd1c2VyX2RlZmluZWRfYXR0cmlidXRlcx'
    'gGIAMoCzI8LmltLnR1cm1zLnByb3RvLlVwZGF0ZVVzZXJSZXF1ZXN0LlVzZXJEZWZpbmVkQXR0'
    'cmlidXRlc0VudHJ5UhV1c2VyRGVmaW5lZEF0dHJpYnV0ZXMSQgoRY3VzdG9tX2F0dHJpYnV0ZX'
    'MYDyADKAsyFS5pbS50dXJtcy5wcm90by5WYWx1ZVIQY3VzdG9tQXR0cmlidXRlcxpfChpVc2Vy'
    'RGVmaW5lZEF0dHJpYnV0ZXNFbnRyeRIQCgNrZXkYASABKAlSA2tleRIrCgV2YWx1ZRgCIAEoCz'
    'IVLmltLnR1cm1zLnByb3RvLlZhbHVlUgV2YWx1ZToCOAFCCwoJX3Bhc3N3b3JkQgcKBV9uYW1l'
    'QggKBl9pbnRyb0ISChBfcHJvZmlsZV9waWN0dXJlQhoKGF9wcm9maWxlX2FjY2Vzc19zdHJhdG'
    'VneQ==');
