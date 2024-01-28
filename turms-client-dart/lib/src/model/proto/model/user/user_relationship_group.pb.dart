//
//  Generated code. Do not modify.
//  source: model/user/user_relationship_group.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:core' as $core;

import 'package:protobuf/protobuf.dart' as $pb;

class UserRelationshipGroup extends $pb.GeneratedMessage {
  factory UserRelationshipGroup({
    $core.int? index,
    $core.String? name,
  }) {
    final $result = create();
    if (index != null) {
      $result.index = index;
    }
    if (name != null) {
      $result.name = name;
    }
    return $result;
  }
  UserRelationshipGroup._() : super();
  factory UserRelationshipGroup.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory UserRelationshipGroup.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);

  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      _omitMessageNames ? '' : 'UserRelationshipGroup',
      package: const $pb.PackageName(_omitMessageNames ? '' : 'im.turms.proto'),
      createEmptyInstance: create)
    ..a<$core.int>(1, _omitFieldNames ? '' : 'index', $pb.PbFieldType.O3)
    ..aOS(2, _omitFieldNames ? '' : 'name')
    ..hasRequiredFields = false;

  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
      'Will be removed in next major version')
  UserRelationshipGroup clone() =>
      UserRelationshipGroup()..mergeFromMessage(this);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
      'Will be removed in next major version')
  UserRelationshipGroup copyWith(
          void Function(UserRelationshipGroup) updates) =>
      super.copyWith((message) => updates(message as UserRelationshipGroup))
          as UserRelationshipGroup;

  $pb.BuilderInfo get info_ => _i;

  @$core.pragma('dart2js:noInline')
  static UserRelationshipGroup create() => UserRelationshipGroup._();
  UserRelationshipGroup createEmptyInstance() => create();
  static $pb.PbList<UserRelationshipGroup> createRepeated() =>
      $pb.PbList<UserRelationshipGroup>();
  @$core.pragma('dart2js:noInline')
  static UserRelationshipGroup getDefault() => _defaultInstance ??=
      $pb.GeneratedMessage.$_defaultFor<UserRelationshipGroup>(create);
  static UserRelationshipGroup? _defaultInstance;

  @$pb.TagNumber(1)
  $core.int get index => $_getIZ(0);
  @$pb.TagNumber(1)
  set index($core.int v) {
    $_setSignedInt32(0, v);
  }

  @$pb.TagNumber(1)
  $core.bool hasIndex() => $_has(0);
  @$pb.TagNumber(1)
  void clearIndex() => clearField(1);

  @$pb.TagNumber(2)
  $core.String get name => $_getSZ(1);
  @$pb.TagNumber(2)
  set name($core.String v) {
    $_setString(1, v);
  }

  @$pb.TagNumber(2)
  $core.bool hasName() => $_has(1);
  @$pb.TagNumber(2)
  void clearName() => clearField(2);
}

const _omitFieldNames = $core.bool.fromEnvironment('protobuf.omit_field_names');
const _omitMessageNames =
    $core.bool.fromEnvironment('protobuf.omit_message_names');
