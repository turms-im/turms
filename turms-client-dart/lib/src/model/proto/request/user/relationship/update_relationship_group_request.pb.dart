//
//  Generated code. Do not modify.
//  source: request/user/relationship/update_relationship_group_request.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:core' as $core;

import 'package:protobuf/protobuf.dart' as $pb;

class UpdateRelationshipGroupRequest extends $pb.GeneratedMessage {
  factory UpdateRelationshipGroupRequest({
    $core.int? groupIndex,
    $core.String? newName,
  }) {
    final $result = create();
    if (groupIndex != null) {
      $result.groupIndex = groupIndex;
    }
    if (newName != null) {
      $result.newName = newName;
    }
    return $result;
  }
  UpdateRelationshipGroupRequest._() : super();
  factory UpdateRelationshipGroupRequest.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory UpdateRelationshipGroupRequest.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);

  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      _omitMessageNames ? '' : 'UpdateRelationshipGroupRequest',
      package: const $pb.PackageName(_omitMessageNames ? '' : 'im.turms.proto'),
      createEmptyInstance: create)
    ..a<$core.int>(1, _omitFieldNames ? '' : 'groupIndex', $pb.PbFieldType.O3)
    ..aOS(2, _omitFieldNames ? '' : 'newName')
    ..hasRequiredFields = false;

  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
      'Will be removed in next major version')
  UpdateRelationshipGroupRequest clone() =>
      UpdateRelationshipGroupRequest()..mergeFromMessage(this);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
      'Will be removed in next major version')
  UpdateRelationshipGroupRequest copyWith(
          void Function(UpdateRelationshipGroupRequest) updates) =>
      super.copyWith(
              (message) => updates(message as UpdateRelationshipGroupRequest))
          as UpdateRelationshipGroupRequest;

  $pb.BuilderInfo get info_ => _i;

  @$core.pragma('dart2js:noInline')
  static UpdateRelationshipGroupRequest create() =>
      UpdateRelationshipGroupRequest._();
  UpdateRelationshipGroupRequest createEmptyInstance() => create();
  static $pb.PbList<UpdateRelationshipGroupRequest> createRepeated() =>
      $pb.PbList<UpdateRelationshipGroupRequest>();
  @$core.pragma('dart2js:noInline')
  static UpdateRelationshipGroupRequest getDefault() => _defaultInstance ??=
      $pb.GeneratedMessage.$_defaultFor<UpdateRelationshipGroupRequest>(create);
  static UpdateRelationshipGroupRequest? _defaultInstance;

  /// Query filter
  @$pb.TagNumber(1)
  $core.int get groupIndex => $_getIZ(0);
  @$pb.TagNumber(1)
  set groupIndex($core.int v) {
    $_setSignedInt32(0, v);
  }

  @$pb.TagNumber(1)
  $core.bool hasGroupIndex() => $_has(0);
  @$pb.TagNumber(1)
  void clearGroupIndex() => clearField(1);

  /// Update
  @$pb.TagNumber(2)
  $core.String get newName => $_getSZ(1);
  @$pb.TagNumber(2)
  set newName($core.String v) {
    $_setString(1, v);
  }

  @$pb.TagNumber(2)
  $core.bool hasNewName() => $_has(1);
  @$pb.TagNumber(2)
  void clearNewName() => clearField(2);
}

const _omitFieldNames = $core.bool.fromEnvironment('protobuf.omit_field_names');
const _omitMessageNames =
    $core.bool.fromEnvironment('protobuf.omit_message_names');
