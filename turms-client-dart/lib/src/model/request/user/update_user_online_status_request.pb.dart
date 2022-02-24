///
//  Generated code. Do not modify.
//  source: request/user/update_user_online_status_request.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,unnecessary_const,non_constant_identifier_names,library_prefixes,unused_import,unused_shown_name,return_of_invalid_type,unnecessary_this,prefer_final_fields

import 'dart:core' as $core;

import 'package:protobuf/protobuf.dart' as $pb;

import '../../constant/user_status.pbenum.dart' as $0;
import '../../constant/device_type.pbenum.dart' as $1;

class UpdateUserOnlineStatusRequest extends $pb.GeneratedMessage {
  static final $pb.BuilderInfo _i = $pb.BuilderInfo(const $core.bool.fromEnvironment('protobuf.omit_message_names') ? '' : 'UpdateUserOnlineStatusRequest', package: const $pb.PackageName(const $core.bool.fromEnvironment('protobuf.omit_message_names') ? '' : 'im.turms.proto'), createEmptyInstance: create)
    ..e<$0.UserStatus>(1, const $core.bool.fromEnvironment('protobuf.omit_field_names') ? '' : 'userStatus', $pb.PbFieldType.OE, defaultOrMaker: $0.UserStatus.AVAILABLE, valueOf: $0.UserStatus.valueOf, enumValues: $0.UserStatus.values)
    ..pc<$1.DeviceType>(2, const $core.bool.fromEnvironment('protobuf.omit_field_names') ? '' : 'deviceTypes', $pb.PbFieldType.PE, valueOf: $1.DeviceType.valueOf, enumValues: $1.DeviceType.values)
    ..hasRequiredFields = false
  ;

  UpdateUserOnlineStatusRequest._() : super();
  factory UpdateUserOnlineStatusRequest({
    $0.UserStatus? userStatus,
    $core.Iterable<$1.DeviceType>? deviceTypes,
  }) {
    final _result = create();
    if (userStatus != null) {
      _result.userStatus = userStatus;
    }
    if (deviceTypes != null) {
      _result.deviceTypes.addAll(deviceTypes);
    }
    return _result;
  }
  factory UpdateUserOnlineStatusRequest.fromBuffer($core.List<$core.int> i, [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) => create()..mergeFromBuffer(i, r);
  factory UpdateUserOnlineStatusRequest.fromJson($core.String i, [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) => create()..mergeFromJson(i, r);
  @$core.Deprecated(
  'Using this can add significant overhead to your binary. '
  'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
  'Will be removed in next major version')
  UpdateUserOnlineStatusRequest clone() => UpdateUserOnlineStatusRequest()..mergeFromMessage(this);
  @$core.Deprecated(
  'Using this can add significant overhead to your binary. '
  'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
  'Will be removed in next major version')
  UpdateUserOnlineStatusRequest copyWith(void Function(UpdateUserOnlineStatusRequest) updates) => super.copyWith((message) => updates(message as UpdateUserOnlineStatusRequest)) as UpdateUserOnlineStatusRequest; // ignore: deprecated_member_use
  $pb.BuilderInfo get info_ => _i;
  @$core.pragma('dart2js:noInline')
  static UpdateUserOnlineStatusRequest create() => UpdateUserOnlineStatusRequest._();
  UpdateUserOnlineStatusRequest createEmptyInstance() => create();
  static $pb.PbList<UpdateUserOnlineStatusRequest> createRepeated() => $pb.PbList<UpdateUserOnlineStatusRequest>();
  @$core.pragma('dart2js:noInline')
  static UpdateUserOnlineStatusRequest getDefault() => _defaultInstance ??= $pb.GeneratedMessage.$_defaultFor<UpdateUserOnlineStatusRequest>(create);
  static UpdateUserOnlineStatusRequest? _defaultInstance;

  @$pb.TagNumber(1)
  $0.UserStatus get userStatus => $_getN(0);
  @$pb.TagNumber(1)
  set userStatus($0.UserStatus v) { setField(1, v); }
  @$pb.TagNumber(1)
  $core.bool hasUserStatus() => $_has(0);
  @$pb.TagNumber(1)
  void clearUserStatus() => clearField(1);

  @$pb.TagNumber(2)
  $core.List<$1.DeviceType> get deviceTypes => $_getList(1);
}

