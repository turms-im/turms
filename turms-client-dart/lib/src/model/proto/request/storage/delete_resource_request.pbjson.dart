//
//  Generated code. Do not modify.
//  source: request/storage/delete_resource_request.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:convert' as $convert;
import 'dart:core' as $core;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use deleteResourceRequestDescriptor instead')
const DeleteResourceRequest$json = {
  '1': 'DeleteResourceRequest',
  '2': [
    {
      '1': 'type',
      '3': 1,
      '4': 1,
      '5': 14,
      '6': '.im.turms.proto.StorageResourceType',
      '10': 'type'
    },
    {'1': 'id_num', '3': 2, '4': 1, '5': 3, '9': 0, '10': 'idNum', '17': true},
    {'1': 'id_str', '3': 3, '4': 1, '5': 9, '9': 1, '10': 'idStr', '17': true},
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
  ],
};

/// Descriptor for `DeleteResourceRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List deleteResourceRequestDescriptor = $convert.base64Decode(
    'ChVEZWxldGVSZXNvdXJjZVJlcXVlc3QSNwoEdHlwZRgBIAEoDjIjLmltLnR1cm1zLnByb3RvLl'
    'N0b3JhZ2VSZXNvdXJjZVR5cGVSBHR5cGUSGgoGaWRfbnVtGAIgASgDSABSBWlkTnVtiAEBEhoK'
    'BmlkX3N0chgDIAEoCUgBUgVpZFN0cogBARJCChFjdXN0b21fYXR0cmlidXRlcxgPIAMoCzIVLm'
    'ltLnR1cm1zLnByb3RvLlZhbHVlUhBjdXN0b21BdHRyaWJ1dGVzQgkKB19pZF9udW1CCQoHX2lk'
    'X3N0cg==');
