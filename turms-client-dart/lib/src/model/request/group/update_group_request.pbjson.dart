///
//  Generated code. Do not modify.
//  source: request/group/update_group_request.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,unnecessary_const,non_constant_identifier_names,library_prefixes,unused_import,unused_shown_name,return_of_invalid_type,unnecessary_this,prefer_final_fields,deprecated_member_use_from_same_package

import 'dart:core' as $core;
import 'dart:convert' as $convert;
import 'dart:typed_data' as $typed_data;
@$core.Deprecated('Use updateGroupRequestDescriptor instead')
const UpdateGroupRequest$json = const {
  '1': 'UpdateGroupRequest',
  '2': const [
    const {'1': 'group_id', '3': 1, '4': 1, '5': 3, '10': 'groupId'},
    const {'1': 'group_name', '3': 2, '4': 1, '5': 9, '9': 0, '10': 'groupName', '17': true},
    const {'1': 'intro', '3': 3, '4': 1, '5': 9, '9': 1, '10': 'intro', '17': true},
    const {'1': 'announcement', '3': 4, '4': 1, '5': 9, '9': 2, '10': 'announcement', '17': true},
    const {'1': 'minimum_score', '3': 5, '4': 1, '5': 5, '9': 3, '10': 'minimumScore', '17': true},
    const {'1': 'group_type_id', '3': 6, '4': 1, '5': 3, '9': 4, '10': 'groupTypeId', '17': true},
    const {'1': 'mute_end_date', '3': 7, '4': 1, '5': 3, '9': 5, '10': 'muteEndDate', '17': true},
    const {'1': 'successor_id', '3': 8, '4': 1, '5': 3, '9': 6, '10': 'successorId', '17': true},
    const {'1': 'quit_after_transfer', '3': 9, '4': 1, '5': 8, '9': 7, '10': 'quitAfterTransfer', '17': true},
  ],
  '8': const [
    const {'1': '_group_name'},
    const {'1': '_intro'},
    const {'1': '_announcement'},
    const {'1': '_minimum_score'},
    const {'1': '_group_type_id'},
    const {'1': '_mute_end_date'},
    const {'1': '_successor_id'},
    const {'1': '_quit_after_transfer'},
  ],
};

/// Descriptor for `UpdateGroupRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List updateGroupRequestDescriptor = $convert.base64Decode('ChJVcGRhdGVHcm91cFJlcXVlc3QSGQoIZ3JvdXBfaWQYASABKANSB2dyb3VwSWQSIgoKZ3JvdXBfbmFtZRgCIAEoCUgAUglncm91cE5hbWWIAQESGQoFaW50cm8YAyABKAlIAVIFaW50cm+IAQESJwoMYW5ub3VuY2VtZW50GAQgASgJSAJSDGFubm91bmNlbWVudIgBARIoCg1taW5pbXVtX3Njb3JlGAUgASgFSANSDG1pbmltdW1TY29yZYgBARInCg1ncm91cF90eXBlX2lkGAYgASgDSARSC2dyb3VwVHlwZUlkiAEBEicKDW11dGVfZW5kX2RhdGUYByABKANIBVILbXV0ZUVuZERhdGWIAQESJgoMc3VjY2Vzc29yX2lkGAggASgDSAZSC3N1Y2Nlc3NvcklkiAEBEjMKE3F1aXRfYWZ0ZXJfdHJhbnNmZXIYCSABKAhIB1IRcXVpdEFmdGVyVHJhbnNmZXKIAQFCDQoLX2dyb3VwX25hbWVCCAoGX2ludHJvQg8KDV9hbm5vdW5jZW1lbnRCEAoOX21pbmltdW1fc2NvcmVCEAoOX2dyb3VwX3R5cGVfaWRCEAoOX211dGVfZW5kX2RhdGVCDwoNX3N1Y2Nlc3Nvcl9pZEIWChRfcXVpdF9hZnRlcl90cmFuc2Zlcg==');
