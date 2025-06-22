import 'dart:math';

import 'package:flutter/rendering.dart';
import 'package:flutter/widgets.dart';

class ScrollUtils {
  ScrollUtils._();

  static void ensureRenderBoxVisible({required RenderBox renderBox}) {
    final viewport =
        RenderAbstractViewport.maybeOf(renderBox) as RenderViewportBase?;
    if (viewport == null) {
      return;
    }
    final leadingEdgeOffset = viewport.getOffsetToReveal(renderBox, 0.0);
    final trailingEdgeOffset = viewport.getOffsetToReveal(renderBox, 1.0);
    final currentOffset = viewport.offset.pixels;
    final targetOffset = RevealedOffset.clampOffset(
      leadingEdgeOffset: leadingEdgeOffset,
      trailingEdgeOffset: trailingEdgeOffset,
      currentOffset: currentOffset,
    );
    if (targetOffset == null) {
      // return if already fully visible
      return;
    }
    viewport.offset.jumpTo(targetOffset.offset);
  }

  static void ensureVisible({
    required ScrollController controller,
    double? viewportDimension,
    required double itemOffset,
    required double itemHeight,
  }) {
    viewportDimension ??= controller.position.viewportDimension;
    if (checkIfOffsetInViewport(
      controller,
      viewportDimension,
      itemOffset,
      itemHeight,
    )) {
      return;
    }
    final moveOffset1 = controller.offset - itemOffset;
    if (moveOffset1 < 0) {
      controller.jumpTo(
        min(
          controller.position.maxScrollExtent,
          itemOffset + itemHeight - viewportDimension,
        ),
      );
    } else {
      final moveOffset2 =
          itemOffset + itemHeight - controller.offset - viewportDimension;
      if (moveOffset2 < 0 || moveOffset1 < moveOffset2) {
        controller.jumpTo(max(controller.position.minScrollExtent, itemOffset));
      } else {
        controller.jumpTo(
          min(
            controller.position.maxScrollExtent,
            itemOffset + itemHeight - viewportDimension,
          ),
        );
      }
    }
  }

  static bool checkIfOffsetInViewport(
    ScrollController controller,
    double viewportDimension,
    double itemOffset,
    double itemHeight,
  ) {
    final offset = controller.offset;
    return offset <= itemOffset &&
        (itemOffset + itemHeight) <= (offset + viewportDimension);
  }
}
