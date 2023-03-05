///
//  Generated code. Do not modify.
//  source: model/message/messages_with_total_list.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,constant_identifier_names,directives_ordering,library_prefixes,non_constant_identifier_names,prefer_final_fields,return_of_invalid_type,unnecessary_const,unnecessary_import,unnecessary_this,unused_import,unused_shown_name

import 'dart:core' as $core;

import 'package:protobuf/protobuf.dart' as $pb;

import 'messages_with_total.pb.dart' as $0;

class MessagesWithTotalList extends $pb.GeneratedMessage {
  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      const $core.bool.fromEnvironment('protobuf.omit_message_names')
          ? ''
          : 'MessagesWithTotalList',
      package: const $pb.PackageName(
          const $core.bool.fromEnvironment('protobuf.omit_message_names')
              ? ''
              : 'im.turms.proto'),
      createEmptyInstance: create)
    ..pc<$0.MessagesWithTotal>(
        1,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'messagesWithTotalList',
        $pb.PbFieldType.PM,
        subBuilder: $0.MessagesWithTotal.create)
    ..hasRequiredFields = false;

  MessagesWithTotalList._() : super();
  factory MessagesWithTotalList({
    $core.Iterable<$0.MessagesWithTotal>? messagesWithTotalList,
  }) {
    final _result = create();
    if (messagesWithTotalList != null) {
      _result.messagesWithTotalList.addAll(messagesWithTotalList);
    }
    return _result;
  }
  factory MessagesWithTotalList.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory MessagesWithTotalList.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);
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
          as MessagesWithTotalList; // ignore: deprecated_member_use
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
