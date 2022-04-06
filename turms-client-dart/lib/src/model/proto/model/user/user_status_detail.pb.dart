///
//  Generated code. Do not modify.
//  source: model/user/user_status_detail.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,unnecessary_const,non_constant_identifier_names,library_prefixes,unused_import,unused_shown_name,return_of_invalid_type,unnecessary_this,prefer_final_fields

import 'dart:core' as $core;

import 'package:fixnum/fixnum.dart' as $fixnum;
import 'package:protobuf/protobuf.dart' as $pb;

import '../../constant/user_status.pbenum.dart' as $0;
import '../../constant/device_type.pbenum.dart' as $1;

class UserStatusDetail extends $pb.GeneratedMessage {
  static final $pb.BuilderInfo _i = $pb.BuilderInfo(const $core.bool.fromEnvironment('protobuf.omit_message_names') ? '' : 'UserStatusDetail', package: const $pb.PackageName(const $core.bool.fromEnvironment('protobuf.omit_message_names') ? '' : 'im.turms.proto'), createEmptyInstance: create)
    ..aInt64(1, const $core.bool.fromEnvironment('protobuf.omit_field_names') ? '' : 'userId')
    ..e<$0.UserStatus>(2, const $core.bool.fromEnvironment('protobuf.omit_field_names') ? '' : 'userStatus', $pb.PbFieldType.OE, defaultOrMaker: $0.UserStatus.AVAILABLE, valueOf: $0.UserStatus.valueOf, enumValues: $0.UserStatus.values)
    ..pc<$1.DeviceType>(3, const $core.bool.fromEnvironment('protobuf.omit_field_names') ? '' : 'usingDeviceTypes', $pb.PbFieldType.PE, valueOf: $1.DeviceType.valueOf, enumValues: $1.DeviceType.values)
    ..hasRequiredFields = false
  ;

  UserStatusDetail._() : super();
  factory UserStatusDetail({
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
  factory UserStatusDetail.fromBuffer($core.List<$core.int> i, [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) => create()..mergeFromBuffer(i, r);
  factory UserStatusDetail.fromJson($core.String i, [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) => create()..mergeFromJson(i, r);
  @$core.Deprecated(
  'Using this can add significant overhead to your binary. '
  'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
  'Will be removed in next major version')
  UserStatusDetail clone() => UserStatusDetail()..mergeFromMessage(this);
  @$core.Deprecated(
  'Using this can add significant overhead to your binary. '
  'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
  'Will be removed in next major version')
  UserStatusDetail copyWith(void Function(UserStatusDetail) updates) => super.copyWith((message) => updates(message as UserStatusDetail)) as UserStatusDetail; // ignore: deprecated_member_use
  $pb.BuilderInfo get info_ => _i;
  @$core.pragma('dart2js:noInline')
  static UserStatusDetail create() => UserStatusDetail._();
  UserStatusDetail createEmptyInstance() => create();
  static $pb.PbList<UserStatusDetail> createRepeated() => $pb.PbList<UserStatusDetail>();
  @$core.pragma('dart2js:noInline')
  static UserStatusDetail getDefault() => _defaultInstance ??= $pb.GeneratedMessage.$_defaultFor<UserStatusDetail>(create);
  static UserStatusDetail? _defaultInstance;

  @$pb.TagNumber(1)
  $fixnum.Int64 get userId => $_getI64(0);
  @$pb.TagNumber(1)
  set userId($fixnum.Int64 v) { $_setInt64(0, v); }
  @$pb.TagNumber(1)
  $core.bool hasUserId() => $_has(0);
  @$pb.TagNumber(1)
  void clearUserId() => clearField(1);

  @$pb.TagNumber(2)
  $0.UserStatus get userStatus => $_getN(1);
  @$pb.TagNumber(2)
  set userStatus($0.UserStatus v) { setField(2, v); }
  @$pb.TagNumber(2)
  $core.bool hasUserStatus() => $_has(1);
  @$pb.TagNumber(2)
  void clearUserStatus() => clearField(2);

  @$pb.TagNumber(3)
  $core.List<$1.DeviceType> get usingDeviceTypes => $_getList(2);
}

