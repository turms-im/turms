//
//  Generated code. Do not modify.
//  source: model/message/messages.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:core' as $core;

import 'package:protobuf/protobuf.dart' as $pb;

import 'message.pb.dart' as $0;

class Messages extends $pb.GeneratedMessage {
  factory Messages({
    $core.Iterable<$0.Message>? messages,
  }) {
    final $result = create();
    if (messages != null) {
      $result.messages.addAll(messages);
    }
    return $result;
  }
  Messages._() : super();
  factory Messages.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory Messages.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);

  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      _omitMessageNames ? '' : 'Messages',
      package: const $pb.PackageName(_omitMessageNames ? '' : 'im.turms.proto'),
      createEmptyInstance: create)
    ..pc<$0.Message>(1, _omitFieldNames ? '' : 'messages', $pb.PbFieldType.PM,
        subBuilder: $0.Message.create)
    ..hasRequiredFields = false;

  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
      'Will be removed in next major version')
  Messages clone() => Messages()..mergeFromMessage(this);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
      'Will be removed in next major version')
  Messages copyWith(void Function(Messages) updates) =>
      super.copyWith((message) => updates(message as Messages)) as Messages;

  $pb.BuilderInfo get info_ => _i;

  @$core.pragma('dart2js:noInline')
  static Messages create() => Messages._();
  Messages createEmptyInstance() => create();
  static $pb.PbList<Messages> createRepeated() => $pb.PbList<Messages>();
  @$core.pragma('dart2js:noInline')
  static Messages getDefault() =>
      _defaultInstance ??= $pb.GeneratedMessage.$_defaultFor<Messages>(create);
  static Messages? _defaultInstance;

  @$pb.TagNumber(1)
  $core.List<$0.Message> get messages => $_getList(0);
}

const _omitFieldNames = $core.bool.fromEnvironment('protobuf.omit_field_names');
const _omitMessageNames =
    $core.bool.fromEnvironment('protobuf.omit_message_names');
