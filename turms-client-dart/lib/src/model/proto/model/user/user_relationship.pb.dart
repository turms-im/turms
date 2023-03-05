///
//  Generated code. Do not modify.
//  source: model/user/user_relationship.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,constant_identifier_names,directives_ordering,library_prefixes,non_constant_identifier_names,prefer_final_fields,return_of_invalid_type,unnecessary_const,unnecessary_import,unnecessary_this,unused_import,unused_shown_name

import 'dart:core' as $core;

import 'package:fixnum/fixnum.dart' as $fixnum;
import 'package:protobuf/protobuf.dart' as $pb;

class UserRelationship extends $pb.GeneratedMessage {
  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      const $core.bool.fromEnvironment('protobuf.omit_message_names')
          ? ''
          : 'UserRelationship',
      package: const $pb.PackageName(
          const $core.bool.fromEnvironment('protobuf.omit_message_names')
              ? ''
              : 'im.turms.proto'),
      createEmptyInstance: create)
    ..aInt64(
        1,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'ownerId')
    ..aInt64(
        2,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'relatedUserId')
    ..aInt64(
        3,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'blockDate')
    ..aInt64(
        4,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'groupIndex')
    ..aInt64(
        5,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'establishmentDate')
    ..hasRequiredFields = false;

  UserRelationship._() : super();
  factory UserRelationship({
    $fixnum.Int64? ownerId,
    $fixnum.Int64? relatedUserId,
    $fixnum.Int64? blockDate,
    $fixnum.Int64? groupIndex,
    $fixnum.Int64? establishmentDate,
  }) {
    final _result = create();
    if (ownerId != null) {
      _result.ownerId = ownerId;
    }
    if (relatedUserId != null) {
      _result.relatedUserId = relatedUserId;
    }
    if (blockDate != null) {
      _result.blockDate = blockDate;
    }
    if (groupIndex != null) {
      _result.groupIndex = groupIndex;
    }
    if (establishmentDate != null) {
      _result.establishmentDate = establishmentDate;
    }
    return _result;
  }
  factory UserRelationship.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory UserRelationship.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
      'Will be removed in next major version')
  UserRelationship clone() => UserRelationship()..mergeFromMessage(this);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
      'Will be removed in next major version')
  UserRelationship copyWith(void Function(UserRelationship) updates) =>
      super.copyWith((message) => updates(message as UserRelationship))
          as UserRelationship; // ignore: deprecated_member_use
  $pb.BuilderInfo get info_ => _i;
  @$core.pragma('dart2js:noInline')
  static UserRelationship create() => UserRelationship._();
  UserRelationship createEmptyInstance() => create();
  static $pb.PbList<UserRelationship> createRepeated() =>
      $pb.PbList<UserRelationship>();
  @$core.pragma('dart2js:noInline')
  static UserRelationship getDefault() => _defaultInstance ??=
      $pb.GeneratedMessage.$_defaultFor<UserRelationship>(create);
  static UserRelationship? _defaultInstance;

  @$pb.TagNumber(1)
  $fixnum.Int64 get ownerId => $_getI64(0);
  @$pb.TagNumber(1)
  set ownerId($fixnum.Int64 v) {
    $_setInt64(0, v);
  }

  @$pb.TagNumber(1)
  $core.bool hasOwnerId() => $_has(0);
  @$pb.TagNumber(1)
  void clearOwnerId() => clearField(1);

  @$pb.TagNumber(2)
  $fixnum.Int64 get relatedUserId => $_getI64(1);
  @$pb.TagNumber(2)
  set relatedUserId($fixnum.Int64 v) {
    $_setInt64(1, v);
  }

  @$pb.TagNumber(2)
  $core.bool hasRelatedUserId() => $_has(1);
  @$pb.TagNumber(2)
  void clearRelatedUserId() => clearField(2);

  @$pb.TagNumber(3)
  $fixnum.Int64 get blockDate => $_getI64(2);
  @$pb.TagNumber(3)
  set blockDate($fixnum.Int64 v) {
    $_setInt64(2, v);
  }

  @$pb.TagNumber(3)
  $core.bool hasBlockDate() => $_has(2);
  @$pb.TagNumber(3)
  void clearBlockDate() => clearField(3);

  @$pb.TagNumber(4)
  $fixnum.Int64 get groupIndex => $_getI64(3);
  @$pb.TagNumber(4)
  set groupIndex($fixnum.Int64 v) {
    $_setInt64(3, v);
  }

  @$pb.TagNumber(4)
  $core.bool hasGroupIndex() => $_has(3);
  @$pb.TagNumber(4)
  void clearGroupIndex() => clearField(4);

  @$pb.TagNumber(5)
  $fixnum.Int64 get establishmentDate => $_getI64(4);
  @$pb.TagNumber(5)
  set establishmentDate($fixnum.Int64 v) {
    $_setInt64(4, v);
  }

  @$pb.TagNumber(5)
  $core.bool hasEstablishmentDate() => $_has(4);
  @$pb.TagNumber(5)
  void clearEstablishmentDate() => clearField(5);
}
