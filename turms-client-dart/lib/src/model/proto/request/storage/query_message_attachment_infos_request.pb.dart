//
//  Generated code. Do not modify.
//  source: request/storage/query_message_attachment_infos_request.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:core' as $core;

import 'package:fixnum/fixnum.dart' as $fixnum;
import 'package:protobuf/protobuf.dart' as $pb;

class QueryMessageAttachmentInfosRequest extends $pb.GeneratedMessage {
  factory QueryMessageAttachmentInfosRequest({
    $core.Iterable<$fixnum.Int64>? userIds,
    $core.Iterable<$fixnum.Int64>? groupIds,
    $fixnum.Int64? creationDateStart,
    $fixnum.Int64? creationDateEnd,
    $core.bool? inPrivateConversation,
    $core.bool? areSharedByMe,
  }) {
    final $result = create();
    if (userIds != null) {
      $result.userIds.addAll(userIds);
    }
    if (groupIds != null) {
      $result.groupIds.addAll(groupIds);
    }
    if (creationDateStart != null) {
      $result.creationDateStart = creationDateStart;
    }
    if (creationDateEnd != null) {
      $result.creationDateEnd = creationDateEnd;
    }
    if (inPrivateConversation != null) {
      $result.inPrivateConversation = inPrivateConversation;
    }
    if (areSharedByMe != null) {
      $result.areSharedByMe = areSharedByMe;
    }
    return $result;
  }
  QueryMessageAttachmentInfosRequest._() : super();
  factory QueryMessageAttachmentInfosRequest.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory QueryMessageAttachmentInfosRequest.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);

  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      _omitMessageNames ? '' : 'QueryMessageAttachmentInfosRequest',
      package: const $pb.PackageName(_omitMessageNames ? '' : 'im.turms.proto'),
      createEmptyInstance: create)
    ..p<$fixnum.Int64>(1, _omitFieldNames ? '' : 'userIds', $pb.PbFieldType.K6)
    ..p<$fixnum.Int64>(2, _omitFieldNames ? '' : 'groupIds', $pb.PbFieldType.K6)
    ..aInt64(3, _omitFieldNames ? '' : 'creationDateStart')
    ..aInt64(4, _omitFieldNames ? '' : 'creationDateEnd')
    ..aOB(5, _omitFieldNames ? '' : 'inPrivateConversation')
    ..aOB(6, _omitFieldNames ? '' : 'areSharedByMe')
    ..hasRequiredFields = false;

  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
      'Will be removed in next major version')
  QueryMessageAttachmentInfosRequest clone() =>
      QueryMessageAttachmentInfosRequest()..mergeFromMessage(this);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
      'Will be removed in next major version')
  QueryMessageAttachmentInfosRequest copyWith(
          void Function(QueryMessageAttachmentInfosRequest) updates) =>
      super.copyWith((message) =>
              updates(message as QueryMessageAttachmentInfosRequest))
          as QueryMessageAttachmentInfosRequest;

  $pb.BuilderInfo get info_ => _i;

  @$core.pragma('dart2js:noInline')
  static QueryMessageAttachmentInfosRequest create() =>
      QueryMessageAttachmentInfosRequest._();
  QueryMessageAttachmentInfosRequest createEmptyInstance() => create();
  static $pb.PbList<QueryMessageAttachmentInfosRequest> createRepeated() =>
      $pb.PbList<QueryMessageAttachmentInfosRequest>();
  @$core.pragma('dart2js:noInline')
  static QueryMessageAttachmentInfosRequest getDefault() => _defaultInstance ??=
      $pb.GeneratedMessage.$_defaultFor<QueryMessageAttachmentInfosRequest>(
          create);
  static QueryMessageAttachmentInfosRequest? _defaultInstance;

  @$pb.TagNumber(1)
  $core.List<$fixnum.Int64> get userIds => $_getList(0);

  @$pb.TagNumber(2)
  $core.List<$fixnum.Int64> get groupIds => $_getList(1);

  @$pb.TagNumber(3)
  $fixnum.Int64 get creationDateStart => $_getI64(2);
  @$pb.TagNumber(3)
  set creationDateStart($fixnum.Int64 v) {
    $_setInt64(2, v);
  }

  @$pb.TagNumber(3)
  $core.bool hasCreationDateStart() => $_has(2);
  @$pb.TagNumber(3)
  void clearCreationDateStart() => clearField(3);

  @$pb.TagNumber(4)
  $fixnum.Int64 get creationDateEnd => $_getI64(3);
  @$pb.TagNumber(4)
  set creationDateEnd($fixnum.Int64 v) {
    $_setInt64(3, v);
  }

  @$pb.TagNumber(4)
  $core.bool hasCreationDateEnd() => $_has(3);
  @$pb.TagNumber(4)
  void clearCreationDateEnd() => clearField(4);

  @$pb.TagNumber(5)
  $core.bool get inPrivateConversation => $_getBF(4);
  @$pb.TagNumber(5)
  set inPrivateConversation($core.bool v) {
    $_setBool(4, v);
  }

  @$pb.TagNumber(5)
  $core.bool hasInPrivateConversation() => $_has(4);
  @$pb.TagNumber(5)
  void clearInPrivateConversation() => clearField(5);

  @$pb.TagNumber(6)
  $core.bool get areSharedByMe => $_getBF(5);
  @$pb.TagNumber(6)
  set areSharedByMe($core.bool v) {
    $_setBool(5, v);
  }

  @$pb.TagNumber(6)
  $core.bool hasAreSharedByMe() => $_has(5);
  @$pb.TagNumber(6)
  void clearAreSharedByMe() => clearField(6);
}

const _omitFieldNames = $core.bool.fromEnvironment('protobuf.omit_field_names');
const _omitMessageNames =
    $core.bool.fromEnvironment('protobuf.omit_message_names');
