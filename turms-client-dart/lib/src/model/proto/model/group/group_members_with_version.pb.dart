//
//  Generated code. Do not modify.
//  source: model/group/group_members_with_version.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:core' as $core;

import 'package:fixnum/fixnum.dart' as $fixnum;
import 'package:protobuf/protobuf.dart' as $pb;

import 'group_member.pb.dart' as $0;

class GroupMembersWithVersion extends $pb.GeneratedMessage {
  factory GroupMembersWithVersion({
    $core.Iterable<$0.GroupMember>? groupMembers,
    $fixnum.Int64? lastUpdatedDate,
  }) {
    final $result = create();
    if (groupMembers != null) {
      $result.groupMembers.addAll(groupMembers);
    }
    if (lastUpdatedDate != null) {
      $result.lastUpdatedDate = lastUpdatedDate;
    }
    return $result;
  }
  GroupMembersWithVersion._() : super();
  factory GroupMembersWithVersion.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory GroupMembersWithVersion.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);

  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      _omitMessageNames ? '' : 'GroupMembersWithVersion',
      package: const $pb.PackageName(_omitMessageNames ? '' : 'im.turms.proto'),
      createEmptyInstance: create)
    ..pc<$0.GroupMember>(
        1, _omitFieldNames ? '' : 'groupMembers', $pb.PbFieldType.PM,
        subBuilder: $0.GroupMember.create)
    ..aInt64(2, _omitFieldNames ? '' : 'lastUpdatedDate')
    ..hasRequiredFields = false;

  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
      'Will be removed in next major version')
  GroupMembersWithVersion clone() =>
      GroupMembersWithVersion()..mergeFromMessage(this);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
      'Will be removed in next major version')
  GroupMembersWithVersion copyWith(
          void Function(GroupMembersWithVersion) updates) =>
      super.copyWith((message) => updates(message as GroupMembersWithVersion))
          as GroupMembersWithVersion;

  $pb.BuilderInfo get info_ => _i;

  @$core.pragma('dart2js:noInline')
  static GroupMembersWithVersion create() => GroupMembersWithVersion._();
  GroupMembersWithVersion createEmptyInstance() => create();
  static $pb.PbList<GroupMembersWithVersion> createRepeated() =>
      $pb.PbList<GroupMembersWithVersion>();
  @$core.pragma('dart2js:noInline')
  static GroupMembersWithVersion getDefault() => _defaultInstance ??=
      $pb.GeneratedMessage.$_defaultFor<GroupMembersWithVersion>(create);
  static GroupMembersWithVersion? _defaultInstance;

  @$pb.TagNumber(1)
  $core.List<$0.GroupMember> get groupMembers => $_getList(0);

  @$pb.TagNumber(2)
  $fixnum.Int64 get lastUpdatedDate => $_getI64(1);
  @$pb.TagNumber(2)
  set lastUpdatedDate($fixnum.Int64 v) {
    $_setInt64(1, v);
  }

  @$pb.TagNumber(2)
  $core.bool hasLastUpdatedDate() => $_has(1);
  @$pb.TagNumber(2)
  void clearLastUpdatedDate() => clearField(2);
}

const _omitFieldNames = $core.bool.fromEnvironment('protobuf.omit_field_names');
const _omitMessageNames =
    $core.bool.fromEnvironment('protobuf.omit_message_names');
