///
//  Generated code. Do not modify.
//  source: request/group/member/create_group_member_request.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,unnecessary_const,non_constant_identifier_names,library_prefixes,unused_import,unused_shown_name,return_of_invalid_type,unnecessary_this,prefer_final_fields,deprecated_member_use_from_same_package

import 'dart:core' as $core;
import 'dart:convert' as $convert;
import 'dart:typed_data' as $typed_data;
@$core.Deprecated('Use createGroupMemberRequestDescriptor instead')
const CreateGroupMemberRequest$json = const {
  '1': 'CreateGroupMemberRequest',
  '2': const [
    const {'1': 'group_id', '3': 1, '4': 1, '5': 3, '10': 'groupId'},
    const {'1': 'user_id', '3': 2, '4': 1, '5': 3, '10': 'userId'},
    const {'1': 'name', '3': 3, '4': 1, '5': 9, '9': 0, '10': 'name', '17': true},
    const {'1': 'role', '3': 4, '4': 1, '5': 14, '6': '.im.turms.proto.GroupMemberRole', '10': 'role'},
    const {'1': 'mute_end_date', '3': 5, '4': 1, '5': 3, '9': 1, '10': 'muteEndDate', '17': true},
  ],
  '8': const [
    const {'1': '_name'},
    const {'1': '_mute_end_date'},
  ],
};

/// Descriptor for `CreateGroupMemberRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List createGroupMemberRequestDescriptor = $convert.base64Decode('ChhDcmVhdGVHcm91cE1lbWJlclJlcXVlc3QSGQoIZ3JvdXBfaWQYASABKANSB2dyb3VwSWQSFwoHdXNlcl9pZBgCIAEoA1IGdXNlcklkEhcKBG5hbWUYAyABKAlIAFIEbmFtZYgBARIzCgRyb2xlGAQgASgOMh8uaW0udHVybXMucHJvdG8uR3JvdXBNZW1iZXJSb2xlUgRyb2xlEicKDW11dGVfZW5kX2RhdGUYBSABKANIAVILbXV0ZUVuZERhdGWIAQFCBwoFX25hbWVCEAoOX211dGVfZW5kX2RhdGU=');
