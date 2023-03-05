///
//  Generated code. Do not modify.
//  source: model/group/group_join_request.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,constant_identifier_names,deprecated_member_use_from_same_package,directives_ordering,library_prefixes,non_constant_identifier_names,prefer_final_fields,return_of_invalid_type,unnecessary_const,unnecessary_import,unnecessary_this,unused_import,unused_shown_name

import 'dart:core' as $core;
import 'dart:convert' as $convert;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use groupJoinRequestDescriptor instead')
const GroupJoinRequest$json = const {
  '1': 'GroupJoinRequest',
  '2': const [
    const {'1': 'id', '3': 1, '4': 1, '5': 3, '9': 0, '10': 'id', '17': true},
    const {
      '1': 'creation_date',
      '3': 2,
      '4': 1,
      '5': 3,
      '9': 1,
      '10': 'creationDate',
      '17': true
    },
    const {
      '1': 'content',
      '3': 3,
      '4': 1,
      '5': 9,
      '9': 2,
      '10': 'content',
      '17': true
    },
    const {
      '1': 'status',
      '3': 4,
      '4': 1,
      '5': 14,
      '6': '.im.turms.proto.RequestStatus',
      '9': 3,
      '10': 'status',
      '17': true
    },
    const {
      '1': 'expiration_date',
      '3': 5,
      '4': 1,
      '5': 3,
      '9': 4,
      '10': 'expirationDate',
      '17': true
    },
    const {
      '1': 'group_id',
      '3': 6,
      '4': 1,
      '5': 3,
      '9': 5,
      '10': 'groupId',
      '17': true
    },
    const {
      '1': 'requester_id',
      '3': 7,
      '4': 1,
      '5': 3,
      '9': 6,
      '10': 'requesterId',
      '17': true
    },
    const {
      '1': 'responder_id',
      '3': 8,
      '4': 1,
      '5': 3,
      '9': 7,
      '10': 'responderId',
      '17': true
    },
  ],
  '8': const [
    const {'1': '_id'},
    const {'1': '_creation_date'},
    const {'1': '_content'},
    const {'1': '_status'},
    const {'1': '_expiration_date'},
    const {'1': '_group_id'},
    const {'1': '_requester_id'},
    const {'1': '_responder_id'},
  ],
};

/// Descriptor for `GroupJoinRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List groupJoinRequestDescriptor = $convert.base64Decode(
    'ChBHcm91cEpvaW5SZXF1ZXN0EhMKAmlkGAEgASgDSABSAmlkiAEBEigKDWNyZWF0aW9uX2RhdGUYAiABKANIAVIMY3JlYXRpb25EYXRliAEBEh0KB2NvbnRlbnQYAyABKAlIAlIHY29udGVudIgBARI6CgZzdGF0dXMYBCABKA4yHS5pbS50dXJtcy5wcm90by5SZXF1ZXN0U3RhdHVzSANSBnN0YXR1c4gBARIsCg9leHBpcmF0aW9uX2RhdGUYBSABKANIBFIOZXhwaXJhdGlvbkRhdGWIAQESHgoIZ3JvdXBfaWQYBiABKANIBVIHZ3JvdXBJZIgBARImCgxyZXF1ZXN0ZXJfaWQYByABKANIBlILcmVxdWVzdGVySWSIAQESJgoMcmVzcG9uZGVyX2lkGAggASgDSAdSC3Jlc3BvbmRlcklkiAEBQgUKA19pZEIQCg5fY3JlYXRpb25fZGF0ZUIKCghfY29udGVudEIJCgdfc3RhdHVzQhIKEF9leHBpcmF0aW9uX2RhdGVCCwoJX2dyb3VwX2lkQg8KDV9yZXF1ZXN0ZXJfaWRCDwoNX3Jlc3BvbmRlcl9pZA==');
