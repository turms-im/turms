///
//  Generated code. Do not modify.
//  source: model/storage/storage_resource_info.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,constant_identifier_names,deprecated_member_use_from_same_package,directives_ordering,library_prefixes,non_constant_identifier_names,prefer_final_fields,return_of_invalid_type,unnecessary_const,unnecessary_import,unnecessary_this,unused_import,unused_shown_name

import 'dart:core' as $core;
import 'dart:convert' as $convert;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use storageResourceInfoDescriptor instead')
const StorageResourceInfo$json = const {
  '1': 'StorageResourceInfo',
  '2': const [
    const {
      '1': 'id_num',
      '3': 1,
      '4': 1,
      '5': 3,
      '9': 0,
      '10': 'idNum',
      '17': true
    },
    const {
      '1': 'id_str',
      '3': 2,
      '4': 1,
      '5': 9,
      '9': 1,
      '10': 'idStr',
      '17': true
    },
    const {
      '1': 'name',
      '3': 3,
      '4': 1,
      '5': 9,
      '9': 2,
      '10': 'name',
      '17': true
    },
    const {
      '1': 'media_type',
      '3': 4,
      '4': 1,
      '5': 9,
      '9': 3,
      '10': 'mediaType',
      '17': true
    },
    const {'1': 'uploader_id', '3': 5, '4': 1, '5': 3, '10': 'uploaderId'},
    const {'1': 'creation_date', '3': 6, '4': 1, '5': 3, '10': 'creationDate'},
  ],
  '8': const [
    const {'1': '_id_num'},
    const {'1': '_id_str'},
    const {'1': '_name'},
    const {'1': '_media_type'},
  ],
};

/// Descriptor for `StorageResourceInfo`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List storageResourceInfoDescriptor = $convert.base64Decode(
    'ChNTdG9yYWdlUmVzb3VyY2VJbmZvEhoKBmlkX251bRgBIAEoA0gAUgVpZE51bYgBARIaCgZpZF9zdHIYAiABKAlIAVIFaWRTdHKIAQESFwoEbmFtZRgDIAEoCUgCUgRuYW1liAEBEiIKCm1lZGlhX3R5cGUYBCABKAlIA1IJbWVkaWFUeXBliAEBEh8KC3VwbG9hZGVyX2lkGAUgASgDUgp1cGxvYWRlcklkEiMKDWNyZWF0aW9uX2RhdGUYBiABKANSDGNyZWF0aW9uRGF0ZUIJCgdfaWRfbnVtQgkKB19pZF9zdHJCBwoFX25hbWVCDQoLX21lZGlhX3R5cGU=');
