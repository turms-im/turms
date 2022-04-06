///
//  Generated code. Do not modify.
//  source: model/common/int64_values_with_version.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,unnecessary_const,non_constant_identifier_names,library_prefixes,unused_import,unused_shown_name,return_of_invalid_type,unnecessary_this,prefer_final_fields

import 'dart:core' as $core;

import 'package:fixnum/fixnum.dart' as $fixnum;
import 'package:protobuf/protobuf.dart' as $pb;

class Int64ValuesWithVersion extends $pb.GeneratedMessage {
  static final $pb.BuilderInfo _i = $pb.BuilderInfo(const $core.bool.fromEnvironment('protobuf.omit_message_names') ? '' : 'Int64ValuesWithVersion', package: const $pb.PackageName(const $core.bool.fromEnvironment('protobuf.omit_message_names') ? '' : 'im.turms.proto'), createEmptyInstance: create)
    ..p<$fixnum.Int64>(1, const $core.bool.fromEnvironment('protobuf.omit_field_names') ? '' : 'values', $pb.PbFieldType.P6)
    ..aInt64(2, const $core.bool.fromEnvironment('protobuf.omit_field_names') ? '' : 'lastUpdatedDate')
    ..hasRequiredFields = false
  ;

  Int64ValuesWithVersion._() : super();
  factory Int64ValuesWithVersion({
    $core.Iterable<$fixnum.Int64>? values,
    $fixnum.Int64? lastUpdatedDate,
  }) {
    final _result = create();
    if (values != null) {
      _result.values.addAll(values);
    }
    if (lastUpdatedDate != null) {
      _result.lastUpdatedDate = lastUpdatedDate;
    }
    return _result;
  }
  factory Int64ValuesWithVersion.fromBuffer($core.List<$core.int> i, [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) => create()..mergeFromBuffer(i, r);
  factory Int64ValuesWithVersion.fromJson($core.String i, [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) => create()..mergeFromJson(i, r);
  @$core.Deprecated(
  'Using this can add significant overhead to your binary. '
  'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
  'Will be removed in next major version')
  Int64ValuesWithVersion clone() => Int64ValuesWithVersion()..mergeFromMessage(this);
  @$core.Deprecated(
  'Using this can add significant overhead to your binary. '
  'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
  'Will be removed in next major version')
  Int64ValuesWithVersion copyWith(void Function(Int64ValuesWithVersion) updates) => super.copyWith((message) => updates(message as Int64ValuesWithVersion)) as Int64ValuesWithVersion; // ignore: deprecated_member_use
  $pb.BuilderInfo get info_ => _i;
  @$core.pragma('dart2js:noInline')
  static Int64ValuesWithVersion create() => Int64ValuesWithVersion._();
  Int64ValuesWithVersion createEmptyInstance() => create();
  static $pb.PbList<Int64ValuesWithVersion> createRepeated() => $pb.PbList<Int64ValuesWithVersion>();
  @$core.pragma('dart2js:noInline')
  static Int64ValuesWithVersion getDefault() => _defaultInstance ??= $pb.GeneratedMessage.$_defaultFor<Int64ValuesWithVersion>(create);
  static Int64ValuesWithVersion? _defaultInstance;

  @$pb.TagNumber(1)
  $core.List<$fixnum.Int64> get values => $_getList(0);

  @$pb.TagNumber(2)
  $fixnum.Int64 get lastUpdatedDate => $_getI64(1);
  @$pb.TagNumber(2)
  set lastUpdatedDate($fixnum.Int64 v) { $_setInt64(1, v); }
  @$pb.TagNumber(2)
  $core.bool hasLastUpdatedDate() => $_has(1);
  @$pb.TagNumber(2)
  void clearLastUpdatedDate() => clearField(2);
}

