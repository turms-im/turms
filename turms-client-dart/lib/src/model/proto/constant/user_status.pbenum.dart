///
//  Generated code. Do not modify.
//  source: constant/user_status.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,constant_identifier_names,directives_ordering,library_prefixes,non_constant_identifier_names,prefer_final_fields,return_of_invalid_type,unnecessary_const,unnecessary_import,unnecessary_this,unused_import,unused_shown_name

// ignore_for_file: UNDEFINED_SHOWN_NAME
import 'dart:core' as $core;
import 'package:protobuf/protobuf.dart' as $pb;

class UserStatus extends $pb.ProtobufEnum {
  static const UserStatus AVAILABLE = UserStatus._(
      0,
      const $core.bool.fromEnvironment('protobuf.omit_enum_names')
          ? ''
          : 'AVAILABLE');
  static const UserStatus OFFLINE = UserStatus._(
      1,
      const $core.bool.fromEnvironment('protobuf.omit_enum_names')
          ? ''
          : 'OFFLINE');
  static const UserStatus INVISIBLE = UserStatus._(
      2,
      const $core.bool.fromEnvironment('protobuf.omit_enum_names')
          ? ''
          : 'INVISIBLE');
  static const UserStatus BUSY = UserStatus._(
      3,
      const $core.bool.fromEnvironment('protobuf.omit_enum_names')
          ? ''
          : 'BUSY');
  static const UserStatus DO_NOT_DISTURB = UserStatus._(
      4,
      const $core.bool.fromEnvironment('protobuf.omit_enum_names')
          ? ''
          : 'DO_NOT_DISTURB');
  static const UserStatus AWAY = UserStatus._(
      5,
      const $core.bool.fromEnvironment('protobuf.omit_enum_names')
          ? ''
          : 'AWAY');
  static const UserStatus BE_RIGHT_BACK = UserStatus._(
      6,
      const $core.bool.fromEnvironment('protobuf.omit_enum_names')
          ? ''
          : 'BE_RIGHT_BACK');

  static const $core.List<UserStatus> values = <UserStatus>[
    AVAILABLE,
    OFFLINE,
    INVISIBLE,
    BUSY,
    DO_NOT_DISTURB,
    AWAY,
    BE_RIGHT_BACK,
  ];

  static final $core.Map<$core.int, UserStatus> _byValue =
      $pb.ProtobufEnum.initByValue(values);
  static UserStatus? valueOf($core.int value) => _byValue[value];

  const UserStatus._($core.int v, $core.String n) : super(v, n);
}
