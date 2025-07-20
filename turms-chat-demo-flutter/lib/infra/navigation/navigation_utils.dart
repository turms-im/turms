import 'package:flutter/cupertino.dart';
import 'package:flutter/services.dart';

class NavigationUtils {
  NavigationUtils._();

  static (KeyEventResult, int?) navigateByKeyEvent(
    KeyEvent event,
    int total,
    int? currentItemIndex,
  ) {
    if (event is! KeyDownEvent && event is! KeyRepeatEvent) {
      return (KeyEventResult.ignored, currentItemIndex);
    }
    final isArrowUp = event.logicalKey == LogicalKeyboardKey.arrowUp;
    final isArrowDown = event.logicalKey == LogicalKeyboardKey.arrowDown;
    if (!isArrowUp && !isArrowDown) {
      return (KeyEventResult.ignored, currentItemIndex);
    }
    if (total == 0) {
      return (KeyEventResult.handled, currentItemIndex);
    }
    final conversationTileItemIndex = currentItemIndex;
    if (isArrowUp) {
      if (conversationTileItemIndex == null) {
        return (KeyEventResult.handled, total - 1);
      } else if (conversationTileItemIndex > 0) {
        return (KeyEventResult.handled, conversationTileItemIndex - 1);
      } else {
        return (KeyEventResult.handled, total - 1);
      }
    } else {
      if (conversationTileItemIndex == null) {
        return (KeyEventResult.handled, 0);
      } else if (conversationTileItemIndex < total - 1) {
        return (KeyEventResult.handled, conversationTileItemIndex + 1);
      } else {
        return (KeyEventResult.handled, 0);
      }
    }
  }
}
