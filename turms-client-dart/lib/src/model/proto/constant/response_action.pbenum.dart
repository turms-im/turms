//
//  Generated code. Do not modify.
//  source: constant/response_action.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:core' as $core;

import 'package:protobuf/protobuf.dart' as $pb;

class ResponseAction extends $pb.ProtobufEnum {
  static const ResponseAction ACCEPT =
      ResponseAction._(0, _omitEnumNames ? '' : 'ACCEPT');
  static const ResponseAction DECLINE =
      ResponseAction._(1, _omitEnumNames ? '' : 'DECLINE');
  static const ResponseAction IGNORE =
      ResponseAction._(2, _omitEnumNames ? '' : 'IGNORE');

  static const $core.List<ResponseAction> values = <ResponseAction>[
    ACCEPT,
    DECLINE,
    IGNORE,
  ];

  static final $core.Map<$core.int, ResponseAction> _byValue =
      $pb.ProtobufEnum.initByValue(values);
  static ResponseAction? valueOf($core.int value) => _byValue[value];

  const ResponseAction._($core.int v, $core.String n) : super(v, n);
}

const _omitEnumNames = $core.bool.fromEnvironment('protobuf.omit_enum_names');
