///
//  Generated code. Do not modify.
//  source: model/group/group.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,constant_identifier_names,directives_ordering,library_prefixes,non_constant_identifier_names,prefer_final_fields,return_of_invalid_type,unnecessary_const,unnecessary_import,unnecessary_this,unused_import,unused_shown_name

import 'dart:core' as $core;

import 'package:fixnum/fixnum.dart' as $fixnum;
import 'package:protobuf/protobuf.dart' as $pb;

class Group extends $pb.GeneratedMessage {
  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      const $core.bool.fromEnvironment('protobuf.omit_message_names')
          ? ''
          : 'Group',
      package: const $pb.PackageName(
          const $core.bool.fromEnvironment('protobuf.omit_message_names')
              ? ''
              : 'im.turms.proto'),
      createEmptyInstance: create)
    ..aInt64(
        1,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'id')
    ..aInt64(
        2,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'typeId')
    ..aInt64(
        3,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'creatorId')
    ..aInt64(
        4,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'ownerId')
    ..aOS(
        5,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'name')
    ..aOS(
        6,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'intro')
    ..aOS(
        7,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'announcement')
    ..aInt64(
        8,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'creationDate')
    ..aInt64(
        9,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'lastUpdatedDate')
    ..aInt64(
        10,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'muteEndDate')
    ..aOB(
        11,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'active')
    ..hasRequiredFields = false;

  Group._() : super();
  factory Group({
    $fixnum.Int64? id,
    $fixnum.Int64? typeId,
    $fixnum.Int64? creatorId,
    $fixnum.Int64? ownerId,
    $core.String? name,
    $core.String? intro,
    $core.String? announcement,
    $fixnum.Int64? creationDate,
    $fixnum.Int64? lastUpdatedDate,
    $fixnum.Int64? muteEndDate,
    $core.bool? active,
  }) {
    final _result = create();
    if (id != null) {
      _result.id = id;
    }
    if (typeId != null) {
      _result.typeId = typeId;
    }
    if (creatorId != null) {
      _result.creatorId = creatorId;
    }
    if (ownerId != null) {
      _result.ownerId = ownerId;
    }
    if (name != null) {
      _result.name = name;
    }
    if (intro != null) {
      _result.intro = intro;
    }
    if (announcement != null) {
      _result.announcement = announcement;
    }
    if (creationDate != null) {
      _result.creationDate = creationDate;
    }
    if (lastUpdatedDate != null) {
      _result.lastUpdatedDate = lastUpdatedDate;
    }
    if (muteEndDate != null) {
      _result.muteEndDate = muteEndDate;
    }
    if (active != null) {
      _result.active = active;
    }
    return _result;
  }
  factory Group.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory Group.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
      'Will be removed in next major version')
  Group clone() => Group()..mergeFromMessage(this);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
      'Will be removed in next major version')
  Group copyWith(void Function(Group) updates) =>
      super.copyWith((message) => updates(message as Group))
          as Group; // ignore: deprecated_member_use
  $pb.BuilderInfo get info_ => _i;
  @$core.pragma('dart2js:noInline')
  static Group create() => Group._();
  Group createEmptyInstance() => create();
  static $pb.PbList<Group> createRepeated() => $pb.PbList<Group>();
  @$core.pragma('dart2js:noInline')
  static Group getDefault() =>
      _defaultInstance ??= $pb.GeneratedMessage.$_defaultFor<Group>(create);
  static Group? _defaultInstance;

  @$pb.TagNumber(1)
  $fixnum.Int64 get id => $_getI64(0);
  @$pb.TagNumber(1)
  set id($fixnum.Int64 v) {
    $_setInt64(0, v);
  }

  @$pb.TagNumber(1)
  $core.bool hasId() => $_has(0);
  @$pb.TagNumber(1)
  void clearId() => clearField(1);

  @$pb.TagNumber(2)
  $fixnum.Int64 get typeId => $_getI64(1);
  @$pb.TagNumber(2)
  set typeId($fixnum.Int64 v) {
    $_setInt64(1, v);
  }

  @$pb.TagNumber(2)
  $core.bool hasTypeId() => $_has(1);
  @$pb.TagNumber(2)
  void clearTypeId() => clearField(2);

  @$pb.TagNumber(3)
  $fixnum.Int64 get creatorId => $_getI64(2);
  @$pb.TagNumber(3)
  set creatorId($fixnum.Int64 v) {
    $_setInt64(2, v);
  }

  @$pb.TagNumber(3)
  $core.bool hasCreatorId() => $_has(2);
  @$pb.TagNumber(3)
  void clearCreatorId() => clearField(3);

  @$pb.TagNumber(4)
  $fixnum.Int64 get ownerId => $_getI64(3);
  @$pb.TagNumber(4)
  set ownerId($fixnum.Int64 v) {
    $_setInt64(3, v);
  }

  @$pb.TagNumber(4)
  $core.bool hasOwnerId() => $_has(3);
  @$pb.TagNumber(4)
  void clearOwnerId() => clearField(4);

  @$pb.TagNumber(5)
  $core.String get name => $_getSZ(4);
  @$pb.TagNumber(5)
  set name($core.String v) {
    $_setString(4, v);
  }

  @$pb.TagNumber(5)
  $core.bool hasName() => $_has(4);
  @$pb.TagNumber(5)
  void clearName() => clearField(5);

  @$pb.TagNumber(6)
  $core.String get intro => $_getSZ(5);
  @$pb.TagNumber(6)
  set intro($core.String v) {
    $_setString(5, v);
  }

  @$pb.TagNumber(6)
  $core.bool hasIntro() => $_has(5);
  @$pb.TagNumber(6)
  void clearIntro() => clearField(6);

  @$pb.TagNumber(7)
  $core.String get announcement => $_getSZ(6);
  @$pb.TagNumber(7)
  set announcement($core.String v) {
    $_setString(6, v);
  }

  @$pb.TagNumber(7)
  $core.bool hasAnnouncement() => $_has(6);
  @$pb.TagNumber(7)
  void clearAnnouncement() => clearField(7);

  @$pb.TagNumber(8)
  $fixnum.Int64 get creationDate => $_getI64(7);
  @$pb.TagNumber(8)
  set creationDate($fixnum.Int64 v) {
    $_setInt64(7, v);
  }

  @$pb.TagNumber(8)
  $core.bool hasCreationDate() => $_has(7);
  @$pb.TagNumber(8)
  void clearCreationDate() => clearField(8);

  @$pb.TagNumber(9)
  $fixnum.Int64 get lastUpdatedDate => $_getI64(8);
  @$pb.TagNumber(9)
  set lastUpdatedDate($fixnum.Int64 v) {
    $_setInt64(8, v);
  }

  @$pb.TagNumber(9)
  $core.bool hasLastUpdatedDate() => $_has(8);
  @$pb.TagNumber(9)
  void clearLastUpdatedDate() => clearField(9);

  @$pb.TagNumber(10)
  $fixnum.Int64 get muteEndDate => $_getI64(9);
  @$pb.TagNumber(10)
  set muteEndDate($fixnum.Int64 v) {
    $_setInt64(9, v);
  }

  @$pb.TagNumber(10)
  $core.bool hasMuteEndDate() => $_has(9);
  @$pb.TagNumber(10)
  void clearMuteEndDate() => clearField(10);

  @$pb.TagNumber(11)
  $core.bool get active => $_getBF(10);
  @$pb.TagNumber(11)
  set active($core.bool v) {
    $_setBool(10, v);
  }

  @$pb.TagNumber(11)
  $core.bool hasActive() => $_has(10);
  @$pb.TagNumber(11)
  void clearActive() => clearField(11);
}
