//
//  Generated code. Do not modify.
//  source: model/message/messages_with_total.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:core' as $core;

import 'package:fixnum/fixnum.dart' as $fixnum;
import 'package:protobuf/protobuf.dart' as $pb;

import 'message.pb.dart' as $0;

class MessagesWithTotal extends $pb.GeneratedMessage {
  factory MessagesWithTotal({
    $core.int? total,
    $core.bool? isGroupMessage,
    $fixnum.Int64? fromId,
    $core.Iterable<$0.Message>? messages,
  }) {
    final $result = create();
    if (total != null) {
      $result.total = total;
    }
    if (isGroupMessage != null) {
      $result.isGroupMessage = isGroupMessage;
    }
    if (fromId != null) {
      $result.fromId = fromId;
    }
    if (messages != null) {
      $result.messages.addAll(messages);
    }
    return $result;
  }
  MessagesWithTotal._() : super();
  factory MessagesWithTotal.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory MessagesWithTotal.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);

  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      _omitMessageNames ? '' : 'MessagesWithTotal',
      package: const $pb.PackageName(_omitMessageNames ? '' : 'im.turms.proto'),
      createEmptyInstance: create)
    ..a<$core.int>(1, _omitFieldNames ? '' : 'total', $pb.PbFieldType.O3)
    ..aOB(2, _omitFieldNames ? '' : 'isGroupMessage')
    ..aInt64(3, _omitFieldNames ? '' : 'fromId')
    ..pc<$0.Message>(4, _omitFieldNames ? '' : 'messages', $pb.PbFieldType.PM,
        subBuilder: $0.Message.create)
    ..hasRequiredFields = false;

  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
      'Will be removed in next major version')
  MessagesWithTotal clone() => MessagesWithTotal()..mergeFromMessage(this);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
      'Will be removed in next major version')
  MessagesWithTotal copyWith(void Function(MessagesWithTotal) updates) =>
      super.copyWith((message) => updates(message as MessagesWithTotal))
          as MessagesWithTotal;

  $pb.BuilderInfo get info_ => _i;

  @$core.pragma('dart2js:noInline')
  static MessagesWithTotal create() => MessagesWithTotal._();
  MessagesWithTotal createEmptyInstance() => create();
  static $pb.PbList<MessagesWithTotal> createRepeated() =>
      $pb.PbList<MessagesWithTotal>();
  @$core.pragma('dart2js:noInline')
  static MessagesWithTotal getDefault() => _defaultInstance ??=
      $pb.GeneratedMessage.$_defaultFor<MessagesWithTotal>(create);
  static MessagesWithTotal? _defaultInstance;

  @$pb.TagNumber(1)
  $core.int get total => $_getIZ(0);
  @$pb.TagNumber(1)
  set total($core.int v) {
    $_setSignedInt32(0, v);
  }

  @$pb.TagNumber(1)
  $core.bool hasTotal() => $_has(0);
  @$pb.TagNumber(1)
  void clearTotal() => clearField(1);

  @$pb.TagNumber(2)
  $core.bool get isGroupMessage => $_getBF(1);
  @$pb.TagNumber(2)
  set isGroupMessage($core.bool v) {
    $_setBool(1, v);
  }

  @$pb.TagNumber(2)
  $core.bool hasIsGroupMessage() => $_has(1);
  @$pb.TagNumber(2)
  void clearIsGroupMessage() => clearField(2);

  @$pb.TagNumber(3)
  $fixnum.Int64 get fromId => $_getI64(2);
  @$pb.TagNumber(3)
  set fromId($fixnum.Int64 v) {
    $_setInt64(2, v);
  }

  @$pb.TagNumber(3)
  $core.bool hasFromId() => $_has(2);
  @$pb.TagNumber(3)
  void clearFromId() => clearField(3);

  @$pb.TagNumber(4)
  $core.List<$0.Message> get messages => $_getList(3);
}

const _omitFieldNames = $core.bool.fromEnvironment('protobuf.omit_field_names');
const _omitMessageNames =
    $core.bool.fromEnvironment('protobuf.omit_message_names');
