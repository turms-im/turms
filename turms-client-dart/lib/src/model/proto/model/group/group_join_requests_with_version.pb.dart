///
//  Generated code. Do not modify.
//  source: model/group/group_join_requests_with_version.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,constant_identifier_names,directives_ordering,library_prefixes,non_constant_identifier_names,prefer_final_fields,return_of_invalid_type,unnecessary_const,unnecessary_import,unnecessary_this,unused_import,unused_shown_name

import 'dart:core' as $core;

import 'package:fixnum/fixnum.dart' as $fixnum;
import 'package:protobuf/protobuf.dart' as $pb;

import 'group_join_request.pb.dart' as $0;

class GroupJoinRequestsWithVersion extends $pb.GeneratedMessage {
  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      const $core.bool.fromEnvironment('protobuf.omit_message_names')
          ? ''
          : 'GroupJoinRequestsWithVersion',
      package: const $pb.PackageName(
          const $core.bool.fromEnvironment('protobuf.omit_message_names')
              ? ''
              : 'im.turms.proto'),
      createEmptyInstance: create)
    ..pc<$0.GroupJoinRequest>(
        1,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'groupJoinRequests',
        $pb.PbFieldType.PM,
        subBuilder: $0.GroupJoinRequest.create)
    ..aInt64(
        2,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'lastUpdatedDate')
    ..hasRequiredFields = false;

  GroupJoinRequestsWithVersion._() : super();
  factory GroupJoinRequestsWithVersion({
    $core.Iterable<$0.GroupJoinRequest>? groupJoinRequests,
    $fixnum.Int64? lastUpdatedDate,
  }) {
    final _result = create();
    if (groupJoinRequests != null) {
      _result.groupJoinRequests.addAll(groupJoinRequests);
    }
    if (lastUpdatedDate != null) {
      _result.lastUpdatedDate = lastUpdatedDate;
    }
    return _result;
  }
  factory GroupJoinRequestsWithVersion.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory GroupJoinRequestsWithVersion.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
      'Will be removed in next major version')
  GroupJoinRequestsWithVersion clone() =>
      GroupJoinRequestsWithVersion()..mergeFromMessage(this);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
      'Will be removed in next major version')
  GroupJoinRequestsWithVersion copyWith(
          void Function(GroupJoinRequestsWithVersion) updates) =>
      super.copyWith(
              (message) => updates(message as GroupJoinRequestsWithVersion))
          as GroupJoinRequestsWithVersion; // ignore: deprecated_member_use
  $pb.BuilderInfo get info_ => _i;
  @$core.pragma('dart2js:noInline')
  static GroupJoinRequestsWithVersion create() =>
      GroupJoinRequestsWithVersion._();
  GroupJoinRequestsWithVersion createEmptyInstance() => create();
  static $pb.PbList<GroupJoinRequestsWithVersion> createRepeated() =>
      $pb.PbList<GroupJoinRequestsWithVersion>();
  @$core.pragma('dart2js:noInline')
  static GroupJoinRequestsWithVersion getDefault() => _defaultInstance ??=
      $pb.GeneratedMessage.$_defaultFor<GroupJoinRequestsWithVersion>(create);
  static GroupJoinRequestsWithVersion? _defaultInstance;

  @$pb.TagNumber(1)
  $core.List<$0.GroupJoinRequest> get groupJoinRequests => $_getList(0);

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
