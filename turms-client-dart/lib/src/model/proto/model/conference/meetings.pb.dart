//
//  Generated code. Do not modify.
//  source: model/conference/meetings.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:core' as $core;

import 'package:protobuf/protobuf.dart' as $pb;

import 'meeting.pb.dart' as $0;

class Meetings extends $pb.GeneratedMessage {
  factory Meetings({
    $core.Iterable<$0.Meeting>? meetings,
  }) {
    final $result = create();
    if (meetings != null) {
      $result.meetings.addAll(meetings);
    }
    return $result;
  }
  Meetings._() : super();
  factory Meetings.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory Meetings.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);

  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      _omitMessageNames ? '' : 'Meetings',
      package: const $pb.PackageName(_omitMessageNames ? '' : 'im.turms.proto'),
      createEmptyInstance: create)
    ..pc<$0.Meeting>(1, _omitFieldNames ? '' : 'meetings', $pb.PbFieldType.PM,
        subBuilder: $0.Meeting.create)
    ..hasRequiredFields = false;

  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
      'Will be removed in next major version')
  Meetings clone() => Meetings()..mergeFromMessage(this);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
      'Will be removed in next major version')
  Meetings copyWith(void Function(Meetings) updates) =>
      super.copyWith((message) => updates(message as Meetings)) as Meetings;

  $pb.BuilderInfo get info_ => _i;

  @$core.pragma('dart2js:noInline')
  static Meetings create() => Meetings._();
  Meetings createEmptyInstance() => create();
  static $pb.PbList<Meetings> createRepeated() => $pb.PbList<Meetings>();
  @$core.pragma('dart2js:noInline')
  static Meetings getDefault() =>
      _defaultInstance ??= $pb.GeneratedMessage.$_defaultFor<Meetings>(create);
  static Meetings? _defaultInstance;

  @$pb.TagNumber(1)
  $core.List<$0.Meeting> get meetings => $_getList(0);
}

const _omitFieldNames = $core.bool.fromEnvironment('protobuf.omit_field_names');
const _omitMessageNames =
    $core.bool.fromEnvironment('protobuf.omit_message_names');
