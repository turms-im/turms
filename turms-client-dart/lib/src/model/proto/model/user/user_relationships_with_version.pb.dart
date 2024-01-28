//
//  Generated code. Do not modify.
//  source: model/user/user_relationships_with_version.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:core' as $core;

import 'package:fixnum/fixnum.dart' as $fixnum;
import 'package:protobuf/protobuf.dart' as $pb;

import 'user_relationship.pb.dart' as $0;

class UserRelationshipsWithVersion extends $pb.GeneratedMessage {
  factory UserRelationshipsWithVersion({
    $core.Iterable<$0.UserRelationship>? userRelationships,
    $fixnum.Int64? lastUpdatedDate,
  }) {
    final $result = create();
    if (userRelationships != null) {
      $result.userRelationships.addAll(userRelationships);
    }
    if (lastUpdatedDate != null) {
      $result.lastUpdatedDate = lastUpdatedDate;
    }
    return $result;
  }
  UserRelationshipsWithVersion._() : super();
  factory UserRelationshipsWithVersion.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory UserRelationshipsWithVersion.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);

  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      _omitMessageNames ? '' : 'UserRelationshipsWithVersion',
      package: const $pb.PackageName(_omitMessageNames ? '' : 'im.turms.proto'),
      createEmptyInstance: create)
    ..pc<$0.UserRelationship>(
        1, _omitFieldNames ? '' : 'userRelationships', $pb.PbFieldType.PM,
        subBuilder: $0.UserRelationship.create)
    ..aInt64(2, _omitFieldNames ? '' : 'lastUpdatedDate')
    ..hasRequiredFields = false;

  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
      'Will be removed in next major version')
  UserRelationshipsWithVersion clone() =>
      UserRelationshipsWithVersion()..mergeFromMessage(this);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
      'Will be removed in next major version')
  UserRelationshipsWithVersion copyWith(
          void Function(UserRelationshipsWithVersion) updates) =>
      super.copyWith(
              (message) => updates(message as UserRelationshipsWithVersion))
          as UserRelationshipsWithVersion;

  $pb.BuilderInfo get info_ => _i;

  @$core.pragma('dart2js:noInline')
  static UserRelationshipsWithVersion create() =>
      UserRelationshipsWithVersion._();
  UserRelationshipsWithVersion createEmptyInstance() => create();
  static $pb.PbList<UserRelationshipsWithVersion> createRepeated() =>
      $pb.PbList<UserRelationshipsWithVersion>();
  @$core.pragma('dart2js:noInline')
  static UserRelationshipsWithVersion getDefault() => _defaultInstance ??=
      $pb.GeneratedMessage.$_defaultFor<UserRelationshipsWithVersion>(create);
  static UserRelationshipsWithVersion? _defaultInstance;

  @$pb.TagNumber(1)
  $core.List<$0.UserRelationship> get userRelationships => $_getList(0);

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
