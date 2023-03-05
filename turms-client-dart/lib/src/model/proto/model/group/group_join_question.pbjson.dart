///
//  Generated code. Do not modify.
//  source: model/group/group_join_question.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,constant_identifier_names,deprecated_member_use_from_same_package,directives_ordering,library_prefixes,non_constant_identifier_names,prefer_final_fields,return_of_invalid_type,unnecessary_const,unnecessary_import,unnecessary_this,unused_import,unused_shown_name

import 'dart:core' as $core;
import 'dart:convert' as $convert;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use groupJoinQuestionDescriptor instead')
const GroupJoinQuestion$json = const {
  '1': 'GroupJoinQuestion',
  '2': const [
    const {'1': 'id', '3': 1, '4': 1, '5': 3, '9': 0, '10': 'id', '17': true},
    const {
      '1': 'group_id',
      '3': 2,
      '4': 1,
      '5': 3,
      '9': 1,
      '10': 'groupId',
      '17': true
    },
    const {
      '1': 'question',
      '3': 3,
      '4': 1,
      '5': 9,
      '9': 2,
      '10': 'question',
      '17': true
    },
    const {'1': 'answers', '3': 4, '4': 3, '5': 9, '10': 'answers'},
    const {
      '1': 'score',
      '3': 5,
      '4': 1,
      '5': 5,
      '9': 3,
      '10': 'score',
      '17': true
    },
  ],
  '8': const [
    const {'1': '_id'},
    const {'1': '_group_id'},
    const {'1': '_question'},
    const {'1': '_score'},
  ],
};

/// Descriptor for `GroupJoinQuestion`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List groupJoinQuestionDescriptor = $convert.base64Decode(
    'ChFHcm91cEpvaW5RdWVzdGlvbhITCgJpZBgBIAEoA0gAUgJpZIgBARIeCghncm91cF9pZBgCIAEoA0gBUgdncm91cElkiAEBEh8KCHF1ZXN0aW9uGAMgASgJSAJSCHF1ZXN0aW9uiAEBEhgKB2Fuc3dlcnMYBCADKAlSB2Fuc3dlcnMSGQoFc2NvcmUYBSABKAVIA1IFc2NvcmWIAQFCBQoDX2lkQgsKCV9ncm91cF9pZEILCglfcXVlc3Rpb25CCAoGX3Njb3Jl');
