//
//  Generated code. Do not modify.
//  source: constant/user_status.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:core' as $core;

import 'package:protobuf/protobuf.dart' as $pb;

class UserStatus extends $pb.ProtobufEnum {
  static const UserStatus AVAILABLE =
      UserStatus._(0, _omitEnumNames ? '' : 'AVAILABLE');
  static const UserStatus OFFLINE =
      UserStatus._(1, _omitEnumNames ? '' : 'OFFLINE');
  static const UserStatus INVISIBLE =
      UserStatus._(2, _omitEnumNames ? '' : 'INVISIBLE');
  static const UserStatus BUSY = UserStatus._(3, _omitEnumNames ? '' : 'BUSY');
  static const UserStatus DO_NOT_DISTURB =
      UserStatus._(4, _omitEnumNames ? '' : 'DO_NOT_DISTURB');
  static const UserStatus AWAY = UserStatus._(5, _omitEnumNames ? '' : 'AWAY');
  static const UserStatus BE_RIGHT_BACK =
      UserStatus._(6, _omitEnumNames ? '' : 'BE_RIGHT_BACK');

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

const _omitEnumNames = $core.bool.fromEnvironment('protobuf.omit_enum_names');
