import 'package:fixnum/fixnum.dart';
import 'package:markdown/markdown.dart';

import 'message_type.dart';

class MessageInfo {
  const MessageInfo(
      {required this.type,
      required this.nodes,
      this.originalUrl,
      this.originalWidth,
      this.originalHeight,
      this.mentionAll,
      this.mentionedUserIds});

  final MessageType type;

  final List<Node> nodes;

  // TODO: no turms server support generate thumbnail yet,
  // so there is no point to use thumbnailUrl currently.
  // final String? thumbnailUrl;
  final String? originalUrl;
  final double? originalWidth;
  final double? originalHeight;

  final bool? mentionAll;
  final Set<Int64>? mentionedUserIds;
}
