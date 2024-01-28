//
//  Generated code. Do not modify.
//  source: request/group/member/create_group_members_request.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:convert' as $convert;
import 'dart:core' as $core;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use createGroupMembersRequestDescriptor instead')
const CreateGroupMembersRequest$json = {
  '1': 'CreateGroupMembersRequest',
  '2': [
    {'1': 'group_id', '3': 1, '4': 1, '5': 3, '10': 'groupId'},
    {'1': 'user_ids', '3': 2, '4': 3, '5': 3, '10': 'userIds'},
    {'1': 'name', '3': 3, '4': 1, '5': 9, '9': 0, '10': 'name', '17': true},
    {
      '1': 'role',
      '3': 4,
      '4': 1,
      '5': 14,
      '6': '.im.turms.proto.GroupMemberRole',
      '9': 1,
      '10': 'role',
      '17': true
    },
    {
      '1': 'mute_end_date',
      '3': 5,
      '4': 1,
      '5': 3,
      '9': 2,
      '10': 'muteEndDate',
      '17': true
    },
  ],
  '8': [
    {'1': '_name'},
    {'1': '_role'},
    {'1': '_mute_end_date'},
  ],
};

/// Descriptor for `CreateGroupMembersRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List createGroupMembersRequestDescriptor = $convert.base64Decode(
    'ChlDcmVhdGVHcm91cE1lbWJlcnNSZXF1ZXN0EhkKCGdyb3VwX2lkGAEgASgDUgdncm91cElkEh'
    'kKCHVzZXJfaWRzGAIgAygDUgd1c2VySWRzEhcKBG5hbWUYAyABKAlIAFIEbmFtZYgBARI4CgRy'
    'b2xlGAQgASgOMh8uaW0udHVybXMucHJvdG8uR3JvdXBNZW1iZXJSb2xlSAFSBHJvbGWIAQESJw'
    'oNbXV0ZV9lbmRfZGF0ZRgFIAEoA0gCUgttdXRlRW5kRGF0ZYgBAUIHCgVfbmFtZUIHCgVfcm9s'
    'ZUIQCg5fbXV0ZV9lbmRfZGF0ZQ==');
