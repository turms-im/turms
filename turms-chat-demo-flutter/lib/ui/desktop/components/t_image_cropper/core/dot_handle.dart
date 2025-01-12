import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

class DotHandle extends StatelessWidget {
  const DotHandle({
    Key? key,
    required this.position,
    required this.dimension,
    this.padding = 4,
    this.color = Colors.white,
  })  : assert(dimension > padding * 2),
        super(key: key);

  final DotHandlePosition position;
  final double dimension;
  final double padding;
  final Color color;

  @override
  Widget build(BuildContext context) {
    final dotDimension = dimension - padding * 2;
    return MouseRegion(
      cursor: position.cursor,
      child: SizedBox(
        width: dimension,
        height: dimension,
        child: Center(
          child: ClipRRect(
            borderRadius: BorderRadius.circular(dimension),
            child: SizedBox(
              width: dotDimension,
              height: dotDimension,
              child: ColoredBox(
                color: color,
              ),
            ),
          ),
        ),
      ),
    );
  }
}

enum DotHandlePosition {
  topLeft(SystemMouseCursors.resizeUpLeftDownRight),
  topRight(SystemMouseCursors.resizeUpRightDownLeft),
  bottomLeft(SystemMouseCursors.resizeUpRightDownLeft),
  bottomRight(SystemMouseCursors.resizeUpLeftDownRight);

  const DotHandlePosition(this.cursor);

  final SystemMouseCursor cursor;
}
