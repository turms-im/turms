//
//  Generated code. Do not modify.
//  source: request/user/update_user_settings_request.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:core' as $core;

import 'package:protobuf/protobuf.dart' as $pb;

import '../../model/common/value.pb.dart' as $0;

class UpdateUserSettingsRequest extends $pb.GeneratedMessage {
  factory UpdateUserSettingsRequest({
    $core.Map<$core.String, $0.Value>? settings,
    $core.Iterable<$0.Value>? customAttributes,
  }) {
    final $result = create();
    if (settings != null) {
      $result.settings.addAll(settings);
    }
    if (customAttributes != null) {
      $result.customAttributes.addAll(customAttributes);
    }
    return $result;
  }
  UpdateUserSettingsRequest._() : super();
  factory UpdateUserSettingsRequest.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory UpdateUserSettingsRequest.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);

  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      _omitMessageNames ? '' : 'UpdateUserSettingsRequest',
      package: const $pb.PackageName(_omitMessageNames ? '' : 'im.turms.proto'),
      createEmptyInstance: create)
    ..m<$core.String, $0.Value>(1, _omitFieldNames ? '' : 'settings',
        entryClassName: 'UpdateUserSettingsRequest.SettingsEntry',
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
  UpdateUserSettingsRequest clone() =>
      UpdateUserSettingsRequest()..mergeFromMessage(this);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
      'Will be removed in next major version')
  UpdateUserSettingsRequest copyWith(
          void Function(UpdateUserSettingsRequest) updates) =>
      super.copyWith((message) => updates(message as UpdateUserSettingsRequest))
          as UpdateUserSettingsRequest;

  $pb.BuilderInfo get info_ => _i;

  @$core.pragma('dart2js:noInline')
  static UpdateUserSettingsRequest create() => UpdateUserSettingsRequest._();
  UpdateUserSettingsRequest createEmptyInstance() => create();
  static $pb.PbList<UpdateUserSettingsRequest> createRepeated() =>
      $pb.PbList<UpdateUserSettingsRequest>();
  @$core.pragma('dart2js:noInline')
  static UpdateUserSettingsRequest getDefault() => _defaultInstance ??=
      $pb.GeneratedMessage.$_defaultFor<UpdateUserSettingsRequest>(create);
  static UpdateUserSettingsRequest? _defaultInstance;

  /// Update
  @$pb.TagNumber(1)
  $core.Map<$core.String, $0.Value> get settings => $_getMap(0);

  @$pb.TagNumber(15)
  $core.List<$0.Value> get customAttributes => $_getList(1);
}

const _omitFieldNames = $core.bool.fromEnvironment('protobuf.omit_field_names');
const _omitMessageNames =
    $core.bool.fromEnvironment('protobuf.omit_message_names');
