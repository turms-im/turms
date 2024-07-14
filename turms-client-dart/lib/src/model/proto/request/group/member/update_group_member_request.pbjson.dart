//
//  Generated code. Do not modify.
//  source: request/group/member/update_group_member_request.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:convert' as $convert;
import 'dart:core' as $core;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use updateGroupMemberRequestDescriptor instead')
const UpdateGroupMemberRequest$json = {
  '1': 'UpdateGroupMemberRequest',
  '2': [
    {'1': 'group_id', '3': 1, '4': 1, '5': 3, '10': 'groupId'},
    {'1': 'member_id', '3': 2, '4': 1, '5': 3, '10': 'memberId'},
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
    {'1': '_name'},
    {'1': '_role'},
    {'1': '_mute_end_date'},
  ],
};

/// Descriptor for `UpdateGroupMemberRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List updateGroupMemberRequestDescriptor = $convert.base64Decode(
    'ChhVcGRhdGVHcm91cE1lbWJlclJlcXVlc3QSGQoIZ3JvdXBfaWQYASABKANSB2dyb3VwSWQSGw'
    'oJbWVtYmVyX2lkGAIgASgDUghtZW1iZXJJZBIXCgRuYW1lGAMgASgJSABSBG5hbWWIAQESOAoE'
    'cm9sZRgEIAEoDjIfLmltLnR1cm1zLnByb3RvLkdyb3VwTWVtYmVyUm9sZUgBUgRyb2xliAEBEi'
    'cKDW11dGVfZW5kX2RhdGUYBSABKANIAlILbXV0ZUVuZERhdGWIAQESQgoRY3VzdG9tX2F0dHJp'
    'YnV0ZXMYDyADKAsyFS5pbS50dXJtcy5wcm90by5WYWx1ZVIQY3VzdG9tQXR0cmlidXRlc0IHCg'
    'VfbmFtZUIHCgVfcm9sZUIQCg5fbXV0ZV9lbmRfZGF0ZQ==');
