///
//  Generated code. Do not modify.
//  source: model/user/user_online_status.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,constant_identifier_names,directives_ordering,library_prefixes,non_constant_identifier_names,prefer_final_fields,return_of_invalid_type,unnecessary_const,unnecessary_import,unnecessary_this,unused_import,unused_shown_name

import 'dart:core' as $core;

import 'package:fixnum/fixnum.dart' as $fixnum;
import 'package:protobuf/protobuf.dart' as $pb;

import '../../constant/user_status.pbenum.dart' as $0;
import '../../constant/device_type.pbenum.dart' as $1;

class UserOnlineStatus extends $pb.GeneratedMessage {
  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      const $core.bool.fromEnvironment('protobuf.omit_message_names')
          ? ''
          : 'UserOnlineStatus',
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
    ..e<$0.UserStatus>(
        2,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'userStatus',
        $pb.PbFieldType.OE,
        defaultOrMaker: $0.UserStatus.AVAILABLE,
        valueOf: $0.UserStatus.valueOf,
        enumValues: $0.UserStatus.values)
    ..pc<$1.DeviceType>(
        3,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'usingDeviceTypes',
        $pb.PbFieldType.KE,
        valueOf: $1.DeviceType.valueOf,
        enumValues: $1.DeviceType.values,
        defaultEnumValue: $1.DeviceType.DESKTOP)
    ..hasRequiredFields = false;

  UserOnlineStatus._() : super();
  factory UserOnlineStatus({
    $fixnum.Int64? userId,
    $0.UserStatus? userStatus,
    $core.Iterable<$1.DeviceType>? usingDeviceTypes,
  }) {
    final _result = create();
    if (userId != null) {
      _result.userId = userId;
    }
    if (userStatus != null) {
      _result.userStatus = userStatus;
    }
    if (usingDeviceTypes != null) {
      _result.usingDeviceTypes.addAll(usingDeviceTypes);
    }
    return _result;
  }
  factory UserOnlineStatus.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory UserOnlineStatus.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
      'Will be removed in next major version')
  UserOnlineStatus clone() => UserOnlineStatus()..mergeFromMessage(this);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
      'Will be removed in next major version')
  UserOnlineStatus copyWith(void Function(UserOnlineStatus) updates) =>
      super.copyWith((message) => updates(message as UserOnlineStatus))
          as UserOnlineStatus; // ignore: deprecated_member_use
  $pb.BuilderInfo get info_ => _i;
  @$core.pragma('dart2js:noInline')
  static UserOnlineStatus create() => UserOnlineStatus._();
  UserOnlineStatus createEmptyInstance() => create();
  static $pb.PbList<UserOnlineStatus> createRepeated() =>
      $pb.PbList<UserOnlineStatus>();
  @$core.pragma('dart2js:noInline')
  static UserOnlineStatus getDefault() => _defaultInstance ??=
      $pb.GeneratedMessage.$_defaultFor<UserOnlineStatus>(create);
  static UserOnlineStatus? _defaultInstance;

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
  $0.UserStatus get userStatus => $_getN(1);
  @$pb.TagNumber(2)
  set userStatus($0.UserStatus v) {
    setField(2, v);
  }

  @$pb.TagNumber(2)
  $core.bool hasUserStatus() => $_has(1);
  @$pb.TagNumber(2)
  void clearUserStatus() => clearField(2);

  @$pb.TagNumber(3)
  $core.List<$1.DeviceType> get usingDeviceTypes => $_getList(2);
}
