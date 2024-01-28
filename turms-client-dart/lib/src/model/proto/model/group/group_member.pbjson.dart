//
//  Generated code. Do not modify.
//  source: model/group/group_member.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:convert' as $convert;
import 'dart:core' as $core;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use groupMemberDescriptor instead')
const GroupMember$json = {
  '1': 'GroupMember',
  '2': [
    {
      '1': 'group_id',
      '3': 1,
      '4': 1,
      '5': 3,
      '9': 0,
      '10': 'groupId',
      '17': true
    },
    {
      '1': 'user_id',
      '3': 2,
      '4': 1,
      '5': 3,
      '9': 1,
      '10': 'userId',
      '17': true
    },
    {'1': 'name', '3': 3, '4': 1, '5': 9, '9': 2, '10': 'name', '17': true},
    {
      '1': 'role',
      '3': 4,
      '4': 1,
      '5': 14,
      '6': '.im.turms.proto.GroupMemberRole',
      '9': 3,
      '10': 'role',
      '17': true
    },
    {
      '1': 'join_date',
      '3': 5,
      '4': 1,
      '5': 3,
      '9': 4,
      '10': 'joinDate',
      '17': true
    },
    {
      '1': 'mute_end_date',
      '3': 6,
      '4': 1,
      '5': 3,
      '9': 5,
      '10': 'muteEndDate',
      '17': true
    },
    {
      '1': 'user_status',
      '3': 7,
      '4': 1,
      '5': 14,
      '6': '.im.turms.proto.UserStatus',
      '9': 6,
      '10': 'userStatus',
      '17': true
    },
    {
      '1': 'using_device_types',
      '3': 8,
      '4': 3,
      '5': 14,
      '6': '.im.turms.proto.DeviceType',
      '10': 'usingDeviceTypes'
    },
  ],
  '8': [
    {'1': '_group_id'},
    {'1': '_user_id'},
    {'1': '_name'},
    {'1': '_role'},
    {'1': '_join_date'},
    {'1': '_mute_end_date'},
    {'1': '_user_status'},
  ],
};

/// Descriptor for `GroupMember`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List groupMemberDescriptor = $convert.base64Decode(
    'CgtHcm91cE1lbWJlchIeCghncm91cF9pZBgBIAEoA0gAUgdncm91cElkiAEBEhwKB3VzZXJfaW'
    'QYAiABKANIAVIGdXNlcklkiAEBEhcKBG5hbWUYAyABKAlIAlIEbmFtZYgBARI4CgRyb2xlGAQg'
    'ASgOMh8uaW0udHVybXMucHJvdG8uR3JvdXBNZW1iZXJSb2xlSANSBHJvbGWIAQESIAoJam9pbl'
    '9kYXRlGAUgASgDSARSCGpvaW5EYXRliAEBEicKDW11dGVfZW5kX2RhdGUYBiABKANIBVILbXV0'
    'ZUVuZERhdGWIAQESQAoLdXNlcl9zdGF0dXMYByABKA4yGi5pbS50dXJtcy5wcm90by5Vc2VyU3'
    'RhdHVzSAZSCnVzZXJTdGF0dXOIAQESSAoSdXNpbmdfZGV2aWNlX3R5cGVzGAggAygOMhouaW0u'
    'dHVybXMucHJvdG8uRGV2aWNlVHlwZVIQdXNpbmdEZXZpY2VUeXBlc0ILCglfZ3JvdXBfaWRCCg'
    'oIX3VzZXJfaWRCBwoFX25hbWVCBwoFX3JvbGVCDAoKX2pvaW5fZGF0ZUIQCg5fbXV0ZV9lbmRf'
    'ZGF0ZUIOCgxfdXNlcl9zdGF0dXM=');
