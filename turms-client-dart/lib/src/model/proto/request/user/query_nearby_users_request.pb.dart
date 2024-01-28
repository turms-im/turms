//
//  Generated code. Do not modify.
//  source: request/user/query_nearby_users_request.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:core' as $core;

import 'package:protobuf/protobuf.dart' as $pb;

class QueryNearbyUsersRequest extends $pb.GeneratedMessage {
  factory QueryNearbyUsersRequest({
    $core.double? latitude,
    $core.double? longitude,
    $core.int? maxCount,
    $core.int? maxDistance,
    $core.bool? withCoordinates,
    $core.bool? withDistance,
    $core.bool? withUserInfo,
  }) {
    final $result = create();
    if (latitude != null) {
      $result.latitude = latitude;
    }
    if (longitude != null) {
      $result.longitude = longitude;
    }
    if (maxCount != null) {
      $result.maxCount = maxCount;
    }
    if (maxDistance != null) {
      $result.maxDistance = maxDistance;
    }
    if (withCoordinates != null) {
      $result.withCoordinates = withCoordinates;
    }
    if (withDistance != null) {
      $result.withDistance = withDistance;
    }
    if (withUserInfo != null) {
      $result.withUserInfo = withUserInfo;
    }
    return $result;
  }
  QueryNearbyUsersRequest._() : super();
  factory QueryNearbyUsersRequest.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory QueryNearbyUsersRequest.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);

  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      _omitMessageNames ? '' : 'QueryNearbyUsersRequest',
      package: const $pb.PackageName(_omitMessageNames ? '' : 'im.turms.proto'),
      createEmptyInstance: create)
    ..a<$core.double>(1, _omitFieldNames ? '' : 'latitude', $pb.PbFieldType.OF)
    ..a<$core.double>(2, _omitFieldNames ? '' : 'longitude', $pb.PbFieldType.OF)
    ..a<$core.int>(3, _omitFieldNames ? '' : 'maxCount', $pb.PbFieldType.O3)
    ..a<$core.int>(4, _omitFieldNames ? '' : 'maxDistance', $pb.PbFieldType.O3)
    ..aOB(5, _omitFieldNames ? '' : 'withCoordinates')
    ..aOB(6, _omitFieldNames ? '' : 'withDistance')
    ..aOB(7, _omitFieldNames ? '' : 'withUserInfo')
    ..hasRequiredFields = false;

  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
      'Will be removed in next major version')
  QueryNearbyUsersRequest clone() =>
      QueryNearbyUsersRequest()..mergeFromMessage(this);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
      'Will be removed in next major version')
  QueryNearbyUsersRequest copyWith(
          void Function(QueryNearbyUsersRequest) updates) =>
      super.copyWith((message) => updates(message as QueryNearbyUsersRequest))
          as QueryNearbyUsersRequest;

  $pb.BuilderInfo get info_ => _i;

  @$core.pragma('dart2js:noInline')
  static QueryNearbyUsersRequest create() => QueryNearbyUsersRequest._();
  QueryNearbyUsersRequest createEmptyInstance() => create();
  static $pb.PbList<QueryNearbyUsersRequest> createRepeated() =>
      $pb.PbList<QueryNearbyUsersRequest>();
  @$core.pragma('dart2js:noInline')
  static QueryNearbyUsersRequest getDefault() => _defaultInstance ??=
      $pb.GeneratedMessage.$_defaultFor<QueryNearbyUsersRequest>(create);
  static QueryNearbyUsersRequest? _defaultInstance;

  @$pb.TagNumber(1)
  $core.double get latitude => $_getN(0);
  @$pb.TagNumber(1)
  set latitude($core.double v) {
    $_setFloat(0, v);
  }

  @$pb.TagNumber(1)
  $core.bool hasLatitude() => $_has(0);
  @$pb.TagNumber(1)
  void clearLatitude() => clearField(1);

  @$pb.TagNumber(2)
  $core.double get longitude => $_getN(1);
  @$pb.TagNumber(2)
  set longitude($core.double v) {
    $_setFloat(1, v);
  }

  @$pb.TagNumber(2)
  $core.bool hasLongitude() => $_has(1);
  @$pb.TagNumber(2)
  void clearLongitude() => clearField(2);

  @$pb.TagNumber(3)
  $core.int get maxCount => $_getIZ(2);
  @$pb.TagNumber(3)
  set maxCount($core.int v) {
    $_setSignedInt32(2, v);
  }

  @$pb.TagNumber(3)
  $core.bool hasMaxCount() => $_has(2);
  @$pb.TagNumber(3)
  void clearMaxCount() => clearField(3);

  @$pb.TagNumber(4)
  $core.int get maxDistance => $_getIZ(3);
  @$pb.TagNumber(4)
  set maxDistance($core.int v) {
    $_setSignedInt32(3, v);
  }

  @$pb.TagNumber(4)
  $core.bool hasMaxDistance() => $_has(3);
  @$pb.TagNumber(4)
  void clearMaxDistance() => clearField(4);

  @$pb.TagNumber(5)
  $core.bool get withCoordinates => $_getBF(4);
  @$pb.TagNumber(5)
  set withCoordinates($core.bool v) {
    $_setBool(4, v);
  }

  @$pb.TagNumber(5)
  $core.bool hasWithCoordinates() => $_has(4);
  @$pb.TagNumber(5)
  void clearWithCoordinates() => clearField(5);

  @$pb.TagNumber(6)
  $core.bool get withDistance => $_getBF(5);
  @$pb.TagNumber(6)
  set withDistance($core.bool v) {
    $_setBool(5, v);
  }

  @$pb.TagNumber(6)
  $core.bool hasWithDistance() => $_has(5);
  @$pb.TagNumber(6)
  void clearWithDistance() => clearField(6);

  @$pb.TagNumber(7)
  $core.bool get withUserInfo => $_getBF(6);
  @$pb.TagNumber(7)
  set withUserInfo($core.bool v) {
    $_setBool(6, v);
  }

  @$pb.TagNumber(7)
  $core.bool hasWithUserInfo() => $_has(6);
  @$pb.TagNumber(7)
  void clearWithUserInfo() => clearField(7);
}

const _omitFieldNames = $core.bool.fromEnvironment('protobuf.omit_field_names');
const _omitMessageNames =
    $core.bool.fromEnvironment('protobuf.omit_message_names');
