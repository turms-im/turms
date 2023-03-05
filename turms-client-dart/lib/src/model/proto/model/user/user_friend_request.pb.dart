///
//  Generated code. Do not modify.
//  source: model/user/user_friend_request.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,constant_identifier_names,directives_ordering,library_prefixes,non_constant_identifier_names,prefer_final_fields,return_of_invalid_type,unnecessary_const,unnecessary_import,unnecessary_this,unused_import,unused_shown_name

import 'dart:core' as $core;

import 'package:fixnum/fixnum.dart' as $fixnum;
import 'package:protobuf/protobuf.dart' as $pb;

import '../../constant/request_status.pbenum.dart' as $0;

class UserFriendRequest extends $pb.GeneratedMessage {
  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      const $core.bool.fromEnvironment('protobuf.omit_message_names')
          ? ''
          : 'UserFriendRequest',
      package: const $pb.PackageName(
          const $core.bool.fromEnvironment('protobuf.omit_message_names')
              ? ''
              : 'im.turms.proto'),
      createEmptyInstance: create)
    ..aInt64(
        1,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'id')
    ..aInt64(
        2,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'creationDate')
    ..aOS(
        3,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'content')
    ..e<$0.RequestStatus>(
        4,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'requestStatus',
        $pb.PbFieldType.OE,
        defaultOrMaker: $0.RequestStatus.PENDING,
        valueOf: $0.RequestStatus.valueOf,
        enumValues: $0.RequestStatus.values)
    ..aOS(
        5,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'reason')
    ..aInt64(
        6,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'expirationDate')
    ..aInt64(
        7,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'requesterId')
    ..aInt64(
        8,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'recipientId')
    ..hasRequiredFields = false;

  UserFriendRequest._() : super();
  factory UserFriendRequest({
    $fixnum.Int64? id,
    $fixnum.Int64? creationDate,
    $core.String? content,
    $0.RequestStatus? requestStatus,
    $core.String? reason,
    $fixnum.Int64? expirationDate,
    $fixnum.Int64? requesterId,
    $fixnum.Int64? recipientId,
  }) {
    final _result = create();
    if (id != null) {
      _result.id = id;
    }
    if (creationDate != null) {
      _result.creationDate = creationDate;
    }
    if (content != null) {
      _result.content = content;
    }
    if (requestStatus != null) {
      _result.requestStatus = requestStatus;
    }
    if (reason != null) {
      _result.reason = reason;
    }
    if (expirationDate != null) {
      _result.expirationDate = expirationDate;
    }
    if (requesterId != null) {
      _result.requesterId = requesterId;
    }
    if (recipientId != null) {
      _result.recipientId = recipientId;
    }
    return _result;
  }
  factory UserFriendRequest.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory UserFriendRequest.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
      'Will be removed in next major version')
  UserFriendRequest clone() => UserFriendRequest()..mergeFromMessage(this);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
      'Will be removed in next major version')
  UserFriendRequest copyWith(void Function(UserFriendRequest) updates) =>
      super.copyWith((message) => updates(message as UserFriendRequest))
          as UserFriendRequest; // ignore: deprecated_member_use
  $pb.BuilderInfo get info_ => _i;
  @$core.pragma('dart2js:noInline')
  static UserFriendRequest create() => UserFriendRequest._();
  UserFriendRequest createEmptyInstance() => create();
  static $pb.PbList<UserFriendRequest> createRepeated() =>
      $pb.PbList<UserFriendRequest>();
  @$core.pragma('dart2js:noInline')
  static UserFriendRequest getDefault() => _defaultInstance ??=
      $pb.GeneratedMessage.$_defaultFor<UserFriendRequest>(create);
  static UserFriendRequest? _defaultInstance;

  @$pb.TagNumber(1)
  $fixnum.Int64 get id => $_getI64(0);
  @$pb.TagNumber(1)
  set id($fixnum.Int64 v) {
    $_setInt64(0, v);
  }

  @$pb.TagNumber(1)
  $core.bool hasId() => $_has(0);
  @$pb.TagNumber(1)
  void clearId() => clearField(1);

  @$pb.TagNumber(2)
  $fixnum.Int64 get creationDate => $_getI64(1);
  @$pb.TagNumber(2)
  set creationDate($fixnum.Int64 v) {
    $_setInt64(1, v);
  }

  @$pb.TagNumber(2)
  $core.bool hasCreationDate() => $_has(1);
  @$pb.TagNumber(2)
  void clearCreationDate() => clearField(2);

  @$pb.TagNumber(3)
  $core.String get content => $_getSZ(2);
  @$pb.TagNumber(3)
  set content($core.String v) {
    $_setString(2, v);
  }

  @$pb.TagNumber(3)
  $core.bool hasContent() => $_has(2);
  @$pb.TagNumber(3)
  void clearContent() => clearField(3);

  @$pb.TagNumber(4)
  $0.RequestStatus get requestStatus => $_getN(3);
  @$pb.TagNumber(4)
  set requestStatus($0.RequestStatus v) {
    setField(4, v);
  }

  @$pb.TagNumber(4)
  $core.bool hasRequestStatus() => $_has(3);
  @$pb.TagNumber(4)
  void clearRequestStatus() => clearField(4);

  @$pb.TagNumber(5)
  $core.String get reason => $_getSZ(4);
  @$pb.TagNumber(5)
  set reason($core.String v) {
    $_setString(4, v);
  }

  @$pb.TagNumber(5)
  $core.bool hasReason() => $_has(4);
  @$pb.TagNumber(5)
  void clearReason() => clearField(5);

  @$pb.TagNumber(6)
  $fixnum.Int64 get expirationDate => $_getI64(5);
  @$pb.TagNumber(6)
  set expirationDate($fixnum.Int64 v) {
    $_setInt64(5, v);
  }

  @$pb.TagNumber(6)
  $core.bool hasExpirationDate() => $_has(5);
  @$pb.TagNumber(6)
  void clearExpirationDate() => clearField(6);

  @$pb.TagNumber(7)
  $fixnum.Int64 get requesterId => $_getI64(6);
  @$pb.TagNumber(7)
  set requesterId($fixnum.Int64 v) {
    $_setInt64(6, v);
  }

  @$pb.TagNumber(7)
  $core.bool hasRequesterId() => $_has(6);
  @$pb.TagNumber(7)
  void clearRequesterId() => clearField(7);

  @$pb.TagNumber(8)
  $fixnum.Int64 get recipientId => $_getI64(7);
  @$pb.TagNumber(8)
  set recipientId($fixnum.Int64 v) {
    $_setInt64(7, v);
  }

  @$pb.TagNumber(8)
  $core.bool hasRecipientId() => $_has(7);
  @$pb.TagNumber(8)
  void clearRecipientId() => clearField(8);
}
