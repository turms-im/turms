//
//  Generated code. Do not modify.
//  source: request/storage/update_message_attachment_info_request.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:core' as $core;

import 'package:fixnum/fixnum.dart' as $fixnum;
import 'package:protobuf/protobuf.dart' as $pb;

import '../../model/common/value.pb.dart' as $0;

class UpdateMessageAttachmentInfoRequest extends $pb.GeneratedMessage {
  factory UpdateMessageAttachmentInfoRequest({
    $fixnum.Int64? attachmentIdNum,
    $core.String? attachmentIdStr,
    $fixnum.Int64? userIdToShareWith,
    $fixnum.Int64? userIdToUnshareWith,
    $fixnum.Int64? groupIdToShareWith,
    $fixnum.Int64? groupIdToUnshareWith,
    $core.Iterable<$0.Value>? customAttributes,
  }) {
    final $result = create();
    if (attachmentIdNum != null) {
      $result.attachmentIdNum = attachmentIdNum;
    }
    if (attachmentIdStr != null) {
      $result.attachmentIdStr = attachmentIdStr;
    }
    if (userIdToShareWith != null) {
      $result.userIdToShareWith = userIdToShareWith;
    }
    if (userIdToUnshareWith != null) {
      $result.userIdToUnshareWith = userIdToUnshareWith;
    }
    if (groupIdToShareWith != null) {
      $result.groupIdToShareWith = groupIdToShareWith;
    }
    if (groupIdToUnshareWith != null) {
      $result.groupIdToUnshareWith = groupIdToUnshareWith;
    }
    if (customAttributes != null) {
      $result.customAttributes.addAll(customAttributes);
    }
    return $result;
  }
  UpdateMessageAttachmentInfoRequest._() : super();
  factory UpdateMessageAttachmentInfoRequest.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory UpdateMessageAttachmentInfoRequest.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);

  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      _omitMessageNames ? '' : 'UpdateMessageAttachmentInfoRequest',
      package: const $pb.PackageName(_omitMessageNames ? '' : 'im.turms.proto'),
      createEmptyInstance: create)
    ..aInt64(1, _omitFieldNames ? '' : 'attachmentIdNum')
    ..aOS(2, _omitFieldNames ? '' : 'attachmentIdStr')
    ..aInt64(3, _omitFieldNames ? '' : 'userIdToShareWith')
    ..aInt64(4, _omitFieldNames ? '' : 'userIdToUnshareWith')
    ..aInt64(5, _omitFieldNames ? '' : 'groupIdToShareWith')
    ..aInt64(6, _omitFieldNames ? '' : 'groupIdToUnshareWith')
    ..pc<$0.Value>(
        15, _omitFieldNames ? '' : 'customAttributes', $pb.PbFieldType.PM,
        subBuilder: $0.Value.create)
    ..hasRequiredFields = false;

  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
      'Will be removed in next major version')
  UpdateMessageAttachmentInfoRequest clone() =>
      UpdateMessageAttachmentInfoRequest()..mergeFromMessage(this);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
      'Will be removed in next major version')
  UpdateMessageAttachmentInfoRequest copyWith(
          void Function(UpdateMessageAttachmentInfoRequest) updates) =>
      super.copyWith((message) =>
              updates(message as UpdateMessageAttachmentInfoRequest))
          as UpdateMessageAttachmentInfoRequest;

  $pb.BuilderInfo get info_ => _i;

  @$core.pragma('dart2js:noInline')
  static UpdateMessageAttachmentInfoRequest create() =>
      UpdateMessageAttachmentInfoRequest._();
  UpdateMessageAttachmentInfoRequest createEmptyInstance() => create();
  static $pb.PbList<UpdateMessageAttachmentInfoRequest> createRepeated() =>
      $pb.PbList<UpdateMessageAttachmentInfoRequest>();
  @$core.pragma('dart2js:noInline')
  static UpdateMessageAttachmentInfoRequest getDefault() => _defaultInstance ??=
      $pb.GeneratedMessage.$_defaultFor<UpdateMessageAttachmentInfoRequest>(
          create);
  static UpdateMessageAttachmentInfoRequest? _defaultInstance;

  /// Query filter
  @$pb.TagNumber(1)
  $fixnum.Int64 get attachmentIdNum => $_getI64(0);
  @$pb.TagNumber(1)
  set attachmentIdNum($fixnum.Int64 v) {
    $_setInt64(0, v);
  }

  @$pb.TagNumber(1)
  $core.bool hasAttachmentIdNum() => $_has(0);
  @$pb.TagNumber(1)
  void clearAttachmentIdNum() => clearField(1);

  @$pb.TagNumber(2)
  $core.String get attachmentIdStr => $_getSZ(1);
  @$pb.TagNumber(2)
  set attachmentIdStr($core.String v) {
    $_setString(1, v);
  }

  @$pb.TagNumber(2)
  $core.bool hasAttachmentIdStr() => $_has(1);
  @$pb.TagNumber(2)
  void clearAttachmentIdStr() => clearField(2);

  /// Update
  @$pb.TagNumber(3)
  $fixnum.Int64 get userIdToShareWith => $_getI64(2);
  @$pb.TagNumber(3)
  set userIdToShareWith($fixnum.Int64 v) {
    $_setInt64(2, v);
  }

  @$pb.TagNumber(3)
  $core.bool hasUserIdToShareWith() => $_has(2);
  @$pb.TagNumber(3)
  void clearUserIdToShareWith() => clearField(3);

  @$pb.TagNumber(4)
  $fixnum.Int64 get userIdToUnshareWith => $_getI64(3);
  @$pb.TagNumber(4)
  set userIdToUnshareWith($fixnum.Int64 v) {
    $_setInt64(3, v);
  }

  @$pb.TagNumber(4)
  $core.bool hasUserIdToUnshareWith() => $_has(3);
  @$pb.TagNumber(4)
  void clearUserIdToUnshareWith() => clearField(4);

  @$pb.TagNumber(5)
  $fixnum.Int64 get groupIdToShareWith => $_getI64(4);
  @$pb.TagNumber(5)
  set groupIdToShareWith($fixnum.Int64 v) {
    $_setInt64(4, v);
  }

  @$pb.TagNumber(5)
  $core.bool hasGroupIdToShareWith() => $_has(4);
  @$pb.TagNumber(5)
  void clearGroupIdToShareWith() => clearField(5);

  @$pb.TagNumber(6)
  $fixnum.Int64 get groupIdToUnshareWith => $_getI64(5);
  @$pb.TagNumber(6)
  set groupIdToUnshareWith($fixnum.Int64 v) {
    $_setInt64(5, v);
  }

  @$pb.TagNumber(6)
  $core.bool hasGroupIdToUnshareWith() => $_has(5);
  @$pb.TagNumber(6)
  void clearGroupIdToUnshareWith() => clearField(6);

  @$pb.TagNumber(15)
  $core.List<$0.Value> get customAttributes => $_getList(6);
}

const _omitFieldNames = $core.bool.fromEnvironment('protobuf.omit_field_names');
const _omitMessageNames =
    $core.bool.fromEnvironment('protobuf.omit_message_names');
