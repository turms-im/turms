///
//  Generated code. Do not modify.
//  source: request/storage/delete_resource_request.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,unnecessary_const,non_constant_identifier_names,library_prefixes,unused_import,unused_shown_name,return_of_invalid_type,unnecessary_this,prefer_final_fields,deprecated_member_use_from_same_package

import 'dart:core' as $core;
import 'dart:convert' as $convert;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use deleteResourceRequestDescriptor instead')
const DeleteResourceRequest$json = const {
  '1': 'DeleteResourceRequest',
  '2': const [
    const {
      '1': 'type',
      '3': 1,
      '4': 1,
      '5': 14,
      '6': '.im.turms.proto.StorageResourceType',
      '10': 'type'
    },
    const {
      '1': 'key_str',
      '3': 2,
      '4': 1,
      '5': 9,
      '9': 0,
      '10': 'keyStr',
      '17': true
    },
    const {
      '1': 'key_num',
      '3': 3,
      '4': 1,
      '5': 3,
      '9': 1,
      '10': 'keyNum',
      '17': true
    },
  ],
  '8': const [
    const {'1': '_key_str'},
    const {'1': '_key_num'},
  ],
};

/// Descriptor for `DeleteResourceRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List deleteResourceRequestDescriptor = $convert.base64Decode(
    'ChVEZWxldGVSZXNvdXJjZVJlcXVlc3QSNwoEdHlwZRgBIAEoDjIjLmltLnR1cm1zLnByb3RvLlN0b3JhZ2VSZXNvdXJjZVR5cGVSBHR5cGUSHAoHa2V5X3N0chgCIAEoCUgAUgZrZXlTdHKIAQESHAoHa2V5X251bRgDIAEoA0gBUgZrZXlOdW2IAQFCCgoIX2tleV9zdHJCCgoIX2tleV9udW0=');
