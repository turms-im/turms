//
//  Generated code. Do not modify.
//  source: request/group/enrollment/delete_group_join_request_request.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:convert' as $convert;
import 'dart:core' as $core;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use deleteGroupJoinRequestRequestDescriptor instead')
const DeleteGroupJoinRequestRequest$json = {
  '1': 'DeleteGroupJoinRequestRequest',
  '2': [
    {'1': 'request_id', '3': 1, '4': 1, '5': 3, '10': 'requestId'},
    {
      '1': 'custom_attributes',
      '3': 15,
      '4': 3,
      '5': 11,
      '6': '.im.turms.proto.Value',
      '10': 'customAttributes'
    },
  ],
};

/// Descriptor for `DeleteGroupJoinRequestRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List deleteGroupJoinRequestRequestDescriptor =
    $convert.base64Decode(
        'Ch1EZWxldGVHcm91cEpvaW5SZXF1ZXN0UmVxdWVzdBIdCgpyZXF1ZXN0X2lkGAEgASgDUglyZX'
        'F1ZXN0SWQSQgoRY3VzdG9tX2F0dHJpYnV0ZXMYDyADKAsyFS5pbS50dXJtcy5wcm90by5WYWx1'
        'ZVIQY3VzdG9tQXR0cmlidXRlcw==');
