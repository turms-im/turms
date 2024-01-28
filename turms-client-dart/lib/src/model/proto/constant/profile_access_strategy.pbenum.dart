//
//  Generated code. Do not modify.
//  source: constant/profile_access_strategy.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:core' as $core;

import 'package:protobuf/protobuf.dart' as $pb;

class ProfileAccessStrategy extends $pb.ProtobufEnum {
  static const ProfileAccessStrategy ALL =
      ProfileAccessStrategy._(0, _omitEnumNames ? '' : 'ALL');
  static const ProfileAccessStrategy ALL_EXCEPT_BLOCKED_USERS =
      ProfileAccessStrategy._(
          1, _omitEnumNames ? '' : 'ALL_EXCEPT_BLOCKED_USERS');
  static const ProfileAccessStrategy FRIENDS =
      ProfileAccessStrategy._(2, _omitEnumNames ? '' : 'FRIENDS');

  static const $core.List<ProfileAccessStrategy> values =
      <ProfileAccessStrategy>[
    ALL,
    ALL_EXCEPT_BLOCKED_USERS,
    FRIENDS,
  ];

  static final $core.Map<$core.int, ProfileAccessStrategy> _byValue =
      $pb.ProtobufEnum.initByValue(values);
  static ProfileAccessStrategy? valueOf($core.int value) => _byValue[value];

  const ProfileAccessStrategy._($core.int v, $core.String n) : super(v, n);
}

const _omitEnumNames = $core.bool.fromEnvironment('protobuf.omit_enum_names');
