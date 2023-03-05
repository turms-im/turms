///
//  Generated code. Do not modify.
//  source: request/storage/update_message_attachment_info_request.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,constant_identifier_names,directives_ordering,library_prefixes,non_constant_identifier_names,prefer_final_fields,return_of_invalid_type,unnecessary_const,unnecessary_import,unnecessary_this,unused_import,unused_shown_name

import 'dart:core' as $core;

import 'package:fixnum/fixnum.dart' as $fixnum;
import 'package:protobuf/protobuf.dart' as $pb;

class UpdateMessageAttachmentInfoRequest extends $pb.GeneratedMessage {
  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      const $core.bool.fromEnvironment('protobuf.omit_message_names')
          ? ''
          : 'UpdateMessageAttachmentInfoRequest',
      package: const $pb.PackageName(
          const $core.bool.fromEnvironment('protobuf.omit_message_names')
              ? ''
              : 'im.turms.proto'),
      createEmptyInstance: create)
    ..aInt64(
        1,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'attachmentIdNum')
    ..aOS(
        2,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'attachmentIdStr')
    ..aInt64(
        3,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'userIdToShareWith')
    ..aInt64(
        4,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'userIdToUnshareWith')
    ..aInt64(
        5,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'groupIdToShareWith')
    ..aInt64(
        6,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'groupIdToUnshareWith')
    ..hasRequiredFields = false;

  UpdateMessageAttachmentInfoRequest._() : super();
  factory UpdateMessageAttachmentInfoRequest({
    $fixnum.Int64? attachmentIdNum,
    $core.String? attachmentIdStr,
    $fixnum.Int64? userIdToShareWith,
    $fixnum.Int64? userIdToUnshareWith,
    $fixnum.Int64? groupIdToShareWith,
    $fixnum.Int64? groupIdToUnshareWith,
  }) {
    final _result = create();
    if (attachmentIdNum != null) {
      _result.attachmentIdNum = attachmentIdNum;
    }
    if (attachmentIdStr != null) {
      _result.attachmentIdStr = attachmentIdStr;
    }
    if (userIdToShareWith != null) {
      _result.userIdToShareWith = userIdToShareWith;
    }
    if (userIdToUnshareWith != null) {
      _result.userIdToUnshareWith = userIdToUnshareWith;
    }
    if (groupIdToShareWith != null) {
      _result.groupIdToShareWith = groupIdToShareWith;
    }
    if (groupIdToUnshareWith != null) {
      _result.groupIdToUnshareWith = groupIdToUnshareWith;
    }
    return _result;
  }
  factory UpdateMessageAttachmentInfoRequest.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory UpdateMessageAttachmentInfoRequest.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);
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
          as UpdateMessageAttachmentInfoRequest; // ignore: deprecated_member_use
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
}
