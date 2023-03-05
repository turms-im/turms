///
//  Generated code. Do not modify.
//  source: constant/group_member_role.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,constant_identifier_names,directives_ordering,library_prefixes,non_constant_identifier_names,prefer_final_fields,return_of_invalid_type,unnecessary_const,unnecessary_import,unnecessary_this,unused_import,unused_shown_name

// ignore_for_file: UNDEFINED_SHOWN_NAME
import 'dart:core' as $core;
import 'package:protobuf/protobuf.dart' as $pb;

class GroupMemberRole extends $pb.ProtobufEnum {
  static const GroupMemberRole OWNER = GroupMemberRole._(
      0,
      const $core.bool.fromEnvironment('protobuf.omit_enum_names')
          ? ''
          : 'OWNER');
  static const GroupMemberRole MANAGER = GroupMemberRole._(
      1,
      const $core.bool.fromEnvironment('protobuf.omit_enum_names')
          ? ''
          : 'MANAGER');
  static const GroupMemberRole MEMBER = GroupMemberRole._(
      2,
      const $core.bool.fromEnvironment('protobuf.omit_enum_names')
          ? ''
          : 'MEMBER');
  static const GroupMemberRole GUEST = GroupMemberRole._(
      3,
      const $core.bool.fromEnvironment('protobuf.omit_enum_names')
          ? ''
          : 'GUEST');
  static const GroupMemberRole ANONYMOUS_GUEST = GroupMemberRole._(
      4,
      const $core.bool.fromEnvironment('protobuf.omit_enum_names')
          ? ''
          : 'ANONYMOUS_GUEST');

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
