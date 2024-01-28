//
//  Generated code. Do not modify.
//  source: model/message/messages_with_total_list.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:core' as $core;

import 'package:protobuf/protobuf.dart' as $pb;

import 'messages_with_total.pb.dart' as $0;

class MessagesWithTotalList extends $pb.GeneratedMessage {
  factory MessagesWithTotalList({
    $core.Iterable<$0.MessagesWithTotal>? messagesWithTotalList,
  }) {
    final $result = create();
    if (messagesWithTotalList != null) {
      $result.messagesWithTotalList.addAll(messagesWithTotalList);
    }
    return $result;
  }
  MessagesWithTotalList._() : super();
  factory MessagesWithTotalList.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory MessagesWithTotalList.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);

  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      _omitMessageNames ? '' : 'MessagesWithTotalList',
      package: const $pb.PackageName(_omitMessageNames ? '' : 'im.turms.proto'),
      createEmptyInstance: create)
    ..pc<$0.MessagesWithTotal>(
        1, _omitFieldNames ? '' : 'messagesWithTotalList', $pb.PbFieldType.PM,
        subBuilder: $0.MessagesWithTotal.create)
    ..hasRequiredFields = false;

  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
      'Will be removed in next major version')
  MessagesWithTotalList clone() =>
      MessagesWithTotalList()..mergeFromMessage(this);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
      'Will be removed in next major version')
  MessagesWithTotalList copyWith(
          void Function(MessagesWithTotalList) updates) =>
      super.copyWith((message) => updates(message as MessagesWithTotalList))
          as MessagesWithTotalList;

  $pb.BuilderInfo get info_ => _i;

  @$core.pragma('dart2js:noInline')
  static MessagesWithTotalList create() => MessagesWithTotalList._();
  MessagesWithTotalList createEmptyInstance() => create();
  static $pb.PbList<MessagesWithTotalList> createRepeated() =>
      $pb.PbList<MessagesWithTotalList>();
  @$core.pragma('dart2js:noInline')
  static MessagesWithTotalList getDefault() => _defaultInstance ??=
      $pb.GeneratedMessage.$_defaultFor<MessagesWithTotalList>(create);
  static MessagesWithTotalList? _defaultInstance;

  @$pb.TagNumber(1)
  $core.List<$0.MessagesWithTotal> get messagesWithTotalList => $_getList(0);
}

const _omitFieldNames = $core.bool.fromEnvironment('protobuf.omit_field_names');
const _omitMessageNames =
    $core.bool.fromEnvironment('protobuf.omit_message_names');
