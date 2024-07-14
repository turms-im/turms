//
//  Generated code. Do not modify.
//  source: request/user/relationship/update_friend_request_request.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:convert' as $convert;
import 'dart:core' as $core;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use updateFriendRequestRequestDescriptor instead')
const UpdateFriendRequestRequest$json = {
  '1': 'UpdateFriendRequestRequest',
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

/// Descriptor for `UpdateFriendRequestRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List updateFriendRequestRequestDescriptor = $convert.base64Decode(
    'ChpVcGRhdGVGcmllbmRSZXF1ZXN0UmVxdWVzdBIdCgpyZXF1ZXN0X2lkGAEgASgDUglyZXF1ZX'
    'N0SWQSRwoPcmVzcG9uc2VfYWN0aW9uGAIgASgOMh4uaW0udHVybXMucHJvdG8uUmVzcG9uc2VB'
    'Y3Rpb25SDnJlc3BvbnNlQWN0aW9uEhsKBnJlYXNvbhgDIAEoCUgAUgZyZWFzb26IAQESQgoRY3'
    'VzdG9tX2F0dHJpYnV0ZXMYDyADKAsyFS5pbS50dXJtcy5wcm90by5WYWx1ZVIQY3VzdG9tQXR0'
    'cmlidXRlc0IJCgdfcmVhc29u');
