///
//  Generated code. Do not modify.
//  source: model/user/nearby_users.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,constant_identifier_names,directives_ordering,library_prefixes,non_constant_identifier_names,prefer_final_fields,return_of_invalid_type,unnecessary_const,unnecessary_import,unnecessary_this,unused_import,unused_shown_name

import 'dart:core' as $core;

import 'package:protobuf/protobuf.dart' as $pb;

import 'nearby_user.pb.dart' as $0;

class NearbyUsers extends $pb.GeneratedMessage {
  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      const $core.bool.fromEnvironment('protobuf.omit_message_names')
          ? ''
          : 'NearbyUsers',
      package: const $pb.PackageName(
          const $core.bool.fromEnvironment('protobuf.omit_message_names')
              ? ''
              : 'im.turms.proto'),
      createEmptyInstance: create)
    ..pc<$0.NearbyUser>(
        1,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'nearbyUsers',
        $pb.PbFieldType.PM,
        subBuilder: $0.NearbyUser.create)
    ..hasRequiredFields = false;

  NearbyUsers._() : super();
  factory NearbyUsers({
    $core.Iterable<$0.NearbyUser>? nearbyUsers,
  }) {
    final _result = create();
    if (nearbyUsers != null) {
      _result.nearbyUsers.addAll(nearbyUsers);
    }
    return _result;
  }
  factory NearbyUsers.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory NearbyUsers.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
      'Will be removed in next major version')
  NearbyUsers clone() => NearbyUsers()..mergeFromMessage(this);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
      'Will be removed in next major version')
  NearbyUsers copyWith(void Function(NearbyUsers) updates) =>
      super.copyWith((message) => updates(message as NearbyUsers))
          as NearbyUsers; // ignore: deprecated_member_use
  $pb.BuilderInfo get info_ => _i;
  @$core.pragma('dart2js:noInline')
  static NearbyUsers create() => NearbyUsers._();
  NearbyUsers createEmptyInstance() => create();
  static $pb.PbList<NearbyUsers> createRepeated() => $pb.PbList<NearbyUsers>();
  @$core.pragma('dart2js:noInline')
  static NearbyUsers getDefault() => _defaultInstance ??=
      $pb.GeneratedMessage.$_defaultFor<NearbyUsers>(create);
  static NearbyUsers? _defaultInstance;

  @$pb.TagNumber(1)
  $core.List<$0.NearbyUser> get nearbyUsers => $_getList(0);
}
