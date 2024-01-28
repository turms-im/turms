//
//  Generated code. Do not modify.
//  source: request/user/update_user_request.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:core' as $core;

import 'package:protobuf/protobuf.dart' as $pb;

import '../../constant/profile_access_strategy.pbenum.dart' as $0;

class UpdateUserRequest extends $pb.GeneratedMessage {
  factory UpdateUserRequest({
    $core.String? password,
    $core.String? name,
    $core.String? intro,
    $core.String? profilePicture,
    $0.ProfileAccessStrategy? profileAccessStrategy,
  }) {
    final $result = create();
    if (password != null) {
      $result.password = password;
    }
    if (name != null) {
      $result.name = name;
    }
    if (intro != null) {
      $result.intro = intro;
    }
    if (profilePicture != null) {
      $result.profilePicture = profilePicture;
    }
    if (profileAccessStrategy != null) {
      $result.profileAccessStrategy = profileAccessStrategy;
    }
    return $result;
  }
  UpdateUserRequest._() : super();
  factory UpdateUserRequest.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory UpdateUserRequest.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);

  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      _omitMessageNames ? '' : 'UpdateUserRequest',
      package: const $pb.PackageName(_omitMessageNames ? '' : 'im.turms.proto'),
      createEmptyInstance: create)
    ..aOS(1, _omitFieldNames ? '' : 'password')
    ..aOS(2, _omitFieldNames ? '' : 'name')
    ..aOS(3, _omitFieldNames ? '' : 'intro')
    ..aOS(4, _omitFieldNames ? '' : 'profilePicture')
    ..e<$0.ProfileAccessStrategy>(
        5, _omitFieldNames ? '' : 'profileAccessStrategy', $pb.PbFieldType.OE,
        defaultOrMaker: $0.ProfileAccessStrategy.ALL,
        valueOf: $0.ProfileAccessStrategy.valueOf,
        enumValues: $0.ProfileAccessStrategy.values)
    ..hasRequiredFields = false;

  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
      'Will be removed in next major version')
  UpdateUserRequest clone() => UpdateUserRequest()..mergeFromMessage(this);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
      'Will be removed in next major version')
  UpdateUserRequest copyWith(void Function(UpdateUserRequest) updates) =>
      super.copyWith((message) => updates(message as UpdateUserRequest))
          as UpdateUserRequest;

  $pb.BuilderInfo get info_ => _i;

  @$core.pragma('dart2js:noInline')
  static UpdateUserRequest create() => UpdateUserRequest._();
  UpdateUserRequest createEmptyInstance() => create();
  static $pb.PbList<UpdateUserRequest> createRepeated() =>
      $pb.PbList<UpdateUserRequest>();
  @$core.pragma('dart2js:noInline')
  static UpdateUserRequest getDefault() => _defaultInstance ??=
      $pb.GeneratedMessage.$_defaultFor<UpdateUserRequest>(create);
  static UpdateUserRequest? _defaultInstance;

  /// Update
  @$pb.TagNumber(1)
  $core.String get password => $_getSZ(0);
  @$pb.TagNumber(1)
  set password($core.String v) {
    $_setString(0, v);
  }

  @$pb.TagNumber(1)
  $core.bool hasPassword() => $_has(0);
  @$pb.TagNumber(1)
  void clearPassword() => clearField(1);

  @$pb.TagNumber(2)
  $core.String get name => $_getSZ(1);
  @$pb.TagNumber(2)
  set name($core.String v) {
    $_setString(1, v);
  }

  @$pb.TagNumber(2)
  $core.bool hasName() => $_has(1);
  @$pb.TagNumber(2)
  void clearName() => clearField(2);

  @$pb.TagNumber(3)
  $core.String get intro => $_getSZ(2);
  @$pb.TagNumber(3)
  set intro($core.String v) {
    $_setString(2, v);
  }

  @$pb.TagNumber(3)
  $core.bool hasIntro() => $_has(2);
  @$pb.TagNumber(3)
  void clearIntro() => clearField(3);

  @$pb.TagNumber(4)
  $core.String get profilePicture => $_getSZ(3);
  @$pb.TagNumber(4)
  set profilePicture($core.String v) {
    $_setString(3, v);
  }

  @$pb.TagNumber(4)
  $core.bool hasProfilePicture() => $_has(3);
  @$pb.TagNumber(4)
  void clearProfilePicture() => clearField(4);

  @$pb.TagNumber(5)
  $0.ProfileAccessStrategy get profileAccessStrategy => $_getN(4);
  @$pb.TagNumber(5)
  set profileAccessStrategy($0.ProfileAccessStrategy v) {
    setField(5, v);
  }

  @$pb.TagNumber(5)
  $core.bool hasProfileAccessStrategy() => $_has(4);
  @$pb.TagNumber(5)
  void clearProfileAccessStrategy() => clearField(5);
}

const _omitFieldNames = $core.bool.fromEnvironment('protobuf.omit_field_names');
const _omitMessageNames =
    $core.bool.fromEnvironment('protobuf.omit_message_names');
