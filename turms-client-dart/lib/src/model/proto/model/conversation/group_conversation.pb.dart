//
//  Generated code. Do not modify.
//  source: model/conversation/group_conversation.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:core' as $core;

import 'package:fixnum/fixnum.dart' as $fixnum;
import 'package:protobuf/protobuf.dart' as $pb;

class GroupConversation extends $pb.GeneratedMessage {
  factory GroupConversation({
    $fixnum.Int64? groupId,
    $core.Map<$fixnum.Int64, $fixnum.Int64>? memberIdToReadDate,
  }) {
    final $result = create();
    if (groupId != null) {
      $result.groupId = groupId;
    }
    if (memberIdToReadDate != null) {
      $result.memberIdToReadDate.addAll(memberIdToReadDate);
    }
    return $result;
  }
  GroupConversation._() : super();
  factory GroupConversation.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory GroupConversation.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);

  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      _omitMessageNames ? '' : 'GroupConversation',
      package: const $pb.PackageName(_omitMessageNames ? '' : 'im.turms.proto'),
      createEmptyInstance: create)
    ..aInt64(1, _omitFieldNames ? '' : 'groupId')
    ..m<$fixnum.Int64, $fixnum.Int64>(
        2, _omitFieldNames ? '' : 'memberIdToReadDate',
        entryClassName: 'GroupConversation.MemberIdToReadDateEntry',
        keyFieldType: $pb.PbFieldType.O6,
        valueFieldType: $pb.PbFieldType.O6,
        packageName: const $pb.PackageName('im.turms.proto'))
    ..hasRequiredFields = false;

  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
      'Will be removed in next major version')
  GroupConversation clone() => GroupConversation()..mergeFromMessage(this);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
      'Will be removed in next major version')
  GroupConversation copyWith(void Function(GroupConversation) updates) =>
      super.copyWith((message) => updates(message as GroupConversation))
          as GroupConversation;

  $pb.BuilderInfo get info_ => _i;

  @$core.pragma('dart2js:noInline')
  static GroupConversation create() => GroupConversation._();
  GroupConversation createEmptyInstance() => create();
  static $pb.PbList<GroupConversation> createRepeated() =>
      $pb.PbList<GroupConversation>();
  @$core.pragma('dart2js:noInline')
  static GroupConversation getDefault() => _defaultInstance ??=
      $pb.GeneratedMessage.$_defaultFor<GroupConversation>(create);
  static GroupConversation? _defaultInstance;

  @$pb.TagNumber(1)
  $fixnum.Int64 get groupId => $_getI64(0);
  @$pb.TagNumber(1)
  set groupId($fixnum.Int64 v) {
    $_setInt64(0, v);
  }

  @$pb.TagNumber(1)
  $core.bool hasGroupId() => $_has(0);
  @$pb.TagNumber(1)
  void clearGroupId() => clearField(1);

  @$pb.TagNumber(2)
  $core.Map<$fixnum.Int64, $fixnum.Int64> get memberIdToReadDate => $_getMap(1);
}

const _omitFieldNames = $core.bool.fromEnvironment('protobuf.omit_field_names');
const _omitMessageNames =
    $core.bool.fromEnvironment('protobuf.omit_message_names');
