///
//  Generated code. Do not modify.
//  source: model/common/int64_values.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,unnecessary_const,non_constant_identifier_names,library_prefixes,unused_import,unused_shown_name,return_of_invalid_type,unnecessary_this,prefer_final_fields

import 'dart:core' as $core;

import 'package:fixnum/fixnum.dart' as $fixnum;
import 'package:protobuf/protobuf.dart' as $pb;

class Int64Values extends $pb.GeneratedMessage {
  static final $pb.BuilderInfo _i = $pb.BuilderInfo(const $core.bool.fromEnvironment('protobuf.omit_message_names') ? '' : 'Int64Values', package: const $pb.PackageName(const $core.bool.fromEnvironment('protobuf.omit_message_names') ? '' : 'im.turms.proto'), createEmptyInstance: create)
    ..p<$fixnum.Int64>(1, const $core.bool.fromEnvironment('protobuf.omit_field_names') ? '' : 'values', $pb.PbFieldType.P6)
    ..hasRequiredFields = false
  ;

  Int64Values._() : super();
  factory Int64Values({
    $core.Iterable<$fixnum.Int64>? values,
  }) {
    final _result = create();
    if (values != null) {
      _result.values.addAll(values);
    }
    return _result;
  }
  factory Int64Values.fromBuffer($core.List<$core.int> i, [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) => create()..mergeFromBuffer(i, r);
  factory Int64Values.fromJson($core.String i, [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) => create()..mergeFromJson(i, r);
  @$core.Deprecated(
  'Using this can add significant overhead to your binary. '
  'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
  'Will be removed in next major version')
  Int64Values clone() => Int64Values()..mergeFromMessage(this);
  @$core.Deprecated(
  'Using this can add significant overhead to your binary. '
  'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
  'Will be removed in next major version')
  Int64Values copyWith(void Function(Int64Values) updates) => super.copyWith((message) => updates(message as Int64Values)) as Int64Values; // ignore: deprecated_member_use
  $pb.BuilderInfo get info_ => _i;
  @$core.pragma('dart2js:noInline')
  static Int64Values create() => Int64Values._();
  Int64Values createEmptyInstance() => create();
  static $pb.PbList<Int64Values> createRepeated() => $pb.PbList<Int64Values>();
  @$core.pragma('dart2js:noInline')
  static Int64Values getDefault() => _defaultInstance ??= $pb.GeneratedMessage.$_defaultFor<Int64Values>(create);
  static Int64Values? _defaultInstance;

  @$pb.TagNumber(1)
  $core.List<$fixnum.Int64> get values => $_getList(0);
}

