//
//  Generated code. Do not modify.
//  source: model/message/message.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:core' as $core;

import 'package:fixnum/fixnum.dart' as $fixnum;
import 'package:protobuf/protobuf.dart' as $pb;

import '../common/value.pb.dart' as $1;
import 'message_reaction_group.pb.dart' as $0;

class Message extends $pb.GeneratedMessage {
  factory Message({
    $fixnum.Int64? id,
    $fixnum.Int64? deliveryDate,
    $fixnum.Int64? modificationDate,
    $core.String? text,
    $fixnum.Int64? senderId,
    $fixnum.Int64? groupId,
    $core.bool? isSystemMessage,
    $fixnum.Int64? recipientId,
    $core.Iterable<$core.List<$core.int>>? records,
    $core.int? sequenceId,
    $fixnum.Int64? preMessageId,
    $core.Iterable<$0.MessageReactionGroup>? reactionGroups,
    $core.Iterable<$1.Value>? customAttributes,
  }) {
    final $result = create();
    if (id != null) {
      $result.id = id;
    }
    if (deliveryDate != null) {
      $result.deliveryDate = deliveryDate;
    }
    if (modificationDate != null) {
      $result.modificationDate = modificationDate;
    }
    if (text != null) {
      $result.text = text;
    }
    if (senderId != null) {
      $result.senderId = senderId;
    }
    if (groupId != null) {
      $result.groupId = groupId;
    }
    if (isSystemMessage != null) {
      $result.isSystemMessage = isSystemMessage;
    }
    if (recipientId != null) {
      $result.recipientId = recipientId;
    }
    if (records != null) {
      $result.records.addAll(records);
    }
    if (sequenceId != null) {
      $result.sequenceId = sequenceId;
    }
    if (preMessageId != null) {
      $result.preMessageId = preMessageId;
    }
    if (reactionGroups != null) {
      $result.reactionGroups.addAll(reactionGroups);
    }
    if (customAttributes != null) {
      $result.customAttributes.addAll(customAttributes);
    }
    return $result;
  }
  Message._() : super();
  factory Message.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory Message.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);

  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      _omitMessageNames ? '' : 'Message',
      package: const $pb.PackageName(_omitMessageNames ? '' : 'im.turms.proto'),
      createEmptyInstance: create)
    ..aInt64(1, _omitFieldNames ? '' : 'id')
    ..aInt64(2, _omitFieldNames ? '' : 'deliveryDate')
    ..aInt64(3, _omitFieldNames ? '' : 'modificationDate')
    ..aOS(4, _omitFieldNames ? '' : 'text')
    ..aInt64(5, _omitFieldNames ? '' : 'senderId')
    ..aInt64(6, _omitFieldNames ? '' : 'groupId')
    ..aOB(7, _omitFieldNames ? '' : 'isSystemMessage')
    ..aInt64(8, _omitFieldNames ? '' : 'recipientId')
    ..p<$core.List<$core.int>>(
        9, _omitFieldNames ? '' : 'records', $pb.PbFieldType.PY)
    ..a<$core.int>(10, _omitFieldNames ? '' : 'sequenceId', $pb.PbFieldType.O3)
    ..aInt64(11, _omitFieldNames ? '' : 'preMessageId')
    ..pc<$0.MessageReactionGroup>(
        12, _omitFieldNames ? '' : 'reactionGroups', $pb.PbFieldType.PM,
        subBuilder: $0.MessageReactionGroup.create)
    ..pc<$1.Value>(
        15, _omitFieldNames ? '' : 'customAttributes', $pb.PbFieldType.PM,
        subBuilder: $1.Value.create)
    ..hasRequiredFields = false;

  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
      'Will be removed in next major version')
  Message clone() => Message()..mergeFromMessage(this);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
      'Will be removed in next major version')
  Message copyWith(void Function(Message) updates) =>
      super.copyWith((message) => updates(message as Message)) as Message;

  $pb.BuilderInfo get info_ => _i;

  @$core.pragma('dart2js:noInline')
  static Message create() => Message._();
  Message createEmptyInstance() => create();
  static $pb.PbList<Message> createRepeated() => $pb.PbList<Message>();
  @$core.pragma('dart2js:noInline')
  static Message getDefault() =>
      _defaultInstance ??= $pb.GeneratedMessage.$_defaultFor<Message>(create);
  static Message? _defaultInstance;

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
  $fixnum.Int64 get deliveryDate => $_getI64(1);
  @$pb.TagNumber(2)
  set deliveryDate($fixnum.Int64 v) {
    $_setInt64(1, v);
  }

  @$pb.TagNumber(2)
  $core.bool hasDeliveryDate() => $_has(1);
  @$pb.TagNumber(2)
  void clearDeliveryDate() => clearField(2);

  @$pb.TagNumber(3)
  $fixnum.Int64 get modificationDate => $_getI64(2);
  @$pb.TagNumber(3)
  set modificationDate($fixnum.Int64 v) {
    $_setInt64(2, v);
  }

  @$pb.TagNumber(3)
  $core.bool hasModificationDate() => $_has(2);
  @$pb.TagNumber(3)
  void clearModificationDate() => clearField(3);

  @$pb.TagNumber(4)
  $core.String get text => $_getSZ(3);
  @$pb.TagNumber(4)
  set text($core.String v) {
    $_setString(3, v);
  }

  @$pb.TagNumber(4)
  $core.bool hasText() => $_has(3);
  @$pb.TagNumber(4)
  void clearText() => clearField(4);

  @$pb.TagNumber(5)
  $fixnum.Int64 get senderId => $_getI64(4);
  @$pb.TagNumber(5)
  set senderId($fixnum.Int64 v) {
    $_setInt64(4, v);
  }

  @$pb.TagNumber(5)
  $core.bool hasSenderId() => $_has(4);
  @$pb.TagNumber(5)
  void clearSenderId() => clearField(5);

  @$pb.TagNumber(6)
  $fixnum.Int64 get groupId => $_getI64(5);
  @$pb.TagNumber(6)
  set groupId($fixnum.Int64 v) {
    $_setInt64(5, v);
  }

  @$pb.TagNumber(6)
  $core.bool hasGroupId() => $_has(5);
  @$pb.TagNumber(6)
  void clearGroupId() => clearField(6);

  @$pb.TagNumber(7)
  $core.bool get isSystemMessage => $_getBF(6);
  @$pb.TagNumber(7)
  set isSystemMessage($core.bool v) {
    $_setBool(6, v);
  }

  @$pb.TagNumber(7)
  $core.bool hasIsSystemMessage() => $_has(6);
  @$pb.TagNumber(7)
  void clearIsSystemMessage() => clearField(7);

  @$pb.TagNumber(8)
  $fixnum.Int64 get recipientId => $_getI64(7);
  @$pb.TagNumber(8)
  set recipientId($fixnum.Int64 v) {
    $_setInt64(7, v);
  }

  @$pb.TagNumber(8)
  $core.bool hasRecipientId() => $_has(7);
  @$pb.TagNumber(8)
  void clearRecipientId() => clearField(8);

  @$pb.TagNumber(9)
  $core.List<$core.List<$core.int>> get records => $_getList(8);

  @$pb.TagNumber(10)
  $core.int get sequenceId => $_getIZ(9);
  @$pb.TagNumber(10)
  set sequenceId($core.int v) {
    $_setSignedInt32(9, v);
  }

  @$pb.TagNumber(10)
  $core.bool hasSequenceId() => $_has(9);
  @$pb.TagNumber(10)
  void clearSequenceId() => clearField(10);

  @$pb.TagNumber(11)
  $fixnum.Int64 get preMessageId => $_getI64(10);
  @$pb.TagNumber(11)
  set preMessageId($fixnum.Int64 v) {
    $_setInt64(10, v);
  }

  @$pb.TagNumber(11)
  $core.bool hasPreMessageId() => $_has(10);
  @$pb.TagNumber(11)
  void clearPreMessageId() => clearField(11);

  @$pb.TagNumber(12)
  $core.List<$0.MessageReactionGroup> get reactionGroups => $_getList(11);

  @$pb.TagNumber(15)
  $core.List<$1.Value> get customAttributes => $_getList(12);
}

const _omitFieldNames = $core.bool.fromEnvironment('protobuf.omit_field_names');
const _omitMessageNames =
    $core.bool.fromEnvironment('protobuf.omit_message_names');
