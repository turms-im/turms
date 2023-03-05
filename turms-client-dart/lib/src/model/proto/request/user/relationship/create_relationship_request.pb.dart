///
//  Generated code. Do not modify.
//  source: request/user/relationship/create_relationship_request.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,constant_identifier_names,directives_ordering,library_prefixes,non_constant_identifier_names,prefer_final_fields,return_of_invalid_type,unnecessary_const,unnecessary_import,unnecessary_this,unused_import,unused_shown_name

import 'dart:core' as $core;

import 'package:fixnum/fixnum.dart' as $fixnum;
import 'package:protobuf/protobuf.dart' as $pb;

class CreateRelationshipRequest extends $pb.GeneratedMessage {
  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      const $core.bool.fromEnvironment('protobuf.omit_message_names')
          ? ''
          : 'CreateRelationshipRequest',
      package: const $pb.PackageName(
          const $core.bool.fromEnvironment('protobuf.omit_message_names')
              ? ''
              : 'im.turms.proto'),
      createEmptyInstance: create)
    ..aInt64(
        1,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'userId')
    ..aOB(
        2,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'blocked')
    ..a<$core.int>(
        3,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'groupIndex',
        $pb.PbFieldType.O3)
    ..hasRequiredFields = false;

  CreateRelationshipRequest._() : super();
  factory CreateRelationshipRequest({
    $fixnum.Int64? userId,
    $core.bool? blocked,
    $core.int? groupIndex,
  }) {
    final _result = create();
    if (userId != null) {
      _result.userId = userId;
    }
    if (blocked != null) {
      _result.blocked = blocked;
    }
    if (groupIndex != null) {
      _result.groupIndex = groupIndex;
    }
    return _result;
  }
  factory CreateRelationshipRequest.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory CreateRelationshipRequest.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);
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
          as CreateRelationshipRequest; // ignore: deprecated_member_use
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
}
