//
//  Generated code. Do not modify.
//  source: model/common/value.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:convert' as $convert;
import 'dart:core' as $core;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use valueDescriptor instead')
const Value$json = {
  '1': 'Value',
  '2': [
    {'1': 'int32_value', '3': 1, '4': 1, '5': 5, '9': 0, '10': 'int32Value'},
    {'1': 'int64_value', '3': 2, '4': 1, '5': 3, '9': 0, '10': 'int64Value'},
    {'1': 'float_value', '3': 3, '4': 1, '5': 2, '9': 0, '10': 'floatValue'},
    {'1': 'double_value', '3': 4, '4': 1, '5': 1, '9': 0, '10': 'doubleValue'},
    {'1': 'bool_value', '3': 5, '4': 1, '5': 8, '9': 0, '10': 'boolValue'},
    {'1': 'bytes_value', '3': 6, '4': 1, '5': 12, '9': 0, '10': 'bytesValue'},
    {'1': 'string_value', '3': 7, '4': 1, '5': 9, '9': 0, '10': 'stringValue'},
    {
      '1': 'list_value',
      '3': 8,
      '4': 3,
      '5': 11,
      '6': '.im.turms.proto.Value',
      '10': 'listValue'
    },
  ],
  '8': [
    {'1': 'kind'},
  ],
};

/// Descriptor for `Value`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List valueDescriptor = $convert.base64Decode(
    'CgVWYWx1ZRIhCgtpbnQzMl92YWx1ZRgBIAEoBUgAUgppbnQzMlZhbHVlEiEKC2ludDY0X3ZhbH'
    'VlGAIgASgDSABSCmludDY0VmFsdWUSIQoLZmxvYXRfdmFsdWUYAyABKAJIAFIKZmxvYXRWYWx1'
    'ZRIjCgxkb3VibGVfdmFsdWUYBCABKAFIAFILZG91YmxlVmFsdWUSHwoKYm9vbF92YWx1ZRgFIA'
    'EoCEgAUglib29sVmFsdWUSIQoLYnl0ZXNfdmFsdWUYBiABKAxIAFIKYnl0ZXNWYWx1ZRIjCgxz'
    'dHJpbmdfdmFsdWUYByABKAlIAFILc3RyaW5nVmFsdWUSNAoKbGlzdF92YWx1ZRgIIAMoCzIVLm'
    'ltLnR1cm1zLnByb3RvLlZhbHVlUglsaXN0VmFsdWVCBgoEa2luZA==');
