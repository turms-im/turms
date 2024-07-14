//
//  Generated code. Do not modify.
//  source: model/file/file.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:convert' as $convert;
import 'dart:core' as $core;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use fileDescriptor instead')
const File$json = {
  '1': 'File',
  '2': [
    {
      '1': 'description',
      '3': 1,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.File.Description',
      '9': 0,
      '10': 'description',
      '17': true
    },
    {'1': 'data', '3': 2, '4': 1, '5': 12, '9': 1, '10': 'data', '17': true},
    {
      '1': 'custom_attributes',
      '3': 15,
      '4': 3,
      '5': 11,
      '6': '.im.turms.proto.Value',
      '10': 'customAttributes'
    },
  ],
  '3': [File_Description$json],
  '8': [
    {'1': '_description'},
    {'1': '_data'},
  ],
};

@$core.Deprecated('Use fileDescriptor instead')
const File_Description$json = {
  '1': 'Description',
  '2': [
    {'1': 'url', '3': 1, '4': 1, '5': 9, '10': 'url'},
    {'1': 'size', '3': 2, '4': 1, '5': 5, '9': 0, '10': 'size', '17': true},
    {'1': 'format', '3': 3, '4': 1, '5': 9, '9': 1, '10': 'format', '17': true},
  ],
  '8': [
    {'1': '_size'},
    {'1': '_format'},
  ],
};

/// Descriptor for `File`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List fileDescriptor = $convert.base64Decode(
    'CgRGaWxlEkcKC2Rlc2NyaXB0aW9uGAEgASgLMiAuaW0udHVybXMucHJvdG8uRmlsZS5EZXNjcm'
    'lwdGlvbkgAUgtkZXNjcmlwdGlvbogBARIXCgRkYXRhGAIgASgMSAFSBGRhdGGIAQESQgoRY3Vz'
    'dG9tX2F0dHJpYnV0ZXMYDyADKAsyFS5pbS50dXJtcy5wcm90by5WYWx1ZVIQY3VzdG9tQXR0cm'
    'lidXRlcxppCgtEZXNjcmlwdGlvbhIQCgN1cmwYASABKAlSA3VybBIXCgRzaXplGAIgASgFSABS'
    'BHNpemWIAQESGwoGZm9ybWF0GAMgASgJSAFSBmZvcm1hdIgBAUIHCgVfc2l6ZUIJCgdfZm9ybW'
    'F0Qg4KDF9kZXNjcmlwdGlvbkIHCgVfZGF0YQ==');
