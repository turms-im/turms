//
//  Generated code. Do not modify.
//  source: model/user/nearby_user.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:core' as $core;

import 'package:fixnum/fixnum.dart' as $fixnum;
import 'package:protobuf/protobuf.dart' as $pb;

import '../../constant/device_type.pbenum.dart' as $3;
import '../common/value.pb.dart' as $2;
import 'user_info.pb.dart' as $0;
import 'user_location.pb.dart' as $1;

class NearbyUser extends $pb.GeneratedMessage {
  factory NearbyUser({
    $fixnum.Int64? userId,
    $3.DeviceType? deviceType,
    $0.UserInfo? info,
    $core.int? distance,
    $1.UserLocation? location,
    $core.Iterable<$2.Value>? customAttributes,
  }) {
    final $result = create();
    if (userId != null) {
      $result.userId = userId;
    }
    if (deviceType != null) {
      $result.deviceType = deviceType;
    }
    if (info != null) {
      $result.info = info;
    }
    if (distance != null) {
      $result.distance = distance;
    }
    if (location != null) {
      $result.location = location;
    }
    if (customAttributes != null) {
      $result.customAttributes.addAll(customAttributes);
    }
    return $result;
  }
  NearbyUser._() : super();
  factory NearbyUser.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory NearbyUser.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);

  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      _omitMessageNames ? '' : 'NearbyUser',
      package: const $pb.PackageName(_omitMessageNames ? '' : 'im.turms.proto'),
      createEmptyInstance: create)
    ..aInt64(1, _omitFieldNames ? '' : 'userId')
    ..e<$3.DeviceType>(
        2, _omitFieldNames ? '' : 'deviceType', $pb.PbFieldType.OE,
        defaultOrMaker: $3.DeviceType.DESKTOP,
        valueOf: $3.DeviceType.valueOf,
        enumValues: $3.DeviceType.values)
    ..aOM<$0.UserInfo>(3, _omitFieldNames ? '' : 'info',
        subBuilder: $0.UserInfo.create)
    ..a<$core.int>(4, _omitFieldNames ? '' : 'distance', $pb.PbFieldType.O3)
    ..aOM<$1.UserLocation>(5, _omitFieldNames ? '' : 'location',
        subBuilder: $1.UserLocation.create)
    ..pc<$2.Value>(
        15, _omitFieldNames ? '' : 'customAttributes', $pb.PbFieldType.PM,
        subBuilder: $2.Value.create)
    ..hasRequiredFields = false;

  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
      'Will be removed in next major version')
  NearbyUser clone() => NearbyUser()..mergeFromMessage(this);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
      'Will be removed in next major version')
  NearbyUser copyWith(void Function(NearbyUser) updates) =>
      super.copyWith((message) => updates(message as NearbyUser)) as NearbyUser;

  $pb.BuilderInfo get info_ => _i;

  @$core.pragma('dart2js:noInline')
  static NearbyUser create() => NearbyUser._();
  NearbyUser createEmptyInstance() => create();
  static $pb.PbList<NearbyUser> createRepeated() => $pb.PbList<NearbyUser>();
  @$core.pragma('dart2js:noInline')
  static NearbyUser getDefault() => _defaultInstance ??=
      $pb.GeneratedMessage.$_defaultFor<NearbyUser>(create);
  static NearbyUser? _defaultInstance;

  /// session info
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
  $3.DeviceType get deviceType => $_getN(1);
  @$pb.TagNumber(2)
  set deviceType($3.DeviceType v) {
    setField(2, v);
  }

  @$pb.TagNumber(2)
  $core.bool hasDeviceType() => $_has(1);
  @$pb.TagNumber(2)
  void clearDeviceType() => clearField(2);

  /// user info
  @$pb.TagNumber(3)
  $0.UserInfo get info => $_getN(2);
  @$pb.TagNumber(3)
  set info($0.UserInfo v) {
    setField(3, v);
  }

  @$pb.TagNumber(3)
  $core.bool hasInfo() => $_has(2);
  @$pb.TagNumber(3)
  void clearInfo() => clearField(3);
  @$pb.TagNumber(3)
  $0.UserInfo ensureInfo() => $_ensure(2);

  /// geo info
  @$pb.TagNumber(4)
  $core.int get distance => $_getIZ(3);
  @$pb.TagNumber(4)
  set distance($core.int v) {
    $_setSignedInt32(3, v);
  }

  @$pb.TagNumber(4)
  $core.bool hasDistance() => $_has(3);
  @$pb.TagNumber(4)
  void clearDistance() => clearField(4);

  @$pb.TagNumber(5)
  $1.UserLocation get location => $_getN(4);
  @$pb.TagNumber(5)
  set location($1.UserLocation v) {
    setField(5, v);
  }

  @$pb.TagNumber(5)
  $core.bool hasLocation() => $_has(4);
  @$pb.TagNumber(5)
  void clearLocation() => clearField(5);
  @$pb.TagNumber(5)
  $1.UserLocation ensureLocation() => $_ensure(4);

  @$pb.TagNumber(15)
  $core.List<$2.Value> get customAttributes => $_getList(5);
}

const _omitFieldNames = $core.bool.fromEnvironment('protobuf.omit_field_names');
const _omitMessageNames =
    $core.bool.fromEnvironment('protobuf.omit_message_names');
