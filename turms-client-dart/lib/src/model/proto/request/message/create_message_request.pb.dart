//
//  Generated code. Do not modify.
//  source: request/message/create_message_request.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:core' as $core;

import 'package:fixnum/fixnum.dart' as $fixnum;
import 'package:protobuf/protobuf.dart' as $pb;

class CreateMessageRequest extends $pb.GeneratedMessage {
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
    final $result = create();
    if (messageId != null) {
      $result.messageId = messageId;
    }
    if (isSystemMessage != null) {
      $result.isSystemMessage = isSystemMessage;
    }
    if (groupId != null) {
      $result.groupId = groupId;
    }
    if (recipientId != null) {
      $result.recipientId = recipientId;
    }
    if (deliveryDate != null) {
      $result.deliveryDate = deliveryDate;
    }
    if (text != null) {
      $result.text = text;
    }
    if (records != null) {
      $result.records.addAll(records);
    }
    if (burnAfter != null) {
      $result.burnAfter = burnAfter;
    }
    if (preMessageId != null) {
      $result.preMessageId = preMessageId;
    }
    return $result;
  }
  CreateMessageRequest._() : super();
  factory CreateMessageRequest.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory CreateMessageRequest.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);

  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      _omitMessageNames ? '' : 'CreateMessageRequest',
      package: const $pb.PackageName(_omitMessageNames ? '' : 'im.turms.proto'),
      createEmptyInstance: create)
    ..aInt64(1, _omitFieldNames ? '' : 'messageId')
    ..aOB(2, _omitFieldNames ? '' : 'isSystemMessage')
    ..aInt64(3, _omitFieldNames ? '' : 'groupId')
    ..aInt64(4, _omitFieldNames ? '' : 'recipientId')
    ..aInt64(5, _omitFieldNames ? '' : 'deliveryDate')
    ..aOS(6, _omitFieldNames ? '' : 'text')
    ..p<$core.List<$core.int>>(
        7, _omitFieldNames ? '' : 'records', $pb.PbFieldType.PY)
    ..a<$core.int>(8, _omitFieldNames ? '' : 'burnAfter', $pb.PbFieldType.O3)
    ..aInt64(9, _omitFieldNames ? '' : 'preMessageId')
    ..hasRequiredFields = false;

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
          as CreateMessageRequest;

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

  /// is_system_message can only be true if the user is an administrator,
  /// or turms server will return an error
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

const _omitFieldNames = $core.bool.fromEnvironment('protobuf.omit_field_names');
const _omitMessageNames =
    $core.bool.fromEnvironment('protobuf.omit_message_names');
