//
//  Generated code. Do not modify.
//  source: request/user/create_session_request.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:core' as $core;

import 'package:fixnum/fixnum.dart' as $fixnum;
import 'package:protobuf/protobuf.dart' as $pb;

import '../../constant/device_type.pbenum.dart' as $2;
import '../../constant/user_status.pbenum.dart' as $1;
import '../../model/user/user_location.pb.dart' as $0;

class CreateSessionRequest extends $pb.GeneratedMessage {
  factory CreateSessionRequest({
    $core.int? version,
    $fixnum.Int64? userId,
    $core.String? password,
    $1.UserStatus? userStatus,
    $2.DeviceType? deviceType,
    $core.Map<$core.String, $core.String>? deviceDetails,
    $0.UserLocation? location,
  }) {
    final $result = create();
    if (version != null) {
      $result.version = version;
    }
    if (userId != null) {
      $result.userId = userId;
    }
    if (password != null) {
      $result.password = password;
    }
    if (userStatus != null) {
      $result.userStatus = userStatus;
    }
    if (deviceType != null) {
      $result.deviceType = deviceType;
    }
    if (deviceDetails != null) {
      $result.deviceDetails.addAll(deviceDetails);
    }
    if (location != null) {
      $result.location = location;
    }
    return $result;
  }
  CreateSessionRequest._() : super();
  factory CreateSessionRequest.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory CreateSessionRequest.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);

  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      _omitMessageNames ? '' : 'CreateSessionRequest',
      package: const $pb.PackageName(_omitMessageNames ? '' : 'im.turms.proto'),
      createEmptyInstance: create)
    ..a<$core.int>(1, _omitFieldNames ? '' : 'version', $pb.PbFieldType.O3)
    ..aInt64(2, _omitFieldNames ? '' : 'userId')
    ..aOS(3, _omitFieldNames ? '' : 'password')
    ..e<$1.UserStatus>(
        4, _omitFieldNames ? '' : 'userStatus', $pb.PbFieldType.OE,
        defaultOrMaker: $1.UserStatus.AVAILABLE,
        valueOf: $1.UserStatus.valueOf,
        enumValues: $1.UserStatus.values)
    ..e<$2.DeviceType>(
        5, _omitFieldNames ? '' : 'deviceType', $pb.PbFieldType.OE,
        defaultOrMaker: $2.DeviceType.DESKTOP,
        valueOf: $2.DeviceType.valueOf,
        enumValues: $2.DeviceType.values)
    ..m<$core.String, $core.String>(6, _omitFieldNames ? '' : 'deviceDetails',
        entryClassName: 'CreateSessionRequest.DeviceDetailsEntry',
        keyFieldType: $pb.PbFieldType.OS,
        valueFieldType: $pb.PbFieldType.OS,
        packageName: const $pb.PackageName('im.turms.proto'))
    ..aOM<$0.UserLocation>(7, _omitFieldNames ? '' : 'location',
        subBuilder: $0.UserLocation.create)
    ..hasRequiredFields = false;

  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
      'Will be removed in next major version')
  CreateSessionRequest clone() =>
      CreateSessionRequest()..mergeFromMessage(this);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
      'Will be removed in next major version')
  CreateSessionRequest copyWith(void Function(CreateSessionRequest) updates) =>
      super.copyWith((message) => updates(message as CreateSessionRequest))
          as CreateSessionRequest;

  $pb.BuilderInfo get info_ => _i;

  @$core.pragma('dart2js:noInline')
  static CreateSessionRequest create() => CreateSessionRequest._();
  CreateSessionRequest createEmptyInstance() => create();
  static $pb.PbList<CreateSessionRequest> createRepeated() =>
      $pb.PbList<CreateSessionRequest>();
  @$core.pragma('dart2js:noInline')
  static CreateSessionRequest getDefault() => _defaultInstance ??=
      $pb.GeneratedMessage.$_defaultFor<CreateSessionRequest>(create);
  static CreateSessionRequest? _defaultInstance;

  @$pb.TagNumber(1)
  $core.int get version => $_getIZ(0);
  @$pb.TagNumber(1)
  set version($core.int v) {
    $_setSignedInt32(0, v);
  }

  @$pb.TagNumber(1)
  $core.bool hasVersion() => $_has(0);
  @$pb.TagNumber(1)
  void clearVersion() => clearField(1);

  @$pb.TagNumber(2)
  $fixnum.Int64 get userId => $_getI64(1);
  @$pb.TagNumber(2)
  set userId($fixnum.Int64 v) {
    $_setInt64(1, v);
  }

  @$pb.TagNumber(2)
  $core.bool hasUserId() => $_has(1);
  @$pb.TagNumber(2)
  void clearUserId() => clearField(2);

  @$pb.TagNumber(3)
  $core.String get password => $_getSZ(2);
  @$pb.TagNumber(3)
  set password($core.String v) {
    $_setString(2, v);
  }

  @$pb.TagNumber(3)
  $core.bool hasPassword() => $_has(2);
  @$pb.TagNumber(3)
  void clearPassword() => clearField(3);

  @$pb.TagNumber(4)
  $1.UserStatus get userStatus => $_getN(3);
  @$pb.TagNumber(4)
  set userStatus($1.UserStatus v) {
    setField(4, v);
  }

  @$pb.TagNumber(4)
  $core.bool hasUserStatus() => $_has(3);
  @$pb.TagNumber(4)
  void clearUserStatus() => clearField(4);

  @$pb.TagNumber(5)
  $2.DeviceType get deviceType => $_getN(4);
  @$pb.TagNumber(5)
  set deviceType($2.DeviceType v) {
    setField(5, v);
  }

  @$pb.TagNumber(5)
  $core.bool hasDeviceType() => $_has(4);
  @$pb.TagNumber(5)
  void clearDeviceType() => clearField(5);

  @$pb.TagNumber(6)
  $core.Map<$core.String, $core.String> get deviceDetails => $_getMap(5);

  @$pb.TagNumber(7)
  $0.UserLocation get location => $_getN(6);
  @$pb.TagNumber(7)
  set location($0.UserLocation v) {
    setField(7, v);
  }

  @$pb.TagNumber(7)
  $core.bool hasLocation() => $_has(6);
  @$pb.TagNumber(7)
  void clearLocation() => clearField(7);
  @$pb.TagNumber(7)
  $0.UserLocation ensureLocation() => $_ensure(6);
}

const _omitFieldNames = $core.bool.fromEnvironment('protobuf.omit_field_names');
const _omitMessageNames =
    $core.bool.fromEnvironment('protobuf.omit_message_names');
