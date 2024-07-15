//
//  Generated code. Do not modify.
//  source: request/group/update_group_request.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:convert' as $convert;
import 'dart:core' as $core;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use updateGroupRequestDescriptor instead')
const UpdateGroupRequest$json = {
  '1': 'UpdateGroupRequest',
  '2': [
    {'1': 'group_id', '3': 1, '4': 1, '5': 3, '10': 'groupId'},
    {
      '1': 'quit_after_transfer',
      '3': 2,
      '4': 1,
      '5': 8,
      '9': 0,
      '10': 'quitAfterTransfer',
      '17': true
    },
    {'1': 'name', '3': 3, '4': 1, '5': 9, '9': 1, '10': 'name', '17': true},
    {'1': 'intro', '3': 4, '4': 1, '5': 9, '9': 2, '10': 'intro', '17': true},
    {
      '1': 'announcement',
      '3': 5,
      '4': 1,
      '5': 9,
      '9': 3,
      '10': 'announcement',
      '17': true
    },
    {
      '1': 'min_score',
      '3': 6,
      '4': 1,
      '5': 5,
      '9': 4,
      '10': 'minScore',
      '17': true
    },
    {
      '1': 'type_id',
      '3': 7,
      '4': 1,
      '5': 3,
      '9': 5,
      '10': 'typeId',
      '17': true
    },
    {
      '1': 'mute_end_date',
      '3': 8,
      '4': 1,
      '5': 3,
      '9': 6,
      '10': 'muteEndDate',
      '17': true
    },
    {
      '1': 'successor_id',
      '3': 9,
      '4': 1,
      '5': 3,
      '9': 7,
      '10': 'successorId',
      '17': true
    },
    {
      '1': 'user_defined_attributes',
      '3': 10,
      '4': 3,
      '5': 11,
      '6': '.im.turms.proto.UpdateGroupRequest.UserDefinedAttributesEntry',
      '10': 'userDefinedAttributes'
    },
    {
      '1': 'custom_attributes',
      '3': 15,
      '4': 3,
      '5': 11,
      '6': '.im.turms.proto.Value',
      '10': 'customAttributes'
    },
  ],
  '3': [UpdateGroupRequest_UserDefinedAttributesEntry$json],
  '8': [
    {'1': '_quit_after_transfer'},
    {'1': '_name'},
    {'1': '_intro'},
    {'1': '_announcement'},
    {'1': '_min_score'},
    {'1': '_type_id'},
    {'1': '_mute_end_date'},
    {'1': '_successor_id'},
  ],
};

@$core.Deprecated('Use updateGroupRequestDescriptor instead')
const UpdateGroupRequest_UserDefinedAttributesEntry$json = {
  '1': 'UserDefinedAttributesEntry',
  '2': [
    {'1': 'key', '3': 1, '4': 1, '5': 9, '10': 'key'},
    {
      '1': 'value',
      '3': 2,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.Value',
      '10': 'value'
    },
  ],
  '7': {'7': true},
};

/// Descriptor for `UpdateGroupRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List updateGroupRequestDescriptor = $convert.base64Decode(
    'ChJVcGRhdGVHcm91cFJlcXVlc3QSGQoIZ3JvdXBfaWQYASABKANSB2dyb3VwSWQSMwoTcXVpdF'
    '9hZnRlcl90cmFuc2ZlchgCIAEoCEgAUhFxdWl0QWZ0ZXJUcmFuc2ZlcogBARIXCgRuYW1lGAMg'
    'ASgJSAFSBG5hbWWIAQESGQoFaW50cm8YBCABKAlIAlIFaW50cm+IAQESJwoMYW5ub3VuY2VtZW'
    '50GAUgASgJSANSDGFubm91bmNlbWVudIgBARIgCgltaW5fc2NvcmUYBiABKAVIBFIIbWluU2Nv'
    'cmWIAQESHAoHdHlwZV9pZBgHIAEoA0gFUgZ0eXBlSWSIAQESJwoNbXV0ZV9lbmRfZGF0ZRgIIA'
    'EoA0gGUgttdXRlRW5kRGF0ZYgBARImCgxzdWNjZXNzb3JfaWQYCSABKANIB1ILc3VjY2Vzc29y'
    'SWSIAQESdQoXdXNlcl9kZWZpbmVkX2F0dHJpYnV0ZXMYCiADKAsyPS5pbS50dXJtcy5wcm90by'
    '5VcGRhdGVHcm91cFJlcXVlc3QuVXNlckRlZmluZWRBdHRyaWJ1dGVzRW50cnlSFXVzZXJEZWZp'
    'bmVkQXR0cmlidXRlcxJCChFjdXN0b21fYXR0cmlidXRlcxgPIAMoCzIVLmltLnR1cm1zLnByb3'
    'RvLlZhbHVlUhBjdXN0b21BdHRyaWJ1dGVzGl8KGlVzZXJEZWZpbmVkQXR0cmlidXRlc0VudHJ5'
    'EhAKA2tleRgBIAEoCVIDa2V5EisKBXZhbHVlGAIgASgLMhUuaW0udHVybXMucHJvdG8uVmFsdW'
    'VSBXZhbHVlOgI4AUIWChRfcXVpdF9hZnRlcl90cmFuc2ZlckIHCgVfbmFtZUIICgZfaW50cm9C'
    'DwoNX2Fubm91bmNlbWVudEIMCgpfbWluX3Njb3JlQgoKCF90eXBlX2lkQhAKDl9tdXRlX2VuZF'
    '9kYXRlQg8KDV9zdWNjZXNzb3JfaWQ=');
