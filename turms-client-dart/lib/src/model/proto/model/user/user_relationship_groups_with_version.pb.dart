//
//  Generated code. Do not modify.
//  source: model/user/user_relationship_groups_with_version.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:core' as $core;

import 'package:fixnum/fixnum.dart' as $fixnum;
import 'package:protobuf/protobuf.dart' as $pb;

import 'user_relationship_group.pb.dart' as $0;

class UserRelationshipGroupsWithVersion extends $pb.GeneratedMessage {
  factory UserRelationshipGroupsWithVersion({
    $core.Iterable<$0.UserRelationshipGroup>? userRelationshipGroups,
    $fixnum.Int64? lastUpdatedDate,
  }) {
    final $result = create();
    if (userRelationshipGroups != null) {
      $result.userRelationshipGroups.addAll(userRelationshipGroups);
    }
    if (lastUpdatedDate != null) {
      $result.lastUpdatedDate = lastUpdatedDate;
    }
    return $result;
  }
  UserRelationshipGroupsWithVersion._() : super();
  factory UserRelationshipGroupsWithVersion.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory UserRelationshipGroupsWithVersion.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);

  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      _omitMessageNames ? '' : 'UserRelationshipGroupsWithVersion',
      package: const $pb.PackageName(_omitMessageNames ? '' : 'im.turms.proto'),
      createEmptyInstance: create)
    ..pc<$0.UserRelationshipGroup>(
        1, _omitFieldNames ? '' : 'userRelationshipGroups', $pb.PbFieldType.PM,
        subBuilder: $0.UserRelationshipGroup.create)
    ..aInt64(2, _omitFieldNames ? '' : 'lastUpdatedDate')
    ..hasRequiredFields = false;

  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
      'Will be removed in next major version')
  UserRelationshipGroupsWithVersion clone() =>
      UserRelationshipGroupsWithVersion()..mergeFromMessage(this);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
      'Will be removed in next major version')
  UserRelationshipGroupsWithVersion copyWith(
          void Function(UserRelationshipGroupsWithVersion) updates) =>
      super.copyWith((message) =>
              updates(message as UserRelationshipGroupsWithVersion))
          as UserRelationshipGroupsWithVersion;

  $pb.BuilderInfo get info_ => _i;

  @$core.pragma('dart2js:noInline')
  static UserRelationshipGroupsWithVersion create() =>
      UserRelationshipGroupsWithVersion._();
  UserRelationshipGroupsWithVersion createEmptyInstance() => create();
  static $pb.PbList<UserRelationshipGroupsWithVersion> createRepeated() =>
      $pb.PbList<UserRelationshipGroupsWithVersion>();
  @$core.pragma('dart2js:noInline')
  static UserRelationshipGroupsWithVersion getDefault() => _defaultInstance ??=
      $pb.GeneratedMessage.$_defaultFor<UserRelationshipGroupsWithVersion>(
          create);
  static UserRelationshipGroupsWithVersion? _defaultInstance;

  @$pb.TagNumber(1)
  $core.List<$0.UserRelationshipGroup> get userRelationshipGroups =>
      $_getList(0);

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
