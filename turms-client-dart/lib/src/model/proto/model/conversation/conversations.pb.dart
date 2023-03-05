///
//  Generated code. Do not modify.
//  source: model/conversation/conversations.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,constant_identifier_names,directives_ordering,library_prefixes,non_constant_identifier_names,prefer_final_fields,return_of_invalid_type,unnecessary_const,unnecessary_import,unnecessary_this,unused_import,unused_shown_name

import 'dart:core' as $core;

import 'package:protobuf/protobuf.dart' as $pb;

import 'private_conversation.pb.dart' as $0;
import 'group_conversation.pb.dart' as $1;

class Conversations extends $pb.GeneratedMessage {
  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      const $core.bool.fromEnvironment('protobuf.omit_message_names')
          ? ''
          : 'Conversations',
      package: const $pb.PackageName(
          const $core.bool.fromEnvironment('protobuf.omit_message_names')
              ? ''
              : 'im.turms.proto'),
      createEmptyInstance: create)
    ..pc<$0.PrivateConversation>(
        1,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'privateConversations',
        $pb.PbFieldType.PM,
        subBuilder: $0.PrivateConversation.create)
    ..pc<$1.GroupConversation>(
        2,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'groupConversations',
        $pb.PbFieldType.PM,
        subBuilder: $1.GroupConversation.create)
    ..hasRequiredFields = false;

  Conversations._() : super();
  factory Conversations({
    $core.Iterable<$0.PrivateConversation>? privateConversations,
    $core.Iterable<$1.GroupConversation>? groupConversations,
  }) {
    final _result = create();
    if (privateConversations != null) {
      _result.privateConversations.addAll(privateConversations);
    }
    if (groupConversations != null) {
      _result.groupConversations.addAll(groupConversations);
    }
    return _result;
  }
  factory Conversations.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory Conversations.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
      'Will be removed in next major version')
  Conversations clone() => Conversations()..mergeFromMessage(this);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
      'Will be removed in next major version')
  Conversations copyWith(void Function(Conversations) updates) =>
      super.copyWith((message) => updates(message as Conversations))
          as Conversations; // ignore: deprecated_member_use
  $pb.BuilderInfo get info_ => _i;
  @$core.pragma('dart2js:noInline')
  static Conversations create() => Conversations._();
  Conversations createEmptyInstance() => create();
  static $pb.PbList<Conversations> createRepeated() =>
      $pb.PbList<Conversations>();
  @$core.pragma('dart2js:noInline')
  static Conversations getDefault() => _defaultInstance ??=
      $pb.GeneratedMessage.$_defaultFor<Conversations>(create);
  static Conversations? _defaultInstance;

  @$pb.TagNumber(1)
  $core.List<$0.PrivateConversation> get privateConversations => $_getList(0);

  @$pb.TagNumber(2)
  $core.List<$1.GroupConversation> get groupConversations => $_getList(1);
}
