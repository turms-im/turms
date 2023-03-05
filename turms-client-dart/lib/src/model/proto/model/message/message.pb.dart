///
//  Generated code. Do not modify.
//  source: model/message/message.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,constant_identifier_names,directives_ordering,library_prefixes,non_constant_identifier_names,prefer_final_fields,return_of_invalid_type,unnecessary_const,unnecessary_import,unnecessary_this,unused_import,unused_shown_name

import 'dart:core' as $core;

import 'package:fixnum/fixnum.dart' as $fixnum;
import 'package:protobuf/protobuf.dart' as $pb;

class Message extends $pb.GeneratedMessage {
  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      const $core.bool.fromEnvironment('protobuf.omit_message_names')
          ? ''
          : 'Message',
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
            : 'deliveryDate')
    ..aInt64(
        3,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'modificationDate')
    ..aOS(
        4,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'text')
    ..aInt64(
        5,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'senderId')
    ..aInt64(
        6,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'groupId')
    ..aOB(
        7,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'isSystemMessage')
    ..aInt64(
        8,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'recipientId')
    ..p<$core.List<$core.int>>(
        9,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'records',
        $pb.PbFieldType.PY)
    ..a<$core.int>(
        10,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'sequenceId',
        $pb.PbFieldType.O3)
    ..aInt64(
        11,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'preMessageId')
    ..hasRequiredFields = false;

  Message._() : super();
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
  }) {
    final _result = create();
    if (id != null) {
      _result.id = id;
    }
    if (deliveryDate != null) {
      _result.deliveryDate = deliveryDate;
    }
    if (modificationDate != null) {
      _result.modificationDate = modificationDate;
    }
    if (text != null) {
      _result.text = text;
    }
    if (senderId != null) {
      _result.senderId = senderId;
    }
    if (groupId != null) {
      _result.groupId = groupId;
    }
    if (isSystemMessage != null) {
      _result.isSystemMessage = isSystemMessage;
    }
    if (recipientId != null) {
      _result.recipientId = recipientId;
    }
    if (records != null) {
      _result.records.addAll(records);
    }
    if (sequenceId != null) {
      _result.sequenceId = sequenceId;
    }
    if (preMessageId != null) {
      _result.preMessageId = preMessageId;
    }
    return _result;
  }
  factory Message.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory Message.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
      'Will be removed in next major version')
  Message clone() => Message()..mergeFromMessage(this);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
      'Will be removed in next major version')
  Message copyWith(void Function(Message) updates) =>
      super.copyWith((message) => updates(message as Message))
          as Message; // ignore: deprecated_member_use
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
}
