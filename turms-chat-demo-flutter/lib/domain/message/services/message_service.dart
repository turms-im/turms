import 'package:fixnum/fixnum.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:markdown/markdown.dart';

import '../../../infra/markdown/mention_syntax.dart';
import '../../../infra/markdown/resource_syntax.dart';
import '../../../ui/desktop/pages/home_page/chat_page/chat_session_pane/message.dart';
import '../models/message_info.dart';
import '../models/message_type.dart';
import '../repositories/message_repository.dart';

final _document = Document(
  withDefaultBlockSyntaxes: false,
  withDefaultInlineSyntaxes: false,
  encodeHtml: false,
  inlineSyntaxes: [ResourceSyntax(), MentionSyntax()],
);

class MessageService {
  const MessageService(this._messageRepository);

  final MessageRepository _messageRepository;

  /// Note that we reference Markdown's syntax, but we don't follow it exactly. e.g.:
  /// we use "![http://example.com/thumbnail.png|100x100](http://example.com/video.mp4)" instead of
  /// "[![|100x100](http://example.com/thumbnail.png)](http://example.com/video.mp4)" to represent video
  /// as the previous one is concise and consistent with our use cases.
  ///
  /// Image: "![http://example.com/thumbnail.png|100x100](http://example.com/original_image.png)"
  /// Audio: "![http://example.com/thumbnail.png|100x100](http://example.com/video.mp3)"
  /// Video: "![http://example.com/thumbnail.png|100x100](http://example.com/video.mp4)"
  static MessageInfo parseMessageInfo(String? text) {
    if (text == null || text.isEmpty) {
      return const MessageInfo(type: MessageType.text, nodes: []);
    }
    final nodes = _document.parseInline(text);
    final nodeCount = nodes.length;
    assert(nodeCount > 0, 'Invalid message text');
    if (nodeCount == 1) {
      final firstNode = nodes.first;
      if (firstNode case final Element element) {
        if (element.tag == ResourceSyntax.tag) {
          return _parseResourceOrTextMessage(nodes, element);
        }
      }
      return _parseTextMessageFromNode(nodes, firstNode);
    }
    return _parseTextMessageFromNodes(nodes);
  }

  static MessageInfo _parseResourceOrTextMessage(
    List<Node> nodes,
    Element element,
  ) {
    final src = element.attributes[ResourceSyntax.attributeSrc];
    if (src == null) {
      return _parseTextMessageFromNode(nodes, element);
    }
    final alt = element.attributes[ResourceSyntax.attributeAlt];
    if (alt == null) {
      return _parseTextMessageFromNode(nodes, element);
    }
    if (src.contains('//www.youtube.com/') || src.contains('//youtube.com/')) {
      return MessageInfo(
        type: MessageType.youtube,
        originalUrl: src,
        nodes: nodes,
      );
    } else if (src.endsWith('.mp4') ||
        src.endsWith('.mov') ||
        src.endsWith('.avi')) {
      final sizeDividerIndex = alt.lastIndexOf('|');
      if (sizeDividerIndex < 0) {
        return _parseTextMessageFromNode(nodes, element);
      }
      final xIndex = alt.lastIndexOf('x');
      if (xIndex < 0 || xIndex <= sizeDividerIndex) {
        return _parseTextMessageFromNode(nodes, element);
      }
      final width = double.tryParse(
        alt.substring(sizeDividerIndex + 1, xIndex),
      );
      if (width == null) {
        return _parseTextMessageFromNode(nodes, element);
      }
      final height = double.tryParse(alt.substring(xIndex + 1));
      if (height == null) {
        return _parseTextMessageFromNode(nodes, element);
      }
      return MessageInfo(
        type: MessageType.video,
        originalUrl: src,
        originalHeight: height.floorToDouble(),
        originalWidth: width.floorToDouble(),
        nodes: nodes,
      );
    } else if (src.endsWith('.mp3') || src.endsWith('.wav')) {
      return MessageInfo(
        type: MessageType.audio,
        originalUrl: src,
        nodes: nodes,
      );
    } else if (_isSupportedImageType(src)) {
      final sizeDividerIndex = alt.lastIndexOf('|');
      if (sizeDividerIndex < 0) {
        return _parseTextMessageFromNode(nodes, element);
      }
      final xIndex = alt.indexOf('x', sizeDividerIndex + 1);
      if (xIndex < 0) {
        return _parseTextMessageFromNode(nodes, element);
      }
      final width = double.tryParse(
        alt.substring(sizeDividerIndex + 1, xIndex),
      );
      if (width == null) {
        return _parseTextMessageFromNode(nodes, element);
      }
      final height = double.tryParse(alt.substring(xIndex + 1));
      if (height == null) {
        return _parseTextMessageFromNode(nodes, element);
      }
      return MessageInfo(
        type: MessageType.image,
        originalUrl: src,
        originalHeight: height.floorToDouble(),
        originalWidth: width.floorToDouble(),
        nodes: nodes,
      );
    } else {
      return MessageInfo(
        type: MessageType.file,
        originalUrl: src,
        nodes: nodes,
      );
    }
  }

  static MessageInfo _parseTextMessageFromNodes(List<Node> nodes) {
    var mentionAll = false;
    final mentionedUserIds = <Int64>{};
    for (final node in nodes) {
      if (node is Element && node.tag == MentionSyntax.tag) {
        final value = node.attributes.values.first;
        if (value == MentionSyntax.mentionAllValue) {
          mentionAll = true;
        } else {
          if (Int64.tryParseInt(value) case final userId?) {
            mentionedUserIds.add(userId);
          }
        }
      }
    }
    return MessageInfo(
      type: MessageType.text,
      nodes: nodes,
      mentionAll: mentionAll,
      mentionedUserIds: mentionedUserIds,
    );
  }

  static MessageInfo _parseTextMessageFromNode(List<Node> nodes, Node node) {
    var mentionAll = false;
    final mentionedUserIds = <Int64>{};
    if (node is Element && node.tag == MentionSyntax.tag) {
      final value = node.attributes.values.first;
      if (value == MentionSyntax.mentionAllValue) {
        mentionAll = true;
      } else {
        final userId = Int64.tryParseInt(value);
        if (userId != null) {
          mentionedUserIds.add(userId);
        }
      }
    }
    return MessageInfo(
      type: MessageType.text,
      nodes: nodes,
      mentionAll: mentionAll,
      mentionedUserIds: mentionedUserIds,
    );
  }

  static bool _isSupportedImageType(String originalUrl) =>
      originalUrl.endsWith('.png') ||
      originalUrl.endsWith('.jpg') ||
      originalUrl.endsWith('.jpeg') ||
      originalUrl.endsWith('.gif') ||
      originalUrl.endsWith('.webp');

  static String encodeImageMessage({
    required String originalUrl,
    required String thumbnailUrl,
    required int width,
    required int height,
  }) => '![$thumbnailUrl|${width}x$height]($originalUrl)';

  Future<ChatMessage> sendMessage(String text, ChatMessage message) async {
    await Future<void>.delayed(const Duration(seconds: 1));
    return message;
  }

  Future<List<ChatMessage>> queryMoreMessages() async {
    await Future<void>.delayed(const Duration(seconds: 2));
    // final messageTexts =  List<String>.generate(
    //     10, (index) => 'New Message $index');
    return List.empty();
  }

  Future<List<ChatMessage>> searchMessages({
    required Int64 loggedInUserId,
    Int64? idStart,
    Int64? groupId,
    List<Int64>? participantIds,
    String? text,
    MessageType? messageType,
    DateTime? createdDateStart,
    DateTime? createdDateEnd,
    required int limit,
  }) async {
    final messageRecords = await _messageRepository.selectMessages(
      idStart: idStart,
      groupId: groupId,
      participantIds: participantIds,
      text: text,
      messageType: messageType,
      createdDateStart: createdDateStart,
      createdDateEnd: createdDateEnd,
      limit: limit,
    );
    return messageRecords
        .map((e) => ChatMessage.fromMessageTableData(e, loggedInUserId))
        .toList();
  }
}

final messageServiceProvider = StateProvider<MessageService?>((ref) => null);
