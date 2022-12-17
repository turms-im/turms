///
//  Generated code. Do not modify.
//  source: model/file/video_file.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,constant_identifier_names,deprecated_member_use_from_same_package,directives_ordering,library_prefixes,non_constant_identifier_names,prefer_final_fields,return_of_invalid_type,unnecessary_const,unnecessary_import,unnecessary_this,unused_import,unused_shown_name

import 'dart:core' as $core;
import 'dart:convert' as $convert;
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
    'CglWaWRlb0ZpbGUSTAoLZGVzY3JpcHRpb24YASABKAsyJS5pbS50dXJtcy5wcm90by5WaWRlb0ZpbGUuRGVzY3JpcHRpb25IAFILZGVzY3JpcHRpb26IAQESFwoEZGF0YRgCIAEoDEgBUgRkYXRhiAEBGpcBCgtEZXNjcmlwdGlvbhIQCgN1cmwYASABKAlSA3VybBIfCghkdXJhdGlvbhgCIAEoBUgAUghkdXJhdGlvbogBARIXCgRzaXplGAMgASgFSAFSBHNpemWIAQESGwoGZm9ybWF0GAQgASgJSAJSBmZvcm1hdIgBAUILCglfZHVyYXRpb25CBwoFX3NpemVCCQoHX2Zvcm1hdEIOCgxfZGVzY3JpcHRpb25CBwoFX2RhdGE=');
