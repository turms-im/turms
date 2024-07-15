//
//  Generated code. Do not modify.
//  source: model/message/message_reaction_group.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:core' as $core;

import 'package:protobuf/protobuf.dart' as $pb;

import '../common/value.pb.dart' as $1;
import '../user/user_info.pb.dart' as $0;

class MessageReactionGroup extends $pb.GeneratedMessage {
  factory MessageReactionGroup({
    $core.int? reactionType,
    $core.int? reactionCount,
    $core.Iterable<$0.UserInfo>? userInfos,
    $core.Iterable<$1.Value>? customAttributes,
  }) {
    final $result = create();
    if (reactionType != null) {
      $result.reactionType = reactionType;
    }
    if (reactionCount != null) {
      $result.reactionCount = reactionCount;
    }
    if (userInfos != null) {
      $result.userInfos.addAll(userInfos);
    }
    if (customAttributes != null) {
      $result.customAttributes.addAll(customAttributes);
    }
    return $result;
  }
  MessageReactionGroup._() : super();
  factory MessageReactionGroup.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory MessageReactionGroup.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);

  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      _omitMessageNames ? '' : 'MessageReactionGroup',
      package: const $pb.PackageName(_omitMessageNames ? '' : 'im.turms.proto'),
      createEmptyInstance: create)
    ..a<$core.int>(1, _omitFieldNames ? '' : 'reactionType', $pb.PbFieldType.O3)
    ..a<$core.int>(
        2, _omitFieldNames ? '' : 'reactionCount', $pb.PbFieldType.O3)
    ..pc<$0.UserInfo>(3, _omitFieldNames ? '' : 'userInfos', $pb.PbFieldType.PM,
        subBuilder: $0.UserInfo.create)
    ..pc<$1.Value>(
        15, _omitFieldNames ? '' : 'customAttributes', $pb.PbFieldType.PM,
        subBuilder: $1.Value.create)
    ..hasRequiredFields = false;

  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
      'Will be removed in next major version')
  MessageReactionGroup clone() =>
      MessageReactionGroup()..mergeFromMessage(this);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
      'Will be removed in next major version')
  MessageReactionGroup copyWith(void Function(MessageReactionGroup) updates) =>
      super.copyWith((message) => updates(message as MessageReactionGroup))
          as MessageReactionGroup;

  $pb.BuilderInfo get info_ => _i;

  @$core.pragma('dart2js:noInline')
  static MessageReactionGroup create() => MessageReactionGroup._();
  MessageReactionGroup createEmptyInstance() => create();
  static $pb.PbList<MessageReactionGroup> createRepeated() =>
      $pb.PbList<MessageReactionGroup>();
  @$core.pragma('dart2js:noInline')
  static MessageReactionGroup getDefault() => _defaultInstance ??=
      $pb.GeneratedMessage.$_defaultFor<MessageReactionGroup>(create);
  static MessageReactionGroup? _defaultInstance;

  @$pb.TagNumber(1)
  $core.int get reactionType => $_getIZ(0);
  @$pb.TagNumber(1)
  set reactionType($core.int v) {
    $_setSignedInt32(0, v);
  }

  @$pb.TagNumber(1)
  $core.bool hasReactionType() => $_has(0);
  @$pb.TagNumber(1)
  void clearReactionType() => clearField(1);

  @$pb.TagNumber(2)
  $core.int get reactionCount => $_getIZ(1);
  @$pb.TagNumber(2)
  set reactionCount($core.int v) {
    $_setSignedInt32(1, v);
  }

  @$pb.TagNumber(2)
  $core.bool hasReactionCount() => $_has(1);
  @$pb.TagNumber(2)
  void clearReactionCount() => clearField(2);

  @$pb.TagNumber(3)
  $core.List<$0.UserInfo> get userInfos => $_getList(2);

  @$pb.TagNumber(15)
  $core.List<$1.Value> get customAttributes => $_getList(3);
}

const _omitFieldNames = $core.bool.fromEnvironment('protobuf.omit_field_names');
const _omitMessageNames =
    $core.bool.fromEnvironment('protobuf.omit_message_names');
