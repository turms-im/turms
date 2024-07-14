//
//  Generated code. Do not modify.
//  source: request/conversation/update_conversation_settings_request.proto
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

class UpdateConversationSettingsRequest extends $pb.GeneratedMessage {
  factory UpdateConversationSettingsRequest({
    $fixnum.Int64? userId,
    $fixnum.Int64? groupId,
    $core.Map<$core.String, $0.Value>? settings,
    $core.Iterable<$0.Value>? customAttributes,
  }) {
    final $result = create();
    if (userId != null) {
      $result.userId = userId;
    }
    if (groupId != null) {
      $result.groupId = groupId;
    }
    if (settings != null) {
      $result.settings.addAll(settings);
    }
    if (customAttributes != null) {
      $result.customAttributes.addAll(customAttributes);
    }
    return $result;
  }
  UpdateConversationSettingsRequest._() : super();
  factory UpdateConversationSettingsRequest.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory UpdateConversationSettingsRequest.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);

  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      _omitMessageNames ? '' : 'UpdateConversationSettingsRequest',
      package: const $pb.PackageName(_omitMessageNames ? '' : 'im.turms.proto'),
      createEmptyInstance: create)
    ..aInt64(1, _omitFieldNames ? '' : 'userId')
    ..aInt64(2, _omitFieldNames ? '' : 'groupId')
    ..m<$core.String, $0.Value>(3, _omitFieldNames ? '' : 'settings',
        entryClassName: 'UpdateConversationSettingsRequest.SettingsEntry',
        keyFieldType: $pb.PbFieldType.OS,
        valueFieldType: $pb.PbFieldType.OM,
        valueCreator: $0.Value.create,
        valueDefaultOrMaker: $0.Value.getDefault,
        packageName: const $pb.PackageName('im.turms.proto'))
    ..pc<$0.Value>(
        15, _omitFieldNames ? '' : 'customAttributes', $pb.PbFieldType.PM,
        subBuilder: $0.Value.create)
    ..hasRequiredFields = false;

  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
      'Will be removed in next major version')
  UpdateConversationSettingsRequest clone() =>
      UpdateConversationSettingsRequest()..mergeFromMessage(this);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
      'Will be removed in next major version')
  UpdateConversationSettingsRequest copyWith(
          void Function(UpdateConversationSettingsRequest) updates) =>
      super.copyWith((message) =>
              updates(message as UpdateConversationSettingsRequest))
          as UpdateConversationSettingsRequest;

  $pb.BuilderInfo get info_ => _i;

  @$core.pragma('dart2js:noInline')
  static UpdateConversationSettingsRequest create() =>
      UpdateConversationSettingsRequest._();
  UpdateConversationSettingsRequest createEmptyInstance() => create();
  static $pb.PbList<UpdateConversationSettingsRequest> createRepeated() =>
      $pb.PbList<UpdateConversationSettingsRequest>();
  @$core.pragma('dart2js:noInline')
  static UpdateConversationSettingsRequest getDefault() => _defaultInstance ??=
      $pb.GeneratedMessage.$_defaultFor<UpdateConversationSettingsRequest>(
          create);
  static UpdateConversationSettingsRequest? _defaultInstance;

  /// Query filter
  @$pb.TagNumber(1)
  $fixnum.Int64 get userId => $_getI64(0);
  @$pb.TagNumber(1)
  set userId($fixnum.Int64 v) {
    $_setInt64(0, v);
  }

  @$pb.TagNumber(1)
  $core.bool hasUserId() => $_has(0);
  @$pb.TagNumber(1)
  void clearUserId() => clearField(1);

  @$pb.TagNumber(2)
  $fixnum.Int64 get groupId => $_getI64(1);
  @$pb.TagNumber(2)
  set groupId($fixnum.Int64 v) {
    $_setInt64(1, v);
  }

  @$pb.TagNumber(2)
  $core.bool hasGroupId() => $_has(1);
  @$pb.TagNumber(2)
  void clearGroupId() => clearField(2);

  /// Update
  @$pb.TagNumber(3)
  $core.Map<$core.String, $0.Value> get settings => $_getMap(2);

  @$pb.TagNumber(15)
  $core.List<$0.Value> get customAttributes => $_getList(3);
}

const _omitFieldNames = $core.bool.fromEnvironment('protobuf.omit_field_names');
const _omitMessageNames =
    $core.bool.fromEnvironment('protobuf.omit_message_names');
