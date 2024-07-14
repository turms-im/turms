//
//  Generated code. Do not modify.
//  source: request/user/delete_user_settings_request.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:core' as $core;

import 'package:protobuf/protobuf.dart' as $pb;

import '../../model/common/value.pb.dart' as $0;

class DeleteUserSettingsRequest extends $pb.GeneratedMessage {
  factory DeleteUserSettingsRequest({
    $core.Iterable<$core.String>? names,
    $core.Iterable<$0.Value>? customAttributes,
  }) {
    final $result = create();
    if (names != null) {
      $result.names.addAll(names);
    }
    if (customAttributes != null) {
      $result.customAttributes.addAll(customAttributes);
    }
    return $result;
  }
  DeleteUserSettingsRequest._() : super();
  factory DeleteUserSettingsRequest.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory DeleteUserSettingsRequest.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);

  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      _omitMessageNames ? '' : 'DeleteUserSettingsRequest',
      package: const $pb.PackageName(_omitMessageNames ? '' : 'im.turms.proto'),
      createEmptyInstance: create)
    ..pPS(1, _omitFieldNames ? '' : 'names')
    ..pc<$0.Value>(
        15, _omitFieldNames ? '' : 'customAttributes', $pb.PbFieldType.PM,
        subBuilder: $0.Value.create)
    ..hasRequiredFields = false;

  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
      'Will be removed in next major version')
  DeleteUserSettingsRequest clone() =>
      DeleteUserSettingsRequest()..mergeFromMessage(this);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
      'Will be removed in next major version')
  DeleteUserSettingsRequest copyWith(
          void Function(DeleteUserSettingsRequest) updates) =>
      super.copyWith((message) => updates(message as DeleteUserSettingsRequest))
          as DeleteUserSettingsRequest;

  $pb.BuilderInfo get info_ => _i;

  @$core.pragma('dart2js:noInline')
  static DeleteUserSettingsRequest create() => DeleteUserSettingsRequest._();
  DeleteUserSettingsRequest createEmptyInstance() => create();
  static $pb.PbList<DeleteUserSettingsRequest> createRepeated() =>
      $pb.PbList<DeleteUserSettingsRequest>();
  @$core.pragma('dart2js:noInline')
  static DeleteUserSettingsRequest getDefault() => _defaultInstance ??=
      $pb.GeneratedMessage.$_defaultFor<DeleteUserSettingsRequest>(create);
  static DeleteUserSettingsRequest? _defaultInstance;

  @$pb.TagNumber(1)
  $core.List<$core.String> get names => $_getList(0);

  @$pb.TagNumber(15)
  $core.List<$0.Value> get customAttributes => $_getList(1);
}

const _omitFieldNames = $core.bool.fromEnvironment('protobuf.omit_field_names');
const _omitMessageNames =
    $core.bool.fromEnvironment('protobuf.omit_message_names');
