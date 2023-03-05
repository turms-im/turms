///
//  Generated code. Do not modify.
//  source: model/group/group_invitation.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,constant_identifier_names,deprecated_member_use_from_same_package,directives_ordering,library_prefixes,non_constant_identifier_names,prefer_final_fields,return_of_invalid_type,unnecessary_const,unnecessary_import,unnecessary_this,unused_import,unused_shown_name

import 'dart:core' as $core;
import 'dart:convert' as $convert;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use groupInvitationDescriptor instead')
const GroupInvitation$json = const {
  '1': 'GroupInvitation',
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
      '1': 'inviter_id',
      '3': 7,
      '4': 1,
      '5': 3,
      '9': 6,
      '10': 'inviterId',
      '17': true
    },
    const {
      '1': 'invitee_id',
      '3': 8,
      '4': 1,
      '5': 3,
      '9': 7,
      '10': 'inviteeId',
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
    const {'1': '_inviter_id'},
    const {'1': '_invitee_id'},
  ],
};

/// Descriptor for `GroupInvitation`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List groupInvitationDescriptor = $convert.base64Decode(
    'Cg9Hcm91cEludml0YXRpb24SEwoCaWQYASABKANIAFICaWSIAQESKAoNY3JlYXRpb25fZGF0ZRgCIAEoA0gBUgxjcmVhdGlvbkRhdGWIAQESHQoHY29udGVudBgDIAEoCUgCUgdjb250ZW50iAEBEjoKBnN0YXR1cxgEIAEoDjIdLmltLnR1cm1zLnByb3RvLlJlcXVlc3RTdGF0dXNIA1IGc3RhdHVziAEBEiwKD2V4cGlyYXRpb25fZGF0ZRgFIAEoA0gEUg5leHBpcmF0aW9uRGF0ZYgBARIeCghncm91cF9pZBgGIAEoA0gFUgdncm91cElkiAEBEiIKCmludml0ZXJfaWQYByABKANIBlIJaW52aXRlcklkiAEBEiIKCmludml0ZWVfaWQYCCABKANIB1IJaW52aXRlZUlkiAEBQgUKA19pZEIQCg5fY3JlYXRpb25fZGF0ZUIKCghfY29udGVudEIJCgdfc3RhdHVzQhIKEF9leHBpcmF0aW9uX2RhdGVCCwoJX2dyb3VwX2lkQg0KC19pbnZpdGVyX2lkQg0KC19pbnZpdGVlX2lk');
