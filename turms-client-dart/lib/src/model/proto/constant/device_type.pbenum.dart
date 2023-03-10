///
//  Generated code. Do not modify.
//  source: constant/device_type.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,constant_identifier_names,directives_ordering,library_prefixes,non_constant_identifier_names,prefer_final_fields,return_of_invalid_type,unnecessary_const,unnecessary_import,unnecessary_this,unused_import,unused_shown_name

// ignore_for_file: UNDEFINED_SHOWN_NAME
import 'dart:core' as $core;
import 'package:protobuf/protobuf.dart' as $pb;

class DeviceType extends $pb.ProtobufEnum {
  static const DeviceType DESKTOP = DeviceType._(
      0,
      const $core.bool.fromEnvironment('protobuf.omit_enum_names')
          ? ''
          : 'DESKTOP');
  static const DeviceType BROWSER = DeviceType._(
      1,
      const $core.bool.fromEnvironment('protobuf.omit_enum_names')
          ? ''
          : 'BROWSER');
  static const DeviceType IOS = DeviceType._(
      2,
      const $core.bool.fromEnvironment('protobuf.omit_enum_names')
          ? ''
          : 'IOS');
  static const DeviceType ANDROID = DeviceType._(
      3,
      const $core.bool.fromEnvironment('protobuf.omit_enum_names')
          ? ''
          : 'ANDROID');
  static const DeviceType OTHERS = DeviceType._(
      4,
      const $core.bool.fromEnvironment('protobuf.omit_enum_names')
          ? ''
          : 'OTHERS');
  static const DeviceType UNKNOWN = DeviceType._(
      5,
      const $core.bool.fromEnvironment('protobuf.omit_enum_names')
          ? ''
          : 'UNKNOWN');

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
