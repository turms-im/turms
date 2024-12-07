import 'package:markdown/markdown.dart';

class MentionSyntax extends InlineSyntax {
  MentionSyntax() : super(r'\B@(ALL|\d{1,19})\b');

  static const mentionAllValue = 'ALL';
  static const tag = 'mention';

  @override
  bool onMatch(InlineParser parser, Match match) {
    parser.addNode(Element.text(tag, match[1].toString()));
    return true;
  }
}
