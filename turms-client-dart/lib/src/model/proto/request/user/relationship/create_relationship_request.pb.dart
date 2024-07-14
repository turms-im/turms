//
//  Generated code. Do not modify.
//  source: request/user/relationship/create_relationship_request.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:core' as $core;

import 'package:fixnum/fixnum.dart' as $fixnum;
import 'package:protobuf/protobuf.dart' as $pb;

import '../../../model/common/value.pb.dart' as $0;

class CreateRelationshipRequest extends $pb.GeneratedMessage {
  factory CreateRelationshipRequest({
    $fixnum.Int64? userId,
    $core.bool? blocked,
    $core.int? groupIndex,
    $core.String? name,
    $core.Iterable<$0.Value>? customAttributes,
  }) {
    final $result = create();
    if (userId != null) {
      $result.userId = userId;
    }
    if (blocked != null) {
      $result.blocked = blocked;
    }
    if (groupIndex != null) {
      $result.groupIndex = groupIndex;
    }
    if (name != null) {
      $result.name = name;
    }
    if (customAttributes != null) {
      $result.customAttributes.addAll(customAttributes);
    }
    return $result;
  }
  CreateRelationshipRequest._() : super();
  factory CreateRelationshipRequest.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory CreateRelationshipRequest.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);

  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      _omitMessageNames ? '' : 'CreateRelationshipRequest',
      package: const $pb.PackageName(_omitMessageNames ? '' : 'im.turms.proto'),
      createEmptyInstance: create)
    ..aInt64(1, _omitFieldNames ? '' : 'userId')
    ..aOB(2, _omitFieldNames ? '' : 'blocked')
    ..a<$core.int>(3, _omitFieldNames ? '' : 'groupIndex', $pb.PbFieldType.O3)
    ..aOS(4, _omitFieldNames ? '' : 'name')
    ..pc<$0.Value>(
        15, _omitFieldNames ? '' : 'customAttributes', $pb.PbFieldType.PM,
        subBuilder: $0.Value.create)
    ..hasRequiredFields = false;

  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
      'Will be removed in next major version')
  CreateRelationshipRequest clone() =>
      CreateRelationshipRequest()..mergeFromMessage(this);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
      'Will be removed in next major version')
  CreateRelationshipRequest copyWith(
          void Function(CreateRelationshipRequest) updates) =>
      super.copyWith((message) => updates(message as CreateRelationshipRequest))
          as CreateRelationshipRequest;

  $pb.BuilderInfo get info_ => _i;

  @$core.pragma('dart2js:noInline')
  static CreateRelationshipRequest create() => CreateRelationshipRequest._();
  CreateRelationshipRequest createEmptyInstance() => create();
  static $pb.PbList<CreateRelationshipRequest> createRepeated() =>
      $pb.PbList<CreateRelationshipRequest>();
  @$core.pragma('dart2js:noInline')
  static CreateRelationshipRequest getDefault() => _defaultInstance ??=
      $pb.GeneratedMessage.$_defaultFor<CreateRelationshipRequest>(create);
  static CreateRelationshipRequest? _defaultInstance;

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
  $core.bool get blocked => $_getBF(1);
  @$pb.TagNumber(2)
  set blocked($core.bool v) {
    $_setBool(1, v);
  }

  @$pb.TagNumber(2)
  $core.bool hasBlocked() => $_has(1);
  @$pb.TagNumber(2)
  void clearBlocked() => clearField(2);

  @$pb.TagNumber(3)
  $core.int get groupIndex => $_getIZ(2);
  @$pb.TagNumber(3)
  set groupIndex($core.int v) {
    $_setSignedInt32(2, v);
  }

  @$pb.TagNumber(3)
  $core.bool hasGroupIndex() => $_has(2);
  @$pb.TagNumber(3)
  void clearGroupIndex() => clearField(3);

  @$pb.TagNumber(4)
  $core.String get name => $_getSZ(3);
  @$pb.TagNumber(4)
  set name($core.String v) {
    $_setString(3, v);
  }

  @$pb.TagNumber(4)
  $core.bool hasName() => $_has(3);
  @$pb.TagNumber(4)
  void clearName() => clearField(4);

  @$pb.TagNumber(15)
  $core.List<$0.Value> get customAttributes => $_getList(4);
}

const _omitFieldNames = $core.bool.fromEnvironment('protobuf.omit_field_names');
const _omitMessageNames =
    $core.bool.fromEnvironment('protobuf.omit_message_names');
