///
//  Generated code. Do not modify.
//  source: request/group/member/delete_group_members_request.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,constant_identifier_names,deprecated_member_use_from_same_package,directives_ordering,library_prefixes,non_constant_identifier_names,prefer_final_fields,return_of_invalid_type,unnecessary_const,unnecessary_import,unnecessary_this,unused_import,unused_shown_name

import 'dart:core' as $core;
import 'dart:convert' as $convert;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use deleteGroupMembersRequestDescriptor instead')
const DeleteGroupMembersRequest$json = const {
  '1': 'DeleteGroupMembersRequest',
  '2': const [
    const {'1': 'group_id', '3': 1, '4': 1, '5': 3, '10': 'groupId'},
    const {'1': 'member_ids', '3': 2, '4': 3, '5': 3, '10': 'memberIds'},
    const {
      '1': 'successor_id',
      '3': 3,
      '4': 1,
      '5': 3,
      '9': 0,
      '10': 'successorId',
      '17': true
    },
    const {
      '1': 'quit_after_transfer',
      '3': 4,
      '4': 1,
      '5': 8,
      '9': 1,
      '10': 'quitAfterTransfer',
      '17': true
    },
  ],
  '8': const [
    const {'1': '_successor_id'},
    const {'1': '_quit_after_transfer'},
  ],
};

/// Descriptor for `DeleteGroupMembersRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List deleteGroupMembersRequestDescriptor =
    $convert.base64Decode(
        'ChlEZWxldGVHcm91cE1lbWJlcnNSZXF1ZXN0EhkKCGdyb3VwX2lkGAEgASgDUgdncm91cElkEh0KCm1lbWJlcl9pZHMYAiADKANSCW1lbWJlcklkcxImCgxzdWNjZXNzb3JfaWQYAyABKANIAFILc3VjY2Vzc29ySWSIAQESMwoTcXVpdF9hZnRlcl90cmFuc2ZlchgEIAEoCEgBUhFxdWl0QWZ0ZXJUcmFuc2ZlcogBAUIPCg1fc3VjY2Vzc29yX2lkQhYKFF9xdWl0X2FmdGVyX3RyYW5zZmVy');
