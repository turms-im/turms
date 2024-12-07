import 'package:flutter/material.dart';

import '../../../../../../../domain/user/models/index.dart';
import '../../../../../../themes/index.dart';
import 'message_bubble.dart';

// TODO: https://allthings.how/how-to-quote-or-reply-to-a-message-in-teams-chat/
class QuotedMessage extends StatelessWidget {
  const QuotedMessage(
      {super.key, required this.user, required this.quotedMessageBubble});

  final User user;
  final MessageBubble quotedMessageBubble;

  @override
  Widget build(BuildContext context) {
    const left = 4.0;
    return DecoratedBox(
        decoration: BoxDecoration(
            color: Colors.grey.shade200,
            border: Border(
                left: BorderSide(color: Colors.grey[350]!, width: left))),
        child: Padding(
          padding: Sizes.paddingV4H8.add(const EdgeInsets.only(left: left)),
          child: Column(
            children: [
              Text(user.name),
              quotedMessageBubble,
            ],
          ),
        ));
  }
}
