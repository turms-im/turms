///
//  Generated code. Do not modify.
//  source: model/user/nearby_user.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,constant_identifier_names,directives_ordering,library_prefixes,non_constant_identifier_names,prefer_final_fields,return_of_invalid_type,unnecessary_const,unnecessary_import,unnecessary_this,unused_import,unused_shown_name

import 'dart:core' as $core;

import 'package:fixnum/fixnum.dart' as $fixnum;
import 'package:protobuf/protobuf.dart' as $pb;

import 'user_info.pb.dart' as $0;
import 'user_location.pb.dart' as $1;

import '../../constant/device_type.pbenum.dart' as $2;

class NearbyUser extends $pb.GeneratedMessage {
  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      const $core.bool.fromEnvironment('protobuf.omit_message_names')
          ? ''
          : 'NearbyUser',
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
    ..e<$2.DeviceType>(
        2,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'deviceType',
        $pb.PbFieldType.OE,
        defaultOrMaker: $2.DeviceType.DESKTOP,
        valueOf: $2.DeviceType.valueOf,
        enumValues: $2.DeviceType.values)
    ..aOM<$0.UserInfo>(
        3,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'info',
        subBuilder: $0.UserInfo.create)
    ..a<$core.int>(
        4,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'distance',
        $pb.PbFieldType.O3)
    ..aOM<$1.UserLocation>(
        5,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'location',
        subBuilder: $1.UserLocation.create)
    ..hasRequiredFields = false;

  NearbyUser._() : super();
  factory NearbyUser({
    $fixnum.Int64? userId,
    $2.DeviceType? deviceType,
    $0.UserInfo? info,
    $core.int? distance,
    $1.UserLocation? location,
  }) {
    final _result = create();
    if (userId != null) {
      _result.userId = userId;
    }
    if (deviceType != null) {
      _result.deviceType = deviceType;
    }
    if (info != null) {
      _result.info = info;
    }
    if (distance != null) {
      _result.distance = distance;
    }
    if (location != null) {
      _result.location = location;
    }
    return _result;
  }
  factory NearbyUser.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory NearbyUser.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
      'Will be removed in next major version')
  NearbyUser clone() => NearbyUser()..mergeFromMessage(this);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
      'Will be removed in next major version')
  NearbyUser copyWith(void Function(NearbyUser) updates) =>
      super.copyWith((message) => updates(message as NearbyUser))
          as NearbyUser; // ignore: deprecated_member_use
  $pb.BuilderInfo get info_ => _i;
  @$core.pragma('dart2js:noInline')
  static NearbyUser create() => NearbyUser._();
  NearbyUser createEmptyInstance() => create();
  static $pb.PbList<NearbyUser> createRepeated() => $pb.PbList<NearbyUser>();
  @$core.pragma('dart2js:noInline')
  static NearbyUser getDefault() => _defaultInstance ??=
      $pb.GeneratedMessage.$_defaultFor<NearbyUser>(create);
  static NearbyUser? _defaultInstance;

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
  $2.DeviceType get deviceType => $_getN(1);
  @$pb.TagNumber(2)
  set deviceType($2.DeviceType v) {
    setField(2, v);
  }

  @$pb.TagNumber(2)
  $core.bool hasDeviceType() => $_has(1);
  @$pb.TagNumber(2)
  void clearDeviceType() => clearField(2);

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
}
