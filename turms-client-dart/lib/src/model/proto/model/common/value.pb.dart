//
//  Generated code. Do not modify.
//  source: model/common/value.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:core' as $core;

import 'package:fixnum/fixnum.dart' as $fixnum;
import 'package:protobuf/protobuf.dart' as $pb;

enum Value_Kind {
  int32Value,
  int64Value,
  floatValue,
  doubleValue,
  boolValue,
  bytesValue,
  stringValue,
  notSet
}

class Value extends $pb.GeneratedMessage {
  factory Value({
    $core.int? int32Value,
    $fixnum.Int64? int64Value,
    $core.double? floatValue,
    $core.double? doubleValue,
    $core.bool? boolValue,
    $core.List<$core.int>? bytesValue,
    $core.String? stringValue,
    $core.Iterable<Value>? listValue,
  }) {
    final $result = create();
    if (int32Value != null) {
      $result.int32Value = int32Value;
    }
    if (int64Value != null) {
      $result.int64Value = int64Value;
    }
    if (floatValue != null) {
      $result.floatValue = floatValue;
    }
    if (doubleValue != null) {
      $result.doubleValue = doubleValue;
    }
    if (boolValue != null) {
      $result.boolValue = boolValue;
    }
    if (bytesValue != null) {
      $result.bytesValue = bytesValue;
    }
    if (stringValue != null) {
      $result.stringValue = stringValue;
    }
    if (listValue != null) {
      $result.listValue.addAll(listValue);
    }
    return $result;
  }
  Value._() : super();
  factory Value.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory Value.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);

  static const $core.Map<$core.int, Value_Kind> _Value_KindByTag = {
    1: Value_Kind.int32Value,
    2: Value_Kind.int64Value,
    3: Value_Kind.floatValue,
    4: Value_Kind.doubleValue,
    5: Value_Kind.boolValue,
    6: Value_Kind.bytesValue,
    7: Value_Kind.stringValue,
    0: Value_Kind.notSet
  };
  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      _omitMessageNames ? '' : 'Value',
      package: const $pb.PackageName(_omitMessageNames ? '' : 'im.turms.proto'),
      createEmptyInstance: create)
    ..oo(0, [1, 2, 3, 4, 5, 6, 7])
    ..a<$core.int>(1, _omitFieldNames ? '' : 'int32Value', $pb.PbFieldType.O3)
    ..aInt64(2, _omitFieldNames ? '' : 'int64Value')
    ..a<$core.double>(
        3, _omitFieldNames ? '' : 'floatValue', $pb.PbFieldType.OF)
    ..a<$core.double>(
        4, _omitFieldNames ? '' : 'doubleValue', $pb.PbFieldType.OD)
    ..aOB(5, _omitFieldNames ? '' : 'boolValue')
    ..a<$core.List<$core.int>>(
        6, _omitFieldNames ? '' : 'bytesValue', $pb.PbFieldType.OY)
    ..aOS(7, _omitFieldNames ? '' : 'stringValue')
    ..pc<Value>(8, _omitFieldNames ? '' : 'listValue', $pb.PbFieldType.PM,
        subBuilder: Value.create)
    ..hasRequiredFields = false;

  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
      'Will be removed in next major version')
  Value clone() => Value()..mergeFromMessage(this);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
      'Will be removed in next major version')
  Value copyWith(void Function(Value) updates) =>
      super.copyWith((message) => updates(message as Value)) as Value;

  $pb.BuilderInfo get info_ => _i;

  @$core.pragma('dart2js:noInline')
  static Value create() => Value._();
  Value createEmptyInstance() => create();
  static $pb.PbList<Value> createRepeated() => $pb.PbList<Value>();
  @$core.pragma('dart2js:noInline')
  static Value getDefault() =>
      _defaultInstance ??= $pb.GeneratedMessage.$_defaultFor<Value>(create);
  static Value? _defaultInstance;

  Value_Kind whichKind() => _Value_KindByTag[$_whichOneof(0)]!;
  void clearKind() => clearField($_whichOneof(0));

  @$pb.TagNumber(1)
  $core.int get int32Value => $_getIZ(0);
  @$pb.TagNumber(1)
  set int32Value($core.int v) {
    $_setSignedInt32(0, v);
  }

  @$pb.TagNumber(1)
  $core.bool hasInt32Value() => $_has(0);
  @$pb.TagNumber(1)
  void clearInt32Value() => clearField(1);

  @$pb.TagNumber(2)
  $fixnum.Int64 get int64Value => $_getI64(1);
  @$pb.TagNumber(2)
  set int64Value($fixnum.Int64 v) {
    $_setInt64(1, v);
  }

  @$pb.TagNumber(2)
  $core.bool hasInt64Value() => $_has(1);
  @$pb.TagNumber(2)
  void clearInt64Value() => clearField(2);

  @$pb.TagNumber(3)
  $core.double get floatValue => $_getN(2);
  @$pb.TagNumber(3)
  set floatValue($core.double v) {
    $_setFloat(2, v);
  }

  @$pb.TagNumber(3)
  $core.bool hasFloatValue() => $_has(2);
  @$pb.TagNumber(3)
  void clearFloatValue() => clearField(3);

  @$pb.TagNumber(4)
  $core.double get doubleValue => $_getN(3);
  @$pb.TagNumber(4)
  set doubleValue($core.double v) {
    $_setDouble(3, v);
  }

  @$pb.TagNumber(4)
  $core.bool hasDoubleValue() => $_has(3);
  @$pb.TagNumber(4)
  void clearDoubleValue() => clearField(4);

  @$pb.TagNumber(5)
  $core.bool get boolValue => $_getBF(4);
  @$pb.TagNumber(5)
  set boolValue($core.bool v) {
    $_setBool(4, v);
  }

  @$pb.TagNumber(5)
  $core.bool hasBoolValue() => $_has(4);
  @$pb.TagNumber(5)
  void clearBoolValue() => clearField(5);

  @$pb.TagNumber(6)
  $core.List<$core.int> get bytesValue => $_getN(5);
  @$pb.TagNumber(6)
  set bytesValue($core.List<$core.int> v) {
    $_setBytes(5, v);
  }

  @$pb.TagNumber(6)
  $core.bool hasBytesValue() => $_has(5);
  @$pb.TagNumber(6)
  void clearBytesValue() => clearField(6);

  @$pb.TagNumber(7)
  $core.String get stringValue => $_getSZ(6);
  @$pb.TagNumber(7)
  set stringValue($core.String v) {
    $_setString(6, v);
  }

  @$pb.TagNumber(7)
  $core.bool hasStringValue() => $_has(6);
  @$pb.TagNumber(7)
  void clearStringValue() => clearField(7);

  @$pb.TagNumber(8)
  $core.List<Value> get listValue => $_getList(7);
}

const _omitFieldNames = $core.bool.fromEnvironment('protobuf.omit_field_names');
const _omitMessageNames =
    $core.bool.fromEnvironment('protobuf.omit_message_names');
