///
//  Generated code. Do not modify.
//  source: model/message/messages_with_total.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,constant_identifier_names,directives_ordering,library_prefixes,non_constant_identifier_names,prefer_final_fields,return_of_invalid_type,unnecessary_const,unnecessary_import,unnecessary_this,unused_import,unused_shown_name

import 'dart:core' as $core;

import 'package:fixnum/fixnum.dart' as $fixnum;
import 'package:protobuf/protobuf.dart' as $pb;

import 'message.pb.dart' as $0;

class MessagesWithTotal extends $pb.GeneratedMessage {
  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      const $core.bool.fromEnvironment('protobuf.omit_message_names')
          ? ''
          : 'MessagesWithTotal',
      package: const $pb.PackageName(
          const $core.bool.fromEnvironment('protobuf.omit_message_names')
              ? ''
              : 'im.turms.proto'),
      createEmptyInstance: create)
    ..a<$core.int>(
        1,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'total',
        $pb.PbFieldType.O3)
    ..aOB(
        2,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'isGroupMessage')
    ..aInt64(
        3,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'fromId')
    ..pc<$0.Message>(
        4,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'messages',
        $pb.PbFieldType.PM,
        subBuilder: $0.Message.create)
    ..hasRequiredFields = false;

  MessagesWithTotal._() : super();
  factory MessagesWithTotal({
    $core.int? total,
    $core.bool? isGroupMessage,
    $fixnum.Int64? fromId,
    $core.Iterable<$0.Message>? messages,
  }) {
    final _result = create();
    if (total != null) {
      _result.total = total;
    }
    if (isGroupMessage != null) {
      _result.isGroupMessage = isGroupMessage;
    }
    if (fromId != null) {
      _result.fromId = fromId;
    }
    if (messages != null) {
      _result.messages.addAll(messages);
    }
    return _result;
  }
  factory MessagesWithTotal.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory MessagesWithTotal.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
      'Will be removed in next major version')
  MessagesWithTotal clone() => MessagesWithTotal()..mergeFromMessage(this);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
      'Will be removed in next major version')
  MessagesWithTotal copyWith(void Function(MessagesWithTotal) updates) =>
      super.copyWith((message) => updates(message as MessagesWithTotal))
          as MessagesWithTotal; // ignore: deprecated_member_use
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
