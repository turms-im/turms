import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

class DotHandle extends StatelessWidget {
  const DotHandle({
    super.key,
    required this.position,
    required this.dimension,
    this.padding = 4,
    this.color = Colors.white,
  }) : assert(dimension > padding * 2);

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
              child: ColoredBox(color: color),
            ),
          ),
        ),
      ),
    );
  }

  @override
  void debugFillProperties(DiagnosticPropertiesBuilder properties) {
    super.debugFillProperties(properties);
    properties
      ..add(EnumProperty<DotHandlePosition>('position', position))
      ..add(DoubleProperty('dimension', dimension))
      ..add(DoubleProperty('padding', padding))
      ..add(DiagnosticsProperty<Color>('color', color));
  }
}

enum DotHandlePosition {
  topLeft(SystemMouseCursors.resizeUpLeftDownRight),
  topRight(SystemMouseCursors.resizeUpRightDownLeft),
  bottomRight(SystemMouseCursors.resizeUpLeftDownRight),
  bottomLeft(SystemMouseCursors.resizeUpRightDownLeft);

  const DotHandlePosition(this.cursor);

  final SystemMouseCursor cursor;

  DotHandlePosition next() =>
      DotHandlePosition.values[(index + 1) % DotHandlePosition.values.length];

  DotHandlePosition flipX() => switch (this) {
    DotHandlePosition.topLeft => DotHandlePosition.topRight,
    DotHandlePosition.topRight => DotHandlePosition.topLeft,
    DotHandlePosition.bottomLeft => DotHandlePosition.bottomRight,
    DotHandlePosition.bottomRight => DotHandlePosition.bottomLeft,
  };

  DotHandlePosition flipY() => switch (this) {
    DotHandlePosition.topLeft => DotHandlePosition.bottomLeft,
    DotHandlePosition.topRight => DotHandlePosition.bottomRight,
    DotHandlePosition.bottomLeft => DotHandlePosition.topLeft,
    DotHandlePosition.bottomRight => DotHandlePosition.topRight,
  };

  DotHandlePosition rotate(double angle) => switch ((angle % 360) / 90) {
    0 => DotHandlePosition.topLeft,
    1 => DotHandlePosition.topRight,
    2 => DotHandlePosition.bottomRight,
    3 => DotHandlePosition.bottomLeft,
    _ => throw UnsupportedError('Unsupported rotation angle: $angle'),
  };
}
