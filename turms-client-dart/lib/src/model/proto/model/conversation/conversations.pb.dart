//
//  Generated code. Do not modify.
//  source: model/conversation/conversations.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:core' as $core;

import 'package:protobuf/protobuf.dart' as $pb;

import 'group_conversation.pb.dart' as $1;
import 'private_conversation.pb.dart' as $0;

class Conversations extends $pb.GeneratedMessage {
  factory Conversations({
    $core.Iterable<$0.PrivateConversation>? privateConversations,
    $core.Iterable<$1.GroupConversation>? groupConversations,
  }) {
    final $result = create();
    if (privateConversations != null) {
      $result.privateConversations.addAll(privateConversations);
    }
    if (groupConversations != null) {
      $result.groupConversations.addAll(groupConversations);
    }
    return $result;
  }
  Conversations._() : super();
  factory Conversations.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory Conversations.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);

  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      _omitMessageNames ? '' : 'Conversations',
      package: const $pb.PackageName(_omitMessageNames ? '' : 'im.turms.proto'),
      createEmptyInstance: create)
    ..pc<$0.PrivateConversation>(
        1, _omitFieldNames ? '' : 'privateConversations', $pb.PbFieldType.PM,
        subBuilder: $0.PrivateConversation.create)
    ..pc<$1.GroupConversation>(
        2, _omitFieldNames ? '' : 'groupConversations', $pb.PbFieldType.PM,
        subBuilder: $1.GroupConversation.create)
    ..hasRequiredFields = false;

  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
      'Will be removed in next major version')
  Conversations clone() => Conversations()..mergeFromMessage(this);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
      'Will be removed in next major version')
  Conversations copyWith(void Function(Conversations) updates) =>
      super.copyWith((message) => updates(message as Conversations))
          as Conversations;

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

const _omitFieldNames = $core.bool.fromEnvironment('protobuf.omit_field_names');
const _omitMessageNames =
    $core.bool.fromEnvironment('protobuf.omit_message_names');
