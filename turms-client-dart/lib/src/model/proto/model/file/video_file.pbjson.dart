//
//  Generated code. Do not modify.
//  source: model/file/video_file.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:convert' as $convert;
import 'dart:core' as $core;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use videoFileDescriptor instead')
const VideoFile$json = {
  '1': 'VideoFile',
  '2': [
    {
      '1': 'description',
      '3': 1,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.VideoFile.Description',
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
  '3': [VideoFile_Description$json],
  '8': [
    {'1': '_description'},
    {'1': '_data'},
  ],
};

@$core.Deprecated('Use videoFileDescriptor instead')
const VideoFile_Description$json = {
  '1': 'Description',
  '2': [
    {'1': 'url', '3': 1, '4': 1, '5': 9, '10': 'url'},
    {
      '1': 'duration',
      '3': 2,
      '4': 1,
      '5': 5,
      '9': 0,
      '10': 'duration',
      '17': true
    },
    {'1': 'size', '3': 3, '4': 1, '5': 5, '9': 1, '10': 'size', '17': true},
    {'1': 'format', '3': 4, '4': 1, '5': 9, '9': 2, '10': 'format', '17': true},
  ],
  '8': [
    {'1': '_duration'},
    {'1': '_size'},
    {'1': '_format'},
  ],
};

/// Descriptor for `VideoFile`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List videoFileDescriptor = $convert.base64Decode(
    'CglWaWRlb0ZpbGUSTAoLZGVzY3JpcHRpb24YASABKAsyJS5pbS50dXJtcy5wcm90by5WaWRlb0'
    'ZpbGUuRGVzY3JpcHRpb25IAFILZGVzY3JpcHRpb26IAQESFwoEZGF0YRgCIAEoDEgBUgRkYXRh'
    'iAEBEkIKEWN1c3RvbV9hdHRyaWJ1dGVzGA8gAygLMhUuaW0udHVybXMucHJvdG8uVmFsdWVSEG'
    'N1c3RvbUF0dHJpYnV0ZXMalwEKC0Rlc2NyaXB0aW9uEhAKA3VybBgBIAEoCVIDdXJsEh8KCGR1'
    'cmF0aW9uGAIgASgFSABSCGR1cmF0aW9uiAEBEhcKBHNpemUYAyABKAVIAVIEc2l6ZYgBARIbCg'
    'Zmb3JtYXQYBCABKAlIAlIGZm9ybWF0iAEBQgsKCV9kdXJhdGlvbkIHCgVfc2l6ZUIJCgdfZm9y'
    'bWF0Qg4KDF9kZXNjcmlwdGlvbkIHCgVfZGF0YQ==');
