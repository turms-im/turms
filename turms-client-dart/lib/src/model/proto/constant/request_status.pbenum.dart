///
//  Generated code. Do not modify.
//  source: constant/request_status.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,constant_identifier_names,directives_ordering,library_prefixes,non_constant_identifier_names,prefer_final_fields,return_of_invalid_type,unnecessary_const,unnecessary_import,unnecessary_this,unused_import,unused_shown_name

// ignore_for_file: UNDEFINED_SHOWN_NAME
import 'dart:core' as $core;
import 'package:protobuf/protobuf.dart' as $pb;

class RequestStatus extends $pb.ProtobufEnum {
  static const RequestStatus PENDING = RequestStatus._(
      0,
      const $core.bool.fromEnvironment('protobuf.omit_enum_names')
          ? ''
          : 'PENDING');
  static const RequestStatus ACCEPTED = RequestStatus._(
      1,
      const $core.bool.fromEnvironment('protobuf.omit_enum_names')
          ? ''
          : 'ACCEPTED');
  static const RequestStatus ACCEPTED_WITHOUT_CONFIRM = RequestStatus._(
      2,
      const $core.bool.fromEnvironment('protobuf.omit_enum_names')
          ? ''
          : 'ACCEPTED_WITHOUT_CONFIRM');
  static const RequestStatus DECLINED = RequestStatus._(
      3,
      const $core.bool.fromEnvironment('protobuf.omit_enum_names')
          ? ''
          : 'DECLINED');
  static const RequestStatus IGNORED = RequestStatus._(
      4,
      const $core.bool.fromEnvironment('protobuf.omit_enum_names')
          ? ''
          : 'IGNORED');
  static const RequestStatus EXPIRED = RequestStatus._(
      5,
      const $core.bool.fromEnvironment('protobuf.omit_enum_names')
          ? ''
          : 'EXPIRED');
  static const RequestStatus CANCELED = RequestStatus._(
      6,
      const $core.bool.fromEnvironment('protobuf.omit_enum_names')
          ? ''
          : 'CANCELED');

  static const $core.List<RequestStatus> values = <RequestStatus>[
    PENDING,
    ACCEPTED,
    ACCEPTED_WITHOUT_CONFIRM,
    DECLINED,
    IGNORED,
    EXPIRED,
    CANCELED,
  ];

  static final $core.Map<$core.int, RequestStatus> _byValue =
      $pb.ProtobufEnum.initByValue(values);
  static RequestStatus? valueOf($core.int value) => _byValue[value];

  const RequestStatus._($core.int v, $core.String n) : super(v, n);
}
