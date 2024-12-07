import 'package:markdown/markdown.dart';

class ResourceSyntax extends InlineSyntax {
  ResourceSyntax() : super(r'^!\[(.*?)]\((https?:\/\/\S+?\.\S+?)\)$');

  static const tag = 'resource';
  static const attributeAlt = 'alt';
  static const attributeSrc = 'src';

  @override
  bool onMatch(InlineParser parser, Match match) {
    final element = Element.empty(tag);
    element.attributes[attributeAlt] = match[1].toString();
    element.attributes[attributeSrc] = match[2].toString();
    parser.addNode(element);
    return true;
  }
}
