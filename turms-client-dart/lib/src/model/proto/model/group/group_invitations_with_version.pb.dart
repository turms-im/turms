///
//  Generated code. Do not modify.
//  source: model/group/group_invitations_with_version.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,constant_identifier_names,directives_ordering,library_prefixes,non_constant_identifier_names,prefer_final_fields,return_of_invalid_type,unnecessary_const,unnecessary_import,unnecessary_this,unused_import,unused_shown_name

import 'dart:core' as $core;

import 'package:fixnum/fixnum.dart' as $fixnum;
import 'package:protobuf/protobuf.dart' as $pb;

import 'group_invitation.pb.dart' as $0;

class GroupInvitationsWithVersion extends $pb.GeneratedMessage {
  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      const $core.bool.fromEnvironment('protobuf.omit_message_names')
          ? ''
          : 'GroupInvitationsWithVersion',
      package: const $pb.PackageName(
          const $core.bool.fromEnvironment('protobuf.omit_message_names')
              ? ''
              : 'im.turms.proto'),
      createEmptyInstance: create)
    ..pc<$0.GroupInvitation>(
        1,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'groupInvitations',
        $pb.PbFieldType.PM,
        subBuilder: $0.GroupInvitation.create)
    ..aInt64(
        2,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'lastUpdatedDate')
    ..hasRequiredFields = false;

  GroupInvitationsWithVersion._() : super();
  factory GroupInvitationsWithVersion({
    $core.Iterable<$0.GroupInvitation>? groupInvitations,
    $fixnum.Int64? lastUpdatedDate,
  }) {
    final _result = create();
    if (groupInvitations != null) {
      _result.groupInvitations.addAll(groupInvitations);
    }
    if (lastUpdatedDate != null) {
      _result.lastUpdatedDate = lastUpdatedDate;
    }
    return _result;
  }
  factory GroupInvitationsWithVersion.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory GroupInvitationsWithVersion.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
      'Will be removed in next major version')
  GroupInvitationsWithVersion clone() =>
      GroupInvitationsWithVersion()..mergeFromMessage(this);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
      'Will be removed in next major version')
  GroupInvitationsWithVersion copyWith(
          void Function(GroupInvitationsWithVersion) updates) =>
      super.copyWith(
              (message) => updates(message as GroupInvitationsWithVersion))
          as GroupInvitationsWithVersion; // ignore: deprecated_member_use
  $pb.BuilderInfo get info_ => _i;
  @$core.pragma('dart2js:noInline')
  static GroupInvitationsWithVersion create() =>
      GroupInvitationsWithVersion._();
  GroupInvitationsWithVersion createEmptyInstance() => create();
  static $pb.PbList<GroupInvitationsWithVersion> createRepeated() =>
      $pb.PbList<GroupInvitationsWithVersion>();
  @$core.pragma('dart2js:noInline')
  static GroupInvitationsWithVersion getDefault() => _defaultInstance ??=
      $pb.GeneratedMessage.$_defaultFor<GroupInvitationsWithVersion>(create);
  static GroupInvitationsWithVersion? _defaultInstance;

  @$pb.TagNumber(1)
  $core.List<$0.GroupInvitation> get groupInvitations => $_getList(0);

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
