//
//  Generated code. Do not modify.
//  source: request/group/member/delete_group_members_request.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:convert' as $convert;
import 'dart:core' as $core;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use deleteGroupMembersRequestDescriptor instead')
const DeleteGroupMembersRequest$json = {
  '1': 'DeleteGroupMembersRequest',
  '2': [
    {'1': 'group_id', '3': 1, '4': 1, '5': 3, '10': 'groupId'},
    {'1': 'member_ids', '3': 2, '4': 3, '5': 3, '10': 'memberIds'},
    {
      '1': 'successor_id',
      '3': 3,
      '4': 1,
      '5': 3,
      '9': 0,
      '10': 'successorId',
      '17': true
    },
    {
      '1': 'quit_after_transfer',
      '3': 4,
      '4': 1,
      '5': 8,
      '9': 1,
      '10': 'quitAfterTransfer',
      '17': true
    },
  ],
  '8': [
    {'1': '_successor_id'},
    {'1': '_quit_after_transfer'},
  ],
};

/// Descriptor for `DeleteGroupMembersRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List deleteGroupMembersRequestDescriptor = $convert.base64Decode(
    'ChlEZWxldGVHcm91cE1lbWJlcnNSZXF1ZXN0EhkKCGdyb3VwX2lkGAEgASgDUgdncm91cElkEh'
    '0KCm1lbWJlcl9pZHMYAiADKANSCW1lbWJlcklkcxImCgxzdWNjZXNzb3JfaWQYAyABKANIAFIL'
    'c3VjY2Vzc29ySWSIAQESMwoTcXVpdF9hZnRlcl90cmFuc2ZlchgEIAEoCEgBUhFxdWl0QWZ0ZX'
    'JUcmFuc2ZlcogBAUIPCg1fc3VjY2Vzc29yX2lkQhYKFF9xdWl0X2FmdGVyX3RyYW5zZmVy');
