///
//  Generated code. Do not modify.
//  source: request/storage/delete_resource_request.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,constant_identifier_names,deprecated_member_use_from_same_package,directives_ordering,library_prefixes,non_constant_identifier_names,prefer_final_fields,return_of_invalid_type,unnecessary_const,unnecessary_import,unnecessary_this,unused_import,unused_shown_name

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
      '1': 'id_num',
      '3': 2,
      '4': 1,
      '5': 3,
      '9': 0,
      '10': 'idNum',
      '17': true
    },
    const {
      '1': 'id_str',
      '3': 3,
      '4': 1,
      '5': 9,
      '9': 1,
      '10': 'idStr',
      '17': true
    },
    const {
      '1': 'extra',
      '3': 4,
      '4': 3,
      '5': 11,
      '6': '.im.turms.proto.DeleteResourceRequest.ExtraEntry',
      '10': 'extra'
    },
  ],
  '3': const [DeleteResourceRequest_ExtraEntry$json],
  '8': const [
    const {'1': '_id_num'},
    const {'1': '_id_str'},
  ],
};

@$core.Deprecated('Use deleteResourceRequestDescriptor instead')
const DeleteResourceRequest_ExtraEntry$json = const {
  '1': 'ExtraEntry',
  '2': const [
    const {'1': 'key', '3': 1, '4': 1, '5': 9, '10': 'key'},
    const {'1': 'value', '3': 2, '4': 1, '5': 9, '10': 'value'},
  ],
  '7': const {'7': true},
};

/// Descriptor for `DeleteResourceRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List deleteResourceRequestDescriptor = $convert.base64Decode(
    'ChVEZWxldGVSZXNvdXJjZVJlcXVlc3QSNwoEdHlwZRgBIAEoDjIjLmltLnR1cm1zLnByb3RvLlN0b3JhZ2VSZXNvdXJjZVR5cGVSBHR5cGUSGgoGaWRfbnVtGAIgASgDSABSBWlkTnVtiAEBEhoKBmlkX3N0chgDIAEoCUgBUgVpZFN0cogBARJGCgVleHRyYRgEIAMoCzIwLmltLnR1cm1zLnByb3RvLkRlbGV0ZVJlc291cmNlUmVxdWVzdC5FeHRyYUVudHJ5UgVleHRyYRo4CgpFeHRyYUVudHJ5EhAKA2tleRgBIAEoCVIDa2V5EhQKBXZhbHVlGAIgASgJUgV2YWx1ZToCOAFCCQoHX2lkX251bUIJCgdfaWRfc3Ry');
