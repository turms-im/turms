import 'package:flutter/material.dart';
import 'package:material_symbols_icons/material_symbols_icons.dart';

import '../../../../../../../domain/user/models/user.dart';
import '../../../../../../themes/index.dart';

import '../../../../../components/index.dart';
import '../chat_session_pane_footer/message_editor.dart';
import '../message.dart';

class MessageBubbleText extends StatefulWidget {
  const MessageBubbleText({
    super.key,
    required this.currentUser,
    required this.message,
    required this.availableWidth,
    required this.borderRadius,
  });

  final User currentUser;
  final ChatMessage message;
  final double availableWidth;
  final BorderRadius borderRadius;

  @override
  State<MessageBubbleText> createState() => _MessageBubbleTextState();
}

const _mentionIconSize = 22.0;

class _MessageBubbleTextState extends State<MessageBubbleText> {
  late bool _isMentioned;
  TextSpan? _textSpan;

  @override
  void initState() {
    super.initState();
    final message = widget.message;
    _isMentioned =
        !message.sentByMe &&
        (message.mentionAll ||
            message.mentionedUserIds.contains(widget.currentUser.userId));
  }

  @override
  Widget build(BuildContext context) {
    final appThemeExtension = context.appThemeExtension;
    _textSpan ??= generateTextSpan(
      appThemeExtension,
      null,
      widget.message.text!,
    );
    const color = Color.fromARGB(255, 204, 74, 49);
    Widget content = IntrinsicWidth(
      child: DecoratedBox(
        decoration: BoxDecoration(
          color: widget.message.sentByMe
              ? const Color.fromARGB(255, 149, 216, 248)
              : Colors.white,
          borderRadius: widget.borderRadius,
          border: _isMentioned
              ? const Border(left: BorderSide(color: color, width: 4))
              : null,
        ),
        child: ConstrainedBox(
          constraints: BoxConstraints(maxWidth: widget.availableWidth * 0.6),
          child: Padding(
            padding: Sizes.paddingV8H8,
            child: TText.rich(
              _textSpan!,
              strutStyle: StrutStyle.fromTextStyle(
                appThemeExtension.chatSessionMessageTextStyle,
                forceStrutHeight: true,
              ),
              // TODO: Wait for:
              // 1. https://github.com/flutter/flutter/pull/140982
              // 2. https://github.com/flutter/flutter/issues/104547
              // selectionHeightStyle: BoxHeightStyle.max,
            ),
          ),
        ),
      ),
    );

    if (_isMentioned) {
      content = Stack(
        clipBehavior: Clip.none,
        children: [
          content,
          const Positioned(
            top: 0,
            bottom: 0,
            right: -_mentionIconSize / 2,
            child: SizedBox(
              width: _mentionIconSize,
              height: _mentionIconSize,
              child: DecoratedBox(
                decoration: BoxDecoration(color: color, shape: BoxShape.circle),
                child: Icon(
                  Symbols.alternate_email_rounded,
                  color: Colors.white,
                  size: _mentionIconSize - 6,
                ),
              ),
            ),
          ),
        ],
      );
    }
    return content;
  }
}
