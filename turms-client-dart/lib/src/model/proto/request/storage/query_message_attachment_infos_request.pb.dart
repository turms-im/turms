///
//  Generated code. Do not modify.
//  source: request/storage/query_message_attachment_infos_request.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,constant_identifier_names,directives_ordering,library_prefixes,non_constant_identifier_names,prefer_final_fields,return_of_invalid_type,unnecessary_const,unnecessary_import,unnecessary_this,unused_import,unused_shown_name

import 'dart:core' as $core;

import 'package:fixnum/fixnum.dart' as $fixnum;
import 'package:protobuf/protobuf.dart' as $pb;

class QueryMessageAttachmentInfosRequest extends $pb.GeneratedMessage {
  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      const $core.bool.fromEnvironment('protobuf.omit_message_names')
          ? ''
          : 'QueryMessageAttachmentInfosRequest',
      package: const $pb.PackageName(
          const $core.bool.fromEnvironment('protobuf.omit_message_names')
              ? ''
              : 'im.turms.proto'),
      createEmptyInstance: create)
    ..p<$fixnum.Int64>(
        1,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'userIds',
        $pb.PbFieldType.K6)
    ..p<$fixnum.Int64>(
        2,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'groupIds',
        $pb.PbFieldType.K6)
    ..aInt64(
        3,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'creationDateStart')
    ..aInt64(
        4,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'creationDateEnd')
    ..aOB(
        5,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'inPrivateConversation')
    ..aOB(
        6,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'areSharedByMe')
    ..hasRequiredFields = false;

  QueryMessageAttachmentInfosRequest._() : super();
  factory QueryMessageAttachmentInfosRequest({
    $core.Iterable<$fixnum.Int64>? userIds,
    $core.Iterable<$fixnum.Int64>? groupIds,
    $fixnum.Int64? creationDateStart,
    $fixnum.Int64? creationDateEnd,
    $core.bool? inPrivateConversation,
    $core.bool? areSharedByMe,
  }) {
    final _result = create();
    if (userIds != null) {
      _result.userIds.addAll(userIds);
    }
    if (groupIds != null) {
      _result.groupIds.addAll(groupIds);
    }
    if (creationDateStart != null) {
      _result.creationDateStart = creationDateStart;
    }
    if (creationDateEnd != null) {
      _result.creationDateEnd = creationDateEnd;
    }
    if (inPrivateConversation != null) {
      _result.inPrivateConversation = inPrivateConversation;
    }
    if (areSharedByMe != null) {
      _result.areSharedByMe = areSharedByMe;
    }
    return _result;
  }
  factory QueryMessageAttachmentInfosRequest.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory QueryMessageAttachmentInfosRequest.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);
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
          as QueryMessageAttachmentInfosRequest; // ignore: deprecated_member_use
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
