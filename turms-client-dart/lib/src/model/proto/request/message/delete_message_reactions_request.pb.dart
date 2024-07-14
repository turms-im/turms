//
//  Generated code. Do not modify.
//  source: request/message/delete_message_reactions_request.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:core' as $core;

import 'package:fixnum/fixnum.dart' as $fixnum;
import 'package:protobuf/protobuf.dart' as $pb;

import '../../model/common/value.pb.dart' as $0;

class DeleteMessageReactionsRequest extends $pb.GeneratedMessage {
  factory DeleteMessageReactionsRequest({
    $fixnum.Int64? messageId,
    $core.Iterable<$core.int>? reactionTypes,
    $core.Iterable<$0.Value>? customAttributes,
  }) {
    final $result = create();
    if (messageId != null) {
      $result.messageId = messageId;
    }
    if (reactionTypes != null) {
      $result.reactionTypes.addAll(reactionTypes);
    }
    if (customAttributes != null) {
      $result.customAttributes.addAll(customAttributes);
    }
    return $result;
  }
  DeleteMessageReactionsRequest._() : super();
  factory DeleteMessageReactionsRequest.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory DeleteMessageReactionsRequest.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);

  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      _omitMessageNames ? '' : 'DeleteMessageReactionsRequest',
      package: const $pb.PackageName(_omitMessageNames ? '' : 'im.turms.proto'),
      createEmptyInstance: create)
    ..aInt64(1, _omitFieldNames ? '' : 'messageId')
    ..p<$core.int>(
        2, _omitFieldNames ? '' : 'reactionTypes', $pb.PbFieldType.K3)
    ..pc<$0.Value>(
        15, _omitFieldNames ? '' : 'customAttributes', $pb.PbFieldType.PM,
        subBuilder: $0.Value.create)
    ..hasRequiredFields = false;

  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
      'Will be removed in next major version')
  DeleteMessageReactionsRequest clone() =>
      DeleteMessageReactionsRequest()..mergeFromMessage(this);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
      'Will be removed in next major version')
  DeleteMessageReactionsRequest copyWith(
          void Function(DeleteMessageReactionsRequest) updates) =>
      super.copyWith(
              (message) => updates(message as DeleteMessageReactionsRequest))
          as DeleteMessageReactionsRequest;

  $pb.BuilderInfo get info_ => _i;

  @$core.pragma('dart2js:noInline')
  static DeleteMessageReactionsRequest create() =>
      DeleteMessageReactionsRequest._();
  DeleteMessageReactionsRequest createEmptyInstance() => create();
  static $pb.PbList<DeleteMessageReactionsRequest> createRepeated() =>
      $pb.PbList<DeleteMessageReactionsRequest>();
  @$core.pragma('dart2js:noInline')
  static DeleteMessageReactionsRequest getDefault() => _defaultInstance ??=
      $pb.GeneratedMessage.$_defaultFor<DeleteMessageReactionsRequest>(create);
  static DeleteMessageReactionsRequest? _defaultInstance;

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
  $core.List<$core.int> get reactionTypes => $_getList(1);

  @$pb.TagNumber(15)
  $core.List<$0.Value> get customAttributes => $_getList(2);
}

const _omitFieldNames = $core.bool.fromEnvironment('protobuf.omit_field_names');
const _omitMessageNames =
    $core.bool.fromEnvironment('protobuf.omit_message_names');
