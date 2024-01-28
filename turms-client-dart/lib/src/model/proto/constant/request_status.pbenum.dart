//
//  Generated code. Do not modify.
//  source: constant/request_status.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:core' as $core;

import 'package:protobuf/protobuf.dart' as $pb;

class RequestStatus extends $pb.ProtobufEnum {
  static const RequestStatus PENDING =
      RequestStatus._(0, _omitEnumNames ? '' : 'PENDING');
  static const RequestStatus ACCEPTED =
      RequestStatus._(1, _omitEnumNames ? '' : 'ACCEPTED');
  static const RequestStatus ACCEPTED_WITHOUT_CONFIRM =
      RequestStatus._(2, _omitEnumNames ? '' : 'ACCEPTED_WITHOUT_CONFIRM');
  static const RequestStatus DECLINED =
      RequestStatus._(3, _omitEnumNames ? '' : 'DECLINED');
  static const RequestStatus IGNORED =
      RequestStatus._(4, _omitEnumNames ? '' : 'IGNORED');
  static const RequestStatus EXPIRED =
      RequestStatus._(5, _omitEnumNames ? '' : 'EXPIRED');
  static const RequestStatus CANCELED =
      RequestStatus._(6, _omitEnumNames ? '' : 'CANCELED');

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

const _omitEnumNames = $core.bool.fromEnvironment('protobuf.omit_enum_names');
