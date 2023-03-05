///
//  Generated code. Do not modify.
//  source: request/message/create_message_request.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,constant_identifier_names,directives_ordering,library_prefixes,non_constant_identifier_names,prefer_final_fields,return_of_invalid_type,unnecessary_const,unnecessary_import,unnecessary_this,unused_import,unused_shown_name

import 'dart:core' as $core;

import 'package:fixnum/fixnum.dart' as $fixnum;
import 'package:protobuf/protobuf.dart' as $pb;

class CreateMessageRequest extends $pb.GeneratedMessage {
  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      const $core.bool.fromEnvironment('protobuf.omit_message_names')
          ? ''
          : 'CreateMessageRequest',
      package: const $pb.PackageName(
          const $core.bool.fromEnvironment('protobuf.omit_message_names')
              ? ''
              : 'im.turms.proto'),
      createEmptyInstance: create)
    ..aInt64(
        1,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'messageId')
    ..aOB(
        2,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'isSystemMessage')
    ..aInt64(
        3,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'groupId')
    ..aInt64(
        4,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'recipientId')
    ..aInt64(
        5,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'deliveryDate')
    ..aOS(
        6,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'text')
    ..p<$core.List<$core.int>>(
        7,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'records',
        $pb.PbFieldType.PY)
    ..a<$core.int>(
        8,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'burnAfter',
        $pb.PbFieldType.O3)
    ..aInt64(
        9,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'preMessageId')
    ..hasRequiredFields = false;

  CreateMessageRequest._() : super();
  factory CreateMessageRequest({
    $fixnum.Int64? messageId,
    $core.bool? isSystemMessage,
    $fixnum.Int64? groupId,
    $fixnum.Int64? recipientId,
    $fixnum.Int64? deliveryDate,
    $core.String? text,
    $core.Iterable<$core.List<$core.int>>? records,
    $core.int? burnAfter,
    $fixnum.Int64? preMessageId,
  }) {
    final _result = create();
    if (messageId != null) {
      _result.messageId = messageId;
    }
    if (isSystemMessage != null) {
      _result.isSystemMessage = isSystemMessage;
    }
    if (groupId != null) {
      _result.groupId = groupId;
    }
    if (recipientId != null) {
      _result.recipientId = recipientId;
    }
    if (deliveryDate != null) {
      _result.deliveryDate = deliveryDate;
    }
    if (text != null) {
      _result.text = text;
    }
    if (records != null) {
      _result.records.addAll(records);
    }
    if (burnAfter != null) {
      _result.burnAfter = burnAfter;
    }
    if (preMessageId != null) {
      _result.preMessageId = preMessageId;
    }
    return _result;
  }
  factory CreateMessageRequest.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory CreateMessageRequest.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
      'Will be removed in next major version')
  CreateMessageRequest clone() =>
      CreateMessageRequest()..mergeFromMessage(this);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
      'Will be removed in next major version')
  CreateMessageRequest copyWith(void Function(CreateMessageRequest) updates) =>
      super.copyWith((message) => updates(message as CreateMessageRequest))
          as CreateMessageRequest; // ignore: deprecated_member_use
  $pb.BuilderInfo get info_ => _i;
  @$core.pragma('dart2js:noInline')
  static CreateMessageRequest create() => CreateMessageRequest._();
  CreateMessageRequest createEmptyInstance() => create();
  static $pb.PbList<CreateMessageRequest> createRepeated() =>
      $pb.PbList<CreateMessageRequest>();
  @$core.pragma('dart2js:noInline')
  static CreateMessageRequest getDefault() => _defaultInstance ??=
      $pb.GeneratedMessage.$_defaultFor<CreateMessageRequest>(create);
  static CreateMessageRequest? _defaultInstance;

  @$pb.TagNumber(1)
  $fixnum.Int64 get messageId => $_getI64(0);
  @$pb.TagNumber(1)
  set messageId($fixnum.Int64 v) {
    $_setInt64(0, v);
  }

  @$pb.TagNumber(1)
  $core.bool hasMessageId() => $_has(0);
  @$pb.TagNumber(1)
  void clearMessageId() => clearField(1);

  @$pb.TagNumber(2)
  $core.bool get isSystemMessage => $_getBF(1);
  @$pb.TagNumber(2)
  set isSystemMessage($core.bool v) {
    $_setBool(1, v);
  }

  @$pb.TagNumber(2)
  $core.bool hasIsSystemMessage() => $_has(1);
  @$pb.TagNumber(2)
  void clearIsSystemMessage() => clearField(2);

  @$pb.TagNumber(3)
  $fixnum.Int64 get groupId => $_getI64(2);
  @$pb.TagNumber(3)
  set groupId($fixnum.Int64 v) {
    $_setInt64(2, v);
  }

  @$pb.TagNumber(3)
  $core.bool hasGroupId() => $_has(2);
  @$pb.TagNumber(3)
  void clearGroupId() => clearField(3);

  @$pb.TagNumber(4)
  $fixnum.Int64 get recipientId => $_getI64(3);
  @$pb.TagNumber(4)
  set recipientId($fixnum.Int64 v) {
    $_setInt64(3, v);
  }

  @$pb.TagNumber(4)
  $core.bool hasRecipientId() => $_has(3);
  @$pb.TagNumber(4)
  void clearRecipientId() => clearField(4);

  @$pb.TagNumber(5)
  $fixnum.Int64 get deliveryDate => $_getI64(4);
  @$pb.TagNumber(5)
  set deliveryDate($fixnum.Int64 v) {
    $_setInt64(4, v);
  }

  @$pb.TagNumber(5)
  $core.bool hasDeliveryDate() => $_has(4);
  @$pb.TagNumber(5)
  void clearDeliveryDate() => clearField(5);

  @$pb.TagNumber(6)
  $core.String get text => $_getSZ(5);
  @$pb.TagNumber(6)
  set text($core.String v) {
    $_setString(5, v);
  }

  @$pb.TagNumber(6)
  $core.bool hasText() => $_has(5);
  @$pb.TagNumber(6)
  void clearText() => clearField(6);

  @$pb.TagNumber(7)
  $core.List<$core.List<$core.int>> get records => $_getList(6);

  @$pb.TagNumber(8)
  $core.int get burnAfter => $_getIZ(7);
  @$pb.TagNumber(8)
  set burnAfter($core.int v) {
    $_setSignedInt32(7, v);
  }

  @$pb.TagNumber(8)
  $core.bool hasBurnAfter() => $_has(7);
  @$pb.TagNumber(8)
  void clearBurnAfter() => clearField(8);

  @$pb.TagNumber(9)
  $fixnum.Int64 get preMessageId => $_getI64(8);
  @$pb.TagNumber(9)
  set preMessageId($fixnum.Int64 v) {
    $_setInt64(8, v);
  }

  @$pb.TagNumber(9)
  $core.bool hasPreMessageId() => $_has(8);
  @$pb.TagNumber(9)
  void clearPreMessageId() => clearField(9);
}
