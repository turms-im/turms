///
//  Generated code. Do not modify.
//  source: constant/group_member_role.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,constant_identifier_names,deprecated_member_use_from_same_package,directives_ordering,library_prefixes,non_constant_identifier_names,prefer_final_fields,return_of_invalid_type,unnecessary_const,unnecessary_import,unnecessary_this,unused_import,unused_shown_name

import 'dart:core' as $core;
import 'dart:convert' as $convert;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use groupMemberRoleDescriptor instead')
const GroupMemberRole$json = const {
  '1': 'GroupMemberRole',
  '2': const [
    const {'1': 'OWNER', '2': 0},
    const {'1': 'MANAGER', '2': 1},
    const {'1': 'MEMBER', '2': 2},
    const {'1': 'GUEST', '2': 3},
    const {'1': 'ANONYMOUS_GUEST', '2': 4},
  ],
};

/// Descriptor for `GroupMemberRole`. Decode as a `google.protobuf.EnumDescriptorProto`.
final $typed_data.Uint8List groupMemberRoleDescriptor = $convert.base64Decode(
    'Cg9Hcm91cE1lbWJlclJvbGUSCQoFT1dORVIQABILCgdNQU5BR0VSEAESCgoGTUVNQkVSEAISCQoFR1VFU1QQAxITCg9BTk9OWU1PVVNfR1VFU1QQBA==');
