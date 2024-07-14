//
//  Generated code. Do not modify.
//  source: request/group/enrollment/update_group_join_request_request.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:convert' as $convert;
import 'dart:core' as $core;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use updateGroupJoinRequestRequestDescriptor instead')
const UpdateGroupJoinRequestRequest$json = {
  '1': 'UpdateGroupJoinRequestRequest',
  '2': [
    {'1': 'request_id', '3': 1, '4': 1, '5': 3, '10': 'requestId'},
    {
      '1': 'response_action',
      '3': 2,
      '4': 1,
      '5': 14,
      '6': '.im.turms.proto.ResponseAction',
      '10': 'responseAction'
    },
    {'1': 'reason', '3': 3, '4': 1, '5': 9, '9': 0, '10': 'reason', '17': true},
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
    {'1': '_reason'},
  ],
};

/// Descriptor for `UpdateGroupJoinRequestRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List updateGroupJoinRequestRequestDescriptor = $convert.base64Decode(
    'Ch1VcGRhdGVHcm91cEpvaW5SZXF1ZXN0UmVxdWVzdBIdCgpyZXF1ZXN0X2lkGAEgASgDUglyZX'
    'F1ZXN0SWQSRwoPcmVzcG9uc2VfYWN0aW9uGAIgASgOMh4uaW0udHVybXMucHJvdG8uUmVzcG9u'
    'c2VBY3Rpb25SDnJlc3BvbnNlQWN0aW9uEhsKBnJlYXNvbhgDIAEoCUgAUgZyZWFzb26IAQESQg'
    'oRY3VzdG9tX2F0dHJpYnV0ZXMYDyADKAsyFS5pbS50dXJtcy5wcm90by5WYWx1ZVIQY3VzdG9t'
    'QXR0cmlidXRlc0IJCgdfcmVhc29u');
