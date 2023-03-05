///
//  Generated code. Do not modify.
//  source: model/file/image_file.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,constant_identifier_names,deprecated_member_use_from_same_package,directives_ordering,library_prefixes,non_constant_identifier_names,prefer_final_fields,return_of_invalid_type,unnecessary_const,unnecessary_import,unnecessary_this,unused_import,unused_shown_name

import 'dart:core' as $core;
import 'dart:convert' as $convert;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use imageFileDescriptor instead')
const ImageFile$json = const {
  '1': 'ImageFile',
  '2': const [
    const {
      '1': 'description',
      '3': 1,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.ImageFile.Description',
      '9': 0,
      '10': 'description',
      '17': true
    },
    const {
      '1': 'data',
      '3': 2,
      '4': 1,
      '5': 12,
      '9': 1,
      '10': 'data',
      '17': true
    },
  ],
  '3': const [ImageFile_Description$json],
  '8': const [
    const {'1': '_description'},
    const {'1': '_data'},
  ],
};

@$core.Deprecated('Use imageFileDescriptor instead')
const ImageFile_Description$json = const {
  '1': 'Description',
  '2': const [
    const {'1': 'url', '3': 1, '4': 1, '5': 9, '10': 'url'},
    const {
      '1': 'original',
      '3': 2,
      '4': 1,
      '5': 8,
      '9': 0,
      '10': 'original',
      '17': true
    },
    const {
      '1': 'image_size',
      '3': 3,
      '4': 1,
      '5': 5,
      '9': 1,
      '10': 'imageSize',
      '17': true
    },
    const {
      '1': 'file_size',
      '3': 4,
      '4': 1,
      '5': 5,
      '9': 2,
      '10': 'fileSize',
      '17': true
    },
  ],
  '8': const [
    const {'1': '_original'},
    const {'1': '_image_size'},
    const {'1': '_file_size'},
  ],
};

/// Descriptor for `ImageFile`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List imageFileDescriptor = $convert.base64Decode(
    'CglJbWFnZUZpbGUSTAoLZGVzY3JpcHRpb24YASABKAsyJS5pbS50dXJtcy5wcm90by5JbWFnZUZpbGUuRGVzY3JpcHRpb25IAFILZGVzY3JpcHRpb26IAQESFwoEZGF0YRgCIAEoDEgBUgRkYXRhiAEBGrABCgtEZXNjcmlwdGlvbhIQCgN1cmwYASABKAlSA3VybBIfCghvcmlnaW5hbBgCIAEoCEgAUghvcmlnaW5hbIgBARIiCgppbWFnZV9zaXplGAMgASgFSAFSCWltYWdlU2l6ZYgBARIgCglmaWxlX3NpemUYBCABKAVIAlIIZmlsZVNpemWIAQFCCwoJX29yaWdpbmFsQg0KC19pbWFnZV9zaXplQgwKCl9maWxlX3NpemVCDgoMX2Rlc2NyaXB0aW9uQgcKBV9kYXRh');
