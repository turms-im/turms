//
//  Generated code. Do not modify.
//  source: model/conversation/conversation_settings_list.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:core' as $core;

import 'package:protobuf/protobuf.dart' as $pb;

import 'conversation_settings.pb.dart' as $0;

class ConversationSettingsList extends $pb.GeneratedMessage {
  factory ConversationSettingsList({
    $core.Iterable<$0.ConversationSettings>? conversationSettingsList,
  }) {
    final $result = create();
    if (conversationSettingsList != null) {
      $result.conversationSettingsList.addAll(conversationSettingsList);
    }
    return $result;
  }
  ConversationSettingsList._() : super();
  factory ConversationSettingsList.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory ConversationSettingsList.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);

  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      _omitMessageNames ? '' : 'ConversationSettingsList',
      package: const $pb.PackageName(_omitMessageNames ? '' : 'im.turms.proto'),
      createEmptyInstance: create)
    ..pc<$0.ConversationSettings>(1,
        _omitFieldNames ? '' : 'conversationSettingsList', $pb.PbFieldType.PM,
        subBuilder: $0.ConversationSettings.create)
    ..hasRequiredFields = false;

  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
      'Will be removed in next major version')
  ConversationSettingsList clone() =>
      ConversationSettingsList()..mergeFromMessage(this);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
      'Will be removed in next major version')
  ConversationSettingsList copyWith(
          void Function(ConversationSettingsList) updates) =>
      super.copyWith((message) => updates(message as ConversationSettingsList))
          as ConversationSettingsList;

  $pb.BuilderInfo get info_ => _i;

  @$core.pragma('dart2js:noInline')
  static ConversationSettingsList create() => ConversationSettingsList._();
  ConversationSettingsList createEmptyInstance() => create();
  static $pb.PbList<ConversationSettingsList> createRepeated() =>
      $pb.PbList<ConversationSettingsList>();
  @$core.pragma('dart2js:noInline')
  static ConversationSettingsList getDefault() => _defaultInstance ??=
      $pb.GeneratedMessage.$_defaultFor<ConversationSettingsList>(create);
  static ConversationSettingsList? _defaultInstance;

  @$pb.TagNumber(1)
  $core.List<$0.ConversationSettings> get conversationSettingsList =>
      $_getList(0);
}

const _omitFieldNames = $core.bool.fromEnvironment('protobuf.omit_field_names');
const _omitMessageNames =
    $core.bool.fromEnvironment('protobuf.omit_message_names');
