//
//  Generated code. Do not modify.
//  source: constant/device_type.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:core' as $core;

import 'package:protobuf/protobuf.dart' as $pb;

class DeviceType extends $pb.ProtobufEnum {
  static const DeviceType DESKTOP =
      DeviceType._(0, _omitEnumNames ? '' : 'DESKTOP');
  static const DeviceType BROWSER =
      DeviceType._(1, _omitEnumNames ? '' : 'BROWSER');
  static const DeviceType IOS = DeviceType._(2, _omitEnumNames ? '' : 'IOS');
  static const DeviceType ANDROID =
      DeviceType._(3, _omitEnumNames ? '' : 'ANDROID');
  static const DeviceType OTHERS =
      DeviceType._(4, _omitEnumNames ? '' : 'OTHERS');
  static const DeviceType UNKNOWN =
      DeviceType._(5, _omitEnumNames ? '' : 'UNKNOWN');

  static const $core.List<DeviceType> values = <DeviceType>[
    DESKTOP,
    BROWSER,
    IOS,
    ANDROID,
    OTHERS,
    UNKNOWN,
  ];

  static final $core.Map<$core.int, DeviceType> _byValue =
      $pb.ProtobufEnum.initByValue(values);
  static DeviceType? valueOf($core.int value) => _byValue[value];

  const DeviceType._($core.int v, $core.String n) : super(v, n);
}

const _omitEnumNames = $core.bool.fromEnvironment('protobuf.omit_enum_names');
