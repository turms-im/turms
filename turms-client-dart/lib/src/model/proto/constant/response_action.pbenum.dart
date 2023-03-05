///
//  Generated code. Do not modify.
//  source: constant/response_action.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,constant_identifier_names,directives_ordering,library_prefixes,non_constant_identifier_names,prefer_final_fields,return_of_invalid_type,unnecessary_const,unnecessary_import,unnecessary_this,unused_import,unused_shown_name

// ignore_for_file: UNDEFINED_SHOWN_NAME
import 'dart:core' as $core;
import 'package:protobuf/protobuf.dart' as $pb;

class ResponseAction extends $pb.ProtobufEnum {
  static const ResponseAction ACCEPT = ResponseAction._(
      0,
      const $core.bool.fromEnvironment('protobuf.omit_enum_names')
          ? ''
          : 'ACCEPT');
  static const ResponseAction DECLINE = ResponseAction._(
      1,
      const $core.bool.fromEnvironment('protobuf.omit_enum_names')
          ? ''
          : 'DECLINE');
  static const ResponseAction IGNORE = ResponseAction._(
      2,
      const $core.bool.fromEnvironment('protobuf.omit_enum_names')
          ? ''
          : 'IGNORE');

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
