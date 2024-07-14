//
//  Generated code. Do not modify.
//  source: model/storage/storage_resource_info.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:convert' as $convert;
import 'dart:core' as $core;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use storageResourceInfoDescriptor instead')
const StorageResourceInfo$json = {
  '1': 'StorageResourceInfo',
  '2': [
    {'1': 'id_num', '3': 1, '4': 1, '5': 3, '9': 0, '10': 'idNum', '17': true},
    {'1': 'id_str', '3': 2, '4': 1, '5': 9, '9': 1, '10': 'idStr', '17': true},
    {'1': 'name', '3': 3, '4': 1, '5': 9, '9': 2, '10': 'name', '17': true},
    {
      '1': 'media_type',
      '3': 4,
      '4': 1,
      '5': 9,
      '9': 3,
      '10': 'mediaType',
      '17': true
    },
    {'1': 'uploader_id', '3': 5, '4': 1, '5': 3, '10': 'uploaderId'},
    {'1': 'creation_date', '3': 6, '4': 1, '5': 3, '10': 'creationDate'},
    {
      '1': 'custom_attributes',
      '3': 15,
      '4': 3,
      '5': 11,
      '6': '.im.turms.proto.Value',
      '10': 'customAttributes'
    },
  ],
  '8': [
    {'1': '_id_num'},
    {'1': '_id_str'},
    {'1': '_name'},
    {'1': '_media_type'},
  ],
};

/// Descriptor for `StorageResourceInfo`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List storageResourceInfoDescriptor = $convert.base64Decode(
    'ChNTdG9yYWdlUmVzb3VyY2VJbmZvEhoKBmlkX251bRgBIAEoA0gAUgVpZE51bYgBARIaCgZpZF'
    '9zdHIYAiABKAlIAVIFaWRTdHKIAQESFwoEbmFtZRgDIAEoCUgCUgRuYW1liAEBEiIKCm1lZGlh'
    'X3R5cGUYBCABKAlIA1IJbWVkaWFUeXBliAEBEh8KC3VwbG9hZGVyX2lkGAUgASgDUgp1cGxvYW'
    'RlcklkEiMKDWNyZWF0aW9uX2RhdGUYBiABKANSDGNyZWF0aW9uRGF0ZRJCChFjdXN0b21fYXR0'
    'cmlidXRlcxgPIAMoCzIVLmltLnR1cm1zLnByb3RvLlZhbHVlUhBjdXN0b21BdHRyaWJ1dGVzQg'
    'kKB19pZF9udW1CCQoHX2lkX3N0ckIHCgVfbmFtZUINCgtfbWVkaWFfdHlwZQ==');
