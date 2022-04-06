///
//  Generated code. Do not modify.
//  source: model/user/users_online_statuses.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,unnecessary_const,non_constant_identifier_names,library_prefixes,unused_import,unused_shown_name,return_of_invalid_type,unnecessary_this,prefer_final_fields

import 'dart:core' as $core;

import 'package:protobuf/protobuf.dart' as $pb;

import 'user_status_detail.pb.dart' as $0;

class UsersOnlineStatuses extends $pb.GeneratedMessage {
  static final $pb.BuilderInfo _i = $pb.BuilderInfo(const $core.bool.fromEnvironment('protobuf.omit_message_names') ? '' : 'UsersOnlineStatuses', package: const $pb.PackageName(const $core.bool.fromEnvironment('protobuf.omit_message_names') ? '' : 'im.turms.proto'), createEmptyInstance: create)
    ..pc<$0.UserStatusDetail>(1, const $core.bool.fromEnvironment('protobuf.omit_field_names') ? '' : 'userStatuses', $pb.PbFieldType.PM, subBuilder: $0.UserStatusDetail.create)
    ..hasRequiredFields = false
  ;

  UsersOnlineStatuses._() : super();
  factory UsersOnlineStatuses({
    $core.Iterable<$0.UserStatusDetail>? userStatuses,
  }) {
    final _result = create();
    if (userStatuses != null) {
      _result.userStatuses.addAll(userStatuses);
    }
    return _result;
  }
  factory UsersOnlineStatuses.fromBuffer($core.List<$core.int> i, [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) => create()..mergeFromBuffer(i, r);
  factory UsersOnlineStatuses.fromJson($core.String i, [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) => create()..mergeFromJson(i, r);
  @$core.Deprecated(
  'Using this can add significant overhead to your binary. '
  'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
  'Will be removed in next major version')
  UsersOnlineStatuses clone() => UsersOnlineStatuses()..mergeFromMessage(this);
  @$core.Deprecated(
  'Using this can add significant overhead to your binary. '
  'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
  'Will be removed in next major version')
  UsersOnlineStatuses copyWith(void Function(UsersOnlineStatuses) updates) => super.copyWith((message) => updates(message as UsersOnlineStatuses)) as UsersOnlineStatuses; // ignore: deprecated_member_use
  $pb.BuilderInfo get info_ => _i;
  @$core.pragma('dart2js:noInline')
  static UsersOnlineStatuses create() => UsersOnlineStatuses._();
  UsersOnlineStatuses createEmptyInstance() => create();
  static $pb.PbList<UsersOnlineStatuses> createRepeated() => $pb.PbList<UsersOnlineStatuses>();
  @$core.pragma('dart2js:noInline')
  static UsersOnlineStatuses getDefault() => _defaultInstance ??= $pb.GeneratedMessage.$_defaultFor<UsersOnlineStatuses>(create);
  static UsersOnlineStatuses? _defaultInstance;

  @$pb.TagNumber(1)
  $core.List<$0.UserStatusDetail> get userStatuses => $_getList(0);
}

