//
//  Generated code. Do not modify.
//  source: model/conversation/private_conversation.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:core' as $core;

import 'package:fixnum/fixnum.dart' as $fixnum;
import 'package:protobuf/protobuf.dart' as $pb;

class PrivateConversation extends $pb.GeneratedMessage {
  factory PrivateConversation({
    $fixnum.Int64? ownerId,
    $fixnum.Int64? targetId,
    $fixnum.Int64? readDate,
  }) {
    final $result = create();
    if (ownerId != null) {
      $result.ownerId = ownerId;
    }
    if (targetId != null) {
      $result.targetId = targetId;
    }
    if (readDate != null) {
      $result.readDate = readDate;
    }
    return $result;
  }
  PrivateConversation._() : super();
  factory PrivateConversation.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory PrivateConversation.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);

  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      _omitMessageNames ? '' : 'PrivateConversation',
      package: const $pb.PackageName(_omitMessageNames ? '' : 'im.turms.proto'),
      createEmptyInstance: create)
    ..aInt64(1, _omitFieldNames ? '' : 'ownerId')
    ..aInt64(2, _omitFieldNames ? '' : 'targetId')
    ..aInt64(3, _omitFieldNames ? '' : 'readDate')
    ..hasRequiredFields = false;

  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
      'Will be removed in next major version')
  PrivateConversation clone() => PrivateConversation()..mergeFromMessage(this);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
      'Will be removed in next major version')
  PrivateConversation copyWith(void Function(PrivateConversation) updates) =>
      super.copyWith((message) => updates(message as PrivateConversation))
          as PrivateConversation;

  $pb.BuilderInfo get info_ => _i;

  @$core.pragma('dart2js:noInline')
  static PrivateConversation create() => PrivateConversation._();
  PrivateConversation createEmptyInstance() => create();
  static $pb.PbList<PrivateConversation> createRepeated() =>
      $pb.PbList<PrivateConversation>();
  @$core.pragma('dart2js:noInline')
  static PrivateConversation getDefault() => _defaultInstance ??=
      $pb.GeneratedMessage.$_defaultFor<PrivateConversation>(create);
  static PrivateConversation? _defaultInstance;

  @$pb.TagNumber(1)
  $fixnum.Int64 get ownerId => $_getI64(0);
  @$pb.TagNumber(1)
  set ownerId($fixnum.Int64 v) {
    $_setInt64(0, v);
  }

  @$pb.TagNumber(1)
  $core.bool hasOwnerId() => $_has(0);
  @$pb.TagNumber(1)
  void clearOwnerId() => clearField(1);

  @$pb.TagNumber(2)
  $fixnum.Int64 get targetId => $_getI64(1);
  @$pb.TagNumber(2)
  set targetId($fixnum.Int64 v) {
    $_setInt64(1, v);
  }

  @$pb.TagNumber(2)
  $core.bool hasTargetId() => $_has(1);
  @$pb.TagNumber(2)
  void clearTargetId() => clearField(2);

  @$pb.TagNumber(3)
  $fixnum.Int64 get readDate => $_getI64(2);
  @$pb.TagNumber(3)
  set readDate($fixnum.Int64 v) {
    $_setInt64(2, v);
  }

  @$pb.TagNumber(3)
  $core.bool hasReadDate() => $_has(2);
  @$pb.TagNumber(3)
  void clearReadDate() => clearField(3);
}

const _omitFieldNames = $core.bool.fromEnvironment('protobuf.omit_field_names');
const _omitMessageNames =
    $core.bool.fromEnvironment('protobuf.omit_message_names');
