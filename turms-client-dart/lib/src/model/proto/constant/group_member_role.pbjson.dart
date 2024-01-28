//
//  Generated code. Do not modify.
//  source: constant/group_member_role.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:convert' as $convert;
import 'dart:core' as $core;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use groupMemberRoleDescriptor instead')
const GroupMemberRole$json = {
  '1': 'GroupMemberRole',
  '2': [
    {'1': 'OWNER', '2': 0},
    {'1': 'MANAGER', '2': 1},
    {'1': 'MEMBER', '2': 2},
    {'1': 'GUEST', '2': 3},
    {'1': 'ANONYMOUS_GUEST', '2': 4},
  ],
};

/// Descriptor for `GroupMemberRole`. Decode as a `google.protobuf.EnumDescriptorProto`.
final $typed_data.Uint8List groupMemberRoleDescriptor = $convert.base64Decode(
    'Cg9Hcm91cE1lbWJlclJvbGUSCQoFT1dORVIQABILCgdNQU5BR0VSEAESCgoGTUVNQkVSEAISCQ'
    'oFR1VFU1QQAxITCg9BTk9OWU1PVVNfR1VFU1QQBA==');
