///
//  Generated code. Do not modify.
//  source: model/user/user_online_statuses.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,constant_identifier_names,directives_ordering,library_prefixes,non_constant_identifier_names,prefer_final_fields,return_of_invalid_type,unnecessary_const,unnecessary_import,unnecessary_this,unused_import,unused_shown_name

import 'dart:core' as $core;

import 'package:protobuf/protobuf.dart' as $pb;

import 'user_online_status.pb.dart' as $0;

class UserOnlineStatuses extends $pb.GeneratedMessage {
  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      const $core.bool.fromEnvironment('protobuf.omit_message_names')
          ? ''
          : 'UserOnlineStatuses',
      package: const $pb.PackageName(
          const $core.bool.fromEnvironment('protobuf.omit_message_names')
              ? ''
              : 'im.turms.proto'),
      createEmptyInstance: create)
    ..pc<$0.UserOnlineStatus>(
        1,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'statuses',
        $pb.PbFieldType.PM,
        subBuilder: $0.UserOnlineStatus.create)
    ..hasRequiredFields = false;

  UserOnlineStatuses._() : super();
  factory UserOnlineStatuses({
    $core.Iterable<$0.UserOnlineStatus>? statuses,
  }) {
    final _result = create();
    if (statuses != null) {
      _result.statuses.addAll(statuses);
    }
    return _result;
  }
  factory UserOnlineStatuses.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory UserOnlineStatuses.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
      'Will be removed in next major version')
  UserOnlineStatuses clone() => UserOnlineStatuses()..mergeFromMessage(this);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
      'Will be removed in next major version')
  UserOnlineStatuses copyWith(void Function(UserOnlineStatuses) updates) =>
      super.copyWith((message) => updates(message as UserOnlineStatuses))
          as UserOnlineStatuses; // ignore: deprecated_member_use
  $pb.BuilderInfo get info_ => _i;
  @$core.pragma('dart2js:noInline')
  static UserOnlineStatuses create() => UserOnlineStatuses._();
  UserOnlineStatuses createEmptyInstance() => create();
  static $pb.PbList<UserOnlineStatuses> createRepeated() =>
      $pb.PbList<UserOnlineStatuses>();
  @$core.pragma('dart2js:noInline')
  static UserOnlineStatuses getDefault() => _defaultInstance ??=
      $pb.GeneratedMessage.$_defaultFor<UserOnlineStatuses>(create);
  static UserOnlineStatuses? _defaultInstance;

  @$pb.TagNumber(1)
  $core.List<$0.UserOnlineStatus> get statuses => $_getList(0);
}
