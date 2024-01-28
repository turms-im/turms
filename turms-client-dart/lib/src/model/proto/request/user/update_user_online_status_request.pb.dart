//
//  Generated code. Do not modify.
//  source: request/user/update_user_online_status_request.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:core' as $core;

import 'package:protobuf/protobuf.dart' as $pb;

import '../../constant/device_type.pbenum.dart' as $0;
import '../../constant/user_status.pbenum.dart' as $1;

class UpdateUserOnlineStatusRequest extends $pb.GeneratedMessage {
  factory UpdateUserOnlineStatusRequest({
    $core.Iterable<$0.DeviceType>? deviceTypes,
    $1.UserStatus? userStatus,
  }) {
    final $result = create();
    if (deviceTypes != null) {
      $result.deviceTypes.addAll(deviceTypes);
    }
    if (userStatus != null) {
      $result.userStatus = userStatus;
    }
    return $result;
  }
  UpdateUserOnlineStatusRequest._() : super();
  factory UpdateUserOnlineStatusRequest.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory UpdateUserOnlineStatusRequest.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);

  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      _omitMessageNames ? '' : 'UpdateUserOnlineStatusRequest',
      package: const $pb.PackageName(_omitMessageNames ? '' : 'im.turms.proto'),
      createEmptyInstance: create)
    ..pc<$0.DeviceType>(
        1, _omitFieldNames ? '' : 'deviceTypes', $pb.PbFieldType.KE,
        valueOf: $0.DeviceType.valueOf,
        enumValues: $0.DeviceType.values,
        defaultEnumValue: $0.DeviceType.DESKTOP)
    ..e<$1.UserStatus>(
        2, _omitFieldNames ? '' : 'userStatus', $pb.PbFieldType.OE,
        defaultOrMaker: $1.UserStatus.AVAILABLE,
        valueOf: $1.UserStatus.valueOf,
        enumValues: $1.UserStatus.values)
    ..hasRequiredFields = false;

  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
      'Will be removed in next major version')
  UpdateUserOnlineStatusRequest clone() =>
      UpdateUserOnlineStatusRequest()..mergeFromMessage(this);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
      'Will be removed in next major version')
  UpdateUserOnlineStatusRequest copyWith(
          void Function(UpdateUserOnlineStatusRequest) updates) =>
      super.copyWith(
              (message) => updates(message as UpdateUserOnlineStatusRequest))
          as UpdateUserOnlineStatusRequest;

  $pb.BuilderInfo get info_ => _i;

  @$core.pragma('dart2js:noInline')
  static UpdateUserOnlineStatusRequest create() =>
      UpdateUserOnlineStatusRequest._();
  UpdateUserOnlineStatusRequest createEmptyInstance() => create();
  static $pb.PbList<UpdateUserOnlineStatusRequest> createRepeated() =>
      $pb.PbList<UpdateUserOnlineStatusRequest>();
  @$core.pragma('dart2js:noInline')
  static UpdateUserOnlineStatusRequest getDefault() => _defaultInstance ??=
      $pb.GeneratedMessage.$_defaultFor<UpdateUserOnlineStatusRequest>(create);
  static UpdateUserOnlineStatusRequest? _defaultInstance;

  /// Query filter
  @$pb.TagNumber(1)
  $core.List<$0.DeviceType> get deviceTypes => $_getList(0);

  /// Update
  @$pb.TagNumber(2)
  $1.UserStatus get userStatus => $_getN(1);
  @$pb.TagNumber(2)
  set userStatus($1.UserStatus v) {
    setField(2, v);
  }

  @$pb.TagNumber(2)
  $core.bool hasUserStatus() => $_has(1);
  @$pb.TagNumber(2)
  void clearUserStatus() => clearField(2);
}

const _omitFieldNames = $core.bool.fromEnvironment('protobuf.omit_field_names');
const _omitMessageNames =
    $core.bool.fromEnvironment('protobuf.omit_message_names');
