///
//  Generated code. Do not modify.
//  source: request/user/relationship/query_friend_requests_request.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,constant_identifier_names,directives_ordering,library_prefixes,non_constant_identifier_names,prefer_final_fields,return_of_invalid_type,unnecessary_const,unnecessary_import,unnecessary_this,unused_import,unused_shown_name

import 'dart:core' as $core;

import 'package:fixnum/fixnum.dart' as $fixnum;
import 'package:protobuf/protobuf.dart' as $pb;

class QueryFriendRequestsRequest extends $pb.GeneratedMessage {
  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      const $core.bool.fromEnvironment('protobuf.omit_message_names')
          ? ''
          : 'QueryFriendRequestsRequest',
      package: const $pb.PackageName(
          const $core.bool.fromEnvironment('protobuf.omit_message_names')
              ? ''
              : 'im.turms.proto'),
      createEmptyInstance: create)
    ..aOB(
        1,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'areSentByMe')
    ..aInt64(
        2,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'lastUpdatedDate')
    ..hasRequiredFields = false;

  QueryFriendRequestsRequest._() : super();
  factory QueryFriendRequestsRequest({
    $core.bool? areSentByMe,
    $fixnum.Int64? lastUpdatedDate,
  }) {
    final _result = create();
    if (areSentByMe != null) {
      _result.areSentByMe = areSentByMe;
    }
    if (lastUpdatedDate != null) {
      _result.lastUpdatedDate = lastUpdatedDate;
    }
    return _result;
  }
  factory QueryFriendRequestsRequest.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory QueryFriendRequestsRequest.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
      'Will be removed in next major version')
  QueryFriendRequestsRequest clone() =>
      QueryFriendRequestsRequest()..mergeFromMessage(this);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
      'Will be removed in next major version')
  QueryFriendRequestsRequest copyWith(
          void Function(QueryFriendRequestsRequest) updates) =>
      super.copyWith(
              (message) => updates(message as QueryFriendRequestsRequest))
          as QueryFriendRequestsRequest; // ignore: deprecated_member_use
  $pb.BuilderInfo get info_ => _i;
  @$core.pragma('dart2js:noInline')
  static QueryFriendRequestsRequest create() => QueryFriendRequestsRequest._();
  QueryFriendRequestsRequest createEmptyInstance() => create();
  static $pb.PbList<QueryFriendRequestsRequest> createRepeated() =>
      $pb.PbList<QueryFriendRequestsRequest>();
  @$core.pragma('dart2js:noInline')
  static QueryFriendRequestsRequest getDefault() => _defaultInstance ??=
      $pb.GeneratedMessage.$_defaultFor<QueryFriendRequestsRequest>(create);
  static QueryFriendRequestsRequest? _defaultInstance;

  @$pb.TagNumber(1)
  $core.bool get areSentByMe => $_getBF(0);
  @$pb.TagNumber(1)
  set areSentByMe($core.bool v) {
    $_setBool(0, v);
  }

  @$pb.TagNumber(1)
  $core.bool hasAreSentByMe() => $_has(0);
  @$pb.TagNumber(1)
  void clearAreSentByMe() => clearField(1);

  @$pb.TagNumber(2)
  $fixnum.Int64 get lastUpdatedDate => $_getI64(1);
  @$pb.TagNumber(2)
  set lastUpdatedDate($fixnum.Int64 v) {
    $_setInt64(1, v);
  }

  @$pb.TagNumber(2)
  $core.bool hasLastUpdatedDate() => $_has(1);
  @$pb.TagNumber(2)
  void clearLastUpdatedDate() => clearField(2);
}
