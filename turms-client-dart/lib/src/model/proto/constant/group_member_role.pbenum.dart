//
//  Generated code. Do not modify.
//  source: constant/group_member_role.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:core' as $core;

import 'package:protobuf/protobuf.dart' as $pb;

class GroupMemberRole extends $pb.ProtobufEnum {
  static const GroupMemberRole OWNER =
      GroupMemberRole._(0, _omitEnumNames ? '' : 'OWNER');
  static const GroupMemberRole MANAGER =
      GroupMemberRole._(1, _omitEnumNames ? '' : 'MANAGER');
  static const GroupMemberRole MEMBER =
      GroupMemberRole._(2, _omitEnumNames ? '' : 'MEMBER');
  static const GroupMemberRole GUEST =
      GroupMemberRole._(3, _omitEnumNames ? '' : 'GUEST');
  static const GroupMemberRole ANONYMOUS_GUEST =
      GroupMemberRole._(4, _omitEnumNames ? '' : 'ANONYMOUS_GUEST');

  static const $core.List<GroupMemberRole> values = <GroupMemberRole>[
    OWNER,
    MANAGER,
    MEMBER,
    GUEST,
    ANONYMOUS_GUEST,
  ];

  static final $core.Map<$core.int, GroupMemberRole> _byValue =
      $pb.ProtobufEnum.initByValue(values);
  static GroupMemberRole? valueOf($core.int value) => _byValue[value];

  const GroupMemberRole._($core.int v, $core.String n) : super(v, n);
}

const _omitEnumNames = $core.bool.fromEnvironment('protobuf.omit_enum_names');
